package a2_CreatorPattern.a3_PrototypePattern.ShallowCloning;

/**
 * ClassName: CitationTest
 * Package: a2_CreatorPattern.a3_PrototypePattern.ShallowCloning
 * Description:测试访问类
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 11:56
 * @Version: v1.0
 */
public class CitationTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        Citation c1 = new Citation();
        c1.setName("张三");
        //复制奖状
        Citation c2 = c1.clone();
        //将奖状的名字修改李四
        c2.setName("李四");
        c1.show();
        c2.show();

    }

}