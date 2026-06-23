package main.a1_Basic_DataStructure.a3_recursion;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ClassName: Recursion_Test
 * Package: main.a1_Basic_DataStructure.a3_recursion
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/8 - 21:06
 * @Version: v1.0
 */
public class Recursion_Test {

    // 阶乘
    @Test // shift + alt + enter 导入测试包
    public void test() {
        System.out.println(Recursion.factorial(0));
        System.out.println(Recursion.factorial(1));
        System.out.println(Recursion.factorial(5));
        System.out.println(Recursion.factorial(20));
    }

    // 反向打印指定长度字符串
    @Test
    public void test2() {
        Recursion.reverse("abcdefg",0,6);
        // Recursion.reverse("abcdefg",0,21);
    }

    // 二分查找
    @Test
    public void test3() {
        int[] a = {1,2,3,4,5,6,7,8};
        assertEquals(0,Recursion.binarySearch(a,1,0,6));
        assertEquals(4,Recursion.binarySearch(a,5,0,7));
        assertEquals(-1,Recursion.binarySearch(a,5,0,1));
        assertEquals(4,Recursion.binarySearch(a,5));
        assertEquals(-1,Recursion.binarySearch(a,12));
    }

    // 冒泡排序
    @Test
    public void test4() {
        int[] a = {23,12,44,2,0,9,54,11};
        Recursion.bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }

    // 插入排序
    @Test
    public void test5() {
        int[] a = {23,12,44,2,0,9,54,11};
        Recursion.insertionSort(a);
        System.out.println(Arrays.toString(a));
        int[] b = {23,12,44,2,0,9,54,11};
        Recursion.insertionSort(b,0,3);
        System.out.println(Arrays.toString(b));
    }

    // 插入排序及冒泡排序时间测试：插入排序优于冒泡排序
    @Test
    public void test6() {
        int[] b = new int[11000];
        for (int i = 0; i < b.length; i++) {
            b[i] = (int) (Math.random() * 10000);
        }
        long start = System.currentTimeMillis();
        Recursion.bubbleSort(b);
        long end = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        Recursion.insertionSort(b);
        long end2 = System.currentTimeMillis();
        System.out.println("冒泡：" + (end - start) + "ms"); // 48ms
        System.out.println("插入：" + (end2 - start2) + "ms"); // 1ms
    }

    // 斐波那切数列
    @Test
    public void test7() {
        System.out.println(Recursion.fibonacci(0));
        System.out.println(Recursion.fibonacci(1));
        System.out.println(Recursion.fibonacci(12));
        System.out.println(Recursion.fibonacci(20));
        System.out.println(Recursion.fibonacciCount(1));
        System.out.println(Recursion.fibonacciCount(20));
    }

    // 斐波那契优化
    @Test
    public void test8() {
        long start = System.currentTimeMillis();
        System.out.println(Recursion.fibonacci(45));
        long end = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        System.out.println(Recursion.fibonacciOptimize(45));
        long end2 = System.currentTimeMillis();
        System.out.println("未优化：" + (end - start) + "ms"); // 3329ms
        System.out.println("优化：" + (end2 - start2) + "ms"); // 0ms
    }

    // 兔子问题与青蛙跳阶
    @Test
    public void test9() {
        System.out.println(Recursion.rabbits(1));
        System.out.println(Recursion.rabbits(10));
        System.out.println(Recursion.frogJumps(1));
        System.out.println(Recursion.frogJumps(7));
    }

    // 递归求和与爆栈问题
    @Test
    public void test10() {
        System.out.println(Recursion.sum(100));
        System.out.println(Recursion.sum(-100));
        System.out.println(Recursion.sum(1000000)); // java.lang.StackOverflowError
            /*
             * 每次方法调用是需要消耗一定的栈内存的，这些内存用来存储方法参数、方法内局部变量、返回地址等等
             * 方法调用占用的内存需要等到**方法结束时**才会释放
             * 而递归调用我们之前讲过，不到最深不会回头，最内层方法没完成之前，外层方法都结束不了
             * 解决爆栈问题：Scala
             */
    }
    
    // 汉诺塔
    @Test
    public void test11() {
        Recursion.Hanoi hanoi = new Recursion.Hanoi(0);
        System.out.println();
        new Recursion.Hanoi(10);
    }
    
    // 杨辉三角及优化
    @Test
    public void test12() {
        long start = System.currentTimeMillis();
        new Recursion.PascalTriangle(13);
        long end = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        new Recursion.PascalTriangleOptimize(13);
        long end2 = System.currentTimeMillis();
        long start3 = System.currentTimeMillis();
        new Recursion.PascalTriangleOptimize2(13);
        long end3 = System.currentTimeMillis();
        System.out.println("未优化：" + (end - start) + "ms"); // 18ms
        System.out.println("优化：" + (end2 - start2) + "ms"); // 5ms
        System.out.println("全优化：" + (end3 - start3) + "ms"); // 4ms
    }

    // 二维数组初始值测试
    @Test
    public void test13() {
        int[][] a = new int[5][5];
        for (int i = 0; i <a.length ; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " "); // 全是0
            }
            System.out.println();
        }
    }

}
