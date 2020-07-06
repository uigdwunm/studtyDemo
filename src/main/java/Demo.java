import com.sun.deploy.util.StringUtils;
import explain.HashMap;
import explain.LinkedHashMap;
import explain.Map;
import org.openjdk.jol.info.ClassLayout;
import threadPool.MyThreadPoolExecutor;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/28 14:10
 **/
public class Demo {


    public static void main(String[] args) {

        String s1 = new String("你" + "好") ;
        String s2 = s1.intern();
        String s3 = "你好";
        System.out.println(s2 == s3);
        new StringBuilder().toString();


    }

    private static void alloc() {
        Domo domo = new Domo();
        domo.setDdd(127);
        domo.setCcc("fddddddddddddddddddddddddddddddddddddddddddsadfasdf;");
//        domo.setDate(new Date());
    }


    private static void ttt(Object o) {
        synchronized (o) {
            ClassLayout classLayout = ClassLayout.parseInstance(o);
            System.out.println(classLayout.toPrintable());
            System.out.println("线程ttt");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("执行完成");
        }
    }

    private static void ddd(Object o) {
        synchronized (o) {
            System.out.println("线程ddd");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static long getMemory() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        return totalMemory - freeMemory;
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
