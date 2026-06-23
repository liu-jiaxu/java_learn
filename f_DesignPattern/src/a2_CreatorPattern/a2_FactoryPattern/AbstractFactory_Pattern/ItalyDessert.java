package a2_CreatorPattern.a2_FactoryPattern.AbstractFactory_Pattern;

/**
 * ClassName: ItalyDessert
 * Package: a2_CreatorModel.a2_FactoryPattern.AbstractFactory_Pattern
 * Description:意大利甜品类(新增测试类)
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 9:45
 * @Version: v1.0
 */
public class ItalyDessert extends Dessert{


    @Override
    public void show() {
        System.out.println("意大利甜品");
    }
}
