package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

/**
 * ClassName: Priority
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.priority_queue
 * Description:优先级接口
 *
 * @Author: zgh296
 * @Create: 2023/5/19 - 11:02
 * @Version: v1.0
 */
public interface Priority {

    /**
     * 返回对象的优先级，约定数字越大优先级越高
     * @return 优先级
     */
    int priority();

}
