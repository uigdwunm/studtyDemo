package leetCode.medium5614;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> dequeL = new LinkedList<>();
        Deque<Integer> dequeR = new LinkedList<>();
        int l = nums.length;
        int ki = 0;

        for (int i = 0; i < l; i++) {
            // 两个队列存满k个才进入最终阶段
            if (ki < k) {
                if (dequeR.size() > 0) {
                    // 第三阶段，开始存右边了就一直存右边
                    dequeR.addLast(nums[i]);
                } else if (dequeL.size() > 0){
                    // 第二阶段，如果发生了逆序的就往右边队列存
                    // 获取左边最后一个
                    Integer lLast = dequeL.peekLast();
                    if (lLast > nums[i]) {
                        // 逆序了，开始存右边
                        dequeR.addLast(nums[i]);
                    } else {
                        // 没逆序，继续存左边
                        dequeL.addLast(nums[i]);
                    }
                } else {
                    // 第一阶段，左边为空，先向左边放
                    dequeL.addLast(nums[i]);
                }
                ki++;
            } else {
                // 进入最终阶段，高速上换轮胎
                if (dequeR.size() > 0) {
                    // 右边还有值可以继续放
                    dequeR.addLast(nums[i]);

                    // 从交界处搞掉一个
                    dequeL.removeLast();
                    Integer last = dequeL.peekLast();
                    if (last == null) {
                        last = dequeR.removeFirst();
                        dequeL.addLast(last);
                    }
                    while(dequeR.size() != 0 && last <= dequeR.peekFirst()) {
                        last = dequeR.removeFirst();
                        dequeL.addLast(last);
                    }
                } else {
                    // 右边没值了
                    int num = nums[i];
                    if (dequeL.peekLast() > num) {
                        dequeL.removeLast();
                        if (dequeL.peekLast() > num) {
                            dequeR.addLast(num);
                        } else {
                            dequeL.addLast(num);
                        }
                    }
                }
            }
        }

        ki = 0;
        int[] result = new int[k];
        for (Integer num : dequeL) {
            result[ki] = num;
            ki++;
        }

        for (Integer num : dequeR) {
            result[ki] = num;
            ki++;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,5,2,6};
        solution.mostCompetitive(nums, 2);
    }
}