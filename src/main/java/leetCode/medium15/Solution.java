package leetCode.medium15;

import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            // 不足三个
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            // 最小的大于0或最大的小于0
            return Collections.emptyList();
        }
        List<List<Integer>> list = new ArrayList<>();

        // 以左边的为基准
        int i;
        for (i = 0; nums[i] < 0;i++) {
            int li = i + 1;
            int ri = nums.length - 1;

            while (li < ri) {
                int val = -(nums[i] + nums[ri]);
                if (val < nums[li]) {
                    // 右边指针大了，动右边
                    do {
                        ri--;
                        // 重复的过掉
                        // 如果相等了说明要超限
                        if (li == ri) break;
                    } while (nums[ri] == nums[ri + 1]);

                } else if (val > nums[li]) {
                    // 左边指针小了，动左边
                    do {
                        li++;
                        // 重复的过掉
                        // 如果相等了说明要超限
                        if (li == ri) break;
                    } while (nums[li] == nums[li - 1]);
                } else {
                    // 匹配成功
                    list.add(Arrays.asList(nums[i], nums[li], nums[ri]));
                    //都动动
                    do {
                        li++;
                        // 重复的过掉
                        // 如果相等了说明要超限
                        if (li == ri) break;
                    } while (nums[li] == nums[li - 1]);
                    do {
                        ri--;
                        // 重复的过掉
                        // 如果相等了说明要超限
                        if (li == ri) break;
                    } while (nums[ri] == nums[ri + 1]);
                }
            }
            while (nums[i] == nums[i + 1]) {
                // 重复的过掉
                i++;
            }
        }

        // 为0的可能
        if (nums[i] == 0 && nums[i+1] == 0 && nums[i+2] == 0) {
            list.add(Arrays.asList(0, 0, 0));
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2, 0, 0};
        List<List<Integer>> lists = solution.threeSum(nums);
        System.out.println(lists);
    }
}
