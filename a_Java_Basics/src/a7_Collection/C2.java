package a7_Collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class C2 {

    //iterator():遍历集合元素
    @Test
    public void test() {

        Collection collection = new ArrayList();
        collection.add(123);
        collection.add("hello");
        collection.add("hello");
        collection.add(true);
        Iterator iterator = collection.iterator();
			
		/*  
		//方式1：
		//每调用一次next方法就按顺序遍历一个元素
		System.out.println(iterator.next());
		System.out.println(iterator.next());	
		System.out.println(iterator.next());
		System.out.println(iterator.next());
		//当遍历完后再调用时会抛异常java.util.NoSuchElementException
		System.out.println(iterator.next());
		
		//方式2：for/foreach循环
		for(Object obj:collection) {
			System.out.println(iterator.next());
		}
		*/

        //方式3：hasNext()方法
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
			/*
				开始时指针指向iterator的开头，每执行一次hasNext()，指针都会下移一次，并且判断是否还有元素，
			若还有则输出true返回当前元素，并且继续遍历，若没有则指针停止下移结束遍历
			 */

        //注：当一个iterator对象遍历完所有元素后，其指针指向最后的元素，当还想继续遍历时，要新建另一个iterator对象来遍历！
        Iterator iterator2 = collection.iterator();
        while (iterator2.hasNext()) {
            Object object = iterator2.next();
            if ("hello".equals(object)) {
                iterator2.remove(); //通过遍历删除集合中的元素
            }
        }

        Iterator iterator3 = collection.iterator();
        while (iterator3.hasNext()) {
            System.out.print(iterator3.next() + " ");
        }

    }

}
