package leetCode.hard41;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        int e = nums.length - 1;
        if (e < 0) {
            return 1;
        }
        int rubbish = this.sort(nums, 0, e);
        if (nums[0] != 1) {
            return 1;
        }

        return this.find(nums, 0, e - rubbish);
    }

    // 这里二分查找
    private int find(int[] nums, int s, int e) {
        if (e - s < 2) {
            // 终止条件
            if (e > s) {
                // 两个数
                if (nums[e] == e + 1) {
                    return e + 2;
                } else {
                    return s + 2;
                }
            } else {
                // 一个数
                return nums[s] == s + 1 ? s + 2 : s;
            }
        }

        int m = s + (e - s) / 2;

        if (nums[m] == m + 1) {
            // 向后找
            return this.find(nums, m, e);
        } else {
            // 向前找
            return this.find(nums, s, m);
        }
    }

    // 快排的路子
    // 重复的是垃圾、负数和0是垃圾
    // 垃圾打包放到最后
    private int sort(int[] nums, int s, int e) {
        // 递归终结条件
        if (e - s < 2) {
            if (e > s) {
                // 两个数
                if (nums[s] == nums[e]) {
                    // 相等的情况
                    return nums[s] < 1 ? 2 : 1;
                }

                // 后一个数该回收
                if (nums[e] < 1) {
                    return nums[s] < 1 ? 2 : 1;
                }
                // 前一个数该回收
                if (nums[s] < 1) {
                    this.swap(nums, s, e);
                    return 1;
                }
                // 两个数都合格
                if (nums[e] < nums[s]) {
                    // 逆序的情况，顺序交换
                    this.swap(nums, s, e);
                    return 0;
                }
            } else if (e == s){
                // 一个数
                return nums[s] < 1 ? 1 : 0;
            } else {
                return 0;
            }
        }

        // 基准值
        int target = nums[s];
        // 待交换位置索引
        int rs = s + 1;
        int rubbish = 0;
        for (int i = rs; i <= e; ++i) {
            int num = nums[i];
            if (num == target || num < 1) {
                // 回收站索引移动
                nums[i] = nums[e];
                --e;
                --i;
                ++rubbish;
            } else if (num < target) {
                if (rs != i) {

                    this.swap(nums, i, rs);
                }
                ++rs;
            }
        }

        if (target < 1) {
            int rr = this.sort(nums, rs, e);
            this.move(nums, s, rs, e);
            return rr + rubbish + 1;
        } else {
            this.swap(nums, s, rs - 1);

            int lr = this.sort(nums, s, rs - 2);
            int rr = this.sort(nums, rs, e);
            this.move(nums, rs - 1 - lr, rs - 1, e);

            return lr + rr + rubbish;
        }

    }

    private void move(int[] nums, int tsi, int ssi, int sei) {
        if (tsi == ssi) {
            return;
        }
        while (ssi <= sei) {
            nums[tsi] = nums[ssi];
            ++tsi;
            ++ssi;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 0};
        int i = solution.firstMissingPositive(nums);
        System.out.println(i);
    }
}
