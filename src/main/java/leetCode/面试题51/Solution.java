package leetCode.面试题51;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Documentation;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    int[] nums;
    int value = 0;
    public int reversePairs(int[] nums) {
        if (nums.length < 2) {
            // 一个或零个的情况
            return 0;
        }

        this.nums = nums;
        this.sort(0, nums.length - 1);
        return value;
    }

    private void sort(int s, int e) {
        if (e - s < 1) {
            // 一个数的情况
            return;
        } else if (e - s < 2) {
            // 两个数的情况
            if (nums[e] < nums[s]) {
                // 第二个数比第一个数小，交换并计数
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
                this.value++;
            }
            return;
        }

        // 向下分治
        int m = (e + s) / 2;
        this.sort(s, m);
        this.sort(m + 1, e);

        // 到这左右分片都是排好序的了。
        int ls = s;
        int rs = m + 1;
        int[] l = Arrays.copyOfRange(nums, ls, m + 1);
        int[] r = Arrays.copyOfRange(nums, rs, e + 1);
        ls = 0;
        rs = 0;
        for (; s <= e; s++) {
            if (ls == l.length) {
                // 左边超限了
                // 取右边值
                nums[s] = r[rs];
                rs++;
            } else if (rs == r.length) {
                // 右边超限了
                // 取左边值
                nums[s] = l[ls];
                ls++;
            } else {
                // 没超限的情况
                if (l[ls] > r[rs]) {
                    // 左边大，逆序了
                    this.value += l.length - ls;
                    // 取右边值
                    nums[s] = r[rs];
                    rs++;
                } else {
                    // 右边大
                    // 取左边值
                    nums[s] = l[ls];
                    ls++;
                }
            }
        }
    }
    public static void main(String[] args) {
//        Solution solution = new Solution();
//        Gson gson = new Gson();
//        Type type = new TypeToken<int[]>(){}.getType();
//        int[] nums = {3,21,4,7,22,3};
//        int i = solution.reversePairs(nums);
//        System.out.println(i);

        String[] split = "".split(",");

        System.out.println(Arrays.toString(split));
        System.out.println(split.length);

        List<String> collect = Stream.of(split).map(x -> {
            System.out.println(x);
            System.out.println(111);
            return x;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
}
