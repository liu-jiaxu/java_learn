package a3_StructuralPattern.a3_DecoratorPattern;

/**
 * ClassName: FriedNoodles
 * Package: a3_StructuralPattern.a3_DecoratorPattern
 * Description:炒面类，具体构件角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 18:14
 * @Version: v1.0
 */
public class FriedNoodles extends FastFood{

    public FriedNoodles(){
        super(12,"炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }

}
