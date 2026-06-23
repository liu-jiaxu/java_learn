package a3_StructuralPattern.a5_FacadePattern;

/**
 * ClassName: Client
 * Package: a3_StructuralPattern.a5_FacadePattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 10:28
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建智能音箱对象
        SmartAppliancesFacade smartAppliancesFacade = new SmartAppliancesFacade();
        // 控制家电
        smartAppliancesFacade.say("打开");
        smartAppliancesFacade.say("关闭");
        smartAppliancesFacade.say("打开电灯");

    }

}
