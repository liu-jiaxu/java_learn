package a2_CreatorPattern.a2_FactoryPattern.AbstractFactory_Pattern;

/**
 * ClassName: DessertFactory
 * Package: a2_CreatorModel.a2_FactoryPattern.AbstractFactory_Pattern
 * Description:抽象工厂类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 9:23
 * @Version: v1.0
 */
public interface DessertFactory {

    // 生产coffee
    Coffee createCoffee();

    // 生产甜品
    Dessert createDessert(String dessertName);

    // 当新增同一产品族时，需新建工厂类并在该接口添加对应方法即可，同时修改所有实现类！

}
