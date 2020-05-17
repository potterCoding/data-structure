package recursion;

/**
 * @author sun
 * @date 2020/3/28 15:50
 * @description 计算arr[l...n)这个区间内所有数字的和 -- 使用递归
 */
public class Sum {

    //计算arr[l...n)这个区间内所有数字的和 -- 使用递归
    private static int sum(int[] arr,int l){
        if (l == arr.length) return 0;
        return arr[l] + sum(arr,l+1);
    }

    public static int sum(int[] arr){
        return sum(arr, 0);
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(nums));
    }

}
