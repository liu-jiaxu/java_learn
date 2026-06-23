package a3_StructuralPattern.a3_DecoratorPattern;

/**
 * ClassName: Egg
 * Package: a3_StructuralPattern.a3_DecoratorPattern
 * Description:培根类，具体装饰角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 18:25
 * @Version: v1.0
 */
public class Bacon extends Garnish {

    public Bacon(FastFood fastFood) {
        super(fastFood, 2, "培根");
    }

    // 计算价格，鸡蛋价格加快餐价格
    @Override
    public float cost() {
        return getPrice() + getFastFood().cost();
    }

    @Override
    public String getDesc(){
        return super.getDesc() + getFastFood().getDesc();
    }

}
