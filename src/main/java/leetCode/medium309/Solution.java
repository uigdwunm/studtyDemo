package leetCode.medium309;

public class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length < 3) {
            if (length > 1) {
                // 两个
                return prices[1] > prices[0] ? prices[1] - prices[0] : 0;
            } else {
                return 0;
            }
        }

        int result = 0;
        int temp = prices[0];
        for (int i = 1; i < length; ++i) {
            prices[i - 1] = prices[i] - temp;
            temp = prices[i];
        }

        result = prices[0] > 0 ? prices[0] : result;
        result = prices[1] > 0 ? result + prices[1] : result;

        for (int i = 2; i < length - 1; ++i) {
            if (prices[i] > 0) {
                if (prices[i - 1] < 0) {
                    if (prices[i - 2] > 0) {
                        temp = result + prices[i - 1] + prices[i];
                        // 前后需要选一个
                        if (prices[i] > prices[i - 2]) {
                            // 选后一个
                            result = temp - prices[i - 1] + prices[i];
                            if (temp > result) {
                                result = temp;
                            } else if (temp == result) {
                                //都可以，选前面
                                prices[i] = -1;
                            }
                        } else {
                            // 选前一个
//                            result = Math.max(result, temp);
                            if (temp > result) {
                                // 全都要
                                result = temp;
                            } else {
                                // 不能全都要
                                // 后一个当作冷冻期的置为-1
                                prices[i] = -1;
                            }
                        }
                    } else {
                        // 正好是冷冻期
                        result += prices[i];
                    }
                } else {
                    result += prices[i];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {2,5,8,3,8,2,6};
        int i = solution.maxProfit(num);
        System.out.println(i);
    }
}
