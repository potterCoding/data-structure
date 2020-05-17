/**
 * 定义Map接口，由于Map是用来存储数据对的数据结构，所以定义时需要两个泛型
 * @param <K> 键的类型使用泛型代替
 * @param <V> 值的类型使用泛型代替
 */
public interface Map<K, V> {

    //添加一个数据对
    void add(K key, V value);
    //根据键来删除这个数据对，并且返回删除的值
    V remove(K key);
    //查找这个map中是否包含key
    boolean contains(K key);
    //通过键查找这个数据对的值
    V get(K key);
    //修改
    void set(K key, V newValue);
    //获取当前映射中数据对的个数
    int getSize();
    //判断当前映射是否为空
    boolean isEmpty();
}
