package queue;

/**
 * @author sun
 * @date 2020/3/27 9:45
 * @description 队列
 */
public interface Queue<E> {

    /**
     * 入队
     * @param e 入队的元素
     */
    void enqueue(E  e);

    /**
     * 出队
     * @return 出队的元素
     */
    E dequeue();

    /**
     * 获取队首的元素
     * @return 队首的元素
     */
    E getFront();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 获取队列中的元素
     * @return
     */
    int getSize();
}
