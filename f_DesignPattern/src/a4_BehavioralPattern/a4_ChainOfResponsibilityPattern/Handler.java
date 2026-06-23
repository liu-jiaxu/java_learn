package a4_BehavioralPattern.a4_ChainOfResponsibilityPattern;

/**
 * ClassName: Handler
 * Package: a4_BehavioralPattern.a4_ChainOfResponsibilityPattern
 * Description:抽象处理者类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 9:16
 * @Version: v1.0
 */
public abstract class Handler {

    protected final static int NUM_ONE = 1;
    protected final static int NUM_THREE = 3;
    protected final static int NUM_SEVEN = 7;

    // 该领导处理的请求天数的区间
    private int numStart;
    private int numEnd;

    // 声明后续者(上级领导)
    private Handler nextHandler;

    public Handler(int numStart) {
        this.numStart = numStart;
    }

    public Handler(int numStart, int numEnd) {
        this.numStart = numStart;
        this.numEnd = numEnd;
    }

    // 设置上级领导对象
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 各级领导处理请假条的方法
    protected abstract void handleLeave(LeaveRequest leaveRequest);

    // 提交请假条
    protected final void submit(LeaveRequest leaveRequest){
        // 该领导是否审批
        this.handleLeave(leaveRequest);
        if(this.nextHandler != null && leaveRequest.getNum() > this.numEnd){
            // 提交给上级领导审批
            this.nextHandler.submit(leaveRequest);
        } else {
            System.out.println("流程结束！");
        }
    }


}
