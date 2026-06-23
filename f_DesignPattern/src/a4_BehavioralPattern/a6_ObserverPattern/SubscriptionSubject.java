package a4_BehavioralPattern.a6_ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SubscriptionSubject
 * Package: a4_BehavioralPattern.a6_ObserverPattern
 * Description:具体主题角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/24 - 15:58
 * @Version: v1.0
 */

public class SubscriptionSubject implements Subject {

    //储存订阅公众号的微信用户
    private List<Observer> weiXinUserList = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        weiXinUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weiXinUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weiXinUserList) {
            observer.update(message);
        }
    }

}
