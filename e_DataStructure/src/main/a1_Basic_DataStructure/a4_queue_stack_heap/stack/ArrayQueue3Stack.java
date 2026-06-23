package main.a1_Basic_DataStructure.a4_queue_stack_heap.stack;

import main.a1_Basic_DataStructure.a4_queue_stack_heap.queue.ArrayQueue3;

/**
 * ClassName: ArrayQueue3Stack
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.stack
 * Description:单队列实现栈
 *
 * @Author: zgh296
 * @Create: 2023/5/18 - 16:31
 * @Version: v1.0
 */
public class ArrayQueue3Stack {

    /*
    队列头     队列尾
    顶           底
    */
    ArrayQueue3<Integer> queue = new ArrayQueue3<>(100);
    int size = 0;
    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
        size++;
    }

    public int pop() {
        size--;
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

}
