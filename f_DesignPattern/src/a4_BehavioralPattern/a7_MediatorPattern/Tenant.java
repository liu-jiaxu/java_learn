package a4_BehavioralPattern.a7_MediatorPattern;

/**
 * ClassName: Tenant
 * Package: a4_BehavioralPattern.a7_MediatorPattern
 * Description:具体同事角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 10:41
 * @Version: v1.0
 */
public class Tenant extends Person{

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 和中介沟通的方法
    public void constact(String message) {
        mediator.constact(message,this);
    }

    // 获取信息的方法
    public void getMessage(String message) {
        System.out.println("租房者" + name + "获取信息：" + message);
    }

}
