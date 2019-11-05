package leetCode.medium6;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/25 13:59
 **/
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(String.valueOf(-123).toCharArray()));
        Solution solution = new Solution();
        String paypalishiring = solution.convert("abc", 3);
        System.out.println(paypalishiring);
    }

    private char[] resultArr;
    private int index = 0;

    public String convert(String s, int numRows) {
        char[] charArr = s.toCharArray();
        resultArr = new char[charArr.length];

        // 特殊情况
        if (numRows > 2) {
            // 正常情况，不做处理
        } else if (numRows == 2) {

        } else {
            return s;
        }

        // 循环次数指针

        int i = 0;
        // 第一个
        oneParamStow(charArr, numRows, i);


        // 中间所有的
        for(i++; i<numRows-1; i++) {
            twoParamStow(charArr, numRows, i);
        }

        // 最后一个
        oneParamStow(charArr, numRows, i);

        return new String(resultArr);
    }


    private void oneParamStow(char[] charArr, int numRows, int current) {
        while (current < charArr.length) {
            resultArr[index] = charArr[current];
            index ++;
            current += 2*numRows - 2;  // (numRows - 2) * 2 + 1 + 1
        }
    }

    private void twoParamStow(char[] charArr, int numRows, int current) {
        int numRows2 = current;
        numRows -= current + 1;
        while (current < charArr.length) {
            // 第一个
            resultArr[index] = charArr[current];
            index ++;
            current += (numRows - 1) * 2 + 2;

            // 第二个
            if (current >= charArr.length) {
                // 超出范围直接结束
                return;
            }
            resultArr[index] = charArr[current];
            index ++;
            current += (numRows2 - 1) * 2 + 2;
        }
    }
}
