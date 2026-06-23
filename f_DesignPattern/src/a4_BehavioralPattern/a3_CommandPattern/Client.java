package a4_BehavioralPattern.a3_CommandPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a3_CommandPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 16:49
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建第一个订单对象
        Order order = new Order();
        order.setDiningTable(1);
        order.setFood("西红柿鸡蛋面",1);
        order.setFood("小杯可乐",1);

        // 创建第二个订单对象
        Order order2 = new Order();
        order2.setDiningTable(2);
        order2.setFood("尖椒肉丝",1);
        order2.setFood("小杯雪碧",2);

        // 创建厨师对象
        SeniorChef receiver = new SeniorChef();

        // 创建命令对象
        OrderCommand cmd = new OrderCommand(receiver,order);
        OrderCommand cmd2 = new OrderCommand(receiver,order2);

        // 创建调用者(服务员对象)
        Waitor waitor = new Waitor();
        waitor.setCommand(cmd);
        waitor.setCommand(cmd2);

        // 让服务员发起命令
        waitor.orderUp();

    }

}
