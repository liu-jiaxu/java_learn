package a3_Exception_Handling;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/*
异常
	1.Error：Java虚拟机无法解决的严重问题（JVM系统内部错误、资源耗尽）
	2.Exception：编程错误或外在因素错误（空指针访问、读取文件不存在、网络连接中断、数组索引越界）
		编译时异常checked（编译时出错，不会生成字节码，没有结果）
			IOException:FileNotFoundException
			ClassNotFoundException
		常见运行时异常unchecked，RunTimeException（运行时异常，但通过编译，生成字节码，有结果）
			空指针异常：NullPointerException
			数组索引越界异常：ArrayIndexOutOfBoundsException
			类型转换异常：ClassCastException
			数字格式化异常：NumberFormatException
			输入不匹配异常：InputMismatchException
			整数除以零异常：ArithmeticException
 */

public class EH1 {

    //1.栈溢出 java.lang.StackOverflowError
    //public static void main(String[] args) {main(args)};
    //2.堆溢出 java.lang.OutOfMemoryError
    //Integer[] arr=new Integer[100000000000];

    //编译时异常
    //文件查找异常
    @Test
    public void test() throws IOException {

        File file = new File("123.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        int data = fileInputStream.read();
        while (data != -1) {
            System.out.println((char) data);
            data = fileInputStream.read();
        }

        fileInputStream.close();

    }

    //运行时异常
    //1.空指针异常
    @Test
    public void test1() {

        //数组访问空指针
        int arr[] = null;
        System.out.println(arr[1]);

        //对象调用空指针
        EH1 eh1 = null;
        System.out.println(eh1.toString());

    }

    //2.数组索引异常
    @Test
    public void test2() {

        //数组索引异常
        int arr[] = new int[10];
        System.out.println(arr[11]);

        //其他索引异常
        String str = "abcdefg";
        System.out.println(str.charAt(10));

    }

    //3.类型转换异常
    @Test
    public void test3() {

        Object object = new Date();
        String string = (String) object;

    }

    //4.数字格式化异常
    @Test
    public void test4() {

        String string = "qwerty";
        int i1 = Integer.parseInt(string);

    }

    //5.输入不匹配异常
    @Test
    public void test5() {

        System.out.print("输入：");
        Scanner scanner = new Scanner(System.in);
        int i2 = scanner.nextInt(); //此处输入非int型报错

    }

    //6.整数除以零异常
    @Test
    public void test6() {

        int i3 = 10 / 0;
        System.out.println(i3);

    }

}
