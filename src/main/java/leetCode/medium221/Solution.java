package leetCode.medium221;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Solution {
    private char[][] matrix;

    // 目前最长的边长
    int max;

    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        this.matrix = matrix;

        int x = 0;
        int y = 0;
        do {
            this.findDiagonal(x++, y++);
        } while (x < matrix.length && y < matrix[0].length);
        return max * max;
    }

    private void findDiagonal(int sx, int sy) {
        int l = 2;
        if (matrix[sx][sy] == '1') {
            // 尝试解析正方形
            l = this.findSquare(sx, sy, l - 1);
        }
        if (sy < matrix[0].length) {
            this.findUp(sx, sy + 1, l - 1);
        }
        if (sx < matrix.length) {
            this.findRigth(sx + 1, sy, l - 1);
        }
    }

    // l是已知的正方形边长，表示可以节省判断的
    // 向上判断
    private void findUp(final int sx, int sy, int l) {
        while (sy < matrix[0].length) {
            if (matrix[sx][sy] == '1') {
                l = this.findSquare(sx, sy, l - 1);
            }
            sy++;
        }
    }

    // 向右判断
    private void findRigth(int sx, final int sy, int l) {
        while (sx < matrix.length) {
            if (matrix[sx][sy] == '1') {
                l = this.findSquare(sx, sy, l - 1);
            }
            sx++;
        }
    }

    // 传入的sx，sy是正方形的一角
    // l是初始正方形边长，
    // 返回扩大判断后的边长
    private int findSquare(int sx, int sy, int l) {
        if (l < 2) {
            l = 1;
        }
        // 先找到对角(l可能为1)
        int ex = sx + l;
        int ey = sy + l;

        // 从对角位置溜边判断正方形

        while (ex < matrix.length && ey < matrix[0].length) {
            if (!this.checkX(ey, sx, ex) || !this.checkY(ex, sy, ey) || matrix[ex][ey] == '0') {
                this.max = Math.max(this.max, l);
                return l;
            }
            l++;
            // 上个长度是正方形
            ex++;
            ey++;

        }
        this.max = Math.max(this.max, l);
        return l;
    }

    // 没判断最后一个
    private boolean checkX(final int y, int from, int end) {
        do {
            if (matrix[from][y] == '0') {
                return false;
            }
            from++;
        } while (from < end);

        return true;
    }

    // 没判断最后一个
    private boolean checkY(final int x, int from, int end) {
        do {
            if (matrix[x][from] == '0') {
                return false;
            }
            from++;
        } while (from < end);

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Gson gson = new Gson();
        Type type = new TypeToken<char[][]>(){}.getType();
        String json = "[" +
                "['1','0','1','1','1','0','0','0','1','0']," +
                "['0','1','0','0','0','0','0','1','1','0']," +
                "['0','1','0','1','0','0','0','0','1','1']," +
                "['1','1','1','0','0','0','0','0','1','0']," +
                "['0','1','1','1','0','0','1','0','1','0']," +
                "['1','1','0','1','1','0','1','1','1','0']]";
        char[][] matrix = gson.fromJson(json, type);
        int i = solution.maximalSquare(matrix);
        System.out.println(i);
    }
}
