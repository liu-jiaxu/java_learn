package main.a1_Basic_DataStructure.a1_binarysearch;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.Math.abs;
import static java.util.Arrays.binarySearch;
import static junit.framework.TestCase.assertEquals;

/**
 * ClassName: BinarySearch
 * Package: main.a1_Basic_DataStructure.a1_binarysearch
 * Description:二分查找
 *
 * @Author: zgh296
 * @Create: 2023/4/27 - 10:07
 * @Version: v1.0
 */
public class BinarySearch {

    /**
     * 二分查找基础版
     * @param a 待查找的升序数组
     * @param target 待查找的目标
     * @return 索引/-1
     */
    public static int binarySearchBasic(int[] a, int target){
        // 设置两个指针初值
        int i = 0, j = a.length - 1;
        while(i <= j){ // 查找范围
            // 注：必须是i<=j，否则可能会漏掉i和j相等时a[m]和target的比较
            int m = (i+j) >>> 1; // 定义中间指针值，用右移运算符代替除以2
                // 当m值超过int允许范围时，会出现二进制进位占用符号位变为负值的情况(Java中最高位默认为符号位，1为负，0为正)
                // 使用无符号右移一位(二进制右移1位且最高位补0，代替除以2并向下取整)代替除以2，可以适用于多个编程语言
            if(target < a[m]){ // 目标在中间值左边
                j = m - 1;
            } else if(a[m] < target){ // 目标在中间值右边
                // 因为是升序排列，所以全部用<符号
                i = m + 1;
            } else { // 找到目标值
                return m;
            }
        }
        return -1;
    }

    @Test
    public void test() {

        int[] a = {7,13,21,30,38,43};
        assertEquals(0,binarySearchBasic(a,7));
        assertEquals(3,binarySearchBasic(a,30));
        assertEquals(-1,binarySearchBasic(a,55));
            // assertEquals(期望值,运行返回值)
            // assertEquals方法会将预期值和实际返回值比较，匹配则运行通过，否则报错并返回两个值

    }


    /**
     * 二分查找改动版
     * @param a 待查找的升序数组
     * @param target 待查找的目标
     * @return 索引/-1
     */
    public static int binarySearchAlternative(int[] a, int target){
        // 设置两个指针初值
        int i = 0, j = a.length; // 改动j的初始范围
        while(i < j){ // 查找范围
            int m = (i+j) >>> 1; // 定义中间指针值，用右移运算符代替除以2
            if(target < a[m]){ // 目标在中间值左边
                j = m;
            } else if(a[m] < target){ // 目标在中间值右边
                i = m + 1;
            } else { // 找到目标值
                return m;
            }
        }
        return -1;
    }

    @Test
    public void test2() {

        int[] a = {7,13,21,30,38,43};
        assertEquals(0,binarySearchAlternative(a,7));
        assertEquals(3,binarySearchAlternative(a,30));
        assertEquals(-1,binarySearchAlternative(a,55));
            // assertEquals(期望值,运行返回值)
            // assertEquals方法会将预期值和实际返回值比较，匹配则运行通过，否则报错并返回两个值

    }


    /**
     * 线性查找
     * @param a 待查找的数组
     * @param target 待查找的目标
     * @return 索引/-1
     */
    public static int linearSearch(int[] a,int target){
        for (int i = 0; i < a.length; i++) {
            if(a[i] == target){
                return i;
            }
        }
        return -1;
    }


    /*
      时间复杂度
          1.二分查找
              平均&最差 O(floor(log_2(n))) -> 以2为底n的对数向下取整(floor()表示向下取整)
                   表示为 O(log_2(n))
          2.线性查找
              平均 O(n/2)
              最坏 O(n)
          3.假设算法要处理的数据规模是n，代码总的执行行数用函数f(n)来表示，
              线性查找算法的函数 f(n) = 3*n + 3
              二分查找算法的函数 f(n) = (floor(log_2(n)) + 1) * 5 + 4
      空间复杂度
          需要的指针个数，例如i，j，m，上述两个算法复杂度均为O(1)
     */


    /**
     * 二分查找平衡版
     *     减少if-elseif语句判断，平衡左右查找执行次数
     *     基础二分查找查找左测元素执行L次，而查找右侧元素由于要先判断if不满足再执行elseif，执行2L次
     * @param a 待查找的升序数组
     * @param target 待查找的目标
     * @return 索引/-1
     */
    public static int binarySearchBalance(int[] a,int target){
        int i = 0, j = a.length;
        while(1 < j - i){
            int m = (i+j) >>> 1;
            // 非if即else，左右元素查找均执行L次
            if(target < a[m]){
                j = m;
            } else {
                i = m;
            }
        }
        // 外侧判断目标值
        return a[i] == target ? i : -1;
    }


    /**
     * 二分查找Java版底层源码
     * @param a 待查找的升序数组
     * @param fromIndex 左边界值，一般为0
     * @param toIndex 右边界值，a.length - 1
     * @param key 待查找的目标
     * @return 找到：返回索引
     *         未找到：-(插入点-1) -> 插入点表示该元素插入数组时应在的索引位置
     */
    // 底层源码
    public static int binarySearch0(long[] a, int fromIndex, int toIndex, long key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        // 此处low+1是为了和找到元素时的返回值区分，否则查找元素为0时无法区分
        return -(low + 1);  // key not found.
    }

    @Test
    public void test3() {

        // 演示查找及插入
        int[] a = {7,13,21,30,38,43};
        int i = binarySearch(a, 7);
        int i2 = binarySearch(a,30);
        int key = 55;
        int i3 = binarySearch(a,key);
        System.out.println(""+ i + i2 + i3);
        // 插入
        if(i3 < 0){
            int insertIndex = Math.abs(i3+1);
            int[] b = new int[a.length+1];
            System.arraycopy(a,0,b,0,insertIndex);
                /*
                   System.arraycopy()：用于数组复制
                       参数列表：原数组，起始位置，需要插入的数组，起始位置，插入元素个数
                 */
            b[insertIndex] = key;
            System.arraycopy(a,insertIndex,b,insertIndex,a.length-insertIndex);
            System.out.println(Arrays.toString(b));

        }

    }


    /**
     * 二分查找Leftmost
     * @param a 待查找的升序数组
     * @param target 待查找的目标
     * @return 找到：候选者位置/-1
     */
    public static int binarySearchLeftmost(int[] a, int target){
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

    /**
     * 二分查找Rightmost
     * @param a 待查找的升序数组
     * @param target 待查找的目标
     * @return 候选者位置/-1
     */
    public static int binarySearchRightmost(int[] a, int target){
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

    /**
     * 二分查找Leftmost修改版
     * @param a 待查找的升序数组
     * @param target 待查找的目标
     * @return i(大于等于目标且最靠左的索引)
     */
    public static int binarySearchLeftmost2(int[] a, int target){
        // 设置两个指针初值
        int i = 0, j = a.length - 1;
        while(i <= j){
            int m = (i+j) >>> 1;
            if(target <= a[m]){ // 目标在中间值左边或找到目标值
                j = m - 1;
            } else { // 目标在中间值右边
                i = m + 1;
            }
        }
        return i;
    }

    /**
     * 二分查找Rightmost修改版
     * @param a 待查找的升序数组
     * @param target 待查找的目标
     * @return i-1(小于等于目标且最靠右的索引)
     */
    public static int binarySearchRightmost2(int[] a, int target){
        // 设置两个指针初值
        int i = 0, j = a.length - 1;
        while(i <= j){
            int m = (i+j) >>> 1;
            if(target < a[m]){ // 目标在中间值左边
                j = m - 1;
            } else { // 目标在中间值右边或找到目标值
                i = m + 1;
            }
        }
        return i - 1;
    }

    @Test
    public void test4() {

        int[] a = {7,13,13,13,13,21,30,38,43};
        assertEquals(1,binarySearchLeftmost(a,13));
        assertEquals(-1,binarySearchLeftmost(a,123));

        assertEquals(4,binarySearchRightmost(a,13));
        assertEquals(-1,binarySearchRightmost(a,123));

        assertEquals(8,binarySearchLeftmost2(a,43));
        // binarySearchLeftmost2找33没找到，返回数组中大于33的最小(最靠左)元素
        assertEquals(7,binarySearchLeftmost2(a,33));

        assertEquals(4,binarySearchRightmost2(a,13));
        // binarySearchRightmost2找33没找到，返回数组中小于33的最大(最靠右)元素
        assertEquals(6,binarySearchRightmost2(a,33));

    }

    @Test
    public void test5() {

        int[] a = {7,13,13,13,13,21,30,38,43};
        // 求排名、前任、后任、最近邻居、查范围
        // 求37的最近邻居
        int target = 37; // 目标值
        int left = binarySearchLeftmost2(a,target) - 1; // 前任
        int right = binarySearchRightmost2(a,target) + 1; // 后任
        System.out.println( abs(target - a[left]) < abs(target - a[right]) ? a[left] : a[right] ); // 最近邻居

        // 查询数组中x<=35的元素
        for (int i = 0; i <= binarySearchRightmost2(a,35); i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();

        // 查询数组中13<=x<=43的元素
        for (int i = binarySearchLeftmost2(a,13); i <= binarySearchRightmost2(a,43); i++) {
            System.out.print(a[i]+" ");
        }

    }

}
