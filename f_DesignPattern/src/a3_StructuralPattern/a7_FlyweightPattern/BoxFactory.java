package a3_StructuralPattern.a7_FlyweightPattern;

import javax.swing.*;
import java.util.HashMap;

/**
 * ClassName: BoxFactory
 * Package: a3_StructuralPattern.a7_FlyweightPattern
 * Description:工厂类，将该类设计为单例
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 21:04
 * @Version: v1.0
 */
public class BoxFactory {

    private static HashMap<String, AbstractBox> map;

    // 在构造方法中进行初始化操作
    private BoxFactory() {
        map = new HashMap<String, AbstractBox>();
        AbstractBox iBox = new IBox();
        AbstractBox lBox = new LBox();
        AbstractBox oBox = new OBox();
        map.put("I", iBox);
        map.put("L", lBox);
        map.put("O", oBox);
    }

    // 获取工厂类对象
    public static BoxFactory getInstance() {
        return factory;
    }

    // 懒汉式
    private static BoxFactory factory = new BoxFactory();

    // 获取工厂对象
    public static AbstractBox getShape(String name){
        return map.get(name);
    }

}
