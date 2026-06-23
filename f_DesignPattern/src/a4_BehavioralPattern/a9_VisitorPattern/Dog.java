package a4_BehavioralPattern.a9_VisitorPattern;

/**
 * ClassName: Cat
 * Package: a4_BehavioralPattern.a9_VisitorPattern
 * Description:具体元素角色类，宠物狗
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 10:46
 * @Version: v1.0
 */
public class Dog implements Animal{

    @Override
    public void accept(Person person) {
        person.feed(new Dog()); // 访问者给修狗喂食
        System.out.println("修狗干饭");
    }

}
