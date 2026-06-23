package main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist;

import org.junit.jupiter.api.Test;

/**
 * ClassName: LinkedList_Test
 * Package: main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist
 * Description:链表测试类
 *
 * @Author: zgh296
 * @Create: 2023/5/7 - 12:58
 * @Version: v1.0
 */
public class LinkedList_Test {

    // 单向链表
    @Test
    public void test1() {

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        // 元素增删改
        singlyLinkedList.addLast(0); // [0]
        singlyLinkedList.addFirst(1); // [1] 0
        singlyLinkedList.addFirst(2); // [2] 1 0
        singlyLinkedList.addFirst(3); // [3] 2 1 0
        singlyLinkedList.addFirst(4); // [4] 3 2 1 0
        singlyLinkedList.addLast(10); // 4 3 2 1 0 [10]
        singlyLinkedList.addLast(10); // 4 3 2 1 0 10 [10]
        singlyLinkedList.add(3,15); // 4 3 2 [15] 1 0 10 10
        singlyLinkedList.add(0,-15); // [-15] 4 3 2 15 1 0 10 10
        // singlyLinkedList.add(100,12);
        System.out.println(singlyLinkedList.removeFirst()); // x-15x 4 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeFirst()); // x4x 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeLast()); // 3 2 15 1 0 10 x10x
        System.out.println(singlyLinkedList.remove(0)); // x3x 2 15 1 0 10
        System.out.println(singlyLinkedList.remove(2)); // 2 15 x1x 0 10
        singlyLinkedList.update(1,10); // 2 15->10 0 10
        singlyLinkedList.updateValue(10,20); // 2 10->20 0 10->20

        // 查找单个元素
        System.out.println(singlyLinkedList.getValue(3)); // 20
        // System.out.println(singlyLinkedList.getValue(20));
        System.out.println(singlyLinkedList.getIndex(20)); // [1, 3]
        System.out.println(singlyLinkedList.getValueCount(20)); // 2

        // 遍历
        // 传入操作遍历
        singlyLinkedList.operation(value -> System.out.print(value+ " ")); // 2 20 0 20
        System.out.println();
        // loop方式遍历
        System.out.println(singlyLinkedList.loop()); // [2, 20, 0, 20]
        // 迭代器遍历
        for(Object value : singlyLinkedList) {
            System.out.print(value + " ");
        } // 2 20 0 20
        System.out.println();
        // 遍历得到链表索引及元素
        System.out.println(singlyLinkedList.loop_index_value()); // {0=2, 1=20, 2=0, 3=20}
        singlyLinkedList.recursion(singlyLinkedList.getLinkedList());

    }

    // 单向链表(带哨兵)
    @Test
    public void test2() {

        SinglyLinkedListSentinel singlyLinkedList = new SinglyLinkedListSentinel();

        // 元素增删改
        singlyLinkedList.addLast(0); // [0]
        singlyLinkedList.addFirst(1); // [1] 0
        singlyLinkedList.addFirst(2); // [2] 1 0
        singlyLinkedList.addFirst(3); // [3] 2 1 0
        singlyLinkedList.addFirst(4); // [4] 3 2 1 0
        singlyLinkedList.addLast(10); // 4 3 2 1 0 [10]
        singlyLinkedList.addLast(10); // 4 3 2 1 0 10 [10]
        singlyLinkedList.add(3,15); // 4 3 2 [15] 1 0 10 10
        singlyLinkedList.add(0,-15); // [-15] 4 3 2 15 1 0 10 10
        // singlyLinkedList.add(100,12);
        System.out.println(singlyLinkedList.removeFirst()); // x-15x 4 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeFirst()); // x4x 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeLast()); // 3 2 15 1 0 10 x10x
        System.out.println(singlyLinkedList.remove(0)); // x3x 2 15 1 0 10
        System.out.println(singlyLinkedList.remove(2)); // 2 15 x1x 0 10
        singlyLinkedList.update(1,10); // 2 15->10 0 10
        singlyLinkedList.updateValue(10,20); // 2 10->20 0 10->20

        // 查找单个元素
        System.out.println(singlyLinkedList.getValue(3)); // 20
        // System.out.println(singlyLinkedList.getValue(20));
        System.out.println(singlyLinkedList.getIndex(20)); // [1, 3]
        System.out.println(singlyLinkedList.getValueCount(20)); // 2

        // 遍历
        // 传入操作遍历
        singlyLinkedList.operation(value -> System.out.print(value+ " ")); // 2 20 0 20
        System.out.println();
        // loop方式遍历
        System.out.println(singlyLinkedList.loop()); // [2, 20, 0, 20]
        // 迭代器遍历
        for(Object value : singlyLinkedList) {
            System.out.print(value + " ");
        } // 2 20 0 20
        System.out.println();
        // 遍历得到链表索引及元素
        System.out.println(singlyLinkedList.loop_index_value()); // {0=2, 1=20, 2=0, 3=20}
        singlyLinkedList.recursion(singlyLinkedList.getHeadNext());

    }

    // 双向链表(带哨兵)
    @Test
    public void test3() {

        DoubleLinkedListSentinel singlyLinkedList = new DoubleLinkedListSentinel();

        // 元素增删改
        singlyLinkedList.addLast(0); // [0]
        singlyLinkedList.addFirst(1); // [1] 0
        singlyLinkedList.addFirst(2); // [2] 1 0
        singlyLinkedList.addFirst(3); // [3] 2 1 0
        singlyLinkedList.addFirst(4); // [4] 3 2 1 0
        singlyLinkedList.addLast(10); // 4 3 2 1 0 [10]
        singlyLinkedList.addLast(10); // 4 3 2 1 0 10 [10]
        singlyLinkedList.add(3,15); // 4 3 2 [15] 1 0 10 10
        singlyLinkedList.add(0,-15); // [-15] 4 3 2 15 1 0 10 10
        // singlyLinkedList.add(100,12);
        System.out.println(singlyLinkedList.removeFirst()); // x-15x 4 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeFirst()); // x4x 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeLast()); // 3 2 15 1 0 10 x10x
        System.out.println(singlyLinkedList.remove(0)); // x3x 2 15 1 0 10
        System.out.println(singlyLinkedList.remove(2)); // 2 15 x1x 0 10
        singlyLinkedList.update(1,10); // 2 15->10 0 10
        singlyLinkedList.updateValue(10,20); // 2 10->20 0 10->20

        // 查找单个元素
        System.out.println(singlyLinkedList.getValue(3)); // 20
        // System.out.println(singlyLinkedList.getValue(20));
        System.out.println(singlyLinkedList.getIndex(20)); // [1, 3]
        System.out.println(singlyLinkedList.getValueCount(20)); // 2

        // 遍历
        // 传入操作遍历
        singlyLinkedList.operation(value -> System.out.print(value+ " ")); // 2 20 0 20
        System.out.println();
        // loop方式遍历
        System.out.println(singlyLinkedList.loop()); // [2, 20, 0, 20]
        // 迭代器遍历
        for(Object value : singlyLinkedList) {
            System.out.print(value + " ");
        } // 2 20 0 20
        System.out.println();
        // 遍历得到链表索引及元素
        System.out.println(singlyLinkedList.loop_index_value()); // {0=2, 1=20, 2=0, 3=20}

    }

    // 环形链表(带哨兵)
    @Test
    public void test4() {

        DoubleCircularlyLinkedListSentinel singlyLinkedList = new DoubleCircularlyLinkedListSentinel();

        // 元素增删改
        singlyLinkedList.addLast(0); // [0]
        singlyLinkedList.addFirst(1); // [1] 0
        singlyLinkedList.addFirst(2); // [2] 1 0
        singlyLinkedList.addFirst(3); // [3] 2 1 0
        singlyLinkedList.addFirst(4); // [4] 3 2 1 0
        singlyLinkedList.addLast(10); // 4 3 2 1 0 [10]
        singlyLinkedList.addLast(10); // 4 3 2 1 0 10 [10]
        singlyLinkedList.add(3,15); // 4 3 2 [15] 1 0 10 10
        singlyLinkedList.add(0,-15); // [-15] 4 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.loop());
        // singlyLinkedList.add(100,12);
        System.out.println(singlyLinkedList.removeFirst()); // x-15x 4 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeFirst()); // x4x 3 2 15 1 0 10 10
        System.out.println(singlyLinkedList.removeLast()); // 3 2 15 1 0 10 x10x
        System.out.println(singlyLinkedList.remove(0)); // x3x 2 15 1 0 10
        System.out.println(singlyLinkedList.remove(2)); // 2 15 x1x 0 10
        singlyLinkedList.removeByValue(100); // 2 15 0 10
        singlyLinkedList.removeByValue(2); // x2x 15 0 10
        singlyLinkedList.update(1,10); //  15 0->10 10
        singlyLinkedList.updateValue(10,20); // 15 10->20 10->20

        // 查找单个元素
        System.out.println(singlyLinkedList.getValue(1)); // 20
        //System.out.println(singlyLinkedList.getValue(20));
        System.out.println(singlyLinkedList.getIndex(20)); // [1, 2]
        System.out.println(singlyLinkedList.getValueCount(20)); // 2

        // 遍历
        // 传入操作遍历
        singlyLinkedList.operation(value -> System.out.print(value+ " ")); // 15 20 20
        System.out.println();
        // loop方式遍历
        System.out.println(singlyLinkedList.loop()); // [15, 20, 20]
        // 迭代器遍历
        for(Object value : singlyLinkedList) {
            System.out.print(value + " ");
        } // 15 20 20
        System.out.println();
        // 遍历得到链表索引及元素
        System.out.println(singlyLinkedList.loop_index_value()); // {0=15, 1=20, 2=20}

    }

}
