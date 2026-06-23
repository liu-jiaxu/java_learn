package a1_Basics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liu
 * @Create: 2023/11/14 - 15:11
 * @Version: v1.0
 */

public class Test {

    static class MyDate {

        private String year;
        private String month;
        private String day;

        private BigDecimal price;

        public MyDate(String year, String month, String day, BigDecimal price) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.price = price;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "MyDate{" +
                    "year='" + year + '\'' +
                    ", month='" + month + '\'' +
                    ", day='" + day + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
    public static void main(String[] args) {
        // System.out.println("\033[%dm%s\033[0m%n", 31, "start");

        List<MyDate> list = new ArrayList<>();
        list.add(new MyDate("2021", "12", "16", new BigDecimal("123.45")));
        list.add(new MyDate("2020", "4", "6", new BigDecimal("325.1")));
        list.add(new MyDate("2022", "2", "1", new BigDecimal("2.1")));
        list.add(new MyDate("2020", "4", "6", new BigDecimal("125.1")));
        list.add(new MyDate("2021", "5", "1", new BigDecimal("75.441")));
        list.add(new MyDate("2020", "9", "2", new BigDecimal("6.1")));
        list.add(new MyDate("2022", "1", "14", new BigDecimal("5")));

//        list.sort((o1, o2) -> {
//            if (o1 != null && o2 != null) {
//                int compareYear = o1.getYear().compareTo(o2.getYear());
//                if (compareYear == 0) {
//                    int compareMonth = o1.getMonth().compareTo(o2.getMonth());
//                    if (compareMonth == 0) {
//                        return o1.getDay().compareTo(o2.getDay());
//                    } else {
//                        return compareMonth;
//                    }
//                } else {
//                    return compareYear;
//                }
//            } else {
//                throw new RuntimeException("Error!");
//            }
//        });

        list.sort((o1, o2) -> {
            if (o1 != null && o2 != null) {
                return o1.getPrice().compareTo(o2.getPrice());
            } else {
                throw new RuntimeException("Error!");
            }
        });

        list.forEach(System.out::println);

    }

}
