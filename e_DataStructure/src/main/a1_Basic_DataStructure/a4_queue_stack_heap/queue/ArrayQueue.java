package main.a1_Basic_DataStructure.a4_queue_stack_heap.queue;

import java.util.Iterator;

/**
 * ClassName: ArrayQueue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.queue
 * Description:环形数组实现队列
 *
 * @Author: zgh296
 * @Create: 2023/5/16 - 20:17
 * @Version: v1.0
 */
public class ArrayQueue<E> implements Queue<E>, Iterable<E> {

    /*
        好处
            1.对比普通数组，起点和终点更为自由，不用考虑数据移动
            2.“环”意味着不会存在【越界】问题
            3.数组性能更佳
            4.环形数组比较适合实现有界队列、RingBuffer 等
    */

    private E[] array; // 数组
    private int head = 0; // 头指针
    private int tail = 0; // 尾指针

    @SuppressWarnings("all")
    public ArrayQueue(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offer(E value) {
        if(isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        E value = array[head];
        array[head] = null; // help GC
        head = (head + 1) % array.length; // 取模数组长度是为了防止head超出数组索引长度，因为超出后要重新从0开始
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
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p != tail;
            }
            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }

}
