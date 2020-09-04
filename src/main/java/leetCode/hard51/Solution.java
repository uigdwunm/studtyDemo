package leetCode.hard51;

import java.util.*;

public class Solution {
    private enum Dire {
        LU(-2, 1),
        UL(-1, 2),
        LD(-2, -1),
        DL(-1, -2),
        RU(2, 1),
        UR(1, 2),
        RD(2, -1),
        DR(1, -2);

        private final int xd;
        private final int yd;
        // 上一个是当前，那么下一个可行的路线
        private Dire[] l2c;

        Dire(int xd, int yd, Dire ... l2c) {
            this.xd = xd;
            this.yd = yd;
            this.l2c = l2c;
        }

        static {
            LU.l2c = new Dire[]{Dire.LU, Dire.UL, Dire.DL, Dire.UR, Dire.DR};
            UL.l2c = new Dire[]{Dire.LU, Dire.UL, Dire.LD, Dire.RU, Dire.RD};
            LD.l2c = new Dire[]{Dire.UL, Dire.LD, Dire.DL, Dire.UR, Dire.DR};
            DL.l2c = new Dire[]{Dire.LU, Dire.LD, Dire.DL, Dire.RU, Dire.RD};
            RU.l2c = new Dire[]{Dire.UL, Dire.DL, Dire.RU, Dire.UR, Dire.DR};
            UR.l2c = new Dire[]{Dire.LU, Dire.LD, Dire.RU, Dire.UR, Dire.RD};
            RD.l2c = new Dire[]{Dire.UL, Dire.DL, Dire.UR, Dire.DR, Dire.RD};
            DR.l2c = new Dire[]{Dire.LU, Dire.LD, Dire.RU, Dire.DR, Dire.RD};
        }

        public int getXd() {
            return xd;
        }

        public int getYd() {
            return yd;
        }

        public Dire[] getL2c() {
            return l2c;
        }
    }

    // 用于回滚
    Deque<int[]> deque = new LinkedList<>();
    // 快照当前棋盘状态
    char[][] arr;
    // 结果
    List<List<String>> result;
    // 初始的n，方便取用
    int n;


    public List<List<String>> solveNQueens(int n) {
        if (n < 4) {
            if (n == 1) {
                return Collections.singletonList(Collections.singletonList("Q"));
            }
            return Collections.emptyList();
        }
        arr = new char[n][n];
        result = new LinkedList<>();
        this.n = n;

        for (int i = 1; i < n - 1; ++i) {
            // 占用上
            int count = this.recordAll(0, i);

            HashSet<String> set = new HashSet<>(8, 1F);
            // 循环下一个
            for (Dire dire : Dire.values()) {
                int x = this.getXorY(dire.getXd());
                int y = this.getXorY(i + dire.getYd());

                String key = x + "," + y;

                if (set.contains(key)) {
                    continue;
                }
                set.add(x + "," + y);
                this.dfs(x, y, n - 1, dire);
            }

            // 回滚
            this.rollback(count);
        }

        return this.result;
    }

    // 姑且试试这样去重
    private final Set<String> testSet = new HashSet<>();
    
    private void dfs(int x, int y, int i, Dire last) {
        if (arr[x][y] != 0) {
            // 已存在
            return;
        }

        // 占用上
        int count = this.recordAll(x, y);

        if (i == 1) {
            // 成功
            List<String> list = new LinkedList<>();

            String s = new String(this.arr[0]);
            if (!testSet.add(s)) {
                // 重了
                return;
            }
            list.add(s);
            for (int j = 1; j < this.arr.length; j++) {
                list.add(new String(this.arr[j]));
            }
            this.result.add(list);
        } else {
            HashSet<String> set = new HashSet<>(8, 1F);
            // 循环下一个
            for (Dire dire : last.getL2c()) {
                int x1 = this.getXorY(x + dire.getXd());
                int y1 = this.getXorY(y + dire.getYd());
                String key = x1 + "," + y1;

                if (set.contains(key)) {
                    continue;
                }
                set.add(key);
                this.dfs(x1, y1, i - 1, dire);
            }
        }

        // 回滚
        this.rollback(count);

    }

    private int getXorY(int v) {
        if (v < 0) {
            return v + n;
        } else if (v >= n) {
            return v - n;
        } else {
            return v;
        }
    }

    // 记录
    private int recordAll(int x, int y) {
        char[][] arr = this.arr;
        int count = 0;
        int i = 0;
        // 横向和竖向
        for (; i < n; ++i) {
            count += this.record(arr, x, i);
            count += this.record(arr, i, y);
        }
        // 斜向
        if (x > y) {
            int x1 = x - y;
            int y1 = 0;
            while (x1 != n) {
                count += this.record(arr, x1++, y1++);
            }
            x1 = x + 1;
            y1 = y - 1;
            while (x1 != n && y1 != -1) {
                count += this.record(arr, x1++, y1--);
            }
            x1 = x - 1;
            y1 = y + 1;
            while (x1 != -1 && y1 != n) {
                count += this.record(arr, x1--, y1++);
            }
        } else {
            int x1 = 0;
            int y1 = y - x;
            while (y1 != n) {
                count += this.record(arr, x1++, y1++);
            }
            x1 = x + 1;
            y1 = y - 1;
            while (x1 != n && y1 != -1) {
                count += this.record(arr, x1++, y1--);
            }
            x1 = x - 1;
            y1 = y + 1;
            while (x1 != -1 && y1 != n) {
                count += this.record(arr, x1--, y1++);
            }
        }

        // 皇后位置
        arr[x][y] ='Q';
        this.deque.push(new int[]{x, y});
        return count + 1;
    }

    private int record(char[][] arr, int x, int y) {
        if (arr[x][y] == 0) {
            this.deque.push(new int[]{x, y});
            arr[x][y] = '.';
            return 1;
        }
        return 0;
    }

    // 回滚
    private void rollback(int count) {
        Deque<int[]> deque = this.deque;
        char[][] arr = this.arr;
        for (int i = 0; i < count; i++) {
            int[] poll = deque.poll();
//            assert poll != null;
            arr[poll[0]][poll[1]] = 0;
        }


    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> lists = solution.solveNQueens(4);
        for (List<String> list : lists) {

            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
