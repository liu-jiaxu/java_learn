package test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * ClassName: T2
 * Package: a1_Basics.test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/6 - 16:31
 * @Version: v1.0
 */

public class T2 {

    /*
    1. 定义一个数组 int[] array = { 6, 7, 0, 2, 4, 9, 8, 5, 6, 1 }，在课上示例的基础上完成如下要求：
       1. 找出数组中的所有奇数，按从小到大进行排序（排序算法）
       2. 找到数组中的所有偶数，按从大到小进行排序
       3. 将所有的偶数排到奇数之前，输出
    2. 评委打分
       比赛中，6名评委打分（0~100之间），去掉一个最高分，去掉一个最低分，选手的得分是剩余的分数的平均分，
   完成上述过程计算出选手的得分
     */

    // 没有注释的。。。
    @Test
    public void test1() {

        int[] array = {6, 7, 0, 2, 4, 9, 8, 5, 6, 1};
        int ji = 0, ou = 0;
        for (int k : array) {
            if (k % 2 != 0) {
                ji++;
            } else {
                ou++;
            }
        }

        int[] a = new int[ji];
        int[] b = new int[ou];
        int jiCount = 0, ouCount = 0;
        for (int k : array) {
            if (k % 2 != 0) {
                a[jiCount] = k;
                jiCount ++;
            } else {
                b[ouCount] = k;
                ouCount ++;
            }
        }

        Arrays.sort(a);
        for (int i = 0; i < b.length - 1; i++) {
            for (int j = 0; j < b.length - i -1; j++) {
                if (b[j] > b[j + 1]) {
                    int temp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = temp;
                }
            }
        }

        int[] arr = new int[array.length];
        /*
          合并数组：使用System.arraycopy(src, srcPos, dest, destPos, length);
              src-源数组，srcPos-源数组元素组起始索引
              dest-目标数组，destPos-目标数组元素起始索引
              length-复制长度
         */
        // b：排序好的偶数数组
        System.arraycopy(b, 0, arr, 0, b.length);
        // a：排序好的奇数数组
        System.arraycopy(a, 0, arr, b.length, a.length);


        /*int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                arr[index] = array[i];
                index ++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                arr[index] = array[i];
                index ++;
            }
        }*/

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(arr));

    }

    public double score(int[] a) {
        Arrays.sort(a);
        int[] arr = Arrays.copyOfRange(a, 1, a.length - 1);
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return 1.0 * sum / arr.length ;
    }

    @Test
    public void test2() {

        int[] a = {82, 76, 56, 55, 72, 99};
        System.out.println(score(a));

    }

}
