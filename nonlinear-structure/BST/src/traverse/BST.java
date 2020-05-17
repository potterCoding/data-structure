package traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author sun
 * @date 2020/3/30 16:10
 * @description 二分搜索树的非递归遍历
 */
public class BST<E extends Comparable<E>> {

    private TreeNode root;

    private int size;

    //前序遍历 -- 非递归实现
    public List<E> preOrderTraversal(TreeNode root) {
        List<E> res = new ArrayList<E>();
        if (root == null)
            return res;

        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        stack.push(root);

        while ( !stack.isEmpty() ){
            TreeNode<E> curr = stack.pop();
            res.add(curr.val);
            if (curr.right !=  null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
        return res;
    }



    //中序遍历 -- 非递归实现
    public List<E> inOrderTraversal(TreeNode root){
        List<E> res = new ArrayList<E>();

        if (root == null)
            return res;

        TreeNode<E> cur = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (cur != null || !stack.isEmpty()){
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }



    //后序遍历 -- 非递归算法
    public List<E> postOrderTraversal(TreeNode root) {
        List<E> res = new ArrayList<E>();

        if (root == null)
            return res;

        //借助栈结构实现二分搜索树的非递归后序遍历
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode<E> curr = root;
        TreeNode<E> pre = null;

        while (curr !=  null || !stack.isEmpty()) {
            if (curr != null){
                stack.push(curr);
                curr = curr.left;
            }else {
                curr = stack.pop();
                if (curr.right == null || curr.right == pre){
                    res.add(curr.val);
                    pre = curr;
                    curr = null;
                }else {
                    stack.push(curr);
                    curr =  curr.right;
                }

            }

        }
        return res;
    }

    //添加
    public void add(E e){
        root = add(root,e);
    }

    private TreeNode<E> add(TreeNode<E> node, E e) {
        if (node == null) {
            node = new TreeNode<E>(e);
            size ++;
            return node;
        }
        if (e.compareTo(node.val) < 0){
            node.left = add(node.left,e);
        }else if (e.compareTo(node.val) > 0){
            node.right = add(node.right,e);
        }
        return node;
    }


    public static void main(String[] args) {
        int[] nums = {10,9,28,39,27,0,18,21};
        BST<Integer> bst = new BST<Integer>();
        for (int num : nums) {
            bst.add(num);
        }
        List<Integer> preList = bst.preOrderTraversal(bst.root);
        System.out.println(preList);
        List<Integer> inList = bst.inOrderTraversal(bst.root);
        System.out.println(inList);
        List<Integer> postList = bst.postOrderTraversal(bst.root);
        System.out.println(postList);
        //10	9	0	28	27	18	21	39
        //0	    9	10	18	21	27	28	39
        //0	    9	21	18	27	39	28	10
    }
}
