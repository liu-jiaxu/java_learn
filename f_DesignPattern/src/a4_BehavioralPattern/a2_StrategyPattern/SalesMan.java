package a4_BehavioralPattern.a2_StrategyPattern;

/**
 * ClassName: SalesMan
 * Package: a4_BehavioralPattern.a2_StrategyPattern
 * Description:环境角色Context：用于连接上下文，即把促销活动推销给客户，这里可以理解为销售员
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 15:35
 * @Version: v1.0
 */

public class SalesMan {

    //持有抽象策略角色的引用
    private Strategy strategy;

    public SalesMan(Strategy strategy) {
        this.strategy = strategy;
    }
    //向客户展示促销活动
    public void salesManShow(){
        strategy.show();
    }

}
