package leetCode.medium31;

public class Solution {
    public void nextPermutation(int[] nums) {
        int r = nums.length - 1;


        int l = this.process(nums, r);

        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

    private int process(int[] nums, int r) {
        for (int i = r - 1; i > -1; i--) {
            if (nums[i] >= nums[i + 1]) {
                continue;
            }
            for (int j = r; j > i; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    return i + 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 2};
        solution.nextPermutation(nums);
    }
}
