package main.a1_Basic_DataStructure.a4_queue_stack_heap.heap;

import java.util.Arrays;

/**
 * ClassName: MaxHeap
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_heap.heap
 * Description:小顶堆
 *
 * @Author: zgh296
 * @Create: 2023/5/23 - 10:07
 * @Version: v1.0
 */
public class MinHeap {

    // 弗洛伊德建堆算法
    // 1. 找到最后一个非叶子节点
    // 2. 从后向前，对每个节点执行下潜

    int[] array;
    int size;

    public MinHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    public MinHeap(int capacity) {
        this.array = new int[capacity];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    /**
     * 获取堆顶元素
     * @return 堆顶元素
     */
    public int peek() {
        if(size == 0) {
            throw new IndexOutOfBoundsException("堆为空！");
        }
        return array[0];
    }

    /**
     * 删除堆顶元素
     * @return 堆顶元素
     */
    public int poll() {
        if(size == 0) {
            throw new IndexOutOfBoundsException("堆为空！");
        }
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    /**
     * 删除指定索引处元素
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index) {
        if(size == 0) {
            throw new IndexOutOfBoundsException("堆为空！");
        }
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    /**
     * 替换堆顶元素
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 堆的尾部添加元素
     * @param offered 新元素
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    // 将 offered 元素上浮: 直至 offered 小于父元素或到堆顶
    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered < array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    // 建堆
    private void heapify() {
        // 如何找到最后这个非叶子节点  size / 2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    // 将 parent 索引处的元素下潜: 与两个孩子较大者交换, 直至没孩子或孩子没它大
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int min = parent; // 记录最大者
        if (left < size && array[left] < array[min]) {
            min = left;
        }
        if (right < size && array[right] < array[min]) {
            min = right;
        }
        if (min != parent) { // 找到了更大的孩子
            swap(min, parent);
            down(min);
        }
    }

    // 交换两个索引处的元素
    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * 堆排序
     * @param minHeap 小顶堆
     * @return 排序后的小顶堆
     */
    public MinHeap loop(MinHeap minHeap) {
        /*
            1. heapify 建立小顶堆
            2. 将堆顶与堆底交换（最大元素被交换到堆底），缩小并下潜调整堆
            3. 重复第二步直至堆里剩一个元素
         */
        while (minHeap.size > 1) {
            minHeap.swap(0, minHeap.size - 1);
            minHeap.size--;
            minHeap.down(0);
        }
        return minHeap;
    }

    /**
     * 小顶堆中第K大元素
     * @param numbers 数组
     * @param k 第k大元素
     * @return 元素k的值
     */
    public int findKthLargest(int[] numbers, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(numbers[i]);
        }
        for (int i = k; i < numbers.length; i++) {
            if(numbers[i] > heap.peek()){
                heap.replace(numbers[i]);
            }
        }
        return heap.peek();
    }

}
