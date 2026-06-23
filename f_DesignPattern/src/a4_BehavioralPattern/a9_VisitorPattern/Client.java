package a4_BehavioralPattern.a9_VisitorPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a9_VisitorPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 10:50
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建home对象
        Home home = new Home();
        home.add(new Cat());
        home.add(new Dog());

        // 喂食
        home.action(new Owner());
        home.action(new Someone());

        // 和宠物玩，需要定义Person玩耍方法，Animal定义玩耍反馈方法，同时类实现方法，Home中新增方法遍历

    }

}
