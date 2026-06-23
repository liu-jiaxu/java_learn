package a9_IOStream;

import org.junit.Test;

import java.io.*;

/*
注：
	对于文本文件（txt/java/c/cpp...），使用字符流处理read/write
	对于非文本文件（jpg/mp4/mp3/avi/doc/ppt...）：使用字节流处理input/outputstream
 */

public class IOS4 {

    //字节流的使用
    //例：使用字节流操作图片的读写
    @Test
    public void test() {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File srcfile = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS4\\IOS4.jpg");
            File destfile = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS4\\IOS42.jpg");
            fileInputStream = new FileInputStream(srcfile);
            fileOutputStream = new FileOutputStream(destfile);
            try {
                //先读取某个文件，在赋值给新的文件，相当于文件复制
                //注意牢记这里的读写操作！！！
                byte[] buffer = new byte[5];
                int data = fileInputStream.read(buffer);
                while (data != -1) {
                    fileOutputStream.write(buffer, 0, data);
                    //注意这里，表示每次写出data个数据，不重载write方法可能会出现写出异常（原因和读入异常相同，都是数组大小引发的问题）
                    data = fileInputStream.read(buffer);
                }
                System.out.println("succeed!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 字节流文件复制通用操作
     * @param srcfile 原始文件名称（绝对路径）
     * @param destfile 复制后的文件名称（绝对路径）
     */
    public void copyfile(String srcfile, String destfile) {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(srcfile);
            File file2 = new File(destfile);
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(file2);
            try {
                byte[] buffer = new byte[1024];
                //这里的数组大小决定一次输入多少字节的数据，数组越大一次处理的数据越多，所用时间越少，但内存开销随之增大
                int data = fileInputStream.read(buffer);
                while (data != -1) {
                    fileOutputStream.write(buffer);
                    data = fileInputStream.read(buffer);
                }
                System.out.println("succeed!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    public static void copyfile2(String srcfile, String destfile) {

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            File file1 = new File(srcfile);
            File file2 = new File(destfile);
            fileReader = new FileReader(file1);
            fileWriter = new FileWriter(file2);
            char[] buffer = new char[1024];
            int data = fileReader.read(buffer);
            while (data != -1) {
                fileWriter.write(buffer, 0, data);
                data = fileReader.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test2() {

        //对于视频复制时间测试
        long start = System.currentTimeMillis();
        copyfile("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS4\\IOS43.mp4", "D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS4\\IOS44.mp4");
        long end = System.currentTimeMillis();
        System.out.println("Time:" + (end - start)); //10ms左右

    }
	
	/*
	注：
		若文件中存在中文等非英文字符，使用字符流可以正常读写（因为读写会以一个字符为单位），但使用字节流读入时会出现乱码，
	字节流读取数据时，由于指定了数组大小且会以字节为单位读取数据，所以当数组还未被之前字节填满但又读取到中文后，会将中文
	对应的字节拆开，之后读取在控制台上的数组中数据就变成了乱码，而字节流的写出操作只是单纯地搬运数据，所以不会有影响。
	总结：
	    字节流：读MP4/jpg等文件，不可读特殊字符
	    字符流：读txt/java等文件，可读特殊字符
	 */

}
