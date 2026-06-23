package b3_Java8_NewFeatures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

//函数式接口FunctionalInterface：内部仅有一个抽象方法

/*
四大核心函数式接口
	消费型接口Consumer<T>：方法void accept(T t)有参无返回值
	供给型接口Supplier<T>：方法T get()无参有返回值
	函数型接口Function<T,R>：方法R apply(T t)函数操作
	断定型接口Predicate<T>：方法boolean test(T t)判断约束
 */

public class J8N2 {

    @Test
    public void test() {

        //常规
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double t) { //此处t相当于money，赋值为500
                System.out.println(t + 500);
            }
        });
        //lambda表达式
        happyTime(100, (Double money) -> System.out.println(money + 100));

    }

    public void happyTime(double money, Consumer<Double> con) {
        con.accept(money); //消费型接口接收money
    }

    @Test
    public void test2() {

        List<String> list = Arrays.asList("abc", "def", "qaz");
        //常规
        System.out.println(filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String t) {
                String[] s = {"abc", "def", "qaz"};
                for (String str : s) {
                    if (t.contains(str)) {
                        return true;
                    }
                }
                return false;
            }
        }));
        //lambda表达式
        System.out.println(filterString(list, s -> {
            String[] strings = {"abc", "def", "qaz"};
            for (String str : strings) {
                if (s.contains(str)) {
                    return true;
                }
            }
            return false;
        }));
        //contains()方法用于判断字符串中是否包含指定的字符或字符串，如果包含指定的字符或字符串返回true，否则返回false。

    }

    //根据给定的规则过滤掉集合中的字符串，此规则由Predicate决定
    public List<String> filterString(List<String> list, Predicate<String> pre) {
        //Predicate接口中有test方法，需要实现类重写
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                arrayList.add(s);
            }
        }
        return arrayList;
    }

}
