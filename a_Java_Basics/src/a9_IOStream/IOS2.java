package a9_IOStream;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//IO流：处理设备之间的数据传输，以stream流的方式进行
/*
要站位在程序（内存）角度看：
	文件->内存 input输入（读）
	内存->文件 output输出（写）
 */

//数据传输的基本单位：字节流（8bit），字符流（16bit，不能处理图片等字节流）

/*
节点流：直接连接文件与内存的流
处理流：包裹在节点流外侧的其它流
 */

/*
	抽象基类	      字节流 	   	字符流
	 输入流	   InputStream		Reader
	 输出流	   OutputStream		Writer
 */

/*
抽象基类        节点流（文件流）     缓冲流（处理流的一种）
InputStream		FileInputStream		BufferedInputStream
OutputStream	FileOutputStream	BufferedOutputStream
Reader			FileReader			BufferedReader
Writer			FileWriter			BufferedWriter
 */

public class IOS2 {

    //字符流的使用

    /*
    文件读取操作：
        1.File类实例化
        2.FileReader流实例化
        3.读入操作
        4.资源关闭
     */
    @Test
    public void test() {

        FileReader fileReader = null;
        try {
            File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\src\\a9_IOStream\\IOS2.java");
            fileReader = new FileReader(file); //提供文件流
            try {
                //read()返回读入的一个字符，若达到文件末尾则返回-1
                //读取文件内容
                int data = fileReader.read();
                while (data != -1) {
                    System.out.print((char) data);
                    data = fileReader.read(); //读完一个字符后会自动下移到下一个字符，此时指定read方法会指向下一个字符
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close(); //文件操作最后必须关闭
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    //文件读取操作优化
    @Test
    public void test2() {

        FileReader fileReader = null;
        try {
            File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS2\\IOS2.txt");
            fileReader = new FileReader(file); //提供文件流
            try {
                //read()返回读入的字符，若达到文件末尾则返回-1
                //读取文件内容
                char[] buffer = new char[5]; //规定每次读取字符的个数
                int data = fileReader.read(buffer);
                //传入buffer数组后，指定read方法一次读取的字符长度为数组长度，同时将fileReader文件流中的字符
                //读取到buffer数组中，并返回一次读取字符的长度5
                while (data != -1) {
                    /*for (int i = 0; i < buffer.length; i++) {
                        System.out.print(buffer[i]);
                    }*/
					    /*
					        注：不要使用buffer.length作为循环条件！
					        例：当文件有9个字符123456789时，每次读取5个，则第一次读取字符12345并存储到buffer数组中，
					    经过循环遍历后，继续第二次读取剩余数据并将字符再次加入buffer，此时仅剩四个字符，加入buffer数组
					    时会覆盖第一次读取的数据，变成了67895，而再用数组长度作为循环条件，会一并将字符5也读取出来，因此
					    此处应该使用data返回的读取的字符数量作为循环条件！
					     */

                    for (int i = 0; i < data; i++) {
                        System.out.print(buffer[i]);
                    }
                    data = fileReader.read(buffer);
                        /*
                            for循环执行完毕后，读取了buffer数组中的字符，此时继续读取文件流中的数据，更新buffer数组中的内容，
                        进行下一次循环遍历，以此往复直至数据读完data返回-1
                         */
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close(); //文件操作最后必须关闭
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
