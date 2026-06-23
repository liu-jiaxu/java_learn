package a8_Generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//自定义泛型类
class Gen<T> {

    String name = "Jack";
    int id = 1;
    T orderT;

    public Gen() {
    }

    public Gen(String name, int id, T orderT) {
        this.name = name;
        this.id = id;
        this.orderT = orderT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Gen [name=" + name + ", id=" + id + ", orderT=" + orderT + "]";
    }

}

class SubGen<T> extends Gen<T> {

    String name;
    int id;
    T orderT;

    public SubGen() {
        this.name = super.name;
        this.id = super.id;
        this.orderT = super.orderT;
    }

    public SubGen(String name, int id, T orderT) {
        this.name = name;
        this.id = id;
        this.orderT = orderT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //注：不是在方法中出现泛型就是泛型方法
    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Gen [name=" + name + ", id=" + id + ", orderT=" + orderT + "]";
    }

    //泛型方法：在方法中出现泛型结构，泛型参数与类的泛型参数没有任何关系（可声明为静态）
    public <E> List<E> Copy(E[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    } //泛型E也可以直接指定在类名上！

}

public class G2 {

    public static void main(String[] args) {

        Gen<Double> gen = new Gen<Double>();
        gen.setId(0);
        gen.setName("ahua");
        gen.setOrderT(25.33);
        System.out.println(gen.toString());

        //子类测试
        SubGen<Double> subgen = new SubGen<Double>();
        System.out.println(subgen.toString());

        //泛型方法测试
        Integer arr[] = new Integer[]{1, 2, 3, 4};
        List<Integer> list = subgen.Copy(arr);
        System.out.println(list);

    }

}
/*
	1.泛型类可能有多个参数，此时应将多个参数一起放在尖括号内。比如:<E1,E2,E3>
	2. 泛型类的构造器如下: public GenericClass(){}，而public GenericClass<E>(){}是错误的
	3.实例化后，操作原来泛型位置的结构必须与指定的泛型类型一致。
	4.泛型不同的引用不能相互赋值。尽管在编译时ArrayList<String>和ArrayList<Integer>是两种类型，但是，在运行时只有。一个ArrayList被加载到JVM中。
	5.泛型如果不指定，将被擦除，泛型对应的类型均按照Object处理，但不等价于Object。经验:泛型要使用一路都用。要不用，一路都不要用。
	6.如果泛型结构是一个接口或抽象类，则不可创建泛型类的对象。
	7. jdk1.7，泛型的简化操作: ArrayList<Fruit> first = new ArrayList<>():
	8.泛型的指定中不能使用基本数据类型，可以使用包装类替换。
	9.在类/接口上声明的泛型，在本类或本接口中即代表某种类型，可以作为非静态属性的类型、非静态方法的参数类型、非静态方法的返回值类型。但在静态方法中不能使用类的泛型。
	10.异常类不能是泛型的
	11.不能使用new E[]。但是可以: E[] elements = (E[])new Object[capacity];
		参考: ArrayList源码中声明: Object[] elementData，而非泛型参数类型数组。
	12.父类有泛型，子类可以选择保留泛型也可以选择指定泛型类型:
		class Father<T,E> {}
		子类不保留父类的泛型:按需实现
		>没有类型擦除 class Son extends Father {}
		>具体类型 class Son extends Father<Integer,String> {}
		子类保留父类的泛型:泛型子类
		>全部保留 class Son<T,E> extends Father<T,E> {}
		>部分保留 class Son<T> extends Father<T,Double> {}
		结论:子类必须是“富二代”，子类除了指定或保留父类的泛型，还可以增加自己的泛型
 */
