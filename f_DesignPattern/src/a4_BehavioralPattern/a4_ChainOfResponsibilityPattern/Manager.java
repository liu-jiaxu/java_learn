package a4_BehavioralPattern.a4_ChainOfResponsibilityPattern;

/**
 * ClassName: GroupLeader
 * Package: a4_BehavioralPattern.a4_ChainOfResponsibilityPattern
 * Description:部门经理类，具体的处理者
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 9:27
 * @Version: v1.0
 */
public class Manager extends Handler{

    public Manager() {
        super(Handler.NUM_ONE,Handler.NUM_THREE);
    }

    @Override
    protected void handleLeave(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getName() + "请假" + leaveRequest.getNum() + "天" + leaveRequest.getContent() + "。");
        System.out.println("部门经理审批同意");
    }

}
