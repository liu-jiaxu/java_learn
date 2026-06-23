package a4_BehavioralPattern.a8_IteratorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: StudentAggregateImpl
 * Package: a4_BehavioralPattern.a8_IteratorPattern
 * Description:具体聚合角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 11:12
 * @Version: v1.0
 */
public class StudentAggregateImpl implements StudentAggregate{

    // 创建聚合
    private List<Student> list = new ArrayList<Student>();

    @Override
    public void addStudent(Student student) {
        list.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        list.remove(student);
    }

    // 获取迭代器对象
    @Override
    public StudentIterator getStudentIterator() {
       return new StudentIteratorImpl(list);
    }
}
