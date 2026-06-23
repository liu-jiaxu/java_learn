package a7_Collection;

import org.junit.Test;

import java.util.*;

/*
集合
	1.数组缺点
		（1）初始化后数组长度不可修改
		（2）数组的方法有限
		（3）数组无属性方法可用
		（4）数组存储数据有序、可重复
	2.集合框架
		（1）Collection接口
			List接口元素有序、可重复（动态数组）
				ArrayList、LinkedList、Vector
			Set接口元素无序、不可重复（不常用）
				HashSet、LinkedHashSet（HashSet的子类）、TreeSet
		（2）Map接口
			双列数据，映射关系key-value
			HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 */

public class C1 {

    //Collection使用及方法
    @Test
    public void test() {

        Collection collection = new ArrayList();

        //add(Object e):将e添加到集合collection中
        collection.add(123);
        collection.add("hello");

        //size():获取添加元素的个数
        System.out.println(collection.size());

        //addAll(Collection c):将集合c中的元素添加到当前集合中
        Collection collection2 = new ArrayList();
        collection2.addAll(collection);
        System.out.println(collection2);

        //clear():清空集合元素
        collection.clear();

        //isEmpty():判断当前集合是否有元素
        System.out.println(collection.isEmpty());

        //contains(Object obj):判断当前obj是否在集合中
        Collection collection3 = new ArrayList();
        collection3.add(new P("abc"));
        collection3.add(new String("aaa"));
        System.out.println(collection3.contains(new P("abc"))); //true，重写了P类的equals方法，不重写false
        System.out.println(collection3.contains(new String("aaa"))); //true，String类自动重写equals方法

        //containsAll(Collection c):判断当前形参obj中的所有元素是否都在集合中
        Collection collection4 = Arrays.asList(new P("abc"), new String("aaa"));
        System.out.println(collection3.containsAll(collection4));

        //remove(Object obj):移除集合中某个元素，成功返回true，失败返回false
        //removeAll(Collection c):移除集合中多个元素（这些元素以集合形式给出）
        Collection collection5 = new ArrayList();
        collection5.add(true);
        collection5.add("ace");
        collection5.add(new P("bgf"));
        collection5.remove(true);
        Collection collection6 = Arrays.asList("ace", new P("bgf"));
        collection5.removeAll(collection6);
        System.out.println(collection5);

        //retainAll(Collection c):保留两个集合中相同的元素，删除当前集合不同元素，注意当前集合元素数要大于参数集合c的元素数
        Collection collection7 = Arrays.asList("123", "456", "789");
        Collection collection8 = Arrays.asList("123", "456", "789", "001");
        collection7.retainAll(collection8);
        System.out.println(collection7);

        //equals(Object obj):判断两个集合所有集合是否相等（包括位置）
        Collection collection9 = Arrays.asList("123", "456", "789");
        Collection collection10 = Arrays.asList("123", "789", "456");
        System.out.println(collection9.equals(collection10));

        //hashCode():返回当前对象的哈希值
        System.out.println(collection10.hashCode());

        //toArray():集合->数组
        Object arr[] = collection9.toArray();
        for (Object item : arr) {
            System.out.print(item + " ");
        }

        //Arrays.asList():数组->集合
        List list = Arrays.asList(new Integer[]{123, 456, 789});
        System.out.println(list);
        List list2 = Arrays.asList(new int[]{123, 456, 789});
        System.out.println(list2); //注意不能用基本数据类型，否则会被识别成一个元素

        //iterator():遍历集合元素（见程序C2）

    }

    class P {

        private String name;

        public P(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //必须重写equals方法contains方法才可以判断同一个类是否含有该对象
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            P other = (P) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            return Objects.equals(name, other.name);
        }

        private C1 getEnclosingInstance() {
            return C1.this;
        }

        @Override
        public String toString() {
            return "P [name=" + name + "]";
        }

    }

}
