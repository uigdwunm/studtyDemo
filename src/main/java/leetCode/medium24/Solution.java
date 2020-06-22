package leetCode.medium24;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import leetCode.utils.ListNode;

import java.lang.reflect.Type;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = head.next;

        ListNode temp = head;
        head = head.next;
        temp.next = head.next;
        head.next = temp;

        head = temp;

        while (head.next != null && head.next.next != null) {
            temp = head.next;
            head.next = head.next.next;
            temp.next = head.next.next;
            head.next.next = temp;

            head = head.next.next;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Gson gson = new Gson();
        Type type = new TypeToken<int[]>() {}.getType();
        String json = "[]";
        int[] o = gson.fromJson(json, type);

        ListNode convert = ListNode.convert(o);
        ListNode listNode = solution.swapPairs(convert);
        listNode.print();
    }
}
