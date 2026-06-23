package main.a1_Basic_DataStructure.a4_queue_stack_heap.queue;

import java.util.Iterator;

/**
 * ClassName: LinkedListQueue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.queue
 * Description:用带哨兵的双向环形链表实现队列
 *
 * @Author: zgh296
 * @Create: 2023/5/16 - 15:57
 * @Version: v1.0
 */
public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {

    // 内部节点类
    private static class Node<E> {

        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

    }

    Node<E> head = new Node<>(null,null); // 头节点
    Node<E> tail = head; // 尾节点
    private int size = 0; // 大小
    private int capacity = Integer.MAX_VALUE; // 容量

    // 构造方法中的重复语句写在外面的代码块中
    {
        tail.next = head;
    }

    public LinkedListQueue() {}

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean offer(E value) {
        if(isFull()) {
            return false;
        }
        Node<E> added = new Node<>(value,head);
        tail.next = added;
        tail = added;
        size ++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        // 队列仅剩一个节点时，应考虑删除该节点后tail尾指针的指向问题
        if(first == tail) {
            tail = head;
        }
        size --;
        return first.value;
    }

    @Override
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size >= capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != head;
            }
            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

}
