package a2_CreatorPattern.a1_SingletonPattern.Break_Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassName: Client_Reflection_Singleton
 * Package: a2_CreatorModel.a1_SingletonPattern.Break_Singleton
 * Description:反射破坏单例模式测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/11 - 15:29
 * @Version: v1.0
 */
public class Client_Reflection_Singleton {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        //获取Singleton类的字节码对象
        Class clazz = a2_CreatorPattern.a1_SingletonPattern.Break_Singleton.Reflection_Singleton.class;
        //获取Singleton类的私有无参构造方法对象
        Constructor constructor = clazz.getDeclaredConstructor();
        //取消访问检查
        constructor.setAccessible(true);
        //创建Singleton类的对象s1
        a2_CreatorPattern.a1_SingletonPattern.Break_Singleton.Reflection_Singleton s1 = (a2_CreatorPattern.a1_SingletonPattern.Break_Singleton.Reflection_Singleton) constructor.newInstance();
        //创建Singleton类的对象s2
        a2_CreatorPattern.a1_SingletonPattern.Break_Singleton.Reflection_Singleton s2 = (a2_CreatorPattern.a1_SingletonPattern.Break_Singleton.Reflection_Singleton) constructor.newInstance();
        //判断通过反射创建的两个Singleton对象是否是同一个对象
        System.out.println(s1 == s2);

    }

}
