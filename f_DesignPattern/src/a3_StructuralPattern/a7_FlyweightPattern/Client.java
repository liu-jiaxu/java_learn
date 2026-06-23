package a3_StructuralPattern.a7_FlyweightPattern;

import javax.swing.*;

/**
 * ClassName: Client
 * Package: a3_StructuralPattern.a7_FlyweightPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 10:02
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 获取I图形对象
        AbstractBox boxi = BoxFactory.getInstance().getShape("I");
        boxi.display("灰色");

        // 获取O图形对象
        AbstractBox boxo = BoxFactory.getInstance().getShape("O");
        boxo.display("白色");
        AbstractBox boxo2 = BoxFactory.getInstance().getShape("O");
        boxo2.display("红色");

        System.out.println(boxo == boxo2);

    }

}
