package a5_Common_Class;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

//开发中，需要对多个对象进行排序，即比较其大小，需要实现两个接口的任意一个：Comparable/Comparator

public class CC6 {

    //Comparable使用
	/*
		1.String等包装类实现了Comparable接口，重写了compareTo方法，给出了比较对象大小的方法
		2.默认从小到大排序
		3.自定义类需要实现Comparable接口，重写了compareTo方法，规定如何排序
		4.重写规则（正序时）：
			（1）当前对象this大于形参obj，返回正数；
			（2）当前对象this小于形参obj，返回负数；
			（3）两者相等，返回0
	 */
    @Test
    public void test() {

        String[] arr = new String[]{"A", "F", "Q", "V", "G", "B"};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println(Arrays.toString(arr));

    }

    //自然排序
    /*
        自然排序是一种默认的对象排序方式，它是根据对象的内在特征或属性来排序的。例如，对于整数，自然排序是按照数字的大小进行排序；
    对于字符串，自然排序是按照字母的字典顺序进行排序。自然排序通常是最直观和常见的排序方式，它使得对象在集合中以一种有序的方式存储和检索。
     */
    @Test
    public void test2() {

        Goods[] arr = new Goods[6];
        arr[0] = new Goods("len", 34);
        arr[1] = new Goods("hua", 25);
        arr[2] = new Goods("xiao", 76);
        arr[3] = new Goods("dell", 14);
        arr[4] = new Goods("micro", 76);
        arr[5] = new Goods("ping", 25);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    //Comparator的使用
    @Test
    public void test3() {

        String[] arr = new String[]{"A", "F", "Q", "V", "G", "B"};
        Arrays.sort(arr, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                        /*
                            a.compareTo(b)
                                a > b ：return > 1，表示compare正序
                                a = b ：return  0 ，表示compare正序
                                a < b ：return < -1，表示compare倒序
                         */
                }
                throw new RuntimeException("Error!");
            }

        });
        System.out.println(Arrays.toString(arr));

        Goods[] arr2 = new Goods[6];
        arr2[0] = new Goods("len", 34);
        arr2[1] = new Goods("hua", 25);
        arr2[2] = new Goods("xiao", 76);
        arr2[3] = new Goods("dell", 14);
        arr2[4] = new Goods("micro", 76);
        arr2[5] = new Goods("ping", 25);
        //指定商品先按名称升序，再按价格升序
        Arrays.sort(arr2, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Goods && o2 instanceof Goods) {
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;
                    //若名称相同则按价格排序，若名称不同则按名称排序
                    if (g1.getName().equals(g2.getName())) {
                        return Double.compare(g1.getPrice(), g2.getPrice());
                    } else {
                        return g1.getName().compareTo(g2.getName());
                    }
                }
                throw new RuntimeException("Error!");
            }

        });
        System.out.println(Arrays.toString(arr2));

    }

}

//商品类
class Goods implements Comparable {

    private String name;
    private double price;

    public Goods(String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods [name=" + name + ", price=" + price + "]";
    }

    //重写compareTo方法，并规定先按价格倒序，若价格相同按商品名称正序
    public int compareTo(Object object) {
        if (object instanceof Goods) {
            Goods goods = (Goods) object;
            //方式1
            if (this.price > goods.price) {
                return -1;
            } else if (this.price < goods.price) {
                return 1;
            } else {
                return this.name.compareTo(goods.name);
            }
            //方式2：return Double.compare(this.price,goods.price);
        }
        throw new RuntimeException("Error!");
    }

}