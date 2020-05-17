package linked;

/**
 * @author sun
 * @date 2020/3/27 17:04
 * @description 使用虚拟头结点进行添加、修改、删除操作
 */
public class LinkedList02<E> {

    //设置虚拟头结点
    private Node dummyHead;
    private int size;

    public LinkedList02() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）
    public void add(int index,E e){
        //判断索引是否合法
        if (index<0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node prev = dummyHead;
        for (int i=0; i<index; i++){
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);
        size ++ ;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        this.add(0,e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        this.add(size,e);
    }

    // 获得链表的第index(0-based)个位置的元素
    public E get(int index){
        //这里爸index=size也排除是因为随后一个节点所指向的节点为空
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node curr = dummyHead.next;
        for (int i=0; i<index; i++){
            curr = curr.next;
        }
        return curr.e;
    }

    // 获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
       Node curr = dummyHead.next;
       while (curr != null){
           if (curr.e.equals(e)) return true;
           curr = curr.next;
       }
       return false;
    }

    // 修改链表的第index(0-based)个位置的元素为e
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node curr = dummyHead.next;
        for (int i=0; i<index; i++){
            curr = curr.next;
        }
        curr.e = e;
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("remove failed. Illegal index.");
        }

        Node prev = dummyHead;
        for (int i=0; i<index; i++){
            prev = prev.next;
        }

        //待删除的元素
        Node delNode = prev.next;
        prev.next = delNode.next;
        //方便GC机制回收
        delNode.next = null;
        size -- ;
        return delNode.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return this.remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return this.remove(size-1);
    }

    // 从链表中删除元素e
    public void removeElement(E e){
        Node prev = dummyHead;
        while ( prev.next != null ){
            //如果找到被删除的元素，就跳出循环
            if (prev.next.e.equals(e)) break;
            prev = prev.next;
        }

        if (prev.next != null){
           Node delNode = prev.next;
           prev.next = delNode.next;
           delNode.next = null;
           size -- ;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    //设置成为内部类，是为了对用户屏蔽链表的内部实现
    private class Node{
        //存储这个节点的数据
        public E e;
        //指向下一个节点的引用
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        //我们只需要输出这个节点的数据信息
        @Override
        public String toString() {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        LinkedList02<Integer> linkedList = new LinkedList02<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
