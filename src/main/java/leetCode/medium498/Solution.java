package leetCode.medium498;

import leetCode.utils.GsonUtils;

import java.util.Arrays;

public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int xl = matrix.length;
        if (xl < 1) {
            return new int[0];
        }
        int yl = matrix[0].length;
        int[] result = new int[xl * yl];

        // 当前位置
        int x = 0;
        int y = 0;
        // 上一步方向 true是向上，false下
        boolean dire = false;

        int i = 0;
        result[i++] = matrix[0][0];

        // 终结位置
        int endx = xl - 1;
        int endy = yl - 1;
        while (!(x == endx && y == endy)) {
            if (dire) {
                if (x == endx) {
                    ++y;
                } else {
                    ++x;
                }
                while (x != 0 && y != endy) {
                    result[i++] = matrix[x--][y++];
                }
                result[i++] = matrix[x][y];
            } else {
                if (y == endy) {
                    ++x;
                } else {
                    ++y;
                }
                while (y != 0 && x != endx) {
                    result[i++] = matrix[x++][y--];
                }
                result[i++] = matrix[x][y];
            }
            dire = !dire;
        }
        return result;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[][] ints = GsonUtils.convertToIntArrArr("[[1,2,3],[4,5,6],[7,8,9]]");
        int[] diagonalOrder = solution.findDiagonalOrder(ints);
        System.out.println(Arrays.toString(diagonalOrder));
    }
}
