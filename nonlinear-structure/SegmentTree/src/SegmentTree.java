/**
 * @author sun
 * @date 2020/4/11 9:03
 * @description 线段树
 */
public class SegmentTree<E> {

    private E[] data;

    private E[] tree;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        //合并的方法
        this.merger = merger;
        this.data = (E[])new Object[arr.length];
        for (int i=0; i<arr.length; i++){
            data[i] = arr[i];
        }
        //创建一个空间为4n的静态数组
        tree = (E[])new Object[arr.length*4];
        //递归构建一颗线段树
        buildSegmentTree(0,0,arr.length-1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        //如果用户传进来的数组中只有一个元素，则我们的线段树只有一个节点，且这一个节点中只有一个元素
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }
        //获取当前节点的左孩子索引
        int leftTreeIndex = leftChild(treeIndex);
        //获取当前节点的右孩子索引
        int rightTreeIndex = rightChild(treeIndex);

        //int mid = (l + r) / 2 该方式可能会导致整型溢出
        int mid = l + (r-l)/2;
        //递归构建左子树
        buildSegmentTree(leftTreeIndex,l,mid);
        //递归构建右子树
        buildSegmentTree(rightTreeIndex,mid+1,r);
        //合并左子树和右子树所代表的区间
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    //构造函数 -- 通过用户传递的数组构建一颗吸纳段数
//    public SegmentTree(E[] arr){
//        this.data = (E[])new Object[arr.length];
//        for (int i=0; i<arr.length; i++){
//            data[i] = arr[i];
//        }
//        //创建一个空间为4n的静态数组
//        tree = (E[])new Object[arr.length*4];
//    }

    //获取线段树中实际元素的个数
    public int getSize(){
        return data.length;
    }

    //查找元素
    public E  get(int index){
        if (index < 0 || index >= data.length ){
            throw new IllegalArgumentException("Index is Illegal");
        }
        return data[index];
    }

    //左孩子节点的索引
    public int leftChild(int index){
        return index*2 + 1;
    }

    //有孩子节点的索引
    public int rightChild(int index){
        return index*2 + 2;
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR){
        //判断索引的合法性
        if(queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");
        //递归查询
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l==queryL && r==queryR){
            return tree[treeIndex];
        }

        int mid = l + (r-l)/2 ;

        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid+1){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }else if (queryR <= mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex,mid+1, r,mid+1,queryR);
        return merger.merge(leftResult,rightResult);
    }

    //将index位置的值，修改为e
    public void set(int index,E val){
        if (index<0 || index >= data.length)
            throw new IllegalArgumentException("index is illegal");
        data[index] = val;
        //数组中对应的值修改后，需要维护线段树中节点的值
        set(0, 0, data.length-1, index, val);
    }

    //以treeIndex为根的线段树中更新index的值为val
    private void set(int treeIndex, int l, int r, int index, E val) {
        //递归终止条件
        if (l == r){
            tree[treeIndex] = val;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //被修改元素的索引大于中间点，则去右子树递归修改
        if (index >= mid+1){
            set(rightTreeIndex, mid+1, r, index, val);
        }else { //被修改元素的索引小于中间点，则去左子树递归修改
            set(leftTreeIndex, l, mid, index, val);
        }
        //合并左子树和右子树修改的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i=0; i<tree.length; i++){
            if (tree[i] == null){
                res.append("null");
            }else {
                res.append(tree[i]);
            }

            if (i != tree.length-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
//在这个过程中，我们不需要从头到尾遍历我们需要查询的区间，我么只需要从我们的线段树上，从根节点想下去找相应的子区间，最后把我们找到的这些子区间在全都组合起来就可以了。这个过程是和整棵树的高度相关的，而和需要查询区间的长度是没关的，而我们整棵树的高度是LogN级别的，所以我们查询操作也是LogN的
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }
}
