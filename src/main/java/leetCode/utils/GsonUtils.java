package leetCode.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;

public class GsonUtils {

    private static final Gson gson = new Gson();

    private static final Type intArrArrtype = new TypeToken<int[][]>() {
    }.getType();

    public static int[][] convertToIntArrArr(String json) {
        return convertToIntArrArr(json,true);
    }

    public static int[][] convertToIntArrArr(String json, boolean isPrint) {
        int[][] arr = gson.fromJson(json, intArrArrtype);
        if (isPrint) {
            System.out.println('{');
            for (int[] ints : arr) {
                System.out.println(Arrays.toString(ints));
            }
            System.out.println('}');
        }
        return arr;
    }

    public static void main(String[] args) {
        String json = "[[1,2,3],[2,3]]";
        int[][] ints = convertToIntArrArr(json);
        for (int[] anInt : ints) {

            System.out.println(Arrays.toString(anInt));
        }
    }
}
