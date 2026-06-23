package a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern;

/**
 * ClassName: Client
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:51
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建计算机对象
        Computer computer = new Computer();
        // 读取SD卡数据
        String msg = computer.readSD(new SDCardImpl());
        System.out.println(msg);

        // 使用该计算机读取TF卡中的数据
        String msg2 = computer.readSD(new SDAdapterTF());
        System.out.println(msg2);

    }

}
