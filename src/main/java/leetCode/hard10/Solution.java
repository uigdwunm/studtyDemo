package leetCode.hard10;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "a";
        String p = "a*";
        System.out.println(solution.isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        // 匹配字符串没值的情况
        if (pArr.length == 0) {
            return sArr.length <= 0;
        }
        // 匹配有值但原字符串没值的情况
        if (sArr.length == 0) {
            // 直着走一遍就完了
            return this.noPattern(pArr);
        }
        // 再往下就是都有值的情况了

        if (pArr.length == 1) {
            // 这个也特么得处理
            return sArr.length == 1 && charMatch(sArr[0], pArr[0]);
        }

        boolean[][] memo = new boolean[s.length()][p.length()];
        // 给0，0位置赋值
        memo[0][0] = charMatch(sArr[0], pArr[0]);
        memo[0][1] = (memo[0][0] && pArr[1] == '*');
        // 要给si为0时赋好值

        // 前边的是否可以视为没有
        this.initFirstPArr(sArr[0], pArr, memo[0]);

        // 准备工作完成，进入主要流程
        for (int si = 1; si < sArr.length; si++) {
            for (int pi = 1; pi < pArr.length; pi++) {
                if (memo[si-1][pi-1] && charMatch(sArr[si], pArr[pi])) {
                    memo[si][pi] = true;
                } else if (pArr[pi] == '*') {
                    if (pi - 2 > 0 && memo[si][pi-2]) {
                        // 视为0个的情况
                        memo[si][pi] = true;
                    } else if (memo[si][pi-1]) {
                        // 视为1个的情况
                        memo[si][pi] = true;
                    } else if (memo[si-1][pi] && charMatch(sArr[si], pArr[pi-1])) {
                        // 视为多个的情况
                        memo[si][pi] = true;
                    }
                }
            }
        }
        return memo[sArr.length-1][pArr.length-1];
    }

    private void initFirstPArr(char firstC, char[] pArr, boolean[] firstPArr) {
        // 前边的是否可以视为没有
        boolean isNull = pArr[1] == '*';
        for (int pi = 2; pi < pArr.length; pi++) {
            if (pArr[pi] != '*') {
                // 当前不是*
                if (pArr[pi - 1] == '*') {
                    // 看一眼前一个,是*，可以无视前边的，只比对的当前的
                    if (isNull) {
                        firstPArr[pi] = charMatch(firstC, pArr[pi]);
                    }
                    // 不能无视前边的，那这个怎么都是错的了
                } else {
                    // 前一个不是*，肯定是错的了，顺便更新值
                    isNull = false;
                }
            } else {
                // 当前是*
                if (firstPArr[pi - 2]) {
                    // 前边的是对的，继承
                    firstPArr[pi] = true;
                } else if (isNull) {
                    // 前边的是错的
                    // 但前边可以视为没有
                    firstPArr[pi] = charMatch(firstC, pArr[pi - 1]);
                } else {
                    // 前边不能视为没有，那只能继承过来
                    firstPArr[pi] = false;
                }
            }
        }
    }

    // 匹配字符串有值但原字符串没值的情况
    private boolean noPattern(char[] pArr) {
        boolean notStar = false;
        for (int i = 0; i < pArr.length; i++) {
            if (pArr[i] != '*') {
                if (notStar) {
                    return false;
                }
                notStar = true;
            } else {
                notStar = false;

            }
        }
        return !notStar;
    }

    private boolean charMatch(char sc, char pc) {
        return sc == pc || pc == '.';
    }
}
