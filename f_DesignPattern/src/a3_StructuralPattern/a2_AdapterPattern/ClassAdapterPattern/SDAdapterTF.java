package a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern;

/**
 * ClassName: SDAdapterTF
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:适配器类
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:53
 * @Version: v1.0
 */

// 类适配器模式违背了合成复用原则。类适配器是客户类有一个接口规范的情况下可用，反之不可用(因为适配器类不能继承多个父类)。
public class SDAdapterTF extends TFCardImpl implements SDCard{

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        writeTF(msg);
    }

}
