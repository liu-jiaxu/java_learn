package a4_BehavioralPattern.a3_CommandPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Waitor
 * Package: a4_BehavioralPattern.a3_CommandPattern
 * Description:服务员类，请求者角色
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 16:46
 * @Version: v1.0
 */
public class Waitor {

    // 持有多个命令对象
    private List<Command> commands = new ArrayList<Command>();

    public void setCommand(Command cmd) {
        commands.add(cmd);
    }

    // 发起命令的功能
    public void orderUp(){
        System.out.println("新订单来了");
        // 遍历list集合
        for(Command command:commands){
            if(command != null){
                command.execute();
            }
        }
    }

}
