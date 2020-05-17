package array;

/**
 * @author sun
 * @date 2020/3/23 22:34
 * @description
 */
public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity){
        //无法定义为new E[capacity] ,使因为jdk1.5之后才支持泛型的，由于历史版本遗留问题
        // 这里只能进行强转
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    //创建无参构造 --- 当用户未指定初始容量时，我们可以设置一个初始容量大小
    public Array(){
        this(10);
    }

    //创建一个获取容量大小的方法
    public int getCapacity(){
        return this.data.length;
    }

    //创建一个获取数组中元素个数的方法
    public int getSize() {
        return this.size;
    }

    //判断数组是否为空
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e){
//        //判断数组中是否还有空位置
//        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. array.Array is full.");
//        }
//        //因为size始终表示数组中元素为空的第一个索引
//        data[size] = e;
//        //添加元素后，需要改变size的值
//        size++;
        add(size,e);
    }

    //在所有元素前添加一个新元素 --- 将新元素添加到索引为0的位置
    public void addFirst(E e){
//        //判断数组中是否还有空位置
//        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. array.Array is full.");
//        }
//        //需要将所有元素向后移一个位置，然后把新元素加入到索引为0的位置
//        for(int i = size - 1; i >= 0 ; i --){
//            //向后赋值
//            data[i + 1] = data[i];
//        }
//        data[0] = e;
//        size ++;
        add(0,e);
    }

    // 在指定索引的位置插入一个新元素e
    public void add(int index, E e){

        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        if(size == data.length){
            //throw new IllegalArgumentException("Add failed. array.Array is full.");
            //所数组满了，则进行扩容操作
            this.resize(this.getCapacity() * 2);
        }

        for(int i = size - 1; i >= index ; i --){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    // 获取index索引位置的元素
    public E get(int index){
        //判断用户输入的索引值是否合法
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    //查找某个元素的索引
    public int find(E e) {
        for(int i = 0; i < this.size; ++i) {
            if (this.data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    //是否包含某个元素
    public boolean contains(E e) {
        for(int i = 0; i < this.size; ++i) {
            if (this.data[i] == e) {
                return true;
            }
        }
        return false;
    }

    //删除指定位置的元素
    public E remove(int index) {
        //索引合法性判断
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for(int i = index + 1 ; i < size ; i ++)
            data[i - 1] = data[i];
        size --;
        data[size] = null; // loitering objects != memory leak
        //当数组的元素个数为数组长度的一半时，进行缩容操作
        if(size == data.length / 2)
            resize(data.length / 2);
        return ret;
    }

    //删除第一个元素
    public E removeFirst() {
        E e = this.get(0);
        this.remove(0);
        return e;
    }

    //删除最后一个元素
    public void removeLast() {
        this.remove(this.size - 1);
    }

    //删除数组中的指定元素
    public void removeElement(E e) {
        //先查找指定元素所在的索引位置
        int index = this.find(e);
        //删除指定索引位置的元素
        this.remove(index);
    }


    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    //将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i=0;i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    //获取数组的第一个元素
    public E getFirst() {
        return this.get(0);
    }

    public static void main(String[] args) {
        Array arr = new Array(20);
        for(int i = 0 ; i < 10 ; i ++){
            //执行添加元素操作 --- 新增到元素末尾
            arr.addLast(i);
        }
        System.out.println(arr);// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //将新元素插入到指定位置
        arr.add(1, 100);
        System.out.println(arr);// [0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //将新元素添加到第一个位置
        arr.addFirst(-1);
        System.out.println(arr);// [-1, 0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //删除指定位置的元素
        arr.remove(2);
        System.out.println(arr);// [-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //删除指定元素
        arr.removeElement(4);
        System.out.println(arr);// [-1, 0, 1, 2, 3, 5, 6, 7, 8, 9]

        //删除第一个位置的元素
        arr.removeFirst();
        System.out.println(arr);// [0, 1, 2, 3, 5, 6, 7, 8, 9]
    }
}
