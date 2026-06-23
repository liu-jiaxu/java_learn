package a2_OOP;

/*
代码执行顺序
	1.父类的静态代码块
	2.子类的静态代码块
	3.main主函数中的代码
	4.父类的非静态代码块与构造方法
	5.子类的非静态代码块与构造方法
	6.父类的其他方法
	7.子类的其他方法
 */

/*
属性的赋值顺序
	1.默认初始化（定以后未赋值，系统自动赋默认值）
	2.显式初始化（定以后手动赋值）/代码块赋值
	3.构造器初始化
	4.对象调用赋值
 */

class China {

    /*
    static静态变量
        1.可修饰属性、方法、代码块、内部类
        2.实例变量（非静态属性）
          静态变量：多个类对象共享同一静态变量，某一对象对静态变量的修改会影响到其他对象
        3.静态变量、方法的加载早于对象的创建，随着类的加载而加载，可以直接通过类调用
        4.静态变量、方法在内存中只存在一份，存放在静态域内（其他对象的属性、方法在堆中）
        5.静态变量、方法中只可以调用静态属性和方法；非静态结构可以调用静态结构
     */
	/*
		首先static的成员是在类加载的时候初始化的，JVM的CLASSLOADER的加载，首次主动使用加载，而非static的成员
	是在创建对象的时候，即new操作的时候才初始化的；先后顺序是先加载，才能初始化，那么加载的时候初始化static的
	成员，此时非static的成员还没有被加载必然不能使用，而非static的成员是在类加载之后，通过new操作符创建对象的
	时候初始化，此时static已经分配内存空间，所以可以访问！

	静态的特点：

		1.随着类的加载而加载
			也就是，说静态会随着类的消失而消失，说明静态的生命周期最长
		2.优先于对象的存在
			明确一点：静态是先存在的对象是后存在的
		3.被所有对象共享
		4.可以直接被类名多调用

	实例变量（属性）和类（static修饰、静态）变量的区别
		1.存放位置
			类变量随着类的加载存在于方法区中，实例变量随着对象的对象的建立存在于堆内存里
		2.生命周期
			类变量生命周期最长，随着“类”的加载而加载，随着类的消失而消失,实例变量随着“对象”的消失而消失
		3.成员变量
			成员变量：成员变量就是定义在类里方法外的变量，也叫全局变量。成员变量包括实例变量和类变量

	全局（static修饰、成员）变量和局部变量
		全局变量定义在类中方法外
		局部变量是类方法内部定义的变量，存储在栈中

	静态的使用注意事项：
		1.静态方法只能访问静态成员（包括成员变量和成员方法）
		   非静态方法可以访问静态也可以访问非静态
		2.静态方法中不可以定义this，super关键字
		   因为静态优先于对象存在，所以静态方法中不可以出现this，super关键字
		3.主函数是静态的。
	 */
    static int now;
    String name;
    int age;

}

class Rectangle {

    private static int id; //编号，每创建一个矩形对象自动赋值为init+1
    private static int init = 1001;
    private static int num = 0; //矩形对象的总和

    /*
    代码块：用来初始化类、对象
        1.静态代码块：随着类的加载而执行，仅执行一次，优先于非静态代码块执行
        2.非静态代码块：随着对象的创建而执行，每创建一个对象执行一次
     */
    static {
        System.out.println("\nbegin：");
    }

    private double length = 0, width = 0;

    {
        System.out.println("矩形" + (num + 1) + "信息：");
    }

    public Rectangle() {
        id = init++;
        num++;
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        id = init++;
        num++;
    }

    public static int getId() {
        return id;
    }

    public static int getNum() {
        return num;
    }

    public String area() {
        return String.format("%.2f", length * width);
    }

}

public class OOP10 {

    public static void main(String[] args) {

        China china1 = new China();
        china1.now = 15;
        China china2 = new China();
        china2.now = 25;
        System.out.println(china1.now); //static静态变量会随不同对象属性值的改变而改变

        China.now = 10; //static修饰的静态变量可以直接通过类赋值
        System.out.println(china1.now);

        Rectangle rectangle1 = new Rectangle();
        System.out.println("area:" + rectangle1.area());
        System.out.println("id:" + rectangle1.getId());
        System.out.println("num:" + rectangle1.getNum());

        Rectangle rectangle2 = new Rectangle(10, 10);
        System.out.println("area:" + rectangle2.area());
        System.out.println("id:" + rectangle2.getId());
        System.out.println("num:" + rectangle2.getNum());
			// 若num变量设置为非静态，则每次num都是1，不会累加；而静态变量num独立于类之外，每次创建对象都会自增

    }

}
