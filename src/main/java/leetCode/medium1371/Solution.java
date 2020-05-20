package leetCode.medium1371;

import explain.HashMap;

public class Solution {
    public int findTheLongestSubstring(String s) {
        int result = 0;
        if (s.length() < 1) {
            return result;
        }
        char[] arr = s.toCharArray();
        int[] memo = new int[arr.length];
        if (this.match(arr[0])) {
            memo[0] = arr[0];
        }
        for (int i = 1; i < arr.length; i++) {
            if (this.match(arr[i])) {
                memo[i] = memo[i - 1] ^ arr[i];
            } else {
                memo[i] = memo[i - 1];
            }

            if (memo[i] == 0) {
                result = Math.max(result, i + 1);
                continue;
            }

            for (int j = 0; j < i; j++) {
                if (memo[j] == memo[i]) {
                    result = Math.max(result, i - j);
                    break;
                }
            }
        }
        return result;
    }

    private boolean match(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' ||c == 'u';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s;
        s = "aleeod";
//        s = "eooro";
//        s = "amntyyaw";
        int leetco = solution.findTheLongestSubstring(s);

        System.out.println(leetco);
    }
}
