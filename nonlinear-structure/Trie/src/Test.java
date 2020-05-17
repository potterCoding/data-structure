import java.util.ArrayList;
import java.util.List;

public class Test {

    //每个节点有26个字母指向下个节点的指针，考虑不同的语言，不同的情境，比如现在这个26个字符是没有包含大写字母的，如果需要包含大写字目母，则需要让每个节点52个指向下个节点的指针，如果现在要加入邮箱呢？所以这里描述为每个节点有若干个指向下个节点的指针。
    //由于很多单词可能是另外一个单词的前缀，比如pan就是panda的前缀，那么再Trie中如何存储呢？所以我们应该对节点添加一个标识符，判断该节点是否是某个单词的结尾，某一个单词的结尾只靠叶子节点是不能区别出来的，因此我们再设计Node节点时，应该添加一个IsWords，判断该节点是否是单词的结尾。

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        List<String> words = new ArrayList<>();

        if(FileOperation.readFile("pride-and-prejudice.txt", words)){
//            Collections.sort(words);

            long startTime = System.nanoTime();

            //使用基于二分搜索树实现的集合进行添加和查询操作
            BSTSet<String> set = new BSTSet<>();
            for(String word: words)
                set.add(word);

            for(String word: words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            //基于二分搜索树实现的集合进行添加和查询操作所花费的时间
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // --- 测试通过Trie通过添加和查询所需要的时间

            startTime = System.nanoTime();

            Trie trie = new Trie();
            for(String word: words)
                trie.add(word);

            for(String word: words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }

    }

}
