package a2_CreatorPattern.a3_PrototypePattern.ShallowCloning;

/**
 * ClassName: Citation
 * Package: a2_CreatorPattern.a3_PrototypePattern.ShallowCloning
 * Description:奖状类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 11:55
 * @Version: v1.0
 */
public class Citation implements Cloneable {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return (this.name);
    }

    public void show() {
        System.out.println(name + "同学：在2020学年第一学期中表现优秀，被评为三好学生。特发此状！");
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone(); // clone返回类型为父类的object，需要强转
    }

}
