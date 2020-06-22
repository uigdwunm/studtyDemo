package leetCode.medium5;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/24 9:12
 **/
class Solution1 {
    int maxLength = 1;
    int maxStart = 0;
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        for (int i = 1; i < s.length() - 1; i++) {
            this.diffusion(s, i - 1, i + 1);
            this.diffusion(s, i - 1, i);
        }

        // 最后两个再看下
        if (maxLength < 2) {
            this.diffusion(s, s.length() - 2, s.length() - 1);
        }

        return s.substring(maxStart, maxStart + maxLength);
    }

    private void diffusion(String s, int l, int r) {
        while (l > -1 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            } else {
                break;
            }
        }
        if (r - l - 1 > maxLength) {
            maxLength = r - l - 1;
            maxStart = l + 1;
        }
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        String babad = solution.longestPalindrome("aa");
        System.out.println(babad);
    }

}
