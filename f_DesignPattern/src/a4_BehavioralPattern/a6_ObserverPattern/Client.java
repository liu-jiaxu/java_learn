package a4_BehavioralPattern.a6_ObserverPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a6_ObserverPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 16:05
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 1.创建公众号对象
        SubscriptionSubject subject = new SubscriptionSubject();
        // 2.订阅公众号
        subject.attach(new WeiXinUser("李四"));
        subject.attach(new WeiXinUser("王五"));
        // 3.公众号更新，发出消息给订阅者(观察者对象)
        subject.notify("更新");

    }

}
