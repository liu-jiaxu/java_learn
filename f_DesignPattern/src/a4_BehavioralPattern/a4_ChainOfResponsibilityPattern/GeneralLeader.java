package a4_BehavioralPattern.a4_ChainOfResponsibilityPattern;

/**
 * ClassName: GroupLeader
 * Package: a4_BehavioralPattern.a4_ChainOfResponsibilityPattern
 * Description:总经理类，具体的处理者
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 9:27
 * @Version: v1.0
 */
public class GeneralLeader extends Handler{

    public GeneralLeader() {
        super(Handler.NUM_THREE,Handler.NUM_SEVEN);
    }

    @Override
    protected void handleLeave(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getName() + "请假" + leaveRequest.getNum() + "天" + leaveRequest.getContent() + "。");
        System.out.println("总经理审批同意");
    }

}
