package leetCode.medium47;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    private final List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int l = nums.length;
        if (l < 1) {
            // 传入空数组
            return Collections.emptyList();
        }

        // 排序放入双端队列
        Deque<Integer> deque = IntStream.of(nums)
                .sorted()
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        this.find(deque, l, new ArrayDeque<>(l));

        return this.result;
    }

    private void find(Deque<Integer> deque, int n, Deque<Integer> data) {
        if (n == 0) {
            // 结束条件
            this.result.add(new ArrayList<>(data));
            return;
        }

        int size = deque.size();
        Integer curr = deque.removeFirst();
        data.addLast(curr);
        // 进入下级
        this.find(deque, n - 1, data);
        data.removeLast();
        deque.addLast(curr);
        // 记录上一个
        Integer last = curr;
        for (int i = 1; i < size; ++i) {
            curr = deque.removeFirst();
            if (!curr.equals(last)) {
                // 不同的才继续，相同的直接挪到最后
                data.addLast(curr);
                this.find(deque, n - 1, data);
                data.removeLast();
                last = curr;
            }
            deque.addLast(curr);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.permuteUnique(new int[]{1, 1, 2, 2});
        for (List<Integer> list : lists) {
            System.out.println(list);
        };
    }
}
