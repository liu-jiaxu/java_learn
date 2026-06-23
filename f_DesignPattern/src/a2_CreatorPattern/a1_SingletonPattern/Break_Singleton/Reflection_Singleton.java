package a2_CreatorPattern.a1_SingletonPattern.Break_Singleton;

/**
 * ClassName: Reflection_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern.Break_Singleton
 * Description:反射破坏单例模式
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 15:28
 * @Version: v1.0
 */
public class Reflection_Singleton {

    private static boolean flag = false;

    //私有构造方法
    private Reflection_Singleton() {
        /*
        反射破解单例模式需要添加的代码
        */
        synchronized (Reflection_Singleton.class) {
            if(flag) {
                throw new RuntimeException("不能创建多个对象！");
            }
            flag = true;
        }
    }
    private static volatile Reflection_Singleton instance;
    //对外提供静态方法获取该对象
    public static Reflection_Singleton getInstance() {
        if(instance != null) {
            return instance;
        }
        synchronized (Reflection_Singleton.class) {
            if(instance != null) {
                return instance;
            }
            instance = new Reflection_Singleton();
            return instance;
        }
    }

}
