package recursion;

import linked.ListNode;

/**
 * @author sun
 * @date 2020/3/28 16:03
 * @description
 */
public class Solution01 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode res = removeElements(head.next, val);
        if (head.val == val) {
            return res;
        }else {
            head.next = res;
            return head;
        }
    }

}
