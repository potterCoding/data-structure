/**
 * @author sun
 * @date 2020/4/5 9:19
 * @description
 */
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
