package a5_Common_Class;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/*
jdk8.0之前的日期时间的API测试
	1.System.currentTimeMillis()
	2.java.util.Date和java.sql.Date
	3.SimpleDateFormat
	4.Calendar
 */

/*
星期日对应1，星期一对应2...
一月对应0...
 */

public class CC4 {

    //Date类的使用
    //获取时间和时间戳
    @Test
    public void test() {

        Date date1 = new Date();
        System.out.println(date1); //得到当前时间
        System.out.println(date1.getTime()); //得到时间戳

        //时间戳：1970.1.1 0:0:0 -- now 的时间（ms）
        long time = System.currentTimeMillis();
        System.out.println(time); //得到时间戳

        Date date2 = new Date(1659688873481L);
        System.out.println(date2); //转换为java的时间格式

        java.sql.Date date3 = new java.sql.Date(1151213963481L);
        System.out.println(date3); //转换为数据库的时间格式

    }

    //SimpleDateFormat类的使用：对日期Date格式化、并解析
    @Test
    public void test2() {

        //格式化：日期->字符串
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //更改字符串日期显示格式，常用！
        Date date = new Date();
        String string = simpleDateFormat.format(date);
        System.out.println(string);

        //解析：字符串->日期
        // String string2 = "2022-8-5 下午5:03";
        String string2 = "2022-8-5 17:45:03";
        Date date2;
        try {
			//设置时区
			TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
            date2 = simpleDateFormat.parse(string2);
            System.out.println(date2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //抽象类Calendar的使用
    @Test
    public void test3() {

        //实例化
        //方式1：创建子类GregorianCalendar的对象
        //方式2：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();

        //常用方法
        //get()获取日期
        int day = calendar.get(Calendar.DAY_OF_MONTH); //获取现在是本月的第几天
        int month = calendar.get(Calendar.MONTH); //获取月份，注意从0开始！
        System.out.println(day);
        System.out.println(month);

        //set()修改指定日期（原始数据不变）
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        int day_change = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day_change);

        //add()增加/减少指定日期
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        //getTime()输出所有修改后的日期
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime()修改当前日期
        Date date2 = new Date();
        calendar.setTime(date2);
        System.out.println(date2);

    }

    @Test
    public void test4() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入年月日:");
        String string = scanner.next();
        Date date = simpleDateFormat.parse(string);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

    }

}
