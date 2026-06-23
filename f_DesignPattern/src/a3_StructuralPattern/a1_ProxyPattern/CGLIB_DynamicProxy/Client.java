package a3_StructuralPattern.a1_ProxyPattern.CGLIB_DynamicProxy;


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

        // 创建代理工厂对象
        ProxyFactory proxyFactory = new ProxyFactory();
        // 获取代理对象
        TrainStation proxyObject = proxyFactory.getProxyObject();
        // 调用代理对象中的sell方法卖票
        proxyObject.sell();

    }

}
