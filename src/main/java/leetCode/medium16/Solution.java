package leetCode.medium16;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = 0;

        if (nums.length < 4) {
            // 小于4个就直接加起来返回
            for (int num : nums) {
                result += num;
            }
            return result;
        }

        int min = nums[0] + nums[1] + nums[2];
        int max = nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];

        if (target <= min) {
            // 比最小值小
            return min;
        } else if (target >= max) {
            // 比最大值大
            return max;
        }

        result = min;

        int i;
        // 先确定起始位置
        for (i = 3; ; i++) {
            int temp = nums[i - 2] + nums[i - 1] + nums[i];
            if (temp > target) {
                i--;
                break;
            }
        }

        for (; i < nums.length; i++) {
            int j;
            // 第二个确定起始位置
            for (j = 1; j < i - 1; j++) {
                int temp = nums[j - 1] + nums[j] + nums[i];
                if (temp > target) {
                    j--;
                    break;
                }
            }
            for (; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    int temp = nums[k] + nums[j] + nums[i];
                    if (Math.abs(target - temp) < Math.abs(target - result)){
                        result = temp;
                    }
                    if (temp > target) {
                        // 这里从小到大超了
                        break;
                    } else if (temp == target) {
                        return target;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 0, 1, 1, 55};
        int i = solution.threeSumClosest(nums, 3);
        System.out.println(i);
    }
}
