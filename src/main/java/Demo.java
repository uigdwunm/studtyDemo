import explain.map.HashMap;
import org.openjdk.jol.info.ClassLayout;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/28 14:10
 **/
public class Demo {

    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();
        ArrayDeque<Object> de = new ArrayDeque<>();
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, 1);

        int h = Integer.MAX_VALUE;
        int hash =  h ^ (h >>> 16);
        System.out.println(h);
        System.out.println(Integer.toBinaryString(h));

        System.out.println(h >>> 16);
        System.out.println(Integer.toBinaryString(h >>> 16));
        System.out.println(hash);
        System.out.println(Integer.toBinaryString(hash));
//        System.out.println(1 << 30);

    }

    private static <T> void fill(T[] arr, Supplier<T> supplier) {
        for (int i = 0, len = arr.length; i < len; i++)
            arr[i] = supplier.get();
    }


    private static void alloc() {
        Set[] s = new Set[3];
        fill(s, HashSet::new);

        HashMap<Object, Object> map = new HashMap<>();
        Domo bbb = new Domo();
        bbb.setDdd(127);
        bbb.setCcc("fdddd;");
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
