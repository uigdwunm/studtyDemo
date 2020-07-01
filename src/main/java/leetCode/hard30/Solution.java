package leetCode.hard30;

import java.util.*;
import java.util.function.BiFunction;

public class Solution {
    private String s;
    private String[] words;
    // map统计相应字符串出现次数
    private final Map<String, Integer> map = new HashMap<>();
    // 双端队列，按顺序记录需要的字符串
    private final Deque<String> deque = new LinkedList<>();
    // 边界指针
    private int end;
    // 将map中对应value加1
    private final BiFunction<String, Integer, Integer> addF = (k, v) -> ++v;
    // 结果
    private final List<Integer> result = new LinkedList<>();

    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length < 1 || s.isEmpty()) {
            return Collections.emptyList();
        }
        this.s = s;
        this.words = words;
        // 单个单词长度
        int wl = words[0].length();
        // 边界，记录最大可能的起始位置
        this.end = s.length() - words.length * wl;
        // 初始化map，value是数量
        for (String word : words) {
            map.compute(word, (k, v) -> v == null ? 1 : ++v);
        }

        // 以单个单词长度循环，
        for (int i = 0; i < wl; i++) {
            // 每次匹配都会以单词长度为粒度，从头匹配到尾
            this.match(i);
            // 重置map所有值，将双向队列中的值都倒灌到map中
            this.resetMap(this.deque);
        }

        return result;
    }

    // 匹配的过程，si表示起始位置索引
    private void match(int si) {
        if (si > this.end) {
            // 触及边界，剩余长度不够了
            return;
        }
        int wl = this.words[0].length();

        do {
            int i = si + deque.size() * wl;
            String key = s.substring(i, i + wl);
            Integer value = map.get(key);
            if (value == null) {
                // 没匹配到
                // 重置
                this.resetMap(this.deque);
                // 跳一个单位长度，继续匹配
                this.match(si + wl);
                return;
            } else if (value > 0) {
                // 还有余量
                this.deque.add(key);
                map.put(key, --value);
            } else {
                // 没有余量了
                // 挨个从头部取，直到找到对应的，倒灌到map中
                for (String pop = deque.pop(); !pop.equals(key); pop = deque.pop()) {
                    map.compute(pop, this.addF);
                }
                // 因为对应的也被pop了，把新的加到队列尾部
                this.deque.add(key);
                // 从新的地方跳一个单位长度，继续匹配
                this.match(i - (deque.size() - 1) * wl);
                return;
            }
        } while (deque.size() != words.length);

        // 出循环了，说明匹配成功一次
        this.result.add(si);
        // 跳一个单位
        map.compute(deque.pop(), this.addF);
        // 跳一个单位继续匹配
        this.match(si + wl);
    }

    private void resetMap(Deque<String> deque) {
        while (!deque.isEmpty()) {
            this.map.compute(deque.pop(), this.addF);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aaa";
        String[] words = {"a", "a"};
        List<Integer> list = solution.findSubstring(s, words);
        System.out.println(list);
    }
}
