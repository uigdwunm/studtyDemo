package leetCode.medium692;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.compute(word, (mk, mv) -> mv == null ? 1 : mv + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(
            (o1, o2) -> {
                int compare = o1.getValue() - o2.getValue();
                if (compare == 0) {
                    return o2.getKey().compareTo(o1.getKey());
                }
                return compare;
            }
        );

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            priorityQueue.offer(entry);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        LinkedList<String> result = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : priorityQueue) {
            System.out.println(entry.getKey());
            result.addFirst(entry.getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
//        words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> strings = solution.topKFrequent(words, 4);
        System.out.println(strings);
        System.out.println("i".compareTo("love"));
    }
}
