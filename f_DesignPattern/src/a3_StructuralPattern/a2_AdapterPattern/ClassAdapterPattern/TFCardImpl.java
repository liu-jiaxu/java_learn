package a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern;

/**
 * ClassName: TFCardImpl
 * Package: a3_StructuralPattern.a2_AdapterPattern.ClassAdapterPattern
 * Description:适配者类
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 10:44
 * @Version: v1.0
 */
public class TFCardImpl implements TFCard{

    @Override
    public String readTF() {
        String msg = "TFCard read msg: hello world TF";
        return msg;
    }

    @Override
    public void writeTF(String msg) {
        System.out.println("TFCard write msg: " + msg);
    }

}
