public class NumArray {

    //使用我们自己实现的线段树
    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0){
            Integer[] data = new Integer[nums.length];
            for (int i=0; i<nums.length; i++){
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data,(a, b) -> a+b);
        }
    }

    public void update(int index,int val){
        if (segmentTree == null){
            throw new IllegalArgumentException("segmentTree is null");
        }
        segmentTree.set(index,val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null){
            throw new IllegalArgumentException("segmentTree is null");
        }
        return segmentTree.query(i,j);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5};
        NumArray numArray = new NumArray(nums);
        int i = numArray.sumRange(0, 2);
        System.out.println(i);
        numArray.update(1, 2);
        int i1 = numArray.sumRange(0, 2);
        System.out.println(i1);
    }

//    private int[] sum;
//    private int[] data;
//
//    public NumArray(int[] nums) {
//
//        data = new int[nums.length];
//        for(int i = 0 ; i < nums.length ; i ++)
//            data[i] = nums[i];
//
//        sum = new int[nums.length + 1];
//        sum[0] = 0;
//        for(int i = 1 ; i < sum.length ; i ++)
//            sum[i] = sum[i - 1] + nums[i - 1];
//    }
//
//    //添加一个更新操作，每一次更新，我们都需要对sum数组中的值进行重置
//    public void update(int index,int val){
//        data[index] = val;
//        for(int i = index+1 ; i < sum.length ; i ++)
//            sum[i] = sum[i - 1] + data[i - 1];
//    }
//
//
//    public int sumRange(int i, int j) {
//        return sum[j + 1] - sum[i];
//    }

}
