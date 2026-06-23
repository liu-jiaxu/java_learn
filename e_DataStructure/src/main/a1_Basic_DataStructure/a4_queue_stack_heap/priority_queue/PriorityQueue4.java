package main.a1_Basic_DataStructure.a4_queue_stack_heap.priority_queue;

import main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist.SinglyLinkedList;

/**
 * ClassName: PriorityQueue4
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.priority_queue
 * Description:小顶堆实现优先级队列
 *
 * @Author: zgh296
 * @Create: 2023/5/22 - 16:56
 * @Version: v1.0
 */
public class PriorityQueue4 {

    // 小顶堆实现优先级队列
    
    SinglyLinkedList.Node[] array;
    int size;

    public PriorityQueue4(int capacity) {
        array = new SinglyLinkedList.Node[capacity];
    }

    public void offer(SinglyLinkedList.Node offered) {
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.value < array[parent].value) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
    }

    public SinglyLinkedList.Node poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);
        size--;
        SinglyLinkedList.Node e = array[size];
        array[size] = null; // help GC

        down(0);

        return e;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int min = parent;
        if (left < size && array[left].value < array[min].value) {
            min = left;
        }
        if (right < size && array[right].value < array[min].value) {
            min = right;
        }
        if (min != parent) {
            swap(min, parent);
            down(min);
        }
    }

    private void swap(int i, int j) {
        SinglyLinkedList.Node t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
