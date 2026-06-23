package a2_CreatorPattern.a2_FactoryPattern.SimpleFactory_Pattern;

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

    public Coffee orderCoffee(String type){

        /*// 简单工厂对象
        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
        Coffee coffee = factory.createCoffee(type);*/

        // 修改为静态方法后直接调用即可返回coffee类
        Coffee coffee = SimpleCoffeeFactory.createCoffee(type);

        // 加配料
        coffee.addSugar();
        coffee.addMilk();

        return coffee;

    }

}
