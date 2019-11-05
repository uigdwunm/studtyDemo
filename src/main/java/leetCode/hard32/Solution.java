package leetCode.hard32;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/8/21 11:28
 **/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.longestValidParentheses("))(())()(");
        Arrays.asList();
        System.out.println(i);
    }

    private class Node {
        // 当前索引位置，仅作记录用
        private int index;
        // 到当前位置为止(包括)，最后一个有效的左边位置
        private int leftIndex;
        // 到当前位置为止(包括)，最后一个有效的右边位置
        private int rightIndex;
        // 向前无效终止位置
        private int shutdownIndex;

        private Node(int index, int leftIndex, int rightIndex, int shutdownIndex) {
            this.index = index;
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
            this.shutdownIndex = shutdownIndex;
        }
    }


    public int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }
        int maxLength = 0;
        char[] arr = s.toCharArray();
        Node[] nodeArr = new Node[arr.length];
        if (arr[0] == '(') {
            nodeArr[0] = new Node (0, -1, -1, 0);
        } else if (arr[0] == ')') {
            nodeArr[0] = new Node (0, -1, -1, -1);
        }

        for (int i = 1; i < arr.length; i++) {
            Node lastNode = nodeArr[i-1];
            switch (arr[i]) {
                case '(' :
                    if (lastNode.shutdownIndex == -1) {
                        nodeArr[i] = new Node(i, lastNode.leftIndex, lastNode.rightIndex, i);
                    } else {
                        nodeArr[i] = new Node(i, lastNode.leftIndex, lastNode.rightIndex, lastNode.shutdownIndex);
                    }
                    continue;
                case ')' :
                    if ((lastNode.shutdownIndex == lastNode.leftIndex && (i-1) == lastNode.rightIndex) || i == lastNode.shutdownIndex || lastNode.shutdownIndex == -1) {
                        // 需要更新终止位置的
                        nodeArr[i] = new Node(i, lastNode.leftIndex, lastNode.rightIndex, i + 1);
                    } else if (lastNode.shutdownIndex > lastNode.rightIndex || lastNode.leftIndex == -1) {
                        // 与前方彻底隔断的
                        nodeArr[i] = new Node(i, i-1, i, lastNode.shutdownIndex);
                        maxLength = Math.max(2, maxLength);
                    } else if (lastNode.rightIndex + 2 < i) {
                        // 未彻底隔绝，但还无法相连
                        nodeArr[i] = new Node(i, i-1, i, lastNode.shutdownIndex);
                        maxLength = Math.max(2, maxLength);
                    } else if (lastNode.shutdownIndex <= lastNode.leftIndex) {
                        // 先加长，在考虑相连的情况
                        if (lastNode.rightIndex + 2 == i) {
                            // 第一种加长
                            nodeArr[i] = new Node(i, lastNode.leftIndex, i, lastNode.shutdownIndex);
                        } else {
                            // 第二种加长
                            nodeArr[i] = new Node(i, lastNode.leftIndex - 1, i, lastNode.shutdownIndex);
                        }

                        // 是否相连
                        if (nodeArr[i].leftIndex != -1) {
                            Node tempNode = nodeArr[nodeArr[i].leftIndex];
                            if (tempNode.rightIndex != -1 && tempNode.rightIndex == nodeArr[i].leftIndex - 1) {
                                nodeArr[i].leftIndex = tempNode.leftIndex;
                            }
                        }

                        maxLength = Math.max(maxLength, nodeArr[i].rightIndex - nodeArr[i].leftIndex + 1);
                    }
            }
        }

        return maxLength;
    }
}
