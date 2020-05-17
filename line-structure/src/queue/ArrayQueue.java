package queue;

import array.Array;

/**
 * @author sun
 * @date 2020/3/27 9:50
 * @description
 */
public class ArrayQueue<E> implements Queue<E>{

    private Array<E> array;


    //有参构造 --- 用户可以自定义队列的大小
    public ArrayQueue(int capacity) {
        this.array = new Array<E>(capacity);
    }

    //无参构造
    public ArrayQueue(){
        array = new Array<E>();
    }

    //入队操作 -- 相当于向动态数组末尾添加元素
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    //出队操作 --- 相当于删除动态数组的第一个元素
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    //获取队首的元素
    @Override
    public E getFront() {
        return array.getFirst();
    }

    //判断队列是否未空
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    //返回队列中元素的个数
    @Override
    public int getSize() {
        return array.getSize();
    }

    //获取队列的容量大小
    public int getCapacity(){
        return array.getCapacity();
    }

    //重写toString() --- 自定义输出格式
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: "); //队列
        builder.append("front["); //队首
        for (int i=0; i<array.getSize(); i++){
            builder.append(array.get(i));
            //如果不是最后一个元素，则在元素后面追加','
            if (i != array.getSize()-1 ){
                builder.append(",");
            }
        }
        builder.append("]trail"); //对位
        return builder.toString();
    }

    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            //当余数为2时，就从队列中删除一个元素
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}