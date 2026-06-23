package a3_StructuralPattern.a1_ProxyPattern.StaticProxy;

/**
 * ClassName: ProxyPoint
 * Package: a3_StructuralPattern.a1_ProxyPattern.StaticProxy
 * Description:代理卖火车票
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 15:22
 * @Version: v1.0
 */
public class ProxyPoint implements SellTickets{

    private TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        System.out.println("代理点收取一些服务费用");
        trainStation.sell();
    }

}
