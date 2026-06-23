package a2_CreatorPattern.a2_FactoryPattern.SimpleFactory_Pattern;

/**
 * ClassName: SimpleCoffeeFactory
 * Package: a2_CreatorModel.a2_FactoryPattern.SimpleFactory_Pattern
 * Description:简单咖啡工厂类，生产咖啡
 *
 * @Author: zgh296
 * @Create: 2023/4/12 - 11:03
 * @Version: v1.0
 */

public class SimpleCoffeeFactory {

    // 修改为静态方法
    public static Coffee createCoffee(String type){

        // 声明coffee类型的变量，根据不同类型创建子类对象
        Coffee coffee = null;
        if("american".equals(type)){
            coffee = new AmericanCoffee();
        } else if("latte".equals(type)){
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("无此咖啡！");
        }

        return coffee;

    }

    /*
        后期如果再加新品种的咖啡，我们势必要需求修改SimpleCoffeeFactory的代码，违反了开闭原
    则。工厂类的客户端可能有很多，比如创建美团外卖等，这样只需要修改工厂类的代码，省去其他的修
    改操作。
    */

}
