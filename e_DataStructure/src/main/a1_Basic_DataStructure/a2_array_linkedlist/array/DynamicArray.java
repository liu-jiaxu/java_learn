package main.a1_Basic_DataStructure.a2_array_linkedlist.array;

// 感觉自己在写简单的源码，泰裤辣...

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * ClassName: DynamicArray
 * Package: main.a1_Basic_DataStructure.a2_array_linkedlist
 * Description:动态数组及增删改查方法
 *
 * @Author: zgh296
 * @Create: 2023/5/5 - 9:31
 * @Version: v1.0
 */
public class DynamicArray implements Iterable<Integer> {

    private int size = 0; // 逻辑大小
    private int capacity = 8; // 容量
    private int[] array = {}; // 原始数组，懒惰初始化，使用时才赋值容量，占用容量减少

    /**
     * 容量检查，若容量不足则扩容1.5倍
     */
    private void checkAndGrow() {
        // 容量检查，使用数组时赋值容量为默认值
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            // 进行扩容, 1.5(Java默认) 1.618(黄金比例) 2
            capacity += capacity >> 1; // 右移一位表示除以2
            // 新建数组并传入初始数组元素
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0,
                    newArray, 0, size);
            // 新数组取代旧数组
            array = newArray;
        }
    }

    /**
     * 随机位置添加元素
     * @param index 插入索引
     * @param element 插入元素
     */
    public void add(int index, int element) {
        // 容量检查
        checkAndGrow();
        /*
          当index < size时，执行if语句，进行数组元素位置移动
          当index == size时，相当于向最后添加元素，直接执行其它语句即可
         */
        if(index >= 0 && index < size) {
            System.arraycopy(array, index, array,index + 1,size - index);
            // 如下方法作用相当于arraycopy
            /*for (int i = size; i >= index; i--) {
                array[i + 1] = array[i];
            }*/
        }
        array[index] = element;
        size ++;
    }

    /**
     * 头部插入元素
     * @param element 插入元素
     */
    public void addFirst(int element) {
        add(0, element);
    }

    // 尾部插入元素，符合add方法的index == size情况，直接调用add方法即可！
    /**
     * 尾部插入元素
     * @param element 插入元素
     */
    public void addLast(int element) {
        add(size, element);
    }

    /**
     * 随机位置删除元素
     * @param index 删除元素的索引
     * @return 删除的元素
     */
    public int remove(int index) {
        if(index >= 0 && index < size) {
            int removed = array[index];
            // 除了删除最后一个元素都要向前移动，最后一个元素直接删除即可，无需移动！
            if(index < size - 1) {
                System.arraycopy(array, index + 1, array, index, size - index - 1);
            }
            size --;
            return removed;
        } else {
            throw new IndexOutOfBoundsException("Index out of Array");
        }
    }

    /**
     * 删除头部元素
     */
    public void removeFirst(){
        remove(0);
    }

    /**
     * 删除尾部元素
     */
    public void removeLast(){
        remove(size - 1); // 注意删除最后一个元素的索引是size - 1！
    }

    /**
     * 依据索引修改元素
     * @param index 修改元素的索引
     * @param updateElement 修改之后的元素
     */
    public void update(int index, int updateElement) {
        if(index >= 0 && index < size) {
            array[index] = updateElement;
        } else {
            throw new IndexOutOfBoundsException("Index out of Array");
        }
    }

    /**
     * 从左/右侧起始修改第一个指定元素（有序数组！）
     * @param position 0：左起 1：右起
     * @param element 要修改的元素
     * @param updateElement 修改后的元素
     */
    public void updatePositionForOrder(int position, int element, int updateElement) {
        // 二分查找Leftmost/Rightmost
        int i = 0, j = array.length - 1;
        while(i <= j){
            int m = (i+j) >>> 1;
            if (position == 0) {
                if (element <= array[m]) { // 目标在中间值左边或找到目标值
                    j = m - 1;
                } else { // 目标在中间值右边
                    i = m + 1;
                }
                array[i] = updateElement;
            } else if (position == 1) {
                if(element < array[m]){ // 目标在中间值左边
                    j = m - 1;
                } else { // 目标在中间值右边或找到目标值
                    i = m + 1;
                }
                array[i - 1] = updateElement;
            } else {
                throw new IllegalArgumentException
                    ("Invalid parameter:'position'(The first parameter can only be assigned 0 or 1)");
            }
        }
    }

    /**
     * 从左/右侧起始修改第一个指定元素
     * @param position 0：左起 1：右起
     * @param element 要修改的元素
     * @param updateElement 修改后的元素
     */
    public void updatePosition(int position, int element, int updateElement) {
        if (position == 0) {
            IntStream.range(0, size)
                    .filter(index -> array[index] == updateElement)
                    .findFirst()
                    .ifPresent(index -> array[index] = updateElement);
        } else if (position == 1) {
            IntStream.range(0, size)
                    .filter(index -> array[index] == updateElement)
                    .reduce((first, second) -> second)
                    .ifPresent(index -> array[index] = updateElement);
        } else {
            throw new IllegalArgumentException
                    ("Invalid parameter:'position'(The first parameter can only be assigned 0 or 1)");
        }
    }

    /**
     * 修改全部指定元素
     * @param element 要修改的元素
     * @param updateElement 修改后的元素
     */
    public void updateAllChoose(int element, int updateElement) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == element) {
                array[i] = updateElement;
            }
        }
    }

    /**
     * 由索引获取单个元素
     * @param index 获取元素的索引
     * @return 索引对应的元素
     */
    public int getElement(int index) {
        if(index < 0 || index > capacity) {
            throw new IndexOutOfBoundsException("Index out of Array");
        }
        return array[index];
    }

    /**
     * 获取单个元素的所有索引位置
     * @param element 获取的元素
     * @return 一个集合，升序存储元素对应的索引
     */
    public List<Integer> getIndex(int element) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            if(array[i] == element) {
//                list.add(i);
//            }
//        }
//        return list;
        return IntStream.range(0, array.length)
                .filter(i -> array[i] == element)
                .boxed()
                .toList();
    }

    /**
     * 循环执行传入操作，传入Consumer接口，接收一个Integer类型的参数
     * @param consumer 传入操作
     */
    public void foreach(Consumer<Integer> consumer) { // 改为小写避免和Iterable接口中的方法重名
        for (int i = 0; i < size; i++) {
            // accept方法会接收传入的操作，同时将accept中的参数值返回给Integer类型的参数
            consumer.accept(array[i]);
        }
        System.out.println();
    }

    /**
     * 遍历1，实现Iterable接口的Iterator方法并给予泛型，在类外便可使用增强for循环
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            // 设置指针
            int i = -1; // 指针指向第一个元素之前

            @Override
            public boolean hasNext() { // 有没有下一个元素
                return i < size - 1;
            }

            @Override
            public Integer next() { // 返回当前元素,并将指针移动到下一个元素
                return array[++i]; // 注意是前++，先自增再返回元素
            }
        };
    }

    /**
     * 遍历2，stream流遍历
     */
    public IntStream stream() {
        // 以流的方式返回数据
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
            // copyOfRange方法会截取传入数组array的[0,size)范围的元素
    }

    /**
     * 调用该方法可以将两个升序数组指定范围内的元素合并到新的数组中，并保持升序
     * @param a1 第一个数组
     * @param i1 第一个数组起始范围索引
     * @param j1 第一个数组结束范围索引
     * @param a2 第二个数组
     * @param i2 第二个数组起始范围索引
     * @param j2 第二个数组结束范围索引
     * @return 新的升序数组
     */
    public int[] merge(int[] a1, int i1, int j1, int[] a2, int i2, int j2) {
        int k = 0;
        int[] a3 = new int[j1 + j2 - i1 - i2 + 2];
        while (i1 <= j1 && i2 <= j2) {
            if(a1[i1] < a2[i2]) {
                a3[k] = a1[i1];
                i1 ++;
            } else {
                a3[k] = a2[i2];
                i2 ++;
            }
            k ++;
        }
        if(i1 > j1) {
            System.arraycopy(a2, i2, a3, k, j2 - i2 + 1);
        }
        if(i2 > j2) {
            System.arraycopy(a1, i1, a3, k, j1 - i1 + 1);
        }
        return a3;
    }

    /*
      插入或删除性能
          头部位置，时间复杂度是 O(n)
          中间位置，时间复杂度是 O(n)
          尾部位置，时间复杂度是 O(1)（均摊来说）
     */

}
