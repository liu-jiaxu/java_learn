package a3_StructuralPattern.a1_ProxyPattern.StaticProxy;

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

        ProxyPoint p = new ProxyPoint();
        p.sell();

    }

}
