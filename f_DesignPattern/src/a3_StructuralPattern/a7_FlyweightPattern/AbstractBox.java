package a3_StructuralPattern.a7_FlyweightPattern;

/**
 * ClassName: AbstractBox
 * Package: a3_StructuralPattern.a7_FlyweightPattern
 * Description:抽象享元角色
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 20:56
 * @Version: v1.0
 */

public abstract class AbstractBox {

    // 获取图形的方法
    public abstract String getShape();

    // 显示图形及颜色
    public void display(String color) {
        System.out.println("方块形状：" + this.getShape() + " 颜色：" + color);
    }

}
