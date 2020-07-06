package leetCode.hard44;

public class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[][] memo = new boolean[pl + 1][sl + 1];
        char pc = p.charAt(0);
        // 空对空
        memo[0][0] = true;
        if (pc == '*') {
            for (int si = 0; si < sl; si++) {
                memo[0][si + 1] = true;
            }
            for (int pi = 0; pi < pl; pi++) {
                if (p.charAt(pi) == '*') {
                    memo[pi + 1][0] = true;
                } else {
                    break;
                }
            }
        }

        for (int pi = 0; pi < pl; pi++) {
            // 当前模式字符
            pc = p.charAt(pi);
            for (int si = 0; si < sl; si++) {
                char sc = s.charAt(si);
                if (pc == '*' && memo[pi][si + 1]) {
                    memo[pi + 1][si + 1] = true;
                } else if (memo[pi][si]) {
                    // 上一个成功，才需要判断
                    if (pc == sc || pc == '?') {
                        memo[pi + 1][si + 1] = true;
                    } else if (pc == '*') {
                        do {
                            memo[pi + 1][si + 1] = true;
                            si++;
                        } while (si < sl);
                        break;
                    }
                }
            }
        }
        return memo[pl][sl];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "";
        String p = "b";
        boolean match = solution.isMatch(s, p);
        System.out.println(match);

    }
}
