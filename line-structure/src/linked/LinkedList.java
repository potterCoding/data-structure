package linked;

/**
 * @author sun
 * @date 2020/3/27 15:40
 * @description 最基本的动态数据结构---链表
 */
public class LinkedList<E> {

    //链表的头结点
    private Node head;
    //链表的长度
    private int size;

    //以为链表是动态的数据结构，所以不需要分配容量
    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    // 获取链表中元素的个数
    public int getSize(){
        return this.size;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表头添加新元素
    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
        //上面三行代码可以合并成一行代码
        head = new Node(e, head);
        size ++;
    }

    // 在链表的index(0-based)位置添加新元素e
    // 在链表中index并不是一个常用的操作，因为链表不支持随机访问
    public void add(int index,E  e){
        //判断index是否合法
        //注意：与数组不同的是，链表这里的index是可以等于size的，此时表示在链表末尾添加元素
        if (index <0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        //判断是否是向头节点中添加元素
        if (index == 0){
            this.addFirst(e);
        }else {
            Node prev = head;
            for (int i = 0;i< index-1 ;i++){
                prev = prev.next;
            }
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
            //上面三行代码可以合并成一行代码
            prev.next = new Node(e,prev.next);
            size ++ ;
        }
    }

    //在链表末尾添加元素
    public void addLast(E e){
        this.add(size,e);
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
}
