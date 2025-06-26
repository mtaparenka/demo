import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task199Test {
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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            result.add(queue.peek().val);

            while (levelSize != 0) {
                TreeNode node = queue.poll();

                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                levelSize--;
            }
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(TreeNode tree, List<Integer> expected) {
        Assertions.assertEquals(expected, rightSideView(tree));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(1, new TreeNode(2, null, new TreeNode(5, null, new TreeNode(6))), new TreeNode(3, null, new TreeNode(4))),
                        List.of(1, 3, 4, 6)
                )
        );
    }
}
