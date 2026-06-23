package a2_CreatorPattern.a2_FactoryPattern.AbstractFactory_Pattern;

/**
 * ClassName: AmericanDessert
 * Package: a2_CreatorModel.a2_FactoryPattern.AbstractFactory_Pattern
 * Description:美式甜品类(测试添加类)
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 9:34
 * @Version: v1.0
 */
public class AmericanDessert extends Dessert{

    @Override
    public void show() {
        System.out.println("美式甜品");
    }

}
