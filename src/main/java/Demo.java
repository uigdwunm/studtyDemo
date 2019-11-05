import explain.HashMap;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/28 14:10
 **/
public class Demo {
    public final static BigDecimal LIMITED_LOAN_QUOTA = new BigDecimal("800000");
    public static void main(String[] args) throws IOException {

    }

    private void aa() {
    }










    static final char[] simpleArr = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '百', '千', '亿'};

    private String enConverthCh(int num) throws IOException {

// fileName = 全景图的name-枚举  +  序号

        String fileName = "";
        String filePath = "E:\\temp\\d.jpg";
        //创建不同的文件夹目录
        File file = new File(filePath);
        //判断文件夹是否存在
        if (!file.exists()) {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        InputStream inputStream = null;
        String contentLength = null;
        try {

            File file1 = new File("C:\\Users\\user\\Desktop\\IMG_0006.HEIC");
            inputStream = new FileInputStream(file1);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            // 获取response header值
            bos.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentLength;

    }
}
