public interface Set<E> {

    void add(E e); //添加元素e,不能添加重复元素
    boolean contains(E e); //当前集合中是否包含元素e
    void remove(E e); //删除元素e
    int getSize(); //获取当前集合中元素的个数
    boolean isEmpty(); //判断当前集合是否为空
}
