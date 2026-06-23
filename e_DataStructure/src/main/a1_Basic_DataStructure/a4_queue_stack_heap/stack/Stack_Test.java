package main.a1_Basic_DataStructure.a4_queue_stack_heap.stack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: Stack_Test
 * Package: main.a1_Basic_DataStructure.a4_stack_stack_pile.stack
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/17 - 10:36
 * @Version: v1.0
 */
public class Stack_Test {

    // 链表栈测试
    @Test
    public void test() {

        LinkedListStack<Integer> stack = new LinkedListStack<>(8);
        assertNull(stack.peek());
        for (int i = 0; i < 5; i++) {
            stack.push(i + 1);
        }
        assertIterableEquals(List.of(5,4,3,2,1), stack);
        assertEquals(5, stack.peek());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        stack.push(1);
        stack.push(1);
        stack.push(100);
        for(Object o : stack) {
            System.out.print(o + " ");
        }
        
    }

    // 数组栈测试
    @Test
    public void test2() {

        ArrayStack<Integer> stack = new ArrayStack<>(8);
        assertNull(stack.peek());
        for (int i = 0; i < 5; i++) {
            stack.push(i + 1);
        }
        assertIterableEquals(List.of(5,4,3,2,1), stack);
        assertEquals(5, stack.peek());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        stack.push(1);
        stack.push(1);
        stack.push(100);
        for(Object o : stack) {
            System.out.print(o + " ");
        }

    }

    // 数组栈其它方法测试
    @Test
    public void test3() {

        ArrayStack<Integer> stack = new ArrayStack<>(8);

        System.out.println("---------------------------------");
        System.out.println(stack.isValid("({[]})"));
        System.out.println(stack.isValid("{}()"));
        System.out.println(stack.isValid("[]"));
        System.out.println(stack.isValid("({]})"));
        System.out.println(stack.isValid("({)}"));
        System.out.println(stack.isValid("{{}()"));
        System.out.println(stack.isValid("}()"));

        System.out.println("---------------------------------");
        String[] token = {"4", "1.5", "2.54", "+", "/"};
        System.out.println(stack.evalRPN(token));

        System.out.println("---------------------------------");
        System.out.println(stack.infixToSuffix("1+2-3*4+(1+2)*6"));

    }

}
