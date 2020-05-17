/**
 * @author sun
 * @date 2020/4/5 13:21
 * @description
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>  {

    private MaxHeap<E> maxHeap;

    //无参构造 -- 初始化最大堆
    public PriorityQueue() {
        this.maxHeap = new MaxHeap<E>();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    //出队是优先级高的先出去，这里是元素值越大，优先级越高
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
