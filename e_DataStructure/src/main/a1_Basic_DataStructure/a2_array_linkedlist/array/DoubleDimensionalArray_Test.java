package main.a1_Basic_DataStructure.a2_array_linkedlist.array;

import org.junit.Test;

/**
 * ClassName: DoubleDimensionalArray_Test
 * Package: main.a1_Basic_DataStructure.a2_array_linkedlist
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/6 - 10:20
 * @Version: v1.0
 */
public class DoubleDimensionalArray_Test {

    // 两种不同顺序遍历二维数组

    // ij方法
    public static void ij(int[][] a, int rows, int columns) {
        long sum = 0L;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum += a[i][j];
            }
        }
        System.out.println(sum);
    }

    // ji方法
    public static void ji(int[][] a, int rows, int columns) {
        long sum = 0L;
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                sum += a[i][j];
            }
        }
        System.out.println(sum);
    }

    @Test
    public void test() {

        int rows = 1000000;
        int columns = 14;
        int[][] a = new int[rows][columns];

        long start1 = System.currentTimeMillis();
        ij(a, rows, columns);
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        ji(a, rows, columns);
        long end2 = System.currentTimeMillis();

        System.out.println(end1 - start1); // 10s
        System.out.println(end2 - start2); // 58s

    }

}
