package a2_CreatorPattern.a3_PrototypePattern.DeepCloning;

/**
 * ClassName: CitationTestError
 * Package: a2_CreatorPattern.a3_PrototypePattern.DeepCloning
 * Description:浅复制测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 16:13
 * @Version: v1.0
 */
public class CitationTestError {

    public static void main(String[] args) throws CloneNotSupportedException {

        Citation c1 = new Citation();
        Student stu = new Student("张三", "西安");
        c1.setStu(stu);
        //复制奖状
        Citation c2 = c1.clone();
        //获取c2奖状所属学生对象
        Student stu1 = c2.getStu();
        stu1.setName("李四");
        //判断stu对象和stu1对象是否是同一个对象
        System.out.println("stu和stu1是同一个对象？" + (stu == stu1));
        c1.show();
        c2.show();

        /*
            stu对象和stu1对象是同一个对象，就会产生将stu1对象中name属性值改为”李四“，两个Citation(奖状)对象中显示的
        都是李四。这就是浅克隆的效果，对具体原型类(Citation)中的引用类型的属性进行引用的复制。这种情况需要使用深克隆，
        而进行深克隆需要使用对象流。
         */

    }

}
