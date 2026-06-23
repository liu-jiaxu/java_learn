package main.a1_Basic_DataStructure.a4_queue_stack_heap.heap;

import org.junit.jupiter.api.Test;

/**
 * ClassName: Heap_Test
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_heap.heap
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/23 - 10:52
 * @Version: v1.0
 */
public class Heap_Test {

    @Test
    public void test() {

        int[] heap = {1, 2, 3, 4, 5, 6, 7};
        MaxHeap maxHeap = new MaxHeap(heap);
        System.out.println(maxHeap);
        System.out.println(maxHeap.getSize());

        MaxHeap maxHeap2 = new MaxHeap(maxHeap.loop(maxHeap).heapToArray(maxHeap));
        System.out.println(maxHeap2);
        System.out.println(maxHeap2.getSize());
        System.out.println(maxHeap2.loop(maxHeap2));
        System.out.println(maxHeap2.getSize());

    }

    @Test
    public void test2() {

        int[] heap = {1, 2, 3, 4, 5, 6, 7};
        MinHeap minHeap = new MinHeap(heap);
        System.out.println(minHeap);
        System.out.println(minHeap.loop(minHeap));
        System.out.println(minHeap.findKthLargest(heap,4));

    }

}
