package a4_BehavioralPattern.a2_StrategyPattern;

/**
 * ClassName: StrategyC
 * Package: a4_BehavioralPattern.a2_StrategyPattern
 * Description:具体策略类，封装算法
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 15:34
 * @Version: v1.0
 */

public class StrategyC implements Strategy {

    public void show() {
        System.out.println("满1000元加一元换购任意200元以下商品");
    }

}
