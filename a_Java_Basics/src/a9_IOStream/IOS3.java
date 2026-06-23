package a9_IOStream;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOS3 {

    /*
    文件的写出
    说明：
        1.若原文件不存在则先创建再写出
        2.若原文件存在则覆盖原内容（可指定是否覆盖fileWriter=new FileWriter(file,true);不覆盖，追加内容）
    操作：
        1.创建File对象
        2.创建输入流和输出流对象
        3.数据读入和写出操作
        4.关闭流资源
     */
    @Test
    public void test() {

        FileWriter fileWriter = null;
        try {
            File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS3\\IOS3.txt");
                //找不到路径则直接报错java.io.FileNotFoundException而不是新建文件再添加！
            fileWriter = new FileWriter(file, true); //提供文件流，指定为true则不覆盖原文件内容，改为追加写出
            fileWriter.write("你好！"); //写出内容
            System.out.println("Write to successful！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //例：文件的读写（注：一般文件读写都会分为两个线程分别进行，不会写到一起，对同一文件同时读写时读入会影响写出,当前的写出操作不会在之后的读入读取！）
    @Test
    public void test2() {

        FileWriter fileWriter = null;
        FileReader fileReader = null;
        try {
            File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS3\\IOS31.txt");
            fileWriter = new FileWriter(file, true);
            fileReader = new FileReader(file);
            char[] arr = new char[]{'h', 'e', 'l', 'l', 'o', '!'};
            /*for (int i = 0; i < arr.length; i++) {
                fileWriter.write(arr[i]);
            }*/
            fileWriter.write(arr, 0, arr.length); //此语句相当于上面的循环添加
                /*
                write参数列表：
                    char cbuf[] ： 字符缓冲区，待写出的数组
                     int off    ： 开始写入字符的偏移量（索引）
                     int len    ： 要写入的字符数
                 */
            System.out.println("Write to successful！");
            //读取操作不会读取本次写出的数据，只会读取原始文件中的数据！
            char[] buffer = new char[5];
            int data = fileReader.read(buffer);
            while (data != -1) {
                for (int i = 0; i < data; i++) {
                    System.out.print(buffer[i]);
                }
                data = fileReader.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
