package a4_BehavioralPattern.a5_StatePattern.StateLast;

/**
 * ClassName: OpeningState
 * Package: a4_BehavioralPattern.a5_StatePattern.StateLast
 * Description:电梯开启状态类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 10:44
 * @Version: v1.0
 */

public class OpeningState extends LiftState{

    // 当前状态要执行的方法
    @Override
    public void open() {
        System.out.println("电梯开启...");
    }

    @Override
    public void close() {
        // 修改状态
        super.context.setLiftState(Context.CLOSING_STATE);
        // 调用当前状态中的close方法
        super.context.close();
    }

    @Override
    public void run() {
        // 什么都不做
    }

    @Override
    public void stop() {
        // 什么都不做
    }

}
