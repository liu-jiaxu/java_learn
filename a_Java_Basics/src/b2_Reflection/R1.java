package b2_Reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
反射（动态语言）
	反射机制允许程序执行期间借助Reflection API获取任何类的信息，并直接操作任意对象的内部属性及方法
 */

class PersonA {

    public int age;
    private String nameString;

    public PersonA() {
    }

    private PersonA(String nameString) {
        super();
        this.nameString = nameString;
    }

    public PersonA(String nameString, int age) {
        super();
        this.nameString = nameString;
        this.age = age;
    }

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonA [nameString=" + nameString + ", age=" + age + "]";
    }

    public void show() {
        System.out.println("我是一个人");
    }

    private String showNation(String nation) {
        System.out.println("国籍" + nation);
        return nation;
    }

}

public class R1 {

    @Test
    public void test() throws Exception {

        //之前类的操作
		/* PersonA personA=new PersonA("liudehua", 45);
		 PersonA.age=50;
		 System.out.println(personA.toString());
		 personA.show();*/

        //反射中类的操作
        Class<PersonA> class1 = PersonA.class; //PersonA类相当于Class的一个实例（这里PersonA类作为Class的对象）
        Constructor<PersonA> constructor = class1.getConstructor(String.class, int.class); //getConstructor()返回指定参数类型public的构造器。
		 	/*
		 		Constructor提供了一个类的单个构造函数的信息和访问。Constructor允许在将实际参数与newInstance()与底层构造函数的形式参数
		 	进行匹配时进行扩展转换，但如果发生缩小转换，则抛出IllegalArgumentException。 
		 	 */
        Object object = constructor.newInstance("Tom", 21); //使用指定的初始化参数创建和初始化构造函数的声明类的新实例
        PersonA p = (PersonA) object; //相当于创建一个PersonA类的对象p
        System.out.println(p);

        //通过反射调用对象属性
        Field field = class1.getDeclaredField("age"); //Field提供有关类或接口的单个字段的信息和动态访问。反射的字段可以是类（静态）字段或实例字段。
        //getDeclaredField(String name)获取类中name属性
        field.set(p, 10); //设置对象p中的age属性，令age=10
        System.out.println(p);

        //通过反射调用对象方法
        Method method = class1.getDeclaredMethod("show");
        method.invoke(p); //调用对象p的show方法

        //通过反射可以调用私有属性及方法
        Constructor constructor2 = class1.getDeclaredConstructor(String.class);
		 	/*
		 		getConstructor()返回指定参数类型public的构造器
		 		getDeclaredConstructor()返回指定参数类型的构造器
		 	 */
            /*
                1.Class类的getConstructor()方法,无论是否设置setAccessible(),都不可获取到类的私有构造器.
                2.Class类的getDeclaredConstructor()方法,可获取到类的私有构造器（包括带有其他修饰符的构造器），
            但在使用private的构造器时，必须设置setAccessible()为true,才可以获取并操作该Constructor对象。
             */
        constructor2.setAccessible(true); //保证当前属性可访问
		 	/*
		 		值为true则指示反射的对象在使用时应该取消Java语言访问检查。值为false则指示反射的对象应该实施Java语言访问检查；
		 	实际上setAccessible是启用和禁用访问安全检查的开关，并不是为true就能访问为false就不能访问；由于JDK的安全检查耗时较
		 	多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的 
		 	 */
        PersonA p2 = (PersonA) constructor2.newInstance("Amy");
        System.out.println(p2);
        Field field2 = class1.getDeclaredField("nameString");
        field2.setAccessible(true); //设置为true可以获取类私有对象及方法
        field2.set(p2, "DaMing");
        Method method2 = class1.getDeclaredMethod("showNation", String.class);
            //获取对应的方法并设置传入参数
        method2.setAccessible(true);
        String string = (String) method2.invoke(p2, "中国");
        //method2.invoke(p2,"中国")调用对象p的showNation方法并赋予方法参数
        //invoke方法调用后会执行类中对应的方法
        System.out.println(p2.getNameString() + string);

    }
	
	/*
	1.开发中通过直接new对象还是反射调用？
		直接new对象
	2.何时使用反射？
		当用户登录或注册网页时，后台程序会一直处于运行状态，此时程序需要判断用户是注册还是登录，从而创建不同的对象。
	此时服务器一直处于运行状态，无法new对象，需要用到反射
	3.如何看待反射和封装的矛盾性？
		不矛盾，封装的私有方法和属性一般都会在其共有方法中调用，共有方法提供了更好地解释，不需要用户调用私有方法属性，
	当然通过反射也可以调用私有方法属性。
	 */

}
