package leetCode.hard25;

import leetCode.utils.ListNode;

import java.util.List;

public class Solution {
    ListNode curr = null;

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }

        ListNode result;
        ListNode lastTail = head;
        head = this.reverse(head, k);
        if (head == null) {
            return curr;
        } else {
            result = head;
        }

        lastTail.next = curr;
        while (curr != null) {
            ListNode tail = curr;

            head = this.reverse(curr, k);
            if (head == null) {
                lastTail.next = tail;
            } else {
                lastTail.next = head;
                lastTail = tail;
            }
        }
        return result;

    }

    // 返回的是新的头节点
    // 也会改变curr为链表反转组后的下一个节点
    private ListNode reverse(ListNode node, int k) {
        ListNode head = null;
        ListNode temp;
        for (int i = k; i != 0; i--) {
            if (node == null) {
                // 再反转回来
                this.reverse(head, k - i);
                return null;
            }
            temp = head;
            head = node;
            node = node.next;
            head.next = temp;
        }
        this.curr = node;
        return head;
    }

    public static void main(String[] args) {
        ListNode node = ListNode.convert(new int[]{1, 2, 3});
        Solution solution = new Solution();
        ListNode listNode = solution.reverseKGroup(node, 3);
        listNode.print();
    }
}
