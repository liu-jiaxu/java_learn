package a2_CreatorPattern.a2_FactoryPattern.SimpleFactory_Pattern;

/**
 * ClassName: Coffee
 * Package: a2_CreatorModel.a2_FactoryPattern.SimpleFactory_Pattern
 * Description:咖啡类
 *
 * @Author: zgh296
 * @Create: 2023/4/12 - 10:52
 * @Version: v1.0
 */
public abstract class Coffee {

    // 获取名称
    public abstract String getName();

    // 加糖
    public void addSugar(){
        System.out.println("加糖");
    }

    // 加奶
    public void addMilk(){
        System.out.println("加奶");
    }

}
