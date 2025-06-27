import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/delete-node-in-a-bst/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task450Test {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode rootHead = root;
        TreeNode preRoot = root;

        while (root != null) {

            if (key < root.val) {
                preRoot = root;
                root = root.left;
            } else if (key > root.val) {
                preRoot = root;
                root = root.right;
            } else {
                TreeNode newRoot = getNewRoot(root);

                if (preRoot.right == root) {
                    preRoot.right = newRoot;
                } else if (preRoot.left == root) {
                    preRoot.left = newRoot;
                } else {
                    rootHead = newRoot;
                }

                return rootHead;
            }
        }

        return rootHead;
    }

    private static TreeNode getNewRoot(TreeNode root) {
        TreeNode newRoot;

        // set right subtree if not null as new root, left otherwise
        if (root.right != null) {
            newRoot = root.right;

            //we should merge new root left subtree and old root left subtree
            if (root.left != null) {
                if (newRoot.left != null) {
                    TreeNode tempLeft = newRoot.left;

                    while (tempLeft.left != null) {
                        tempLeft = tempLeft.left;
                    }

                    tempLeft.left = root.left;
                } else {
                    newRoot.left = root.left;
                }
            }
        } else {
            newRoot = root.left;
        }

        return newRoot;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(TreeNode tree) {
        TreeNode res = deleteNode(tree, 3);
        System.out.println(res);
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new TreeNode(5, new TreeNode(3, new TreeNode(2), new TreeNode(4)), new TreeNode(6, null, new TreeNode(7))))
                //Arguments.of(new TreeNode(0)),
                //Arguments.of(new TreeNode(2, new TreeNode(1), null))
        );
    }
}
