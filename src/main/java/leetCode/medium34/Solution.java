package leetCode.medium34;

import java.util.Arrays;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = nums.length;
        if (l == 0) {
            return new int[]{-1, -1};
        }
        int li;
        if (nums[0] == target) {
            li = 0;
        } else {
            li = this.searchLeft(nums, 0, l - 1, target);
        }
        if (li == -1) {
            return new int[]{-1, -1};
        }

        int ri;
        if (nums[l - 1] == target) {
            ri = l - 1;
        } else {
            ri = this.searchRight(nums, li, l - 1, target);
        }

        return new int[]{li, ri};
    }

    // 找到目标值的第一个
    // 保证不是第一个
    private int searchLeft(int[] nums, int s, int e, int target) {
        if (e - s < 2) {
            if (nums[s] == target) {
                return s;
            }
            if (e == s) {
                // 一个值
                return -1;
            } else {
                // 两个值
                return nums[e] == target ? e : -1;
            }
        }

        int m = nums.length / 2;
        if (nums[m] < target) {
            return this.searchLeft(nums, m, e, target);
        } else if (target < nums[m]) {
            // 相等或小于
            return this.searchLeft(nums, s, m, target);
        } else if (nums[m] != nums[m - 1]) {
            // 相等，判断是不是最终结果
            return m;
        } else {
            return this.searchLeft(nums, s, m, target);
        }
    }

    // 左边值已经确定，找右边
    // 确保不是最后一个
    private int searchRight(int[] nums, int s, int e, int target) {
        if (e - s < 2) {
            if (nums[e] == target) {
                return e;
            }
            if (s == e) {
                return -1;
            } else {
                return nums[s] == target ? s : -1;
            }
        }

        int m = nums.length / 2;
        if (target < nums[m]) {
            // 相等或小于
            return this.searchRight(nums, s, m, target);
        } else if (nums[m] != nums[m + 1]) {
            // 相等，判断是不是最终结果
            return m;
        } else {
            return this.searchRight(nums, m, e, target);
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ints = solution.searchRange(nums, 8);
        System.out.println(Arrays.toString(ints));
    }
}
