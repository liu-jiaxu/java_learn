package a2_CreatorPattern.a1_SingletonPattern.Implement_Singleton;

/**
 * ClassName: Lazy_Singleton2
 * Package: a2_CreatorModel.a1_SingletonPattern
 * Description:懒汉式，线程安全
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 10:00
 * @Version: v1.0
 */
/**
 * 懒汉式
 * 线程安全
 */
public class Lazy_Singleton2 {
    //私有构造方法
    private Lazy_Singleton2() {}
    //在成员位置创建该类的对象
    private static Lazy_Singleton2 instance;
    //对外提供静态方法获取该对象
    // 使用同步锁，必须等到线程执行完释放后另外的线程才能进入
    public static synchronized Lazy_Singleton2 getInstance() {
        if(instance == null) {
            instance = new Lazy_Singleton2();
        }
        return instance;
    }

    /*
        该方式也实现了懒加载效果，同时又解决了线程安全问题。但是在getInstance()方法上添加了
    synchronized关键字，导致该方法的执行效果特别低。从上面代码我们可以看出，其实就是在初始化
    instance的时候才会出现线程安全问题，一旦初始化完成就不存在了。
    */
}