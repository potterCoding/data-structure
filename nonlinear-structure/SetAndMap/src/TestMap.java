import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 * @date 2020/4/4 17:32
 * @description
 */
public class TestMap {

    private static double testMap(Map<String, Integer> map, String filename){
        //获取当前系统的时间，单位时纳秒
        long startTime = System.nanoTime();

        System.out.println(filename);
        List<String> words = new ArrayList<String>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words){
                if(map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        long endTime = System.nanoTime();
        //将时间单位纳秒转为秒
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "pride-and-prejudice.txt";

        //基于二分搜索树实现的映射
        Map<String, Integer> bstMap = new BSTMap<String, Integer>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + " s");

        System.out.println();

        //基于链表实现的映射
        Map<String, Integer> linkedListMap = new LinkedListMap<String, Integer>();
        double time2 = testMap(linkedListMap, filename);
        System.out.println("Linked List Map: " + time2 + " s");

    }

}
