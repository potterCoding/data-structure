public class TestBSTAdd {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        //前序遍历 -- 递归算法
        bst.preOrder();
        System.out.println();

        System.out.println(bst);
    }

}
