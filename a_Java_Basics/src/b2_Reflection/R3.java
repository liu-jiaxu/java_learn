package b2_Reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class PersonB {

    public static int age;
    private String nameString;

    public PersonB() {
    }

    private PersonB(String nameString) {
        super();
        this.nameString = nameString;
    }

    public PersonB(String nameString, int age) {
        super();
        this.nameString = nameString;
        this.age = age;
    }

    public static void showDesc() {
        System.out.println("我是一个可爱的人");
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
        return "PersonB [nameString=" + nameString + ", age=" + age + "]";
    }

    private void show() {
        System.out.println("我是一个人");
    }

    public String show(String string) {
        return "我是一个" + string;
    }

    private String showNation(String nation) {
        System.out.println("国籍" + nation);
        return nation;
    }

}

public class R3 {

    //调用运行时类中指定结构
    @Test
    public void test() throws Exception {

        //获取属性
        Class class1 = PersonB.class;
        PersonB personB = (PersonB) class1.newInstance(); //使用原类中对应构造器创建新实例（此处对应无参构造器,在jdk11中过时）
        Field field = class1.getDeclaredField("nameString"); //getDeclaredField获取指定属性(注：getField方法只能获取指定public属性，已抛弃)
        field.setAccessible(true); //保证当前属性可访问（用于获取非public属性时，取消Java语言访问检查）
        //不设置会报错：java.lang.IllegalAccessException非法访问
        field.set(personB, "huahua"); //设置指定属性的值
        String string = (String) field.get(personB); //得到设置属性的值
        System.out.println(string);
        System.out.println(personB);

    }

    @Test
    public void test2() throws Exception {

        //调用方法
        Class class2 = PersonB.class;
        PersonB personB2 = (PersonB) class2.newInstance();
        Method method = class2.getDeclaredMethod("show"); //获取指定方法
        Method method2 = class2.getDeclaredMethod("show", String.class); //获取指定方法，为防止有方法重载，还可以指定对应方法的对应参数
        method.setAccessible(true); //保证当前属性可访问
        //因为重载的show(String string)方法设置权限为public所以不用设置setAccessible(true)了
        method.invoke(personB2); //这里相当于personB2.show()
        String string = (String) method2.invoke(personB2, "猫猫"); //invoke方法的返回值就是对应调用方法的返回值
        //invoke(方法的调用者,传递给该方法的参数)
        System.out.println(string);

        //调用静态方法
        Method method3 = class2.getDeclaredMethod("showDesc");
        method3.setAccessible(true);
        method3.invoke(personB2);
        String string2 = (String) method3.invoke(null); //因为静态方法可以在类的创建时就存在了，此处可以不用写对象了（PersonB.class2/personB2/null都可以）
        //invoke写几次就调用几次对应方法
        System.out.println(string2); //invoke对应的方法无返回值时invoke的返回值为null

    }

    @Test
    public void test3() throws Exception {

        //调用指定构造器
        Class class3 = PersonB.class;
        //getDeclaredConstructor获取指定构造器（通过指定参数列表）
        Constructor constructor = class3.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        PersonB personB3 = (PersonB) constructor.newInstance("Tom", 15);
        System.out.println(personB3);

    }


}
