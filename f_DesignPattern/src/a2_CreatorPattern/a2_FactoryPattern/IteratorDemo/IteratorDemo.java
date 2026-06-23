package a2_CreatorPattern.a2_FactoryPattern.IteratorDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: IteratorDemo
 * Package: a2_CreatorModel.a2_FactoryPattern.IteratorDemo
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 10:31
 * @Version: v1.0
 */
public class IteratorDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("令狐冲");
        list.add("风清扬");
        list.add("任我行");
        //获取迭代器对象
        Iterator<String> it = list.iterator();
        //使用迭代器遍历
        while(it.hasNext()) {
            String ele = it.next();
            System.out.println(ele);
        }

    }

}
