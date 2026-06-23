package a9_IOStream;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/*
处理流：转换流
	1.InputStreamReader：将一个字节的输入流转换为字符的输入流（解码）
	2.OutputStreamWriter：将一个字符的输出流转换为字节的输出流（编码）
 */

public class IOS7 {

    @Test
    public void test() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS7\\IOS7.txt");
        //可以直接指定文件路径
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream); //以系统默认编码输出
        InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8); //以指定编码输出

        char[] buffer = new char[3];
        int data = inputStreamReader.read(buffer);
        while (data != -1) {
            String string = new String(buffer, 0, data);
            data = inputStreamReader.read(buffer);
            System.out.print(string);
        }

        inputStreamReader.close();

    }

    @Test
    public void test2() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS7\\IOS71.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS7\\IOS72.txt");

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);

        char[] buffer = new char[3];
        int data = inputStreamReader.read(buffer);
        while (data != -1) {
            String string = new String(buffer, 0, data);
            outputStreamWriter.write(buffer);
            data = inputStreamReader.read(buffer);
            System.out.print(string);
        }

        inputStreamReader.close();
        outputStreamWriter.close();

    }
	
	/*
	字符集
		常见的编码表
			ASCII:美国标准信息交换码。用一个字节的7位可以表示
			ISO8859-1:拉丁码表，欧洲码表。用一个字节的8位表示。
			GB2312:中国的中文编码表。最多两个字节编码所有字符
			GBK（ANSI）:中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
			Unicode:国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
			UTF-8:变长的编码方式，可用1-4个字节来表示一个字符。（一个汉字（相当于一个字符）用三个字节保存）
				utf-8不同位数字节保存方式：
					1位：0xxxxxxx
					2位：110xxxxx 10xxxxxx
					3位：1110xxxx 10xxxxxx 10xxxxxx （汉字保存方式，将汉字二进制放入x中保存）
					4位：11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
			Unicode编码：是对utf-8、utf-16等编码方案的统称，而不是具体的编码方案
	 */

}
