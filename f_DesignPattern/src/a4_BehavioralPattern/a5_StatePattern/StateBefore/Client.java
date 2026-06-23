package a4_BehavioralPattern.a5_StatePattern.StateBefore;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a5_StatePattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 10:32
 * @Version: v1.0
 */

public class Client {

    public static void main(String[] args) {

        Lift lift = new Lift();
        lift.setState(ILift.STOPPING_STATE);//电梯是停止的
        lift.open();//开门
        lift.close();//关门
        lift.run();//运行
        lift.stop();//停止

    }

}