package leetCode.utils;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode convert(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp = temp.next = new ListNode(arr[i]);
        }

        return head;
    }

    public void print() {
        System.out.print(val + ", ");
        if (next != null) {
            next.print();
        } else {
            System.out.println();
        }
    }
}
