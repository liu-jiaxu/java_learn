package a2_OOP;

/*
final关键字
	1.可修饰类、方法、变量
	2.修饰类时不可被继承
	3.修饰方法时不可重写
	4.修饰属性时不可改变，不可为默认初始化（只定义不赋值）
	5.修饰变量时不可改变，只能在方法体内使用，，称为常量
 */
final class Final {

    public final int a = 0;

    public final static void finall() {
        final int b = 10;
    }

}

/*
abstract关键字
	1.可修饰类、方法
	2.修饰的类为抽象类
		（1）抽象类不能实例化
		（2）抽象类一定有构造器，供子类实例化使用
	3.修饰的方法为抽象方法
		（1）抽象方法只有方法声明，没有方法体
		（2）包含抽象方法的类一定是抽象类
	4.abstract不能修饰属性、构造器、私有方法、静态方法、final方法/类（抽象类要被子类继承，而final规定不能有继承类）
 */
abstract class Abstract {

    private int a = 0;

    public abstract void eat(); //抽象方法不能有方法体，使用时要在子类重写

    public abstract void method();

}

class Person1 extends Abstract {

    public void eat() {
        System.out.println("meat");
    }

    public void method() {
        System.out.println("run");
    }
    //子类继承抽象父类时，必须重写其所有的抽象方法，否则该子类也要声明为抽象类

}

abstract class Person2 extends Abstract {
}

public class OOP11 {

    public static void returnClass(Person2 person2) {
        person2.eat();
        person2.method();
    }

    public static void main(String[] args) {

        Person1 person1 = new Person1();
        person1.eat();

        //匿名类
        Person2 person2 = new Person2() {
			//创建一个Person2对象person2，但开辟的内存空间里存放的不是Person2类，而是一个匿名子类
			//该匿名子类实现了抽象方法后，再向上转型变为抽象类对象
            @Override
            public void eat() {
                System.out.println("eggs");
            }

            @Override
            public void method() {
                System.out.println("go");
            }
        };
        returnClass(person2);

    }

}
