package a1_Basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @ClassName: AA
 * @Package: a1_Basics
 * @Description:
 * @Author: zgh296
 * @Create: 2023/10/9 - 8:48
 * @Version: v1.0
 */

public class AA {

    //例：输入年、月、日，得到这是一年中的第几天

    public static void main(String[] args) throws ParseException {

        /*Scanner scanner = new Scanner(System.in);

        System.out.println("输入年份");
        int year = scanner.nextInt();
        System.out.println("输入月份");
        int month = scanner.nextInt();
        System.out.println("输入日期");
        int day = scanner.nextInt();
        int sum = 0;

        if (year > 0 && month > 0 && month < 13 && day > 0 && day < 32) {

            for (int i = 1; i < month; i++) {

                if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10) {
                    sum += 31;
                } else if (i == 4 || i == 6 || i == 9 || i == 11) {
                    sum += 30;
                } else {
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                        sum += 29;
                    } else {
                        sum += 28;
                    }
                }

            }

            sum += day;

        }

        System.out.println(sum);*/

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年月日：");
        String d = sc.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(d);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        System.out.println(c.get(Calendar.DAY_OF_YEAR));

    }

}
