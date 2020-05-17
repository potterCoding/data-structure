package stack;

import linked.LinkedList02;
import stack.Stack;

/**
 * @author sun
 * @date 2020/3/28 14:29
 * @description
 */
public class LinkedStack<E> implements Stack<E> {

    private LinkedList02<E> linkedList = new LinkedList02<>();

    @Override
    public int getSize() {
        return this.linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        this.linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return this.linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return this.linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(this.linkedList);
        return res.toString();
    }
}
