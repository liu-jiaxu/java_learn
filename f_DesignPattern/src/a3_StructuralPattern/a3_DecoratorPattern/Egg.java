package a3_StructuralPattern.a3_DecoratorPattern;

/**
 * ClassName: Egg
 * Package: a3_StructuralPattern.a3_DecoratorPattern
 * Description:鸡蛋类，具体装饰角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 18:25
 * @Version: v1.0
 */
public class Egg extends Garnish {

    public Egg(FastFood fastFood) {
        super(fastFood, 1, "鸡蛋");
    }

    // 计算价格，鸡蛋价格加快餐价格
    @Override
    public float cost() {
        return getPrice() + getFastFood().cost();
    }

    /*
        通过Egg构造方法的super已经将三个参数传入了父类的变量中，即父类的变量被赋值为子类传入的参数了，
    之后super.getDesc()调用的是父类的getDesc方法，获取子类传入的参数“鸡蛋”，而getFastFood().getDesc()
    方法获取的则是子类Egg传入的fastFood参数的getDesc方法
     */
    @Override
    public String getDesc(){
        return super.getDesc() + getFastFood().getDesc();
    }

}
