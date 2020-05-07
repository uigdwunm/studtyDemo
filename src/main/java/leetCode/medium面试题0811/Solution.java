package leetCode.medium面试题0811;

public class Solution {
    int[] dp;
    int[] val = {25, 10, 5, 1, 0};

    public int waysToChange(int n) {
        dp = new int[n + 1];
        dp[0] = 1;
//        dp[1] = 1;
//        dp[2] = 1;
//        dp[3] = 1;
//        dp[4] = 1;
//        for (int i = 1; i < dp.length; i++) {
//            if (i > 50) {
//                dp[i] = dp[i - 25] * dp[25];
//            } else if (i > 25) {
//
//            } else if (i == 25) {
//                dp[i] = dp[i - 10] * dp[10] + 1;
//            } else if (i > 10) {
//                dp[i] = dp[(i - 5) / 2] * dp[5];
//                dp[i] += dp[(i - 10) / 2] * dp[10];
//            } else if (i == 10) {
//                dp[i] = dp[i - 5] * dp[5] - 1 + 1;
//            } else if (i > 5) {
//                dp[i] = dp[i - 5] * dp[5];
//            } else if (i == 5) {
//                dp[i] = dp[i - 1] + 1;
//            } else {
//                dp[i] = 1;
//            }
//            System.out.println(i + ": " + dp[i]);
//        }
//
//        dp[25] = 13;
//        for (int i = 75; i < dp.length; i++) {
//            dp[i] = dp[25] * dp[i - 50];
//            System.out.println(i + ": " + dp[i]);
//        }
//
        return dp[n];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        // 75: 121
        // 50: 49
        // 25:13
        // 10-14 : 4
        // 15-19 :6
        // 20 : 9
        int oi = solution.waysToChange(25);
        System.out.println(oi);
    }
}
