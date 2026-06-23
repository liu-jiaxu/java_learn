package a3_StructuralPattern.a7_FlyweightPattern;

/**
 * ClassName: LBox
 * Package: a3_StructuralPattern.a7_FlyweightPattern
 * Description:L图形类，具体享元角色
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 21:03
 * @Version: v1.0
 */

public class LBox extends AbstractBox {

    @Override
    public String getShape() {
        return "L";
    }

}