package a2_OOP;

import org.junit.Test;

/*
单元测试方法
	1.右键工程Build Path -> Add Library -> JUnit
	2.创建java类（public、无参构造器、无main）
	3.声明单元测试方法（public、无返回值、无形参）
	4.单元测试方法上要声明注解：@Test，并在单元测试类中导入：import org.junit.Test;
	5.写完代码后双击单元测试名，右键run as -> JUnit Test
	6.结果无异常：绿条；异常：红条
	7.可以一次测试多块代码
 */

public class OOP9 {

    //@Test
    public void test_name() {
        int count = 0;
        System.out.print("水仙花数：");
        for (int i = 100; i < 1000; i++) {
            int bai = i / 100, shi = i / 10 % 10, ge = i % 10;
            if (i == (bai * bai * bai + shi * shi * shi + ge * ge * ge)) {
                count++;
                System.out.print(i + " ");
            }
        }
        System.out.println();
        System.out.println("共有水仙花数：" + count + "个");
    }
	
	/*
	包装类：基本数据类型对应的引用数据类型
		Number父类
			byte 	 Byte
			short  	 Short
			int		 Integer
			long	 Long
			float    Float
			double   Double
		非Number父类	
			boolean  Boolean
			char	 Character
		
	 */

    @Test
    public void test_name2() {
        int num = 10;
        Integer integer1 = new Integer(num); //基本数据类型->引用数据类型
        System.out.println(integer1.toString());

        Boolean boolean1 = new Boolean("abc"); //Boolean除了true和false还接受String类型的数据
        System.out.println(boolean1); //String型输出false
		
		/*
		包装类转换为基本数据类型
			int i1=integer.intValue();
			double d1=double1.doubleValue();调用xxxValue()方法
			便于进行计算（引用数据类型无法运算）
		 */
        Integer integer2 = new Integer(20);
        int i1 = integer2.intValue();
        System.out.println(i1 + 10);

        //自动装箱、自动拆箱（JDK5.0）
        Integer age = 25;
        int ageInt = age; //会自动转换
        int id = 21;
        Integer idInt = id;
        int num2 = 20; //会自动转换
        method(num2); //自动装箱：函数形参为引用数据类型，形参会自动将基本数据类型装换为对应的包装类

    }

    public void method(Object obj) {
        System.out.println(obj); //自动拆箱：本来引用数据类型输出的是地址，但是系统会自动将包装类装换为基本数据类型
    }

    @Test
    public void test_name3() {

        //基本数据类型、包装类、String三者相互转换

        //基本数据类型 -> String
        int i2 = 100;
        double d1 = 23.333;
        //1.连接运算
        String str1 = "" + i2;
        System.out.println(str1);
        //2.调用String的valueOf
        String str2 = String.valueOf(d1);
        System.out.println(str2 instanceof String);

        //String -> 基本数据类型
        String str3 = "123345";
        int i3 = Integer.parseInt(str3);
        String str4 = "123.45";
        double i4 = Double.parseDouble(str4);
        System.out.println(i3); //不是数字会报错
        System.out.println(i4);

        String str5 = "true";
        boolean b1 = Boolean.parseBoolean(str5);
        System.out.println(b1); //true
        String str6 = "true123";
        boolean b2 = Boolean.parseBoolean(str6);
        System.out.println(b2); //false

    }

}
