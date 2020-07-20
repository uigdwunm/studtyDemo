package leetCode.medium面试题1713;

import java.util.*;

public class Solution {
    // 第一层是长度，第二层是尾字母，第三层是首字母。
    private List<Map<Character, Map<Character, Set<String>>>> dict;

    public int respace(String[] dictionary, String sentence) {
        if (sentence.length() == 0 || dictionary.length == 0) {
            return 0;
        }
        int maxWord = this.convertDict(dictionary, sentence.length());
        if (maxWord == 0) {
            return sentence.length();
        }
        int[] memo = new int[sentence.length() + 1];
        memo[1] = this.match(sentence, 0, 0) ? 0 : 1;
        for (int e = 2; e <= sentence.length(); ++e) {
            int s = e;
            memo[e] = memo[e - 1] + 1;
            while (s > 0 && e - s != maxWord) {
                if (this.match(sentence, s - 1, e - 1)) {
                    memo[e] = Math.min(memo[e], memo[s - 1]);
                }
                --s;
            }
        }
        return memo[sentence.length()];
    }

    private int convertDict(String[] dictionary, int length) {
        int maxWord = 0;

        Map<Integer, Map<Character, Map<Character, Set<String>>>> tempDict = new HashMap<>();
        for (String str : dictionary) {
            if (str.length() > length || str.length() < 1) {
                // 长度大于字符串的
                continue;
            }
            maxWord = Math.max(maxWord, str.length());
            // 正常范围，长度大于0的
            tempDict.compute(str.length(), (k1, v1) -> {
                if (v1 == null) {
                    // 第一层需要新建
                    Map<Character, Map<Character, Set<String>>> map1 = new HashMap<>();
                    Map<Character, Set<String>> map2 = new HashMap<>();
                    HashSet<String> set = new HashSet<>();
                    set.add(str);
                    map2.put(str.charAt(0), set);
                    map1.put(str.charAt(str.length() - 1), map2);
                    return map1;
                }
                // 第一层已经有了
                v1.compute(str.charAt(str.length() - 1), (k2, v2) -> {
                    if (v2 == null) {
                        // 第二层需要新建
                        Map<Character, Set<String>> map2 = new HashMap<>();
                        HashSet<String> set = new HashSet<>();
                        set.add(str);
                        map2.put(str.charAt(0), set);
                        return map2;
                    }
                    // 第二层已经有了
                    v2.compute(str.charAt(0), (k3, v3) -> {
                        if (v3 == null) {
                            HashSet<String> set = new HashSet<>();
                            set.add(str);
                            return set;
                        }
                        v3.add(str);
                        return v3;
                    });
                    return v2;
                });
                return v1;
            });
        }

        this.dict = new ArrayList<>(maxWord);
        for (int i = 0; i < maxWord; i++) {
            this.dict.add(tempDict.get(i + 1));
        }
        return maxWord;
    }

    private boolean match(String str, int s, int e) {
        Map<Character, Map<Character, Set<String>>> map1 = dict.get(e - s);
        if (map1 == null) {
            return false;
        }
        Map<Character, Set<String>> map2 = map1.get(str.charAt(e));
        if (map2 == null) {
            return false;
        }
        Set<String> set = map2.get(str.charAt(s));
        return set != null && set.contains(str.substring(s, e + 1));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] dictionary = {"asln"};
        String sentence = "fn";
        int respace = solution.respace(dictionary, sentence);
        System.out.println(respace);
    }
}
