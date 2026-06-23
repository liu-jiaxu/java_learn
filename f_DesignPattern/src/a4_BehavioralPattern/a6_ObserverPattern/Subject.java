package a4_BehavioralPattern.a6_ObserverPattern;

/**
 * ClassName: Subject
 * Package: a4_BehavioralPattern.a6_ObserverPattern
 * Description:抽象主题角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 15:56
 * @Version: v1.0
 */

public interface Subject {

    // 添加订阅者(观察者对象)
    void attach(Observer observer);

    // 删除订阅者(观察者对象)
    void detach(Observer observer);

    // 通知订阅者更新消息
    void notify(String message);

}
