package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

import java.util.Iterator;

/**
 * ClassName: PriorityQueue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.priority_queue
 * Description:无序数组实现优先级队列
 *
 * @Author: zgh296
 * @Create: 2023/5/19 - 10:55
 * @Version: v1.0
 */
public class PriorityQueue<E extends Priority> implements Queue<E>, Iterable<E>{
    // E extends Priority：E是Priority的下限及本身，E类必须是Priority的子类或本身

    // 无序数组：按插入顺序排列元素，删除时按优先级弹出
    // 1. 入队保持顺序
    // 2. 出队前找到优先级最高的出队，相当于一次选择排序
    // 3.出队效率低O(n)，入队O(1)

    Priority[] array;
    int size;

    public PriorityQueue(int capacity) {
       array = new Priority[capacity];
    }

    /**
     * 顺序向数组插入元素
     * @param value 待插入值
     * @return 插入结果
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    /**
     * 返回优先级最高的索引值
     * @return 优先级最高的索引值
     */
    private int selectMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if(array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 删除数组一个元素
     * @param index 数组元素索引
     */
    private void remove(int index) {
        if(index < size - 1) {
            // 移动
            System.arraycopy(array, index + 1, array, index,size - 1 - index);
        }
        // 不移动
        array[--size] = null; // help GC
    }

    /**
     * 删除数组中优先级最高的元素
     * @return 删除元素
     */
    @Override
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        int max = selectMax();
        E ret = (E) array[max];
        remove(max);
        return ret;
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
        return (E) array[selectMax()];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

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
