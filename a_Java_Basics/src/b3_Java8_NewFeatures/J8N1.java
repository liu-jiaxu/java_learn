package b3_Java8_NewFeatures;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

//Lambda表达式（是一个匿名函数）
    // CLass class =  (参数列表) -> { 其他语句 + return返回值 }

public class J8N1 {

    @Test
    public void test() {

        //原式
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("22333333");
            }
        };
        runnable.run();
        //Lambda表达式
        Runnable runnable2 = () -> System.out.println("11444444");
        runnable2.run();

        //原式
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator.compare(12, 21));
        //Lambda表达式：(参数) -> return返回值
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comparator2.compare(12, 21));
        //方法引用
        Comparator<Integer> comparator3 = Integer::compare;
        System.out.println(comparator3.compare(12, 21));

    }
	
	/*
	Lambda表达式使用（6种情况）
	格式：
		->Lambda操作符
		->左边：lambda形参列表（接口中的抽象方法的形参列表）
		->右边：lambda体（其实就是重写的抽象方法的方法体）
	举例：
		(o1,o2)->{Integer.compare(o1,o2);};
		参数列表->方法体
	本质：
		本质是作为函数式接口的实例（对象），其接口只能有一个抽象方法（函数式接口）
	 */

    //格式1：无参，无返回值
    @Test
    public void test2() {
        First f1 = () -> System.out.println("123");
        f1.f();
    }

    //格式2：有参，无返回值
    @Test
    public void test3() {
        //例1：
        Second s1 = (String s) -> System.out.println(s);
        s1.s("hahaha");
        //例2：
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println(t);
                System.out.println(t);
            }
        };
        Consumer<String> consumer2 = (String t) -> {
            System.out.println(t);
            System.out.println(t);
        }; //方法体中包含多个语句时，要用{}包括
        consumer2.accept("lalala");
    }

    //格式3：无参，有返回值
    @Test
    public void test4() {
        Third t1 = () -> "hello!";
        t1.t();
    }

    //格式4：省略数据类型，由编译器推断出，称为类型推断（之前数组和泛型学过）
    //补充：当只有一个参数时可以省略()
    @Test
    public void test5() {
        Fourth f1 = (s) -> System.out.println(s);
        Fourth f2 = s -> System.out.println(s); //参数()省略
        f1.f("huahuahua");
    }

    //格式5：两个以上的参数，有返回值，可以直接省略方法体中除了返回值的其他部分
    @Test
    public void test6() {
        //例1：
        Fifth f1 = (i, j) -> {
            if (i >= j) {
                return i;
            } else {
                return j;
            }
        };
        //例1实现了接口之后
        System.out.println(f1.f(13, 8));
        Fifth f2 = new Fifth() {
            @Override
            public int f(int i, int j) {
                return 0;
            }
        };
        Fifth f3 = (i, j) -> 0;
        System.out.println(f3.f(10, 20));
        //例2：
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2); //直接省略其余部分（{}和return要一起省略或保留）
        Comparator<Integer> comparator3 = Integer::compare;
        System.out.println(comparator2.compare(12, 21));
    }

    interface First {
        public void f();
    }

    interface Second {
        public void s(String s);
    }

    interface Third {
        public String t();
    }

    interface Fourth {
        public void f(String s);
    }

    interface Fifth {
        public int f(int i, int j);
    }

}
