package a4_BehavioralPattern.a8_IteratorPattern;

/**
 * ClassName: Student
 * Package: a4_BehavioralPattern.a8_IteratorPattern
 * Description:学生类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 11:03
 * @Version: v1.0
 */
public class Student {

    private String name;
    private String number;

    public Student(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

}
