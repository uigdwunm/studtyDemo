package leetCode.medium912;

import java.util.Arrays;

public class Solution {
    private int[] nums;
    public int[] sortArray(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        this.nums = nums;
        this.sort(0, nums.length - 1);
        return this.nums;
    }
    // s是开始索引，e是结束索引
    private void sort(int s, int e) {
        // 起始点同时也是基准点
        int os = s;
        // 结束位置
        int oe = e;
        // s变成左边指针，e变成右边指针
        outer: for (; s != e; e--) {
            if (nums[e] < nums[os]) {
                while (true) {
                    if (s == e) {
                        break outer;
                    }
                    s++;
                    if (nums[s] > nums[os]) {
                        this.swap(s, e);
                        break;
                    }
                }
            }
        }

        if (os != e) {
            this.swap(os, e);
        }

        // 判断新的基准点是否还需要继续
        if (e - os > 1) {
            this.sort(os, e - 1);
        }
        if (oe - e > 1) {
            this.sort(e + 1, oe);
        }
    }

    private void swap(int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.sortArray(new int[]{5, 1, 2, 0, 0});
        System.out.println(Arrays.toString(solution.nums));
    }
}
