package leetCode.easy189;

import java.util.Arrays;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/11/18 9:58
 **/
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6};
        s.rotate2(nums, 5);

        System.out.println(Arrays.toString(nums));
    }

    /**
     * Description 我的，错的
     *
     * @author zhaolaiyuan
     * @Date 2019/11/18 10:07
     **/
    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        // 长度小于2的不用管，怎么整都一样
        if (length < 2) {
            return;
        }
        k = k%length;
        if (k == 0) {
            return;
        }

        // 环状替换
        int count = 0;
        for (int i = 0; count < length; i++) {
            int target = (i + k) % length;
            int temp = nums[i];
            do {
//                System.out.println(i);
//                System.out.println(nums[target] + " " + temp);
                // 替换
                nums[target] = nums[target] ^ temp;
                temp = nums[target] ^ temp;
                nums[target] = nums[target] ^ temp;

//                System.out.println(Arrays.toString(nums));
                // 为下一次替换做准备
                target = (target + k) % length;
                count++;
            } while (i != target);
            // 最后一个手动
            nums[i] = temp;
            count++;
        }

    }



    /**
     * Description 官方题解
     *
     * @author zhaolaiyuan
     * @Date 2019/11/18 10:07
     **/
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        // 长度小于2的不用管，怎么整都一样
        if (length < 2) {
            return;
        }

        k = k % length;
        if (k == 0) {
            return;
        }

        int count = 0;
        for (int start = 0; count < length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);

        }
    }
}