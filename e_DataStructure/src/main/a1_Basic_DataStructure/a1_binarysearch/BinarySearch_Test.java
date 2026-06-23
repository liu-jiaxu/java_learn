package main.a1_Basic_DataStructure.a1_binarysearch;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BinarySearch_Test
 * Package: main.a1_Basic_DataStructure.a1_binarysearch
 * Description:二分查找练习
 *
 * @Author: zgh296
 * @Create: 2023/5/4 - 16:26
 * @Version: v1.0
 */
public class BinarySearch_Test {

    // 例1：普通二分查找
    public static int search(int[] a,int target){
        int i = 0, j = a.length - 1;
        while(i <= j){
            int m = (i+j) >>> 1;
            if(target < a[m]){
                j = m - 1;
            } else if(target > a[m]){
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static int search2(int[] a,int target){
        int i = 0, j = a.length;
        while(i + 1 < j){
            int m = (i+j) >>> 1;
            if(target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        return a[i] == target ? i : -1;
    }

    @Test
    public void test() {

        int[] a = {1,2,3,4,5,6,7,8};
        System.out.println(search(a,6));
        System.out.println(search2(a,6));

    }


    // 例2：二分查找返回值改变为插入索引：使用源码或Leftmost都可以实现
    public int search3(int[] a,int target){
        int left = 0, right = a.length - 1;
        while(left <= right){
            int mid = (left + right) >>> 1;
            if(target <= a[mid]){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    @Test
    public void test2() {

        int[] a = {1,2,3,4,5,6,7,8};
        System.out.println(search3(a,6));
        System.out.println(search3(a,9));

    }

    // 例3：查找升序数组的某个元素的起始和结束位置，找不到return -1
    // 使用Leftmost和Rightmost获取即可
    public static int search4(int[] a, int target){
        // 设置两个指针初值
        int i = 0, j = a.length - 1;
        // 定义候选变量
        int candidate = -1;
        while(i <= j){
            int m = (i+j) >>> 1; // 定义中间指针值，用右移运算符代替除以2
            if(target < a[m]){ // 目标在中间值左边
                j = m - 1;
            } else if(a[m] < target){ // 目标在中间值右边
                i = m + 1;
            } else { // 找到目标值
                // 记录下来此时的候选者
                candidate = m;
                j = m - 1;
            }
        }
        return candidate;
    }

    public static int search5(int[] a, int target){
        // 设置两个指针初值
        int i = 0, j = a.length - 1;
        // 定义候选变量
        int candidate = -1;
        while(i <= j){
            int m = (i+j) >>> 1; // 定义中间指针值，用右移运算符代替除以2
            if(target < a[m]){ // 目标在中间值左边
                j = m - 1;
            } else if(a[m] < target){ // 目标在中间值右边
                i = m + 1;
            } else { // 找到目标值
                // 记录下来此时的候选者
                candidate = m;
                i = m + 1;
            }
        }
        return candidate;
    }

    @Test
    public void test3() {

        int[] a = {1,1,3,4,6,6,6,8};
        System.out.println(search4(a,6) + " " + search5(a,6));
        System.out.println(search4(a,9) + " " + search5(a,9));

    }

    public static int searchTime(List<LocalDate> a, LocalDate target){
        int i = 0, j = a.size() - 1;
        while(i <= j){
            int m = (i+j) >>> 1;
            if(target.isBefore(a.get(m))){
                j = m - 1;
            } else if(target.isAfter(a.get(m))){
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    @Test
    public void testTime() {
        long a = 0, b = 0;
        for (int c = 0; c < 10; c++) {
            List<LocalDate> times = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int year = i + 1;
                times.add(LocalDate.of(year, 12, 31));
            }

            List<LocalDate> findList = new ArrayList<>(times);

            // 获取代码开始执行时的纳秒时间
            long startTime1 = System.nanoTime();
            for (LocalDate time : times) {
                int index = searchTime(findList, time);
            }
            // 获取代码结束执行时的纳秒时间
            long endTime1 = System.nanoTime();
            // 计算代码运行的总时间（纳秒）
            long duration1 = endTime1 - startTime1;
            // 输出运行时间
            System.out.println("The code took1 " + duration1 + " nanoseconds to run.");

            // 获取代码开始执行时的纳秒时间
            long startTime2 = System.nanoTime();
            for (LocalDate time : times) {
                for (LocalDate localDate : findList) {
                    if (time.isEqual(localDate)) {
                        break;
                    }
                }
            }
            // 获取代码结束执行时的纳秒时间
            long endTime2 = System.nanoTime();
            // 计算代码运行的总时间（纳秒）
            long duration2 = endTime2 - startTime2;
            // 输出运行时间
            System.out.println("The code took2 " + duration2 + " nanoseconds to run.");

            a = a + duration1;
            b = b + duration2;
        }
        System.out.println("The sum took1 " + a + " nanoseconds to run.");
        System.out.println("The sum took2 " + b + " nanoseconds to run.");
    }
}
