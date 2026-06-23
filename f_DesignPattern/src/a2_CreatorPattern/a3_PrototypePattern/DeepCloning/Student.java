package a2_CreatorPattern.a3_PrototypePattern.DeepCloning;

import java.io.Serializable;

/**
 * ClassName: Student
 * Package: a2_CreatorPattern.a3_PrototypePattern.DeepCloning
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 16:12
 * @Version: v1.0
 */
public class Student implements Serializable {

    private String name;
    private String address;

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Student() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
