package a2_CreatorPattern.a2_FactoryPattern.Factory_Pattern;

/**
 * ClassName: CoffeeStore
 * Package: a2_CreatorModel.a2_FactoryPattern.SimpleFactory_Pattern
 * Description:咖啡店类
 *
 * @Author: zgh296
 * @Create: 2023/4/12 - 10:57
 * @Version: v1.0
 */
public class CoffeeStore {

    private CoffeeFactory coffeeFactory;

    public void setCoffeeFactory(CoffeeFactory coffeeFactory) {

        this.coffeeFactory = coffeeFactory;

    }

    public Coffee orderCoffee(){

        Coffee coffee = coffeeFactory.createCoffee();
        coffee.addMilk();
        coffee.addSugar();

        return coffee;

    }

}
