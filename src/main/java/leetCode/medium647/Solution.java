package leetCode.medium647;

import java.util.*;

public class Solution {
    public int countSubstrings(String s) {
        int l = s.length();
        if (l < 3) {
            if (l > 1) {
                return s.charAt(0) == s.charAt(1) ? 3 : 2;
            } else if (l > 0) {
                return 1;
            }
        }
        boolean[][] memo = new boolean[l][l];

        // 初始统计，每个都算一个
        int count = l;
        memo[0][0] = true;

        for (int i = 1; i < l; ++i) {
            memo[i][i] = true;
            if (s.charAt(i - 1) == s.charAt(i)) {
                memo[i - 1][i] = true;
                ++count;
            }
        }

        // 差值从2开始，最少3个数
        for (int g = 2; g < l; ++g) {
            int e = l - g;
            for (int i = 0; i < e; ++i) {
                int t = i + g;
                if (memo[i + 1][t - 1] && s.charAt(i) == s.charAt(t)) {
                    memo[i][t] = true;
                    ++count;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<int[]> areas = new LinkedList<int[]>();
        areas.add(new int[]{0, 0});
        areas.add(new int[]{0, 1});
        areas.add(new int[]{0, 2});
        ListIterator<int[]> listIterator = areas.listIterator();
        Arrays.fill(new boolean[8], false);
        Iterator<int[]> iterator = areas.iterator();

        while (listIterator.hasNext()) {
            int[] next = listIterator.next();
            System.out.println(Arrays.toString(next));
        }
        listIterator.previous();
        while (listIterator.hasPrevious()) {
            int[] next = listIterator.previous();
            System.out.println(Arrays.toString(next));
        }

//        int aa = solution.countSubstrings("aaa");
//        System.out.println(aa);
    }
}
