package b3_Java8_NewFeatures;

import org.junit.Test;

import java.io.PrintStream;
import java.util.function.Consumer;

/*
方法引用
	1.使用：当要传递给lambda体的操作已经有实现的方法时，可以使用方法引用
	2.本质：是lambda表达式，是函数式接口的实例
	3.格式：类（对象）::方法名
	4.具体三种情况：
		对象::非静态方法
		类::静态方法
		类::非静态方法
 */

public class J8N3 {

    @Test
    public void test() {

        //lambda表达式
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("hello!");

        //方法引用示例
        PrintStream printStream = System.out; //现在才知道System.out是个对象...
        Consumer<String> consumer2 = printStream::println; //对象::非静态方法
        consumer2.accept("hello");

    }

}
