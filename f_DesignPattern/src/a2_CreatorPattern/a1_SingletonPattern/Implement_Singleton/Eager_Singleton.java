package a2_CreatorPattern.a1_SingletonPattern.Implement_Singleton;

/**
 * ClassName: Eager_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern
 * Description:饿汉式，静态成员变量
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 9:31
 * @Version: v1.0
 */

/**
 * 饿汉式
 * 静态变量创建类的对象
 */
public class Eager_Singleton {

    //私有构造方法
    private Eager_Singleton() {}
    //在成员位置创建该类的对象
    private static Eager_Singleton instance = new Eager_Singleton();
    //对外提供静态方法获取该对象
    public static Eager_Singleton getInstance() {
        return instance;
    }

    /*
        该方式在成员位置声明Eager_Singleton类型的静态变量，并创建Eager_Singleton类的对象instance。
    instance对象是随着类的加载而创建的。如果该对象足够大的话，而一直没有使用就会造成内存的浪费。
    */

}
