package exercise1;

public final class BinaryTree {

    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node Search(int value) { //поиск числа в дереве
        Node currentNode = root;
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue())
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();
            if (currentNode == null) return null;
        }
        return currentNode;
    }

    public void Insert(int value) { //добавление числа
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

    private Node FindHeir(Node newRoot) { //функция поиска преемника для случая с двумя потомками
        //преемником является либо правый потомок удаляемого узла (newRoot),
        //либо один из его (newRoot'а) левых потомков
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

    public void Remove(int value) { //удаление числа
        Node removingNode = root;
        Node parentNode = null;
        //удаление узла - обращение в null левого или правого потомка parentNode
        while (removingNode.getValue() != value) { //
            parentNode = removingNode;
            if (value < removingNode.getValue())
                removingNode = removingNode.getLeftChild();
            else
                removingNode = removingNode.getRightChild();
            if (removingNode == null) System.out.println("Node not found");
        }
        //removingNode - удаляемый узел
        //случай, когда у удаляемого узла нет потомков
        if (removingNode.getLeftChild() == null && removingNode.getRightChild() == null)
            //если removingNode - корневой, то через parentNode на него сослаться не удастся
            //проверяем этот случай отдельно
            if (root == removingNode) root = null;
            else if (parentNode.getLeftChild().getValue() == value) parentNode.setLeftChild(null);
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
            Node heir = FindHeir(removingNode.getRightChild()); //находим преемника
            if (removingNode == root) root = heir;
            else if (parentNode.getLeftChild() == removingNode) parentNode.setLeftChild(heir);
            else parentNode.setRightChild(heir);
            heir.setLeftChild(removingNode.getLeftChild());
        }
    }

    public Node FindLeftChild(int value) {
        return Search(value).getLeftChild();
    }

    public Node FindRightChild(int value) {
        return Search(value).getRightChild();
    }

    public Node FindParent(int value) {
        Node prevNode = null;
        Node currentNode = root;
        while (currentNode.getValue() != value) {
            prevNode = currentNode;
            if (value < currentNode.getValue())
                currentNode = currentNode.getLeftChild();
            else
                currentNode = currentNode.getRightChild();
            if (currentNode == null) return null;
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

}