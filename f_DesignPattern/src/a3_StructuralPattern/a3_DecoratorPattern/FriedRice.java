package a3_StructuralPattern.a3_DecoratorPattern;

/**
 * ClassName: FriedRice
 * Package: a3_StructuralPattern.a3_DecoratorPattern
 * Description:炒饭，具体构件角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 18:11
 * @Version: v1.0
 */
public class FriedRice extends FastFood{

    public FriedRice(){
        super(10,"炒饭");
    }

    @Override
    public float cost() {
        return getPrice();
    }

}
