package leetCode.medium5;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/24 9:12
 **/
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String babad = solution.longestPalindrome("abb");
        System.out.println(babad);

        System.out.println(1/2);
    }


    private char[] charArr;

    private int forwardsIndex = 0;
    private int backwardsIndex = 0;

    private int length = 1;

    public String longestPalindrome(String s) {
        // 空或只有一个字符的极限状态
        if(s==null || s.length()<2) {
            return s;
        }
        charArr = s.toCharArray();

        // 有两个字符的情况
        if(charArr.length == 2) {
            if(charArr[0] == charArr[1]) {
                return s;
            } else {
                return String.valueOf(charArr[0]);
            }
        }


        int i=1;
        for(; i < charArr.length-(length == 1 ? 1 : length/2); i++) {

            // 有中
            cycleCompare(i-1, i+1);

            // 无中
            cycleCompare(i-1, i);

        }

        // 额外情况
        cycleCompare(i-1, i);

//        System.out.println(charArr);
//        System.out.println(forwardsIndex);
//        System.out.println(backwardsIndex);
        return new String(charArr, forwardsIndex, length);
    }


    /**
     * 循环两个指针位置字符判断是否一样
     */
    private void cycleCompare(int forwardsIndex, int backwardsIndex) {
        int tempLength;
        while(forwardsIndex > -1 && backwardsIndex < charArr.length) {
            if(charArr[forwardsIndex] == charArr[backwardsIndex]) {
                // 字符相同
                forwardsIndex--;
                backwardsIndex++;
            } else {
                // 字符不同
                tempLength = backwardsIndex - forwardsIndex - 1;
                if(tempLength > this.length) {
                    // 长度更长
                    this.forwardsIndex = forwardsIndex + 1;
                    this.backwardsIndex = backwardsIndex - 1;
                    this.length = tempLength;
                }
                return;
            }
        }

        // 触及边界
        forwardsIndex++;
        backwardsIndex--;
        tempLength = backwardsIndex - forwardsIndex + 1;
        if(tempLength > this.length) {
            // 长度更长
            this.forwardsIndex = forwardsIndex;
            this.backwardsIndex = backwardsIndex;
            this.length = tempLength;
        }
    }

}
