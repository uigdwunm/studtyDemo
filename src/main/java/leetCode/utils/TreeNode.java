package leetCode.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }


    public static TreeNode create(String s) {
        Gson gson = new Gson();
        Type type = new TypeToken<Integer[]>() {
        }.getType();
        Integer[] o = gson.fromJson(s, type);
        return create2(o);

    }
    public static TreeNode create2(Integer[] nums) {
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        deque.add(root);
        create22(deque, nums, 1);
        return root;

    }

    private static void create22(Deque<TreeNode> deque, Integer[] nums, int i) {
        if (i >= nums.length) {
            return;
        }
        TreeNode first = deque.removeFirst();
        Integer ln = nums[i];
        if (ln != null) {
            first.left = new TreeNode(ln);
            deque.addFirst(first.left);
        }
        if (i + 1 >= nums.length) {
            return;
        }

        Integer rn = nums[i + 1];
        if (rn != null) {
            first.right = new TreeNode(rn);
            deque.addFirst(first.right);
        }

        create22(deque, nums, i + 2);
    }

    public static TreeNode create(Integer[] nums) {
        List<TreeNode> flist = new ArrayList<TreeNode>();
        List<TreeNode> slist = new ArrayList<TreeNode>();

        TreeNode node = new TreeNode(nums[0]);
        flist.add(node);

        for (int s = 1; s < nums.length;) {
            for (TreeNode f : flist) {
                if (f == null) {
                    s += 2;
                    continue;
                }
                if (nums[s] != null) {
                    f.left = new TreeNode(nums[s]);
                    slist.add(f.left);
                }
                if (nums[s + 1] != null) {
                    f.right = new TreeNode(nums[s + 1]);
                    slist.add(f.right);
                }
                s += 2;
            }

            flist = slist;
            slist = new ArrayList<TreeNode>();

        }

        return node;
    }

    @Override
    public String toString() {

        return (left == null ? null : left.val) + ", [" + val + "], " + (right == null ? null : right.val);
    }

    private void addTab(List<StringBuilder> lines) {
        for (StringBuilder line : lines) {
            line.insert(0, "      ");
        }

    }

    public static void main(String[] args) {
        TreeNode treeNode = create("[5,4,8,11,null,13,4,7,2,null,null,null,1]");
        String s = String.format("%1$4s", 123);
        String s2 = String.format("%1$4s", null);
        System.out.println(s + "***");
        System.out.println(s2+ "***");

    }
}
