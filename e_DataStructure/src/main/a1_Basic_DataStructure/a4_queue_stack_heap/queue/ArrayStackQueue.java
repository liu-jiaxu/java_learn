package main.a1_Basic_DataStructure.a4_queue_stack_heap.queue;

import main.a1_Basic_DataStructure.a4_queue_stack_heap.stack.ArrayStack;

/**
 * ClassName: ArrayStackQueue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.queue
 * Description:双栈实现队列
 *
 * @Author: zgh296
 * @Create: 2023/5/18 - 16:26
 * @Version: v1.0
 */
public class ArrayStackQueue {

    /*
        队列头      队列尾
        s1        s2
        顶   底   底   顶
     */
    ArrayStack<Integer> s1 = new ArrayStack<>(100);
    ArrayStack<Integer> s2 = new ArrayStack<>(100);

    public void push(int x) {
        s2.push(x);
    }

    public int pop() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }

    public int peek() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

}
