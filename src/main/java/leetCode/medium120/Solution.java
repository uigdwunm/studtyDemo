package leetCode.medium120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] arr = new int[triangle.size()];
        arr[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); ++i) {
            List<Integer> curr = triangle.get(i);
            // 最后一个
            arr[i] = arr[i - 1] + curr.get(i);

            for (int j = i - 1; j > 0 ; --j) {
                arr[j] = Math.min(arr[j], arr[j - 1]) + curr.get(j);
            }
            // 第一个
            arr[0] = arr[0] + curr.get(0);
        }

        return IntStream.of(arr).min().orElse(0);
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3,4));
        list.add(Arrays.asList(6,5,7));
        list.add(Arrays.asList(4,1,8,3));
        Solution solution = new Solution();
        int i = solution.minimumTotal(list);
        System.out.println(i);
    }
}
