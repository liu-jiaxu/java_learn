package b3_Java8_NewFeatures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 练习
public class J8N5 {

    public static void p(String p) {
        System.out.print(p + " ");
    }

    private static void p(Student student) {
        System.out.print(student.toString() + " ");
    }

    @Test
    public void test() {

        List<String> list = new ArrayList<>();
        Collections.addAll(list,"张三,23","李四,24","王五,25");

        // 保留年龄大于23的人
        list.stream().filter(s -> Integer.parseInt(s.split(",")[1]) >= 24).collect(Collectors.toSet()).forEach(J8N5::p);
        System.out.println();

        // 转换成Student对象保存到List
        List<Student> list2 = list.stream().map(Student::new).collect(Collectors.toList());
        list2.forEach(J8N5::p);
        System.out.println();

        // 过滤出姓名打印
        String string = list.stream().map(s -> s.split(",")[0]).collect(Collectors.joining());
        System.out.println(string);

    }

}

class Student {

    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String s) {
        this.name = s.split(",")[0];
        this.age = Integer.parseInt(s.split(",")[1]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
