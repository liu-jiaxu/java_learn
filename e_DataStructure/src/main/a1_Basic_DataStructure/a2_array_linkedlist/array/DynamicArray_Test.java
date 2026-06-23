package main.a1_Basic_DataStructure.a2_array_linkedlist.array;

import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * ClassName: DynamicArray_Test
 * Package: main.a1_Basic_DataStructure.a2_array_linkedlist
 * Description:动态数组测试类
 *
 * @Author: zgh296
 * @Create: 2023/5/6 - 9:41
 * @Version: v1.0
 */
public class DynamicArray_Test {

    // 测试
    @Test
    public void test() {

        DynamicArray dynamicArray = new DynamicArray();
        DynamicArray dynamicArray2 = new DynamicArray();
        DynamicArray dynamicArray3 = new DynamicArray();

        // 添加、删除、修改数据
        dynamicArray.add(0,1); // [1]
        dynamicArray.add(1,2); // 1 [2]
        dynamicArray.add(2,3); // 1 2 [3]
        dynamicArray.add(2,8); // 1 2 [8] 3
        dynamicArray.add(0,19); // [19] 1 2 8 3
        dynamicArray.addLast(9); // 19 1 2 8 3 [9]
        dynamicArray.addFirst(100); // [100] 19 1 2 8 3 9
        dynamicArray.addLast(200); // 100 19 1 2 8 3 9 [200]
        dynamicArray.removeFirst(); // x100x 19 1 2 8 3 9 200
        dynamicArray.removeLast(); // 19 1 2 8 3 9 x200x
        assertEquals(1, dynamicArray.remove(1)); // 断言通过 19 x1x 2 8 3 9
        dynamicArray.update(1,8); // 19 2->8 8 3 9
        dynamicArray.updatePosition(0,9,15); // 19 8 8 3 9->15
        dynamicArray.updateAllChoose(8,80); // 19 8->80 8->80 3 15

        // 获取单个元素及其索引位置
        System.out.println(dynamicArray.getElement(3)); // 3
        System.out.println(dynamicArray.getElement(0)); // 19
        System.out.println(dynamicArray.getIndex(80)); // [1, 2]

        // 遍历
        // 传入Integer类型参数element，accept方法接收sout语句并传值给element
        dynamicArray.foreach((element)->{
            System.out.print(element + " ");
        }); // 19 80 80 3 15
        // 增强for循环
        for(Object element:dynamicArray){
            System.out.print(element+" ");
        } // 19 80 80 3 15
        System.out.println();
        // 数据流
        dynamicArray.stream().forEach((element)->{
            System.out.print(element+" ");
        }); // 19 80 80 3 15
        System.out.println();

        // 扩容测试
        for (int i = 0; i < 10; i++) {
            dynamicArray2.addLast(i+1);
        }
        /*
          assertIterableEquals方法
             断言预期和实际的迭代是完全相同的。若为迭代器必须以相同的顺序返回相等的元素，但迭代器不需要是同一类型。
          List.of方法：Jdk9版本及之后
              1.添加元素后，接口里面的元素不能够再次改变，也就是说集合中存储的元素的个数已经是确定的不再改变。
              2.只能用于List接口，Set接口和Map接口不适用与接口的实现类
              3.of方法返回的是一个不能改变的集合，不能再用add()和put(K,V)方法添加元素
              4.Set和Map接口在调用of()方法的时候不能添加重复的元素不然就会抛出异常
         */
        List list = List.of(1,2,3,4,5,6,7,8,9,10);
        assertIterableEquals(list,dynamicArray2); // 断言通过

        int[] a1 = new int[15];
        int[] a2 = new int[15];
        for (int i = 0; i < a1.length; i++) {
            a1[i] = i + 1;
            a2[i] = i + 3;
        }
        dynamicArray3.addLast(4);

        int[] a3 = dynamicArray3.merge(a1,0, a1.length - 1, a2,0, a2.length - 1);
        for (int i = 0; i < a3.length; i++) {
            System.out.print(a3[i] + "-");
        }

    }

}
