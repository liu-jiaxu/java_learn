package a5_Common_Class;

import org.junit.Test;

//常用类

//1.String
class StringTest {

	/*
	String：字符串，用""表示
		1.声明为final，不可继承
		2.实现了Serializable接口，表示字符串支持序列化
		3.实现了Comparable接口，表示可以比较大小
		4.内部定义了final char[] value用于存储字符串数据
		5.代表不可变字符序列，不可变性（一旦对象被创建并初始化后，内部的状态数据就会保持不变）
		6.通过字面量的方式给一个字符串赋值，此时字符串声明在常量池中，常量池不会存储相同的数据。例：String s1="abc";
	s1作为变量存在栈中，abc作为字符串内容存放在常量池中，变量s1地址指向常量池中的abc
		7.通过new+构造器的方式赋值时，一共创建了两个对象，一个是字符串对象，一个是常量池中的对象。例：String s2=new String("abc");
	创建两个对象，对象s2存放在堆中，字符串对象abc存在常量池中，对象s2地址指向常量池中的abc
	 */

}

public class CC1 {

    @Test
    public void test1() {

        //String实例化方式
        String s1 = "abc"; //字面量定义
        String s2 = new String("abc"); //new+构造器方式
        String s3 = new String();
        s3 = "abc";
        String s4 = new String("abc");
        //s2、s3、s4都是堆中的对象，地址都不同，但指向常量池同一数据

        //字符串比较
        System.out.println(s1 == s3); //true，此时视为基本数据类型，比较的仅仅是常量池中的内容的地址
        System.out.println(s1 == s2); //false，变量s1和对象s2地址不同
        System.out.println(s3 == s2); //false，此时因为s3和s2都是堆中的对象，比较的是对象地址
        System.out.println(s3.equals(s4)); //true，String重写了equals比较方法，比较实体值是否相等
        System.out.println(s3.equals(s2)); //true
        System.out.println();

    }

    @Test
    public void test2() {

        //String拼接比较
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = "a" + "b";
        //s1、s2、s3、s4均为变量，对应值存放在常量池中
        String s5 = "a" + s2; //s5为变量，但其指向的对象"a"+s2是在堆中存放的，而不是在常量池中
        String s6 = new String("ab");
        String s7 = new String("a" + "b");
        String s8 = new String("a" + s2);
        //s6、s7、s8均为对象，存放在堆中，地址指向内容存放在常量池中
        String s9 = s5.intern();
        //intern方法使变量跳过堆，只能在常量池中存储实体值，s9为变量，指向的实体值存放在常量池中

        System.out.println(s3 == s4); //true
        System.out.println(s3 == s5); //false
        System.out.println(s4 == s5); //false
        System.out.println(s6 == s7); //false
        System.out.println(s6 == s8); //false
        System.out.println(s6.equals(s7)); //true
        System.out.println(s6.equals(s8)); //true
        System.out.println(s7.equals(s8)); //true
        System.out.println(s4 == s9); //true
        System.out.println(s5 == s9); //false
        System.out.println(s7 == s9); //false
        System.out.println();

    }

    //String型变量实体值修改测试
    String s3 = new String("123");
    char[] ch = {'a', 'c', 'e'};

    public void change(String str, char[] ch) {
        str = "456";
        ch[0] = 'q';
    }

    @Test
    public void test3() {

        CC1 cc1 = new CC1();
        cc1.change(cc1.s3, cc1.ch);
        System.out.println(cc1.s3); //123
        System.out.println(cc1.ch); //qce
			/*
				String数据类型每次新建变量时（包括形参新建变量）都会在字符串池中新开辟地址存储，因此每次改变传入实参的值时，
            都不是对实参修改，而是对新创建的形参值进行了修改，因此String传入的实参值不变！
				但字符串数组ch不同，它的参数传递机制与String不同，虽然参数传递的还是实参的拷贝地址，但拷贝地址与实际地址相同，
			形参改变了地址副本地址也会改变原始地址，因此最终原始数据也会改变！
			 */
        System.out.println();

    }

    @Test
    public void test4() {

        //String常用方法
        String string1 = "  123456  abcdef  ";
        String string2 = "  123481  abcdef  ";
        System.out.println(string1.length()); //获取长度
        System.out.println(string1.charAt(0) + " " + string1.charAt(2) + " "); //获取字符串指定索引位置元素
        //System.out.println(string1.charAt(20)); //报错java.lang.StringIndexOutOfBoundsException: String index out of range: 20
        System.out.println(string1.isEmpty()); //是否为空，长度为0返回true
        System.out.println(string1.toLowerCase()); //字母变为小写
        System.out.println(string1.toUpperCase()); //字母变为大写
        System.out.println(string1.trim()); //去除字符串首尾的空格
        System.out.println(string1.equals(string2)); //比较两字符串实体值是否相等
        System.out.println(string1.concat("789")); //连接方法（一般不用）
        System.out.println(string1.compareTo(string2)); //比较两字符串相差的量（ascii码排序）
        System.out.println(string1.substring(4)); //删除指定索引前的原字符串内容
        System.out.println(string1.substring(4, 9)); //指定索引范围左闭右开，获取原字符串指定索引范围的字符内容（4,9表示索引从4-8）
        System.out.println(string1); //注意：调用所有方法后原始字符串不会改变（因为字符串声明为final）
        System.out.println(string1.endsWith("f  ")); //判断字符串是否以指定字符串结尾
        System.out.println(string1.startsWith("  A")); //判断字符串是否以指定字符串开头
        System.out.println(string1.startsWith("1", 2)); //判断字符串从指定位置开始是否以指定字符串开头
        System.out.println(string1.contains("abc")); //判断字符串是否包含指定字符串
        System.out.println(string1.indexOf("abc")); //找到指定字符串在原字符串中第一次出现的索引
        System.out.println(string1.indexOf("abc", 2));
        System.out.println(string1.lastIndexOf("a"));
        System.out.println(string1.lastIndexOf("e", 3));
        System.out.println(string1.replace(" ", "")); //替换指定字符串为后面的字符串
        System.out.println(string1.matches("\\d+")); //配合正则表达式使用，检查原字符串是否全部由数字组成

    }

}
