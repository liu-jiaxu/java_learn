package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

import org.junit.jupiter.api.Test;

/**
 * ClassName: PriorityQueue_Test
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.priority_queue
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/22 - 9:38
 * @Version: v1.0
 */
public class PriorityQueue_Test {

    // 无序数组
    @Test
    public void test() {

        PriorityQueue<Entry> priorityQueue = new PriorityQueue<>(5);
        priorityQueue.offer(new Entry("a1",1));
        priorityQueue.offer(new Entry("a2",2));
        priorityQueue.offer(new Entry("a3",0));
        priorityQueue.offer(new Entry("a4",5));
        priorityQueue.offer(new Entry("a5",4));
        priorityQueue.poll();
        for (Object o : priorityQueue) {
            System.out.println(o);
        }

    }

    // 有序数组
    @Test
    public void test2() {

        PriorityQueue2<Entry> priorityQueue = new PriorityQueue2<>(5);
        System.out.println(priorityQueue.offer(new Entry("a1",1)));
        priorityQueue.offer(new Entry("a2",2));
        priorityQueue.offer(new Entry("a3",0));
        priorityQueue.offer(new Entry("a4",5));
        priorityQueue.offer(new Entry("a5",4));
        priorityQueue.poll();
        for (Object o : priorityQueue) {
            System.out.println(o);
        }

    }

    // 堆
    @Test
    public void test3() {

        PriorityQueue3<Entry> priorityQueue = new PriorityQueue3<>(9);
        System.out.println(priorityQueue.offer(new Entry("23",1)));
        priorityQueue.offer(new Entry("16",2));
        priorityQueue.offer(new Entry("55",0));
        priorityQueue.offer(new Entry("43",5));
        priorityQueue.offer(new Entry("8",4));
        priorityQueue.offer(new Entry("48",3));
        priorityQueue.poll();
        for (Object o : priorityQueue) {
            System.out.println(o);
        }

    }

}
