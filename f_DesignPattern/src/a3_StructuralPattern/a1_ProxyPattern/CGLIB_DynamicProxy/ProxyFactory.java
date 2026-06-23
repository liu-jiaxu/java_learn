package a3_StructuralPattern.a1_ProxyPattern.CGLIB_DynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ClassName: ProxyFactory
 * Package: a3_StructuralPattern.a1_ProxyPattern.CGLIB_DynamicProxy
 * Description:代理对象工厂，用来获取代理对象
 *
 * @Author: zgh296
 * @Create: 2023/4/20 - 14:43
 * @Version: v1.0
 */
public class ProxyFactory implements MethodInterceptor {

    // 导包问题Enhancer报错，程序无法运行！
    public TrainStation getProxyObject(){
        // 1.创建Enhancer对象，类似于JDK代理中的Proxy类
        Enhancer enhancer = new Enhancer();
        // 2.设置父类的字节码对象
        enhancer.setSuperclass(TrainStation.class);
        // 3.设置回调函数
        enhancer.setCallback(this); // this表示TrainStation类的对象
        // 4.创建代理对象
        TrainStation trainStation = (TrainStation) enhancer.create();
        return trainStation;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理点收取一些服务费用(CGLIB动态代理方式)");
        TrainStation result = (TrainStation) methodProxy.invokeSuper(o, objects);
        return result;
    }

}
