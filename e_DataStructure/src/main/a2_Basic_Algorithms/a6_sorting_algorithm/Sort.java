package main.a2_Basic_Algorithms.a6_sorting_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ClassName: Sort
 * Package: main.a2_Basic_Algorithms.a6_sorting_algorithm
 * Description:各种排序算法
 *
 * @Author: zgh296
 * @Create: 2023/6/26 - 17:33
 * @Version: v1.0
 */
public class Sort {

    /*
    补：稳定 & 不稳定
        稳定：排序数列中有相同的元素，排序前后这些元素次序不变，称为稳定排序
            例：数列中有两个5, -> 1,5,3,5,7 -> 排序后，第一个5还在第二个5前面，便是稳定排序
        不稳定：反之
     */

    // 一、基于比较的排序算法
    /*
     算法    最好      最坏       平均      空间    稳定  思想  注意事项
     冒泡    O(n)     O(n^2)    O(n^2)    O(1)     Y   比较  最好情况需要额外判断
     选择   O(n^2)    O(n^2)    O(n^2)    O(1)     N   比较  交换次数一般少于冒泡
      堆   O(nlogn)  O(nlogn)  O(nlogn)   O(1)     N   选择  堆排序的辅助性较强，理解前先理解堆的数据结构
     插入    O(n)     O(n^2)    O(n^2)    O(1)     Y   比较  插入排序对于近乎有序的数据处理速度比较快，复杂度有所下降，可以提前结束
     希尔  O(nlogn)   O(n^2)   O(nlogn)   O(1)     N   插入  gap序列的构造有多种方式，不同方式处理的数据复杂度可能不同
     归并  O(nlogn)  O(nlogn)  O(nlogn)   O(n)     Y   分治  需要额外的O(n)的存储空间
     快速  O(nlogn)   O(n^2)   O(nlogn)  O(logn)   N   分治  快排可能存在最坏情况，需要把枢轴值选取得尽量随机化来缓解最坏情况下的时间复杂度
     */

    /**
     * <h3>冒泡排序</h3>
     * @param a 待排序的数组
     */
    public static void bubbleSort(int[] a) {
        /*
         * 每轮冒泡不断地比较相邻的两个元素，如果它们是逆序的，则交换它们的位置
         * 下一轮冒泡，可以调整未排序的右边界，减少不必要比较
         * 优化手段：每次循环时，若能确定更合适的右边界，则可以减少冒泡轮数
         */
        /*优化方案1：确定每次排序好的右边界a.length - i，之后的循环不再判断右侧元素
        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }*/
        // 优化方案2：确定更合适的无序右边界x，即x索引右侧的元素均以排序好，之后遍历到索引x停止即可
        // 此处为了提升效率，可以判断数组长度是否小于10000，从而使用递归
        for (int j = a.length - 1; j != 0;) {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    int t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    x = i;
                }
            }
            j = x;
        }
    }



    /**
     * <h3>递归冒泡排序内部实现</h3>
     * @param a 待排序的数组
     * @param j 数组右边界索引
     */
    private static void bubbleSort(int[] a, int j) {
        if(j == 0) {
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {
            if(a[i] > a[i + 1]){
                int t = a[i];
                a[i] = a[i + 1];
                a[i + 1] = t;
                x = i;
            }
        }
        bubbleSort(a, x);
    }

    /**
     * <h3>递归冒泡排序</h3>
     * 注：递归虽然效率极高，但存在爆栈可能，因此数组长度设置为10000以内！
     * @param a 待排序的数组
     */
    public static void bubbleSortRecursion(int[] a) {
        bubbleSort(a, a.length - 1);
    }



    /**
     * <h3>选择排序</h3>
     * @param a 待排序的数组
     */
    public static void selectionSort(int[] a) {
        // 每一轮选择，找出最大（最小）的元素，并把它交换到合适的位置
        // 1. 选择轮数 a.length - 1
        // 2. 交换的索引位置(right) 初始 a.length - 1, 每次递减
        for (int right = a.length - 1; right > 0 ; right--) {
            /*
                假定最右侧元素最大，遍历其余元素并将它们和最右侧元素比较，若大于最右侧元素，则修改最大值指向，
            将其指定为最大值max，之后继续向后遍历判断，不断修改max的值，直至遍历结束后找到数组中最大的max
             */
            int max = right;
            for (int i = 0; i < right; i++) {
                if (a[i] > a[max]) {
                    max = i;
                }
            }
            // 经过上面的遍历，已经找到数组元素最大值max，将其与最右侧元素交换位置即可，之后进行新一轮外层循环
            if(max != right) {
                int t = a[max];
                a[max] = a[right];
                a[right] = t;
            }
        }
    }


    
    /**
     * <h3>堆排序</h3>
     * @param a 待排序的数组
     */
    public static void heapSort(int[] a) {
        /*
         * 建立大顶堆
         * 每次将堆顶元素（最大值）交换到末尾，调整堆顶元素，让它重新符合大顶堆特性
         */
        heapify(a, a.length);
        // 堆排序：先将堆顶元素（最大值）交换到末尾，再调整堆顶元素，依次循环，最后得到的就是顺序数组了
        for (int right = a.length - 1; right > 0; right--) {
            heapSwap(a, 0, right);
            down(a, 0, right);
        }
    }

    /**
     * <h3>建堆操作(将指定数组转换为堆)</h3>
     * @param a 用于建堆的数组
     * @param size 数组元素数量，即堆大小
     */
    private static void heapify(int[] a, int size) {
        /*
        建堆操作思路：
            1.找到最后一个非叶子节点(最右下方的非叶子节点)，其在数组中的索引为  size / 2 - 1
            2.对其执行下潜操作。下潜：交换父节点(此处为找到的非叶子节点)和其两个孩子中较大的那个两者的位置，
        之后，
            3.循环操作，直至下潜完成所有的非叶子节点(最后的非叶子节点应是根节点)
         */
        // 如何找到最后这个非叶子节点  size / 2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(a, i, size);
        }
    }

    /**
     * <h3>下潜操作</h3>
     * @param a 存储堆元素的数组
     * @param parent 指定下潜的索引位置，即最后一个非叶子节点
     * @param size 数组元素数量，即堆大小
     */
    private static void down(int[] a, int parent, int size) {
        // 将 parent 索引处的元素下潜: 与两个孩子较大者交换, 直至没孩子或孩子没它大
        while (true) {
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
            if (left < size && a[left] > a[max]) {
                max = left;
            }
            if (right < size && a[right] > a[max]) {
                max = right;
            }
            // 没找到更大的孩子
            if (max == parent) {
                break;
            }
            // 找到更大的孩子，交换两者的值，继续让max索引处充当父节点继续循环判断是否下潜
            heapSwap(a, max, parent);
            parent = max;
        }
    }

    /**
     * <h3>交换数组两个索引处的元素</h3>
     * @param a 指定的数组
     * @param i 第一个索引
     * @param j 第二个索引
     */
    private static void heapSwap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }



    /**
     * <h3>插入排序</h3>
     * @param a 待排序的数组
     */
    public static void insertionSort(int[] a) {
        for (int low = 1; low < a.length; low++) {
            // 未排序区域第一个元素t
            int t = a[low];
            // 已排序区域指针i
            int i = low - 1;
            // 自右向左找插入位置，若比待插入元素大，则不断右移，空出插入位置
            while(i >= 0 && a[i] > t) {
                // 空出插入位置
                a[i + 1] = a[i];
                i--;
            }
            /*
                找到插入位置，如果i + 1 = low说明上述while循环未进行，表示这一轮递归未排序区域的元素t的
            位置正好可以排在已排序区域的后面，此时不用再给a[i + 1]赋值了，因为a[i + 1]位置就是元素t本身
                例：       low
                           |
                        3  4  1   此时a[i + 1] = t，直接将4排在已排序区域3的后面即可，不用重新赋值
                        |  |
                        i  t
             */
            if(i + 1 != low) {
                a[i + 1] = t;
            }
        }
    }



    /**
     * <h3>递归实现插入排序</h3>
     * @param a 待排序的数组
     * @param low 左边界(包含)
     * @param high 右边界(不包含)
     */
    private static void insertionSort(int[] a, int low, int high) {
        /*
         * 将数组分为两部分[0 .. low-1]  [low .. a.length-1]
         *     左边[0 .. low-1]是已排序部分
         *     右边[low .. a.length-1]是未排序部分
         * 每次从未排序区域取出low位置的元素, 插入到已排序区域
         */
        // 递归结束条件
        if(low == high) {
            return;
        }
        // 未排序区域第一个元素t
        int t = a[low];
        // 已排序区域指针i
        int i = low - 1;
        // 自右向左找插入位置，若比待插入元素大，则不断右移，空出插入位置
        while(i >= 0 && a[i] > t) {
            // 空出插入位置
            a[i + 1] = a[i];
            i--;
        }
        /*
            找到插入位置，如果i + 1 = low说明上述while循环未进行，表示这一轮递归未排序区域的元素t的
        位置正好可以排在已排序区域的后面，此时不用再给a[i + 1]赋值了，因为a[i + 1]位置就是元素t本身
            例：       low
                       |
                    3  4  1   此时a[i + 1] = t，直接将4排在已排序区域3的后面即可，不用重新赋值
                    |  |
                    i  t
         */
        if(i + 1 != low) {
            a[i + 1] = t;
        }
        insertionSort(a, low + 1, high);
    }

    /**
     * <h3>递归插入排序</h3>
     * @param a 待排序的数组
     */
    public static void insertionSortRecursion(int[] a) {
        insertionSort(a, 1, a.length);
    }



    /**
     * <h3>希尔排序</h3>
     * @param a 待排序的数组
     */
    public static void shellSort(int[] a) {
        /*
         * 简单的说，就是分组实现插入，每组元素间隙称为 gap
         * 每轮排序后 gap 逐渐变小，直至 gap 为 1 完成排序
         * 对插入排序的优化，让元素更快速地交换到最终位置
         */
        for (int gap = a.length >> 1; gap >= 1; gap = gap >> 1) {
            // 其实就是将插入排序所有的1都变成gap，1表示不分组直接排序，gap表示分组后排序
            for (int low = gap; low < a.length; low++) {
                // 未排序区域第一个元素t
                int t = a[low];
                // 已排序区域指针i
                int i = low - gap;
                // 自右向左找插入位置，若比待插入元素大，则不断右移，空出插入位置
                while(i >= 0 && a[i] > t) {
                    a[i + gap] = a[i];
                    i -= gap;
                }
                // 找到插入位置
                // if(i + gap != low) {
                    a[i + gap] = t;
                // }
            }
        }
    }



    /**
     * <h3>归并排序 - 自上至下</h3>
     * @param a1 待排序的数组
     */
    public static void mergeSortTopDown(int[] a1) {
        int[] a2 = new int[a1.length];
        mergeSplit(a1, 0, a1.length - 1, a2);
    }

    /**
     * <h3>归并排序合并有序数组</h3>
     * @param a1 原始数组
     * @param i 原始数组第一个索引起始范围
     * @param iEnd 原始数组第一个索引结束范围
     * @param j 原始数组第二个索引起始范围
     * @param jEnd 原始数组第二个索引结束范围
     * @param a2 临时数组(大小等同于原始数组)
     */
    private static void mergeSort(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;
        // 从相同起始位置比较两个数组元素大小，依次添加到临时数组a2中
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        // 若两个范围中有一个范围元素已经比较完成，则直接将另一范围的元素全部添加到临时数组a2中
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }

    /**
     * <h3>归并排序分治方法</h3>
     * @param a1 待排序的数组
     * @param left 左索引边界
     * @param right 右索引边界
     * @param a2 临时数组，用于存储两个指定范围元素的排序结果，用于mergeTopDown方法
     */
    private static void mergeSplit(int[] a1, int left, int right, int[] a2) {
        /*
         * 分 - 每次从中间切一刀，处理的数据少一半
         * 治 - 当数据仅剩一个时可以认为有序
         * 合 - 两个有序的结果，可以进行合并排序
         */
        /*输出测试
        int[] a = Arrays.copyOfRange(a1, left, right + 1);
        System.out.println(Arrays.toString(a));*/
        // 2. 治
        if (left == right) {
            return;
        }
        // 1. 分
        // 中间点m
        int m = (left + right) >>> 1;
        // 左侧数据
        mergeSplit(a1, left, m, a2);
        // 右侧数据
        mergeSplit(a1, m + 1, right, a2);
        // 3. 合
        //
        mergeSort(a1, left, m, m + 1, right, a2);
        System.arraycopy(a2, left, a1, left, right - left + 1);
    }



    /**
     * <h3>归并排序 - 自下至上</h3>
     * @param a1 待排序的数组
     */
    public static void mergeSortBottomUp(int[] a1) {
        int n = a1.length;
        int[] a2 = new int[n];
        /*
        思路：将排序的区间设置为1，每次扩大一倍，扩大前对区间元素进行排序
         */
        /*
            width：每次排序时待排序的有序区间宽度依次为1,2,4,8...
            例：3 2 4 9 6 7 0 1
                width = 1时待排序区间分别为 3 2 / 4 9 / 6 7 / 0 1 -> 2 3 / 4 9 / 6 7 / 0 1
                    此时的有序区间就是每个元素，之后调用mergeBottomUp方法进行排序
                width = 2时待排序区间分别为 2 3 4 9 / 6 7 0 1 -> 2 3 4 9 / 0 1 6 7
                    此时的有序区间即是两个元素一组
                width = 4时待排序区间分别为 2 3 4 9 0 1 6 7 -> 0 1 2 3 4 6 7 9
                    此时的有序区间即是四个元素一组
         */
        for (int width = 1; width < n; width = width << 1) {
            // 表示合并区间的左右边界[left , right]
            for (int left = 0; left < n; left += width << 1) {
                // 中间值
                int m = Integer.min(left + width - 1, n - 1);
                int right = Integer.min(left + 2 * width - 1, n - 1);
                // System.out.println(left + " " + m + " " + j);
                // 合并排序左右区间元素
                mergeSort(a1, left, m, m + 1, right, a2);
            }
            System.arraycopy(a2, 0, a1, 0, n);
        }
    }



    /**
     * <h3>归并排序 - 插入排序</h3>
     * @param a1 待排序的数组
     */
    public static void mergeInsertionSort(int[] a1) {
        int[] a2 = new int[a1.length];
        mergeInsertionSplit(a1, 0, a1.length - 1, a2);
    }

    /**
     * <h3>归并排序 - 插入排序分治方法</h3>
     * @param a1 待排序的数组
     * @param left 左索引边界
     * @param right 右索引边界
     * @param a2 临时数组，用于存储两个指定范围元素的排序结果，用于mergeTopDown方法
     */
    private static void mergeInsertionSplit(int[] a1, int left, int right, int[] a2) {
        /*
         * 小数据量且有序度高时，插入排序效果高
         * 大数据量用归并效果好
         * 可以结合二者
         */
        // 2. 治
        if (right == left) {
            return;
        }
        if (right - left <= 32) {
            insertionSortForMerge(a1, left, right);
            // System.out.println("insert..." + left + " " + right +" "+Arrays.toString(a1));
            return;
        }
        // 1. 分
        int m = (left + right) >>> 1;
        mergeInsertionSplit(a1, left, m, a2);
        mergeInsertionSplit(a1, m + 1, right, a2);
        // System.out.println(left + " " + right + " "+ Arrays.toString(a1));
        // 3. 合
        mergeSort(a1, left, m, m + 1, right, a2);
        System.arraycopy(a2, left, a1, left, right - left + 1);
    }

    /**
     * <h3>用于归并排序的插入排序</h3>
     * @param a 待排序的数组
     * @param left 左边界(包含)
     * @param right 右边界(包含)
     */
    private static void insertionSortForMerge(int[] a, int left, int right) {
        for (int low = left + 1; low <= right; low++) {
            int t = a[low];
            int i = low - 1;
            while (i >= left && t < a[i]) {
                a[i + 1] = a[i];
                i--;
            }
            if (i != low - 1) {
                a[i + 1] = t;
            }
        }
    }



    /**
     * <h3>快速排序 - 单边循环</h3>
     * @param a 待排序的数组
     */
    public static void quickSortLomuto(int[] a) {
        quickSortLomuto(a, 0, a.length - 1);
    }

    /**
     * <h3>快速排序 - 单边循环递归方法</h3>
     * @param a 待排序的数组
     * @param left 左侧索引边界
     * @param right 右侧索引边界
     */
    private static void quickSortLomuto(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        // 基准点索引
        int p = quickLomutoPartition(a, left, right);
        quickSortLomuto(a, left, p - 1);
        quickSortLomuto(a, p + 1, right);
    }

    /**
     * <h3>快速排序 - 单边循环查找基准点与分区</h3>
     * @param a 待排序的数组
     * @param left 左侧索引边界
     * @param right 右侧索引边界
     */
    private static int quickLomutoPartition(int[] a, int left, int right) {
        /*
        单边循环(lomuto分区)要点
            选择最右侧元素作为基准点
            j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
                交换时机：j 找到小的，且与 i 不相等
                i 找到 >= 基准点元素后，不应自增
            最后基准点与 i 交换，i 即为基准点最终索引
         */
        // 基准点pv
        int pv = a[right];
        // 两个指针i、j从左向右查找
        // 找比基准点大的i
        int i = left;
        // 找比基准点小的j
        int j = left;
        while (j < right) {
            // 找到比基准点小的元素了
            if (a[j] < pv) {
                if (i != j) {
                    quickSwap(a, i, j);
                }
                // i自增时，表示没找到比基准点大的元素，因此应在if内
                i++;
            }
            j++;
        }
        // 最后交换基准点和i的位置
        quickSwap(a, i, right);
        return i;
    }

    /**
     * <h3>快速排序 - 单边循环交换元素位置</h3>
     * @param a 待交换元素位置的数组
     * @param i 元素1
     * @param j 元素2
     */
    private static void quickSwap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }



    /**
     * <h3>快速排序 - 双边循环</h3>
     * @param a 待排序的数组
     */
    public static void quickSortHoare(int[] a) {
        quickSortHoare(a, 0, a.length - 1);
    }

    /**
     * <h3>快速排序 - 双边循环递归方法</h3>
     * @param a 待排序的数组
     * @param left 左侧索引边界
     * @param right 右侧索引边界
     */
    private static void quickSortHoare(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = quickHoarePartition(a, left, right);
        quickSortHoare(a, left, p - 1);
        quickSortHoare(a, p + 1, right);
    }

    /**
     * <h3>快速排序 - 双边循环查找基准点与分区</h3>
     * @param a 待排序的数组
     * @param left 左侧索引边界
     * @param right 右侧索引边界
     */
    private static int quickHoarePartition(int[] a, int left, int right) {
        /*
        双边循环要点
            选择最左侧元素作为基准点
            j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
                i 从左向右
                j 从右向左
            最后基准点与 i 交换，i 即为基准点最终索引
         */
        // 基准点pv
        int pv = a[left];
        // i、j两个指针分别从左、右向两侧查找，到i遇到j时说明已经走了一遍
        // 找比基准点大的i
        int i = left;
        // 找比基准点小的j
        int j = right;
        while (i < j) {
            // 1.j从右向左找小的
            while (i < j && a[j] > pv) {
                j--;
            }
            // 2.i从左向右找大的
            while (i < j && pv >= a[i]) {
                i++;
            }
            // 3.找到对应i、j后交换位置
            quickSwap(a, i, j);
        }
        quickSwap(a, left, j);
        return j;
    }



    /**
     * <h3>快速排序 - 双边循环方法优化：随机基准点</h3>
     * @param a 待排序的数组
     */
    public static void quickSortHandleDuplicate(int[] a) {
        quickSortHandleDuplicate(a, 0, a.length - 1);
    }

    /**
     * <h3>快速排序 - 双边循环递归方法优化</h3>
     * @param a 待排序的数组
     * @param left 左侧索引边界
     * @param right 右侧索引边界
     */
    private static void quickSortHandleDuplicate(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = quickHandleDuplicatePartition(a, left, right);
        quickSortHandleDuplicate(a, left, p - 1);
        quickSortHandleDuplicate(a, p + 1, right);
    }

    /**
     * <h3>快速排序 - 双边循环随机基准点与分区</h3>
     * @param a 待排序的数组
     * @param left 左侧索引边界
     * @param right 右侧索引边界
     */
    private static int quickHandleDuplicatePartition(int[] a, int left, int right) {
         /*
            1.循环
                循环内
                    i 从 left + 1 开始，从左向右找大的或相等的
                    j 从 right 开始，从右向左找小的或相等的
                    交换，i++ j--
                循环外 j 和 基准点交换，j 即为分区位置
            2.核心思想是
                改进前，i 只找大于的，j 会找小于等于的。一个不找等于、一个找等于，势必导致等于的值分布不平衡
                改进后，二者都会找等于的交换，等于的值会平衡分布在基准点两边
            3.细节：
                因为一开始 i 就可能等于 j，因此外层循环需要加等于条件保证至少进入一次，让 j 能减到正确位置
                内层 while 循环中 i <= j 的 = 也不能去掉，因为 i == j 时也要做一次与基准点的判断，好让 i 及 j 正确
                i == j 时，也要做一次 i++ 和 j-- 使下次循环二者不等才能退出
                因为最后退出循环时 i 会大于 j，因此最终与基准点交换的是 j
        */
        // 改进1：获取随机数并作为左侧基准点
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
            // nextInt(right - left + 1) + left取值范围：[0 , right - left] + left
        quickSwap(a, left, idx);
        int pv = a[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            // 优化2：当a[i] == a[j] == pv时，也交换两者元素，同时i、j均再移动一次，平衡分区
            // i 从左向右找大的或者相等的
            while (i <= j && a[i] < pv) {
                i++;
            }
            // j 从右向左找小的或者相等的
            while (i <= j && a[j] > pv) {
                j--;
            }
            if (i <= j) {
                /*此处加if判断不交换两者的值减少swap操作，但if语句也占用执行时间，因此加了if语句反而执行效率降低
                if (a[i] != a[j]) {
                    quickSwap(a, i, j);
                }*/
                quickSwap(a, i, j);
                i++;
                j--;
            }
        }
        // 注意此时应该是j和基准点交换
        quickSwap(a, j, left);
        return j;
    }



    // 二、非比较排序算法
    /*
     算法    时间复杂度   空间复杂度   稳定性
     计数     O(n+k)      O(n+k)     稳定
      桶      O(n+k)      O(n+k)     稳定
     基数   O(d*(n+k))    O(n+k)     稳定
          n 是数组长度
          k 是桶长度
          d 是基数位数
     */

    /**
     * <h3>计数排序</h3>
     * @param a 待排序的数组
     */
    public static void countSort(int[] a) {
        /*
        思路：
            1.找到最大值最小值，创建一个大小为max - min + 1的count数组
            2.count数组索引顺序依次对应原始数组的元素，用于统计该元素出现的次数
            3.遍历count数组，根据count数组的索引以及出现次数，生成排序后的内容
        例：原数组a：3 1 1 5 3 2
            1.找到原始数组最大值max，min
            2.创建新数组count，长度为max - min + 1
            3.遍历原始数组a，将其元素出现次数对应到新数组count的索引位置
                count[0, 1, 2, 3, 4] = 2, 1, 2, 0, 1
            4.遍历count数组，替换原始数组a
        注：待排序的数组不能太大，且数组内每两个元素差值不能过大！
         */
        int min = a[0];
        int max = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            } else if (i < min) {
                min = i;
            }
        }
        int[] count = new int[max - min + 1];
        for (int i : a) {
            /*增强for循环拆分示例：
            for (int j = 0; j < a.length; j++) {
                i = a[j];
            }*/
            count[i - min]++;
        }
        int k = 0;
        // 将count数组遍历，遇到索引上有元素值n，就打印n个对应的索引替换数组a
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                // 将原始数组a中的元素替换为count中的元素
                a[k++] = i + min; // 此处a的元素和count索引不是相等而是顺序对应关系，因此要加上min！
                count[i]--; // count[i]相当于元素n，每次打印后都要- 1，直到n = 0，再打印之后的索引
            }
        }
    }



    /**
     * <h3>稳定计算排序</h3>
     * @param a 待排序的数组
     */
    public static void countSortStable(int[] a) {
        int min = a[0];
        int max = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            } else if (i < min) {
                min = i;
            }
        }
        int[] counting = new int[max - min + 1];
        for (int i : a) {
            counting[i - min]++;
        }
        for (int i = 1; i < counting.length; i++) {
            counting[i] = counting[i] + counting[i - 1];
        }
        int[] b = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            int j = a[i] - min;
            counting[j]--;
            b[counting[j]] = a[i];
        }
        System.arraycopy(b, 0, a, 0, a.length);
    }



    /**
     * <h3>桶排序</h3>
     * @param a 待排序的数组
     */
    public static void bucketSort(int[] a) {
        /*
        桶排序的基本思想是：把数组 a 划分为n个大小相同子区间（桶），每个子区间各自排序，最后合并。
        计数排序是桶排序的一种特殊情况，可以把计数排序当成每个桶里只有一个元素的情况。
            1.找出待排序数组中的最大值max、最小值min
            2.使用动态数组 ArrayList 作为桶，桶里放的元素也用 ArrayList 存储。桶的数量为(max - min) / a.length + 1
            3.遍历数组 a，计算每个元素 a[i] 放的桶
            4.每个桶各自排序
            5.遍历桶数组，把排序好的元素放进输出数组
         */
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int k : a) {
            max = Math.max(max, k);
            min = Math.min(min, k);
        }
        //桶数
        int bucketNum = (max - min) / a.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<>());
        }
        //将每个元素放入桶
        for (int j : a) {
            int num = (j - min) / (a.length);
            bucketArr.get(num).add(j);
        }
        //对每个桶进行排序
        for (ArrayList<Integer> integers : bucketArr) {
            Collections.sort(integers);
        }
    }



    /**
     * <h3>基数排序</h3>
     * @param a 待排序的数组
     * @param length 数组中字符串位数
     */
    @SuppressWarnings ("unchecked")
    public static void radixSort(String[] a, int length) {
        /*
            基数排序是按每个元素位由低到高排序，先按各位排序，将每个元素以个位数字为标准，添加到对应的桶中，
        之后遍历桶元素，覆盖原始数组，之后，按十位继续排序，依次类推直至全部位排序完成。
            注：基数排序适用于较长字符串的排序，例如工号、学号、电话等非负且等长的字符串数据
         */
        // 1.准备桶
        // 若仅排序数字，则桶大小可以设置为10，若还要排序其它字符串，可以扩大桶容量
        ArrayList<String>[] buckets = new ArrayList[128];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        // i表示字符串位数，开始从索引最大位(即个位)开始排序
        for (int i = length - 1; i >= 0 ; i--) {
            // 将原始数组元素按位添加到对应的桶
            for (String s : a) {
                // 注意减去对应ASCII码 '0' -> 48
                buckets[s.charAt(i) - '0'].add(s);
            }
            // 添加完成后，遍历桶，覆盖原始数组，继续进行新一轮的位排序
            int k = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k++] = s;
                }
                // 当前桶元素已经无用，为进行下一轮排序，需要进行清空
                bucket.clear();
            }
        }
    }



    // 三、Java 中的排序
    /*
    Arrays.sort
    JDK 7~13 中的排序实现
    排序目标                        条件                                         采用算法
    int[] long[] float[] double[]  size < 47                                   混合插入排序 (pair)
                                   size < 286                                  双基准点快排
                                   有序度高                                     归并排序
                                   有序度低                                     双基准点快排
    byte[]                         size > 29                                   计数排序
                                   size <= 29                                  插入排序
    char[] short[]                 size > 3200                                 计数排序
                                   size < 47                                   插入排序
                                   size < 286                                  双基准点快排
                                   有序度高                                     归并排序
                                   有序度低                                     双基准点快排
    Object[]                       -Djava.util.Arrays.useLegacyMergeSort=true  传统归并排序
                                                                               TimSort
    JDK 14~20 中的排序实现
    排序目标                        条件                                         采用算法
    int[] long[] float[] double[]  size < 65 并不是最左侧                        混合插入排序 (pin)
                                   size < 44 并位于最左侧                        插入排序
                                   递归次数超过 384                              堆排序
                                   对于整个数组或非最左侧 size > 4096，有序度高     归并排序
                                   有序度低                                      双基准点快排
    byte[]                         size > 64                                    计数排序
                                   size <= 64                                   插入排序
    char[] short[]                 size > 1750                                  计数排序
                                   size < 44                                    插入排序
                                   递归次数超过 384                              计数排序
                                   不是上面情况                                  双基准点快排
    Object[]                       -Djava.util.Arrays.useLegacyMergeSort=true   传统归并排序
                                                                                TimSort
    注：其中 TimSort 是用归并+二分插入排序的混合排序算法
        值得注意的是从 JDK 8 开始支持 Arrays.parallelSort 并行排序(多线程排序，将数组拆分成多组供多个线程排序)
     */

    /**
     * <h3>Arrays.sort排序</h3>
     * @param a 待排序的数组
     */
    public static void sort(int[] a) {
        Arrays.sort(a);
    }

    /**
     * <h3>Arrays.parallelSort排序</h3>
     * @param a 待排序的数组
     */
    public static void parallelSort(int[] a) {
        // 多线程排序，将数组拆分成多组供多个线程排序
        Arrays.parallelSort(a);
    }



    /**
     * <h3>根据另一个数组次序排序，另一个数组没有的元素按大小排序</h3>
     * @param arr1 待排序的数组
     * @param arr2 规定的排序数组
     * @return 排序后的数组
     */
    public static int[] relativeSort(int[] arr1, int[] arr2) {
        // 使用计数排序
        int min = arr1[0];
        int max = arr1[0];
        for (int i : arr1) {
            if (i > max) {
                max = i;
            } else if (i < min) {
                min = i;
            }
        }
        int[] count = new int[max - min + 1];
        for (int i : arr1) {
            /*增强for循环拆分示例：
            for (int j = 0; j < a.length; j++) {
                i = a[j];
            }*/
            count[i - min]++;
        }
        // 创建新数组用于存储排序好的数组
        int[] result = new int[arr1.length];
        /*
        之下是对计数排序的改动：
            1.创建一个k，共享与arr2和count排序
            2.先要按照arr2数组指定的元素顺序排序，即按照arr2数组的元素找到count对应索引，优先输出
            3.按照arr2元素顺序排序完后，剩余的元素再进行计数排序
         */
        int k = 0;
        for (int i : arr2) {
            /*增强for循环拆分示例：
            for (int j = 0; j < a.length; j++) {
                i = arr2[j];
            }*/
            // 此处会出现负数，要用绝对值！
            while (count[Math.abs(i - min)] > 0) {
                result[k++] = i;
                count[i - min]--;
            }
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                result[k++] = i + min;
                count[i]--;
            }
        }
        return result;
    }

    /**
     * <h3>按出现频率排序，频率相同的元素按降序排序</h3>
     * @param nums 待排序的数组
     * @param range 数据范围[-range, range]
     * @return 排序后的数组
     */
    public static int[] frequencySort(int[] nums, int range) {
        // 用于计数的数组，正负空间对半分
        int[] count = new int[range * 2 + 1];
        // 统计元素出现的次数，存入对应索引
        for (int i : nums) {
            count[i + range]++;
        }
        // 设置比较器，按频率升序，频率相同按元素降序
        return Arrays.stream(nums).boxed().sorted((a, b) -> {
            // 获取两个参数的出现频率
            int fa = count[a + range];
            int fb = count[b + range];
            // 比较两者，相等则表示频率相同，按降序排序元素；不同则返回 1 / -1，传入compare方法
            if (fa == fb) {
                return Integer.compare(b, a);
            } else {
                return fa - fb;
            }
        }).mapToInt(Integer::intValue).toArray();
            /*
                mapToInt：封装类返回基本类型数据流
                toArray：将数据流转换为数组
             */

    }

}
