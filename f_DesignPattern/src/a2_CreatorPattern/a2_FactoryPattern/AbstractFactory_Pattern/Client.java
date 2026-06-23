package a2_CreatorPattern.a2_FactoryPattern.AbstractFactory_Pattern;

/**
 * ClassName: Client
 * Package: a2_CreatorModel.a2_FactoryPattern.AbstractFactory_Pattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 9:27
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建意大利风味甜品工厂
        ItalyDessertFactory factory = new ItalyDessertFactory();
        // 获取拿铁coffee和提拉米苏
        Coffee coffee = factory.createCoffee();
        Dessert dessert = factory.createDessert("ItalyDessert");
        Dessert dessert2 = factory.createDessert("Trimisu");

        System.out.println(coffee.getName());
        dessert.show();
        dessert2.show();

    }

}
