package leetCode.meium209;

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int si = 0;
        int ei = 0;
        // 先找到第一组大于s的数
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ei = i;
            // 找到了就会进入循环
            if (sum > s) {
                result = ei - si + 1;
                while (++ei < nums.length) {
                    // 移动si直至小于s
                    do {
                        sum -= nums[si];
                        si++;
                    } while (sum > s || si <= ei);

                    // 移动ei直至大于s
                    sum += nums[ei];

                    if (sum > s) {
                        result = Math.min(result, ei - si + 1);
                    }
                }
                return result;
            }

        }
        // 出了循环都没大于s，说明不存在
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,3,1,2,4,3};
        int i = solution.minSubArrayLen(7, nums);
        System.out.println(i);
    }
}
