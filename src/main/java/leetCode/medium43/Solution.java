package leetCode.medium43;

public class Solution {
    private char[] result = {'0'};

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] arr1 = num1.toCharArray();
        int l2 = num2.length();
        for (int i = l2 - 1; i > -1; --i) {
            char[] arr = this.multiply(arr1, l2 - i, num2.charAt(i) - '0');
            this.addToResult(arr);
        }
        if (result[0] == '0') {
            return new String(result, 1, result.length - 1);
        }
        return new String(result);
    }

    private void addToResult(char[] lArr) {
        // result默认作为短的。
        char[] sArr = this.result;
        int li = lArr.length - 1;
        int si = sArr.length - 1;

        // 进位
        boolean margin = false;

        char[] nArr = new char[li + 1];

        while (si > -1) {
            int n = sArr[si] + lArr[li] - '0';
            n = margin ? n + 1 : n;
            if (n > '9') {
                nArr[li] = (char) (n - 10);
                margin = true;
            } else {
                nArr[li] = (char) n;
                margin = false;
            }
            --li;
            --si;
        }
        while (li > -1) {
            int n = lArr[li];
            n = margin ? n + 1 : n;
            if (n > '9') {
                nArr[li] = (char) (n - 10);
                margin = true;
            } else {
                nArr[li] = (char) n;
                margin = false;
            }
            --li;
        }

        this.result = nArr;
    }

    /*
     * 单位数乘多位数
     */
    private char[] multiply(char[] arr, int n, int num) {
        char[] nArr = new char[arr.length + n];
        int end = nArr.length - 1;
        while (n > 1) {
            nArr[end] = '0';
            --end;
            --n;
        }

        // 进位
        int margin = 0;
        while (end > 0) {
            int x = arr[end - 1] - '0';
            x *= num;
            x += margin;

            nArr[end] = (char) (x % 10 + '0');
            margin = x / 10;
            --end;
        }
        nArr[0] = (char) (margin + '0');
        return nArr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String multiply = solution.multiply("9999999999", "0");
        System.out.println(multiply);
    }
}
