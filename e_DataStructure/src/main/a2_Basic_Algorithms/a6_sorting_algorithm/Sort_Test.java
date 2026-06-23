package main.a2_Basic_Algorithms.a6_sorting_algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ClassName: Sort_Test
 * Package: main.a2_Basic_Algorithms.a6_sorting_algorithm
 * Description:排序算法测试类
 *
 * @Author: zgh296
 * @Create: 2023/6/26 - 17:34
 * @Version: v1.0
 */
public class Sort_Test {

    /*
        1.所有排序测试数组大小均设置为50000，分最坏和随机两种情况，分别做四次测试，记录运行时间ms
        2.由于递归排序存在爆栈可能，因此数组长度设置为10000！
     */

    /*
    如下为所有排序最坏和随机两种情况四次运行时间的平均值记录：
           算法              最坏(ms)           平均，应除以3，此处保留原始数据(ms)
           冒泡              497.25             1916.75
           选择              693.25             665.75
            堆               6.5                5.5
           插入              295                166.75
           希尔              5.5                4.75
        归并自上至下          3                  6
        归并自下至上          5.5                5.75
          归并插入           3.5                4.5
        快速双边优化          5.5                6.5

           计数              2.25               0.75
         稳定计数            3                   1.75
            桶              8                   25.5
           基数             13.75               9.25

           sort            2.5                  18.25
       parallelSort        7.25                 14

         冒泡递归           28.25                43
         插入递归           21.5                 14.75
         快速单边           22.5                 1.75
         快速双边           19.5                 1.5

    按50000次随机数据平均比较(ms)：
        计数0.75 > 稳定计数1.75 > 归并插入4.5 > 希尔4.75 > 堆5.5 > 归并自下至上5.75 > 归并自上至下6 >
        快速双边优化6.5 > 基数9.25 > parallelSort14 > sort18.25 > 桶25.5 > 插入166.75 > 选择665.75 > 冒泡1916.75
            注：1.其中计数排序仅适用于小规模数据排序，基数排序适用于较长字符串数组排序
               2.因为数组随机赋值影响了执行效率，因此此处计算的所有平均执行时间应再次除以3！
     */

    // 测试数组赋值效率
    @Test
    public void test() {

        // 经测试后，随机赋值数组会占用三倍顺序赋值的时间，因此在计算平均时间时应再除以3！

        long timeStartA = System.currentTimeMillis();
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   ")); // 1ms
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }
        long timeEndB = System.currentTimeMillis();
        System.out.println("time:" + (timeEndB - timeStartB + "ms   ")); // 3ms

        System.out.print(c[0] + " " + d [0]);

    }

    // 冒泡排序
    @Test
    public void testBubbleSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.bubbleSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.bubbleSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.bubbleSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：534ms / 485ms / 482ms / 488ms

        long timeStartD = System.currentTimeMillis();
        Sort.bubbleSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：1861ms / 1990ms / 2036ms / 1780ms (为什么慢了3.5倍？)

    }

    // 递归冒泡排序
    @Test
    public void testBubbleSortRecursion() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[10000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[10000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.bubbleSortRecursion(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.bubbleSortRecursion(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.bubbleSortRecursion(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 递归排序存在爆栈问题，因此数组长度设置为10000！
            // 最坏情况：29ms / 28ms / 27ms / 29ms

        long timeStartD = System.currentTimeMillis();
        Sort.bubbleSortRecursion(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：43ms / 43ms / 42ms / 44ms

    }

    // 选择排序
    @Test
    public void testSelectionSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.selectionSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.selectionSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.selectionSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：678ms / 711ms / 682ms / 702ms

        long timeStartD = System.currentTimeMillis();
        Sort.selectionSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：667ms / 659ms / 661ms / 676ms

    }

    // 堆排序
    @Test
    public void testHeapSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.heapSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.heapSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.heapSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：7ms / 7ms / 6ms / 6ms

        long timeStartD = System.currentTimeMillis();
        Sort.heapSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：5ms / 6ms / 5ms / 6ms

    }

    // 插入排序
    @Test
    public void testInsertionSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.insertionSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.insertionSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.insertionSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：286ms / 294ms / 292ms / 308ms

        long timeStartD = System.currentTimeMillis();
        Sort.insertionSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：165ms / 166ms / 169ms / 167ms

    }

    // 递归插入排序
    @Test
    public void testInsertionSortRecursion() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[10000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[10000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.insertionSortRecursion(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.insertionSortRecursion(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.insertionSortRecursion(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 递归排序存在爆栈问题，因此数组长度设置为10000！
            // 最坏情况：22ms / 22ms / 21ms / 21ms

        long timeStartD = System.currentTimeMillis();
        Sort.insertionSortRecursion(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：14ms / 14ms / 15ms / 16ms

    }

    // 希尔排序
    @Test
    public void testShellSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.shellSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.shellSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.shellSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：6ms / 5ms / 6ms / 5ms

        long timeStartD = System.currentTimeMillis();
        Sort.shellSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：5ms / 5ms / 4ms / 5ms

    }

    // 归并排序 - 自上至下
    @Test
    public void testMergeSortTopDown() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.mergeSortTopDown(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.mergeSortTopDown(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.mergeSortTopDown(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：3ms / 3ms / 3ms / 3ms

        long timeStartD = System.currentTimeMillis();
        Sort.mergeSortTopDown(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：6ms / 6ms / 6ms / 6ms

    }

    // 归并排序 - 自下至上
    @Test
    public void testMergeSortBottomUp() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.mergeSortBottomUp(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.mergeSortBottomUp(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.mergeSortBottomUp(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：5ms / 5ms / 6ms / 6ms

        long timeStartD = System.currentTimeMillis();
        Sort.mergeSortBottomUp(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：5ms / 6ms / 6ms / 6ms

    }

    // 归并排序 - 插入排序
    @Test
    public void testMergeInsertionSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.mergeInsertionSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.mergeInsertionSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.mergeInsertionSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：4ms / 3ms / 4ms / 3ms

        long timeStartD = System.currentTimeMillis();
        Sort.mergeInsertionSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：5ms / 4ms / 4ms / 5ms

    }

    // 快速排序 - 单边循环
    @Test
    public void testQuickSortLomuto() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[10000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[10000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.quickSortLomuto(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.quickSortLomuto(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.quickSortLomuto(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 递归排序存在爆栈问题，因此数组长度设置为10000！
            // 最坏情况：22ms / 21ms / 24ms / 23ms

        long timeStartD = System.currentTimeMillis();
        Sort.quickSortLomuto(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：2ms / 1ms / 2ms / 2ms

    }

    // 快速排序 - 双边循环
    @Test
    public void testQuickSortHoare() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[10000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[10000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.quickSortHoare(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.quickSortHoare(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.quickSortHoare(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 递归排序存在爆栈问题，因此数组长度设置为10000！
            // 最坏情况： 21ms / 22ms / 18ms / 17ms

        long timeStartD = System.currentTimeMillis();
        Sort.quickSortHoare(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：1ms / 2ms / 1ms / 2ms

    }

    // 快速排序 - 双边循环方法优化：随机基准点
    @Test
    public void testQuickSortHandleDuplicate() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.quickSortHandleDuplicate(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.quickSortHandleDuplicate(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.quickSortHandleDuplicate(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：6ms / 7ms / 4ms / 5ms

        long timeStartD = System.currentTimeMillis();
        Sort.quickSortHandleDuplicate(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：6ms / 5ms / 9ms / 6ms

    }

    // 计数排序
    @Test
    public void testCountSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.countSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.countSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.countSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：2ms / 3ms / 2ms / 2ms

        long timeStartD = System.currentTimeMillis();
        Sort.countSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：0ms / 1ms / 1ms / 1ms

    }

    // 稳定计数排序
    @Test
    public void testCountSortStable() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.countSortStable(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.countSortStable(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.countSortStable(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：3ms / 3ms / 3ms / 3ms

        long timeStartD = System.currentTimeMillis();
        Sort.countSortStable(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：1ms / 1ms / 2ms / 3ms

    }

    // 桶排序
    @Test
    public void testBucketSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.bucketSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.bucketSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.bucketSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：8ms / 7ms / 8ms / 9ms

        long timeStartD = System.currentTimeMillis();
        Sort.bucketSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：25ms / 22ms / 25ms / 30ms

    }

    // 基数排序
    @Test
    public void testRadixSort() {

        // 基数排序只能排序位数相同的字符串元素，因此稍作修改测试用例
        String[] a = {"9", "3", "7", "2", "5", "8", "1", "4"};
        String[] b = {"acs", "qwd", "123", "y12", "wwa", "be3", "8uh"};
        String[] c = new String[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = 10000 + i + "";
        }
        String[] d = new String[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = String.valueOf (ThreadLocalRandom.current().nextInt(90000) + 10000);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.radixSort(a, 1);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.radixSort(b, 3);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.radixSort(c, 5);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：15ms / 12ms / 13ms / 15ms

        long timeStartD = System.currentTimeMillis();
        Sort.radixSort(d, 5);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：10ms / 9ms / 8ms / 10ms

    }

    // Arrays.sort排序
    @Test
    public void testSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.sort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.sort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.sort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：2ms / 2ms / 3ms / 3ms

        long timeStartD = System.currentTimeMillis();
        Sort.sort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：19ms / 21ms / 17ms / 17ms

    }

    // Arrays.parallelSort排序
    @Test
    public void testParallelSort() {

        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        int[] b = {9, -3, 7, 1, 7, 8, 1, 7, 11, 21, 54, -88, 17, 34, 66, 78, 23, 13, -21, -3, 45};
        int[] c = new int[50000];
        for (int i = 0; i < c.length; i++) {
            c[c.length - i - 1] = i;
        }
        int[] d = new int[50000];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int) (Math.random() * 29600);
        }

        // 设置为static方法后，该随类的加载而加载，可以直接用类调用
        long timeStartA = System.currentTimeMillis();
        Sort.parallelSort(a);
        long timeEndA = System.currentTimeMillis();
        System.out.print("time:" + (timeEndA - timeStartA + "ms   "));
        System.out.print(Arrays.toString(a));
        System.out.println();

        long timeStartB = System.currentTimeMillis();
        Sort.parallelSort(b);
        long timeEndB = System.currentTimeMillis();
        System.out.print("time:" + (timeEndB - timeStartB + "ms   "));
        System.out.print(Arrays.toString(b));
        System.out.println();

        long timeStartC = System.currentTimeMillis();
        Sort.parallelSort(c);
        long timeEndC = System.currentTimeMillis();
        System.out.println("time:" + (timeEndC - timeStartC + "ms   "));
            // 最坏情况：7ms / 8ms / 7ms / 7ms

        long timeStartD = System.currentTimeMillis();
        Sort.parallelSort(d);
        long timeEndD = System.currentTimeMillis();
        System.out.print("time:" + (timeEndD - timeStartD + "ms   "));
            // 随机情况：13ms / 14ms / 13ms / 16ms

    }

    // 其它排序测试
    @Test
    public void testOther() {

        int[] a = {9, 8, 7, 6, 5, -9, -8, -7, -6};
        int[] b = new int[40];
        for (int i = 0; i < b.length; i++) {
            b[i] = ThreadLocalRandom.current().nextInt(19) - 9; // [-9, 10)
        }
        System.out.println(Arrays.toString(b));

        // 按a排序
        System.out.println(Arrays.toString(Sort.relativeSort(b, a)));
        // 按频率排序
        System.out.println(Arrays.toString(Sort.frequencySort(b, 9)));

    }

}
