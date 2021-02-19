package code;

public final class Node {

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
            return value == other.value && (leftChild == null || leftChild.equals(other.leftChild))
                    && (rightChild == null || rightChild.equals(other.rightChild));
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int x = 31;
        int result = 1;
        result = x * result + value;
        if (leftChild != null)
            result = x * result + leftChild.value;
        if (rightChild != null)
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
