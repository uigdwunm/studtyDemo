import com.google.gson.internal.bind.util.ISO8601Utils;
import threadPool.MyThreadPoolExecutor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/5/23 13:59
 **/
public class test {
    public static void main(String[] args) {

        System.out.println(97 ^ 98);
        System.out.println(99 ^ 98);
        System.out.println(99 ^ 100);
        System.out.println(101 ^ 100);
    }

    private static void sleep(TimeUnit timeUnit, int num) {
        try {
            timeUnit.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
