package a2_OOP;

import java.time.LocalDate;
import java.util.Objects;

/*
Object
	1.是所有类的父类（java.lang.Object），
	2.类下无属性，只有无参构造器
	3.包含许多方法（clone、equals、getClass...）
 */

class A8 {

    protected int id = 1;
    protected String coding = "a1";

    public A8() {
    }

    public A8(int id, String coding) {
        this.id = id;
        this.coding = coding;
    }

    public void print() {
        System.out.println(id);
    }
	
	/*
	重写equals方法，重写equals方法之后再比较类A8的对象是就会比较对象的实体值是否相同而不是地址是否相同了
	public boolean equals(Object object) {
		if(this==object){
			return true;
		}
		if(object instanceof A8) {
			A8 a8=(A8)object;
			return this.id==a8.id && this.coding==a8.coding;
		}
		return false;
	}
	*/

}

class B8 extends A8 {

    private int method;

    public B8() {
    }

    public B8(int id, String coding, int method) {
        super(id, coding);
        this.method = method;
    }

    public void print() {
        System.out.println(super.id + "\t" + super.coding);
    }

}

class C8 extends A8 {
    private int a = 0;
    private double b = 0;
    private String c = "0";
    private A8 aa;

    //用系统自动生成的equals方法
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        C8 other = (C8) obj;
        return a == other.a && Objects.equals(aa, other.aa)
                && Double.doubleToLongBits(b) == Double.doubleToLongBits(other.b) && Objects.equals(c, other.c);
    }

}

public class OOP8 {

    public static void main(String[] args) {

        //多态：创建一个A8类的对象a8但指向子类B8，此对象a8只能调用父类A8的方法属性，但若子类重写了父类的方法，调用的是子类中重写的方法
        A8 a8 = new B8(); //只能父类对象指向子类空间，不可以反过来B8 b8=new A8()是错的！
        a8.print(); //由于子类B8重写了print方法，因此调用的是子类的print方法，若子类没有重写调用的就是父类的方法

        //向下转型 将父类对象a8转换为子类B8的对象并赋值给b8
        B8 b8 = (B8) a8;
        b8.print();

        //a instanceof A判断对象a是否为类A的实例
        if (a8 instanceof A8) {
            System.out.println(true);
        }
        if (b8 instanceof A8) { //因为B8继承A8，所以对象b8也是类A8的实例
            System.out.println(true);
        }

        //多态是运行时行为
		
		
		
		/*
		==使用
			1.基本数据类型：比较数据是否相等，不比较类型
			2.引用数据类型：比较对象地址是否相同
		 */
        int a = 1;
        double b = 1.0;
        String str1 = "a", str2 = "a";
        String str3 = new String();
        String str4 = new String();
        System.out.println(a == b); //相等
        System.out.println(str1 == str2); //相等，因为没有new一个新的空间
        System.out.println(str3 == str4); //不相等，比较的是地址
				
		/*
		equals使用
			1.是一个方法而非运算符
			2.只用于引用数据类型，比较地址是否相同
			3.String、Date、File、包装类等重写了Object的equals方法，它们比较的是对象指向的实体值是否相同！
			4.自定义类重写equals，也可以只比较实体值
		 */
        System.out.println(str1.equals(str2)); //相等
        System.out.println(str3.equals(str4)); //相等,String重写了equals方法只比较实体值是否相等
        A8 a81 = new A8();
        A8 a82 = new A8();
        B8 b81 = new B8();
        System.out.println(a81.equals(a82)); //不相等，地址指向不同
        System.out.println(a81.equals(b81)); //不相等，地址指向不同

        //用重写后的equals方法比较两个对象，只会比较实体值是否相同了
        C8 c8 = new C8();
        C8 c81 = new C8();
        System.out.println(c8.equals(c81)); //相等，重写了equals方法
		
		/*
		toString使用
			1.输出一个对象的引用时，实际是调用当前对象的toString方法，输出的是对象的地址
				System.out.print(对象.toString());
			2.String、Date、File、包装类等重写了Object的toString方法，它们均返回实体内容！
				System.out.print(str.toString());
		 */

        LocalDate localDate1 = LocalDate.of(1, 1, 1);
        LocalDate localDate2 = LocalDate.of(1, 1, 1);
        boolean equals = Objects.equals(localDate1, localDate2);
        System.out.println(equals);

    }

    //自动生成toString方法
    @Override
    public String toString() {
        return "OOP8 [toString()=" + super.toString() + "]";
    }

}
