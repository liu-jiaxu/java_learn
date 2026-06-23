package a2_OOP;

/*
接口interface的使用
	1.Java类和接口是并列的两个结构
	2.接口中只可以定义全局常量（public static final，可省略），没有变量
	3.接口中可定义全局变量、抽象方法、静态方法、默认方法...
	4.接口中无构造器，不可实例化
	5.可用类通过implements实现接口，若实现类覆盖了接口的所有抽象方法，则此实现类就可实例化，否则仍为一个抽象类
	6.可实现多个接口 class A extends B implements C,D,E
	7.类类之间单继承，类接口之间实现，接口接口之间多继承interface A extends B,C,D
	8.接口的多态性
 */
interface Flyable {

    public static final int MAX_SPEED = 7990;
    int MIN_SPEED = 1; //省略了public static final

    public abstract void fly();

    void stop(); //省略了public abstract
}

interface Hurt {

    void hurt();

}

interface End extends Flyable, Hurt { //接口之间的多继承

    void method();

}

//注意接口和类的变量调用问题
interface A12 {
    int x = 0;
}

class Plane implements Flyable, Hurt { //类实现接口，实现类覆盖了接口的所有抽象方法，则此实现类就可实例化

    @Override
    public void fly() {
        System.out.println("引擎起飞");
    }

    @Override
    public void stop() {
        System.out.println("驾驶员减速停止");
    }

    @Override
    public void hurt() {
        System.out.println("打击");
    }

}

abstract class Kite extends Plane implements Flyable { //实现类未覆盖接口的所有抽象方法，仍为一个抽象类

}

class B12 {
    static int x1 = 10;
    int x = 1;
}

public class OOP12 extends B12 implements A12 {

    public static void return_kite(Kite kite) {
        kite.fly();
        kite.stop();
        kite.hurt();
    }

    //接口的多态性，哪个类实现了接口就用谁
    //此处类Plane、Kite均实现了接口，所以可以写成Flyable flyable = new Plane()或Flyable flyable = new Kite()
    public static void return_Flyable(Flyable flyable) {
        System.out.println(flyable.MAX_SPEED + " " + flyable.MIN_SPEED);
    }

    //创建匿名类，因为Kite为抽象类无法实例化
    public static void main(String[] args) {

        Kite kite = new Kite() {

            public void stop() {
                System.out.println("stop");
            }

            public void fly() {
                System.out.println("fly");
            }

            public void hurt() {
                System.out.println("hurt");
            }

        };
        return_kite(kite);

        //利用匿名子类作为参数传递
        return_Flyable(kite);

        new OOP12().return_12();
			/*
				此处直接return_12()是错误的！因为return_12()为非静态方法，而主函数main为静态方法，在静态方法中
			无法直接调用非静态方法。有两种方法：1.修改原方法为static静态方法；（但是原方法中含有super关键字，不
			能定义为static方法，因为静态方法和类一起创建，所以在静态方法创建的时候还没有类的属性和非静态方法，因
			此无法用super调用）2.在main函数中创建类对象，通过对象调用该方法。
			 */

    }

    public void return_12() {
        System.out.println(super.x); //类B12中的变量x为局部变量，只能通过子类的super调用
        System.out.println(B12.x1); //类B12在的x1为静态变量，可以直接通过类名.变量的方式调用
        System.out.println(A12.x); //接口A12在的x默认为静态常量，可以直接通过类名.变量的方式调用
    }

}
