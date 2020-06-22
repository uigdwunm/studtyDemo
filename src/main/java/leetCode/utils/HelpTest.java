package leetCode.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.StringJoiner;

public class HelpTest {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String json = "[\"LRUCache\",\"put\",\"put\",\"put\",\"get\",\"put\",\"put\",\"get\",\"put\",\"put\",\"get\",\"put\",\"get\",\"get\",\"get\",\"put\",\"put\",\"get\",\"put\",\"get\"]";
        Type type = new TypeToken<String[]>() {}.getType();
        String[] nameArr = gson.fromJson(json, type);

        type = new TypeToken<Integer[][]>() {}.getType();
        json = "[[10],[7,28],[7,1],[8,15],[6],[10,27],[8,10],[8],[6,29],[1,9],[6],[10,7],[1],[2],[13],[8,30],[1,5],[1],[13,2],[12]]";
        Integer[][] argArr = gson.fromJson(json, type);

        String name = "cache";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nameArr.length; i++) {
            String prefix = name + '.' + nameArr[i] +  "(";
            String suffix = ")";

            StringJoiner joiner = new StringJoiner(",", prefix, suffix);
            for (Integer arg : argArr[i]) {
                joiner.add(arg.toString());
            }
            if (nameArr[i].equals("get")) {
                sb.append("System.out.println(");
                sb.append(joiner);
                sb.append(");");
            } else {
                sb.append(joiner.toString()).append(';');
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);


    }
}
