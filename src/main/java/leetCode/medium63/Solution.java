package leetCode.medium63;

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int xl = obstacleGrid.length;
        if (xl < 1) {
            return 0;
        }
        int yl = obstacleGrid[0].length;

        boolean flag = false;
        for (int yi = 0; yi < yl; yi++) {
            if (flag) {
                obstacleGrid[0][yi] = 0;
            } else if (obstacleGrid[0][yi] == 1) {
                obstacleGrid[0][yi] = 0;
                flag = true;
            } else {
                obstacleGrid[0][yi] = 1;
            }
        }
        flag = false;
        for (int xi = 1; xi < xl; xi++) {
            if (flag) {
                obstacleGrid[xi][0] = 0;
            } else if (obstacleGrid[xi][0] == 1) {
                obstacleGrid[xi][0] = 0;
                flag = true;
            } else {
                obstacleGrid[xi][0] = 1;
            }
        }

        for (int xi = 1; xi < xl; xi++) {
            for (int yi = 1; yi < yl; yi++) {
                if (obstacleGrid[xi][yi] == 1) {
                    obstacleGrid[xi][yi] = 0;
                } else {
                    obstacleGrid[xi][yi] = obstacleGrid[xi - 1][yi] + obstacleGrid[xi][yi - 1];
                }
            }
        }

        return obstacleGrid[xl - 1][yl - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] nums = {{1}, {0}};
        int i = solution.uniquePathsWithObstacles(nums);
        System.out.println(i);
    }
}
