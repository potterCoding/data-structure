package queue;

/**
 * @author sun
 * @date 2020/3/27 13:18
 * @description
 */
public class LoopQueue<E> implements Queue<E> {

    //注意这里的data类型为E[]，并不是Array
    private E[] data;
    //队首元素的索引
    private int front;
    //队尾待添加元素的索引
    private int tail;
    //队列中元素的个数
    private int size;

    //有参构造
    public LoopQueue(int capacity){
        //这里要比用户分配的空间多1，是为了处理循环队列为空和已满的问题
        this.data = (E[])new Object[capacity+1];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    //无参构造
    public LoopQueue(){
        this(10);
    }

    //判断队列是否未空
    @Override
    public boolean isEmpty() {
        return this.front == this.tail;
    }

    //获取队列中元素的个数
    @Override
    public int getSize() {
        return this.size;
    }

    //获取队列的容量
    public int getCapacity() {
        //这这里需要减去1，因为这一个空间是辅助我们去实现循环队列入队出队操作的
        return this.data.length - 1;
    }

    //获取队首的元素
    @Override
    public E getFront() {
        //判断队列是否为空
        if (this.isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return this.data[this.front];
    }

    //入队操作
    @Override
    public void enqueue(E e) {
        //判断队列是否已满
        if ((this.tail+1) % this.data.length == this.front){
            //若满了，则对该队列进行扩容
            this.resize(getCapacity() * 2);
        }
        //将该元素加入队尾
        this.data[this.tail] = e;
        //修改tail的值 -- 需要考虑到tail的值是(data.length-1)时
        this.tail = (this.tail+1) % data.length;
        //修改size的值
        this.size ++ ;
    }

    //出队操作
    @Override
    public E dequeue() {
        //判断队列是否为空
        if (this.isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }else {
            //获取队首的元素
            E e = this.data[this.front];
            //修改front的值
            this.front = (this.front + 1) % this.data.length;
            //修改size的值
            --this.size;
            //为了节约空间，当队列中的元素为当前队列的1/4时，进行缩容
            //要保证空间缩容为原来的1/2时，容量不为0
            if (this.size == this.getCapacity() / 4 && this.getCapacity() / 2 != 0){
                this.resize(this.getCapacity()/2);
            }
            return e;
        }
    }

    //修改队列的空间大小
    private void resize(int capacity){
        //创建一个新数组
        E[] newData = (E[])(new Object[capacity+1]);
        //把原队列中的值放入新数组中
        for (int i=0; i<this.size; i++){
            //注意：要保持队列的数据结构特性,即需要保持数据的先进先出的数据格式
            //注意超出数组长度的情况 -- 通过对数组长度取余数来避免
            newData[i] = this.data[(i + this.front) % this.data.length ];
        }

        this.data = newData;
        this.front = 0;
        this.tail = this.size;
    }

    //重写toString() --- 自定义我们的输出格式
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        //获取当前 队列的元素个数和容量大小
        builder.append(String.format("Queue：size=%d, capacity=%d\n ",this.getSize(),this.getCapacity()));
        //队首
        builder.append("front [");
        //取余数是为了防止下标越界
        for (int i = this.front;i != this.tail;i = (i+1)%this.data.length){
            builder.append(this.data[i]);
            if ((i+1) % this.data.length != tail){
                builder.append(",");
            }
        }
        builder.append("] tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue();

        for(int i = 0; i < 10; ++i) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }
}
