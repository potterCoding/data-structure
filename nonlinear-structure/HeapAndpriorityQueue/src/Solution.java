import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author sun
 * @date 2020/4/5 13:35
 * @description
 */
public class Solution {

    //定义我们优先队列的出队规则
    private class Freq implements Comparable<Freq>{
        //e代表元素 freq代表出现的频次
        public int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another){
            //频次高的先出队
            if(this.freq < another.freq)
                return 1;
            else if(this.freq > another.freq)
                return -1;
            else
                return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        //使用map来存储这个数，以及这个数出现的频次
        TreeMap<Integer,Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            //如果map中已经存在num，则对num的频次加一
            if (map.containsKey(num)){
                map.put(num,map.get(num) + 1);
            }else {
                map.put(num,1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<Freq>();
        for (Integer key : map.keySet()) {
            //若优先队列中的元素小于K
            if (pq.getSize() < k){
                pq.enqueue(new Freq(key,map.get(key)));
            }else if(map.get(key) > pq.getFront().freq){
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }

        List<Integer> res = new LinkedList<Integer>();
        while(!pq.isEmpty())
            res.add(pq.dequeue().e);
        return res;
    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        printList((new Solution()).topKFrequent(nums, k));
    }
}
