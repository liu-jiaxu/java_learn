package a4_BehavioralPattern.a1_TemplateMethodPattern;

/**
 * ClassName: ConcreteClass_BaoCai
 * Package: a4_BehavioralPattern.a1_TemplateMethodPattern
 * Description:炒包菜类
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 11:03
 * @Version: v1.0
 */
public class ConcreteClass_BaoCai extends AbstractClass {

    @Override
    public void pourVegetable() {
        System.out.println("下锅的蔬菜是包菜");
    }

    @Override
    public void pourSauce() {
        System.out.println("下锅的酱料是辣椒");
    }

}
