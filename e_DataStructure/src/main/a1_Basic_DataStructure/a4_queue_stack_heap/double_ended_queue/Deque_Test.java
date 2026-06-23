package main.a1_Basic_DataStructure.a4_queue_stack_heap.double_ended_queue;

import main.a1_Basic_DataStructure.a4_queue_stack_heap.queue.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ClassName: Deque_Test
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.double_ended_queue
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/18 - 20:36
 * @Version: v1.0
 */
public class Deque_Test {

    // 双向环形链表双端队列测试
    @Test
    public void test() {

        LinkedListDeque<Integer> deque = new LinkedListDeque<>(20);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(5);
        deque.offerLast(4);
        deque.pollFirst();
        deque.pollLast();
        System.out.println(deque.peekLast());
        for (Object p : deque) {
            System.out.print(p + " ");
        }

    }

    // 循环数组双端队列测试
    @Test
    public void test2() {

        ArrayDeque<Integer> deque = new ArrayDeque<>(20);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(5);
        deque.offerLast(4);
        deque.pollFirst();
        deque.pollLast();
        System.out.println(deque.peekLast());
        for (Object p : deque) {
            System.out.print(p + " ");
        }
        System.out.println();

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
        List<List<Integer>> lists = ArrayDeque.zigzagLevelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

    }

}
