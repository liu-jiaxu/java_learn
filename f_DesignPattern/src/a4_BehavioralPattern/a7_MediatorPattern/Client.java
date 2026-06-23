package a4_BehavioralPattern.a7_MediatorPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a7_MediatorPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 10:49
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建中介者对象
        MediatorStructure mediatorStructure = new MediatorStructure();
        // 创建租房者对象
        Tenant tenant = new Tenant("李四",mediatorStructure);
        // 创建房主对象
        HouseOwner houseOwner = new HouseOwner("张三",mediatorStructure);

        // 中介者要知道具体的房主和租房者
        mediatorStructure.setTenant(tenant);
        mediatorStructure.setHouseOwner(houseOwner);

        tenant.constact("我要三室的租房");
        houseOwner.constact("ok");

    }

}
