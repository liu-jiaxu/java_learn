package main.a1_Basic_DataStructure.a5_binarytree;

import java.util.Iterator;

/**
 * ClassName: LinkedListStack
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.stack
 * Description:链表实现栈
 *
 * @Author: zgh296
 * @Create: 2023/6/2 - 10:21
 * @Version: v1.0
 */
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    // 内部节点类
    private static class Node<E> {

        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

    }

    private int capacity; // 栈容量
    private int size; // 栈大小
    private Node<E> head = new Node<>(null,null); // 哨兵节点

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean push(E value) {
        if(isFull()) {
            return false;
        }
        head.next = new Node<>(value, head.next);
        size ++;
        return true;
    }

    @Override
    public E pop() {
        if(isEmpty()) {
            return null;
        }
        Node<E> p = head.next;
        head.next = p.next;
        size --;
        return p.value;
    }

    @Override
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        Node<E> p = head.next;
        return p.value;
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
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != null;
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
