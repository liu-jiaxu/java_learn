package main.a1_Basic_DataStructure.a5_binarytree;

/**
 * ClassName: Stack
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.stack
 * Description:栈接口
 *
 * @Author: zgh296
 * @Create: 2023/6/2 - 10:14
 * @Version: v1.0
 */
public interface Stack<E> {

    // 先入后出

    /**
     * 向栈顶压入元素
     * @param value 元素
     * @return 压入结果
     */
    boolean push(E value);

    /**
     * 从栈顶弹出元素
     * @return 弹出的元素 / null
     */
    E pop();

    /**
     * 返回栈顶元素，不弹出
     * @return 栈顶元素
     */
    E peek();

    /**
     * 判断栈是否为空
     * @return 空：true / 非空：false
     */
    boolean isEmpty();

    /**
     * 检查栈是否已满
     * @return 满：true / 未满：false
     */
    boolean isFull();

}
