package a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern;

/**
 * ClassName: TFCard
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:适配者类的接口
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:43
 * @Version: v1.0
 */
public interface TFCard {

    // 从TF卡中读数据
    String readTF();

    // 向TF卡中写数据
    void writeTF(String msg);

}
