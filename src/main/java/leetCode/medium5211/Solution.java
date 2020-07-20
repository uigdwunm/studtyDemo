package leetCode.medium5211;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private final Map<Integer, Map<Integer, Double>> memo = new HashMap<>();

    // 超时了
    @Deprecated
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        this.init(edges, succProb);

        return this.find(start, end);
    }

    private double find(int s, int e) {
        double max = 0;

        Map<Integer, Double> eMap = memo.get(s);
        for (Map.Entry<Integer, Double> eEntry : eMap.entrySet()) {
            Double v = eEntry.getValue();
            Integer ce = eEntry.getKey();
            if (v < 0 || ce == s) {
                continue;
            }
            if (e == ce) {
                max = Math.max(max, v);
            } else {
                eEntry.setValue(-eEntry.getValue());
                this.memo.get(ce).put(s, this.memo.get(ce).get(s));

                double subProb = this.find(ce, e);
                if (subProb == 0) {
                    // 此路不通，永远为负吧
                    continue;
                }
                max = Math.max(max, v * subProb);

                eEntry.setValue(-eEntry.getValue());
                this.memo.get(ce).put(s, this.memo.get(ce).get(s));
            }
        }

        return max;
    }

    private void init(int[][] edges, double[] succProb) {
        for (int i = 0; i < edges.length; i++) {
            final int ii = i;
            final int[] edge = edges[i];
            this.memo.compute(edge[0], (k, v) -> {
                if (v == null) {
                    v = new HashMap<>();
                }
                v.put(edge[1], succProb[ii]);
                return v;
            });
            this.memo.compute(edge[1], (k, v) -> {
                if (v == null) {
                    v = new HashMap<>();
                }
                v.put(edge[0], succProb[ii]);
                return v;
            });
        }

    }

    /**
     10
     [[3,5],[3,4],[6,8],[1,5],[2,8],[6,9],[0,2],[7,8],[0,4],[3,7],[8,9],[5,8],[3,6],[0,6],[1,6],[0,1],[0,3],[5,6],[1,9]]
     [0.72,0.81,0.18,0.62,0.96,0.45,0.19,0.77,0.1,0.99,0.49,0.25,0.45,0.68,0.95,0.61,0.11,0.72,0.2]
     1
     6
     **/
    public static void main(String[] args) {
        Solution solution = new Solution();
        Gson gson = new Gson();
        Type type = new TypeToken<int[][]>() {
        }.getType();
        int n = 10;
        int start = 1;
        int end = 6;
        String edgesStr = "[" +
                "[3,5]," +
                "[3,4]," +
                "[6,8]," +
                "[1,5]," +
                "[2,8]," +
                "[6,9]," +
                "[0,2]," +
                "[7,8]," +
                "[0,4]," +
                "[3,7]," +
                "[8,9]," +
                "[5,8]," +
                "[3,6]," +
                "[0,6]," +
                "[1,6]," +
                "[0,1]," +
                "[0,3]," +
                "[5,6]," +
                "[1,9]" +
                "]";
        int[][] edges = gson.fromJson(edgesStr, type);
        double[] succProb = {
                0.72,
                0.81,
                0.18,
                0.62,
                0.96,
                0.45,
                0.19,
                0.77,
                0.1,
                0.99,
                0.49,
                0.25,
                0.45,
                0.68,
                0.95,
                0.61,
                0.11,
                0.72,
                0.2};
        double v = solution.maxProbability(n, edges, succProb, start, end);
        System.out.println(v);
    }
}
