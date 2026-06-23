package a5_Common_Class;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CC2 {

    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return 出现的次数
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        // 将指定字符串变为正则表达式，与原字符串进行匹配，每匹配一次则记录
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        } // 利用for遍历也可以
        return count;
    }

    @Test
    public void test() {

        //String -> char[]：调用String的toCharArray()
        String string = "123abc";
        char[] ch = string.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            System.out.print(ch[i] + " ");
        }
        //cha[] -> String：调用String构造器
        char[] ch2 = new char[]{'1', '3', 'a', 'b'};
        String string2 = new String(ch2);
        System.out.println(string2);

    }

    @Test
    public void test2() {

        //String -> byte[]：调用String的getBytes()
        String string3 = "zxcvbn一二";
        byte[] bytes = string3.getBytes(); //按编码utf-8转换，编码：字符串->字节
        System.out.println(Arrays.toString(bytes));

        String string4 = "zxcvbn一二";
        byte[] bytes2;
        try {
            bytes2 = string4.getBytes("gbk");
            System.out.println(Arrays.toString(bytes2));
            String string6 = new String(bytes2);
            System.out.println(string6);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //按编码gbk转换

        String string5 = new String(bytes); //解码：字节->字符串
        System.out.println(string5);

    }

    @Test
    public void text3() {

        //字符串翻转

        //方法1：字符串转换为char数组，倒序输出数组
        String string6 = "程序新视界";
        char[] try1 = string6.toCharArray();
        for (int i = try1.length - 1; i >= 0; i--) {
            System.out.print(try1[i]);
        }
        System.out.println();

        //方法2：字符串转换为char数组，利用指针方法遍历数组交换元素位置
        String string7 = "程序新视界";
        char[] tempArray = string7.toCharArray();
        int right = tempArray.length - 1;

        for (int left = 0; left < right; left++, right--) {
            // 左右值进行交换
            char temp = tempArray[left];
            tempArray[left] = tempArray[right];
            tempArray[right] = temp;
        }

        for (char c : tempArray) {
            System.out.print(c);
        }
        System.out.println();

        //方法3：通过charAt方法获取单个字符串，倒序输出
        String string8 = "程序新视界";
        for (int i = string8.length() - 1; i >= 0; i--) {
            System.out.print(string8.charAt(i));
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串及翻转位置索引范围:");
        String string9 = scanner.next();
        char[] c = string9.toCharArray();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        change(c, n, m);

    }

    public void change(char[] c, int n, int m) {
        if (n > m) {
            int temp = n;
            n = m;
            m = temp;
        }
        for (int i = n, j = m; i < j; i++, j--) {
            char temp = c[j];
            c[j] = c[i];
            c[i] = temp;
        }
        for (char value : c) {
            System.out.print(value);
        }
    }

    @Test
    public void test4() {

        //获取字符串中某一字符/字符串出现的次数
        String srcText = "Hello World";
        String findText = "o";
        int num = appearNumber(srcText, findText);
        System.out.println(num);

    }

    @Test
    public void test5() {

        //字符串排序：调用Array.sort()
        String string10 = "acedbgzws123";
        char[] c = string10.toCharArray();
        Arrays.sort(c);
        String string = new String(c);
        System.out.println(string);

    }

}
