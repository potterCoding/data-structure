import java.util.TreeMap;

/**
 * @author sun
 * @date 2020-04-18 8:23
 * @description
 */
public class Trie {

    //设计Trie的节点类
    private class Node{

        //判断是否是一个单词
        public boolean isWord;
        //每个节点有若干个指向下个节点的指针
        public TreeMap<Character,Node> next;

        //有参构造：对该节点进行初始化
        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        //无参构造：默认当前节点不是单词的结尾
        public Node(){
            this(false);
        }

    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    //向Trie中添加一个新的单词word
    public void add(String word){
        Node cur = root;
        for (int i = 0 ;i < word.length(); i++){
            //将这个新单词，拆成一个一个字符
            char c = word.charAt(i);
            //如果当前节点的若干个子节点中，没有存储当前字符的节点，则需要创建一个子节点，存储当前字符
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        //对添加的新单词遍历结束后，判断当前节点是否为单词的结尾,如果不是我们才对size加一，并且维护当前节点的isWord
        if (! cur.isWord){
            cur.isWord = true;
            size ++;
        }

    }

    //Tire的查询操作
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0;i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null ){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    //查询在Trie中是否有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }
}
