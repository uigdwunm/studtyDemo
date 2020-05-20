package leetCode.medium560;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        // 最终返回的结果以及第一位的值是否满足
        int count = nums[0] == k ? 1 : 0;
        if (nums.length == 1) {
            return count;
        }
        // 记录每个累积值，及出现的次数
        Map<Integer, Integer> map = new HashMap<>(nums.length, 1F);
        // 首位特殊处理
        map.put(nums[0], 1);
        int sum = nums[0];
        // 循环
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                // 当前的累积值是否正好满足
                count++;
            }
            // 用累积值减去目标值，在记录中找是否有对应的值
            count += map.getOrDefault(sum - k, 0);
            map.merge(sum, 1, Integer::sum);
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, -1, 1};
        int i = solution.subarraySum(nums, 0);
        System.out.println(i);
    }
}
