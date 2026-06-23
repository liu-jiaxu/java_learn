package a4_BehavioralPattern.a7_MediatorPattern;

/**
 * ClassName: MeddiatorStructure
 * Package: a4_BehavioralPattern.a7_MediatorPattern
 * Description:具体中介者角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/25 - 10:45
 * @Version: v1.0
 */
public class MediatorStructure extends Mediator {

    // 聚合房主和具体租房者对象
    private HouseOwner houseOwner;
    private Tenant tenant;

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void constact(String message, Person person){
        if(person == houseOwner){
            tenant.getMessage(message);
        } else {
            houseOwner.getMessage(message);
        }
    }

}
