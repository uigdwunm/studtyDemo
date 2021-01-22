package leetCode.medium659;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public boolean isPossible(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        Deque<Integer> dequeL = new LinkedList<>();
        Deque<Integer> dequeR = new LinkedList<>();

        // 先把数先都放进去
        for (int num : nums) {
            dequeR.addLast(num);
        }

        return this.dfs(dequeL, dequeR, dequeR.pollFirst(), 1, true);
    }

    private boolean dfs(Deque<Integer> dequeL, Deque<Integer> dequeR, Integer last, int currCount, boolean isToRight) {
        while (dequeL.size() != 0 || dequeR.size() != 0) {
            if (isToRight) {
                // 向右
                Integer curr = dequeR.pollFirst();
                if (curr.equals(last)) {
                    // 重复了，开个新分支试试
                    if (this.dfs(dequeL, dequeR, curr, 1, isToRight)) {
                        // 成功了，返回成功
                        return true;
                    } else {
                        dequeL.addLast(curr);
                    }
                } else if (curr == last + 1) {
                    currCount++;
                    last = curr;
                } else if (currCount > 2) {
                    // 满足条件,清零计算下一个。
                    last = curr;
                    currCount = 1;
                } else {
                    return false;
                }

                if (dequeR.isEmpty()) {
                    if (currCount > 2) {
                        last = dequeL.pollLast();
                        currCount = 1;
                        // 换方向
                        isToRight = false;
                    } else {
                        return false;
                    }
                }
            } else {
                // 向左
                Integer curr = dequeL.pollLast();
                if (curr.equals(last)) {
                    dequeR.addFirst(curr);
                } else if (curr == last - 1) {
                    currCount++;
                    last = curr;
                } else if (currCount > 2) {
                    last = curr;
                    currCount = 1;
                } else {
                    return false;
                }

                if (dequeL.isEmpty()) {
                    if (currCount > 2) {
                        last = dequeR.pollFirst();
                        currCount = 1;
                        // 换方向
                        isToRight = true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean possible = solution.isPossible(new int[]{1, 2, 3, 4, 4, 5});
        System.out.println(possible);
    }
}
