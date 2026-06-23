package a1_Basics;

import java.util.Arrays;

public class B9 {

    public static void main(String[] args) {

        //Arrays类常用方法（注意引用import java.util.Arrays;）
        //选中方法/异常，按F3可以查看源码
        //1.Arrays.equals(arr1,arr2)判断两个数组是否相等
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = new int[]{5, 7, 1, 3, 9, 0, 12};
        System.out.println(Arrays.equals(arr1, arr2));
            /*
            equals源代码：
                public static boolean equals(int[] a, int[] a2) {
                    if (a==a2)
                        return true;
                    if (a==null || a2==null)
                        return false;
                    int length = a.length;
                    if (a2.length != length)
                        return false;
                    for (int i=0; i<length; i++)
                        if (a[i] != a2[i])
                            return false;
                    return true;
                }
             */

        //2.Arrays.toString(arr1)字符串方式输出数组
        System.out.println(Arrays.toString(arr1));

        //3.Arrays.fill(arr1,n)将数组所有值替换为n
        Arrays.fill(arr2, 10);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]);
        }
        System.out.println();

        //4.Arrays.sort(arr1)正序数组（无法倒序）
        Arrays.sort(arr3);
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();

        //5.Arrays.binarySearch(arr1, n)判断n是否在数组arr1中，在返回n的索引，不在返回一个负数，该负数表示若n插入数组时应在的位置
        // 注：数组必须正序！
        System.out.println(Arrays.binarySearch(arr1, 11));

		
		
		/*
		数组异常
			1.数组索引越界异常：ArrayIndexOutOfBoundsException
				System.out.print(arr1[-1]);索引越界
			2.空指针异常：NullPointerException
				int arr4[][]=new int[4][];
				System.out.print(arr4[0][0]);没有元素
		 */

    }

}
