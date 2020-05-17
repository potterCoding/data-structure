package linked;

import javax.xml.soap.Node;

/**
 * @author sun
 * @date 2020/3/28 15:11
 * @description
 */
public class Solution {

    //leetcode上的题：删除给定链表中和指定元素相等的所有元素
    public ListNode removeElements(ListNode head, int val) {
        //当给定的链表为空时，直接返回
        if (head == null) {
            return head;
        }else {
            ListNode prev;
            //当头节点的值和val相等时 --- 相当于删除头结点
            while (head != null && head.val == val){
                prev = head;
                head = head.next;
                prev.next = null;
            }
            prev = head;

            while (prev.next != null){
                if (prev.next.val == val){
                    ListNode delNode = prev.next;
                    prev.next = delNode.next;
                    delNode.next = null;
                }else {
                    prev = prev.next;
                }
            }
        }
        return head;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = (new Solution()).removeElements(head, 6);
        System.out.println(res);
    }

}
