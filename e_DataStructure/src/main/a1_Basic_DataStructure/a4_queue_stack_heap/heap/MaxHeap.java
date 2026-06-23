package main.a1_Basic_DataStructure.a4_queue_stack_heap.heap;

import java.util.Arrays;

/**
 * ClassName: MaxHeap
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_heap.heap
 * Description:大顶堆
 *
 * @Author: zgh296
 * @Create: 2023/5/23 - 10:07
 * @Version: v1.0
 */
public class MaxHeap {

    // 弗洛伊德建堆算法
    // 1. 找到最后一个非叶子节点
    // 2. 从后向前，对每个节点执行下潜

    int[] array;
    int size;

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    /**
     * 获取堆大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取堆顶元素
     * @return 堆顶元素
     */
    public int peek() {
        if(size == 0) {
            throw new IndexOutOfBoundsException("堆为空！");
        }
        return array[0];
    }

    /**
     * 删除堆顶元素
     * @return 删除的堆顶元素
     */
    public int poll() {
        if(size == 0) {
            throw new IndexOutOfBoundsException("堆为空！");
        }
        /*
            1.记录之前的堆顶元素
            2.交换堆顶元素和最右侧叶子节点元素(就是交换数组首位元素)
            3.将数组长度- 1，即删除了最右侧的叶子结点(即删除了交换后的堆顶元素)
            4.将替换到堆顶的叶子节点做下潜操作
            5.返回删除的原始堆顶元素
         */
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    /**
     * 删除指定索引处元素
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index) {
        if(size == 0) {
            throw new IndexOutOfBoundsException("堆为空！");
        }
        // 该方法与删除堆顶元素原理一致
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    /**
     * 替换堆顶元素
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 堆的尾部添加元素
     * @param offered 新元素
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        // 将添加的元素做上浮操作
        up(offered);
        size++;
        return true;
    }

    /*
    堆结构示例：
      数组索引   元素位置               堆
         0        1                   7
                                  /       \
         1        2              5         \
         2        3            /   \        6
         3        4           4     \      / \
         4        5                  2    /   \
         5        6                      1     \
         6        7                             3
     */

    // 将 offered 元素上浮: 直至 offered 小于父元素或到堆顶
    private void up(int offered) {
        int child = size; // 此处的size是堆尾添加元素后还未更新的size，因此作为索引的话不用再- 1了！
        while (child > 0) {
            /*
                堆尾新增元素值：offered
                堆尾新增元素索引：child
                父节点索引：parent = (child - 1) / 2
             */
            int parent = (child - 1) / 2;
            /*
                1.若新增元素值大于其父节点元素值，则让孩子节点的值 = 父节点的值
                2.若新增元素值小于其父节点元素值，则表示不用上浮，退出循环即可
             */
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            } // 到此完成一次上浮
            /*
                一次上浮完成后，要更新子节点的索引值，原先父节点变为子节点，寻找父节点的父节点，比较两者
            的元素值(即继续上浮)
             */
            child = parent;
        }
        /*
            1.若没有进行上浮操作，则相当于数组直接新增一个元素并添加到数组尾(堆尾)
            2.若进行了上浮操作，则child最终表示上浮后合理的索引位置，直接让该位置的值变为新增值即可(因为上浮操作会
        将父节点的值赋值给孩子节点，因此不用考虑覆盖交换问题)
         */
        array[child] = offered;
    }

    // 建堆，时间复杂度O(n)
    private void heapify() {
        /*
        建堆操作思路：
            1.找到最后一个非叶子节点(最右下方的非叶子节点)，其在数组中的索引为  size / 2 - 1
            2.对其执行下潜操作。下潜：交换父节点(此处为找到的非叶子节点)和其两个孩子中较大的那个两者的位置，
        之后，
            3.循环操作，直至下潜完成所有的非叶子节点(最后的非叶子节点应是根节点)
         */
        // 如何找到最后这个非叶子节点  size / 2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    // 将 parent 索引处的元素下潜: 与两个孩子较大者交换, 直至没孩子或孩子没它大
    private void down(int parent) {
        /*
            父节点索引；parent
            左孩子索引：parent * 2 + 1
            右孩子索引：left + 1
            最大元素索引值：默认为父节点索引
         */
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent; // 记录最大者
        // 如下两种情况表示左孩子或右孩子比父节点大，则更新max值(注意判断父节点有没有孩子：left或right < size)
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        // 找到了更大的孩子，则交换父节点和更大孩子的位置，同时将max作为索引继续向下下潜，直到没有(更大)孩子
        if (max != parent) {
            swap(max, parent);
            // 注：swap只是交换了数组两个位置的值，max对应的索引不变，因此递归下潜的参数应该是max而不是parent！
            down(max);
        }
    }

    // 交换两个索引处的元素
    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * 堆排序
     * @param maxHeap 大顶堆
     * @return 排序后的大顶堆
     */
    public MaxHeap loop(MaxHeap maxHeap) {
        /*
            1. heapify 建立大顶堆
            2. 将堆顶与堆底交换（最大元素被交换到堆底），缩小并下潜调整堆
            3. 重复第二步直至堆里剩一个元素
            4.注意记录原始堆大小，循环后替换size
         */
        int size = maxHeap.size;
        while (maxHeap.size > 1) {
            maxHeap.swap(0, maxHeap.size - 1);
            maxHeap.size--;
            maxHeap.down(0);
        }
        maxHeap.size = size;
        return maxHeap;
    }

    public int[] heapToArray (MaxHeap maxHeap) {
        return this.array;
    }

}
