package a2_OOP;

import java.util.Scanner;

/*
Debug的使用
	1.Resume：返回下一个断点，没有直接结束
	2.Terminate：终止调试
	3.Step Into：进入一个类中
	4.Step Over：执行下一条代码
	5.Drop To Frame：返回上一个断点
 */

/*
public A extends B {}
	1.子类A获取父类B的属性和方法，若父类属性、方法权限为private时也会继承，但子类不能直接调用
	2.java无法多重继承，可以多层继承（直接父类和间接父类）
	3.所有类都间接或直接继承父类Object
 */
//例：圆及圆柱类
class Circle1 {

    protected int id = 1;
    protected double radius;

    public Circle1() {
        radius = 1;
    }

    public Circle1(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double findArea() {
        return Math.PI * radius * radius;
    }

    public String findArea_Print() {
        return String.format("%.2f", (Math.PI * radius * radius));
    }

}

class Cylinder extends Circle1 {

    private final int id = 2;
    private double length;

    public Cylinder() {
        length = 1;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String findVolume_Print() {
        return String.format("%.2f", Math.PI * getRadius() * getRadius() * length);
    }

    //子类方法重写
	/*
	重写
		1.类名和参数列表相同
		2.权限要大于等于父类
		3.无法重写父类private方法
		4.父子类的返回值数据类型相同
		5.子类重写方法抛出的异常类型不大于父类方法的异常
		6.重写父子类均要为非static，（static不是重写）
	 */
    public double findArea() {
        return 2 * Math.PI * getRadius() * length + 2 * Math.PI * getRadius() * getRadius();
    }

    //super:直接调用父类的属性/方法
    //this：先在子类中查找，若有相应属性方法则调用子类的，没有调用父类的
    public int return_son_id() {
        return this.id;
    }

    public int return_father_id() {
        return super.id;
    }

    //super一般用于子类重写父类方法后，在子类中调用父类的相应方法
    public double return_Area(int choose) {
        if (choose == 1) {
            return this.findArea();
        } else {
            return super.findArea();
        }
    }

}

class Circle2 extends Circle1 {

    public double radius;

    //子类super调用父类构造器
    public Circle2(double radius) {
        super(radius); //相当于用了父类的this.radius=radius，给父类的radius赋了值，但子类中的radius没有赋值
        //this.radius=radius这么写的话子类的radius接受了赋值但父类没有
        //注意子类的radius和父类的radius虽同名但不是同一个变量！一般创建子类后要重新定义不同的变量名！
    }

    public String return_radius() {
        return "Circle2 radius:" + this.radius + "  Circle1 radius" + super.radius;
    }

}

public class OOP7 {

    public static void main(String[] args) {

        Cylinder cylinder = new Cylinder();
        cylinder.setRadius(10);
        cylinder.setLength(10);
        System.out.println("Cylinder Volume:" + cylinder.findVolume_Print());

        //通过父类、子类对象分别调用重写前后的方法
        Circle1 circle1 = new Circle1();
        circle1.setRadius(5);
        System.out.println("Circle Area:" + String.format("%.2f", circle1.findArea()));
        System.out.println("Cylinder Area:" + String.format("%.2f", cylinder.findArea()));

        //查看super和this的返回值
        System.out.println("Circle id:" + cylinder.return_father_id());
        System.out.println("Cylinder id:" + cylinder.return_son_id());

        //查看super和this调用方法的返回值
        System.out.print("your choose(1.Circle Area  2.Cylinder Area):");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        System.out.println("Old Area:" + cylinder.return_Area(choose));

        Circle2 circle2 = new Circle2(10);
        System.out.println(circle2.return_radius());

    }

}
