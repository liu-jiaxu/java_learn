package a7_Collection;

import java.util.*;

//例：按生日日期先后排序MyDate对象
//自己写的，成就感+100%
class MyDate {

    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
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

//    @Override
//    public int hashCode() {
//        return Objects.hash(day, month, year);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        MyDate other = (MyDate) obj;
//        return day == other.day && month == other.month && year == other.year;
//    }

}

public class C5 {

    public static void main(String[] args) {

		// 普通写法
		/*Comparator<MyDate> com = new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if (o1 instanceof MyDate && o2 instanceof  MyDate) {
					MyDate myDate1 = (MyDate) o1;
					MyDate myDate2 = (MyDate) o2;
					int compareYear = Integer.compare(myDate1.getYear(), myDate2.getYear());
					if (compareYear == 0) {
						int compareMonth = Integer.compare(myDate1.getMonth(), myDate2.getMonth());
						if (compareMonth == 0) {
							return Integer.compare(myDate1.getDay(), myDate2.getDay());
						} else {
							return compareMonth;
						}
					} else {
						return compareYear;
					}
				} else {
					throw new RuntimeException("Error!");
				}
			}
		};*/

		// lambda表达式
		Comparator com2 = (o1, o2) -> {
			if (o1 instanceof MyDate && o2 instanceof  MyDate) {
				MyDate myDate1 = (MyDate) o1;
				MyDate myDate2 = (MyDate) o2;
				int compareYear = Integer.compare(myDate1.getYear(), myDate2.getYear());
				if (compareYear == 0) {
					int compareMonth = Integer.compare(myDate1.getMonth(), myDate2.getMonth());
					if (compareMonth == 0) {
						return Integer.compare(myDate1.getDay(), myDate2.getDay());
					} else {
						return compareMonth;
					}
				} else {
					return compareYear;
				}
			} else {
				throw new RuntimeException("Error!");
			}
		};
        List<MyDate> list = new ArrayList<MyDate>();
        list.add(new MyDate(2021, 12, 16));
        list.add(new MyDate(2020, 4, 6));
        list.add(new MyDate(2022, 2, 1));
        list.add(new MyDate(2021, 12, 16));
        list.add(new MyDate(2022, 5, 7));
        list.add(new MyDate(2021, 9, 22));
        list.add(new MyDate(2021, 9, 23));
        list.add(new MyDate(2021, 9, 14));
        list.sort(com2);
        list.forEach(System.out::println);

		// treeSet使用com2比较器排序
//        TreeSet treeSet = new TreeSet(com2);
//        treeSet.add(new MyDate(2021, 12, 16));
//        treeSet.add(new MyDate(2020, 4, 6));
//        treeSet.add(new MyDate(2022, 2, 1));
//        treeSet.add(new MyDate(2021, 12, 16));
//        treeSet.add(new MyDate(2022, 5, 7));
//        treeSet.add(new MyDate(2021, 9, 22));
//		treeSet.add(new MyDate(2021, 9, 23));
//		treeSet.add(new MyDate(2021, 9, 14));
//        //treeSet.add(new MyDate(2021, 19, 22));
//        for (Object item : treeSet) {
//            System.out.println(item);
//        }
    }

}
