package linked;

/**
 * @author sun
 * @date 2020/3/28 15:11
 * @description
 */
public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
    }


    public ListNode(int[] arr) {
        if (arr != null && arr.length != 0) {
            this.val = arr[0];
            ListNode cur = this;

            for(int i = 1; i < arr.length; ++i) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }

        } else {
            throw new IllegalArgumentException("arr can not be empty");
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for(ListNode cur = this; cur != null; cur = cur.next) {
            s.append(cur.val + "->");
        }

        s.append("NULL");
        return s.toString();
    }
}
