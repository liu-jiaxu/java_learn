package a2_CreatorPattern.a1_SingletonPattern.Break_Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ClassName: Client_Serializable_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern.Break_Singleton
 * Description:序列化破坏单例模式测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 14:46
 * @Version: v1.0
 */
public class Client_Serializable_Singleton {

    public static void main(String[] args) throws Exception {

        //往文件中写对象
        writeObject2File();
        //从文件中读取对象
        Serializable_Singleton s1 = readObjectFromFile();
        Serializable_Singleton s2 = readObjectFromFile();
        //判断两个反序列化后的对象是否是同一个对象
        System.out.println(s1 == s2);
    }

    // 从文件中读数据(对象)
    private static Serializable_Singleton readObjectFromFile() throws Exception {
        //创建对象输入流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream
            ("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\f_DesignPattern\\src\\a2_CreatorModel\\a1_SingletonPattern\\Break_Singleton\\a.txt"));
        //第一个读取Singleton对象
        Serializable_Singleton instance = (Serializable_Singleton) ois.readObject();
        // 释放资源
        ois.close();
        return instance;
    }

    // 向文件中写数据(对象)
    public static void writeObject2File() throws Exception {
        //获取Singleton类的对象
        Serializable_Singleton instance = Serializable_Singleton.getInstance();
        //创建对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream
            ("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\f_DesignPattern\\src\\a2_CreatorModel\\a1_SingletonPattern\\Break_Singleton\\a.txt"));
        //将instance对象写出到文件中
        oos.writeObject(instance);
        // 释放资源
        oos.close();
    }

}
