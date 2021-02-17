package exercise1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    public BinaryTree createEmptyTree(int value) {
        return (new BinaryTree(new Node(value)));
    }

    public BinaryTree createFilledTree() {
        BinaryTree tree = createEmptyTree(50);
        tree.insert(25);
        tree.insert(35);
        tree.insert(15);
        tree.insert(75);
        tree.insert(65);
        tree.insert(85);
        tree.insert(100);
        tree.insert(105);
        tree.insert(110);
        tree.insert(115);
        tree.insert(10);
        tree.insert(52);
        tree.insert(1);
        tree.insert(113);
        return tree;
/*
                       50
                  25        75
                15  35    65  85
              10         52     100
             1                     105
                                     110
                                       115
                                     113
 */
    }

    @Test
    public void findParentAndChildrenTest() {
        BinaryTree tree = createFilledTree();
        int[] actual = new int[]{
                tree.findRightChild(75).getValue(),
                tree.findRightChild(50).getValue(),
                tree.findLeftChild(25).getValue(),
                tree.findLeftChild(115).getValue(),
                tree.findParent(52).getValue(),
                tree.findParent(35).getValue(),
                tree.findParent(105).getValue(),
        };
        int[] expected = new int[]{85, 75, 15, 113, 65, 25, 100};
        assertArrayEquals(actual, expected);
        tree = createEmptyTree(10);
        assertNull(tree.findParent(10));
        assertNull(tree.findLeftChild(10));
        assertNull(tree.findRightChild(10));
    }

    @Test
    public void FRCExceptionTest() {
        BinaryTree tree = createFilledTree();
        assertThrows(IllegalStateException.class, () -> tree.findRightChild(5));
    }

    @Test
    public void FPExceptionTest() {
        BinaryTree tree = createFilledTree();
        assertThrows(IllegalStateException.class, () -> tree.findParent(5));
    }

    @Test
    public void searchTest() {
        BinaryTree tree = createFilledTree();
        int[] actual = new int[]{
                tree.search(100).getValue(),
                tree.search(105).getValue(),
                tree.search(110).getValue(),
                tree.search(115).getValue()
        };
        int[] expected = new int[]{100, 105, 110, 115};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void searchExceptionTest() {
        BinaryTree tree = createEmptyTree(10);
        assertThrows(IllegalStateException.class, () -> tree.search(2));
    }

    @Test
    public void removeTest() {
        BinaryTree tree = createFilledTree();
        assertEquals(75, tree.findRightChild(50).getValue());
        tree.remove(75);
        assertEquals(85, tree.findRightChild(50).getValue());
        tree.remove(50);
        assertEquals(52, tree.findParent(25).getValue());
        tree.remove(1);
        assertNull(tree.findLeftChild(10));
    }

    @Test
    public void removeExceptionTest() {
        BinaryTree tree = createEmptyTree(10);
        assertThrows(IllegalStateException.class, () -> tree.remove(2));
    }

    @Test
    public void equalsTest() {
        BinaryTree tree1 = createEmptyTree(3);
        BinaryTree tree2 = createEmptyTree(3);
        assertEquals(tree1.search(3), tree2.search(3));
        tree1.insert(1);
        tree1.insert(4);
        assertNotEquals(tree1.search(3), tree2.search(3));
        tree2.insert(1);
        tree2.insert(4);
        assertEquals(tree1.search(3), tree2.search(3));
        tree2.insert(0);
        tree2.insert(2);
        assertNotEquals(tree1.search(3), tree2.search(3));
        tree1.insert(0);
        tree1.insert(2);
        assertEquals(tree1.search(3), tree2.search(3));
    }

}