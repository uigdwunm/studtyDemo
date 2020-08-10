package leetCode.medium34;

public class Solution {

    public int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return new int[] {-1, -1};
        }
        int l = this.left(nums, target, 0, length - 1);
        if (l == -1) {
            return new int[]{-1, -1};
        }
        int r = this.right(nums, target, l, length - 1);

        return new int[]{l, r};

    }

    private int left(int[] nums, int t, int l, int r) {
        int x = r - l;
        if (x < 2) {
            if (x > 0) {
                // 剩两个的情况
                return nums[l] == t ? l : nums[r] == t ? r : -1;
            } else {
                // 剩一个的情况
                return nums[l] == t ? l : -1;
            }
        }
        int m = l + x / 2;
        if (nums[m] < t) {
            return this.left(nums, t, m, r);
        } else {
            return this.left(nums, t, l, m);
        }
    }

    private int right(int[] nums, int t, int l, int r) {
        int x = r - l;
        if (x < 2) {
            if (x > 0) {
                // 剩两个的情况
                return nums[r] == t ? r : nums[l] == t ? l : -1;
            } else {
                // 剩一个的情况
                return nums[r] == t ? r : -1;
            }
        }
        int m = l + x / 2;
        if (t < nums[m]) {
            return this.right(nums, t, l, m);
        } else {
            return this.right(nums, t, m, r);
        }
    }
}
