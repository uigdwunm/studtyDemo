package leetCode.medium983;

public class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days[days.length - 1] + 1];
        int di = 0;
        for (int i = days[0]; i < dp.length; i++) {
            if (di < days.length && days[di] == i) {
                int min = dp[i - 1] + costs[0];
                if (i > 29) {
                    min = this.min(
                            min,
                            dp[i - 7] + costs[1],
                            dp[i - 30] + costs[2]
                    );
                } else if (i > 6) {
                    min = this.min(
                            min,
                            dp[i - 7] + costs[1],
                            costs[2]
                    );
                } else {
                    min = this.min(min, costs[1], costs[2]);
                }
                dp[i] = min;
                di++;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] days = {1, 4, 6, 9,10,11,12,13,14,15, 16,17,18,20,21,22, 23, 27, 28};
        int[] days = {1, 4, 6,9,10,11,12};
        int[] costs = {3,13,45};
        int i = solution.mincostTickets(days, costs);
        System.out.println(i);

    }
}
