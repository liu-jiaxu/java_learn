package a4_BehavioralPattern.a9_VisitorPattern;

/**
 * ClassName: Animal
 * Package: a4_BehavioralPattern.a9_VisitorPattern
 * Description:抽象元素角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 10:45
 * @Version: v1.0
 */
public interface Animal {

    // 接收访问者访问的功能
    void accept(Person person);

}
