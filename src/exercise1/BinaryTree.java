package exercise1;

public final class BinaryTree {

    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node search(int value) { //поиск узла в дереве
        Node currentNode = root;
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue())
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();
            if (currentNode == null) throw new IllegalStateException("Node not found!");
        }
        return currentNode;
    }

    public void insert(int value) { //добавление числа
        Node prevNode;
        if (root == null) root = new Node(value);
        else {
            Node currentNode = root;
            while (true) { //остановка цикла через break
                prevNode = currentNode;
                if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        prevNode.setLeftChild(new Node(value));
                        break;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        prevNode.setRightChild(new Node(value));
                        break;
                    }
                }
            }
        }
    }

    private Node findHeir(Node newRoot) { //функция поиска преемника для случая с двумя потомками
        //преемником является либо правый потомок удаляемого узла (newRoot),
        //либо один из его левых потомков
        Node heirParent = null;
        Node heir = null;
        Node currentNode = newRoot;
        while (currentNode != null) {
            heirParent = heir;
            heir = currentNode;
            currentNode = currentNode.getLeftChild();
        }
        if (heir != newRoot) {
            //если преемник не прямой потомок удаляемого узла, нужно правую ветку преемника привязать
            //как левую ветку к родителю преемника
            heirParent.setLeftChild(heir.getRightChild());
            //а правой веткой преемника становится правая ветка удаляемого узла
            heir.setRightChild(newRoot);
        }
        return heir;
    }

    public void remove(int value) { //удаление числа
        Node removingNode = root;
        Node parentNode = root;
        //удаление узла - обращение в null левого или правого потомка parentNode
        while (removingNode.getValue() != value) { //
            parentNode = removingNode;
            if (value < removingNode.getValue())
                removingNode = removingNode.getLeftChild();
            else
                removingNode = removingNode.getRightChild();
            if (removingNode == null) {
                throw new IllegalStateException("Node not found!");
            }
        }
        //removingNode - удаляемый узел
        //случай, когда у удаляемого узла нет потомков
        if (removingNode.getLeftChild() == null && removingNode.getRightChild() == null)
            //если removingNode - корневой, то через parentNode на него сослаться не удастся
            //проверяем этот случай отдельно
            if (root == removingNode) root = null;
            else if (parentNode.getLeftChild() == removingNode) parentNode.setLeftChild(null);
            else parentNode.setRightChild(null);
        else if (removingNode.getRightChild() == null) //случай, когда у удаляемого узла есть левый потомок
            if (root == removingNode) root = removingNode.getLeftChild();
            else if (parentNode.getLeftChild() == removingNode) parentNode.setLeftChild(removingNode.getLeftChild());
            else parentNode.setRightChild(removingNode.getLeftChild());
        else if (removingNode.getLeftChild() == null) //случай, когда у удаляемого узла есть правый потомок
            if (root == removingNode) root = removingNode.getRightChild();
            else if (parentNode.getLeftChild() == removingNode) parentNode.setLeftChild(removingNode.getRightChild());
            else parentNode.setRightChild(removingNode.getRightChild());
        else {   //случай, когда у удаляемого узла есть оба потомка
            Node heir = findHeir(removingNode.getRightChild()); //находим преемника
            if (removingNode == root) root = heir;
            else if (parentNode.getLeftChild() == removingNode) parentNode.setLeftChild(heir);
            else parentNode.setRightChild(heir);
            heir.setLeftChild(removingNode.getLeftChild());
        }
    }

    public Node findLeftChild(int value) {
        Node node = search(value);
        if (node == null) throw new IllegalStateException("Node not found!");
        else return node.getLeftChild();
    }

    public Node findRightChild(int value) {
        Node node = search(value);
        if (node == null) throw new IllegalStateException("Node not found!");
        else return node.getRightChild();
    }

    public Node findParent(int value) {
        Node prevNode = null;
        Node currentNode = root;
        while (currentNode.getValue() != value) {
            prevNode = currentNode;
            if (value < currentNode.getValue())
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();
            if (currentNode == null) throw new IllegalStateException("Node not found!");
        }
        return prevNode;
    }

}

class Node {

    private final int value;
    private Node leftChild;
    private Node rightChild;

    public Node(int value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getValue() {
        return value;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Node) {
            Node other = (Node) obj;
            if (leftChild == null && other.leftChild != null || leftChild != null && other.leftChild == null)
                return false;
            if (rightChild == null && other.rightChild != null || rightChild != null && other.rightChild == null)
                return false;
            return value == other.value && (leftChild == null || leftChild.value == other.leftChild.value)
                    && (rightChild == null || rightChild.value == other.rightChild.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int x = 31;
        int result = 1;
        result = x * result + value;
        result = x * result + leftChild.value;
        result = x * result + rightChild.value;
        return result;
    }

    @Override
    public String toString() {
        String output = "Value: " + value;
        if (leftChild != null) output += "\nLeftChild: " + leftChild.value;
        else output += "\nNo left child";
        if (rightChild != null) output += "\nRightChild: " + rightChild.value;
        else output += "\nNo right child";
        return output;
    }

}