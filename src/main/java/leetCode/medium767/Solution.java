package leetCode.medium767;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public String reorganizeString(String S) {
        int len = S.length();
        int[] arr = new int[26];
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            int index = c - 'a';
            arr[index]++;
        }
        int half = len % 2 == 1 ? len / 2 + 1 : len / 2;

        for (int count : arr) {
            if (count > half) {
                return "";
            }
        }
        char[] result = new char[len];

        int ci = this.nextUsed(arr, 0);

        char cChar = (char)(ci + 'a');
        int cCount = arr[ci];

        for (int i = 0; i < len; i += 2) {
            if (cCount == 0) {
                ci = this.nextUsed(arr, ci + 1);
                cChar = (char)(ci + 'a');
                cCount = arr[ci];
            }

            result[i] = cChar;
            cCount--;
        }

        for (int i = 1; i < len; i += 2) {
            if (cCount == 0) {
                ci = this.nextUsed(arr, ci + 1);
                cChar = (char)(ci + 'a');
                cCount = arr[ci];
            }

            result[i] = cChar;
            cCount--;
        }
        return new String(result);
    }

    private int nextUsed(int[] arr, int ci) {
        while (ci < arr.length) {
            if (arr[ci] > 0) {
                break;
            }
        }
        return ci;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String vvvlo = solution.reorganizeString("vvvlo");
        System.out.println(vvvlo);
    }
}