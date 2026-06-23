package a2_CreatorPattern.a1_SingletonPattern.Implement_Singleton;

/**
 * ClassName: Eager_Singleton2
 * Package: a2_CreatorModel.a1_SingletonPattern
 * Description:饿汉式，静态代码块
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 9:50
 * @Version: v1.0
 */

/**
 * 饿汉式
 * 在静态代码块中创建该类对象
 */
public class Eager_Singleton2 {

    //私有构造方法
    private Eager_Singleton2() {}
    //在成员位置创建该类的对象
    private static Eager_Singleton2 instance;
    static {
        instance = new Eager_Singleton2();
    }
    //对外提供静态方法获取该对象
    public static Eager_Singleton2 getInstance() {
        return instance;
    }

    /*
        该方式在成员位置声明Eager_Singleton2类型的静态变量，而对象的创建是在静态代码块中，也是随着
    类的加载而创建。所以和饿汉式的方式1基本上一样，当然该方式也存在内存浪费问题。
    */

}
