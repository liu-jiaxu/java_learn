package a4_BehavioralPattern.a7_MediatorPattern;

/**
 * ClassName: Person
 * Package: a4_BehavioralPattern.a7_MediatorPattern
 * Description:抽象同事类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 10:40
 * @Version: v1.0
 */

public class Person {

    protected String name;
    protected Mediator mediator;

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

}
