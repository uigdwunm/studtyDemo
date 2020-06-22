import com.google.gson.internal.bind.util.ISO8601Utils;
import threadPool.MyThreadPoolExecutor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/5/23 13:59
 **/
public class test {
    public static void main(String[] args) {
        System.out.println((int)'A');
        System.out.println((int)'Z');
        System.out.println((int)'0');
        System.out.println((int)'9');
        System.out.println((int)'a');
        System.out.println((int)'z');
    }

    private static void sleep(TimeUnit timeUnit, int num) {
        try {
            timeUnit.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static String create(Map<String, Object> map) {
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String collect = treeMap.entrySet().stream().map(x -> x.getKey() + '=' + x.getValue()).collect(Collectors.joining("&"));
        return '?' + collect;
    }

    public static void sort(int[] ints, int l , int r){
        int left = l;
        int right = r;

        int base = ints[(l+r)/2];

        while(left <= right){

            while (ints[left] < base)
                left++;

            while (ints[right] > base)
                right--;

            if(left <= right){
                int tmp = ints[left];
                ints[left] = ints[right];
                ints[right] = tmp;
                left++;
                right--;
            }
        }
        // only one
        if(l < right){
            sort(ints, l, right);
        }
        if(left < r){
            sort(ints, left, r);
        }

    }
}
