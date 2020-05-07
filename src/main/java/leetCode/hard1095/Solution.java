package leetCode.hard1095;

import java.lang.management.MonitorInfo;
import java.math.BigDecimal;

public class Solution {
    MountainArray mountainArr;
    int target;

    // 山后高能区
    int ab;
    int aa;

    // 是否进入山前平缓区
    boolean bFlat = false;
    // 是否进入山后平缓区
    boolean aFlat = false;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        this.mountainArr = mountainArr;
        this.target = target;

        this.ab = 0;
        this.aa = mountainArr.length() - 1;

        // 先假设在山前一定有值
        int result = this.findPrevious(0, aa);
        if (result == -1) {
            // 再去后面找找
            return this.findLast(ab, aa);
        }
        return result;
    }

    private int findPrevious(int s, int e) {
        if (s == e) {
            // 只剩一个数
            if (mountainArr.get(s) == target) {
                return s;
            } else {
                return -1;
            }
        }
        int m = (e + s) / 2;
        if (m == s) {
            // 到这说明只剩两个数了。
            if (mountainArr.get(s) == target) {
                return s;
            } else if (mountainArr.get(e) == target) {
                return e;
            } else {
                return -1;
            }
        }

        int mv = mountainArr.get(m);

        if (bFlat) {
            // 是山前平缓区
            if (mv < target) {
                return this.findPrevious(m + 1, e);
            } else {
                return this.findPrevious(s, m);
            }
        } else {
            // 可能没在山前
            // 此处判断中值是不是在山前
            // 取前一个值
            int mbv = mountainArr.get(m - 1);
            if (mbv < mv) {
                // 中值在山前
                this.bFlat = true;
                // 缩小山后高能区范围
                this.ab = Math.max(m, this.ab);
            } else if (mbv > mv) {
                // 在山后
                if (mv > target) {
                    this.ab = Math.max(m, this.ab);
                } else {
                    // 相等或小于视为右边界
                    this.aa = Math.min(m, this.aa);
                }
            }

            if (bFlat && mv < target) {
                // 中值在山前的处理
                // 中值比目标小，重置，向后找
                bFlat = false;
                return this.findPrevious(m + 1, e);
            }
            // 其余情况向前找
            return this.findPrevious(s, m);
        }
    }


    private int findLast(int s, int e) {
        if (s == e) {
            // 只剩一个数
            if (mountainArr.get(s) == target) {
                return s;
            } else {
                return -1;
            }
        }
        int m = (e + s) / 2;
        if (m == s) {
            // 到这说明只剩两个数了。
            if (mountainArr.get(s) == target) {
                return s;
            } else if (mountainArr.get(e) == target) {
                return e;
            } else {
                return -1;
            }
        }

        int mv = mountainArr.get(m);

        if (aFlat) {
            // 是山后平缓区
            if (mv > target) {
                return this.findLast(m + 1, e);
            } else {
                return this.findLast(s, m);
            }
        } else {
            // 可能没在山后
            // 此处判断中值是不是在山后
            // 取前一个值
            int mbv = mountainArr.get(m - 1);
            if (mbv > mv) {
                // 中值在山后
                this.aFlat = true;
            }

            if (aFlat && mv > target) {
                // 中值在山后的处理
                // 中值比目标大，向后找
                return this.findLast(m + 1, e);
            }
            // 其余情况重置向前找
            aFlat = false;
            return this.findLast(s, m);
        }
    }
    public static void main(String[] args) {
//        int[] nums = {2,3,4,5,3,1};
//        int target = 1;
//        Solution solution = new Solution();
//        int result = solution.findInMountainArray(target, new MountainArray(nums));
//        System.out.println(result);

        System.out.println(new BigDecimal("1").compareTo(BigDecimal.ZERO));
    }
}
