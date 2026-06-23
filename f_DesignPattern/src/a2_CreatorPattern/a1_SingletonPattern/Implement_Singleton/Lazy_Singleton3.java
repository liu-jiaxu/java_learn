package a2_CreatorPattern.a1_SingletonPattern.Implement_Singleton;

/**
 * ClassName: Lazy_Singleton3
 * Package: a2_CreatorModel.a1_SingletonPattern
 * Description:懒汉式，双重检查锁方式
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 10:23
 * @Version: v1.0
 */
/**
 * 双重检查方式
 */
public class Lazy_Singleton3 {

    //私有构造方法
    private Lazy_Singleton3() {}
    private static volatile Lazy_Singleton3 instance;
    //对外提供静态方法获取该对象
    public static Lazy_Singleton3 getInstance() {
        //第一次判断，如果instance不为null，不进入抢锁阶段，直接返回对象
        if(instance == null) {
            synchronized (Lazy_Singleton3.class) {
                //抢到锁之后再次判断是否为空
                if(instance == null) {
                    instance = new Lazy_Singleton3();
                }
            }
        }
        return instance;
    }

    /*
        双重检查锁模式是一种非常好的单例实现模式，解决了单例、性能、线程安全问题，上面的双重检
    测锁模式看上去完美无缺，其实是存在问题，在多线程的情况下，可能会出现空指针问题，出现问题的
    原因是JVM在实例化对象的时候会进行优化和指令重排序操作。
        要解决双重检查锁模式带来空指针异常的问题，只需要使用volatile关键字, volatile关键字
    可以保证可见性和有序性。
    */

}
