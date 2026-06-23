package a2_CreatorPattern.a3_PrototypePattern.DeepCloning;

import java.io.Serializable;

/**
 * ClassName: Citation
 * Package: a2_CreatorPattern.a3_PrototypePattern.DeepCloning
 * Description:奖状类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 16:11
 * @Version: v1.0
 */
public class Citation implements Cloneable,Serializable {

    private Student stu;

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    void show() {
        System.out.println(stu.getName() + "同学：在2020学年第一学期中表现优秀，被评为三好学生。特发此状！");
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }

}