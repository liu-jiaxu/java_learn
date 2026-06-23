package a3_StructuralPattern.a2_AdapterPattern.ObjectAdapterPattern;

/**
 * ClassName: Computer
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:计算机类
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:49
 * @Version: v1.0
 */
public class Computer {

    // 从SD卡中读取数据
    public String readSD(SDCard sdCard){
        if(sdCard == null){
            throw new NullPointerException("sd card is not null!");
        }
        return sdCard.readSD();
    }

}
