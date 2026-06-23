package main.a1_Basic_DataStructure.a4_queue_stack_heap.double_ended_queue;

import java.util.Iterator;

/**
 * ClassName: LinkedListDeque
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.double_ended_queue
 * Description:双向环形链表实现双端队列
 *
 * @Author: zgh296
 * @Create: 2023/5/18 - 20:10
 * @Version: v1.0
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    // 内部节点类
    private static class Node<E> {

        Node<E> prev; // 上一个节点
        E value; // 元素
        Node<E> next; // 下一个节点

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

    }

    int capacity; // 容量
    int size; // 大小
    Node<E> sentinel = new Node<>(null,null,null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public boolean offerFirst(E e) {
        if(isFull()) {
            return false;
        }
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(a, e, b);
        a.next = added;
        b.prev = added;
        size ++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(isFull()) {
            return false;
        }
        Node<E> a = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> added = new Node<>(a, e, b);
        a.next = added;
        b.prev = added;
        size ++;
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()) {
            return null;
        }
        E value = sentinel.next.value;
        Node<E> a = sentinel;
        Node<E> b = sentinel.next.next;
        a.next = b;
        b.prev = a;
        size --;
        return value;
    }

    @Override
    public E pollLast() {
        if(isEmpty()) {
            return null;
        }
        E value = sentinel.prev.value;
        Node<E> a = sentinel.prev.prev;
        Node<E> b = sentinel;
        a.next = b;
        b.prev = a;
        size --;
        return value;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if(isEmpty()) {
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next;
            @Override
            public boolean hasNext() {
                return p != sentinel;
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
