package a4_BehavioralPattern.a8_IteratorPattern;

/**
 * ClassName: StudentIterator
 * Package: a4_BehavioralPattern.a8_IteratorPattern
 * Description:抽象迭代器角色接口
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 11:04
 * @Version: v1.0
 */
public interface StudentIterator {

    // 判断是否还有元素
    boolean hasNext();

    // 获取下一个元素
    Student next();

}
