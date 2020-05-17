import java.util.TreeMap;

/**
 * @author sun
 * @date 2020-04-18 9:43
 * @description
 */
public class WordDictionary {

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

    // 获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
        size = 0;
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        //递归匹配查找
        return match(root,word,0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length())
            return node.isWord;

        char c = word.charAt(index);
        if (c != '.'){
            if (node.next.get(c) == null)
                return false;
            return match(node.next.get(c),word,index+1);
        }
        else {
            //如果当前节点的的值为‘.’,则需要遍历当前节点的所有子节点
            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar),word,index+1)){
                    return true;
                }
            }
            return false;
        }
    }

}
