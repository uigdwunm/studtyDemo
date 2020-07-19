package leetCode;

import leetCode.utils.GsonUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<Integer, Map<Integer, Double>> memo;
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        memo = new HashMap<>();
        // 填充
        for (int i = 0; i < edges.length; i++) {
            int[] ab = edges[i];
            Map<Integer, Double> map = memo.getOrDefault(ab[0], new HashMap<>());
            map.put(ab[1], succProb[i]);
            map = memo.getOrDefault(ab[1], new HashMap<>());
            map.put(ab[0], succProb[i]);
        }

        return this.find(start, end, start, 1);

    }

    private double find(int s, int e, int ss, double pp) {
        double max = 0;
        for (Map.Entry<Integer, Map<Integer, Double>> entry : memo.entrySet()) {
            int l = entry.getKey();
            if (l == s) {
                continue;
            }
            Map<Integer, Double> map = entry.getValue();
            for (Map.Entry<Integer, Double> prodEntry : map.entrySet()) {
                double prob = prodEntry.getValue();
                if (prob < 0) {
                    continue;
                }

                int r = prodEntry.getKey();
                if (r == e) {
                    max = Math.max(max, prob);
                } else {
                    prodEntry.setValue(-prob);
                    memo.get(r).put(l, -memo.get(r).get(l));

                    double v = prob * this.find(r, e, ss, ppp);
                    if (v > 0) {
                        memo[s][e] = Math.max(memo[s][e], v);
                        memo[e][s] = Math.max(memo[e][s], v);
                    }
                    max = Math.max(max, v);

                    prodEntry.setValue(-prob);
                    memo.get(r).put(l, -memo.get(r).get(l));
                }
            }
        }
        for (int i = 0; i < memo[s].length; i++) {
            if (i == s) {
                continue;
            }
            double prob = memo[s][i];
            if (prob > 0) {
                double ppp = pp * prob;
                memo[ss][i] = Math.max(memo[ss][i], ppp);
                memo[i][ss] = Math.max(memo[i][ss], ppp);

                if (i == e) {
                    max = Math.max(max, prob);
                } else {
                    // 表示走过的
                    memo[s][i] = -memo[s][i];
                    memo[i][s] = -memo[i][s];
                    double v = prob * this.find(i, e, ss, ppp);
                    if (v > 0) {
                        memo[s][e] = Math.max(memo[s][e], v);
                        memo[e][s] = Math.max(memo[e][s], v);
                    }
                    max = Math.max(max, v);
                    // 还原
                    memo[s][i] = -memo[s][i];
                    memo[i][s] = -memo[i][s];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n =3;
        int[][] edges = GsonUtils.convertToIntArrArr("[[0,1],[1,2],[0,2]]");
        double[] succProd = {0.5,0.5,0.2};
        int start = 0;
        int end = 2;
        double v = solution.maxProbability(n, edges, succProd, start, end);
        System.out.println(v);

    }
}
