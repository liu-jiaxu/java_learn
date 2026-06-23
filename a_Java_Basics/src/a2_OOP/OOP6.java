package a2_OOP;

/*
package关键字
	1.便于项目管理
	2.属于标识符
	3.每一个.就表示一层目录
	4.声明在首行，同一包下不能定义同名的类或接口
import关键字
	用于导入包下指定的类、接口，*可以导入所有内容
 */

/*
JavaBean
	1.类是公共的
	2.有一个无参公共构造器
	3.有属性和get、set方法
 */

//this调用方法（无法递归）
class A {

    private int a = 0;

    public A() {
        ha(); //this调用方法ha()
    }

    public A(int a) {
        this(); //this()会调用无参构造方法，注意调用时必须放在首行
        this.a = a;
    }

    public static void ha() {
        System.out.println("lalala");
    }

}

class B {

    private int b = 0;

    public B() {
        A.ha(); //类名.方法实现不同类调用同名方法
    }

    public B(int b) {
        this();
        this.b = b;
    }

    public void ha() {
        A.ha();
    }

}

public class OOP6 {

    public static void main(String[] args) {

        //银行存款示例
        OOP6_Customer customer = new OOP6_Customer("Jane", "Smith");
        OOP6_Account account = new OOP6_Account(1000, 2000, 0.0123);
        customer.setAccount(account); //set将account对象作为参数赋值给Customer类
        customer.getAccount().deposit(100);
        //customer对象先调用get方法获得上一步set的赋值account，之后用这个account对象调用类OOP6_Account中的deposit方法
        customer.getAccount().withdraw(960);
        customer.getAccount().withdraw(1600);
        System.out.println("Customer[" + customer.getLastName() + "," + customer.getFirstName() + "] has a account:id is " +
                customer.getAccount().getId() + ",annual is " + customer.getAccount().getAnnual() * 100 + "% ,balance is " +
                customer.getAccount().getBalance());

        OOP6_Bank bank = new OOP6_Bank();
        bank.add("Jane", "Smith");
        bank.getCustomer(0).setAccount(new OOP6_Account(2000));
        bank.getCustomer(0).getAccount().withdraw(500);
        double balance = bank.getCustomer(0).getAccount().getBalance();
        System.out.println("客户：" + bank.getCustomer(0).getFirstName() + "的余额为：" + balance);

    }

}
