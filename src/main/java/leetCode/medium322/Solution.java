package leetCode.medium322;

import java.util.Arrays;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/8/19 8:40
 **/
public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        long start = System.nanoTime();

        int compute = s.coinChange(new int[]{186, 419, 83, 408}, 6249);
//        int compute = s.coinChange(new int[]{1, 2, 5}, 11);
        long end = System.nanoTime();
        System.out.println(compute);
        System.out.println(end - start);
    }

//    public int coinChange(int[] coins, int amount) {
//        int max = amount + 1;
//        int[] dp = new int[amount + 1];
//        Arrays.fill(dp, max);
//        dp[0] = 0;
//        for (int i = 1; i <= amount; i++) {
//            for (int coin : coins) {
////            System.out.println(++count);
//                if (coin <= i) {
//                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
//                }
//            }
//        }
//        return dp[amount] > amount ? -1 : dp[amount];
//    }


    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        int[] arr = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i > coin) {
                    int index = i - coin;
                    if (arr[index] != 0) {
                        if (arr[index] == -1) {
                            arr[i] = -1;
                        } else if (arr[i] == 0 || arr[i] == -1) {
                            arr[i] = arr[index] + 1;
                        } else {
                            arr[i] = Math.min(arr[index] + 1, arr[i]);
                        }
                    }
                } else if (i == coin) {
                    arr[i] = 1;
                } else if (arr[i] < 1) {
                    arr[i] = -1;
                }
            }
        }

        return arr[amount];
    }

//    public int coinChange(int[] coins, int amount) {
//        if (amount <= 0) {
//            return 0;
//        }
//        arr = new int[amount];
//        return this.compute(coins, amount);
//    }

//    private int compute(int[] coins, int amount) {
//        int index = amount - 1;
//        if (arr[index] != 0) {
//            return arr[index];
//        }
//        int lessSize = -1;
//
//        for (int coin : coins) {
////            System.out.println(++count);
//            if (amount > coin) {
//                int computeSize = this.compute(coins, amount - coin);
//                if (computeSize != -1 && (lessSize < 0 || lessSize > computeSize)) {
//                    lessSize = ++computeSize;
//                }
//            } else if (amount == coin) {
//                return 1;
//            }
//        }
//        arr[index] = lessSize;
//        return lessSize;
//    }


    private int count;


//
//    public int coinChange(int[] coins, int amount) {
//        if (amount == 0) {
//            return 0;
//        }
//        arr = new int[amount];
////        Arrays.fill(arr, -1);
//        return compute(coins, amount);
//    }
//
//    private int compute(int[] coins, int amount) {
//        int index = amount - 1;
//        if (arr[index] != -1) {
//            return arr[index];
//        }
//        int lessSize = -1;
//        for (int coin : coins) {
//            int compute = compute(coins, amount - coin);
//        }
//
//
//        arr[index] = lessSize;
//        return lessSize;
//    }

}
