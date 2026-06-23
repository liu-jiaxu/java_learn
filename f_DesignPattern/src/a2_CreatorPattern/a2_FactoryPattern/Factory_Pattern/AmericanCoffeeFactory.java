package a2_CreatorPattern.a2_FactoryPattern.Factory_Pattern;

/**
 * ClassName: AmericanCoffeeFactory
 * Package: a2_CreatorModel.a2_FactoryPattern.Factory_Pattern
 * Description:美式咖啡工厂类
 *
 * @Author: zgh296
 * @Create: 2023/4/13 - 14:33
 * @Version: v1.0
 */
public class AmericanCoffeeFactory implements CoffeeFactory{

    @Override
    public Coffee createCoffee() {

        return new AmericanCoffee();

    }

}

