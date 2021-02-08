package exercise1;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void Test1() {

        Node root = new Node(7);
        BinaryTree testTree = new BinaryTree(root);

        testTree.Insert(5);
        testTree.Insert(20);
        testTree.Insert(8);
        testTree.Insert(9);
        testTree.Insert(3);
        testTree.Insert(99);
        testTree.Insert(4);
        testTree.Insert(335);
        testTree.Insert(1);
        int[] expected = new int[]{1, 99, 8};
        int[] actual = new int[]{
                testTree.FindLeftChild(3).getValue(),
                testTree.FindRightChild(20).getValue(),
                testTree.FindParent(9).getValue()
        };
        Assert.assertArrayEquals(expected, actual);

        testTree.Insert(2);
        testTree.Insert(65);
        testTree.Insert(6);
        expected = new int[]{5, 9, 2};
        actual = new int[]{
                testTree.FindParent(6).getValue(),
                testTree.FindRightChild(8).getValue(),
                testTree.FindRightChild(1).getValue()
        };
        Assert.assertArrayEquals(expected, actual);

        testTree.Remove(3);
        expected = new int[]{1, 4};
        actual = new int[]{
                testTree.FindLeftChild(4).getValue(),
                testTree.FindParent(1).getValue()
        };
        Assert.assertArrayEquals(expected, actual);

    }

    @Test(expected = NullPointerException.class)
    public void Test2() {

        Node root = new Node(7);
        BinaryTree testTree = new BinaryTree(root);

        testTree.Remove(7);
        testTree.Search(7);

    }

    @Test(expected = NullPointerException.class)
    public void Test3() {

        Node root = new Node(7);
        BinaryTree testTree = new BinaryTree(root);

        testTree.Insert(5);
        testTree.Insert(20);
        testTree.Insert(8);
        testTree.Insert(9);
        testTree.Insert(3);
        testTree.Insert(99);

        testTree.Remove(5);
        testTree.Remove(8);
        testTree.Remove(20);
        testTree.Remove(9);
        testTree.Remove(3);

        int expected = 99;
        Assert.assertEquals(expected, testTree.Search(99).getValue());

        testTree.Remove(99);
        testTree.Remove(7);
        testTree.Search(7);

    }

    @Test
    public void Test4() {

        Node root = new Node(7);
        BinaryTree testTree = new BinaryTree(root);

        testTree.Insert(8);
        testTree.Insert(9);
        testTree.Insert(10);
        testTree.Insert(11);
        testTree.Insert(6);
        testTree.Insert(5);

        int[] expected = new int[]{6, 8};
        int[] actual = new int[]{
                testTree.FindLeftChild(7).getValue(),
                testTree.FindRightChild(7).getValue(),
        };
        Assert.assertArrayEquals(expected, actual);

        testTree.Remove(6);
        expected = new int[]{5, 8};
        actual = new int[]{
                testTree.FindLeftChild(7).getValue(),
                testTree.FindRightChild(7).getValue(),
        };
        Assert.assertArrayEquals(expected, actual);

        testTree.Remove(8);
        expected = new int[]{5, 9};
        actual = new int[]{
                testTree.FindLeftChild(7).getValue(),
                testTree.FindRightChild(7).getValue(),
        };
        Assert.assertArrayEquals(expected, actual);

        Assert.assertEquals(10, testTree.FindParent(11).getValue());

    }

    @Test
    public void Test5() {

        Node root = new Node(7);
        BinaryTree testTree = new BinaryTree(root);

        testTree.Insert(5);
        testTree.Insert(20);
        testTree.Insert(8);
        testTree.Insert(9);
        testTree.Insert(3);
        testTree.Insert(99);
        /*
                              7
                             /  \
                            5    20
                           /    /  \
                          3    8    99
                                \
                                 9
         */
        int[] expected = new int[]{7, 8, 99};
        int[] actual = new int[]{
                testTree.FindParent(20).getValue(),
                testTree.FindLeftChild(20).getValue(),
                testTree.FindRightChild(20).getValue()
        };
        Assert.assertArrayEquals(expected, actual);

        testTree.Remove(8);
        /*
                              7
                             /  \
                            5    20
                           /    /  \
                          3    9    99
         */
        expected = new int[]{7, 9, 99};
        actual = new int[]{
                testTree.FindParent(20).getValue(),
                testTree.FindLeftChild(20).getValue(),
                testTree.FindRightChild(20).getValue()
        };
        Assert.assertArrayEquals(expected, actual);

        testTree.Remove(20);
        /*
                              7
                             /  \
                            5    99
                           /    /
                          3    9
         */
        expected = new int[]{7, 99, 9};
        actual = new int[]{
                testTree.FindParent(99).getValue(),
                testTree.FindRightChild(7).getValue(),
                testTree.FindLeftChild(99).getValue()
        };
        Assert.assertArrayEquals(expected, actual);

        testTree.Remove(7);
        /*
                              9
                             / \
                            5   99
                           /
                          3
         */
        expected = new int[]{9, 5, 99};
        actual = new int[]{
                testTree.FindParent(5).getValue(),
                testTree.FindLeftChild(9).getValue(),
                testTree.FindRightChild(9).getValue()
        };
        Assert.assertArrayEquals(expected, actual);

    }

}
