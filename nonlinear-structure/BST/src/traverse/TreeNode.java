package traverse;

/**
 * @author sun
 * @date 2020/3/30 16:11
 * @description
 */
public class TreeNode<E extends Comparable<E>> {

    E val;
    TreeNode left;
    TreeNode right;
    TreeNode(E x) { val = x; }

}
