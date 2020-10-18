import leetCode.utils.GsonUtils;

import java.util.*;

public class Solution {
    private int max;

    public int maxUniqueSplit(String s) {
        HashSet<String> set = new HashSet<>();

        this.dfs(s, 0, set);
        return max;

    }

    private void dfs(String s, final int l, Set<String> set) {
        int length = s.length();
        if (l == length) {
            max = Math.max(max, set.size());
            return;
        }

        String curr;

        for (int r = l + 1; r <= length; r++) {
            curr = s.substring(l, r);
            if (set.add(curr)) {
                this.dfs(s, r, set);
                set.remove(curr);
            }

        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] points = GsonUtils.convertToIntArrArr("[[3,12],[-2,5],[-4,1]]");
        int s = solution.maxUniqueSplit("a");
        System.out.println(s);
    }
}
