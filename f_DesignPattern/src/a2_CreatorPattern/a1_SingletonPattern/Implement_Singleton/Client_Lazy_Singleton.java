package a2_CreatorPattern.a1_SingletonPattern.Implement_Singleton;

/**
 * ClassName: Client_Lazy_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 10:51
 * @Version: v1.0
 */
public class Client_Lazy_Singleton {

    public static void main(String[] args) {

        Lazy_Singleton lazySingleton = Lazy_Singleton.getInstance();
        Lazy_Singleton lazySingleton2 = Lazy_Singleton.getInstance();
        System.out.println(lazySingleton == lazySingleton2);

        Lazy_Singleton4 lazySingleton3 = Lazy_Singleton4.getInstance();
        Lazy_Singleton4 lazySingleton4 = Lazy_Singleton4.getInstance();
        System.out.println(lazySingleton3 == lazySingleton4);

    }

}
