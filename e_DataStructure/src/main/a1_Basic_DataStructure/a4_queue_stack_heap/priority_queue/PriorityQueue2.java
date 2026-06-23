package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

import java.util.Iterator;

/**
 * ClassName: PriorityQueue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.priority_queue
 * Description:有序数组实现优先级队列
 *
 * @Author: zgh296
 * @Create: 2023/5/19 - 10:55
 * @Version: v1.0
 */
public class PriorityQueue2<E extends Priority> implements Queue<E>, Iterable<E>{
    // E extends Priority：E是Priority的下限及本身，E类必须是Priority的子类或本身

    // 有序数组：按优先级排列元素，删除时弹出顶端元素
    // 1. 入队后排好序，优先级最高的排列在尾部
    // 2. 出队只需删除尾部元素即可
    // 3.入队效率低O(n)，出队O(1)

    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
       array = new Priority[capacity];
    }

    /**
     * 向数组指定优先级位置插入元素
     * @param value 待插入元素
     */
    private void insert(E value) {
        int index = size - 1;
        while(index >= 0 && array[index].priority() >= value.priority()) {
            array[index + 1] = array[index];
            index --;
        }
        array[index + 1] = value;
    }

    /**
     * 向数组插入元素，按优先级排序
     * @param value 待插入值
     * @return 插入结果
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        insert(value);
        size ++;
        return true;
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
        E value = (E) array[--size];
        array[size] = null; // help GC
        return value;
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
        return (E) array[size - 1];
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
