import explain.HashMap;
import explain.Map;
import leetCode.utils.GsonUtils;

import java.util.*;

public class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, Integer> map = new HashMap<>(n);
        boolean[] exist = new boolean[n];
        for (int[] edge : edges) {
            exist[edge[0]] = true;
            map.put(edge[1], edge[0]);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (!exist[i]) {
                // 是起点
                this.to(labels.charAt(i), i, result, map, labels);
            }
        }

        return result;
    }

    private void to(char c, int i, int[] result, Map<Integer, Integer> map, String labels) {
        result[i] += 1;
        Integer t = map.get(i);
        while (t != null) {
            if (result[t] == 0) {
                this.to(labels.charAt(t), t, result, map, labels);
            }
            if (labels.charAt(t) == c) {
                result[t] += 1;
            }

            t = map.get(t);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ints = GsonUtils.convertToIntArrArr("[[0,2],[0,3],[1,2]]");
        int[] abaedcds = solution.countSubTrees(4, ints, "aeed");
        System.out.println(Arrays.toString(abaedcds));
    }
}
