package leetCode.medium542;

import java.util.*;

public class Solution {
    private enum DIRECTION {
        UP, DOWN, LEFT, RIGHT
    }

    // 这里记录对应的值是否被标记过
    boolean[][] shadow;
    // 原数组，也是结果数组
    int[][] matrix;
    // 横向长度
    int xl;
    // 纵向长度
    int yl;


    public int[][] updateMatrix(int[][] matrix) {
        LinkedList<Object> list = new LinkedList<>();
        list.poll();
        this.xl = matrix.length;
        this.yl = matrix[0].length;
        this.matrix = matrix;
        this.shadow = new boolean[xl][yl];

        // 随便找到一个0
        for (int i = 0; i < xl; i++) {
            for (int j = 0; j < yl; j++) {
                if (matrix[i][j] == 0) {
                    // 找到了
                    // 向四个方向传播
                    this.toUp(i, j);
                    this.toDown(i, j);
                    this.toLeft(i, j);
                    this.toRight(i, j);
                    break;
                }
            }
        }

        return matrix;
    }

    // 递归，找到第一个0，蔓延直到碰到第二个0，反向蔓延
    // 传进来的还有过来时期待的距离，
    private void spread(int x, int y, int distance, DIRECTION direction) {
        if (matrix[x][y] != 0) {
            if (shadow[x][y]) {
                if (matrix[x][y] <= distance) {
                    return;
                }
            } else {
                // 未被标记过
                shadow[x][y] = true;
            }
            matrix[x][y] = distance;
            // 继续蔓延，直到碰到下个0或边界
            // 从哪个方向过来的，就向其余方向蔓延
            switch (direction) {
                case UP:
                    this.toUp(x, y);
                    this.toLeft(x, y);
                    this.toRight(x, y);
                    break;
                case DOWN:
                    this.toDown(x, y);
                    this.toLeft(x, y);
                    this.toRight(x, y);
                    break;
                case LEFT:
                    this.toUp(x, y);
                    this.toDown(x, y);
                    this.toLeft(x, y);
                    break;
                case RIGHT:
                    this.toUp(x, y);
                    this.toDown(x, y);
                    this.toRight(x, y);
                    break;
            }
        } else {
            this.shadow[x][y] = true;
            this.toUp(x, y);
            this.toLeft(x, y);
            this.toRight(x, y);
            this.toDown(x, y);
        }
    }

    private void toUp(int x, int y) {
        if (x + 1 == xl) {
            // 超限了
            return;
        }

        if (matrix[x][y] == 0) {
            // 为0的情况
            do {
                x++;
                if (matrix[x][y] == 0) {
                    // 连续为0，
                    // 看是否标记过
                    if (shadow[x][y]) {
                        // 标记过，可以停下
                        return;
                    } else{
                        // 没有标记过,标记上
                        shadow[x][y] = true;
                        // 直角方向蔓延
                        this.toLeft(x, y);
                        this.toRight(x, y);
                    }
                } else {
                    // 遇见非0，可以传播
                    break;
                }
            } while (x + 1 != xl);
        } else {
            x++;
        }

        // 可以传播
        this.spread(x, y, matrix[x - 1][y] + 1, DIRECTION.UP);
    }

    private void toDown(int x, int y) {
        if (x - 1 == -1) {
            // 超限了
            return;
        }

        if (matrix[x][y] == 0) {
            // 为0的情况
            do {
                x--;
                if (matrix[x][y] == 0) {
                    // 连续为0，
                    // 看是否标记过
                    if (shadow[x][y]) {
                        // 标记过
                        return;
                    } else{
                        // 没标记过
                        shadow[x][y] = true;
                        // 直角方向蔓延
                        this.toLeft(x, y);
                        this.toRight(x, y);
                    }
                } else {
                    // 遇见非0，可以传播
                    break;
                }
            } while (x - 1 != -1);
        } else {
            x--;
        }

        // 可以传播
        this.spread(x, y, matrix[x + 1][y] + 1, DIRECTION.DOWN);
    }

    private void toLeft(int x, int y) {
        if (y - 1 == -1) {
            // 超限了
            return;
        }

        if (matrix[x][y] == 0) {
            // 为0的情况
            do {
                y--;
                if (matrix[x][y] == 0) {
                    // 连续为0，
                    // 看是否标记过
                    if (shadow[x][y]) {
                        // 标记过
                        return;
                    } else{
                        // 没标记过
                        shadow[x][y] = true;
                        // 直角方向蔓延
                        this.toUp(x, y);
                        this.toDown(x, y);
                    }
                } else {
                    // 遇见非0，可以传播
                    break;
                }
            } while (y - 1 != -1);
        } else {
            y--;
        }

        // 可以传播
        this.spread(x, y, matrix[x][y + 1] + 1, DIRECTION.LEFT);
    }

    private void toRight(int x, int y) {
        if (y + 1 == yl) {
            // 超限了
            return;
        }

        if (matrix[x][y] == 0) {
            // 为0的情况
            do {
                y++;
                if (matrix[x][y] == 0) {
                    // 连续为0，
                    // 是否标记过
                    if (shadow[x][y]) {
                        // 标记过
                        return;
                    } else {
                        // 没标记过
                        shadow[x][y] = true;
                        // 直角方向蔓延
                        this.toUp(x, y);
                        this.toDown(x, y);
                    }
                } else {
                    // 遇见非0，可以传播
                    break;
                }
            } while (y + 1 != yl);
        } else {
            y++;
        }

        // 可以传播
        this.spread(x, y, matrix[x][y - 1] + 1, DIRECTION.RIGHT);
    }






    public static void main(String[] args) {
//        Gson gson = new Gson();
//        List<List<Integer>> list = gson.fromJson("", new TypeToken<List<List<Integer>>>(){}.getType());
//        for (List<Integer> integers : list) {
//            StringJoiner joiner = new StringJoiner(", ", "new int[] {", "},");
//            for (Integer integer : integers) {
//                joiner.add(integer.toString());
//            }
//            System.out.println(joiner.toString());
//        }
//        System.out.println();
        int[][] ints = new int[][]{
                new int[] {0},
                new int[] {0},
                new int[] {0}
        };
        Solution solution = new Solution();
        ints = solution.updateMatrix(ints);

        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }

    }
}
