package a3_StructuralPattern.a5_FacadePattern;

/**
 * ClassName: SmartAppliancesFacade
 * Package: a3_StructuralPattern.a5_FacadePattern
 * Description:智能音箱，外观类，用户和其进行交互
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 10:22
 * @Version: v1.0
 */
public class SmartAppliancesFacade {

    // 聚合电灯对象、电视机对象、空调对象
    private Light light;
    private TV tv;
    private AirCondition airCondition;

    public SmartAppliancesFacade(){
        light = new Light();
        tv = new TV();
        airCondition = new AirCondition();
    }

    // 通过语音控制
    public void say(String msg){
        if(msg.equals("打开")){
            on();
        } else if(msg.equals("关闭")){
            off();
        } else if(msg.equals("打开电灯")){
            onLight();
        } else {
            System.out.println("我听不懂");
        }
    }

    // 一键打开
    private void on(){
        light.on();
        tv.on();
        airCondition.on();
    }

    // 一键关闭
    private void off(){
        light.off();
        tv.off();
        airCondition.off();
    }

    // 可以定义其它私有方法用于分别打开不同家具，并修改say方法中的if判断
    private void onLight(){
        light.on();
    }

}
