package leetCode.hard37;

import leetCode.utils.GsonUtils;

import java.util.Arrays;

public class Solution {
  private final int gap = '0' + 1;

  public void solveSudoku(char[][] board) {
    this.test(board, 0, 0);
  }

  private boolean test(char[][] board, int x, int y) {
    int i = x;
    int j = y;
    for (; i < 9; ++i) {
      for (; j < 9; ++j) {
        char c = board[i][j];
        if (c == '.') {
          boolean[] nums = this.fill(board, i, j);
          for (int k = 0; k < 9; ++k) {
            if (!nums[k]) {
              board[i][j] = (char) (k + gap);
              if (this.test(board, i, j)) {
                return true;
              }
            }
          }
          board[x][y] = '.';
          return false;
        }
      }
      j = 0;
    }
    return true;
  }

  private boolean[] fill(char[][] board, int x, int y) {
    boolean[] nums = new boolean[9];
    int a, b;
    if (x < 3) {
      a = 0;
    } else if (x < 6) {
      a = 3;
    } else {
      a = 6;
    }
    if (y < 3) {
      b = 0;
    } else if (y < 6) {
      b = 3;
    } else {
      b = 6;
    }
    // 方块中的数都塞进去
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        char c = board[a + i][b + j];
        if (c != '.') {
          nums[c - gap] = true;
        }
      }
    }
    // 纵向航向的塞进去
    for (int i = 0; i < 9; ++i) {
      char c = board[x][i];
      if (c != '.') {
        nums[c - gap] = true;
      }
      c = board[i][y];
      if (c != '.') {
        nums[c - gap] = true;
      }
    }
    return nums;
  }

  public static void main(String[] args) {
    String json = "[" +
            "[\".\",\".\",\"9\",\"7\",\"4\",\"8\",\".\",\".\",\".\"]," +
            "[\"7\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"]," +
            "[\".\",\"2\",\".\",\"1\",\".\",\"9\",\".\",\".\",\".\"]," +
            "[\".\",\".\",\"7\",\".\",\".\",\".\",\"2\",\"4\",\".\"]," +
            "[\".\",\"6\",\"4\",\".\",\"1\",\".\",\"5\",\"9\",\".\"]," +
            "[\".\",\"9\",\"8\",\".\",\".\",\".\",\"3\",\".\",\".\"]," +
            "[\".\",\".\",\".\",\"8\",\".\",\"3\",\".\",\"2\",\".\"]," +
            "[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\"6\"]," +
            "[\".\",\".\",\".\",\"2\",\"7\",\"5\",\"9\",\".\",\".\"]" +
            "]";
    char[][] chars = GsonUtils.convertToCharArrArr(json);
    Solution solution = new Solution();
    try {

      solution.solveSudoku(chars);
    } finally {
      for (char[] aChar : chars) {
        System.out.println(Arrays.toString(aChar));
      }
    }
  }
}

//        ["5","1","9","7","4","8","6","3","2"],
//        ["7","8","3","6","5","2","4","1","9"],
//        ["4","2","6","1","3","9","8","7","5"],
//        ["3","5","7","9","8","6","2","4","1"],
//        ["2","6","4","3","1","7","5","9","8"],
//        ["1","9","8","5","2","4","3","6","7"],
//        ["9","7","5","8","6","3","1","2","4"],
//        ["8","3","2","4","9","1","7","5","6"],
//        ["6","4","1","2","7","5","9","8","3"]