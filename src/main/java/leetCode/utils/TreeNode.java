package leetCode.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
        return create(o);

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
        boolean over = false;
        List<TreeNode> list = new ArrayList<>();
        list.add(this);
        List<StringBuilder> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("      ").append(String.format("%1$4s", this.val)).append("      ");
        lines.add(sb);

        for (TreeNode node : list) {
            if (node == null) {
                sb.append("null").append("      ").append("null");
            }
        }
        sb.append(System.lineSeparator());



        return "";
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
