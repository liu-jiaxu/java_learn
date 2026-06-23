package a2_CreatorPattern.a3_PrototypePattern.ShallowCloningDemo;

/**
 * ClassName: Realizetype
 * Package: a2_CreatorModel.a3_PrototypePattern.ShallowCloningDemo
 * Description:具体的原型类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 11:39
 * @Version: v1.0
 */

/*
        Java中的Object类中提供了 clone() 方法来实现浅克隆。 Cloneable 接口是上面的类图中的抽象原型类，而实现了Cloneable
    接口的子实现类就是具体的原型类。
 */
public class Realizetype implements Cloneable {
    public Realizetype() {
        System.out.println("具体的原型对象创建完成！");
    }

    @Override
    protected Realizetype clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return (Realizetype) super.clone(); // clone返回类型为父类的object，需要强转
    }
}
