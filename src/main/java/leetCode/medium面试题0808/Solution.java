package leetCode.medium面试题0808;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private final List<String> result = new ArrayList<>();

    public String[] permutation(String str) {
        int l = str.length();
        Map<Character, Integer> memo = new HashMap<>();
        for (int i = 0; i < l; i++) {
            memo.merge(str.charAt(i), 1, (ov, nv) -> ov + 1);
        }
        new LinkedList<>(memo.entrySet());
        Deque<int[]> deque = new ArrayDeque<>();
        for (Map.Entry<Character, Integer> entry : memo.entrySet()) {
            deque.add(new int[]{entry.getKey(), entry.getValue()});
        }

        this.dfs(new char[l], 0, deque);

        return result.toArray(new String[0]);
    }

    private void dfs(char[] arr, int i, Deque<int[]> deque) {
        if (i == arr.length) {
            this.result.add(new String(arr));
            return;
        }

        int l = deque.size();
        for (int j = 0; j < l; j++) {
            int[] kv = deque.removeFirst();
            char c = (char) (kv[0]);
            int count = kv[1];
            arr[i] = c;
            if (count == 1) {
                this.dfs(arr, i + 1, deque);
                deque.addLast(kv);
            } else {
                kv[1]--;
                deque.addLast(kv);
                this.dfs(arr, i + 1, deque);
                kv[1]++;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] ads = solution.permutation("ads");
        for (String ad : ads) {
            System.out.println(ad);
        }
    }
}
