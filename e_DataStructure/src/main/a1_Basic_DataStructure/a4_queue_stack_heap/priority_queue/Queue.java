package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

/**
 * ClassName: Queue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.queue
 * Description:队列接口
 *
 * @Author: zgh296
 * @Create: 2023/5/16 - 15:53
 * @Version: v1.0
 */
public interface Queue<E>{

    // 先入先出

    /**
     * 向队尾插入值
     * @param value 待插入值
     * @return 插入成功：true 插入失败：false
     */
    boolean offer(E value);

    /**
     * 删除队列头的值
     * @return 若队列非空则删除头值并返回该值，否则返回null
     */
    E poll();

    /**
     * 获取队列头值
     * @return 队列头值
     */
    E peek();

    /**
     * 判断队列是否为空
     * @return 空：true / 非空：false
     */
    boolean isEmpty();

    /**
     * 检查队列是否已满
     * @return 满：true / 未满：false
     */
    boolean isFull();

}
