package a3_StructuralPattern.a2_AdapterPattern.ObjectAdapterPattern;

import a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern.TFCard;

/**
 * ClassName: SDAdapterTF
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:适配器类
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:53
 * @Version: v1.0
 */

// 注：还有一个适配器模式是接口适配器模式。当不希望实现一个接口中所有的方法时，
// 可以创建一个抽象类Adapter，实现所有方法。而此时我们只需要继承该抽象类即可。
public class SDAdapterTF implements SDCard {

    // 声明适配者类
    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard){
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return tfCard.readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        tfCard.writeTF(msg);
    }

}
