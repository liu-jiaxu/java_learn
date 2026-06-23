package a7_Collection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class C8 {

    public static void main(String[] args) {

        //Properties类使用
        //文件操作需要在try-catch中
        FileInputStream fileInputStream = null;
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("D:\\SoftwareInstallation\\ideaIU_20220301\\file_saving\\Java_Backend\\a_Java_Basics\\src\\a7_Collection\\C81.properties");
            //创建文件
            properties.load(fileInputStream); //加载文件
            String name = properties.getProperty("name"); //获取文件的key
            String password = properties.getProperty("password");
            System.out.println(name + " " + password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //将编码格式改为ISO-8859-1可识别中文
		
		
		
		/*
		Collections工具类
			Collections是一个操作Set、List和Map等集合的工具类
			Collections中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作,还提供了对集合对象设置不可变、对集合对象实现同步控制等方法
			Collection和Collections的区别：Collection是存储一系列数据的接口，Collections是操作Collection的工具类
		 */	
			
		/*	
		Collections方法
			排序操作: (均为static方法)
				reverse(List):反转List中元素的序
				shuffle(List):对List集合元素进行随机排序
				sort(List):根据元素的自然顺序对指定List集合元素按升序排序
				sort(List, Comparator):根据指定的Comparator产生的顺序对List集合元素进行排序
				swap(List, int, int):将指定list集合中的i处元素和j处元素进行交换
			查找、替换
				Object max(Collection):根据元素的自然顺序,返回给定集合中的最大元素
				Object max(Collection, Comparator):根据Comparator指定的期序,返回给定集合中的最大元素
				Object min(Collection):根据元素的自然顺序,返回给定集合中的最小元素
				Object min(Collection, Comparator):根据Comparator指定的期序,返回给定集合中的最小元素
				int frequency(Collection, Object):返回指定集合中指定元素的出现次数
				void copy(List dest,List src):将src中的内容复制到dest中
				boolean replaceAll(List list, Object oldVal, Object newVal):使用新值替换List对象的所有旧值
			线程安全
				synchronizedXxx():指定集合包装成线程同步的集合
		*/
        List list = new ArrayList();
        list.add(12);
        list.add(2);
        list.add(65);
        list.add(-412);
        list.add(65);

        Collections.reverse(list);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        System.out.println(Collections.max(list));

        System.out.println(Collections.frequency(list, 65));

        //注意使用copy方法时新集合的长度要大于等于原数组
        List list2 = Arrays.asList(new Object[list.size()]);
        Collections.copy(list2, list);
        System.out.println(list2);

        List list3 = Collections.synchronizedList(list);

    }

}
