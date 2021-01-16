import leetCode.utils.ListNode;

import java.sql.ClientInfoStatus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int check = this.check(nums1, nums2);
        if (check > 0) {
            if (check == 3) {
                throw new RuntimeException("请求参数错误");
            } else if (check == 2) {
                // 第二个数组有值
                return this.findMedianSortedArray(nums2);
            } else {
                // 仅第一个数组有值
                return this.findMedianSortedArray(nums1);

            }
        }
        // 数组长度
        int l1  = nums1.length;
        int l2 = nums2.length;
        // 总长
        int l = l1 + l2;
        // 中间长度
        int m = l / 2;

        // 结果
        double result;
        // 两个数组的进度指针
        int i1 = 0;
        int i2 = 0;

        // 标识当前运行到哪个数组了，1数组为true，2数组为false
        boolean isOne = false;

        for (int i = 0; i < m; ++i) {
            if (nums1[i1] > nums2[i2]) {
                i2++;
                isOne = false;
            } else {
                // 相等的情况，随便哪边动都可以
                i1++;
                isOne = true;
            }
        }
        result = isOne ? nums1[i1] : nums2[i2];
        if (l % 2 == 0) {
            // 偶数，需要计算两个数
            int next;
            if (nums1[i1] > nums2[i2]) {
                next = nums2[i2 + 1];
            } else {
                // 相等的情况，随便哪边动都可以
                next = nums2[i1 + 1];
            }

            result =  ((double)result + (double)next) / 2.0;
        }
        return result;
    }

    // 取单个数组的中位数，这里只会传入合法的数组
    private double findMedianSortedArray(int[] nums) {
        int l = nums.length;
        if (l == 1) {
            return nums[0];
        }
        int m = l / 2;

        if (l % 2 == 0) {
            return ((double)nums[m] + (double)nums[m + 1]) / 2.0;
        } else {
            return (double) nums[m];
        }
    }

    // 如果都有值返回0，仅第一个数组有值返回1，仅第二个数组有值返回2，都没值返回3
    private int check(int[] nums1, int[] nums2) {
        if (nums1 != null && nums1.length != 0 && nums2 != null && nums2.length != 0) {
            // 全都没有值的情况
            return 0;
        } else if (nums1 != null && nums1.length != 0) {
            // 仅1号数组有值的情况
            return 1;
        } else if (nums2 != null && nums2.length != 0) {
            // 仅2号数组有值的情况
            return 2;
        } else {
            // 全都没有值
            return 3;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {1, 3};
        int[] b = null;
        double medianSortedArrays = solution.findMedianSortedArrays(a, b);
        System.out.println(medianSortedArrays);
    }
}



/* 题目2:
有A类线程50个，任务是打印字符A。有B类线程50个，任务是打印字符B。现在异步启动这100个线程，问如何才能让他们交替打印AB字符？
*/

