package leetCode.medium18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        int li = 0;
        int ri = nums.length - 1;

        // 先判断下边界
        int num = nums[0] + nums[1] + nums[2] + nums[3];
        if (num > target) {
            // 比最小值小
            return Collections.emptyList();
        } else if (num == target) {
            return Collections.singletonList(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
        }

        num = nums[ri] + nums[ri - 1] + nums[ri - 2] + nums[ri - 3];
        if (num < target) {
            // 比最大值大
            return Collections.emptyList();
        } else if (num == target) {
            return Collections.singletonList(Arrays.asList(nums[ri], nums[ri - 1], nums[ri - 2], nums[ri - 3]));
        }

        l:
        while (true) {
            ri = nums.length - 1;
            r:
            while (true) {
                // 先判断范围内最小值
                int lli = li + 1;
                int rri = lli + 1;
                num = nums[li] + nums[lli] + nums[rri] + nums[ri];
                if (num > target) {
                    // 比最小值小，下一个
                    do {
                        if (--ri < li + 3) {
                            // 外层结束条件
                            break r;
                        }
                    } while (nums[ri] == nums[ri + 1]);
                }

                // 再判断范围内最大值
                rri = ri - 1;
                lli = rri - 1;
                num = nums[li] + nums[lli] + nums[rri] + nums[ri];
                if (num < target) {
                    // 比最大值大，下一个
                    break;
                }

                lli = li + 1;
                rri = ri - 1;


                while (lli < rri) {
                    num = nums[li] + nums[ri] + nums[lli] + nums[rri];
                    if (num > target) {
                        // 大了，动右边
                        do {
                            rri--;
                        } while (lli < rri && nums[rri] == nums[rri+1]);
                    } else if (num < target) {
                        // 小了，动左边
                        lli++;
                    } else {
                        // 等于,保存，然后都动动
                        result.add(Arrays.asList(nums[li], nums[lli], nums[rri], nums[ri]));
                        lli++;
                        do {
                            rri--;
                        } while (lli < rri && nums[rri] == nums[rri+1]);
                    }
                }


                // 过掉重复的
                do {
                    if (--ri < li + 3) {
                        // 外层结束条件
                        break r;
                    }
                } while (nums[ri] == nums[ri + 1]);
            }


            // 这个是为了过掉重复的
            do {
                if (++li == nums.length - 3) {
                    // 外层循环结束条件
                    break l;
                }
            } while (nums[li] == nums[li - 1]);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2, -1, 0, 0, 1, 2};
        List<List<Integer>> lists = solution.fourSum(nums, 0);
        System.out.println(lists);
    }

}
