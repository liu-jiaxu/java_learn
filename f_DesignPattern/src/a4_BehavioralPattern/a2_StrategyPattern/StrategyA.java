package a4_BehavioralPattern.a2_StrategyPattern;

/**
 * ClassName: StrategyA
 * Package: a4_BehavioralPattern.a2_StrategyPattern
 * Description:具体策略类，封装算法
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 15:32
 * @Version: v1.0
 */
public class StrategyA implements Strategy{

    @Override
    public void show() {
        System.out.println("买一送一");
    }

}
