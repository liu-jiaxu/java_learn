package a2_OOP;

//同一包下不可以定义相同名的类

import java.util.Scanner;

/*
面向过程POP：函数
面向对象OOP：万物皆对象。封装为有功能的对象（封装、继承、多态）
 */

/*
类与对象
	1.创建类、属性、方法
	2.创建类对象
	3.调用类
 */
class Person { // 注：同一文件中只有和文件名相同的类才可以定义为public
	// 属性
	public String name;
	public int age;
	public boolean isMale;

	// 方法
	public void name1() {
		System.out.println(name);
	}

	public void sleep(String time) { // 局部变量time无法使用修饰符
		System.out.println("sleep time:" + time);
	}
}

/*
 * 属性（成员变量）：定义在{}内
 * 局部变量：声明在方法内、方法形参、代码块内、构造器形参、构造器内部变量
 * 内存存储位置
 * 	   非静态属性（全局变量）：堆
 *     static属性：方法区
 *     局部变量：栈
 */

//四种权限：public protected 缺省 private 

class Circle {
	
	public double radius = 0;

	public double Area() {
		return Math.PI * radius * radius;
	}
	
}

class Juxing {
	
	public double length = 0;
	public double width = 0;

	// 构造方法
	public Juxing(double length, double width) {
		this.length = length;
		this.width = width;
	}

	public double Area() {
		return length * width;
	}
	
}

public class OOP1 {

	public static void main(String[] args) {

		// 创建对象person并调用
		Person person1 = new Person();
		person1.name = "Hello!";
		person1.name1();
		person1.sleep("23:15");

		Person person2 = new Person();
		System.out.println(person2.isMale);

		Person person3 = person1; // p1给p3是给了地址，所以p1和p3指向的内容相同,修改了p3同时也会修改p1
		person3.sleep("22:35"); // 此时p1.sleep()也变成了22:35
		person3.name = "11"; // 此时p1.name也变成了11
		System.out.println(person1.name);

		Circle circle = new Circle();
		circle.radius = 10;
		System.out.println(circle.Area());

		Scanner in = new Scanner(System.in);
		System.out.print("请输入矩形长：");
		double length = in.nextDouble();
		System.out.print("请输入矩形宽：");
		double width = in.nextDouble();
		Juxing juxing = new Juxing(length, width); // 配合构造方法使用
		double f = juxing.Area();
		System.out.println("矩形面积为：" + String.format("%.3f", f)); // 保留有限位小数的方法（四舍五入）

	}

}
