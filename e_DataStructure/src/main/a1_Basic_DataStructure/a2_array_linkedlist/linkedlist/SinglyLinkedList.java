package main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist;

import java.util.*;
import java.util.function.Consumer;

/**
 * ClassName: SinglyLinkedList
 * Package: main.a1_Basic_DataStructure.a2_array_linkedlist.linkedlist
 * Description:单向链表及其基本操作
 *
 * @Author: zgh296
 * @Create: 2023/5/7 - 12:30
 * @Version: v1.0
 */
public class SinglyLinkedList implements Iterable {

    // 注：部分链表的操作会改变指针指向，因此会在操作后修改原链表，所以在使用前应当传入复制的链表(深拷贝，新开辟空间)而不是原链表！
    // 浅拷贝只复制指向某个对象的指针，而不复制对象本身，新旧对象还是共享同一块内存。
    // 深拷贝会另外创造一个一模一样的对象，新对象跟原对象不共享内存，修改新对象不会改到原对象。

    // 内部类(隐藏实现细节)，节点类，为操作方便权限全部改为public！
    public static class Node {

        public int value; // 值
        public Node next; // 下一个节点指针

        /**
         * 有参构造方法
         * @param value 值
         * @param next 下一个节点指针
         */
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        /**
         * 重写toString方法
         * @return 输出链表各节点元素
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("[");
            Node p = this;
            while (p != null) {
                sb.append(p.value);
                if(p.next != null) {
                    sb.append(", ");
                }
                p = p.next;
            }
            sb.append("]");
            return sb.toString();
        }

    }

    private Node head; // 头指针，这个头指针开始为null，当有节点时，头指针即代表了第一个节点(相当于第一个节点名字为head！)
        // 注：head开始不是真正的节点，它指向null，直到有节点时起代表第一个节点，而不是作为旧节点指向第一个节点！

    /**
     * 提供get方法获取该链表
     * @return 链表
     */
    public Node getLinkedList() {
        return head;
    }

    /**
     * 提供get方法获取头节点
     * @return 链表头节点
     */
    public Node getHead() {
        if(head == null) {
            return null;
        }
        Node headNode = head;
        headNode.next = null;
        return headNode;
    }

    /**
     * 提供get方法获取尾节点
     * @return 链表尾节点
     */
    public Node getLast() {
        Node lastNode = head;
        for (int size = 0; size != size() - 1; size ++){
            lastNode = lastNode.next;
        }
        return lastNode;
    }

    /**
     * 提供get方法获取中间节点
     * @return 链表中间节点
     */
    public Node getMiddle() {
        // 解法：快慢指针，快指针一次走两步，慢指针一次走一步，当快指针到链表结尾时，慢指针恰好走到链表的一半
        Node p1 = hasLinkedList(head);	// 慢指针，中间点
        Node p2 = hasLinkedList(head);	// 快指针
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
            p2 = p2.next;
        }
        if (p1 != null && p1.next != null) {
            p1.next = null;
        }
        return p1;
    }

    /**
     * 空参构造方法
     */
    public SinglyLinkedList(){}

    /**
     * 有参构造方法
     * @param node 链表节点
     */
    public SinglyLinkedList(Node node) {
        head = node;
    }

    /**
     * 判断链表是否为空
     * @return 判断结果
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * 获取链表长度
     * @return 链表大小
     */
    public int size() {
        int size = 0;
        // 由于下面有一次性删除多个指定元素的方法，无法准确的记录size的增减，因此只能增加时间复杂度，遍历一次链表
        for (Node p = head; p != null; p = p.next) {
            size++;
        }
        return size;
    }

    /**
     * 开辟新空间用于创建新的链表，该链表与初始链表完全相同(深复制)
     * @param node 原链表
     * @return 新链表
     */
    public Node hasLinkedList(Node node) {
        /*
          思想：先让指针p指向原始链表的头结点，声明一个复制链表的头结点为空，同时声明另外一个指针指向该复制链表的头结点；
          	    若是首次复制节点，则复制链表的头结点的next为空，并让指针q指向该头结点；否则，再次new一个节点，让指针q指
          	    向该节点，直到读取到初始链表的最后一个节点；
         */
        Node p = node;
        Node list = null;
        Node q = list;
        while(p != null){
            if(list == null){
                list = new Node(p.value,null);
                q = list;
            }else{
                q.next = new Node(p.value,null);
                q = q.next;
            }
            p = p.next;
        }
        return list;
    }

    /**
     * 内部调用方法，查找索引对应节点
     * @param index 查找的节点
     * @return 索引对应的指针
     */
    public Node   findNode(int index) {
        // 链表不会在创建时一同创建索引，因为有索引时，增删操作后需要像数组一样移动多个索引位置，导致效率降低
        int i = 0; // 索引
        for(Node p = head; p != null; p = p.next, i++){
            if(i == index) {
                return p;
            }
        }
        return null;
    }

    /**
     * 首部插入节点
     * @param value 待插入的元素
     */
    public void addFirst(int value) {
        /*
          1.链表为空
              (1)先创建一个新节点Node，存入value，使其指针指向下一个元素(没有下一个就是null)
              (2)之后再让头指针head指向此节点
         */
        // head = new Node(value, null);
        /*
          2.链表非空
              (1)先创建一个新节点Node，存入value，使其指针指向下一个元素(下一个元素就是之前head指针指向的元素)
              (2)之后再让头指针head指向此节点
              (3)因为链表为空时head指针指向空，因此可以直接使用一行代码代替链表为空/非空两种情况！
         */
        head = new Node(value, head);
    }

    /**
     * 尾部插入节点
     * @param value 待插入的元素
     */
    public void addLast(int value) {
        Node p = head;
        // 若当前链表为空，则p == null无法使其指向下一个节点，此时直接执行头插法即可
        if(p == null) {
            addFirst(value);
            return;
        }
        //     若链表不为空，需依次判断当前指针的下一个指针是否指向null，若指向不为null则表示还有元素不为链表尾部，
        // 需要循环继续判断下一个指针，直到p.next == null时说明当前指针的下一个指针指向null了，此时下一个指针即为
        // 链表的最后一个指针，改变其指向，让其指向新添加的元素指针即可，然后让新指针指向null
        while (p.next != null) {
            p = p.next;
        }
        // 注意执行顺序，先是新建节点，之后改变指针指向！
        p.next = new Node(value, null);
    }

    /**
     * 依据索引添加节点
     * @param index 添加节点的索引
     * @param value 待添加元素
     */
    public void add(int index, int value) {
        // 索引不合法直接退出
        if(index < 0) {
            return;
        }
        // 若index == 0相当于向链表头插入节点，此时index - 1 = -1不满足以下操作，需要单独处理
        if(index == 0) {
            addFirst(value);
            return;
        }
        // 调用内部私有方法查找该索引对应的前一个指针
        Node prev = findNode(index - 1);
        // 不合法索引处理
        if(prev == null) {
            // 若一个类中包含多个相同异常，可以实现该异常方法(选中异常右键重构提取方法)直接调用抛出即可！
            throw new IllegalArgumentException(String.format("index [%d] illegal%n", index));
        }
        // 注意执行顺序：1.新增指针指向后一个指针  2.前一个指针指向新增指针
        prev.next = new Node(value, prev.next);
    }

    /**
     * 删除首部节点，并返回该节点的元素
     * @return 删除的首部元素
     */
    public int removeFirst() {
        // 空链表情况
        if (head == null) {
            throw new IllegalArgumentException("The linkedlist is empty");
        }
        // 直接让头指针指向下一个指针即可，java中没有引用的节点不会内存泄漏，系统自动清除
        int v = head.value;
        head = head.next;
        return v;
    }

    /**
     * 删除尾部节点，并返回该节点的元素
     * @return 删除的尾部元素
     */
    public int removeLast() {
        // 空链表情况
        if (head == null) {
            throw new IllegalArgumentException("The linkedlist is empty");
        }
        // 链表仅有一个节点时，直接让头结点指向null即可
        if (head.next == null) {
            int v = head.value;
            head = null;
            return v;
        }
        // 链表有至少两个节点时
        Node p = head;
        while (p.next != null) {
            if(p.next.next == null) {
                int v = p.next.value;
                p.next = null;
                return v;
            }
            p = p.next;
        }
        // 其它没想到的情况，直接报错吧，程序非得要返回值，我也无语了
        throw new IllegalStateException("Other question");
    }

    /**
     * 按索引删除对应节点
     * @param index 删除节点的索引
     * @return 删除的元素
     */
    public int remove(int index) {
        // 索引不合法直接报错
        if(index < 0) {
            throw new IllegalArgumentException(String.format("index [%d] illegal%n", index));
        }
        // 删除节点为第一个时
        if (index == 0) {
            return removeFirst();
        }
        // 不为第一个节点时，找到索引对应的前一个节点，注意判断不合法情况
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] illegal%n", index));
        }
        // removed指针指向待删除的节点
        Node removed = prev.next;
        if (removed == null) {
            throw new IllegalArgumentException(String.format("index [%d] illegal%n", index));
        }
        // 记录待删除的元素
        int v = removed.value;
        prev.next = removed.next;
        /*
            图示：
                  head               prev         removed待删除节点  待删除节点的下一个节点
                   |                  |                  |                  |
             ------ ------      ------ -------     ------ ------      ------ ------
            |value1|point1| -> |value2|point2| -> |value3|point3| -> |value4|point4| -> null
             ------ ------      ------ ------      ------ ------      ------ ------       |
                                      |            删除后的指针指向           |
                                      |------>------------->-------------->|
         */
        return v;
    }

    /**
     * 删除链表所有节点
     */
    public void removeAll() {
        head = null;
    }

    /**
     * 私有方法，递归查找，根据参数position查找链表对应节点
     * @param node 链表
     * @param position 倒数位置
     * @return 递归获取节点的倒数位置
     */
    private int removeLastNode_Test(Node node, int position) {
        if(node == null) {
            return 0;
        }
        int nth =  removeLastNode_Test(node.next, position); // 下一个节点的倒数位置
        if(nth == position) {
            node.next = node.next.next;
        }
        return nth + 1; // 当前节点的倒数位置
    }

    /**
     * 根据参数position删除链表对应节点
     * @param node 链表
     * @param position 倒数位置
     * @return 删除节点后的链表
     */
    public Node removeLastNode(Node node, int position) {
        // 首个节点递归时，不知道上一个节点，需要单独处理，或者用哨兵解决
        if(position == size()) {
            removeFirst();
            return node;
        }
        removeLastNode_Test(node, position);
        return node;
        /*// 加入前置哨兵后
        Node s = new Node(-1, node);
        removeLastNode_Test(s,position);
        return s.next;*/
    }

    /**
     * 根据参数position删除链表对应节点
     * @param node 链表
     * @param position 倒数位置
     * @return 删除节点后的链表
     */
    public Node removeLastNode2(Node node, int position) {
        if(node == null) {
            throw new IllegalArgumentException("Linkedlist is null");
        }
        // 删除第一个节点时
        if(position == size()) {
            removeFirst();
            return node;
        }
        if(position > size()) {
            throw new IllegalArgumentException("Delete position exceeds Linkedlist length");
        }
        // 创建两个指针，p2在p1前面position的位置，遍历结束后，删除p1节点的下一个节点即可
        Node p1 = node;
        Node p2 = node;
        for (int i = 0; i < position + 1; i++) {
            p2 = p2.next;
        }
        while(p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return node;
    }

    /**
     * 删除有序链表中相同的节点，相同的节点会保留一个
     * @param node 链表
     * @return 删除节点后的链表
     */
    public Node removeIdenticalValue(Node node) {
        // 节点数 < 2
        if(node == null || node.next == null) {
            return node;
        }
        // 节点数 >= 2
        Node p1 = node;
        Node p2;
        // p2节点进入循环再赋值，
        while((p2 = p1.next) != null) {
            if(p1.value == p2.value) { // 找到相同元素
                // 删除p2
                p1.next = p2.next;
            } else { // 这两个元素不相同
                // 向后平移
                p1 = p1.next; // 不用管p2指针，因为下一次while循环会重新给p2赋值
            }
        }
        return node;
    }

    /**
     * 递归删除有序链表中相同的节点，相同的节点会保留一个
     * @param node 链表
     * @return 删除节点后的链表
     */
    private Node removeIdenticalValue2_Test(Node node) {
        // BUG：该方法无法修改链表的首个相同元素节点
        // 不考虑空链表和仅有一个元素的链表
        if(node == null || node.next == null) {
            return node;
        }
        // 若node和node.next节点元素相等，则返回下一个节点开始的去重结果
        // 若node和node.next节点元素不相等，则向后递归，比较node.next和node.next.next节点元素，依次递归
        if(node.value == node.next.value) {
            return removeIdenticalValue2_Test(node.next);
        } else {
            node.next = removeIdenticalValue2_Test(node.next);
            return node;
        }
    }

    /**
     * 递归删除有序链表中相同的节点，相同的节点会保留一个
     * @param node 链表
     * @return 删除节点后的链表
     */
    public Node removeIdenticalValue2(Node node) {
        // removeIdenticalValue2_Test输出的修改链表无问题，但无法修改链表的首个相同元素节点
        head = removeIdenticalValue2_Test(node);
        return head;
    }

    /**
     * 删除有序链表中相同的节点，相同的节点全部删除
     * @param node 链表
     * @return 删除节点后的链表
     */
    public Node removeIdenticalAllValue(Node node) {
        // 不考虑空链表和仅有一个元素的链表
        if(node == null || node.next == null) {
            return node;
        }
        /*
          p1 是待删除的上一个节点，每次循环对比 p2、p3 的值
          如果 p2 与 p3 的值重复，那么 p3 继续后移，直到找到与 p2 不重复的节点，p1 指向 p3 完成删除
          如果 p2 与 p3 的值不重复，p1，p2，p3 向后平移一位，继续上面的操作
          p2 或 p3 为 null 退出循环
         */
        Node s = new Node(-1, node);
        Node p1 = s;
        Node p2, p3;
        while((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if(p2.value == p3.value) {
                while ((p3 = p3.next) != null && p3.value == p2.value) {
                }
                // p3找到了不重复的值
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
        }
        head = s.next; // 该方法无法删除原链表的首个相同节点
        return head;
    }

    /**
     * 递归删除有序链表中相同的节点，相同的节点全部删除
     * @param node 链表
     * @return 删除节点后的链表
     */
    private Node removeIdenticalAllValue2_Test(Node node) {
        // BUG：该方法无法修改链表的首个相同元素节点
        // 不考虑空链表和仅有一个元素的链表
        if(node == null || node.next == null) {
            return node;
        }
        // 表示看不懂了......
        // 若当前节点与next重复，一直找到下一个不重复的节点，以它返回的结果为准
        // 若当前节点与next不重复，返回当且节点，同时更新next
        if(node.value == node.next.value) {
            Node x = node.next.next;
            while(x  != null && x.value == node.value) {
                x = x.next;
            }
            return removeIdenticalAllValue2_Test(x);
        } else {
            node.next = removeIdenticalAllValue2_Test(node.next);
            return node;
        }
    }

    /**
     * 递归删除有序链表中相同的节点，相同的节点全部删除
     * @param node 链表
     * @return 删除节点后的链表
     */
    public Node removeIdenticalAllValue2(Node node) {
        // removeIdenticalValue2_Test输出的修改链表无问题，但无法修改链表的首个相同元素节点
        head = removeIdenticalAllValue2_Test(node);
        return head;
    }

    /**
     * 修改指定索引位置的元素值
     * @param index 索引位置
     * @param updateValue 修改后的元素值
     */
    public void update(int index, int updateValue) {
        Node prev = findNode(index);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] illegal%n", index));
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
        for(Node p = head; p != null; p = p.next) {
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
        // 索引不合法
        if(index < 0) {
            throw new IllegalArgumentException(String.format("index [%d] illegal%n", index));
        }
        // 调用内部私有方法查找该索引对应指针
        Node node = findNode(index);
        // 找不到抛异常，找到返回元素
        if(node == null) {
            throw new IllegalArgumentException(String.format("index [%d] illegal%n", index));
        }
        return node.value;
    }

    /**
     * 依据元素查找其在链表的所以出现的索引
     * @param value 待查找的元素
     * @return 以集合存储多个索引位置
     */
    public List<Integer> getIndex(int value) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for(Node p = head; p != null; p = p.next,i++) {
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
        for(Node p = head; p != null; p = p.next) {
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
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    /**
     * 遍历链表
     * @return 以集合形式存储遍历元素
     */
    public List<Integer> loop() {
        List<Integer> list = new ArrayList<>();
        // 两种循环方式
        /*Node p = head;
        while (p != null) {
            list.add(p.value);
            p = p.next;
        }*/
        for(Node p = head; p != null; p = p.next) {
            list.add(p.value);
        }
        return list;
    }

    /**
     * 获取链表所有元素及对应索引
     * @return 以map集合的形式存储数据：key-value -> 索引-元素
     */
    public Map<Integer, Integer> loop_index_value() {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for(Node p = head; p != null; p = p.next, i++){
            map.put(i, p.value);
        }
        return map;
    }

    /**
     * 迭代器遍历
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            Node p = head;

            // 是否有下一个元素
            @Override
            public boolean hasNext() {
                return p != null;
            }

            // 返回当前值，并指向下一个元素
            @Override
            public Integer next() {
                int v = p.value;
                p = p.next;
                return v;
            }
        };
    }

    /**
     * 递归遍历
     * @param node 节点
     */
    public void recursion(Node node) {
        if(node == null) {
            return;
        }
        System.out.println("before:" + node.value);
        recursion(node.next);
        System.out.println("after:" + node.value);
    }

    /**
     * 获取反转链表的元素，但不修改原链表
     * @param node 原链表
     * @return 反转链表后的各个结点
     */
    public Node reverseNoChoose(Node node) {
        Node n1 = null; // 新的节点
        Node p = node; // 此链表中的头结点
        while(p != null) {
            // 先新建一个节点保存原链表节点的值，让其下一个指针指向n1，之后让指针n1指向该节点的指针
            n1 = new Node(p.value, n1);
            // 循环原链表指针
            p = p.next;
        }
        return n1;
    }

    /**
     * 获取反转链表的元素，但不修改原链表(有bug，直接调用会修改原链表，需要传入复制链表)
     * @param node 原链表
     * @return 反转链表后的各个结点
     */
    private Node reverseNoChoose2_Test(Node node) {
        // 递归实现
        // 当原链表为空或递归结束时
        if (node == null || node.next == null) {
            return node;
        }
        // 依次递归获取下一个节点
        Node last = reverseNoChoose2_Test(node.next);
        // 修改此节点(node)，让此节点(node)的下一个节点(node.next)指向(node.next.next)此节点(node)
        node.next.next = node;
        // 此时两个节点均指向对方，成为死循环，为避免此情况，需要使该节点(node)的下一个节点(node.next)指向null
        node.next = null;
        return last;
    }

    /**
     * 获取反转链表的元素，但不修改原链表
     * @param node 原链表
     * @return 反转链表后的各个结点
     */
    public Node reverseNoChoose2(Node node) {
        Node newNode = hasLinkedList(node);
        return reverseNoChoose2_Test(newNode);
    }

    /**
     * 获取反转链表的元素，但不修改原链表
     * @param node 原链表
     * @return 反转链表后的各个结点
     */
    public Node reverseNoChoose3(Node node) {
        // 有bug，调用会修改原链表，因此需要用hasLinkedList传入复制链表
        Node newNode = hasLinkedList(node);
        // 当原链表为空或循环结束时
        if (newNode == null || newNode.next == null) {
            return newNode;
        }
        Node o1 = newNode.next; // 旧指针
        Node n1 = newNode; // 新指针
        // 从链表第二个节点开始，将节点移动变为首个节点(头结点之后)，依次循环最终反转
        while(o1 != null) {
            newNode.next = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = newNode.next;
        }
        return n1;
    }

    /**
     * 获取反转链表的元素，但不修改原链表
     * @param node 原链表
     * @return 反转链表后的各个结点
     */
    public Node reverseNoChoose4(Node node) {
        // 有bug，调用会修改原链表，因此需要用hasLinkedList传入复制链表
        Node newNode = hasLinkedList(node);
        Node n1 = null;
        // 从链表第二个节点开始，将节点移动变为首个节点并添加到新链表中(头结点之后)，依次循环最终得到反转链表
        while(newNode != null) {
            Node o1 = newNode.next;
            newNode.next = n1;
            n1 = newNode;
            newNode = o1;
        }
        return n1;
    }

    /**
     * 获取反转链表的元素，但不修改原链表
     * @return 反转链表的元素组成的集合
     */
    public List<Integer> reverseNoChoose5() {
        List<Integer> list = loop();
        Collections.reverse(list);
        return list;
    }

    /**
     * 反转链表
     */
    public void reverseChoose() {
        Node n1 = null; // 新的节点
        Node p = head; // 此链表中的头结点
        while(p != null) {
            // 先新建一个节点保存原链表节点的值，让其下一个指针指向n1，之后让指针n1指向该节点的指针
            n1 = new Node(p.value, n1);
            // 循环原链表指针
            p = p.next;
        }
        // 修改原链表头节点，让其指向修改后的节点
        head = n1;
    }

    /**
     * 链表升序，插入排序
     * @param node 链表
     * @return 排序后的链表
     */
    public Node insertionSort(Node node) {
        if(node == null || node.next == null) {
            return node;
        }
        Node pre = node; // pre指向已经有序的节点
        Node cur = node.next; // cur指向待排序的节点
        Node aux = new Node(-1, node); // 辅助节点
        while(cur != null) {
            if(cur.value < pre.value) {
                // 先把cur节点从当前链表中删除，然后再把cur节点插入到合适位置
                pre.next = cur.next;
                // 从前往后找到l2.val>cur.val,然后把cur节点插入到l1和l2之间
                Node l1 = aux;
                Node l2 = aux.next;
                while(cur.value > l2.value) {
                    l1 = l2;
                    l2 = l2.next;
                }
                // 把cur节点插入到l1和l2之间
                l1.next = cur;
                cur.next = l2;// 插入合适位置
                cur = pre.next;// 指向下一个待处理节点
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        head = hasLinkedList(aux.next);
        return aux.next;
    }

    /**
     * 链表升序，归并排序
     * @param node 链表
     * @return 排序后的链表
     */
    public Node mergeSort(Node node){
        if(node == null || node.next == null) {
            return node;
        }
        Node mid = getMid(node); // 获取链表中间节点
        // 把链表从之间拆分为两个链表：head和second两个子链表
        Node second = mid.next;
        mid.next = null;
        // 对两个子链表排序
        Node left = mergeSort(node);
        Node right = mergeSort(second);
        return merge(right,left);
    }

    // 两个有序链表的归并
    private Node merge(Node l1, Node l2){
        // 辅助节点，排好序的节点将会链接到dummy后面
        Node dummy = new Node(0, head);
        Node tail = dummy;// tail指向最后一个排好序的节点
        while(l1 != null && l2 != null) {
            if(l1.value < l2.value) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next; // 移动tail指针
        }
        if(l1 != null) {
            tail.next = l1;
        } else {
            tail.next = l2;
        }
        return dummy.next;
    }

    /**
     *  返回链表中间节点及之后的节点
     * @param node 链表
     * @return 链表中间节点及之后的节点
     */
    private Node getMid(Node node){
        if(node == null || node.next == null) {
            return node;
        }
        Node slow = node;
        Node faster = node.next;
        while(faster != null && faster.next != null) {
            slow = slow.next;
            faster = faster.next.next;
        }
        return slow;
    }

    /**
     * 链表升序，快速排序
     * @param begin 起始节点
     * @param end 结束节点
     * @return 排序后的链表
     */
    public Node quickSort(Node begin, Node end) {
        // 判断为空，判断是不是只有一个节点
        if (begin == null || end == null || begin == end) {
            return begin;
        }
        // 从第一个节点和第一个节点的后面一个几点
        // begin指向的是当前遍历到的最后一个 <= nMidValue的节点
        Node first = begin;
        Node second = begin.next;
        int nMidValue = begin.value;
        // 结束条件，second到最后了
        while (second != end.next && second != null) { // 结束条件
            // 一直往后寻找<=nMidValue的节点，然后与fir的后继节点交换
            if (second.value < nMidValue) {
                first = first.next;
                // 判断一下，避免后面的数比第一个数小，不用换的局面
                if (first != second) {
                    int temp = first.value;
                    first.value = second.value;
                    second.value = temp;
                }
            }
            second = second.next;
        }
        // 判断，有些情况是不用换的，提升性能
        if (begin != first) {
            int temp = begin.value;
            begin.value = first.value;
            first.value = temp;
        }
        // 前部分递归
        quickSort(begin, first);
        // 后部分递归
        quickSort(first.next, end);
        return begin;
    }

    /**
     * 合并两个有序链表
     * @param node1 第一个有序链表
     * @param node2 第二个有序链表
     * @return 合并后的有序链表
     */
    public Node mergeTwoLinkedlist(Node node1, Node node2) {
        // 注意新建链表存储数据，否则会异常修改原链表数据
        Node p1 = hasLinkedList(node1);
        Node p2 = hasLinkedList(node2);
        /*
        p1 是待删除的上一个节点，每次循环对比 p2、p3 的值
            如果 p2 与 p3 的值重复，那么 p3 继续后移，直到找到与 p2 不重复的节点，p1 指向 p3 完成删除
            如果 p2 与 p3 的值不重复，p1，p2，p3 向后平移一位，继续上面的操作
            p2 或 p3 为 null 退出循环
         */
        Node s = new Node(-1, null);
        Node p = s;
        while(p1 != null && p2 != null) {
            if(p1.value < p2.value) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if(p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        head.next = s.next;
        return head.next;
    }

    /**
     * 合并多个链表
     * @param lists 链表集合
     * @return 合并后的链表
     */
    public Node mergeMultipleLinkedlist(Node[] lists) {
        return split(lists, 0, lists.length - 1);
    }

    /**
     * 内部方法，递归合并数组中的链表
     * @param lists 链表集合
     * @param i 集合起始索引
     * @param j 集合结束索引
     * @return  合并后的链表
     */
    private Node split(Node[] lists, int i, int j) {
        // 集合为空判断
        if(lists.length == 0 || lists.length == 1) {
            return null;
        }
        if(lists.length == 2) {
            return mergeTwoLinkedlist(lists[i], lists[j]);
        }
        // 递归结束条件，当二分法划分到每个数组仅剩一个元素时，返回该元素参与链表合并
        if(i == j) {
            return lists[i];
        }
        // 数组中点
        int m = (i + j) >>> 1;
        // 二分法，将数组从中点一分为二，之后依次递归继续划分，直至数组仅剩一个元素时结束
        // 分而治之，减而治之
        Node left = split(lists, i , m);
        Node right = split(lists, m + 1, j);
        // 左右划分的数组均仅剩一个元素时，获取数组中唯一的链表元素，进行两个链表的合并操作
        return mergeTwoLinkedlist(left, right);
    }

    /**
     * 判断链表是否为回文链表
     * @param node 链表
     * @return 判断结果true or false
     */
    public boolean isPalindrome(Node node) {
        Node middle = getMid(node);
        Node newHead = reverseNoChoose(middle);
        while (newHead != null) {
            if(newHead.value != node.value) {
                return false;
            }
            newHead = newHead.next;
            node = node.next;
        }
        return true;
    }

    /**
     * 判断链表是否有环
     * @param node 链表
     * @return 判断结果
     */
    public boolean hasCycle(Node node) {
        Node fast = node;
        Node slow = node;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 若链表有环，找到环的入口节点，若无环，则返回null
     * @param node 链表
     * @return 环的入口节点
     */
    public Node detectCycle(Node node) {
        // 弗洛伊德龟兔赛跑求环及入口
        Node fast = node;
        Node slow = node;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                slow = node;
                while(true) {
                    if(fast == slow) {
                        return fast;
                    }
                    fast = fast.next;
                    slow = slow.next;
                }
            }
        }
        return null;
    }

    /*
      随机访问性能
          根据 index 查找，时间复杂度 O(n)
      插入或删除性能
          起始位置：O(1)
          结束位置：如果已知 tail 尾节点是 O(1)，不知道 tail 尾节点是 O(n)
          中间位置：根据 index 查找时间 + O(1)
     */

}
