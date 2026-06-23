package b3_Java8_NewFeatures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Stream API提供高效的增删改查数据的方式
	操作：延迟性（只有执行了终止操作才会执行多个中间操作）
		创建、中间操作、终止操作
	特点：
		1.Stream不会存储元素
		2.Stream不会改变源对象，而是返回一个持有结果的新Stream
		3.操作延迟执行，等到需要的结果时才执行，之后不会再被使用
 */

public class J8N4 {

    //@Test2中用到的方法
    //将字符串多个字符构成的实例转换成对应Stream的实例
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    //输出改写
    private static void p(Object obj) {
        System.out.print(obj + " ");
    }

    //Stream创建
    @Test
    public void test() {

        //集合创建Stream
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        Stream<String> stream = list.stream(); //顺序流（按顺序取集合中的元素）
        Stream<String> stream2 = list.parallelStream(); //并行流（类似多线程，同时取元素所有元素）

        //数组创建Stream
        int[] arr = new int[]{1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(arr); //IntStream对应int型数据，自定义类型（指定泛型）时为Stream<>

        Emp emp = new Emp(0);
        Emp emp2 = new Emp(1);
        Emp[] e = new Emp[]{emp, emp2};
        Stream<Emp> stream3 = Arrays.stream(e); //自定义类型（指定泛型）时为Stream<>

        //通过Stream的of()
        Stream<Integer> stream4 = Stream.of(1, 2, 3, 4, 5);

        //创建无限流
        //1.迭代iterate(final T seed,final UnaryOperator<T> f)，seed为起始量，UnaryOperator为接口执行对应操作
        Stream.iterate(0, t -> t + 2).limit(20).forEach(System.out::println);
        //limit为限制条件，只取前20个数，forEach为每次执行的终止操作
        //2.生成generate(Supplier<T> s)，Supplier为接口执行对应操作
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

    }

    //Stream中间操作
    @Test
    public void test2() {

		/*
		1.筛选与切片
			filter(Predicate p)接收Lambda，从流中排除某些元素（断定型接口Predicate<T>：方法boolean test(T t)判断约束）
			distinct()筛选，通过流所生成元素的hashCode()和equals()去除重复元素
			limit(long maxSize)截断流,使其元素不超过给定数量
			skip(long n)跳过元素，近回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空源，与limit(n)互补
		 */
        List<Emp> list = new ArrayList<>();
        list.add(new Emp(10));
        list.add(new Emp(54));
        list.add(new Emp(91));
        list.add(new Emp(43));
        list.add(new Emp(17));
        list.add(new Emp(43));
        Stream<Emp> stream = list.stream();

        //filter(e->e.getI()>40)查询集合中Emp类i>40的对象
        stream.filter(e -> e.getI() > 40).forEach(System.out::print);
        System.out.println();

        //distinct()去除重复元素(注意需要重写hashCode和equals方法)
        list.stream().filter(e -> e.getI() > 40).distinct().forEach(System.out::print);
        //注：当一个stream执行终止操作后不会再被使用，要再次使用要创建新对象！
        System.out.println();

        //limit(1)仅查询一个数据便停止
        list.stream().filter(e -> e.getI() > 40).limit(1).forEach(System.out::print);
        System.out.println();

        //skip(2)跳过前两个对象直接查询其后的数据
        list.stream().filter(e -> e.getI() > 40).skip(2).forEach(System.out::print);
        System.out.println();

		/*
		2.映射
			map(Function f)接收一个函数作为参数，将元素转换为其它形式提取信息，该函数会被应用到每个元素上，并将其映射成一个新元素（类似add）
			flatMap(Function f)接收一个函数作为参数，将流中每个值都换成另一个流，然后把所有流连接成一个流（类似addAll）
		 */
        List<String> list2 = Arrays.asList("aa", "bb", "cc", "dd");
        list2.stream().map(str -> str.toUpperCase()).forEach(System.out::print);
        System.out.println();

        //常规遍历方法（两次遍历）
        Stream<Stream<Character>> stream2 = list2.stream().map(J8N4::fromStringToStream); //类::静态方法
        stream2.forEach(s -> {
            s.forEach(System.out::print);
        });
        System.out.println();
        //flatMap一次遍历
        list2.stream().flatMap(J8N4::fromStringToStream).forEach(System.out::print);
        System.out.println();

		/*
		3.排序
			sorted()自然排序
			sorted(Comparator com)定制排序
		 */
        //sorted()自然排序
        List<Integer> list3 = Arrays.asList(12, 54, 64, 34, 49, 41, 21, 9, 88);
        list3.stream().sorted().forEach(J8N4::p);
        System.out.println();
        //sorted(Comparator com)定制排序
        List<Emp> list4 = new ArrayList<>();
        list4.add(new Emp(10));
        list4.add(new Emp(54));
        list4.add(new Emp(91));
        list4.add(new Emp(43));
        list4.add(new Emp(17));
        list4.add(new Emp(43));
        list4.stream().sorted((e1, e2) -> Integer.compare(e2.getI(), e1.getI())).forEach(J8N4::p);
        System.out.println();
        //倒序排序
        list4.stream().sorted((e1, e2) -> -Integer.compare(e2.getI(), e1.getI())).forEach(J8N4::p);

    }

    //Stream终止操作
    @Test
    public void test3() {
		
		/*
		1.匹配与查找
			allMatch(Predicate p)检查是否匹配所有元素
			anyMatch(Predicate p)检查是否至少匹配一个元素
			noneMatch(Predicate p)检查是否没有匹配的元素
			findFirst()返回第一个元素
			findAny()返回当前流中的任意元素
			count()返回流中元素的总个教
			max(Comparator c)返回流中最大值
			min(Comparator c)返回流中最小值
			forEach(Consumer c)内部送代
		 */
        List<Emp> list5 = new ArrayList<>();
        list5.add(new Emp(10));
        list5.add(new Emp(54));
        list5.add(new Emp(91));
        list5.add(new Emp(43));
        list5.add(new Emp(17));
        //部分方法示例：
        System.out.println(list5.stream().allMatch(i -> i.getI() > 18));
        System.out.println(list5.stream().findAny()); //结果示例Optional[Emp [i=10]]
        System.out.println(list5.stream().max( (o1, o2) -> Integer.compare(o1.getI(), o2.getI()) )); //Optional[Emp [i=91]]

		/*
		2.归约
			reduce(T iden, BinaryOperator b)iden初始值，b要进行的操作，可以将流中元素反复结合起来，得到一个值。返回T
			reduce(BinaryOperator b)可以将流中元素反复结合起来，得到一个值。返回Optional<T>
		 */
        //例1：计算1-10的和
        List<Integer> list6 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list6.stream().reduce(0, Integer::sum)); //Integer::sum求和
        //例2：求和
        List<Double> list7 = Arrays.asList(106.01, 232.54, 39.4, 409.55, 509.32, 687.34, 77.00, 801.01, 909.01);
        // Double和Float包装类是对double、float基本类型的封装，它们都是 Number 类的子类
        System.out.println(list7.stream().reduce(Double::sum));
		
		/*
		3.收集
		    toArray()收集流中的数据存储到数组中
			collect(Collector collector)收集流中的数据存储到集合中。将接口转换为其他形式，用于给Stream中元素做汇总的方法
		 */
        //例：查找集合中大于指定值的数据
        List<Integer> list8 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list8.stream().filter(e -> e > 5).collect(Collectors.toList()));
        //Collectors.toList()、Collectors.toSet()

        /*
        4.合并
            concat(Stream a, Stream b)合并两个流
         */
        Stream.concat(list6.stream(), list7.stream()).forEach(J8N4::p);

    }

}

class Emp {
    public int i;

    public Emp(int i) {
        super();
        this.i = i;
    }

    @Override
    public String toString() {
        return "Emp [i=" + i + "]";
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Emp other = (Emp) obj;
        return i == other.i;
    }
}
