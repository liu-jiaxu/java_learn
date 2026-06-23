package a3_StructuralPattern.a1_ProxyPattern.JDK_DynamicProxy;

import a3_StructuralPattern.a1_ProxyPattern.StaticProxy.ProxyPoint;

/**
 * ClassName: Client
 * Package: a3_StructuralPattern.a1_ProxyPattern.StaticProxy
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 15:24
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 获取代理对象
        ProxyFactory proxyFactory = new ProxyFactory();
        // 使用工厂对象的方法获取代理对象
        SellTickets proxyObject = proxyFactory.getProxyObject();
        // 调用卖票方法
        proxyObject.sell();

    }

}
