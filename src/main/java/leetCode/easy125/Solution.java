package leetCode.easy125;

public class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }
        char[] chars = s.toCharArray();

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            char lc = s.charAt(l);
            char rc = s.charAt(r);
            while (this.valid(lc) == '*') {
                lc = s.charAt(++l);
            }
            while (this.valid(rc) == '*') {
                rc = s.charAt(--r);
            }

            if (lc == rc) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }


    private char valid(char a) {
        if (a > 47 && a < 58) {
            return a;
        }
        if (a > 64 && a < 91) {
            return a;
        }
        if (a > 96 && a < 123) {
            return (char) (a - 32);
        }
        return '*';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean palindrome = solution.isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(palindrome);

    }
}
