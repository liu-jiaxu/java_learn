package a2_CreatorPattern.a2_FactoryPattern.Factory_Pattern;

/**
 * ClassName: LatteCoffeeFactory
 * Package: a2_CreatorModel.a2_FactoryPattern.Factory_Pattern
 * Description:拿铁咖啡工厂类
 *
 * @Author: zgh296
 * @Create: 2023/4/13 - 14:34
 * @Version: v1.0
 */
public class LatteCoffeeFactory implements CoffeeFactory{

    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }

}
