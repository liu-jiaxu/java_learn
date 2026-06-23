package test;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * ClassName: T5
 * Package: a1_Basics.test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/11 - 17:06
 * @Version: v1.0
 */

public class T5 {

    public static void main(String[] args) {

        String[] arr = {"王昭君", "王昭君", "西施", "杨玉环", "貂蝉"};
        List<String> list = Arrays.asList(arr);
        list.forEach(System.out::println);

    }

    @Test
    public void test() {

        String[] arr = {"张三", "李四", "王五", "二丫", "钱六", "孙七"};
        List<String> list = Arrays.asList(arr);
        for (int i = 0; i < list.size(); i++) {
            if ("二丫".equals(list.get(i))) {
                list.set(i, "王二丫");
            }
        }
        list.forEach(System.out::println);

    }

    @Test
    public void test3() {

        Set<String> set = new HashSet<>();
        set.add("王昭君");
        set.add("王昭君");
        set.add("西施");
        set.add("杨玉环");
        set.add("貂蝉");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        /*set.forEach(System.out::println);*/
        System.out.println();

    }

    @Test
    public void test4() {

        String[] arr = {"王昭君", "王昭君", "西施", "杨玉环", "杨玉环"};
        List<String> list = Arrays.asList(arr);
        Set<String> set2 = new HashSet<>(list);
        for (String s : set2) {
            System.out.println(s);
        }

        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            Integer count = map.get(s);
            map.put(s, (count == null) ? 1 : count + 1);
        }

        for (Object o : map.entrySet()) {
            System.out.print(o + " ");
        }

    }

    @Test
    public void test5() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        /*for (int i = 1; i <= 10; i++) {
            list.add(i);
        }*/
        for(Iterator<Integer> it = list.iterator(); it.hasNext();){
            Integer i = it.next();
            if(i % 2 != 0){
                it.remove();
            }
        }
        /*for (Integer i : list) {
            if (i % 2 != 0) {
                list.remove(i);
            }
        }*/
        list.forEach(System.out::print);

        List<Integer> list2 = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            list2.add(i);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("ji", 0);
        map.put("ou", 0);
        for (Integer i : list2) {
            if (i % 2 != 0) {
                Integer value = map.get("ji");
                map.put("ji", ++value);
            } else {
                Integer value = map.get("ou");
                map.put("ou", ++value);
            }
        }
        for (Object o : map.entrySet()) {
            System.out.print(o + " ");
        }

    }

}
