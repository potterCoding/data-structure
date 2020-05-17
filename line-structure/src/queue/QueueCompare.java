package queue;

import java.util.Random;

/**
 * @author sun
 * @date 2020/3/27 15:03
 * @description 比较数组队列和循环队列的性能
 */
public class QueueCompare {

    //测试队列进行入队和出队操作所花费的时间
    public static Long testQueue(Queue<Integer> queue,int num){
        //获取系统的当前时间 --- 毫秒
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        int i;
        for (i=0; i<num; i++){
            //在队列中添加一个整数范围内的随机数
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (i=0; i<num; i++){
            //出队操作
            queue.dequeue();
        }
        //获取进行入队和出队操作后的当前时间 --- 毫秒
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    public static void main(String[] args) {
        //比较10万个整数的入队和出队操作所花费的时间
        int num = 100000;
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        Long time1 = testQueue(arrayQueue, num);
        System.out.println("ArrayQueue, time: " + time1 + " ms");

        Queue<Integer> loopQueue = new LoopQueue<>();
        Long time2 = testQueue(loopQueue, num);
        System.out.println("loopQueue, time: " + time2 + " ms");

    }

}
