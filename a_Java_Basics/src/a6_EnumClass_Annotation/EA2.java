package a6_EnumClass_Annotation;

import org.junit.Test;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Date;

/*
注解Annotation
	1.jkd5.0之后
	2.代码的特殊标记，可以在编译、类加载、运行时被读取，相当于源文件的补充信息
	3.可用于修饰包、类、构造器、方法、成员变量、参数、局部变量的声明
	4.JavaEE/Android中重要
	5.框架=注解+反射+设计模式
 */

/*
例1：生成文档相关的注解
	@author作者1,作者2...
	@version类模块的
	@see参考转向
	@since从哪个版本开始新增的
	@param参数说明
	@return方法返回值说明
	@exception抛出异常说明
	......
例2：编译时进行格式检查（jdk内置三个注解）
	@Override限定重写父类方法
	@Deprecated用于表示所修饰的元素（已过时），提示所修饰的结构危险或有更好的选择
	@SuppressWarnings抑制编译器警告（不会显示Warnings）
例3：跟踪代码依赖性，实现替代配置文件功能
 */

//例1：生成文档相关的注解
/*
 * @author 296
 *
 * @create 2022.8.6 20:22
 */

@Retention(RetentionPolicy.RUNTIME) //元注解修饰注解
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.TYPE_USE})
@Repeatable(MyAnnotations.class) //表示注解可重复
@Inherited
@interface MyAnnotation {

    String value() default "hi";

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.TYPE_USE}) //此处定义注解数组，重复注解修饰类型需要相同
@Inherited //重复注解修饰类型需要相同
@interface MyAnnotations { //定义数组存放多个注解

    MyAnnotation[] value();

}

/*
自定义注解
	1.声明为@interface
	2.内部定义成员，一般使用value表示
	3.成员默认值用default指定
	4.自定义注解无成员时表示为表示作用
	5.注解有成员时，使用注解时要指定成员值
	6.自定义注解的信息处理流程使用反射
 */

/*
jdk4种元注解（对注解的修饰）
	Retention:指定修饰注解的生命周期
		SOURCE:注解在编译（javac）时抛弃，不保留
		CLASS(默认):编译后注解会保留，但运行时抛弃
		RUNTIME:一直保留，可以通过反射获取
	Target:指明注解可以修饰哪些类型
		TYPE类、接口、枚举类
		FIELD属性
		METHOD方法
		PARAMETER形参  
		CONSTRUCTOR构造器 
		LOCAL_VARIABLE局部变量
		TYPE_PARAMETER泛型
		TYPE_USE任何语句
	Documented:修饰的注解被javadoc解析时保留下来
	Inherited:使修饰的注解具有继承性（注解修饰父类时子类也会被该注解修饰）
 */

/*
 * jdk8.0新增注解
 * 	@Repeatable:表示注解可重复（重复的注解的@Retention、@Target、@Inherited等注解要相同）
 */

//例2：编译时进行格式检查
class Person {

    public void show() {
    }

}

class Son extends Person {

    @Deprecated //过时提示
    Date date = new Date(2020, 1, 1);
    @SuppressWarnings("rawtypes") //抑制编译器警告（不会显示Warnings）
    ArrayList arrayList = new ArrayList();

    @Override //重写
    public void show() {
        // TODO Auto-generated method stub
        super.show();
    }

}

@MyAnnotation(value = "hello")
@MyAnnotation(value = "hello world")//自定义注解使用
class A {

    public void show() throws @MyAnnotation RuntimeException {

        int num = (@MyAnnotation int) 10; //ElementType.TYPE_USE表示注解可以修饰任何类型的数据

    }

}

class B extends A {
}

public class EA2 {

    //利用反射获取注解
    @Test
    public void show() {

        Class clazzClass = B.class;
        Annotation[] annotations = clazzClass.getAnnotations();
        for (Annotation item : annotations) {
            System.out.println(item);
        }
    }

}
