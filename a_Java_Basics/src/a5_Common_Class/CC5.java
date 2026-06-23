package a5_Common_Class;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

public class CC5 {

    //jdk8.0新增时间类Time
    @Test
    public void test() {

        //常用方法
        //now()获取当前日期、时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of()修改当前日期时间
        LocalDateTime localDateTime2 = LocalDateTime.of(2000, 1, 1, 12, 23); //设置当前日期，年月日时分秒毫米（不设置不显示，设置错误报错）
        System.out.println(localDateTime2);

        //getXxx()获取当前日期（年月日...，注意：第几天/月就是对应日期，不会改！）
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //withXxx()修改当前日期
        //不可变性!
        LocalDateTime localDateTime3 = localDateTime.withDayOfMonth(12);
        System.out.println(localDateTime); //当前日份8，原始值不变
        System.out.println(localDateTime3); //修改后的日份12

        //当前日期加减
        LocalDateTime localDateTime4 = localDateTime.plusMonths(3);
        System.out.println(localDateTime4); //9月份+3变为12
        localDateTime4 = localDateTime.minusYears(1);
        System.out.println(localDateTime4); //2022年份-1变为2021

    }

    @Test
    public void test2() {

        Instant instant = Instant.now(); //得到本初子午线时间点（东八区差八个小时）
        System.out.println(instant);
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        //添加偏移量（此处添加小时偏移）
        System.out.println(offsetDateTime);

        System.out.println(instant.toEpochMilli()); //long型，返回时间戳
        System.out.println(instant.ofEpochMilli(191234567890L)); //返回指定毫秒的日期

    }

    //DateTimeFormat：格式化/解析日期、时间
    @Test
    public void test3() {

        //格式化
        LocalDateTime localDateTime1 = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM); //不同格式
        String string1 = dateTimeFormatter1.format(localDateTime1);
        String string2 = dateTimeFormatter2.format(localDateTime1);
        String string3 = dateTimeFormatter3.format(localDateTime1);
        System.out.println(string1);
        System.out.println(string2);
        System.out.println(string3);

        //解析
        TemporalAccessor temporalAccessor1 = dateTimeFormatter1.parse(string1);
        System.out.println(temporalAccessor1);
        TemporalAccessor temporalAccessor2 = dateTimeFormatter2.parse(string2);
        System.out.println(temporalAccessor2);

        //自定义格式化/解析
        DateTimeFormatter dateTimeFormatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String string4 = dateTimeFormatter4.format(LocalDateTime.now());
        System.out.println(string4);
        TemporalAccessor temporalAccessor4 = dateTimeFormatter4.parse(string4);
        System.out.println(temporalAccessor4);

    }

}
