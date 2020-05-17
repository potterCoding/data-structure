import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 * @date 2020/4/3 15:57
 * @description
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    //基于二分搜索树实现集合
    private BST<E> bst;

    public BSTSet(){
        bst = new BST<E>();
    }

    //直接调用bst的添加方法
    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.removeElement(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        List<String> words1 = new ArrayList<String>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            //输出《傲慢与偏见》这本书中的总词数
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<String>();
            for (String word : words1) {
                //将《傲慢与偏见》这本书中的所有单词加如我们的基于二分搜索树实现的集合中
                set1.add(word);
            }
            //输出《傲慢与偏见》这本书中去重后的总词数
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();

        //测试《双城记》这本书
        System.out.println("A Tale of Two Cities");

        List<String> words2 = new ArrayList<String>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set2 = new BSTSet<String>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
