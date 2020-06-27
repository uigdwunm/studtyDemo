package leetCode.hard124;


import leetCode.utils.TreeNode;

public class Solution {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        this.maxSub(root);
        return max;
    }

    private int maxSub(TreeNode node) {
        Integer leftMax = null;
        if (node.left != null) {
            leftMax = this.maxSub(node.left);
        }
        Integer rightMax = null;
        if (node.right != null) {
            rightMax = this.maxSub(node.right);
        }

        return this.mathMax(node.val, leftMax, rightMax);
    }

    private int mathMax(int c, Integer l, Integer r) {
        if (l == null && r == null) {
            this.max = Math.max(this.max, c);
            return c;
        }
        int cmax = Integer.MIN_VALUE;
        if (l != null) {
            cmax = Math.max(cmax, l + c);
        }
        if (r != null) {
            cmax = Math.max(cmax, r + c);

            if (l != null) {
                this.max = Math.max(this.max, c + l + r);
            }
        }
        cmax = Math.max(c, cmax);
        this.max = Math.max(this.max, cmax);

        return cmax;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = TreeNode.create("[-10,9,20,null,null,15,7]");
        int i = solution.maxPathSum(treeNode);
        System.out.println(i);
    }
}
