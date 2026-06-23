package a4_BehavioralPattern.a1_TemplateMethodPattern;

/**
 * ClassName: ConcreteClass_CaiXin
 * Package: a4_BehavioralPattern.a1_TemplateMethodPattern
 * Description:炒菜心类
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 11:04
 * @Version: v1.0
 */
public class ConcreteClass_CaiXin extends AbstractClass {

    @Override
    public void pourVegetable() {
        System.out.println("下锅的蔬菜是菜心");
    }

    @Override
    public void pourSauce() {
        System.out.println("下锅的酱料是蒜蓉");
    }

}
