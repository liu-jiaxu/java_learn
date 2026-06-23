package a1_Basics;

public class B6 {

    public static void main(String[] args) {

        //嵌套循环（外层控制行数，内层控制列数）
        //例1：输出倒三角
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        //例2：菱形
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j > i; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(" ");
            }
            for (int j = 14; j > i * 2 + 1; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        //例3：99乘法表
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                int sum = i * j;
                if (i * j >= 10 || j == 1) {
                    System.out.print(i + "*" + j + "=" + sum + " ");
                } else if (i * j < 10) {
                    System.out.print(i + "*" + j + "=" + sum + "  ");
                }
            }
            System.out.println();
        }

        System.out.println();
        //例4：100000内质数与计时（学习算法优化思想与标签lable使用！）
        int count = 0;
        long start = System.currentTimeMillis(); //计算运行时间ms
        lable: for (int i = 2; i <= 100000; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
				/*
				算法优化：
					因为质数规定为有两个在开方数两侧的数相乘，所以必定有一个大于开方数，一个小于开方数，只要判断那些小于开方数的数
				是否可以被除尽就可以知道对应大于开方数的数是否可以被除尽了，不用全部判断。
				 */
                if (i % j == 0) {
                    continue lable; //lable标签可以指定返回的循环，lable可以指定continue、break
                }
            }
            count++;
        }
        long end = System.currentTimeMillis(); //计算运行时间ms
        System.out.println("质数个数：" + count);
        System.out.println("time：" + (end - start) + "ms");

    }

}
