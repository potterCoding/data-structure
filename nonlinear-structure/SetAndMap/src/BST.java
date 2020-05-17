import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author sun
 * @date 2020/3/30 11:02
 * @description 二分搜索树
 */

/**
 * 由于二分搜索树中的元素必须具有可比较性，所以二分搜索树中存储的数据必须要实现Comparable接口
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    //一个树只有一个根节点
    private Node root;
    //节点的个数
    private int size;

    //无参构造，这里不写也可以，因为和系统默认的无参构造是相同的
    public BST() {
        this.root = null;
        this.size = 0;
    }

    //获取节点的个数
    public int getSize() {
        return size;
    }

    //判断树是否为空
    public Boolean isEmpty(){
        return this.size == 0;
    }

/*   添加方式一：
    //向二分搜索树中添加新元素e
    public void add(E e){
        if (root == null){
            root = new Node(e);
            size ++;
            return;
        }else {
            add(root,e);
        }
    }

    //向以node为根的二分搜索树中插入元素e，递归算法
    private void add(Node node, E e) {
        //二分搜索中不允许添加重复元素
        if (e.equals(node.e)){
            return;
        }
        if (e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size ++;
            return;
        }
        if (e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size ++;
            return;
        }

        if (e.compareTo(node.e) < 0){
            add(node.left,e);
        }else {
            add(node.right,e);
        }
    }
*/

    // 向二分搜索树中添加新元素e
    public void add(E e){
       root = add(root,e);
    }

    //向以node为根的二分搜索树中插入元素e，递归算法
    private Node add(Node node, E e) {
        if (node == null){
            node = new Node(e);
            size ++;
            return node;
        }
        if (e.compareTo(node.e) < 0){
           node.left = add(node.left,e);
        }else if (e.compareTo(node.e) > 0){
            node.right = add(node.right , e);
        }
        return node;
    }

    //看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node, E e) {
        //判断根节点是否为空
        if (node == null)
            return false;

        if (e.compareTo(node.e) == 0){
            return true;
        }else if (e.compareTo(node.e) < 0 ){
            return contains(node.left,e);
        }else { //(e.compareTo(node.e) > 0 )
            return contains(node.right,e);
        }
    }

    //二分搜索树的前序遍历 -- 递归算法
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.print(node.e +"\t");
        preOrder(node.left);
        preOrder(node.right);

    }

    //二分搜索树的中序遍历 -- 递归算法
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.print(node.e + "\t");
        inOrder(node.right);
    }

    //二分搜索树的后序遍历 --递归算法
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node) {
        if ( node== null )
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e+"\t");
    }

    //二分搜索树前序遍历的非递归算法 -- 使用栈
    public void preOrderByStack(){
        if (root == null)
            return;

        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        while ( !stack.isEmpty() ){
            Node delNode = stack.pop();
            System.out.print(delNode.e + "\t");

            if (delNode.right != null)
                stack.push(delNode.right);
            if (delNode.left != null)
                stack.push(delNode.left);
        }
    }

    //层序遍历
    public void levelOrder(){
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while ( !queue.isEmpty()){
            Node removeNode = queue.remove();
            System.out.print(removeNode.e+"\t");
            if (removeNode.left != null)
                queue.add(removeNode.left);
            if (removeNode.right != null)
                queue.add(removeNode.right);
        }
    }

    //寻找二分搜索数中的最小元素 -- 递归法
    public E minNum(){
        if (size == 0) // 也可以写为 root==null
            throw new IllegalArgumentException("BST is empty");
        Node minNode = minNum(root);
        return minNode.e;
    }

    //以node为根节点搜索最小元素所在的节点
    private Node minNum(Node node) {
        if (node.left == null)
            return node;

        return minNum(node.left);
    }

    //从二分搜索树中删除最小元素所在的节点，并返回最小元素的值
    public E removeMinNum(){
        //获取最小值
        E e = minNum();

        root = removeMinNum(root);

        return e;
    }

    //以node为根节点删除最小元素所在的节点
    private Node removeMinNum(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMinNum(node.left);
        return node;
    }

    //寻找二分搜索树中最大元素 -- 递归获取
    public E maxNum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        Node maxNode = maxNum(root);
        return maxNode.e;
    }

    //以node为根节点，获取二分搜索中最大值所在的节点
    private Node maxNum(Node node) {
        if (node.right == null)
            return node;

        return maxNum(node.right);
    }

    //删除二分搜索数中最大元素所在的节点，并返回该值
    public E removeMaxNum(){
        E e = maxNum();

        root = removeMaxNum(root);

        return e;
    }

    //删除以node为根节点的二分搜索中最大元素所在的节点
    private Node removeMaxNum(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMaxNum(node.right);
        return node;
    }

    //删除二分搜索树中的指定元素
    public void removeElement(E e){
        root = removeElement(root,e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node removeElement(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) <0 ){
            node.left = removeElement(node.left, e);
            return node;
        }else if (e.compareTo(node.e) >0 ){
            node.right = removeElement(node.right,e);
            return node;
        }else { //e.compareTo(node.e) == 0

            //待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            //待删除节点的右子树为空的情况
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size -- ;
                return leftNode;
            }

            //待删除节点的左右子树都不为空的情况
            // 待删除节点的后继：找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minNum(node.right);
            successor.right = removeMinNum(node.right);
            successor.left = node.left;
            node.right = node.left = null;
            return successor;
        }
    }


    //数的节点定义
    private class Node{
        //数据
        public E e;
        //左孩子
        public Node left;
        //右孩子
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        generateBSTString(root,0,builder);
        return builder.toString();
    }

    //生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder builder) {
        if(node == null){
            builder.append(generateDepthString(depth) + "null\n");
            return;
        }
        builder.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left,depth+1,builder);
        generateBSTString(node.right,depth+1,builder);
    }

    //添加深度标识符
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i=0; i<depth; i++){
            res.append("--");
        }
        return res.toString();
    }
}
