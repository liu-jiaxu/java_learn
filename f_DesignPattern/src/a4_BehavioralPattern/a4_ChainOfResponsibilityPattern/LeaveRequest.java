package a4_BehavioralPattern.a4_ChainOfResponsibilityPattern;

/**
 * ClassName: LeaveRequest
 * Package: a4_BehavioralPattern.a4_ChainOfResponsibilityPattern
 * Description:请假条类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 9:14
 * @Version: v1.0
 */
public class LeaveRequest {

    // 姓名
    private String name;

    // 请假天数
    private int num;

    // 请假内容
    private String content;

    public LeaveRequest(String name, int num, String content) {
        this.name = name;
        this.num = num;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
