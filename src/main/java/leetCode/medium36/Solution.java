package leetCode.medium36;

import leetCode.utils.GsonUtils;

import java.util.Arrays;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] arr1 = new boolean[10];
        boolean[] arr2 = new boolean[10];
        int a = '.';
        // 所有水平 和 所有垂直
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                int v = board[i][j];
                if (v != a) {
                    v -= '0';
                    if (!arr1[v]) {
                        arr1[v] = true;
                    } else {
                        return false;
                    }
                }


                v = board[j][i];
                if (v != a) {
                    v -= '0';
                    if (!arr2[v]) {
                        arr2[v] = true;
                    } else {
                        return false;
                    }
                }

            }
            Arrays.fill(arr1, false);
            Arrays.fill(arr2, false);
        }

        // 所有方块
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int k = i; k < 3; ++k) {
                    for (int l = j; l < 3; ++l) {
                        int v = board[k][l];
                        if (v != a) {
                            v -= '0';
                            if (!arr1[v]) {
                                arr1[v] = true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
                Arrays.fill(arr1, false);
            }
        }


        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String json = "[[\".\",\".\",\".\",\".\",\"5\",\".\",\".\",\"1\",\".\"],[\".\",\"4\",\".\",\"3\",\".\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\".\",\".\",\"3\",\".\",\".\",\"1\"],[\"8\",\".\",\".\",\".\",\".\",\".\",\".\",\"2\",\".\"],[\".\",\".\",\"2\",\".\",\"7\",\".\",\".\",\".\",\".\"],[\".\",\"1\",\"5\",\".\",\".\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\"],[\".\",\"2\",\".\",\"9\",\".\",\".\",\".\",\".\",\".\"],[\".\",\".\",\"4\",\".\",\".\",\".\",\".\",\".\",\".\"]]";
        char[][] ints = GsonUtils.convertToCharArrArr(json);
        boolean validSudoku = solution.isValidSudoku(ints);
        System.out.println(validSudoku);
    }
}
