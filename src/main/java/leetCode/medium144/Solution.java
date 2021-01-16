package leetCode.medium144;

import leetCode.utils.TreeNode;

import java.util.*;

public class Solution {
    /**
     * Description 这是中序遍历，操！
     *
     * @author zhaolaiyuan
     * Date 2020/10/28 9:06
     **/
    public List<Integer> preorderTraversal2(TreeNode root) {
        TreeNode node = root;
        if (node == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        // 是否正在回溯中
        boolean isRecall = false;

        while (true) {
            if (isRecall) {
                // 回溯中
                TreeNode temp = deque.poll();
                TreeNode right = temp.right;
                if (node == null) {
                    node = temp;
                    result.add(node.val);
                } else if (right == node) {
                    // 是右节点，直接结束
                    node = temp;
                    if (deque.size() == 0) {
                        break;
                    }
                    continue;
                } else {
                    // 是左节点
                    node = temp;
                    result.add(node.val);
                }

                // 看看有没有右节点
                if (right != null) {
                    // 右节点还有，继续蔓延
                    deque.push(temp);
                    node = right;
                    isRecall = false;
                } else if (deque.size() == 0) {
                    break;
                }

            } else {
                // 蔓延中
                if (node == null) {
                    isRecall = true;
                } else {
                    deque.push(node);
                    node = node.left;
                }
            }
        }
        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        TreeNode node = root;
        if (node == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(node);
        List<Integer> result = new ArrayList<>();

        while (!deque.isEmpty()) {
            node = deque.pop();
            result.add(node.val);

            TreeNode right = node.right;
            if (right != null) {
                deque.push(right);
            }
            TreeNode left = node.left;
            if (left != null) {
                deque.push(left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = TreeNode.create("[1]");
        List<Integer> integers = solution.preorderTraversal(treeNode);
        System.out.println(integers);
    }
}
