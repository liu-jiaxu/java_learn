package a4_BehavioralPattern.a4_ChainOfResponsibilityPattern;

/**
 * ClassName: GroupLeader
 * Package: a4_BehavioralPattern.a4_ChainOfResponsibilityPattern
 * Description:小组长类，具体的处理者
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 9:27
 * @Version: v1.0
 */
public class GroupLeader extends Handler{

    public GroupLeader() {
        super(0,Handler.NUM_ONE);
    }

    @Override
    protected void handleLeave(LeaveRequest leaveRequest) {
        if(leaveRequest.getNum() > Handler.NUM_SEVEN) {
            System.out.println(leaveRequest.getName() + "请假失败，请假天数不得超过一周！");
            System.exit(0);
        } else {
            System.out.println(leaveRequest.getName() + "请假" + leaveRequest.getNum() + "天" + leaveRequest.getContent() + "。");
            System.out.println("小组长审批同意");
        }
    }

}
