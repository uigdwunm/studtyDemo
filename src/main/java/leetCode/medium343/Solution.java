package leetCode.medium343;

public class Solution {
    public int integerBreak(int n) {
        if (n < 4) {
            if (n > 2) {
                return 2;
            } else if (n > 1) {
                return 1;
            }
        }
        int[][] memo = new int[n][n + 1];
        memo[0][0] = 1;
        memo[1][0] = 2;
        memo[1][1] = 1;
        memo[2][0] = 3;
        memo[2][1] = 2;
        memo[2][2] = 2;
        for (int i = 3; i < memo.length; ++i) {
            memo[i][0] = i + 1;
            for (int j = 1; j <= i; ++j) {
                int max = 0;
                for (int k = 0; k < i ; ++k) {
                    max = Math.max(max, j * memo[i - j][k]);
                }
                memo[i][j] = max;
            }
        }
        int max = 0;
        for (int v : memo[n - 1]) {
            max = Math.max(max, v);
        }

        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.integerBreak(10);
        System.out.println(i);
    }
}
