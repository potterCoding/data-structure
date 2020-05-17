import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sun
 * @date 2020/4/3 11:41
 * @description
 */
public class TestRemove {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<Integer>();
        Random random = new Random();

        int n = 1000;

        // 测试删除最小元素所在的节点
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        List<Integer> nums = new ArrayList<Integer>();
        while(!bst.isEmpty()){
            //从二分搜索树中删除最小元素所在的节点，并拿到该最小元素
            Integer minNum = bst.removeMinNum();
            //向我们的集合中添加该最小元素
            nums.add(minNum);
        }

        //我们的nums集合中存储的是二分搜索树中所有节点的值按从小到大顺序排序后的元素
        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            //如果该集合中前一个元素的值大于后一个元素的值，则不满足从小到大的排序规则，则抛出错异常
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // 测试删除最大元素所在的节点
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<Integer>();
        while(!bst.isEmpty()){
            //从二分搜索树中删除最大元素所在的节点，并拿到该最大元素
            Integer maxNum = bst.removeMaxNum();
            //向我们的集合中添加该最小元素
            nums.add(maxNum);
        }

        //我们的nums集合中存储的是二分搜索树中所有节点的值按从大到小倒序排序后的元素
        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            //如果该集合中前一个元素的值小于后一个元素的值，则不满足从大到小的排序规则，则抛出错异常
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");
    }

}
