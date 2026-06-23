package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

/**
 * ClassName: Entry
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.priority_queue
 * Description:优先级接口实现类
 *
 * @Author: zgh296
 * @Create: 2023/5/22 - 9:36
 * @Version: v1.0
 */
public class Entry implements Priority{

    String value;
    int priority;

    public Entry(String value, int priority) {
        this.value =value;
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "value='" + value + '\'' +
                ", priority=" + priority +
                '}';
    }

}
