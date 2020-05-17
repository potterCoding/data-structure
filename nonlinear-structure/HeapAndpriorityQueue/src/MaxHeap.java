/**
 * 基于二叉最大堆的性质：堆中某个节点的值总是不大于其父节点的值
 * @param <E> 二叉最大堆中的元素必须要具有可比较性
 */
public class MaxHeap<E extends Comparable<E>> {

    //使用的是我们的自定义动态数组
    private Array<E> data;

    //有参构造--用户指定堆的大小
    public MaxHeap(int capacity){
        data = new Array<E>(capacity);
    }

    //无参构造
    public MaxHeap(){
        //这里我们就使用动态数组的默认大小
        data = new Array<E>();
    }

    //返回堆中元素的个数
    public int getSize(){
        return data.getSize();
    }

    //返回一个布尔值，判断堆是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        //如果为根节点，由于根节点没有父亲节点，则抛出异常
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    //向堆中添加元素
    public void add(E e){
        //把元素存储到动态数组中
        data.addLast(e);
        //data.getSize()-1 是待添加元素在动态数组中的索引
        siftUp(data.getSize()-1);
    }

    private void siftUp(int index) {
        //比较当前元素的父亲节点 data.get(parent(index)的值与带添加元素的值大小
        //如果待添加元素的父亲节点的值小于带添加元素，则需要交换位置
        while (index>0 && data.get(parent(index)).compareTo(data.get(index)) <0 ){
            //在我们的动态数组中新增一个两个索引位置元素交换的方法
            data.swap(index,parent(index));
            //交换索引的值，进行下一轮循环比较
            index = parent(index);
        }
    }

    //取出堆中最大元素
    public E extractMax(){
        E res = findMax();
        //交换最大元素和堆中最后一个元素的位置
        data.swap(0,data.getSize()-1);
        //删除堆中最后一个元素
        data.removeLast();
        siftDown(0);
        return res;
    }

    private void siftDown(int index) {
        //即判断左孩子节点不为空
        while (leftChild(index) < data.getSize()){
            //获取左孩子节点的索引
            int childIndex = leftChild(index);
            //childIndex+1 即为右孩子的索引
            if (childIndex+1 < data.getSize() &&
                    //在右孩子不为空的情况下，比较右孩子和左孩子的大小
                    data.get(childIndex+1).compareTo(data.get(childIndex))> 0 ){
                childIndex ++;//即data[childIndex] 是 leftChild 和 rightChild 中的最大值
            }
            if (data.get(index).compareTo(data.get(childIndex)) >= 0){
                break;
            }
            data.swap(index,childIndex);
            index = childIndex;
        }
    }

    //二叉最大堆中最大的元素就是堆顶的元素，在数组中对应索引为零的元素
    public E findMax() {
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    //replace:将堆顶元素替换以后Sift Down
    public E replace(E e){
        E maxValue = findMax();
        data.set(0,e);
        siftDown(0);
        return maxValue;
    }

    //Heapify
    //从第一个非叶子节点进行下沉操作，直到根节点
    public MaxHeap(E[] arr){
        data = new Array<E>(arr);
        for (int i=parent(arr.length-1); i>=0; i--){
            siftDown(i);
        }
    }

}
