package leetCode.hard685;

import leetCode.utils.GsonUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int l = edges.length;

        // 只有一个父节点，所以v2k是唯一的。
        int[] v2k = new int[l + 1];

        Set<Integer> set = new HashSet<>(l + 1);

        // 重复kv，只会记录一个，认为只有一个
        int rv = 0;
        // 可替换的k
        int rk = 0;

        // 从后向前遍历
        int[] edge;
        for (int i = l - 1; i > -1; --i) {
            edge = edges[i];
            int k = edge[0];
            int v = edge[1];
            if (v2k[v] != 0) {
                // 有重复的了，某个节点有两个父节点，返回
                rv = v;
                rk = v2k[v];
                v2k[v] = k;
            } else {
                v2k[v] = k;
            }
            set.add(v);
        }

        if (rv != 0) {
            // 有重复kv的结束方式。
            int result = this.checkLoop(v2k, rv);
            if (result == 0) {
                return new int[]{rk, rv};
            } else {
                return new int[]{v2k[rv], rv};
            }
        } else {
            // 没有根节点的结束方式。
            // 找到入环口

            int result = 0;
            for (int i = l - 1; i > -1 && set.size() > 0; --i) {
                int v = edges[i][1];
                result = this.checkLoop(v2k, v, set);
                set.remove(v);
            }

            return new int[]{v2k[result], result};
        }
    }

    // 从某个指定位置起有没有v
    private int checkLoop(int[] v2k, int v, Set<Integer> set) {
        // 检测环
        set.remove(v);
        int s = v2k[v];
        set.remove(s);
        int f = v2k[v2k[v]];

        // 第一次相遇
        while (s != f) {
            s = v2k[s];
            set.remove(s);
            f = v2k[v2k[f]];
        }
        // 起点再出发一次
        f = v;
        while (s != f) {
            s = v2k[s];
            set.remove(s);
            f = v2k[f];
        }

        return s;
    }

    // 从某个指定位置起有没有v
    private int checkLoop(int[] v2k, int v) {
        // 检测环
        int s = v2k[v];
        int f = v2k[v2k[v]];

        while (s != f) {
            s = v2k[s];
            f = v2k[v2k[f]];
        }
        return s;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String json = "[[1,2], [2,3], [3,4], [4,1], [1,5]]";

        json = "[[2,1],[3,1],[4,2],[1,4]]";
//        json = "[[1,2],[1,3],[2,3]]";
        int[][] ints = GsonUtils.convertToIntArrArr(json);
        int[] redundantDirectedConnection = solution.findRedundantDirectedConnection(ints);
        System.out.println(Arrays.toString(redundantDirectedConnection));
    }
}
