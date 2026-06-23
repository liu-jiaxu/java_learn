package main.a1_Basic_DataStructure.a4_queue_stack_heap.stack;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * ClassName: ArrayStack
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.stack
 * Description:数组实现栈
 *
 * @Author: zgh296
 * @Create: 2023/5/18 - 12:13
 * @Version: v1.0
 */
public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    // 使用数组右侧作为栈顶，增删后其它元素不用移动，性能更高

    private E[] array; // 数组
    private int top; // 指针

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E value) {
        if(isFull()) {
            return false;
        }
        array[top++] = value;
        return true;
    }

    @Override
    public E pop() {
        if(isEmpty()) {
            return null;
        }
        return array[--top];
    }

    @Override
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return array[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int p = top;
            @Override
            public boolean hasNext() {
                return p > 0;
            }

            @Override
            public E next() {
                return array[--p];
            }
        };
    }

    /**
     * 有效的括号<br>
     * 一个字符串中可能出现 `[]` `()` 和 `{}` 三种括号，判断该括号是否有效<br>
     * 有效的例子<br>
     * ()[]{}<br>
     * ([{}])<br>
     * ()
     * @param s 传入的字符串
     * @return 判断结果
     */
    public boolean isValid(String s) {
        /*
            思路
                1.遇到左括号, 把要配对的右括号放入栈顶
                2.遇到右括号, 若此时栈为空, 返回 false，否则把它与栈顶元素对比
                    若相等, 栈顶元素弹出, 继续对比下一组
                    若不等, 无效括号直接返回 false
                3.循环结束
                    若栈为空, 表示所有括号都配上对, 返回 true
                    若栈不为空, 表示右没配对的括号, 应返回 false
         */
        ArrayStack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (!stack.isEmpty() && c == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 后缀表达式求值<br>
     * 从左向右进行计算<br>
     * 不必考虑运算符优先级，即不用包含括号
     * @param tokens 正确的逆波兰表达式数组
     * @return 运算结果
     */
    public double evalRPN(String[] tokens) {
        // 使用java给定类，可以动态指定栈长度
        LinkedList<Double> stack = new LinkedList<>();
        for(String t : tokens) {
            switch(t) {
                // case新语法不用写break
                case "+" -> {
                    // 注意栈弹出的顺序
                    Double a = stack.pop(); // 先弹出a
                    Double b = stack.pop(); // 再弹出b
                    stack.push(b + a);
                }
                case "-" -> {
                    Double a = stack.pop();
                    Double b = stack.pop();
                    stack.push(b - a);
                }
                case "*" -> {
                    Double a = stack.pop();
                    Double b = stack.pop();
                    stack.push(b * a);
                }
                case "/" -> {
                    Double a = stack.pop();
                    Double b = stack.pop();
                    stack.push(b / a);
                }
                default -> { // 到此全是数字，直接压入栈中
                    stack.push(Double.parseDouble(t));
                }
            }
        }
        return stack.pop();
    }

    /**
     * 中缀表达式转后缀
     * @param exp 中缀表达式
     * @return 后缀表达式
     */
    public String infixToSuffix(String exp) {
        /*
            思路
                1. 遇到数字, 拼串
                2. 遇到 + - * /
                    - 优先级高于栈顶运算符 入栈
                    - 否则将栈中高级或平级运算符出栈拼串, 本运算符入栈
                3. 遍历完成, 栈中剩余运算符出栈拼串
                    - 先出栈,意味着优先运算
                4. 带 ()
                    - 左括号直接入栈
                    - 右括号要将栈中直至左括号为止的运算符出栈拼串
         */
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case '+', '-', '*', '/' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            while (!stack.isEmpty()
                                    && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            stack.push(c);
                        }
                    }
                }
                case '(' -> {
                    stack.push(c);
                }
                case ')' -> {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }
                default -> {
                    sb.append(c);
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 符号优先级判断
     * @param c 符号
     * @return 优先级
     */
    private int priority(char c) {
        return switch (c) {
            case '(' -> 0;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> throw new IllegalArgumentException("不合法字符:" + c);
        };
    }

}
