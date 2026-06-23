package a2_CreatorPattern.a2_FactoryPattern.AbstractFactory_Pattern;

/**
 * ClassName: ItalyDessertFactory
 * Package: a2_CreatorModel.a2_FactoryPattern.AbstractFactory_Pattern
 * Description:意大利风味甜点工厂，生产拿铁coffee和提拉米苏
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 9:25
 * @Version: v1.0
 */
public class ItalyDessertFactory implements DessertFactory {

    public Coffee createCoffee() {
        return new LatteCoffee();
    }

    // 新增新产品时，需要修改对应类
    public Dessert createDessert(String dessertName) {

        switch (dessertName){
            case "Trimisu":
                return new Trimisu();
            case "ItalyDessert":
                return new ItalyDessert();
            default:
                System.out.println("没有此甜品");
                return null;
        }

    }

}
