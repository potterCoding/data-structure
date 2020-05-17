package stack;

import array.Array;

/**
 * @author sun
 * @date 2020/3/25 19:38
 * @description
 */
public class ArrayStack<E> implements Stack<E> {

    //这里的Array是我们的自定义动态数组
    private Array<E> array;

    //有参构造器，为栈分配指定空间
    public ArrayStack(int capacity) {
        this.array = new Array(capacity);
    }

    //无参构造器，调用动态数组的无参构造进行赋值
    public ArrayStack(){
        this.array = new Array<>();
    }

    //获取栈的容量
    public int getCapacity() {
        return this.array.getCapacity();
    }

    @Override
    public int getSize() {
        //直接调用动态数组的getSize()
        return this.array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    @Override
    public void push(E e) {
        //向栈中添加元素，调用动态数组的向最后一个元素位置的添加方法
        this.array.addLast(e);
    }

    @Override
    public E pop() {
        //获取栈顶的元素，即动态数组的最后一个元素
        E e = this.array.get(array.getSize() - 1);
        //删除动态数组中最后一个元素
        this.array.removeLast();
        return e;
    }

    @Override
    public E peek() {
        return this.array.get(array.getSize() - 1);
    }

    //重写toString()
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i=0; i<this.getSize(); i++){
            res.append(this.array.get(i));
            if (i != this.getSize()-1 ){
                res.append(',');
            }
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack();

        for(int i = 0; i < 5; ++i) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

        System.out.println(stack.peek());
    }
}
