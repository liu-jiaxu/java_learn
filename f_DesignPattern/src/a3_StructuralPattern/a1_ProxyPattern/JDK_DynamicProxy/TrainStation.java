package a3_StructuralPattern.a1_ProxyPattern.JDK_DynamicProxy;

/**
 * ClassName: TrainStation
 * Package: a3_StructuralPattern.a1_ProxyPattern.StaticProxy
 * Description:火车站卖票
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 15:22
 * @Version: v1.0
 */
public class TrainStation implements SellTickets {

    @Override
    public void sell() {
        System.out.println("火车站卖票");
    }

}
