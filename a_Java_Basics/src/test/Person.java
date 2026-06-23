package test;

/**
 * ClassName: Person
 * Package: a1_Basics.test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/7 - 16:03
 * @Version: v1.0
 */

public abstract class Person {

    public abstract String getName();

}

class Person1 extends Person {

    @Override
    public String getName() {
        return "aaa";
    }

}

class Testq {

    public static void main(String[] args) {

        // 抽象类实现类
        Person p = new Person1() {};
        System.out.println(p.getName());

    }

}