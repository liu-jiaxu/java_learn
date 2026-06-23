package a7_Collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
Collection接口
	List接口元素有序、可重复（动态数组）
		ArrayList：List接口的主要实现类，线程不安全，效率高，底层使用Object[] e存储
		LinkedList：底层使用双向链表存储，用于频繁的插入删除操作
		Vector：List接口的原始实现类，线程安全，效率低，底层使用Object[] e存储
	
	ArrayList、LinkedList、Vector三者异同：
		同：都实现了List接口，存储数据特点相同，存储有序、可重复的数组
		异：见上
*/

/*
jdk8.0中ArrayList源码分析
	ArrayList list =new ArrayList(); //此时底层的Object e初始化为{}，并没有创建长度为10的数组；
	list.add(123); //第一次调用add()时，底层才创建长度为10的数组并将元素123添加到数组中
	list.add(11); //当容量不够时，默认扩容1.5倍，同时转移数据到新数组中
	对象创建类似于单例的懒汉式，延迟数组创建，节省内存
jdk8.0中LinkedList源码分析
	双向链表模式
jdk8.0中Vector源码分析
	创建长度为10的数组，容量不够时，默认扩容2倍
 */

public class C3 {

    //List常用方法
    //增 删 改 查 插入 替换 长度 遍历
    @Test
    public void test() {

        ArrayList arrayList = new ArrayList();

        //1.add(int index,Object e)在index索引前插入e元素
        arrayList.add(12);
        arrayList.add(1, "qwq");
        arrayList.add(0, "qwe");
        System.out.println(arrayList);

        //2.addAll(int index,Collection e)在index索引前插入集合e的所有元素
        arrayList.addAll(1, Arrays.asList("aa", "cc"));
        arrayList.add(Arrays.asList("aa", "cc")); //注意add方法会将一个集合作为整体加入到现有
        System.out.println(arrayList);

        //3.get(int index)获取指定index索引的元素
        System.out.println(arrayList.get(4));

        //4.int indexOf(Object obj)返回obj在集合中首次出现的位置
        //5.int lastIndexOf(Object obj)返回obj在集合中最后出现的位置

        //6.Object remove(int index)移除指定索引的元素并return该元素
        //补充：Object remove(new Integer(index))移除指定index元素并输出该元素
        arrayList.remove(0);
        arrayList.remove(new String("qwq"));
        System.out.println(arrayList);

        //7.Object set(int index,Object obj)设置指定index索引的元素变为obj
        arrayList.set(0, "abc");
        System.out.println(arrayList);

        //8.List sublist(int fromIndex, int toIndex)返回该范围之间的子集合(左闭右开)
        List arrayList2 = arrayList.subList(0, 3);
        System.out.println(arrayList2);

        //9.size()得到当前集合长度
        System.out.println(arrayList.size());
		
		/*
		10.遍历
			Iterator
			for
			foreach
		 */
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        for (Object item : arrayList) {
            System.out.print(item + " ");
        }
        System.out.println();

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }

    }

}
