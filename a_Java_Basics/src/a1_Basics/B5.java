package a1_Basics;

import java.util.Scanner;

public class B5 {

    public static void main(String[] args) {

		/*
		循环结构
			for
			while
			do-while
		 */

		
		
		/*
		for循环4要素
		①初始化条件
		②循环条件（boolean型）
		③循环体
		④迭代条件
		for(①;②;④){
			③
		}
		执行过程：BEGIN->①->②->③->④->②->③->④->②->③->④->...->②->END
		 */

        //例1：输入两个数，得到其最大公约数和最小公倍数（例如12、20最大公约数为4，最小公倍数为60）
		/*
		程序设计步骤：
			1.先理解题意，理清程序设计的步骤，找到自己不会的点（比如说忘记的数学公式和代码等等）
			2.解决自己不会的点，理清思路，重新设计程序
			3.最后敲代码
		 */
        Scanner in = new Scanner(System.in);
        System.out.print("请输入一个数：");
        int m = in.nextInt();
        System.out.print("请输入另一个数：");
        int n = in.nextInt();
        int min = Math.max(m, n);
        int max = Math.max(m, n);
        for (int i = min; i > 0; i--) {
            if (m % i == 0 && n % i == 0) {
                System.out.println("最大公约数为：" + i);
                break;
            }
        }
        for (int i = max; i <= m * n; i++) {
            if (i % m == 0 && i % n == 0) {
                System.out.println("最小公倍数为：" + i);
                break;
            }
        }

        //例2：水仙花数输出及记录个数
        int count = 0;
        System.out.print("水仙花数：");
        for (int i = 100; i < 1000; i++) {
            int bai = i / 100, shi = i / 10 % 10, ge = i % 10;
            if (i == (bai * bai * bai + shi * shi * shi + ge * ge * ge)) {
                count++;
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.println("共有水仙花数：" + count + "个");


        //while、do-while循环（条件一样就行）
        int num = 0, num1 = 0;
        int i = 0, j = 0;
        while (i <= 100) {
            if (i % 2 == 0) {
                num = num + i;
            }
            i++;
        }
        System.out.print(num + " "); //2550

        do {
            if (j % 2 == 0) {
                num1 = num1 + j;
            }
            j++;
        } while (j <= 100);
        System.out.print(num1); //2550

    }

}
