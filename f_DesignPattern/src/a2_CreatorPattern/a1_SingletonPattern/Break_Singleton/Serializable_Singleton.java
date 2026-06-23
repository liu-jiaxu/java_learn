package a2_CreatorPattern.a1_SingletonPattern.Break_Singleton;

import java.io.Serializable;

/**
 * ClassName: Serializable_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern.Break_Singleton
 * Description:序列化反序列化破坏单例模式
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 14:45
 * @Version: v1.0
 */

public class Serializable_Singleton implements Serializable {

    //私有构造方法
    private Serializable_Singleton() {}
    private static class SingletonHolder {
        private static final Serializable_Singleton INSTANCE = new Serializable_Singleton();
    }
    //对外提供静态方法获取该对象
    public static Serializable_Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    /**
     * 下面是为了解决序列化反序列化破解单例模式
     */
    private Object readResolve() {
        return SingletonHolder.INSTANCE;
    }

}
