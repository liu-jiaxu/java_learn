package a4_BehavioralPattern.a9_VisitorPattern;

/**
 * ClassName: Owner
 * Package: a4_BehavioralPattern.a9_VisitorPattern
 * Description:具体访问角色类，客人
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 10:49
 * @Version: v1.0
 */
public class Someone implements Person{

    @Override
    public void feed(Cat cat) {
        System.out.println("客人喂食猫猫");
    }

    @Override
    public void feed(Dog dog) {
        System.out.println("客人喂食修狗");
    }

}
