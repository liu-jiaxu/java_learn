package a4_BehavioralPattern.a5_StatePattern.StateLast;

/**
 * ClassName: Context
 * Package: a4_BehavioralPattern.a5_StatePattern.StateLast
 * Description:环境角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 10:42
 * @Version: v1.0
 */
public class Context {

    // 定义对应状态对象的常量
    public final static OpeningState OPENING_STATE = new OpeningState();
    public final static ClosingState CLOSING_STATE  = new ClosingState();
    public final static RunningState RUNNING_STATE = new RunningState();
    public final static StoppingState STOPPING_STATE = new StoppingState();

    // 当前状态变量
    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    // 设置当前状态对象
    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        // 设置当前状态对象中的context对象
        this.liftState.setContext(this);
    }

    public void open(){
        this.liftState.open();
    }

    public void close(){
        this.liftState.close();
    }

    public void run(){
        this.liftState.run();
    }

    public void stop(){
        this.liftState.stop();
    }

}
