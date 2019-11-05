package leetCode.easy7;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/25 16:31
 **/
public class Solution {

    public static void main(String[] args) {
        String url = "https://test2-1257273589.cos.ap-shanghai.myqcloud.com/wf/2019-04-10/189774946761707520.json?q-sign-?algorithm=sha1&q-ak=AKIDJWFHB7BFQtxvqbtfyPakmjGdovDmuWjU&q-sign-time=1561618655;1561619555&q-key-time=1561618655;1561619555&q-header-list=&q-url-param-list=&q-signature=1fed467db5d13a1782b63b67051396f88616d11e";

        String urlPreFix = "test2-1257273589";
        String bucketName = "https://test2-1257273589.cos.ap-shanghai.myqcloud.com";

        // 从url截取存储桶中的key
        int subIndex = url.indexOf(bucketName) + bucketName.length() + 1;
        int endIndex = url.indexOf('?');
        String key = null;
        if (endIndex == -1) {
            key = url.substring(subIndex);
        } else {
            key = url.substring(subIndex, endIndex);
        }

        System.out.println(key);
//        Solution solution = new Solution();
//        int reverse = solution.reverse(-2147483648);
//        System.out.println(reverse);
    }


    /**
     * Description
     * 解题思路大概就是从个位数开始取余，取余之后再除以10，原先的十位数就又变成个位数，循环取。
     * 类似把数字看成一个队列，从个位数一个一个取出来，组成新数。
     * 主要困难是边界检查。首先像这种正数反转永远不用判断个位数是否越界
     * int最大值2147483648 最高位是8，最低位是2，
     * 假如反过来一个10位数，最大的可能就是1463847412，传进来的数总不可能8463847412，传不进来的
     *
     * @author zhaolaiyuan
     * @Date 2019/6/26 16:42
     **/
    public int reverse(int x) {
        byte length = 0;
        int result = 0;
        final byte temp = 10;
        while (x != 0) {
            byte currentDigit = (byte) (x % temp);

            // 有十位数时只校验前九位就够了
            if (length == 9 && (result > Integer.MAX_VALUE / temp || result < Integer.MIN_VALUE / temp)) {
                return 0;
            }
            result = result * temp + currentDigit;
            x /= temp;
            length++;
        }

        return result;
    }


}
