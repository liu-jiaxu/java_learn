package a7_Collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

//TreeMap两种排序

class MyDate2 implements Comparable {

    private int year;
    private int month;
    private int day;

    public MyDate2(int year, int month, int day) {
        if (year >= 0 && month <= 12 && month > 0 && day <= 31 && day > 0) {
            this.year = year;
            this.month = month;
            this.day = day;
        } else {
            System.out.println("Input error!");
            System.exit(0);
        }

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate [year=" + year + ", month=" + month + ", day=" + day + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyDate2 other = (MyDate2) obj;
        return day == other.getDay() && month == other.getMonth() && year == other.getYear();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof MyDate2) {
            MyDate2 myDate = (MyDate2) o;
            int compare1 = Integer.compare(this.getYear(), myDate.getYear());
            int compare2 = Integer.compare(this.getMonth(), myDate.getMonth());
            if (compare1 != 0) {
                return compare1;
            } else if (compare2 != 0) {
                return compare2;
            } else {
                return Integer.compare(this.getDay(), myDate.getDay());
            }
        } else {
            throw new RuntimeException("Runtime Error！");
        }
    }

}

public class C7 {

    public static void main(String[] args) {

        MyDate2 myDate21 = new MyDate2(2020, 11, 22);
        MyDate2 myDate22 = new MyDate2(2022, 2, 1);
        MyDate2 myDate23 = new MyDate2(2021, 6, 26);
        MyDate2 myDate24 = new MyDate2(2018, 11, 23);
        MyDate2 myDate25 = new MyDate2(2020, 5, 5);
        TreeMap treeMap = new TreeMap();
        treeMap.put(myDate21, 12);
        treeMap.put(myDate22, 34);
        treeMap.put(myDate23, 54);
        treeMap.put(myDate24, 1);
        treeMap.put(myDate25, 7);
        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();

        TreeMap treeMap2 = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof MyDate2 && o2 instanceof MyDate2) {
                    MyDate2 myDate = (MyDate2) o1;
                    MyDate2 myDate2 = (MyDate2) o2;
                    int compare1 = Integer.compare(myDate.getYear(), myDate2.getYear());
                    int compare2 = Integer.compare(myDate.getMonth(), myDate2.getMonth());
                    if (compare1 != 0) {
                        return compare1;
                    } else if (compare2 != 0) {
                        return compare2;
                    } else {
                        return Integer.compare(myDate.getDay(), myDate2.getDay());
                    }
                } else {
                    throw new RuntimeException("Runtime Error！");
                }
            }
        });
        treeMap2.put(myDate21, 12);
        treeMap2.put(myDate22, 34);
        treeMap2.put(myDate23, 54);
        treeMap2.put(myDate24, 1);
        treeMap2.put(myDate25, 7);
        Set set2 = treeMap2.entrySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

    }

}
