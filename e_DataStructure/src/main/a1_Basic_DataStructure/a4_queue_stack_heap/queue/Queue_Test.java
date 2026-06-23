package main.a1_Basic_DataStructure.a4_queue_stack_heap.queue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: Queue_Test
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.queue
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/16 - 16:09
 * @Version: v1.0
 */
public class Queue_Test {

    // 链表队列
    @Test
    public void test() {

        LinkedListQueue<Integer> queue = new LinkedListQueue<>(5);
        assertNull(queue.peek());
        for (int i = 0; i < 5; i++) {
            queue.offer(i + 1);
        }
        assertIterableEquals(List.of(1,2,3,4,5), queue);
        assertEquals(1, queue.peek());
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        queue.offer(1);
        queue.offer(1);
        queue.offer(100); // 队列满了，无法添加
        for(Object o : queue) {
            System.out.print(o + " ");
        }

    }

    // 数组队列
    @Test
    public void test2() {

        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        assertNull(queue.peek());
        for (int i = 0; i < 5; i++) {
            queue.offer(i + 1);
        }
        assertIterableEquals(List.of(1,2,3,4,5), queue);
        assertEquals(1, queue.peek());
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        queue.offer(1);
        queue.offer(1);
        queue.offer(100); // 队列满了，无法添加
        for(Object o : queue) {
            System.out.print(o + " ");
        }

    }

    // 数组队列2
    @Test
    public void test3() {

        ArrayQueue2<Integer> queue = new ArrayQueue2<>(5);
        assertNull(queue.peek());
        for (int i = 0; i < 5; i++) {
            queue.offer(i + 1);
        }
        assertIterableEquals(List.of(1,2,3,4,5), queue);
        assertEquals(1, queue.peek());
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        queue.offer(1);
        queue.offer(1);
        queue.offer(100); // 队列满了，无法添加
        for(Object o : queue) {
            System.out.print(o + " ");
        }
        
    }

    // 数组队列3
    @Test
    public void test4() {

        ArrayQueue3<Integer> queue = new ArrayQueue3<>(8);
        assertNull(queue.peek());
        for (int i = 0; i < 5; i++) {
            queue.offer(i + 1);
        }
        assertIterableEquals(List.of(1,2,3,4,5), queue);
        assertEquals(1, queue.peek());
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        queue.offer(1);
        queue.offer(1);
        queue.offer(100);
        for(Object o : queue) {
            System.out.print(o + " ");
        }
        
    }

    // 二叉树层序遍历
    @Test
    public void test5() {

        // 根节点root及下方的各个节点
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(
            new BinaryTree.TreeNode(
                    new BinaryTree.TreeNode(4),
                    2,
                    new BinaryTree.TreeNode(5)
            ),
            1,
            new BinaryTree.TreeNode(
                    new BinaryTree.TreeNode(6),
                    3,
                    new BinaryTree.TreeNode(7)
            )
        );

        BinaryTree binaryTree = new BinaryTree();
        List list = binaryTree.levelOredr(root);
        System.out.println(list);

    }

}
