package a8_Generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class G3 {

    //泛型继承性
    @Test
    public void test() {
        //1.多态
        Object object = null;
        String string = null;
        object = string;

        Object object1[] = null;
        String string1[] = null;
        object1 = string1;

        //2.泛型错误使用
        List<Object> list = null;
        List<String> list2 = null;
        //list=list2;
        //error!虽然Object时String的父类，但原始类List不是子父类关系（并列关系），无法继承

        //3.泛型正确使用
        List<Integer> list3 = Arrays.asList(12);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(13);
        list3 = arrayList; //原始类为继承关系就可以使用泛型继承
        for (Integer item : list3) {
            System.out.println(item);
        }

    }

    //通配符？的使用
    @Test
    public void test2() {

        List<Object> list = Arrays.asList(22);
        List<String> list2 = Arrays.asList("abc");

        List<?> list3 = null; //相当于规定list3是list和list2的父类
        list3 = list2;
        //list3.add("a"); //注：使用通配符后，除了null不可以再添加其他元素
        System.out.println(list3.get(0));
        //使用通配符后可以读取数据，规定数据类型为Object

    }

    /*
    java 中泛型标记符：
        E - Element (在集合中使用，因为集合中存放的是元素)
        T - Type（Java 类）
        K - Key（键）
        V - Value（值）
        N - Number（数值类型）
        ？ - 表示不确定的 java 类型
     */

}

