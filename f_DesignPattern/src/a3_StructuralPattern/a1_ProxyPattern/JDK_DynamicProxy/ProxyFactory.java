package a3_StructuralPattern.a1_ProxyPattern.JDK_DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ClassName: ProxyFactory
 * Package: a3_StructuralPattern.a1_ProxyPattern.JDK_DynamicProxy
 * Description:代理工厂，用于创建代理对象
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 15:29
 * @Version: v1.0
 */

// ProxyFactory不是代理模式中所说的代理类，代理类是程序在运行过程中动态的在内存中生成的类。
public class ProxyFactory {

    // 声明目标对象
    private TrainStation trainStation = new TrainStation();

    // 获取代理对象的方法
    public SellTickets getProxyObject() {
        // 返回代理对象
        SellTickets proxyObject = (SellTickets) Proxy.newProxyInstance(
            trainStation.getClass().getClassLoader(),
            trainStation.getClass().getInterfaces(),
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 代理类增强
                    System.out.println("代理点收取代理费");
                    // 执行目标对象的方法
                    Object object = method.invoke(trainStation, args);
                    return object;
                }
            }
                /*
                    InvocationHandler中invoke方法参数说明：
                        proxy ：代理对象，和proxyObject对象是同一个对象，在invoke方法中基本不用
                        method ：对应于在代理对象上调用的接口方法的Method实例，对应对象调用接口中的sell方法
                        args ：代理对象调用接口方法时传递的实际参数，对应对象调用接口中的sell方法传递的参数(该方法无参数因此args为null)
                        返回值 ：方法的返回值，对应sell方法的返回值(sell方法无返回值，因此为null，若方法有返回值则返回对应值)
                 */
        ); // 返回Object，需要强转
            /*
                newProxyInstance()方法参数说明：
                    ClassLoader loader ：类加载器，用于加载代理类，使用真实对象的类加载器即可
                    Class<?>[] interfaces ：代理类实现的接口的字节码对象，真实对象所实现的接口，
                                            代理模式真实对象和代理对象实现相同的接口
                    InvocationHandler h ：代理对象的调用处理程序
            */
        return proxyObject;
    }

    /*
        执行流程如下：
            1. 在测试类中通过代理对象调用sell()方法
            2. 根据多态的特性，执行的是代理类（$Proxy0）中的sell()方法
            3. 代理类（$Proxy0）中的sell()方法中又调用了InvocationHandler接口的子实现类对象的invoke方法
            4. invoke方法通过反射执行了真实对象所属类(TrainStation)中的sell()方法
     */

}
