package a2_CreatorPattern.a2_FactoryPattern.AbstractFactory_Pattern;

/**
 * ClassName: AmericanDessertFactory
 * Package: a2_CreatorModel.a2_FactoryPattern.AbstractFactory_Pattern
 * Description:美式甜点工厂，生产美式coffee和抹茶慕斯
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 9:24
 * @Version: v1.0
 */
public class AmericanDessertFactory implements DessertFactory {

    public Coffee createCoffee() {
        return new AmericanCoffee();
    }

    public Dessert createDessert(String dessertName) {

        switch (dessertName){
            case "MatchaMousse":
                return new MatchaMousse();
            case "AmericanDessert":
                return new AmericanDessert();
            default:
                System.out.println("没有此甜品");
                return null;
        }

    }

}
