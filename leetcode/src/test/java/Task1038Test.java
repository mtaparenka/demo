import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/description/
 */
public class Task1038Test {

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

    public int val = 0;

    public void traverseBinaryTreeDesc(TreeNode root) {
        if (root == null) {
            return;
        }
        traverseBinaryTreeDesc(root.right);
        System.out.println(root.val);
        val += root.val;
        root.val = val;
        traverseBinaryTreeDesc(root.left);
    }


    public TreeNode bstToGst(TreeNode root) {
        if (root.right == null && root.left == null) {
            return root;
        }

        Map<Integer, TreeNode> sorted = new TreeMap<>();

        bstToGstSort(root, sorted);

        List<Integer> keys = new ArrayList<>(sorted.keySet());

        for (int i = keys.size() - 2; i >= 0 ; i--) {
            int toAdd = sorted.get(keys.get(i + 1)).val;
            TreeNode current = sorted.get(keys.get(i));

            current.val += toAdd;
        }

        return root;
    }

    public TreeNode bstToGst2(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    public void bstToGstSort(TreeNode current, Map<Integer, TreeNode> sortedMap) {
        sortedMap.put(current.val, current);

        if (current.left != null) {
            bstToGstSort(current.left, sortedMap);
        }

        if (current.right != null) {
            bstToGstSort(current.right, sortedMap);
        }
    }

    public int dfs(TreeNode current, int add) {
        if (current == null) {
            return add;
        }

        int right = dfs(current.right, add);
        current.val += right;

        return dfs(current.left, current.val);
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(TreeNode root) {
        traverseBinaryTreeDesc(root);
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(new TreeNode(4, new TreeNode(1, null, new TreeNode(3)), new TreeNode(6, new TreeNode(5), new TreeNode(7) )))
                //Arguments.of(new TreeNode(4, new TreeNode(1), new TreeNode(6, null, null )))
        );
    }
}
