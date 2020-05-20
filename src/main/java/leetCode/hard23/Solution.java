package leetCode.hard23;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import leetCode.utils.ListNode;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Solution {
    ListNode[] lists;

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length < 1) {
            // 空值
            return null;
        }
        if (lists.length < 2) {
            // 只有一个值
            return lists[0];
        }

        // 结果的头节点存一下
        ListNode main;
        this.lists = lists;

        if (lists.length < 3) {
            // 只有两个值
            if (this.compare(lists[1], lists[0])) {
                this.swap(0, 1);
            }
            main = lists[0];
            this.listMerge(0, 1);
            return main;
        }


        // 先排个序，找到主链，保存起点
        this.sort(0, lists.length - 1);
        main = lists[0];

        while (lists[2] != null) {
            this.listMerge(0, 1, lists[2].val);
            this.sort(1);
        }

        // 最后两个节点排序
        this.listMerge(0, 1);
        return main;
    }

    // 两个位置的链合并，没有限制的
    private void listMerge(int a, int b) {
        while (lists[b] != null) {
            if (lists[a].next == null) {
                lists[a].next = lists[b];
//                lists[b] = null;
                return;
            }
            if (lists[a].next.val <= lists[b].val) {
                // 后移
                lists[a] = lists[a].next;
            } else {
                // 交换
                ListNode temp = lists[a].next;
                lists[a].next = lists[b];
                lists[b] = temp;
                // 后移
                lists[a] = lists[a].next;
            }
        }
    }

    // 其实就是固定的第0个和第1个链合并，最大不超过max
    private void listMerge(int a, int b, int max) {
        while (lists[b] != null && lists[b].val <= max) {
            if (lists[a].next == null) {
                lists[a].next = lists[b];
                lists[b] = null;
                return;
            }
            if (lists[a].next.val <= lists[b].val) {
                // 后移
                lists[a] = lists[a].next;
            } else {
                // 交换
                ListNode temp = lists[a].next;
                lists[a].next = lists[b];
                lists[b] = temp;
                // 后移
                lists[a] = lists[a].next;
            }
        }
    }

    // 在已经排好序的基础上，把指定索引位置向上冒泡到对应位置即可
    private void sort(int i) {
        while (i + 1 < lists.length && lists[i + 1] != null && this.compare(lists[i + 1], lists[i])) {
            this.swap(i, i + 1);
            i++;
        }
    }

    // 搞个快排，空的会挪到最后
    // e为数组中需要排序的结束位置，s是起始位置
    private void sort(int s, int e) {
        if (e - s < 1) {
            // 只剩一个数的情况，包括负数的情况，不必排序
            return;
        } else if (e - s < 2) {
            // 只剩两个数的情况
            if (this.compare(lists[e], lists[s])) {
                this.swap(s, e);
            }
            return;
        }

        ListNode standard = lists[s];
        // 以s为基准
        int l = s + 1;
        int r = e;
        while (l < r) {
            if (this.compare(lists[l], standard)) {
                l++;
            } else if (this.compare(standard, lists[r])) {
                r--;
            } else {
                this.swap(l, r);
                l++;
                r--;
            }
        }

        // 征用l作为基准点索引
        if (this.compare(lists[l], standard)) {
            // 交换基准点
            this.swap(l, s);
        } else {
            // 前一个作为基准点
            l = l - 1;
            this.swap(l, s);
        }

        this.sort(s, l - 1);
        this.sort(l + 1, e);
    }

    private boolean compare(ListNode o1, ListNode o2) {
        if (o2 == null) {
            return true;
        }
        if (o1 != null && o1.val <o2.val) {
            return true;
        }
        // 其他情况为false
        return false;
    }

    private void swap(int a, int b) {
        ListNode temp = lists[a];
        lists[a] = lists[b];
        lists[b] = temp;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        Type type = new TypeToken<int[][]>() {
        }.getType();
//        String json = "[" +
//                "[-7,0,1]," +
//                "[-5,1]," +
//                "[2]" +
//                "]";
        String json = "[" +
                "[-8,-7,-6,-3,-2,-2,0,3]," +
                "[-10,-6,-4,-4,-4,-2,-1,4]," +
                "[-10,-9,-8,-8,-6]," +
                "[-10,0,4]" +
                "]";
        int[][] arrs = gson.fromJson(json, type);
        ListNode[] lists = Stream.of(arrs).map(ListNode::convert).toArray(ListNode[]::new);
        Solution solution = new Solution();
        ListNode listNode = solution.mergeKLists(lists);
        while (listNode != null) {
            System.out.print(listNode.val + ", ");
            listNode = listNode.next;
        }
    }
}
