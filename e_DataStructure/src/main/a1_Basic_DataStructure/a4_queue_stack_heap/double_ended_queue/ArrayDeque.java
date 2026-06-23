package main.a1_Basic_DataStructure.a4_queue_stack_heap.double_ended_queue;

import main.a1_Basic_DataStructure.a4_queue_stack_heap.queue.BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: ArrayDeque
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.double_ended_queue
 * Description:循环数组实现双端队列
 *
 * @Author: zgh296
 * @Create: 2023/5/18 - 21:07
 * @Version: v1.0
 */
public class ArrayDeque<E> implements Deque<E>, Iterable<E> {

    /*
                    h
                t
        0   1   2   3
        a   b       c
        offerLast(a) 先添加元素，再tail++
        offerLast(b)
        offerFirst(c) 先head--，再添加元素
        pollFirst() 先获取移除的值，再head++
        pollLast() 先tail--， 再获取移除的值
        判空 head == tail
        判满 head - tail == array.length - 1
        停下来的位置不存储, 会浪费一个位置
     */

    E[] array;
    int head;
    int tail;

    public ArrayDeque(int capacity) {
        this.array = (E[]) new Object[capacity + 1];
    }

    /**
     * 私有方法，用于增删元素时头/尾指针的++移动
     * @param i 头/尾指针索引
     * @param length 数组长度
     * @return 增删头/后尾指针索引
     */
    private static int inc(int i, int length) {
        if(i + 1 >= length) {
            return 0;
        }
        return i + 1;
    }

    /**
     * 私有方法，用于增删元素时头/尾指针的--移动
     * @param i 头/尾指针索引
     * @param length 数组长度
     * @return 增删头/后尾指针索引
     */
    private static int dec(int i, int length) {
        if(i - 1 < 0) {
            return length - 1;
        }
        return i - 1;
    }

    @Override
    public boolean offerFirst(E e) {
        if(isFull()) {
            return false;
        }
        // 头插先head--，再插入
        head = dec(this.head, array.length);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(isFull()) {
            return false;
        }
        // 尾插先插入，再tail++
        array[tail] = e;
        tail = inc(this.tail, array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty()) {
            return null;
        }
        // 头删先获取值，再head++
        E value = array[head];
        // 若数组存储引用数据类型，因为有数组元素一直指向引用数据，所以系统不会自动回收，需要进行垃圾回收操作
        array[head] = null;
        head = inc(this.head, array.length);
        return value;
    }

    @Override
    public E pollLast() {
        if(isEmpty()) {
            return null;
        }
        // 尾删先tail--，再获取值
        tail = dec(this.tail,array.length);
        E value = array[tail];
        array[tail] = null; // help GC
        return value;
    }

    @Override
    public E peekFirst() {
        if(isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if(isEmpty()) {
            return null;
        }
        return array[dec(tail,array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        // 因为是循环数组，需要判断头尾指针前后位置，分情况讨论
        if(tail > head) {
            return tail - head == array.length - 1;
        } else if (tail < head) {
            return head - tail == 1;
        } else {
            return false;
        }
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
                p = inc(p, array.length);
                return value;
            }
        };
    }

    /**
     * 二叉树Z字层序遍历
     * @param root 二叉树
     * @return 集合数组存储二叉树
     */
    public static List<List<Integer>> zigzagLevelOrder(BinaryTree.TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 二叉树为空
        if (root == null) {
            return result;
        }
        LinkedList<BinaryTree.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        int c1 = 1;
        while (!queue.isEmpty()) {
            int c2 = 0;
            LinkedList<Integer> deque = new LinkedList<>(); // 保存每一层结果
            for (int i = 0; i < c1; i++) {
                BinaryTree.TreeNode n = queue.poll();
                if (leftToRight) {
                    deque.offerLast(n.value);
                } else {
                    deque.offerFirst(n.value);
                }
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            c1 = c2;
            leftToRight = !leftToRight;
            result.add(deque);
        }
        return result;
    }

}
