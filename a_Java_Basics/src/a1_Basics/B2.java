package a1_Basics;

//syso / sout -> 输出语句快捷键

public class B2 {

    public static void main(String[] args) {
		
		/*
			数据类型
			  	基本数据类型：
			  		数值型：整数型：byte（-128~127）、short、
			  					   int（4字节，-2^31~2^31-1）、long(8字节，2^63-1)
			  			   浮点型：float（4字节）、double（8字节）
			  				注意：1.虽然int和float都是4字节，但float存储范围大于int，因为float分两部分，
			  			由原始数据+位数表示的，而int只是存储原始数据！
			  					 float数据表示：原始数据 * 位数
			  					 https://www.cnblogs.com/Java-Script/p/11123897.html
			  					  2.常用int、double
			  		字符型：char（1字符==2字节）
			  		布尔型：boolean
			    引用数据类型：
			    	类class、接口interface、数组[]
			 注：整数型默认int，浮点型默认double！
			 	 1 Byte字节 = 8 bit比特
			 	 数组中地址连续，int型数组前一个元素地址为b，则后一个元素地址为b+4(字节)
		 */
        long l1 = 111111L; //long后加l或L（软件中自动识别非必要）
        float f1 = 123.123f; //float后必加f或F
        char c1 = 'a'; //char定义用''，只能有一个字符（包括转义字符，例如\n换行、\tTab）
        char c2 = '\u0043'; //用Unicode表示
        System.out.print(c2); //输出C
        System.out.println("中国"); //更改设置全部选用utf-8编码，否则乱码
        boolean b1 = true;
        boolean b2 = false;
        System.out.println(b1);
        System.out.println(b2); //java会直接输出，c会输出1/0


        //自动数据类型转换：低字节->高字节
        //byte、char、short->int->long->float->double
        //byte、char、short三者相互或独立运算后结果为int型
        int i1 = 15;
        double d1 = 144;
        double d2 = i1 + d1;
        System.out.println(d2); //d2为159.0浮点型
        char c3 = 'q';
        byte by1 = 123;
        System.out.println(c3 + by1); //236 int型
        System.out.println(by1 + by1); //246 int型

        //强制数据类型转换：高字节->低字节
        //转换运算为去尾法转换
        //可能精度损失，要用强制转换符
        int i2 = 129;
        double d3 = 34.98;
        System.out.println(i2 + (int) d3); //153 int型
        System.out.println((byte) i2); //-127 byte自动循环转换
		
		
		
		/*
		String 
		  	String为引用数据类型，用""定义 
		  	String可以和基本数据类型做连接运算
		注：String+char为String型，char+char为int型
		 */
        String str1 = "你好";
        int i3 = 123;
        String str2 = str1 + i3;
        System.out.println(str2);
        //String->int
        String str3 = "555";
        int i4 = Integer.parseInt(str3);
        System.out.println(i4); //int型555，若String->int时String中包含非数字则转换失败

    }

}
