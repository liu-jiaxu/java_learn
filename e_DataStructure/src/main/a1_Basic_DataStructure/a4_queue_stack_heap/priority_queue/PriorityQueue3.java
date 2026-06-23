package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

import java.util.Iterator;

/**
 * ClassName: PriorityQueue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.priority_queue
 * Description:大顶堆实现优先级队列
 *
 * @Author: zgh296
 * @Create: 2023/5/19 - 10:55
 * @Version: v1.0
 */
public class PriorityQueue3<E extends Priority> implements Queue<E>, Iterable<E>{
    // E extends Priority：E是Priority的下限及本身，E类必须是Priority的子类或本身

    // 大顶堆中，任意节点 C 与它的父节点 P 符合 P.value >= C.value
    // 入队出队效率均为O(log_n)，但遍历随机

    Priority[] array;
    int size;

    public PriorityQueue3(int capacity) {
       array = new Priority[capacity];
    }

    /**
     * 向大顶堆插入元素
     * @param offered 待插入值
     * @return 插入结果
     */
    @Override
    public boolean offer(E offered) {
        if (isFull()) {
            return false;
        }
        /*
         * 如果从索引 0 开始存储节点数据
         *     节点 i 的父节点为 floor((i-1)/2)，当 i>0 时
         *     节点 i 的左子节点为 2i+1，右子节点为 2i+2，当然它们得 < size
         * 如果从索引 1 开始存储节点数据
         *     节点 i 的父节点为 floor(i/2)，当 i > 1 时
         *     节点 i 的左子节点为 2i，右子节点为 2i+1，同样得 < size
         */
        int child = size ++;
        int parent = (child - 1) / 2;
        while(child > 0 && offered.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
        return true;
    }

    /**
     * 交换两者位置
     * @param i 元素1
     * @param j 元素2
     */
    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 下潜操作
     * @param parent 父元素索引值(从堆顶开始)
     */
    private void shiftDown(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        // 找到父元素下两个子节点中优先级较大的索引
        int max = parent;
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        // 一但发现父节点优先级小于子节点，要交换两者位置，同时递归执行
        if (max != parent) {
            swap(max, parent);
            shiftDown(max);
        }
    }

    /**
     * 删除数组中优先级最高的元素(堆顶元素)
     * @return 删除元素
     */
    @Override
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        /*
            1. 交换堆顶和尾部元素，记录尾部元素出队
            2. 下潜
                从堆顶开始，将父元素与两个孩子较大者交换
                直到父元素大于两个孩子，或者没有孩子为止
         */
        swap(0, size - 1);
        Priority e = array[size - 1];
        array[size - 1] = null; // help GC
        size --;
        // 下潜
        shiftDown(0);
        return (E) e;
    }

    /**
     * 返回数组中优先级最高的元素
     * @return 数组中优先级最高的元素
     */
    @Override
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    // 由于是数组实现大顶堆，因此遍历无法按照任何元素位置或优先级顺序遍历
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return (E) array[index++];
            }
        };
    }

}
