package a9_IOStream;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/*
File的使用
	1.File类的一个对象，代表一个文件或一个文件目录（文件夹）
	2.File类声明在java.io包下
 */

public class IOS1 {

    //创建File类的实例
    @Test
    public void test() {

        //构造器1：File(String filePath)
        File file = new File("IOS1.txt"); //相对路径
        File file2 = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS1\\IOS1.txt"); //绝对路径
        //Windows和DOS下分隔符用“\”，UNIX和URL使用“/”，可以用File.separator代替
        System.out.println(file);
        System.out.println(file2); //输出的是文件路径

        //构造器2：File(String parentPath,String childPath)，childPath可以是文件/文件夹
        File file3 = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File", "IOS1");
        System.out.println(file3);

        //构造器3：File(File file,String chilePath)，childPath可以是文件/文件夹
        File file4 = new File(file3, "IOS1.txt");
        System.out.println(file4);

    }

    //File类的获取、重命名功能
    @Test
    public void test2() {

        File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS1\\IOS1.txt");
        File file2 = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS1");

        //获取绝对路径
        System.out.println(file.getAbsolutePath());

        //获取路径
        System.out.println(file.getPath());

        //获取名称
        System.out.println(file.getName());

        //获取上层文件目录路径。若无返回null
        System.out.println(file.getParent());

        //获取文件内容长度
        System.out.println(file.length());

        //获取最后一次的修改时间（毫秒值）
        System.out.println(file.lastModified());
        System.out.println(new SimpleDateFormat().format(new Date(file.lastModified()))); //返回常规目期格式

        //获取指定目录下的所有文件或者文件目录的名称（绝对路径）数组
        File[] string = file2.listFiles();
		if (string != null) {
			for (File s : string) {
				System.out.println(s);
			}
		}

		//获取指定目录下的所有文件或者文件目录的名称（仅获取名称）数组
        String[] string2 = file2.list();
		if (string2 != null) {
			for (String s : string2) {
				System.out.println(s);
			}
		}

		//把文件file重命名为指定的文件路径file2
        System.out.println(file.renameTo(file2)); //file文件必须存在且file2不存在，否则false

    }

    //File类的判断功能
    @Test
    public void test3() {

        File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS1\\IOS11.txt");

        System.out.println(file.isDirectory()); //判断文件是否为文件目录
        System.out.println(file.isFile()); //判断是否是文件
        System.out.println(file.exists()); //判断文件是否存在（尽量先判断！）
        System.out.println(file.canRead()); //判断可读
        System.out.println(file.canWrite()); //判断可写
        System.out.println(file.isHidden()); //判断隐藏

    }

    //File类的创建、删除功能
    //File类涉及到文件内容的操作要用到IO流
    @Test
    public void test4() {

        File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS1\\IOS12.txt");
        File file2 = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS2");

        //创建文件，若文件存在，则不创建，返回false（结合exists使用）
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("创建成功！");
        }

        //创建文件目录，如果此文件目录存在，就不创建了（如果此文件目录的上层目录不存在，也不创建）
        System.out.println(file.mkdir());

        //创建文件目录。如果上层文件目录不存在，一并创建（注:如果创建文件或者文件目录没有写盘符路径，那么默认在项目路径下）
        System.out.println(file2.mkdir());

        //删除文件（无法删除文件夹！）
        System.out.println(file2.delete());

    }

    @Test
    public void test5() {

        //例1：创建一个与file同目录下的另一个文件IOS13.txt，之后删除文件IOS13.txt
        File file = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS1\\IOS12.txt");
        File file2 = new File(file.getParent(), "IOS13.txt");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
                System.out.println("Creating a successful!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("Create a failure");
        }

        if (file2.exists()) {
            file2.delete();
        }

        //例2：遍历指定目录下所有文件名称并计算指定目录名称占用空间大小
        File file3 = new File("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS1");
        String[] string = file3.list();
        int byteNum = 0;
        if (string != null) {
            for (String s : string) {
                byteNum += s.length();
                System.out.println(s);
            }
        }
        System.out.println(byteNum);

    }

}
