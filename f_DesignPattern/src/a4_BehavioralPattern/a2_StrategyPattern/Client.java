package a4_BehavioralPattern.a2_StrategyPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a2_StrategyPattern
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 15:37
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        SalesMan salesMan = new SalesMan(new StrategyA());
        salesMan.salesManShow();

    }

}
