package test;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

/**
 * ClassName: T1
 * Package: a1_Basics.test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/5 - 9:18
 * @Version: v1.0
 */

public class T1 {

   /* public static void main(String[] args) {

        int grade = new Scanner(System.in).nextInt();
        if (grade >= 90) {
            System.out.println("Grade A");
        } else if (grade >= 80) {
            System.out.println("Grade B");
        } else if (grade >= 70) {
            System.out.println("Grade C");
        }  else if (grade >= 60) {
            System.out.println("Grade D");
        } else {
            System.out.println("Grade E");
        }

        find7(21);

    }*/

    public static void find7(int count) {
        for (int i = 0; i < 100; i++) {
            if ((i != 0 && i % 7 == 0) || i / 10 == 7 || i % 10 == 7) {
                System.out.println(i + "是");
            } else {
                System.out.println(i + "不是");
            }
        }
    }

    @Test
    public void test() {

        int size = 10;

        for (int i = 1; i <= size; i++) {
            for (int k = size; k > i; k--) {
                System.out.print("   ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }
        for (int i = size - 1; i >= 1; i--) {
            for (int k = size - 1; k >= i; k--) {
                System.out.print("   ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }

    }

    @Test
    public void testNumber() {

        int size = 9;

        for (int i = 1; i <= size; i++) {
            for (int k = size; k > i; k--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        // 定义随机数[0, 100]
        int  number = random.nextInt(101) ;
        // 初始化猜数次数
        int count = 1;
        System.out.println("请猜数字(0-100)，共十次机会：");
        // 循环判断
        while (count <= 10) {
            int guess = new Scanner(System.in).nextInt();
            if (guess > number) {
                System.out.println("你猜的数太大，请继续输入！");
                count ++;
            } else if (guess < number) {
                System.out.println("你猜的数太小，请继续输入！");
                count ++;
            } else {
                System.out.println("恭喜你猜对了，共用" + count + "次！");
                break;
            }
            if (count == 10) {
                System.out.println("您的次数用完了！");
            }
        }

    }

    @Test
    public void test3() {

        int ji = 0, ou = 0;
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int j : a) {
            if (j % 2 != 0) {
                ji++;
            } else {
                ou++;
            }
        }
        System.out.println("" + ji + " " + ou);
    }

    @Test
    public void test4() {

        int[] a = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 30);
        }
        for (int k : a) {
            System.out.print(k + " ");
        }

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        
        System.out.println();

        for (int j : a) {
            System.out.print(j + " ");
        }

    }

}