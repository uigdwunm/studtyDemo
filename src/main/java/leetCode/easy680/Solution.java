package leetCode.easy680;

public class Solution {
    private int chance = 1;
    private char[] arr;
    public boolean validPalindrome(String s) {
        arr = s.toCharArray();
        chance = 1;
        return this.check(0, arr.length - 1);
    }

    private boolean check(int l, int r) {
        while (l < r) {
            if (arr[l] == arr[r]) {
                // 左右相等，过
                l++;
                r--;
            } else if (chance > 0) {
                chance--;
                // 不相等，但有容错的机会
                if (arr[l + 1] == arr[r]) {
                    return this.check(l + 2, r - 1);
                } else if (arr[l] == arr[r - 1]) {
                    return this.check(l + 1, r - 2);
                } else {
                    // 容错失败了
                    return false;
                }
            } else {
                // 不相等，也没有容错机会了
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean lcupuupucul = solution.validPalindrome("lcupuupucul");
        System.out.println(lcupuupucul);
    }
}
