package a4_BehavioralPattern.a5_StatePattern.StateLast;

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

        // 创建环境角色对象
        Context context = new Context();
        // 设置当前电梯装填
        context.setLiftState(new ClosingState());

        context.open();
        context.close();
        context.run();
        context.stop();

    }

}