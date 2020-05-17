import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author sun
 * @date 2020/4/4 18:12
 * @description
 */
public class Solution02 {

    public int[] intersect(int[] nums1, int[] nums2) {

        //key-代表数组中的元素，value表示的是该元素出现的次数
        //这里我们使用的是Java类库提供的TreeMap，你也可以使用我们基于二分搜索树实现的Map来解决这个问题
        Map<Integer,Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums1) {
            if (map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        //用来存储包含重复元素的集合
        List<Integer> res = new ArrayList<Integer>();
        for(int num: nums2){
            if(map.containsKey(num)){
                res.add(num);
                map.put(num, map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }

        int[] ret = new int[res.size()];
        for(int i = 0 ; i < res.size() ; i ++)
            ret[i] = res.get(i);

        return ret;
    }

}
