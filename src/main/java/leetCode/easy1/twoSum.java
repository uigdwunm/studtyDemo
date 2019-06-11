package leetCode.easy1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/10 9:53
 **/
public class twoSum {

    public static int[] twoSum(int[] nums, int target) {

        int length = nums.length;
        HashMap<Integer, Integer> dictMap = new HashMap<>(length);
        dictMap.put(target - nums[0], 0);
        for (int i = 1; i < length; i++) {
            if (dictMap.containsKey(nums[i])) {
                return new int[]{dictMap.get(nums[i]), i};
            } else {
                dictMap.put(target - nums[i], i);
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{3, 2, 4}, 6);
        System.out.println(Arrays.toString(ints));
    }
}
