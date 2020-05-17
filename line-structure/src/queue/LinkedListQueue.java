package queue;

/**
 * @author sun
 * @date 2020/3/28 14:38
 * @description
 */
public class LinkedListQueue<E> implements Queue<E> {

    //head指向头结点,tail指向下次添加元素的位置
    private Node head, tail;
    private int size;

    //不写也可以，和系统自动生成的无参构造器作用效果相同
    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    //入队 -- 只能从队尾添加元素
    @Override
    public void enqueue(E e) {
        if (tail == null){
            tail = new Node(e);
            head = tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++ ;
    }

    //出队操作 -- 只能从队首删除元素
    @Override
    public E dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        if (head == null) tail=null;
        size -- ;
        return delNode.e;
    }

    //获取队首的元素
    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node curr = head;
        while (curr != null){
            res.append(curr.e+"->");
            curr = curr.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }


    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
