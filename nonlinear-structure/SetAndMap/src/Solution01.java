import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Solution01 {

    public int[] intersection(int[] nums1, int[] nums2) {

        //这里是使用的Java类库中的TreeSet,我们也可以使用我们自己基于二分搜索树是实现的Set
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int num : nums1) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums2) {
            if (set.contains(num)){
                list.add(num);
                //从set集合删除该元素，为了避免下次运到该元素进行重复添加
                set.remove(num);
            }
        }
        //返回值需要一个数组
        int[] res = new int[list.size()];
        for (int i=0; i<res.length; i++){
            res[i] = list.get(i);
        }
        return res;
    }

}
