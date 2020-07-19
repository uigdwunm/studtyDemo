package leetCode.hard44;

public class Solution {
    public boolean isMatch(String s, String p) {
        if (s.length() == 0) {
            return p.length() == 0 || "*".equals(p);
        }
        if (p.length() == 0) {
            return false;
        }

        return this.isMatch(s, 0, p, 0);
    }

    private boolean isMatch(String s, int si, String p, int pi) {
        while (si != s.length() && pi != p.length()) {
            char pc = p.charAt(pi);
            char sc = s.charAt(si);
            if (pc == sc || pc == '?') {
                si++;
                pi++;
            } else if (pc == '*') {
                if (pi == p.length() - 1) {
                    return true;
                }
                pi++;
                while (si < s.length()) {
                    if (this.isMatch(s, si, p, pi)) {
                        return true;
                    }
                    si++;
                }
                return false;
            } else {
                return false;
            }
        }
        if (si == s.length()) {
            if (pi == p.length()) {
                return true;
            } else {
                for (int i = pi; i < p.length(); i++) {
                    if (p.charAt(i) != '*') {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab";
        String p = "b*b*ab**ba*b**b***bba";
        boolean match = solution.isMatch(s, p);
        System.out.println(match);


    }
}
