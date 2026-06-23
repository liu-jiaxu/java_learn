package main.a1_Basic_DataStructure.a3_recursion;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * ClassName: Recursion
 * Package: main.a1_Basic_DataStructure.a3_recursion
 * Description:递归相关问题
 *
 * @Author: zgh296
 * @Create: 2023/5/8 - 21:00
 * @Version: v1.0
 */
public class Recursion {

    // 深入到最里层叫做递，从最里层出来叫做归

    /**
     * 阶乘
     * @param n 阶乘数字
     * @return 阶乘结果
     */
    public static long factorial(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("n must be positive number");
        } else if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * 反向打印指定长度字符串，输入一个字符串及反转范围[start, end)，递归打印并将结果存入集合，
     * @param str 指定字符串
     * @param start 起始索引(包含)
     * @param end 结束索引(不包含)
     * @throw: IndexOutOfBoundsException 参数end范围超过字符串长度则抛出异常
     */
    public static void reverse(String str, int start, int end) {
        if(end <= str.length()){
            if(start == end) {
                return;
            }
            reverse(str,start + 1,end);
            System.out.print(str.charAt(start));
        } else {
            throw new IndexOutOfBoundsException("The parameter 'end' out of string length!");
        }
    }

    /**
     * 递归实现随机范围二分查找
     * @param a 待查找的升序数组
     * @param target 查找目标值
     * @param start 起始索引(包含)
     * @param end 结束索引(包含)
     * @return 找到返回索引位置，未找到返回-1
     */
    public static long binarySearch(int[] a, int target, int start, int end) {
        // 设置结束条件
        if(start > end) {
            return -1;
        }
        // 递归二分查找
        int m = (start + end) >>> 1;
        if(target < a[m]) {
            return binarySearch(a, target, start, m - 1);
        } else if(a[m] < target) {
            return binarySearch(a, target, m + 1, end);
        } else {
            return m;
        }
    }

    /**
     * 整个数组范围二分查找
     * @param a 待查找的升序数组
     * @param target 查找目标值
     * @return 找到返回索引位置，未找到返回-1
     */
    public static long binarySearch(int[] a, int target) {
        return binarySearch(a, target,0,a.length - 1);
    }

    /**
     * 递归冒泡排序
     * @param a 待排序的数组
     * @param j 数组右边界索引
     */
    public static void bubbleSort(int[] a, int j) {
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
     * 用户调用的冒泡排序
     * @param a 待排序的数组
     */
    public static void bubbleSort(int[] a) {
        bubbleSort(a, a.length - 1);
    }

    /**
     * 递归插入排序，可以指定排序范围[low, high)
     * @param a 待排序的数组
     * @param low 数组左边界索引
     * @param high 数组右边界索引
     */
    public static void insertionSort(int[] a, int low, int high) {
        // 暂时看不懂...
        if(low == high) {
            return;
        }
        int t = a[low];
        int i = low - 1; // 已排序区域指针
        while(i >= 0 && a[i] > t) { // 没有找到插入位置
            a[i + 1] = a[i]; // 空出插入位置
            i--;
        }
        // 找到插入位置
        if(i + 1 != low) {
            a[i + 1] = t;
        }
        insertionSort(a, low + 1, high);
    }

    /**
     * 整个数组进行插入排序
     * @param a 待排序的数组
     */
    public static void insertionSort(int[] a) {
        insertionSort(a, 1, a.length);
    }

    /**
     * 斐波那切数列
     * @param n 数列项数
     * @return 该项数对应值
     */
    public static int fibonacci(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("The parameter n must be an integer greater than or equal to 0");
        } else if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
        /*
          时间复杂度
              递归的次数也符合斐波那契规律，count = 2 * f(n+1)-1
              时间复杂度推导过程
                 斐波那契通项公式 f(n) = frac{1}{sqrt{5}}*({frac{1+sqrt{5}}{2}}^n - {frac{1-sqrt{5}}{2}}^n)
                  简化为：f(n) = frac{1}{2.236}*({1.618}^n - {(-0.618)}^n)
                  带入递归次数公式 2*frac{1}{2.236}*({1.618}^{n+1} - {(-0.618)}^{n+1})-1
                  时间复杂度为 Θ(1.618^n)
                      frac{1}：倒数
                      sqrt{}：根号
         */
    }

    /**
     * 斐波那切数列的递归次数
     * @param n 数列项数
     * @return 该数列项的递归次数
     */
    public static int fibonacciCount(int n) {
        if (n == 0) {
            return 1;
        } else {
            return 2 * fibonacci(n + 1) - 1;
        }
    }

    public static int fibonacciOptimize(int n) {
        /*
              在递归时，用一个初始值均为-1的数组存储斐波那契各项值，初始化0和1的值(因为斐波那切数列已知)，
          调用内部方法f，每次递归后将该项的值存入数组对应位置，之后递归用到时不用再次计算，而是从数组中拿去，
          优化效率。时间复杂度：O(n)
         */
        int[] cache = new int[n + 1];
        Arrays.fill(cache,-1);
        cache[0] = 0;
        cache[1] = 1;
        return f(n, cache);
    }

    /**
     * 斐波那契内部优化方法
     * @param n 数列项数
     * @param cache 该数组用于存储斐波那契项数的值
     * @return 该项数对应值
     */
    private static int f(int n, int[] cache) {
        // 省去n = 0/1时的判断，因为0和1已经在数组中初始化值了
        if(cache[n] != -1) {
            return cache[n];
        }
        // 完成一次递归时，将对应值存入数组相应位置，并返回该值供下几次递归使用
        cache[n] = f(n - 1, cache) + f(n - 2, cache);
        return cache[n];
        // 时间复杂度：O(n)
    }

    /**
     * 兔子问题<br>
     *     第一个月，有一对未成熟的兔子<br>
     *     第二个月，它们成熟<br>
     *     第三个月，它们能产下一对新的小兔子<br>
     *     所有兔子遵循相同规律，求第n个月的兔子数<br>
     *     规律：f(n) = f(n - 1) + f(n - 2)
     * @param n 月份
     * @return 兔子数量
     */
    public static int rabbits(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("The parameter 'n' must be greater than or equal to 0");
        } else if(n == 1 || n == 2) {
            return 2;
        } else {
            return rabbits(n - 1) + rabbits(n - 2);
        }
    }

    /**
     * 青蛙跳阶<br>
     *     楼梯有n阶<br>
     *     青蛙要爬到楼顶，可以一次跳一阶，也可以一次跳两阶<br>
     *     只能向上跳，问有多少种跳法<br>
     *     规律：f(n) = f(n - 1) + f(n - 2)，n阶可以由n-1阶或n-2阶到达
     * @param n 楼梯总阶数
     * @return 总跳法
     */
    public static int frogJumps(int n) {
        if(n < 0) {
            throw new IllegalArgumentException("The parameter 'n' must be greater than 0");
        } else if(n == 0) {
            return 0;
        } else if(n == 1 || n == 2) {
            return 1;
        } else {
            return frogJumps(n - 1) + frogJumps(n - 2);
        }
    }

    /**
     * 递归求和，若输入负数，则求[n,0]范围的和，若输入正数，则求[0,n]范围的和
     * @param n 范围
     * @return 和
     */
    public static int sum(int n) {
        if(n == 0) {
            return 0;
        }
        if(n < 0) {
            return sum(n + 1) + n;
        } else {
            return sum(n - 1) + n;
        }
    }

    // 汉诺塔问题：三颗石柱上有n个圆盘，一次仅能移动一个圆盘，小圆盘不能在大圆盘上
    public static class Hanoi {
        // 创建三根柱子
        static LinkedList<Integer> a = new LinkedList<>();
        static LinkedList<Integer> b = new LinkedList<>();
        static LinkedList<Integer> c = new LinkedList<>();

        // 构造方法直接调用move递归方法即可
        public Hanoi(int n) {
            if(n < 0) {
                throw new IllegalArgumentException("n must be positive than 0");
            } else if (n == 0) {
                System.out.println("初始圆柱及圆盘位置：");
                print();
                System.out.println("共移动了 0 次");
            } else {
                for (int i = n; i >= 1 ; i--) {
                    a.addLast(i);
                }
                System.out.println("初始圆柱及圆盘位置：");
                print();
                System.out.println("共移动了 " + (int) (Math.pow(2, n) - 1) + "次");
                move(n, a, b, c);
            }
        }

        // 打印每次移动后圆盘位置的方法
        private static void print() {
            System.out.println("--------------");
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        }

        /**
         * 汉诺塔问题递归方法
         * @param n 圆盘个数
         * @param a 原始柱子
         * @param b 移动指定圆盘时借助的柱子
         * @param c 目标柱子
         */
        private static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
            // 结束递归判断
            if(n == 0) {
                return;
            }
            move(n - 1, a, c, b); // 开始时将n - 1个盘子移动到中间柱子b位置，依次递归移动到只剩最后一个盘子停止
            c.addLast(a.removeLast()); // 中间一步，将初始柱子a最底下的圆盘移动到目标柱子c上
            print();
            move(n - 1, b ,a, c); // 将中间柱子b上的n - 1个圆盘借助圆柱a移动到目标柱子c上
        }
        // 时间复杂度：O(2^n)，T(n) = c(2^n - 1)，因此当圆盘数量增多时程序运行极慢

    }

    // 杨辉三角
    public static class PascalTriangle {

        public PascalTriangle(int n) {
            print(n);
        }

        // 打印空格操作
        private static void printSpace(int n, int i) {
            int num = (n - i - 1) * 2;
            for (int j = 0; j < num; j++) {
                System.out.print(" ");
            }
        }

        // 打印操作
        private static void print(int n) {
            for (int i = 0; i < n; i++) {
                printSpace(n, i);
                for(int j = 0; j <= i; j++) {
                    System.out.printf("%-4d", element(i, j)); // %4d表示一个数字占四位，不满则前补空格，-表示左对齐
                }
                System.out.println();
            }
        }

        /**
         * 杨辉三角递归规律
         * @param i 行
         * @param j 列
         * @return 对应值
         */
        private static int element(int i, int j) {
            /*
              规律：f(i, j) = f(i - 1, j - 1) + f(i - 1, j)
             */
            if(j == 0 || i == j) {
                return 1;
            }
            return element(i - 1, j - 1) + element(i - 1, j);
        }

    }

    // 杨辉三角时间复杂度优化
    public static class PascalTriangleOptimize {

        public PascalTriangleOptimize(int n) {
            print(n);
        }

        // 打印空格操作
        private static void printSpace(int n, int i) {
            int num = (n - i - 1) * 2;
            for (int j = 0; j < num; j++) {
                System.out.print(" ");
            }
        }

        // 打印操作，与斐波那契优化相同，使用数组存储递归已求元素，之后的递归直接获取数组元素即可
        private static void print(int n) {
            // 由于杨辉三角和二维数组相似，因此用二维数组存储
            int[][] triangle = new int[n][]; // 杨辉三角列数不相同，可以在循环递归时再指定列数
            for (int i = 0; i < n; i++) {
                triangle[i] = new int[i + 1]; // 指定二维数组列数
                printSpace(n, i);
                for(int j = 0; j <= i; j++) {
                    System.out.printf("%-4d", element(triangle, i, j)); // %4d表示一个数字占四位，不满则前补空格，-表示左对齐
                }
                System.out.println();
            }
        }

        /**
         * 杨辉三角递归规律
         * @param triangle 二维数组，用于存储递归求得的元素
         * @param i 行
         * @param j 列
         * @return 对应值
         */
        private static int element(int[][] triangle , int i, int j) {
            // 二维数组初始化未赋值时默认所有值均为0
            if(triangle[i][j] > 0) {
                return triangle[i][j];
            }
            /*
              规律：f(i, j) = f(i - 1, j - 1) + f(i - 1, j)
             */
            if(j == 0 || i == j) {
                triangle[i][j] = 1;
                return 1;
            }
            triangle[i][j] = element(triangle,i - 1, j - 1) + element(triangle,i - 1, j);
            return triangle[i][j];
        }

    }

    // 杨辉三角空间复杂度优化，动态规划
    public static class PascalTriangleOptimize2 {

        public PascalTriangleOptimize2(int n) {
            print(n);
        }

        // 打印空格操作
        private static void printSpace(int n, int i) {
            int num = (n - i - 1) * 2;
            for (int j = 0; j < num; j++) {
                System.out.print(" ");
            }
        }

        private static void print(int n) {
            // 使用一维数组存储元素
            int[] row = new int[n];
            for (int i = 0; i < n; i++) {
                // 由于杨辉三角最后一行数据量与行数相等，以此类推可知每行的数据量 = 循环次数 + 1
                createRow(row, i);
                printSpace(n, i);
                for(int j = 0; j <= i; j++) {
                    System.out.printf("%-4d", row[j]); // %4d表示一个数字占四位，不满则前补空格，-表示左对齐
                }
                System.out.println();
            }
        }

        /**
         * 动态规划，一维数组赋值
         * @param row 一维数组
         * @param i 一维数组每次存储元素的数量 - 1，即每次的最大索引
         */
        public static void createRow(int[] row, int i) {
            if(i == 0) {
                row[0] = 1;
            }
            //     一维数组存储第n轮元素，到第n + 1轮时，上一轮的元素已经被输出了，因此失效，结合杨辉三角规律，
            // 替换掉一维数组原位置上的元素，依次类推，节省空间复杂度
            for (int j = i; j > 0; j--) {
                row[j] = row[j] + row[j - 1];
            }
        }

    }

}
