package a8_Generic;

import org.junit.Test;

import java.util.*;

/*
泛型（是一个类）
	允许在定义类、接口时通过一个标识表示类中某个属性的类型或方法返回值及参数类型，这个类型的参数将在使用时确定
 */

public class G1 {

    public static void main(String[] args) {

        //例：集合中使用泛型
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 110);
        map.put("B", 111);
        map.put("D", 112);
        map.put("Q", 113);
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator2 = set.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }    //HashMap中按指定编码排序

    }

    /*
    不使用泛型的缺点
        1.类型不安全
        2.类型强转容易异常
     */
    @Test
    public void test() {

        ArrayList arrayList = new ArrayList();
        arrayList.add(12);
        arrayList.add(34);
        arrayList.add(41);
        arrayList.add("Tom");
        for (Object item : arrayList) {
            int i = (Integer) item;
            System.out.println(i);
        }

    }

    //泛型的使用（要用数据的包装类，因为要使用方法）
    @Test
    public void test2() {

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        //泛型使用，此处仅可以传入Integer的数据类型
        arrayList.add(12);
        arrayList.add(34);
        arrayList.add(41);
        //arrayList.add("Tom"); //此处编译时会进行类型检查
        for (Object item : arrayList) {
            //int i=(Integer)item; //使用泛型也避免了类型转换
            System.out.println(item);
        }
        Iterator<Integer> iterator = arrayList.iterator();
        //使用Iterator时指定泛型

    }

}
