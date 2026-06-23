package a4_BehavioralPattern.a3_CommandPattern;

import java.util.Map;
import java.util.Set;

/**
 * ClassName: OrderCommand
 * Package: a4_BehavioralPattern.a3_CommandPattern
 * Description:具体命令类
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 16:42
 * @Version: v1.0
 */
public class OrderCommand implements Command{

    // 持有接受者对象
    private SeniorChef receiver;
    private Order order;

    public OrderCommand(SeniorChef receiver, Order order) {
        this.receiver = receiver;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println(order.getDiningTable() + "桌的订单：");
        Map<String,Integer> foodDir = order.getFoodDir();
        Set<String> keys = foodDir.keySet();
        for (String foodName : keys) {
            receiver.makeFood(foodName,foodDir.get(foodName));
        }
        System.out.println(order.getDiningTable() + "桌的饭准备完毕");
    }

}
