package main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist;

import java.util.*;
import java.util.function.Consumer;

/**
 * ClassName: DoubleLinkedListSentinel
 * Package: main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist
 * Description:双向环形链表(带哨兵)及其操作
 *
 * @Author: zgh296
 * @Create: 2023/5/8 - 10:54
 * @Version: v1.0
 */
public class DoubleCircularlyLinkedListSentinel implements Iterable{

    // 不想再添加额外功能了，现在看着链表就烦...
    // 双向环形链表(带哨兵)：每个节点除元素外还有前指针、后指针，且链表开始有一个哨兵节点，该哨兵节点既作为头，也作为尾

    private Node sentinel = new Node(null,296,null); // 哨兵


    // 无参构造函数付初值并确定指针指向
    public DoubleCircularlyLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    // 内部节点类
    private static class Node {

        Node prev; // 上一个节点
        int value; // 元素
        Node next; // 下一个节点

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

    }

    /**
     * 内部调用方法，查找索引对应指针，用户调用无实际意义，权限改为私有
     * @param index 查找的索引
     * @return 索引对应的指针
     */
    private Node findNode(int index) {

        // 因为有哨兵节点，因此索引从-1开始
        int i = -1;
        // 循环从哨兵开始，当节点的下一个指针又指向哨兵时，表示循环进行了一次
        for(Node p = sentinel; p.next != sentinel; p = p.next,i++) {
            if(i == index) {
                return p;
            }
        }
        return null;
    }

    /**
     * 内部调用方法，查找索引对应指针，用户调用无实际意义，权限改为私有
     * @param value 查找的元素
     * @return 索引对应的指针
     */
    private Node findNodeByValue(int value) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 内部私有重写异常方法，用于索引越界时抛出异常
     * @param index 传入索引
     * @return 索引越界提示
     */
    private static IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] illegal%n", index));
    }

    /**
     * 首部插入节点
     * @param value 待插入的元素
     */
    public void addFirst(int value) {
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a, value, b);
        a.next = added;
        b.prev = added;
    }

    /**
     * 尾部插入节点
     * @param value 待插入的元素
     */
    public void addLast(int value) {
        // 已知哨兵及前置指针，不用循环遍历了
        Node a = sentinel.prev;
        Node b = sentinel;
        Node added = new Node(a, value, b);
        a.next = added;
        b.prev = added;
    }

    /**
     * 依据索引添加节点
     * @param index 添加节点的索引
     * @param value 待添加元素
     */
    public void add(int index, int value) {
        if(index == 0) {
            addFirst(value);
            return;
        }
        Node prev1 = findNode(index - 1);
        if(prev1 == null) {
            throw illegalIndex(index);
        }
        /**
         * 一共需要修改四个指针指向
         *     prev1：要插入位置索引之前的指针
         *     next1：要插入位置索引之后的指针
         *     inserted：要插入的节点，该节点前指针指向prev1，后指针指向next1
         *     prev1.next = inserted：使插入节点之后的指针指向插入的节点
         *     next1.prev = inserted：使插入节点之前的指针指向插入的节点
         */
        Node next1 = prev1.next;
        Node inserted = new Node(prev1, value, next1);
        prev1.next = inserted;
        next1.prev = inserted;
    }

    /**
     * 删除首部节点，并返回该节点的元素
     * @return 删除的首部元素
     */
    public int removeFirst() {
        Node removed = sentinel.next;
        if (removed == sentinel) {
            throw illegalIndex(0);
        }
        Node a = sentinel;
        Node b = removed.next;
        int v = removed.value;
        a.next = b;
        b.prev = a;
        return v;
    }

    /**
     * 删除尾部节点，并返回该节点的元素
     * @return 删除的尾部元素
     */
    public int removeLast() {
        Node removed = sentinel.prev;
        if (removed == sentinel) {
            throw illegalIndex(0);
        }
        int v = removed.value;
        Node a = removed.prev;
        Node b = sentinel;
        a.next = b;
        b.prev = a;
        return v;
    }

    /**
     * 按索引删除对应节点
     * @param index 删除节点的索引
     * @return 删除的元素
     */
    public int remove(int index) {
        if(index == 0) {
            return removeFirst();
        }
        Node prev1 = findNode(index - 1);
        if(prev1 == null) {
            throw illegalIndex(index);
        }
        Node removed = prev1.next;
        if(removed == sentinel) {
            throw illegalIndex(index);
        }
        Node next1 = removed.next;
        int v = removed.value;
        // 让删除节点的上一个节点后指针指向下一个节点，删除节点的下一个节点的前指针指向前一个节点，
        // 此时待删节点无节点指向，系统自动回收
        prev1.next = next1;
        next1.prev = prev1;
        return v;
    }

    /**
     * 根据值删除节点
     * 假定 value 在链表中作为 key, 有唯一性
     * @param value 待删除值
     */
    public void removeByValue(int value) {
        Node removed = findNodeByValue(value);
        if (removed != null) {
            Node prev = removed.prev;
            Node next = removed.next;
            prev.next = next;
            next.prev = prev;
        }
    }

    /**
     * 根据值删除所有对应节点
     * @param value 待删除值
     */
    public void removeAllByValue(int value) {
        Node p = sentinel.next;
        // 空链表直接退出
        if (p == sentinel) {
            return;
        }
        // 循环遍历链表，当找到指定值时执行删除操作
        while (p != sentinel) {
            if (p.value == value) {
                Node prev = p.prev;
                Node next = p.next;
                prev.next = next;
                next.prev = prev;
            }
            p = p.next;
        }
    }

    /**
     * 修改指定索引位置的元素值
     * @param index 索引位置
     * @param updateValue 修改后的元素值
     */
    public void update(int index, int updateValue) {
        Node prev = findNode(index);
        if (prev == null) {
            throw illegalIndex(index);
        }
        prev.value = updateValue;
    }

    /**
     * 将链表中所有相同的某个元素修改为指定值
     * @param value 待修改的元素
     * @param updateValue 修改后的元素
     * @return 返回修改次数，即待修改元素出现的次数
     */
    public int updateValue(int value, int updateValue) {
        int sum = 0;
        for(Node p = sentinel.next; p != sentinel; p = p.next) {
            if(p.value == value) {
                p.value = updateValue;
                sum++;
            }
        }
        return sum;
    }

    /**
     * 依据索引获取对应元素
     * @param index 索引
     * @return 该索引对应元素
     */
    public int getValue(int index) {
        int i = 0;
        for(Node p = sentinel.next; p != sentinel; p = p.next,i++) {
            if (i == index) {
                return p.value;
            }
        }
        throw illegalIndex(index);
    }

    /**
     * 依据元素查找其在链表的所以出现的索引
     * @param value 待查找的元素
     * @return 以集合存储多个索引位置
     */
    public List<Integer> getIndex(int value) {
        List<Integer> list = new ArrayList<Integer>();
        int i = 0;
        for(Node p = sentinel.next; p != sentinel; p = p.next,i++) {
            if (p.value == value) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 查找一个链表中某个元素出现的次数
     * @param value 待查找的元素
     * @return 元素出现次数
     */
    public int getValueCount(int value) {
        int sum = 0;
        for(Node p = sentinel.next; p != sentinel; p = p.next) {
            if (p.value == value) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 循环执行传入操作，传入Consumer接口，接收一个Integer类型的参数
     * @param consumer 传入操作
     */
    public void operation(Consumer<Integer> consumer) {
        Node p = sentinel.next;
        // 所有循环操作仅需修改条件为p != sentinel，表示循环一遍又回到哨兵了
        while (p != sentinel) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    /**
     * 遍历链表
     * @return 以集合形式存储遍历元素
     */
    public List<Integer> loop() {
        List<Integer> list = new ArrayList<Integer>();
        // 两种循环方式
        /*Node p = sentinel.next;
        while (p != null) {
            list.add(p.value);
            p = p.next;
        }*/
        for(Node p = sentinel.next; p != sentinel; p = p.next) {
            list.add(p.value);
        }
        return list;
    }

    /**
     * 获取链表所有元素及对应索引
     * @return 以map集合的形式存储数据：key-value -> 索引-元素
     */
    public Map<Integer, Integer> loop_index_value() {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int i = 0;
        for(Node p = sentinel.next; p != sentinel; p = p.next,i++) {
            map.put(i, p.value);
        }
        return map;
    }

    /**
     * 迭代器遍历
     */
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;
            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     * 递归遍历
     * @param node 节点
     */
    public void recursion(Node node) {
        if(node == sentinel) {
            return;
        }
        System.out.println("before:" + node.value);
        recursion(node.next);
        System.out.println("after:" + node.value);
    }

}
