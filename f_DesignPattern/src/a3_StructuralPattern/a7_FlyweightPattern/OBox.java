package a3_StructuralPattern.a7_FlyweightPattern;

/**
 * ClassName: OBox
 * Package: a3_StructuralPattern.a7_FlyweightPattern
 * Description:O图形类，具体享元角色
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 21:04
 * @Version: v1.0
 */
public class OBox extends AbstractBox {

    @Override
    public String getShape() {
        return "O";
    }

}
