package a4_BehavioralPattern.a6_ObserverPattern;

/**
 * ClassName: WeiXinUser
 * Package: a4_BehavioralPattern.a6_ObserverPattern
 * Description:具体的观察者角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 16:02
 * @Version: v1.0
 */

public class WeiXinUser implements Observer {
    // 微信用户名
    private String name;
    public WeiXinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }

}
