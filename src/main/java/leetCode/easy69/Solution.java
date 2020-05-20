package leetCode.easy69;

public class Solution {


    public int mySqrt(int x) {
        int s = 0;
        int e = x;

        while (s < e) {
            int m = s + (e - s) / 2 + 1;
            if (m * m > x) {
                e = m - 1;
            } else {
                s = m;
            }
        }
        return s;
    }

    public int mySqrt2(int x) {
        int s = 0;
        int e = x;

        while (s < e) {
            int m = (s + e) / 2 + 1;
            if (m < 0) {
                return m;
            }
            if (m * m > x) {
                e = m - 1;
            } else {
                s = m;
            }
        }
        return s;
    }


    static int a = 0;
    public static void main(String[] args) {

        System.out.println(tt());
        System.out.println(a);
    }

    private static int tt() {
        try {
            return a++;
        } finally {
            return a++;
        }
    }
}
