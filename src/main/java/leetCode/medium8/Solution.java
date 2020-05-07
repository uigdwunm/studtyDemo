package leetCode.mdeium8;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/7/12 10:51
 **/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.myAtoi("-42");
        System.out.println(i);
    }

    public int myAtoi(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        int result = 0;
        char[] charArr = str.toCharArray();

        int i;
        boolean isNegative = false;

        // 符号后第一个是否为数字
        boolean firstIsNum = false;

        // 判断循环直到遇到第一个数字
        for(i=0; i < charArr.length; i++) {
            switch (charArr[i]) {
                case ' ':
                    continue;
                case '+':
                    break;
                case '-':
                    isNegative = true;
                    break;
                case '0':
                    firstIsNum = true;
                    result = 0;
                    break;
                case '1':
                    result = 1;
                    firstIsNum = true;
                    break;
                case '2':
                    result = 2;
                    firstIsNum = true;
                    break;
                case '3':
                    result = 3;
                    firstIsNum = true;
                    break;
                case '4':
                    result = 4;
                    firstIsNum = true;
                    break;
                case '5':
                    result = 5;
                    firstIsNum = true;
                    break;
                case '6':
                    result = 6;
                    firstIsNum = true;
                    break;
                case '7':
                    result = 7;
                    firstIsNum = true;
                    break;
                case '8':
                    result = 8;
                    firstIsNum = true;
                    break;
                case '9':
                    result = 9;
                    firstIsNum = true;
                    break;
                default:
                    // 其余都是无效情况
                    return 0;
            }
            i++;
            break;
        }

        // 判断符号后第一个字符是否数字，如果没有符号就跳过
        if(!firstIsNum) {
            if((i >= charArr.length || (result = isNumber(charArr[i])) == -1)) {
                return 0;
            }
            i++;
        }

        // 后续位数添加
        int temp;
        int resultLength = 1;
        for(; i<charArr.length; i++) {
            temp = isNumber(charArr[i]);
            if(temp == -1) {
                // 返回结果
                return isNegative ? result * -1 : result;
            } else {
                // 位数加一
                resultLength++;

                if(resultLength == 10) {
                    // 长度等于10时需要判断
                    if (result > Integer.MAX_VALUE / 10) {
                        // 超限返回
                        if (isNegative) {
                            // 负的
                            return Integer.MIN_VALUE;
                        } else {
                            // 正的
                            return Integer.MAX_VALUE;
                        }
                    } else if(Integer.MAX_VALUE / 10 == result) {
                        if (isNegative) {
                            // 负的
                            return temp > 8 ? Integer.MIN_VALUE : (result*10 + temp) * -1;
                        } else {
                            // 正的
                            return temp > 7 ? Integer.MAX_VALUE : result*10 + temp;
                        }
                    }
                } else if (resultLength > 10) {
                    // 长度大于10返回极限
                    if (isNegative) {
                        // 负的
                        return Integer.MIN_VALUE;
                    } else {
                        // 正的
                        return Integer.MAX_VALUE;
                    }
                }
                // 判断极限情况
                result = result * 10 + temp;
            }
        }

        return isNegative ? result * -1 : result;
    }

    private int isNumber(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                // 其余都是无效情况
                return -1;
        }
    }
}
