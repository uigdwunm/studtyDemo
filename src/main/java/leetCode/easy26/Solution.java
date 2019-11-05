package leetCode.easy26;

import java.util.Arrays;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/11/5 13:07
 **/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = {1,2,2,3};
        int i = solution.removeDuplicates(ints);
        System.out.println(i);
        System.out.println(Arrays.toString(ints));
    }
    public int removeDuplicates(int[] nums) {
        // 长度为0或为1时
        if (nums.length < 2) {
            return nums.length;
        }

        // 最终返回的长度
        int length = 1;
        // 标记下一个是否需要替换
        boolean flag = false;
        // 标记待比较的值
        int flagValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (flagValue == nums[i]) {
                // 重复，标记待替换
                flag = true;
            } else if (flag) {
                // 不重复，但是需要替换
                // 记录下一次要替换的值
                flagValue = nums[i];

                // 交换
                nums[length] = nums[i];
                // 长度增加
                length++;
            } else {
                // 不重复，也不需要替换
                length++;
                flagValue = nums[i];
            }
        }
        return length;
    }
}
