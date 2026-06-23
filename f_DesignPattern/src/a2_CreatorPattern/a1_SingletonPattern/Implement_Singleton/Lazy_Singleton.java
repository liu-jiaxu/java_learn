package a2_CreatorPattern.a1_SingletonPattern.Implement_Singleton;

/**
 * ClassName: Lazy_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern
 * Description:懒汉式，线程不安全
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 10:00
 * @Version: v1.0
 */

/**
 * 懒汉式
 * 线程不安全
 */
public class Lazy_Singleton {

    //私有构造方法
    private Lazy_Singleton() {}
    //在成员位置创建该类的对象
    private static Lazy_Singleton instance;
    //对外提供静态方法获取该对象
    public static Lazy_Singleton getInstance() {
        // 判断是否首次使用对象
        // 首次使用时多个线程对象均为null，多个线程可能同时进入if语句，从而创建多个对象
        if(instance == null) {
            instance = new Lazy_Singleton();
        }
        return instance;
    }

    /*
        从上面代码我们可以看出该方式在成员位置声明Singleton类型的静态变量，并没有进行对象的
    赋值操作，那么什么时候赋值的呢？当调用getInstance()方法获取Lazy_Singleton类的对象的时
    候才创建Lazy_Singleton类的对象，这样就实现了懒加载的效果。但是，如果是多线程环境，会出现
    线程安全问题。
    */

}
