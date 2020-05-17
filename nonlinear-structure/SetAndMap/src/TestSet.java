import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 * @date 2020/4/3 16:07
 * @description
 */
public class TestSet {

    /**
     * 该集合对指定文件进行添加操作所需要花费的时间
     * @param set 集合
     * @param filename 文件名
     * @return
     */
    private static double testSet(Set<String> set, String filename){

        long startTime = System.nanoTime();

        System.out.println(filename);
        List<String> words = new ArrayList<String>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }
        long endTime = System.nanoTime();
        //将纳秒转为秒
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "pride-and-prejudice.txt";

        Set<String> bstSet = new BSTSet<String>();
        //基于二分搜索树实现的集合进行测试
        double time1 = testSet(bstSet, filename);
        System.out.println("BST Set: " + time1 + " s");

        System.out.println();

        Set<String> linkedListSet = new LinkedListSet<String>();
        //基于链表实现的集合进行测试
        double time2 = testSet(linkedListSet, filename);
        System.out.println("Linked List Set: " + time2 + " s");

    }

}
