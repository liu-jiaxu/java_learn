package a4_BehavioralPattern.a8_IteratorPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a8_IteratorPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 11:15
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        StudentAggregateImpl studentAggregate = new StudentAggregateImpl();
        // 添加元素
        studentAggregate.addStudent(new Student("lisi","112"));
        studentAggregate.addStudent(new Student("wangwu","113"));

        // 遍历聚合对象
        // 1.获取迭代器对象
        StudentIterator studentIterator = studentAggregate.getStudentIterator();
        // 2.遍历
        while (studentIterator.hasNext()) {
            // 3.获取元素
            Student student = studentIterator.next();
            System.out.println(student);
        }

    }

}
