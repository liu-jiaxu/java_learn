package a4_BehavioralPattern.a4_ChainOfResponsibilityPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a4_ChainOfResponsibilityPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 9:33
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建请假条对象
        LeaveRequest leaveRequest = new LeaveRequest("肖敏",2,"回家");
        LeaveRequest leaveRequest2 = new LeaveRequest("李四",10,"身体不适");

        // 创建各级领导对象
        GroupLeader groupLeader = new GroupLeader();
        Manager manager = new Manager();
        GeneralLeader generalLeader = new GeneralLeader();

        // 设置处理者链
        groupLeader.setNextHandler(manager);
        manager.setNextHandler(generalLeader);

        // 肖敏提交请假申请
        groupLeader.submit(leaveRequest);
        groupLeader.submit(leaveRequest2);

    }

}
