package a2_CreatorPattern.a2_FactoryPattern.Factory_Pattern;

/**
 * ClassName: Client
 * Package: a2_CreatorModel.a2_FactoryPattern.Factory_Pattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/13 - 14:41
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建咖啡店对象
        CoffeeStore coffeeStore = new CoffeeStore();
        // 创建对象
        CoffeeFactory coffeeFactory = new AmericanCoffeeFactory();
        coffeeStore.setCoffeeFactory(coffeeFactory);
        // 点咖啡
        Coffee coffee = coffeeStore.orderCoffee();
        System.out.println(coffee.getName());

    }

}
