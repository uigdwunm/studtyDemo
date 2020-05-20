package leetCode.medium22;

import java.util.*;

class Solution {
    private static final char lb = '(';
    private static final char rb = ')';
    private List<String> result;
    private char[] template;
    public List<String> generateParenthesis(int n) {
        result = new LinkedList<String>();
        template = new char[2 * n];
        this.dfs(n, n, 0);

        return result;
    }

    private void dfs(int lc, int rc, int i) {
        if (i == template.length) {
            result.add(new String(this.copy()));
            return;
        }

        if (lc > 0) {
            template[i] = lb;
            this.dfs(lc - 1, rc, i + 1);
        }
        if (rc > lc) {
            template[i] = rb;
            this.dfs(lc, rc - 1, i + 1);
        }
    }

    private char[] copy() {
        char[] copy = new char[template.length];
        System.arraycopy(template, 0, copy, 0, template.length);
        return copy;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.generateParenthesis(4);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
