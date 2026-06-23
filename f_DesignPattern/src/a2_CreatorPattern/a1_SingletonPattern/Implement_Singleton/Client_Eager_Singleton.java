package a2_CreatorPattern.a1_SingletonPattern.Implement_Singleton;

/**
 * ClassName: Client_Eager_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 9:35
 * @Version: v1.0
 */
public class Client_Eager_Singleton {

    public static void main(String[] args) {

        // 创建Eager_Singleton对象
        Eager_Singleton eagerSingleton = Eager_Singleton.getInstance();
        Eager_Singleton eagerSingleton2 = Eager_Singleton.getInstance();
        System.out.println(eagerSingleton == eagerSingleton2);

        // 创建Eager_Singleton2对象
        Eager_Singleton2 eagerSingleton3 = Eager_Singleton2.getInstance();
        Eager_Singleton2 eagerSingleton4 = Eager_Singleton2.getInstance();
        System.out.println(eagerSingleton3 == eagerSingleton4);

        Eager_Singleton3 eagerSingleton5 = Eager_Singleton3.INSTANCE;
        Eager_Singleton3 eagerSingleton6 = Eager_Singleton3.INSTANCE;
        System.out.println(eagerSingleton5 == eagerSingleton6);

    }

}
