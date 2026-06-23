package main.a1_Basic_DataStructure.a4_queue_stack_heap.stack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @ClassName: aaa
 * @Package: main.a1_Basic_DataStructure.a4_queue_stack_heap.stack
 * @Description:
 * @Author: zgh296
 * @Create: 2023/10/12 - 8:46
 * @Version: v1.0
 */

public class aaa {
    public static void main(String[] args) throws ParseException {
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