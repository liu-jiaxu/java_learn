package a9_IOStream;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
RandomAccessFile随机存取文件流（随意为输入输出流）
	RandomAccessFile实现DataInput和DataOutput接口
	构造器：public RandomAccessFile(File file,String mode)
		file:指定文件位置
		mode:访问模式r只读 rw读写 rwd读写更新内容 rws读写更新内容+元数据
	补充：RandomAccessFile作为输出流时会从头开始覆盖原文件内容
 */

public class IOS10 {

    //例：RandomAccessFile的使用
    @Test
    public void test() throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt", "rwd");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt", "rwd");

        byte[] buffer = new byte[1024];
        int data = randomAccessFile.read(buffer);
        while (data != -1) {
            randomAccessFile2.write(buffer, 0, data);
            data = randomAccessFile.read(buffer);
        }
        System.out.println("succeed!");
        randomAccessFile.close();

    }

    /*
    例：RandomAccessFile的插入操作（在第三处插入“xyz”）
        1.void seek(long pos)；将文件指针定位到pos位置（默认为0，即最开头）
        2.getBytes(): 使用平台的默认字符集将字符串编码为byte序列，并将结果存储到一个新的byte数组中。
        3.先将插入后会覆盖的内容移出到StringBuilder中，再插入，之后再将StringBuilder中的数据写出到文件中
     */
    @Test
    public void test2() throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt", "rws");
        randomAccessFile.seek(3); //修改指针位置

        StringBuilder stringBuilder = new StringBuilder((int) new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt").length());
        //创建StringBuilder用底层数组存储文件第三处后面的内容

        byte[] buffer = new byte[(int) new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt").length()];
        int data = randomAccessFile.read(buffer); //将randomAccessFile获取的文件内容从指定位置3处开始读取到buffer数组中
        while (data != -1) {
            stringBuilder.append(new String(buffer, 0, data)); //将buffer中的数据添加到stringBuilder对象中
            data = randomAccessFile.read(buffer); //data指针下移
        }
        randomAccessFile.seek(3); //当数据移动完成后，pos指针会随遍历移动到文件末尾，此时需重新指定pos位置
        randomAccessFile.write("xyz".getBytes()); //写入添加数据（pos指针随写入操作自动下移）
        randomAccessFile.write(stringBuilder.toString().getBytes()); //写入最初文件第三处后的数据

        randomAccessFile.close();

    }

    //例：ByteArrayOutputStream流的使用（替代例2中的StringBuilder，原理基本相同，底层都由数组搭建）
    @Test
    public void test3() throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt", "rws");
        randomAccessFile.seek(3); //修改指针位置

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[(int) new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS.txt").length()];
        int data = randomAccessFile.read(buffer); //将randomAccessFile获取的文件内容从指定位置3处开始读取到buffer数组中
        while (data != -1) {
            byteArrayOutputStream.write(buffer, 0, data); //将buffer中的数据添加到stringBuilder对象中
            data = randomAccessFile.read(buffer); //data指针下移
        }
        randomAccessFile.seek(3); //当数据移动完成后，pos指针会随遍历移动到文件末尾，此时需重新指定pos位置
        randomAccessFile.write("xyz".getBytes()); //写入添加数据（pos指针随写入操作自动下移）
        randomAccessFile.write(byteArrayOutputStream.toString().getBytes()); //写入最初文件第三处后的数据

        randomAccessFile.close();

    }

}

//补充：日常开发时都是直接导入jar包使用方法，不用自己写输入输出等操作了，jar包放在java文件目录新建文件夹中即可

