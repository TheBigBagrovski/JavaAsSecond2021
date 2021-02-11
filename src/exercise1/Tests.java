package exercise1;

import org.junit.Assert;
import org.junit.Test;

public class Tests {

    public BinaryTree CreateEmptyTree(int value) {
        return (new BinaryTree(new Node(value)));
    }

    public BinaryTree CreateFilledTree() {
        BinaryTree tree = CreateEmptyTree(50);
        tree.Insert(25);
        tree.Insert(35);
        tree.Insert(15);
        tree.Insert(75);
        tree.Insert(65);
        tree.Insert(85);
        tree.Insert(100);
        tree.Insert(105);
        tree.Insert(110);
        tree.Insert(115);
        tree.Insert(10);
        tree.Insert(52);
        tree.Insert(1);
        tree.Insert(113);
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
    public void FindParentAndChildrenTest() {
        BinaryTree tree = CreateFilledTree();
        int[] actual = new int[]{
                tree.FindRightChild(75).getValue(),
                tree.FindRightChild(50).getValue(),
                tree.FindLeftChild(25).getValue(),
                tree.FindLeftChild(115).getValue(),
                tree.FindParent(52).getValue(),
                tree.FindParent(35).getValue(),
                tree.FindParent(105).getValue(),
        };
        int[] expected = new int[]{85, 75, 15, 113, 65, 25, 100};
        Assert.assertArrayEquals(actual, expected);
        tree = CreateEmptyTree(10);
        Assert.assertNull(tree.FindParent(10));
        Assert.assertNull(tree.FindLeftChild(10));
        Assert.assertNull(tree.FindRightChild(10));
    }

    @Test(expected = IllegalStateException.class)
    public void FRCExceptionTest() {
        BinaryTree tree = CreateFilledTree();
        tree.FindLeftChild(5);
    }

    @Test(expected = IllegalStateException.class)
    public void FPExceptionTest() {
        BinaryTree tree = CreateFilledTree();
        tree.FindLeftChild(5);
    }

    @Test
    public void SearchTest() {
        BinaryTree tree = CreateFilledTree();
        int[] actual = new int[]{
                tree.Search(100).getValue(),
                tree.Search(105).getValue(),
                tree.Search(110).getValue(),
                tree.Search(115).getValue()
        };
        int[] expected = new int[]{100, 105, 110, 115};
        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalStateException.class)
    public void SearchExceptionTest() {
        BinaryTree tree = CreateEmptyTree(10);
        tree.Search(2);
    }

    @Test
    public void RemoveTest(){
        BinaryTree tree = CreateFilledTree();
        Assert.assertEquals(75, tree.FindRightChild(50).getValue());
        tree.Remove(75);
        Assert.assertEquals(85, tree.FindRightChild(50).getValue());
        tree.Remove(50);
        Assert.assertEquals(52, tree.FindParent(25).getValue());
        tree.Remove(1);
        Assert.assertNull(tree.FindLeftChild(10));
    }

    @Test(expected = IllegalStateException.class)
    public void RemoveExceptionTest(){
        BinaryTree tree = CreateEmptyTree(10);
        tree.Remove(2);
    }

}
