package a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern;

/**
 * ClassName: SDCardImpl
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:具体的SD卡类
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:48
 * @Version: v1.0
 */
public class SDCardImpl implements SDCard{

    @Override
    public String readSD() {
        return "SDCard read msg: hello world SD";
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("SDCard write msg: " + msg);
    }

}
