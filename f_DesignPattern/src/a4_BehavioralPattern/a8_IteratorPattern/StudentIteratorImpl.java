package a4_BehavioralPattern.a8_IteratorPattern;

import java.util.List;

/**
 * ClassName: StudentIteratorImpl
 * Package: a4_BehavioralPattern.a8_IteratorPattern
 * Description:具体迭代器角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 11:08
 * @Version: v1.0
 */
public class StudentIteratorImpl implements StudentIterator{

    // 声明集合
    private List<Student> list;
    private int position = 0; // 用于记录遍历时的位置

    public StudentIteratorImpl(List<Student> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return position < list.size();
    }

    @Override
    public Student next() {
        // 从集合中获取指定位置的元素
        Student student = list.get(position);
        position++;
        return student;
    }

}
