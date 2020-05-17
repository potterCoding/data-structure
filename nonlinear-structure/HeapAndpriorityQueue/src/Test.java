import java.util.Random;

/**
 * @author sun
 * @date 2020/4/5 12:27
 * @description
 */
public class Test {

    public static void main(String[] args) {

        int n = 1000000;

        //测试自定义的最大堆
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            //向我们的最大堆中添加100万个随机整数
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            //从最大堆中取出堆顶的元素，放入arr数组中
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < n ; i ++)
            //如果arr数组的前一个元素的值小于后一个元素的值，则说明我们实现的二叉堆有问题
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");
    }

}
