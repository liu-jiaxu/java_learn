package a2_CreatorPattern.a1_SingletonPattern.RuntimeDemo;

import java.io.InputStream;

/**
 * ClassName: RuntimeDemo
 * Package: a2_CreatorModel.a1_SingletonPattern.RuntimeDemo
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 16:15
 * @Version: v1.0
 */
public class RuntimeDemo {

    public static void main(String[] args) throws Exception {

        // 获取Runtime类的对象
        Runtime runtime = Runtime.getRuntime();
        // 调用runtime方法，参数为一个命令
        Process process = runtime.exec("ipconfig");
        // 调用process对象的获取输入流的方法
        InputStream inputStream = process.getInputStream();
        byte [] data = new byte[1024*1024*100];
        int len = inputStream.read(data); // 返回读到的字节的个数
        // 将字节数组转换为字符串输出
        System.out.println(new String(data,0,len,"gbk"));

    }

}
