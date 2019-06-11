package leetCode.medium2;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/10 11:10
 **/
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = l1.val + l2.val;
        l1 = l1.next;
        l2 = l2.next;
        boolean isIncrease;
        ListNode result;
        if(temp > 9) {
            isIncrease = true;
            result = new ListNode(temp%10);
        } else {
            isIncrease = false;
            result = new ListNode(temp);
        }

        ListNode resultIndex = result;

        while(l1 != null || l2 != null) {
            int l1v = 0;
            int l2v = 0;
            if(l1 != null) {
                l1v = l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                l2v = l2.val;
                l2 = l2.next;
            }

            if (isIncrease) {
                temp = l1v + l2v + 1;
            } else {
                temp = l1v + l2v;
            }
            if(temp > 9) {
                isIncrease = true;
                resultIndex.next = new ListNode(temp%10);
            } else {
                isIncrease = false;
                resultIndex.next = new ListNode(temp);
            }
            resultIndex = resultIndex.next;
        }
        if(isIncrease) {
            resultIndex.next = new ListNode(1);
        }
        return result;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
