package a4_BehavioralPattern.a8_IteratorPattern;

/**
 * ClassName: StudentAggregate
 * Package: a4_BehavioralPattern.a8_IteratorPattern
 * Description:抽象聚合角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 11:11
 * @Version: v1.0
 */
public interface StudentAggregate {

    // 添加学生功能
    void addStudent(Student student);

    // 删除学生功能
    void removeStudent(Student student);

    // 获取迭代器对象功能
    StudentIterator getStudentIterator();

}
