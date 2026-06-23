package a7_Collection;

import org.junit.Test;

import java.util.*;

/*
Collection接口
	Set接口元素无序、不可重复（不常用）
		HashSet：Set即可的主要实现类，线程不安全，可存储null值
			LinkedHashSet：HashSet的子类，利用链表存储可按顺序遍历
		TreeSet：利用红黑树存储，可以按照添加对象的属性进行排序
 */

/*
	1.无序性!=随机性
		以HashSet为例，存储元素时，并不是按照索引顺序存储，而是按照元素的哈希值决定存储
	2.不可重复	
		按照equals判断，相同的元素只能有一个
	添加元素：
		以HashSet为例，添加元素a时，首先调用元素a所在类的hashCode方法获取元素a的哈希值，通过某种计算方法
	计算出在HashSet中的存放位置，并判断该位置上是否有其他元素，若没有则元素a直接添加，若有其他一个或多个元
	素，则依次比较这些元素的哈希值，若哈希值不相同则按链表的形式存储（一个链表为一个整体，仅占一个位置，用
	指针向外扩展），若哈希值相同则调用equals方法，equals返回true时添加失败，返回false则按链表顺序添加。
 */

class ST implements Comparable {

    public String name;
    public int age;

    public ST(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ST other = (ST) obj;
        return age == other.age && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "ST [name=" + name + ", age=" + age + "]";
    }

    //指定姓名从大到小排序，年龄从小到大排序
    @Override
    public int compareTo(Object o) {
        if (o instanceof ST) {
            ST st = (ST) o;
            int compare = -this.name.compareTo(st.name);
            if (compare != 0) {
                return compare;
            } else {
                return Integer.compare(this.age, st.age);
            }
        } else {
            throw new RuntimeException("Error!");
        }
    }

}

public class C4 {

    //HashSet
    @Test
    public void test() {

        Set set = new HashSet();
        set.add(123);
        set.add(123);
        set.add(new ST("qw", 12));
        set.add(new ST("qw", 12)); //由于重写了hashCode和equals方法，所以这两个对象不同
        set.add("cc");
        set.add(true);

        for (Object item : set) {
            System.out.println(item);
        }

    }

    //例：利用HashSet去除List中的重复数据
    @Test
    public void test2() {
        //注：若去除自定义类对象时要重写hashCode方法
        List list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        HashSet hashSet = new HashSet();
        hashSet.addAll(list);
        for (Object item : hashSet) {
            System.out.println(item);
        }

    }

    //LinkedHashSet：按顺序遍历，会记录一个数据的前后数据，遍历效率优于HashSet
    @Test
    public void test3() {

        LinkedHashSet set = new LinkedHashSet();
        set.add(123);
        set.add(123);
        set.add(new ST("qw", 12));
        set.add(new ST("qw", 12));
        set.add("cc");
        set.add(true);

        for (Object item : set) {
            System.out.println(item);
        }

    }

    /*
    TreeSet
        1.TreeSet中添加的数据必须是同一个类的对象
        2.两种排序方式（不按equals而是compareTo比较）：自然排序、定制排序
     */
    @Test
    public void test4() {

        //例1：比较int型
        TreeSet set = new TreeSet();
        set.add(123);
        set.add(123);
        set.add(19);
        set.add(143);
        set.add(-89);
        for (Object item : set) {
            System.out.println(item);
        }
        System.out.println();

        //例2：比较ST型（自然排序：重写compareTo方法）
        TreeSet set2 = new TreeSet();
        set2.add(new ST("a", 12));
        set2.add(new ST("b", 15));
        set2.add(new ST("d", 11));
        set2.add(new ST("c", 15));
        set2.add(new ST("d", 18));
        set2.add(new ST("d", 18));
        for (Object item : set2) {
            System.out.println(item);
        }
        System.out.println();

        //例3：比较ST型（定制排序）
        Comparator com = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof ST && o2 instanceof ST) {
                    ST st = (ST) o1;
                    ST st2 = (ST) o2;
                    int compare = Integer.compare(st.age, st2.age);
                    if (compare != 0) {
                        return compare;
                    } else {
                        return st.name.compareTo(st2.name);
                    }
                } else {
                    throw new RuntimeException("error!");
                }
            }

        };
        TreeSet set3 = new TreeSet(com); //按照com定制排序
        set3.add(new ST("a", 12));
        set3.add(new ST("b", 15));
        set3.add(new ST("d", 11));
        set3.add(new ST("c", 15));
        set3.add(new ST("d", 18));
        set3.add(new ST("d", 18));
        for (Object item : set3) {
            System.out.println(item);
        }

    }

}
