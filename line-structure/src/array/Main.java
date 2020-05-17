package array;

/**
 * @author sun
 * @date 2020/3/23 23:19
 * @description
 */
public class Main {

    public static void main(String[] args) {
        /*array.Array arr = new array.Array(20);
        for(int i = 0 ; i < 10 ; i ++){
            //执行添加元素操作 --- 新增到元素末尾
            arr.addLast(i);
        }
        System.out.println(arr);// [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //将新元素插入到指定位置
        arr.add(1, 100);
        System.out.println(arr);// [0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //将新元素添加到第一个位置
        arr.addFirst(-1);
        System.out.println(arr);// [-1, 0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //删除指定位置的元素
        arr.remove(2);
        System.out.println(arr);// [-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        //删除指定元素
        arr.removeElement(4);
        System.out.println(arr);// [-1, 0, 1, 2, 3, 5, 6, 7, 8, 9]

        //删除第一个位置的元素
        arr.removeFirst();
        System.out.println(arr);// [0, 1, 2, 3, 5, 6, 7, 8, 9]*/

        Array<Integer> arr = new Array<>();
        for(int i = 0 ; i < 10 ; i ++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

    }

    public static int sum(int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        return sum;
    }

}
