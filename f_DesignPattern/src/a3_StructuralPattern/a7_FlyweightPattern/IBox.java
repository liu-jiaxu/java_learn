package a3_StructuralPattern.a7_FlyweightPattern;

/**
 * ClassName: IBox
 * Package: a3_StructuralPattern.a7_FlyweightPattern
 * Description:I图形类，具体享元角色
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 21:02
 * @Version: v1.0
 */

public class IBox extends AbstractBox {

    @Override
    public String getShape() {
        return "I";
    }

}

