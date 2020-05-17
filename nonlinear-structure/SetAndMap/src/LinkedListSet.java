import java.util.ArrayList;
import java.util.List;

/**
 * @author sun
 * @date 2020/4/3 16:38
 * @description
 */
public class LinkedListSet<E> implements Set<E> {

    //基于链表实现
    private LinkedList<E> linkedList;

    public LinkedListSet(){
        linkedList = new LinkedList<E>();
    }

    //由于链表中的添加操作是可以添加重复元素的
    //所以这里向集合中添加元素时，需要先判断集合中是否有该元素
    @Override
    public void add(E e) {
        if ( !linkedList.contains(e))
            linkedList.addFirst(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        List<String> words1 = new ArrayList<String>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<String>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<String>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<String>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
