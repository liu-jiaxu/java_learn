package a9_IOStream;

import org.junit.Test;

import java.io.*;

public class IOS5 {

	/*
	缓冲流的使用（处理流之一，作用于流上，提高流的读取、写出速度）
		BufferedInputStream
		BufferedOutputStream
		BufferedReader
		BufferedWriter
	操作：
		1.创建文件
		2.创建流
		3.创建缓冲流
		4.读写操作
		5.关闭流（先关外层流，再关内层流,关闭外层流时会自动关闭内层流）
	 */

    //字节流的缓冲流使用
    @Test
    public void test() throws IOException { //未使用try-catch（太懒了...）

        long start = System.currentTimeMillis();

        File srcFile = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS5\\IOS5.mp4");
        File destFile = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS5\\IOS51.mp4");

        FileInputStream fileInputStream = new FileInputStream(srcFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        //这两个缓冲流包裹在字节流外侧
        //BufferedOutputStream中有flush方法刷新缓冲区（达到8192字节自动移出），提高处理效率

        byte[] buffer = new byte[1024];
        int data = bufferedInputStream.read(buffer);
        while (data != -1) {
            bufferedOutputStream.write(buffer);
            data = bufferedInputStream.read(buffer);
        }
        System.out.println("succeed!");

        bufferedInputStream.close();
        bufferedOutputStream.close();
		/*
		自动关闭的内层流
			fileInputStream.close();
			fileOutputStream.close();
		*/

        long end = System.currentTimeMillis();
        System.out.println("Time:" + (end - start)); //5ms 效率优于字节流

    }
    //这个测试模块可以包装成缓冲流文件复制方法！

    //对于BufferedReader、BufferedWriter两个缓冲流类似，包裹在字符流外侧，也只能处理文本文件
    @Test
    public void test2() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS5\\IOS52.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS5\\IOS53.txt")));

        //方法一：char数组
        /*char[] buffer = new char[6];
        int data = bufferedReader.read(buffer);
        while (data != -1) {
            for (int i = 0; i < data; i++) {
                System.out.print(buffer[i]);
            }
            bufferedWriter.write(buffer, 0, data);
            data = bufferedReader.read(buffer);
        }
        System.out.println("succeed!");*/

        //方法二：String按行读取
        String data1 = bufferedReader.readLine(); //按行读取：读取文件一行数据并转换为String字符串，读到最后则返回null
        while (data1 != null) {
            System.out.println(data1);
            bufferedWriter.write(data1);
            bufferedWriter.newLine(); //提供换行
            data1 = bufferedReader.readLine(); //继续读取下一行的数据
        }
        System.out.println("succeed!");

        bufferedReader.close();
        bufferedWriter.close();

    }

}
