package main.a1_Basic_DataStructure.a4_queue_stack_heap.blocking_queue;

/**
 * ClassName: BlockingQueue
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.blocking_queue
 * Description:阻塞队列接口
 *
 * @Author: zgh296
 * @Create: 2023/5/22 - 16:59
 * @Version: v1.0
 */
public interface BlockingQueue<E> {

    /**
     * 向队尾插入值
     * @param value 待插入值
     * @return 插入成功：true 插入失败：false
     */
    void offer(E value) throws InterruptedException;

    /**
     * 向队尾插入值
     * @param value 待插入值
     * @param timeout 最大等待时间
     * @return 插入成功：true 插入失败：false
     */
    boolean offer(E value, long timeout) throws InterruptedException;

    /**
     * 删除队列头的值
     * @return 若队列非空则删除头值并返回该值，否则返回null
     */
    E poll() throws InterruptedException;

    /**
     * 获取队列头值
     * @return 队列头值
     */
    E peek() throws InterruptedException;

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
