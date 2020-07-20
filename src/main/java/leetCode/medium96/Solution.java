package leetCode.medium96;

public class Solution {
    public int numTrees(int n) {
        if (n < 1) {
            return 0;
        }

        int[] memeo = new int[n + 1];
        memeo[0] = 1;

        for (int i = 1; i <= n; ++i) {
            int m = i / 2;
            int temp = 0;
            for (int j = 0; j < m; ++j) {
                temp += memeo[j] * memeo[i - j - 1];
            }
            temp *= 2;
            if (i % 2 == 1) {
                temp += memeo[m] * memeo[m];
            }
            memeo[i] = temp;
        }

        return memeo[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.numTrees(5);
        System.out.println(i);
    }
}
