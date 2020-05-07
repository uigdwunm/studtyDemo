package leetCode.medium17;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final char[][] datas = {
        new char[0],
        new char[0],
        new char[]{'a', 'b', 'c'},
        new char[]{'d', 'e', 'f'},
        new char[]{'g', 'h', 'i'},
        new char[]{'g', 'k', 'l'},
        new char[]{'m', 'n', 'o'},
        new char[]{'p', 'q', 'r', 's'},
        new char[]{'t', 'u', 'v'},
        new char[]{'w', 'x', 'y', 'z'}
    };
    public List<String> letterCombinations(String digits) {
        // 传入的字符串转成的数组
        int[] strArr = new int[digits.length()];
        // 结果的长度
        int length = 1;
        for (int i = 0; i < digits.length(); i++) {
            strArr[i] = digits.charAt(i) - 48;
            length *= datas[strArr[i]].length;
        }

        List<String> result = new ArrayList<>(length);

        // 每个位置的索引
        int[] iArr = new int[strArr.length];

        result.add(this.assemble(strArr, iArr));
        for (int i = 1; i < length; i++) {
            // 先搞进位
            for (int k = iArr.length - 1; k > -1; k--) {
                if (iArr[k]++ != datas[strArr[k]].length - 1) {
                    break;
                }
                iArr[k] = 0;
            }
            result.add(this.assemble(strArr, iArr));
        }

        return result;
    }

    private String assemble(int[] strArr, int[] iArr) {
        StringBuilder sb = new StringBuilder(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            sb.append(datas[strArr[i]][iArr[i]]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.letterCombinations("89");
        System.out.println(strings);

    }
}
