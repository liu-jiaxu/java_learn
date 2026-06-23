package a2_OOP;

class Phone {

    public int price = 0;

    /*
    方法重载：
            相同：类、方法名
            不同：参数列表、参数个数、参数类型
            无关：权限修饰符、函数及返回值类型、形参变量名、方法体
     */
    public void callprice() {
        System.out.println(price);
    }

    public void callprice(double price1) {
        System.out.println(price1);
    }
}

class StudentT {

    public String name;

    public StudentT(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

public class OOP3 {
	
	/*
	Java传递方式：值传递机制（Java中没有真正的引用传递！）
		传递方式不是看参数是什么类型就是什么传递，而是要看参数传递时是否进行了副本拷贝传递，在Java
	中，值传递和引用传递都是会传递拷贝的副本给形参的，而对于值传递来说拷贝的副本在传递后被修改不影
	响原始值，而对于引用传递，因为拷贝的是地址，所以原地址和拷贝的地址会指向同一内存空间，因此在引
	用传递时修改拷贝的地址也会修改原始数据。但要注意，Java不会将原始地址传递给形参，所以Java中没有
	引用传递！
		1.参数为基本数据类型：实参赋给形参的是实参的拷贝数据值（栈），形参的改变会另外在栈中创建一
	块空间来存储，而不是改变原始实参的值
		2.参数为引用数据类型：实参赋给形参的是实参存储数据的拷贝地址值（栈指向堆内容的地址），当实
	参中发生数据改变时，形参相应的地址值也会改变，但此时因为实参给形参的拷贝地址值和原始地址值相同，
	因此即是拷贝地址值的改变也会影响原始地址值，即实参的数据也发生改变（注意：若仅仅是地址交换而不
	改变地址，则视为地址值不发生改变，即使交换了地址值也是拷贝的地址值，对原始地址值无影响）
		3.String：
			栈存储，传递数据：String a = "abc";
			堆存储，传递地址：String a = new String("abc");
	 */

	//例1：
	int a, b;
    OOP3(int i, int j) {
        a = i;
        b = j;
    }

    //可变个数形参使用，相当于可变长度数组，注意返回的参数是地址
    public static void A(int... a) {
        System.out.println(a);
    }

    //例2：
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }
    public static void swap(String a, String b) {
        String temp = a;
        a = b;
        b = temp;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }
    public static void swap(StudentT x, StudentT y) {
        StudentT temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }

    void change(int a) {
        a = 100;
        System.out.println(a);
    }
    void change(String a) {
        a = "ccc";
        System.out.println(a);
    }
    // 声明change()方法，obj是形参
    void change(OOP3 obj) {
        obj = new OOP3(15, 20);
			/*
				1、注意此处在堆中新开辟了一个OOP3空间，在参数传递期间改变了obj对象的指向，因此此时opp3对象会
			指向堆中另一块的内存，之后的值改变也是改变堆中新创建内存OOP3的内容（即改变了obj对象指向新开辟空间
			OOP3的地址），传递时地址没有改变，因此为值传递，最终此方法结束后，再次输出a、b的值，obj对象又会按
			照之前的地址指向之前堆的数据，所以a、b值最终没有改变！
				2.若没有开辟新的空间，则在参数传递时改变了obj的地址，最终改变a、b的值。
			 */
        obj.a = 50;
        obj.b = 40;
        System.out.println("在change方法中  obj.a=" + obj.a + ",obj.b=" + obj.b);
    }

    public static void main(String[] args) {

        int a = 10, b = 20;
        swap(a, b);
        System.out.println("之后的a:" + a);
        System.out.println("之后的b:" + b);

        // String
        String A = "abc";
        String B = new String("def");
        swap(A, B);
        System.out.println("之后的A:" + A);
        System.out.println("之后的B:" + B);

        StudentT s1 = new StudentT("小张");
        StudentT s2 = new StudentT("小李");
        OOP3.swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
        	/*
        	    解释：
        	        首先在栈中创建两个变量s1、s2，分别在堆中开辟空间存储StudentT对象，
        	    调用swap方法后，将s1、s2指向堆的地址传递给形参x、y，初始时x与s1指向地址
        	    相同，y与s2指向地址相同，在方法内部交换地址指向后，改变了形参x、y的指向，
        	    但因为是值传递，传递的是拷贝地址，因此实参s1、s2指向的地址不会改变，改变的
        	    仅仅是拷贝地址的指向！
                    要想交换两者只能直接交换！
                    StudentT temp = s1;
                    s1 = s2;
                    s2 = temp;
        	 */

        Phone phone = new Phone();
        phone.price = 1500;
        phone.callprice();

        //匿名对象用于只调用一次的时候
        new Phone().price = 2000; // 匿名对象
        new Phone().callprice(); //匿名对象每new一次就是创建一个新对象，所以这里的new Phone().callprice()值为0
        new Phone().callprice(2399.99);

        A(1, 2, 3); //输出地址

        //传递示例：
        OOP3 obj = new OOP3(15, 20);

        //基本数据类型
        int firstA = 50;
        obj.change(firstA);
        System.out.println(firstA); //不变
        //String
        String bbb = "bbb";
        String aaa = new String("aaa");
        obj.change(bbb);
        System.out.println(bbb); //不变
        obj.change(aaa);
        System.out.println(aaa); //不变
            /*
                String数据类型每次新建变量时（包括形参新建变量）都会在字符串池中新开辟地址存储，因此每次改变传入实参的值时，
            都不是对实参修改，而是对新创建的形参值进行了修改，因此String传入的实参值不变！
             */
        // 引用数据类型
        System.out.println("调用change方法前  obj.a=" + obj.a + ",obj.b=" + obj.b);
        // 调用change()方法，obj是实参
        obj.change(obj);
        //在方法中新开辟空间存储OOP3的数据，改变的地址是新开辟空间的地址，与原先地址无关，因此改变的数据是新开辟空间中的数据。
        System.out.println("调用change方法后  obj.a=" + obj.a + ",obj.b=" + obj.b); //obj地址不变不会改变ab的值

    }

}
