package a4_BehavioralPattern.a1_TemplateMethodPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a1_TemplateMethodPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 11:05
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        //炒手撕包菜
        ConcreteClass_BaoCai baoCai = new ConcreteClass_BaoCai();
        baoCai.cookProcess();

        //炒蒜蓉菜心
        ConcreteClass_CaiXin caiXin = new ConcreteClass_CaiXin();
        caiXin.cookProcess();

    }

}
