package a4_BehavioralPattern.a9_VisitorPattern;

/**
 * ClassName: Cat
 * Package: a4_BehavioralPattern.a9_VisitorPattern
 * Description:具体元素角色类，宠物猫
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 10:46
 * @Version: v1.0
 */
public class Cat implements Animal{

    @Override
    public void accept(Person person) {
        person.feed(this); // 访问者给猫猫喂食
        System.out.println("猫猫干饭");
    }

}
