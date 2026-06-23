package a5_Common_Class;

import org.junit.Test;

public class CC3 {
		
	/*
	String、StringBuffer、StringBuilder：
	String:不可变字符序列，底层使用char[]存储
	StringBuffer:可变字符序列，线程安全，效率低，底层使用char[]存储
	StringBuilder:可变字符序列，线程不安全，效率高，底层使用char[]存储
	 */
	
	/*
	源码分析：
		1.String
			String str=new String(); //底层代码char[] value=new char[0];
			String str1=new String("abc"); //底层代码char[] value=new char[]{'a','b','c'};
		2.StringBuffer
			StringBuffer sb1=new StringBuffer(); //底层代码char[] value=new char[16];
			sb1.append('a'); //底层代码value[0]='a';		
			StringBuffer sb2=new StringBuffer("abc");//底层代码char[] value=new char[16+"abc".length]{'a','b','c'};
			问题：
			（1）长度问题：
				System.out.println(sb1.length); //长度=0
			（2）扩容问题：
				初始长度不够时，默认为扩容2倍+2，同时复制原始元素		
	 */

    //StringBuffer的可变性
    @Test
    public void test() {

        StringBuffer stringBuffer = new StringBuffer("abcde");
        stringBuffer.setCharAt(0, 'f'); //改变指定索引的一个字符
        System.out.println(stringBuffer);

    }

    //StringBuffer常用方法
    @Test
    public void test2() {

        StringBuffer stringBuffer2 = new StringBuffer("abcdefghijklmn");
        stringBuffer2.append(1); //添加字符
        stringBuffer2.append('4');
        System.out.println(stringBuffer2.charAt(0)); //获取指定索引的一个字符
        stringBuffer2.setCharAt(0, 'f'); //改变指定索引的一个字符
        stringBuffer2.delete(0, 2); //删除范围字符（左闭右开）
        stringBuffer2.replace(10, 12, "hello!"); //将原字符串指定范围的字符串替换为其后的字符串（左闭右开）
        stringBuffer2.insert(0, "my"); //指定位置插入字符串
        System.out.println(stringBuffer2.length()); // 获取字符串长度
        stringBuffer2.reverse(); //翻转字符串
        System.out.println(stringBuffer2.indexOf("c")); //返回指定字符串在原字符串中首次出现的位置（没有返回-1）
        System.out.println(stringBuffer2.substring(1, 16)); //从指定范围（左闭右开）切割原字符串，并返回切割后的字符串
		
		/*
		常用功能：
			1.增：append(xxx)
			2.删：delete(int start,int end)
			3.改：setCharAt(int n,char ch)
			4.查：charAt(int n)
			5.插：insert(int offset,xxx)
			6.长度：length()
			7.遍历：for()+charAt()
			8.翻转：reverse()+int left/right
		 */
    }

    //对比三者的执行效率
    @Test
    public void test3() {

        String string = new String();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        long starttime;
        long endtime;

        starttime = System.currentTimeMillis();
        for (int i = 0; i < 40000; i++) {
            string = string + i;
        }
        endtime = System.currentTimeMillis();
        System.out.println("String执行时间：" + (endtime - starttime)); //5812ms

        starttime = System.currentTimeMillis();
        for (int i = 0; i < 40000; i++) {
            stringBuffer.append(i);
        }
        endtime = System.currentTimeMillis();
        System.out.println("StringBuffer执行时间：" + (endtime - starttime)); //2-3ms

        starttime = System.currentTimeMillis();
        for (int i = 0; i < 40000; i++) {
            stringBuilder.append(i);
        }
        endtime = System.currentTimeMillis();
        System.out.println("StringBuilder执行时间：" + (endtime - starttime)); //2ms

    }

}
