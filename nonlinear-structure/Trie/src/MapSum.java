import java.util.TreeMap;

public class MapSum {

    //设计节点类
    private class Node{
        //单词的权重值
        public int value;
        //每个节点都可能有若干个子节点
        public TreeMap<Character,Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    public MapSum(){
        root = new Node();
    }

    //添加操作和我们实现的字典树中的添加操作类型
    public void insert(String word,int val){
        Node cur = root;

        for (int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    //求前缀为prefix的权重和
    public int sum(String prefix){
        Node cur = root;
        for (int i = 0 ; i < prefix.length() ; i++){
            char c = prefix.charAt(i);
            if ( cur.next.get(c) == null ){
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node node) {
        int res = node.value;
        for (char c : node.next.keySet()) {
            res += sum(node.next.get(c));
        }
        return res;
    }


}
