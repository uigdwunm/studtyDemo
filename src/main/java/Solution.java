import explain.HashMap;
import explain.Map;
import leetCode.utils.GsonUtils;
import org.omg.CORBA.INTERNAL;

import java.util.*;

public class Solution {
    private List<Integer>[] memo;
    Map<Integer, Integer> map;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        memo = new LinkedList[n];
        map = new HashMap<>(n, 1F);
        for (int[] edge : edges) {
            this.save(edge[0], edge[1]);
            this.save(edge[1], edge[0]);
        }

        // 单向的摘出来
        this.find(0, 0);
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            List<Integer> list = memo[i];
            if (list.size() == 1) {
                this.to(labels.charAt(i), i, result, map, labels);
            }
        }

        return result;
    }

    // 单向的摘出来
    private void find(int k, int l) {
        List<Integer> tList = memo[k];
        for (Integer integer : tList) {
            if (integer != l) {
                map.put(integer, k);
                this.find(integer, k);
            }
        }
    }

    private void save(int k, int v) {
        List<Integer> list = memo[k];
        if (list != null) {
            list.add(v);
        } else {
            list = new LinkedList<>();
            list.add(v);
            memo[k] = list;
        }

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
        ArrayList<Integer>[] arrayLists = new ArrayList[1];
        arrayLists[0] = new ArrayList<>();
        Solution solution = new Solution();
        int[][] ints = GsonUtils.convertToIntArrArr("[[0,2],[0,3],[1,2]]");
        int[] abaedcds = solution.countSubTrees(4, ints, "aeed");
        System.out.println(Arrays.toString(abaedcds));
    }
}
