import org.apache.commons.lang3.StringEscapeUtils;

import java.beans.IntrospectionException;
import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/28 14:10
 **/
public class Demo {

    public static void main(String[] args) throws IntrospectionException {
//        byte[] bytes = {71, 69, 84, 32, 47, 99, 111, 109, 109, 101, 110, 116, 47, 100, 101, 108, 105, 118, 101, 114, 121, 69, 118, 97, 108, 117, 97, 116, 101, 80, 111, 112, 63, 97, 112, 105, 95, 118, 101, 114, 115, 105, 111, 110, 61, 57, 46, 50, 50, 46, 51, 38, 97, 112, 112, 95, 99};
        byte[] bytes = {80, 79, 83, 84, 32, 47, 99, 111, 109, 109, 101, 110, 116, 45, 115, 101, 114, 118, 105, 99, 101, 47, 99, 108, 105, 101, 110, 116, 47, 80, 111, 112, 67, 111, 109, 109, 101, 110, 116, 47, 112, 111, 112, 84, 114, 105, 103, 103, 101, 114, 32, 72, 84, 84, 80, 47};
        String s = new String(bytes);
        System.out.println(s);
//        String wordsRegex = "aaa|bbb";
//        Pattern pattern = Pattern.compile(wordsRegex);
//        Matcher matcher = pattern.matcher(null);
//
//        pattern.matcher(null).replaceAll("<span style=\"color:red\">$0</span>");
//        System.out.println(matcher);

    }

}
