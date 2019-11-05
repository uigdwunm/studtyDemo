package sort;

import explain.HashMap;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

class ddd {
    static int[] dd = {9, 5, 85, 22 ,14 ,68 ,78 ,873 ,75 ,23 ,1, 2};

    public int[] getDd() {
        return dd;
    }
}
/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/8/14 14:44
 **/
public class QuickSort {


    public static void main(String[] args) throws InterruptedException {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>() {
            public int a = 0;
            {
                int a = 0;
                a = 3;
                put(1, 1);
            }
        };

        int[] arr = {9, 5, 85, 22 ,14 ,68 ,78 ,873 ,75 ,23 ,1, 2};


        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.execute(new QuickSortTask(new ddd(), 0, arr.length - 1));
        System.out.println(forkJoinPool.getActiveThreadCount());
        System.out.println(forkJoinPool.getPoolSize());
        System.out.println(forkJoinPool.getRunningThreadCount());

        Thread.sleep(1000L);
        System.out.println(Thread.currentThread().getName() + "    " + Arrays.toString(ddd.dd));

    }


    private void sort(int[] arr) {
        int leftIndex = 0;
        int rigthIndex = arr.length -1;

        this.sort(arr, leftIndex, rigthIndex);
    }

    private void sort(int[] arr, int leftIndex, int rightIndex) {

        // 循环后基准点位置
        int daturmIndex = leftIndex + 1;
        // 从左到右依次和基准点比对，基准点默认左边第一个 arr[leftIndex]
        for (int tempIndex = daturmIndex; tempIndex <= rightIndex ; tempIndex++) {
            if (arr[leftIndex] > arr[tempIndex]) {
                if (daturmIndex != tempIndex) {

                    arr[daturmIndex] = arr[daturmIndex] ^ arr[tempIndex];
                    arr[tempIndex] = arr[daturmIndex] ^ arr[tempIndex];
                    arr[daturmIndex] = arr[daturmIndex] ^ arr[tempIndex];
                }
                daturmIndex++;
            }

        }

        // 基准点指针多走了一步
        daturmIndex--;

        int temp = arr[leftIndex];
        arr[leftIndex] = arr[daturmIndex];
        arr[daturmIndex] = temp;


        if (daturmIndex - leftIndex > 1) {
            this.sort(arr, leftIndex, daturmIndex - 1);
        }
        if (rightIndex - daturmIndex > 1) {
            this.sort(arr, daturmIndex + 1, rightIndex);
        }
    }

}

class QuickSortTask extends RecursiveAction {

    private ddd dd;
    private int leftIndex;
    private int rightIndex;

    QuickSortTask(ddd arr, int leftIndex, int rightIndex) {
        this.dd = arr;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }

    @Override
    protected void compute() {
        int[] arr = dd.getDd();

        // 循环后基准点位置
        int daturmIndex = leftIndex + 1;
        // 从左到右依次和基准点比对，基准点默认左边第一个 arr[leftIndex]
        for (int tempIndex = daturmIndex; tempIndex <= rightIndex ; tempIndex++) {
            if (arr[leftIndex] > arr[tempIndex]) {
                if (daturmIndex != tempIndex) {

                    arr[daturmIndex] = arr[daturmIndex] ^ arr[tempIndex];
                    arr[tempIndex] = arr[daturmIndex] ^ arr[tempIndex];
                    arr[daturmIndex] = arr[daturmIndex] ^ arr[tempIndex];
                }
                daturmIndex++;
            }

        }

        // 基准点指针多走了一步
        daturmIndex--;

        int temp = arr[leftIndex];
        arr[leftIndex] = arr[daturmIndex];
        arr[daturmIndex] = temp;

        System.out.println(Thread.currentThread().getName() + "***" + Arrays.toString(dd.getDd()));

        if (daturmIndex - leftIndex > 1) {
            QuickSortTask quickSortTask1 = new QuickSortTask(dd, leftIndex, daturmIndex - 1);
            quickSortTask1.fork();
        }
        if (rightIndex - daturmIndex > 1) {
            QuickSortTask quickSortTask2 = new QuickSortTask(dd, daturmIndex + 1, rightIndex);
            quickSortTask2.fork();
        }

    }
}
