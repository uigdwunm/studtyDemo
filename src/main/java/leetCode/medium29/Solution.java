package leetCode.medium29;

public class Solution {
    int result = 0;

    public int divide(int dividend, int divisor) {

        if (Integer.MIN_VALUE == dividend && divisor == 1) {
            // 有一种无法处理的特殊情况
            return dividend;
        }
        boolean isNegative;
        if (dividend < 0 && divisor < 0) {
            // 全小于0
            isNegative = false;
        } else if (dividend < 0) {
            isNegative = true;
            divisor = -divisor;
        } else if (divisor < 0) {
            isNegative = true;
            dividend = -dividend;
        } else {
            // 全大于0
            isNegative = false;
            dividend = -dividend;
            divisor = -divisor;
        }
        // 到这里全是负数

        this.subDivide(dividend, divisor);
        return isNegative ? -result : result;
    }

    private void subDivide(int dividend, int divisor) {
        if (divisor < dividend) {
            // 被除数更大
            return;
        }

        if (dividend == divisor) {
            // 相等的情况
            result++;
            return;
        }
        int i = 1;
        int d;
        int remain = dividend - divisor;
        if (remain <= divisor) {
            i = i + i;
            d = divisor + divisor;
            remain = dividend - d;
            while (remain <= d) {
                if (Integer.MAX_VALUE - i < i) {
                    result = Integer.MAX_VALUE;
                    return;
                }
                i = i + i;
                d = d + d;
                remain = dividend - d;
            }
        }
        result += i;
        this.subDivide(remain, divisor);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int divide = solution.divide(Integer.MIN_VALUE, 1);
        System.out.println(divide);
    }
}
