package a4_BehavioralPattern.a9_VisitorPattern;

/**
 * ClassName: Person
 * Package: a4_BehavioralPattern.a9_VisitorPattern
 * Description:抽象访问者角色
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 10:44
 * @Version: v1.0
 */
public interface Person {

    // 猫狗喂食
    void feed(Cat cat);
    void feed(Dog dog);

}
