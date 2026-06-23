package a2_CreatorPattern.a4_BuilderPattern.Expand;

/**
 * ClassName: Client
 * Package: a2_CreatorPattern.a4_BuilderPattern.Expand
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 11:06
 * @Version: v1.0
 */

public class Client {

    public static void main(String[] args) {

        // 链式编程，通过构建者对象获取手机对象
        Phone phone = new Phone.Builder()
                .cpu("intel")
                .mainboard("华硕")
                .memory("金士顿")
                .screen("三星")
                .build();
        System.out.println(phone);
            // 重构后的代码在使用起来更方便，某种程度上也可以提高开发效率。从软件设计上，对程序员的要求比较高。

    }

}
