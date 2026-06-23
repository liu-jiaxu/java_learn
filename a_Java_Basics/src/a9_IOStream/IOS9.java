package a9_IOStream;

import org.junit.Test;

import java.io.*;

/*
1.对象流ObjectInputStream/ObjectOutputStream
	用于存储和读取基本数据类型或对象的处理流
2.序列化机制
	序列化就是把对象以流的方式写入到文件中保存，可以持久保存这个对象的信息（对象的数据、对象的类型、对象存储的
数据信息）。反过来，反序列化就是从文件中读取出来，重构对象。）
    序列化：ObjectOutputStream保存数据的机制
    反序列化：ObjectInputStream读取数据的机制
    补充：1.static（因为static修饰的不属于类本身）、transient（此修饰符修饰后表示不可序列化）修饰的成员变量不可序列化
    	  2.需要实现接口Serializable，并且内部所有属性均可序列化（基本数据类型默认可序列化）
    	  3.提供一个全局变量serialVersionUID
 */

class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    //常量定义必须有，当序列化后，若再修改此类，必须有常量，否则修改此类后再反序列化会报错（类改变找不到了）
    public int age;
    private String name;
    private Account account = new Account(12.88); //这个Account属性也必须可序列化，因此要在此类中定义常量并实现接口Serializable

    public Person() {
    }

    public Person(String name, int age, Account account) {
        super();
        this.name = name;
        this.age = age;
        this.account = account;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", account=" + account + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

class Account implements Serializable {

    public static final long serialVersionUID = 2l;

    private double balance;

    public Account(double balance) {
        super();
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [balance=" + balance + "]";
    }

}

public class IOS9 {

    @Test
    public void test() throws IOException, ClassNotFoundException {

        //序列化
        //1.创建ObjectOutputStream对象,构造方法中传递字节输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS9\\IOS9.txt"));
        //2.使用ObjectOutputStream对象中的方法writeObject,把对象写入到文件中
        oos.writeObject(new Person("mingming", 18, new Account(13.88)));
        //3.释放资源
        oos.close();

        //反序列化
        //1.创建ObjectInputStream对象,构造方法中传递字节输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\a9IOStream_File\\IOS9\\IOS9.txt"));
        //2.使用ObjectInputStream对象中的方法readObject读取保存对象的文件
        Object o = ois.readObject();
        //3.释放资源
        ois.close();
        //4.使用读取出来的对象(打印)
        System.out.println(o);
        Person p = (Person) o;
        System.out.println(p.getName() + " " + p.getAge() + " " + p.getAccount());

    }

}
