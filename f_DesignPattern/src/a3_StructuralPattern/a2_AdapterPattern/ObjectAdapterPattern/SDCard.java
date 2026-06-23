package a3_StructuralPattern.a2_AdapterPattern.ObjectAdapterPattern;

/**
 * ClassName: SDCard
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:目标接口
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:47
 * @Version: v1.0
 */
public interface SDCard {

    // 从SD卡中读数据
    String readSD();

    // 向SD卡中写数据
    void writeSD(String msg);

}
