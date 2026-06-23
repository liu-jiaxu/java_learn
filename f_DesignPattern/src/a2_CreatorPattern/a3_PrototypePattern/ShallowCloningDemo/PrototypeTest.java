package a2_CreatorPattern.a3_PrototypePattern.ShallowCloningDemo;

/**
 * ClassName: PrototypeTest
 * Package: a2_CreatorModel.a3_PrototypePattern.ShallowCloningDemo
 * Description:测试访问类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 11:40
 * @Version: v1.0
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException
    {
        Realizetype r1 = new Realizetype();
        Realizetype r2 = r1.clone();
        System.out.println("对象r1和r2是同一个对象？" + (r1 == r2)); // false
    }
}
