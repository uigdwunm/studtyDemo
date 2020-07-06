package leetCode.medium378;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public int kthSmallest(int[][] matrix, int k) {
        if (k == 1) {
            return matrix[0][0];
        }
        // 边界
        int border = Math.min(matrix.length, k);

        int[] temp = this.sort(matrix[0], matrix[1], k);

        if (k == 2) {
            return temp[k - 1];
        }

        for (int i = 2; i < border; i++) {
            temp = this.sort(temp, matrix[i], k);
        }
        return temp[k - 1];
    }

    // 归并的方式，选出前k个
    private int[] sort(int[] a, int[] b, int k) {
        int length = Math.min(a.length + b.length, k);
        int[] temp = new int[length];

        int ai = 0;
        int bi = 0;
        for (int i = 0; i < length; i++) {
            if (ai == a.length) {
                // a到头了
                temp[i] = b[bi++];
                continue;
            }
            if (bi == b.length) {
                // b到头了
                temp[i] = a[ai++];
                continue;
            }

            if (a[ai] > b[bi]) {
                temp[i] = b[bi++];
            } else {
                temp[i] = a[ai++];
            }
        }

        return temp;

    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(5, (o1, o2) -> Integer.compare(o2, o1));
        pq.offer(2);
        pq.offer(0);
        pq.offer(8);
        pq.offer(3);
        pq.offer(1);
//        pq.offer(7);

//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq);
        System.out.println();
        Solution solution = new Solution();
        Gson gson = new Gson();
        String json = "[" +
                "[1,1,3,8,13]," +
                "[4,4,4,8,18]," +
                "[9,14,18,19,20]," +
                "[14,19,23,25,25]," +
                "[18,21,26,28,29]]";
        Type type = new TypeToken<int[][]>() {
        }.getType();
        int[][] matrix = gson.fromJson(json, type);
        int i = solution.kthSmallest(matrix, 13);
        System.out.println(i);

    }
}
