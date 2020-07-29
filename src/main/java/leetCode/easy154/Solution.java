package leetCode.easy154;

public class Solution {
    public int minArray(int[] numbers) {
        int l = 0;
        int r = numbers.length;
        if (r < 2) {
            return numbers[l];
        } else if (r < 3) {
            return Math.min(numbers[0], numbers[1]);
        }
        if (numbers[l] < numbers[r - 1]) {
            return numbers[l];
        }
        return this.minArray(numbers, 0, numbers.length - 1);
    }

    private int minArray(int[] numbers, int l, int r) {
        int m = l + (r - l) / 2;
        if (l == r) {
            return numbers[m];
        }
        if (numbers[m] > numbers[m + 1]) {
            return numbers[m + 1];
        }

        if (numbers[l] < numbers[m]) {
            return this.minArray(numbers, m, r);
        }
        if (numbers[l] > numbers[m]) {
            return this.minArray(numbers, l, m);
        }

        return Math.min(this.minArray(numbers, l, m), this.minArray(numbers, m + 1, r));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,1,0};
        int i = solution.minArray(nums);
        System.out.println(i);
    }
}
