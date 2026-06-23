package test;

import org.junit.jupiter.api.Test;

/**
 * ClassName: Teacher
 * Package: a1_Basics.test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/7 - 14:09
 * @Version: v1.0
 */

public class Teacher {

    // name age content

    private String name;
    private int age;
    private String content;

    public Teacher() {
    }

    public Teacher(String name, int age, String content) {
        this.name = name;
        this.age = age;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // eat teach 谁讲什么内容
    public void eat() {
        System.out.println("吃饭");
    }

    public void teach() {
        System.out.println(name + "讲" + content);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", content='" + content + '\'' +
                '}';
    }

}

class TestTeacher {

    @Test
    public void test() {

        new Teacher("李四", 23, "java").teach();

    }

}