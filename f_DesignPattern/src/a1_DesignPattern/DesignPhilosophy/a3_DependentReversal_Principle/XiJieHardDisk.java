package a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle;

/**
 * ClassName: XiJieHardDisk
 * Package: a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle
 * Description:具体硬盘类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 20:10
 * @Version: v1.0
 */
public class XiJieHardDisk implements HardDisk{

    public void save(String data) {
        System.out.println("使用希捷硬盘存储数据" + data);
    }
    public String get() {
        System.out.println("使用希捷希捷硬盘取数据");
        return "123abc";
    }

}
