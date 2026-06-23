package main.a2_Basic_Algorithms.a5_hashtable;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * ClassName: HashTable_Test
 * Package: main.a2_Basic_Algorithms.a5_hashtable
 * Description:哈希表测试类
 *
 * @Author: zgh296
 * @Create: 2023/6/25 - 11:19
 * @Version: v1.0
 */
public class HashTable_Test {

    // hash测试
    @Test
    public void test() throws IOException {

        // 测试 Object.hashCode
        HashTable table = new HashTable();
        for (int i = 0; i < 200000; i++) {
            Object obj = new Object();
            table.put(obj, obj);
        }
        table.print();

        // murmur3_32测试
        HashTable table2 = new HashTable();
        for (int i = 0; i < 200000; i++) {
            String str = i + "abc";
            table2.put(str, str);
        }
        table2.print();
        table2.print2();

    }

    @Test
    public void test2() {

        HashTable table = new HashTable();
        String[] str = new String[]{"wee"};
        System.out.println(table.mostCommonWord2("he he He, la la La wee. wee. wee. wee", str));

    }

}
