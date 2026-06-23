package main.a1_Basic_DataStructure.a4_queue_stack_heap.double_ended_queue;

/**
 * ClassName: Deque
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.double_ended_queue
 * Description:双端队列接口
 *
 * @Author: zgh296
 * @Create: 2023/5/18 - 20:11
 * @Version: v1.0
 */
public interface Deque<E> {

    // Deque：Double Ended Queue

    /**
     * 头部插入
     * @param e 元素
     * @return 插入结果
     */
    boolean offerFirst(E e);

    /**
     * 尾部插入
     * @param e 元素
     * @return 插入结果
     */
    boolean offerLast(E e);

    /**
     * 头部删除
     * @return 删除元素
     */
    E pollFirst();

    /**
     * 尾部删除
     * @return 删除元素
     */
    E pollLast();

    /**
     * 头部值查询
     * @return 头部元素
     */
    E peekFirst();

    /**
     * 尾部值查询
     * @return 尾部元素
     */
    E peekLast();

    /**
     * 是否为空
     * @return 判断结果
     */
    boolean isEmpty();

    /**
     * 是否已满
     * @return 判断结果
     */
    boolean isFull();
}
