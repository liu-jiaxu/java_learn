package a9_IOStream;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/*
其它流：
	1.标准输入、输出流
	2.打印流
	3.数据流
 */

public class IOS8 {

	/*
	输入输出流
		System.in：输入流，默认从键盘输入
		System.out：输出流，默认从控制台输出
		方法：
			static void setIn(InputStream in)  setOut(PrintStream ps)方式重新指定输入输出
	 */

    //例1：键盘输入一行字符串，将小写变为大写，直到输入e或exit时退出程序
    @Test
    public void test() {

        //方法一：使用Scanner的next()方法
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        //方法二：使用System.in的readLine()方法
        BufferedReader bufferedReader = null;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in); //控制台输入
            bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                System.out.print("请输入字符串：");
                String string = bufferedReader.readLine(); //读取字符串
                if ("e".equalsIgnoreCase(string) || "exit".equalsIgnoreCase(string)) { //设置退出识别，equalsIgnoreCase()忽略大小写匹配
                    System.out.println("end!");
                    break;
                }
                String upperCase = string.toUpperCase(); //字符串大写转换
                System.out.println("转换后的字符串为" + upperCase);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    //测试
    @Test
    public void test2() {
        System.out.print("请输入：");
        System.out.println(MyClass.readInt());
    }
	
	
	
	/*
	打印流PrintStream/PrintWriter
		提供一系列重写print()和println()方法
	 */

    //例3
    @Test
    public void test3() {

        PrintStream printStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\软件安装\\javaee\\jee文件保存\\0Java学习\\a9IOStream_File\\IOS8\\IOS8.txt"));
            printStream = new PrintStream(fileOutputStream);
            if (printStream != null) {
                System.setOut(printStream); //更改数据输出位置（控制台->文件）
            }
            for (int i = 0; i < 255; i++) {
                System.out.print((char) (i));
                if (i % 50 == 0) {
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printStream != null) {
                printStream.close();
            }
        }

    }
	
	
	
	/*
	数据流DataInputStream/DataOutputStream
		用于读取或写出基本数据类型的变量或字符串
	 */

    //例4
    @Test
    public void test4() throws IOException {

        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS8\\IOS8.txt"));
        dataOutputStream.writeUTF("你好");
        dataOutputStream.writeInt(12);
        dataOutputStream.flush(); //刷新操作，将数据写入文件

    }

    @Test
    public void test5() throws IOException {

        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS8\\IOS81.txt"));
        String string = dataInputStream.readUTF(); //读取顺序要与写入顺序相同
        int i = dataInputStream.readInt();
        dataInputStream.close();
        System.out.println(string + i);

    }

}

//例2：模仿Scanner的方法从键盘输入分别获取不同数据类型的数据并返回
class MyClass {

    public static String readString() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        try {
            string = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static double readDouble() {
        return Double.parseDouble(readString());
    }

    //其余类型做类似转换即可

}
