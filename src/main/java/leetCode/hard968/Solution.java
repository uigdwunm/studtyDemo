package leetCode.hard968;

import leetCode.utils.TreeNode;

public class Solution {
    private int count = 0;

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int n = this.dfs(root);
        if (n == 3) {
            return this.count + 1;
        }
        return this.count;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 4;
        }

        int left = this.dfs(node.left);
        int right = this.dfs(node.right);

        if (left == 3 || right == 3) {
            this.count++;
            return 2;
        }

        int min = Math.min(left, right);
        int max = Math.max(left, right);
        if (min == 1 && max == 2) {
            return 1;
        }

        int curr = min - 1;
        if (curr > 0) {
            return curr;
        } else {
            return 3;
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = TreeNode.create("[0,null,0,0,0,null,null,null,0]");
        int i = solution.minCameraCover(treeNode);
        System.out.println(i);

    }
}
