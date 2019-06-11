package leetCode.medium3;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * Date 2019/6/10 13:13
 **/
public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        char[] charArray = s.toCharArray();

        int[] prevIndexDic = new int[256];
        for (int j = 0; j < 256; j++) {
            prevIndexDic[j] = -1;
        }
        prevIndexDic[charArray[0]] = 0;

        int resultLength = 0;
        int currentStartCharIndex = 0;

        int tempLength;
        for (int i = 1; i < charArray.length; i++) {
            if (prevIndexDic[charArray[i]] != -1) {
                int provIndex = prevIndexDic[charArray[i]];
                if (provIndex >= currentStartCharIndex) {
                    tempLength = i - currentStartCharIndex;
                    if (tempLength > resultLength) {
                        resultLength = tempLength;
                    }
                    currentStartCharIndex = provIndex + 1;
                }
            }
            prevIndexDic[charArray[i]] = i;
        }
        tempLength = charArray.length - currentStartCharIndex;
        if (tempLength > resultLength) {
            resultLength = tempLength;
        }

        return resultLength;


    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        char[] charArray = s.toCharArray();

        int[] prevIndexDic = new int[256];
        prevIndexDic[charArray[0]] = -1;

        int resultLength = 0;
        int currentStartCharIndex = 0;

        int tempLength;
        for (int i = 1; i < charArray.length; i++) {
            if (prevIndexDic[charArray[i]] != 0) {
                int provIndex = prevIndexDic[charArray[i]];
                provIndex = provIndex == -1 ? 0 : provIndex;
                if (provIndex >= currentStartCharIndex) {
                    tempLength = i - currentStartCharIndex;
                    if (tempLength > resultLength) {
                        resultLength = tempLength;
                    }
                    currentStartCharIndex = provIndex + 1;
                }
            }
            prevIndexDic[charArray[i]] = i;
        }
        tempLength = charArray.length - currentStartCharIndex;
        if (tempLength > resultLength) {
            resultLength = tempLength;
        }

        return resultLength;


    }
    public static void main(String[] args) {
        int abcabcbb = lengthOfLongestSubstring("abcdab");
        System.out.println(abcabcbb);
    }


    public int lengthOfLongestSubstring2(String s) {

        int[] prevIndexDic = new int[256];
        for (int j = 0; j < 256; j++) {
            prevIndexDic[j] = -1;
        }
        int maxLen = 0;

        int currentSubstringStartIndex = 0;
        int currentIndex = 0;

        for (; currentIndex < s.length(); currentIndex++) {
            char ch = s.charAt(currentIndex);
            if (prevIndexDic[ch] != -1) {
                int prevIndex = prevIndexDic[ch];
                if (prevIndex >= currentSubstringStartIndex) {
                    maxLen = Math.max(maxLen, currentIndex - currentSubstringStartIndex);
                    currentSubstringStartIndex = prevIndex + 1;
                }
            }
            prevIndexDic[ch] = currentIndex;

        }

        maxLen = Math.max(maxLen, currentIndex - currentSubstringStartIndex);


        return maxLen;

    }
}
