package main.a1_Basic_DataStructure.a4_queue_stack_heap.queue;

import java.util.Iterator;

/**
 * ClassName: ArrayQueue2
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.queue
 * Description:环形数组实现队列
 *
 * @Author: zgh296
 * @Create: 2023/5/16 - 20:39
 * @Version: v1.0
 */

public class ArrayQueue2<E> implements Queue<E>, Iterable<E> {

    private E[] array; // 数组
    private int head = 0; // 头指针
    private int tail = 0; // 尾指针
    private int size = 0; // 元素个数

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if(isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        size ++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        E value = array[head];
        array[head] = null; // help GC
        head = (head + 1) % array.length;
        size --;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return array[head];
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
        return new Iterator<>() {
            int p = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                count++;
                return value;
            }
        };
    }

}

