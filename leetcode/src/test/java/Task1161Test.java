import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/?envType=study-plan-v2&envId=leetcode-75
 */
public class Task1161Test {
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

    public int maxLevelSum(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int maxLevel = 0;
        int currentLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int currentSum = Integer.MIN_VALUE;

            while (levelSize != 0) {
                TreeNode node = queue.poll();

                if (currentSum == Integer.MIN_VALUE) {
                    currentSum = node.val;
                } else {
                    currentSum += node.val;
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }

                levelSize--;
            }

            if (currentSum > maxSum) {
                maxLevel = currentLevel;
                maxSum = currentSum;
            }

            currentLevel++;
        }

        return maxLevel;
    }

    public int maxLevelSum2(TreeNode root) {
        Map<Integer, Integer> sumByLevel = new HashMap<>();

        dfs(root, 1, sumByLevel);

        return Collections.max(sumByLevel.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void dfs(TreeNode root, int level, Map<Integer, Integer> storage) {
        if (root == null) {
            return;
        }

        storage.put(level, storage.getOrDefault(level, 0) + root.val);

        dfs(root.left, level + 1, storage);
        dfs(root.right, level + 1, storage);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test() {
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
        );
    }
}
