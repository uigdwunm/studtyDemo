package leetCode.easy20;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solution {

    public boolean isValid(String s) {
        int l = s.length();
        if (l == 0) {
            return true;
        }

        Deque<Character> list = new LinkedList<>();

        for (int i = 0; i < l; ++i) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                list.push(c);
                continue;
            }
            if (list.isEmpty()) {
                return false;
            }
            Character pop = list.pop();
            if (c == ')') {
                if (pop != '(') return false;
            } else if (c == ']') {
                if (pop != '[') return false;
            } else if (c == '}') {
                if (pop != '{') return false;
            } else {
                return false;
            }
        }
        return list.size() == 0;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        Map<Object, Object> map = new HashMap<Object, Object>(){{
            put("", "");
            Solution solution1 = new Solution();
            solution1.isValid("()");
        }};

        boolean valid = solution.isValid("()");
        System.out.println(valid);
    }
}
