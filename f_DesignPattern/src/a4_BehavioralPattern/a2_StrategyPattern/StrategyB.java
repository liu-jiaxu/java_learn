package a4_BehavioralPattern.a2_StrategyPattern;

/**
 * ClassName: StrategyB
 * Package: a4_BehavioralPattern.a2_StrategyPattern
 * Description:具体策略类，封装算法
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 15:33
 * @Version: v1.0
 */

public class StrategyB implements Strategy {

    public void show() {
        System.out.println("满200元减50元");
    }

}
