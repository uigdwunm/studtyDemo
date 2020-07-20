package leetCode.medium33;

public class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        return this.search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int s, int e, int t) {
        if (e - s < 2) {
            if (e == s) {
                // 一个数
                return nums[s] == t ? s : -1;
            } else {
                // 两个数
                return nums[s] == t ? s : (nums[e] == t ? e : -1);
            }
        }
        int m = s + (e - s) / 2;

        if (nums[s] < nums[e]) {
            // 没在反转之内
            if (nums[m] < t) {
                return this.search(nums, m + 1, e, t);
            } else if (t < nums[m]) {
                return this.search(nums, s, m, t);
            } else {
                // 相等
                return m;
            }
        } else {
            // 包含了反转部分
            if (nums[m] < t) {
                if (t <= nums[e]) {
                    return this.search(nums, m + 1, e, t);
                } else {
                    return this.search(nums, s, m, t);
                }
            } else if (t < nums[m]) {
                if (nums[s] <= t) {
                    return this.search(nums, s, m, t);
                } else {
                    return this.search(nums, m + 1, e, t);
                }
            } else {
                // 相等
                return m;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4,5,6,7,8,1,2,3};
        int search = solution.search(nums, 8);
        System.out.println(search);
    }
}
