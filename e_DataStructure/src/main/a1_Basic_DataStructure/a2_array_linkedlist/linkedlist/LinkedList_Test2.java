package main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist;

import org.junit.jupiter.api.Test;

/**
 * ClassName: LinkedList_Test2
 * Package: main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/10 - 20:43
 * @Version: v1.0
 */
public class LinkedList_Test2 {

    @Test
    public void test() {

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        for (int i = 0; i < 10;i++) {
            singlyLinkedList.addLast(i + 1);
        }
        System.out.println(singlyLinkedList.size());
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.hasLinkedList(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose2(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose3(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose4(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose5());

        System.out.println("----------------------------------------------------------");
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.reverseChoose();
        System.out.println(singlyLinkedList.loop());

        System.out.println("----------------------------------------------------------");
        singlyLinkedList.removeAll();
        System.out.println(singlyLinkedList.loop());
        for (int i = 0; i < 10;i++) {
            singlyLinkedList.addLast(i + 1);
        }
        singlyLinkedList.removeLastNode(singlyLinkedList.getLinkedList(),1);
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.removeLastNode(singlyLinkedList.getLinkedList(),9);
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.removeLastNode2(singlyLinkedList.getLinkedList(),1);
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.removeLastNode2(singlyLinkedList.getLinkedList(),3);
        System.out.println(singlyLinkedList.loop());

        System.out.println("----------------------------------------------------------");
        singlyLinkedList.removeAll();
        for (int i = 0; i < 20; i++) {
            singlyLinkedList.addLast((int) (Math.random() * 10));
        }
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.insertionSort(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.mergeSort(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.size());
        //System.out.println(singlyLinkedList.removeIdenticalValue(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.removeIdenticalValue2(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.size());

        System.out.println("----------------------------------------------------------");
        singlyLinkedList.removeAll();
        for (int i = 0; i < 20; i++) {
            singlyLinkedList.addLast((int) (Math.random() * 10));
        }
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.insertionSort(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.removeIdenticalAllValue(singlyLinkedList.getLinkedList()));
        //System.out.println(singlyLinkedList.removeIdenticalAllValue2(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.getLinkedList());
        System.out.println(singlyLinkedList.size());

        System.out.println("----------------------------------------------------------");
        SinglyLinkedList singlyLinkedList2 = new SinglyLinkedList();
        SinglyLinkedList singlyLinkedList3 = new SinglyLinkedList();
        for (int i = 0; i < 20; i++) {
            singlyLinkedList2.addFirst((int) (Math.random() * 15));
            singlyLinkedList3.addFirst((int) (Math.random() * 15));
        }
        singlyLinkedList2.insertionSort(singlyLinkedList2.getLinkedList());
        singlyLinkedList3.insertionSort(singlyLinkedList3.getLinkedList());
        System.out.println(singlyLinkedList2.loop());
        System.out.println(singlyLinkedList3.loop());
        SinglyLinkedList singlyLinkedList4 = new SinglyLinkedList();
        singlyLinkedList4.mergeTwoLinkedlist(singlyLinkedList2.getLinkedList(),singlyLinkedList3.getLinkedList());
        System.out.println(singlyLinkedList4.size());
        System.out.println(singlyLinkedList4.loop());

        System.out.println("----------------------------------------------------------");
        SinglyLinkedList singlyLinkedList5 = new SinglyLinkedList(singlyLinkedList3.getLast());
        SinglyLinkedList singlyLinkedList6 = new SinglyLinkedList();
        SinglyLinkedList.Node[] lists = {singlyLinkedList2.getLinkedList(),singlyLinkedList3.getLinkedList(),singlyLinkedList5.getLinkedList()};
        singlyLinkedList6.mergeMultipleLinkedlist(lists);
        System.out.println(singlyLinkedList6.loop());
        System.out.println(lists[0]);
        System.out.println(lists[1]);
        System.out.println(singlyLinkedList6.getMiddle());
        System.out.println(singlyLinkedList6.loop());
        System.out.println(singlyLinkedList6.isPalindrome(singlyLinkedList6.getLinkedList()));

    }

    @Test
    public void test2() {

        SinglyLinkedListSentinel singlyLinkedList = new SinglyLinkedListSentinel();
        for (int i = 0; i < 10;i++) {
            singlyLinkedList.addLast(i + 1);
        }
        System.out.println(singlyLinkedList.size());
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.hasLinkedList(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose2(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose3(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose4(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.reverseNoChoose5());

        System.out.println("----------------------------------------------------------");
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.reverseChoose();
        System.out.println(singlyLinkedList.loop());

        System.out.println("----------------------------------------------------------");
        singlyLinkedList.removeAll();
        System.out.println(singlyLinkedList.loop());
        for (int i = 0; i < 10;i++) {
            singlyLinkedList.addLast(i + 1);
        }
        singlyLinkedList.removeLastNode(singlyLinkedList.getLinkedList(),1);
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.removeLastNode(singlyLinkedList.getLinkedList(),9);
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.removeLastNode2(singlyLinkedList.getLinkedList(),1);
        System.out.println(singlyLinkedList.loop());
        singlyLinkedList.removeLastNode2(singlyLinkedList.getLinkedList(),3);
        System.out.println(singlyLinkedList.loop());

        System.out.println("----------------------------------------------------------");
        singlyLinkedList.removeAll();
        for (int i = 0; i < 20; i++) {
            singlyLinkedList.addLast((int) (Math.random() * 10));
        }
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.insertionSort(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.mergeSort(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.size());
        //System.out.println(singlyLinkedList.removeIdenticalValue(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.removeIdenticalValue2(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.size());

        System.out.println("----------------------------------------------------------");
        singlyLinkedList.removeAll();
        for (int i = 0; i < 20; i++) {
            singlyLinkedList.addLast((int) (Math.random() * 10));
        }
        System.out.println(singlyLinkedList.loop());
        System.out.println(singlyLinkedList.insertionSort(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.removeIdenticalAllValue(singlyLinkedList.getLinkedList()));
        //System.out.println(singlyLinkedList.removeIdenticalAllValue2(singlyLinkedList.getLinkedList()));
        System.out.println(singlyLinkedList.getLinkedList());
        System.out.println(singlyLinkedList.size());

        System.out.println("----------------------------------------------------------");
        SinglyLinkedListSentinel singlyLinkedList2 = new SinglyLinkedListSentinel();
        SinglyLinkedListSentinel singlyLinkedList3 = new SinglyLinkedListSentinel();
        for (int i = 0; i < 20; i++) {
            singlyLinkedList2.addFirst((int) (Math.random() * 15));
            singlyLinkedList3.addFirst((int) (Math.random() * 15));
        }
        singlyLinkedList2.insertionSort(singlyLinkedList2.getLinkedList());
        singlyLinkedList3.insertionSort(singlyLinkedList3.getLinkedList());
        System.out.println(singlyLinkedList2.loop());
        System.out.println(singlyLinkedList3.loop());
        SinglyLinkedListSentinel singlyLinkedList4 = new SinglyLinkedListSentinel();
        singlyLinkedList4.mergeTwoLinkedlist(singlyLinkedList2.getLinkedList(),singlyLinkedList3.getLinkedList());
        System.out.println(singlyLinkedList4.size());
        System.out.println(singlyLinkedList4.loop());

        System.out.println("----------------------------------------------------------");
        SinglyLinkedListSentinel singlyLinkedList5 = new SinglyLinkedListSentinel(singlyLinkedList3.getLast());
        System.out.println(singlyLinkedList5.getLinkedList());
        SinglyLinkedListSentinel singlyLinkedList6 = new SinglyLinkedListSentinel();
        SinglyLinkedListSentinel.Node[] lists = {singlyLinkedList2.getLinkedList(),singlyLinkedList3.getLinkedList(),singlyLinkedList5.getLinkedList()};
        singlyLinkedList6.mergeMultipleLinkedlist(lists);
        System.out.println(singlyLinkedList6.loop());
        System.out.println(lists[0]);
        System.out.println(lists[1]);
        System.out.println(singlyLinkedList6.getMiddle());
        System.out.println(singlyLinkedList6.loop());
        System.out.println(singlyLinkedList6.size());
        System.out.println(singlyLinkedList6.isPalindrome(singlyLinkedList6.getLinkedList()));

    }

    @Test
    public void test3() {

        DoubleLinkedListSentinel doubleLinkedList = new DoubleLinkedListSentinel();
        doubleLinkedList.addFirst(10);
        doubleLinkedList.addFirst(10);
        doubleLinkedList.removeAllByValue(10);
        System.out.println(doubleLinkedList.loop());
        for (int i = 0; i < 10; i++) {
            doubleLinkedList.addFirst(i + 1);
        }
        doubleLinkedList.removeByFirstValue(10);
        System.out.println(doubleLinkedList.loop());

    }

    @Test
    public void test4() {

        DoubleCircularlyLinkedListSentinel doubleCircularlyLinkedListSentinel = new DoubleCircularlyLinkedListSentinel();
        doubleCircularlyLinkedListSentinel.addFirst(10);
        doubleCircularlyLinkedListSentinel.add(0,5);
        doubleCircularlyLinkedListSentinel.addFirst(10);
        doubleCircularlyLinkedListSentinel.add(1,7);
        doubleCircularlyLinkedListSentinel.add(1,7);
        doubleCircularlyLinkedListSentinel.removeAllByValue(7);
        doubleCircularlyLinkedListSentinel.removeByValue(10);
        System.out.println(doubleCircularlyLinkedListSentinel.loop());

    }

}
