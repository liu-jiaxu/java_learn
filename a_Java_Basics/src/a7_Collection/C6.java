package a7_Collection;

import org.junit.Test;

import java.util.*;

/*
Map接口
	双列数据，映射关系key-value
	HashMap：主要实现类，线程不安全，效率高，可存储null
		LinkedHashMap：链表结构，按照顺序遍历，效率高
	TreeMap：红黑树结构，可以按照key进行排序（自然/定制）和遍历
	Hashtable（不常用）：原始实现类，线程安全，效率低，不可存储null
		Properties：处理配置文件，key和value都是String型
 */

/*
Map结构理解：
	1.Map中的key：无序，不可重复，使用Set存储 -> key所在类要重写hasCode()和equals()
		补充：map默认是按key值从小到大排序的
	2.Map中的value：无序，可重复，使用Collection存储 -> value所在类要重写equals()
	3.一个键值对key-value构成一个Entry对象
	4.Map中的Entry：无序，不可重复，使用Set存储
 */

/*
jdk8中HashMap底层实现原理
	HashMap map = new HashMap():
		在实例化以后，底层未创建数组，当调用put方法时，创建了长度为16的一维数组Node[] table
	map.put(key1, value1):
		首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Node数组中的存放位置
		如果此位置上的数据为空，此时的key1-vaLue1添加成功		----情况1
		如果此位置上的数据不为空(意味着此位置上存在一个或多个数据(以链表形式存在))，比较key1和已经存在的一个或多个数据的哈希值:
			如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-vaLue1添加成功
			如果key1的哈希值和已经存在的某一个数据(key2-value2)的哈希值相同，继读比较:调用key1所在类的equals(key2)
				如果equaLs()返回false:此时key1-value1添加成功		----情况2
				如果equals()返回true:使用value1替换value2	----情况3
	补充：
		1.情况2、3此时多个key-value数据以链表形式存储
		2.扩容问题：扩容为原先的两倍
		3.jdk8底层结构为：数组+链表+红黑树
			当数组某一位置元素以链表形式存储时 且 链表中数据个数>8 且 当前数组长度>64时，将链表替换为红黑树结构，便于比较存储
 */

public class C6 {

    @Test
    public void test() {

        Hashtable hashtable = new Hashtable();
        hashtable.put(null, "qwe"); //Hashtable不能存放null值

    }

    //Map常用方法
    //增 删 改 查 替换 长度 判断 获取key-value 遍历
    @Test
    public void test2() {

        //1. Object put(Object key,Object value)将指定key-value添加（修改）到当前map对象中
        HashMap map = new HashMap();
        map.put("a", 13);
        map.put("b", 55);
        map.put("c", 6);
        map.put("a", 41); //添加时相同的key会替换原先对应的value值
        System.out.println(map);

        //2.void putAll(Map m)将m中所有的key-value添加（修改）到当前map对象中
        HashMap map2 = new HashMap();
        map2.put("f", 88);
        map2.put("e", 8);
        map2.put("d", 34);
        map2.put("a", 11);
        map.putAll(map2);
        System.out.println(map); //map默认是按key值从小到大排序的 {a=11, b=55, c=6, d=34, e=8, f=88}

        //3. Object remove(Object key)移除指定key对应的key-value对并返回value
        map.remove("f");
        System.out.println(map);

        //4.void clear()清空当前map中的所有数据
        map.clear();
        System.out.println(map); //对象map仍存在，但其中没有数据了

        //5.Object get(Object key)获取指定key对应的vaLue
        map.put("a", 13);
        map.put("b", 55);
        map.put("c", 55);
        map.put("d", 2);
        System.out.println(map.get("b"));
        System.out.println(map.get("q")); //null

        //6.boolean containsKey(Object key)是否包含指定的key
        System.out.println(map.containsKey("a"));

        //7.boolean containsValue(Object value)是否包含指定的value
        System.out.println(map.containsValue(12));

        //8.int size():返回map中key-value对的个数
        System.out.println(map.size());

        //9.boolean isEmpty()判新当前map是否为空
        System.out.println(map.isEmpty());

        //10.boolean equals(Object obj):判断当前map和参数对象obj是否相等
        System.out.println(map.equals(map2));

        //遍历
        //11.Set keySet()返回所有key构成Set集合
        Set set = map.keySet();
        for (Object item : set) {
            System.out.print(item + " ");
        }
        System.out.println();

        //12.Collection values()返回所有value构成Collection集合
        Collection collection = map.values();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        //13.Set entrySet()返回所有key-value构成Set集合
        Set set2 = map.entrySet();
		for (Object o : set2) {
			System.out.print(o + " ");
		}
        //Set和Collection没有get方法获取元素，不能用普通for循环

    }

}
