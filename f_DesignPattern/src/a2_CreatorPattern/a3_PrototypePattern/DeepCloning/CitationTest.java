package a2_CreatorPattern.a3_PrototypePattern.DeepCloning;

import java.io.*;

/**
 * ClassName: CitationTest
 * Package: a2_CreatorPattern.a3_PrototypePattern.DeepCloning
 * Description:深复制测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 16:14
 * @Version: v1.0
 */
public class CitationTest {

    public static void main(String[] args) throws Exception {

        Citation c1 = new Citation();
        Student stu = new Student("张三", "西安");
        c1.setStu(stu);
        //创建对象输出流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\f_DesignPattern\\src\\a2_CreatorPattern\\a3_PrototypePattern\\DeepCloning\\stu.txt"));
        //将c1对象写出到文件中
        oos.writeObject(c1);
        oos.close();
        //创建对象出入流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\f_DesignPattern\\src\\a2_CreatorPattern\\a3_PrototypePattern\\DeepCloning\\stu.txt"));
        //读取对象
        Citation c2 = (Citation) ois.readObject();
        ois.close();
        //获取c2奖状所属学生对象
        Student stu1 = c2.getStu();
        stu1.setName("李四");
        //判断stu对象和stu1对象是否是同一个对象
        System.out.println("stu和stu1是同一个对象？" + (stu == stu1));
        c1.show();
        c2.show();

    }

    // 注意：因为要序列化操作，所以Citation类和Student类必须实现Serializable接口，否则会抛NotSerializableException异常。

}