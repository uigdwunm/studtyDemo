package leetCode.easy9;

import java.time.Duration;
import java.time.Instant;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/7/12 14:10
 **/
public class Solution {
    public static void main(String[] args) {

        int a = 65279;
        System.out.println((char)a + "aaaaaaa");
        Instant instant = Instant.ofEpochSecond(1567346100L);
        System.out.println(instant.toString());
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            // 负数直接返回false
            return false;
        }

        int tempNum = 0;
        int temp;


        temp = x % 10;
        x = x / 10;
        if(x > 0) {
            // 个位数是0且还有十位数以上则直接返回false
            if(temp == 0) return false;
        } else {
            // 只有一位数直接返回true
            return true;
        }

        tempNum = temp;

        while(x > 0) {
            temp = x % 10;

            if(tempNum == x || (tempNum = tempNum *10 + temp) == x) {
                return true;
            }
            x = x / 10;
        }

        return false;
    }





    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        int old = x;

        // 在循环外计算第一次
        int now = old % 10;
        if (now == 0) {
            return false;
        }
        old = old / 10;
        int result = now;

        if (old == 0 && old < result) {
            return false;
        }

        do {
            if (old == result) {
                // 比较一次
                return true;
            }

            now = old % 10;
            old = old/10;

            if (old == result && old != 0) {
                // 减一个数再比一次
                return true;
            }

            // 加数
            result = result * 10 + now;
            // 循环，判断当新数大于旧数时不需要再继续
        } while (old > 0 && result < old);

        return false;
    }

}
