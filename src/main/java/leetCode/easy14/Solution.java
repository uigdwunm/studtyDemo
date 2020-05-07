package leetCode.easy14;

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 1) {
            return "";
        } else if (strs.length < 2) {
            return strs[0];
        } else if (strs[0].length() < 1) {
            return "";
        }
        int l;
        char[] carr = strs[0].toCharArray();
        for (l = 0; l < carr.length; l++) {
            char c = carr[l];
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() == l || c != strs[i].charAt(l)) {
                    return new String(carr, 0, l);
                }
            }
        }
        return new String(carr, 0, l);

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.longestCommonPrefix(new String[]{"c", "c"});
        System.out.println(s);
    }
}
