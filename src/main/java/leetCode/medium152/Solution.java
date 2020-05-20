package leetCode.medium152;

public class Solution {
    public int maxProduct(int[] nums) {
        // 如果有负数用这个记录第一个需要被剔除的负数
        int flagIndex = -1;
        int result = Integer.MIN_VALUE;
        int temp = nums[0];
        if (temp < 0) {
            flagIndex = 0;
        }

        for (int i = 1; i < nums.length; i++) {
            if (temp == 0) {
                temp = nums[i];
                result = Math.max(result, 0);
                if (temp < 0) {
                    flagIndex = i;
                }
                continue;
            }

            if (nums[i] < 0) {
                // 进来一个负数
                // 先把前面部分记录
                result = Math.max(result, temp);
                if (flagIndex == -1) {
                    // 但flag尚无记录，说明前面有值而且是正数
                    // 再把这个负数乘上
                    temp *= nums[i];
                    // 替换首负的索引
                    flagIndex = i;
                    // 征用这个位置了，反正后面也没用了
                    nums[i] = temp;
                } else {
                    // 有记录的情况，直接乘就完了，负负得正
                    temp *= nums[i];
                }

            } else if (nums[i] > 0) {
                // 进来一个正数
                temp *= nums[i];
            } else {
                // 进来0,阶段性终结
                if (temp < 0) {
                    // 前面都小于0的情况
                    if (flagIndex != i - 1) {
                        result = Math.max(result, temp / nums[flagIndex]);
                    }
                    result = Math.max(result, 0);
                } else {
                    // 这里temp一定大于0
                    result = Math.max(result, temp);
                }
                temp = 0;
                flagIndex = -1;
            }
        }


        if (temp < 0) {
            // 最后一层得判断一次
            if (flagIndex != nums.length - 1) {
                result = Math.max(result, temp / nums[flagIndex]);
            }
        }
        result = Math.max(result, temp);

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = {-4, -3, -2};
        int[] nums = {-2,3,0,2,-2,3};
        int o = solution.maxProduct(nums);
        System.out.println(o);
    }
}
