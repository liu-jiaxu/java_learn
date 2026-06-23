package a3_StructuralPattern.a3_DecoratorPattern;

/**
 * ClassName: Garnish
 * Package: a3_StructuralPattern.a3_DecoratorPattern
 * Description:配料类，抽象装饰角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 18:16
 * @Version: v1.0
 */
public abstract class Garnish extends FastFood {

   // 声明快餐类变量
    FastFood fastFood;

    public FastFood getFastFood() {
        return fastFood;
    }

    public void setFastFood(FastFood fastFood) {
        this.fastFood = fastFood;
    }

    public Garnish(FastFood fastFood,float price,String desc){
        super(price, desc);
        this.fastFood = fastFood;
    }

}
