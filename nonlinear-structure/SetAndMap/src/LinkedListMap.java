import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 * @date 2020/4/4 15:54
 * @description
 */
public class LinkedListMap<K,V> implements Map<K,V> {

    //定义链表的节点
    private class Node{
        //存储Map的键值对
        public K key;
        public V value;
        //指向像下一个节点
        public Node next;

        //链表节点的有参构造
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //当用户在创建节点时，上传了key和value的初始值
        public Node(K key, V value){
            this(key, value, null);
        }

        //链表节点的无参构造
        public Node(){
            this(null, null, null);
        }

        //键和值的输出格式为： key:value
        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    //虚拟头节点（不存储数据）
    private Node dummyHead;
    private int size;

    //Map的无参构造
    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    //根据key所在的节点，Map中的key是唯一的
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        //若根据key找到了对应的节点，则该Map中含有该键
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value) {
        //添加之前，先查找该映射中key是否已经存在了
        Node node = getNode(key);
        if (node == null){
            dummyHead.next = new Node(key,value,dummyHead.next);
            size ++;
        }else {
            //如果该节点已存在，则覆盖值
            node.value =value;
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        //在进行修改操作时，如果该节点不存在，则抛出异常
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;

        while (prev.next != null){
            if (prev.next.key.equals(key)){
                break;
            }
            prev = prev.next;
        }
        //prev.next 就是需要删除的节点
        if (prev.next != null){
           Node delNode = prev.next;
           prev.next = delNode.next;
           delNode.next = null;
           size --;
           return delNode.value;
        }
        return null;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        List<String> words = new ArrayList<String>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            //《傲慢与偏见》这本书的总词数
            System.out.println("Total words: " + words.size());

            //让我们的映射中存储key,value分别表示单词和这个单词出现的次数
            Map<String, Integer> map = new LinkedListMap<String,Integer>();
            for (String word : words) {
                //如果当前应次数已经存在这个单词（键）了，就让我们对这个次数进行加一
                if (map.contains(word)){
                    map.set(word, map.get(word) + 1);
                } else {
                    //如果该单词是第一次出现，频次就设置为一
                    map.add(word, 1);
                }
            }
            //输出《傲慢与偏见》这本书不同单词的总数
            System.out.println("Total different words: " + map.getSize());
            //输出这本书中出“pride”这个单词的次数
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            //输出这本书中出“prejudice”这个单词的次数
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

}
