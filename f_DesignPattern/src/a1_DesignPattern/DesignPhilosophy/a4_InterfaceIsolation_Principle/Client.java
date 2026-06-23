package a1_DesignPattern.DesignPhilosophy.a4_InterfaceIsolation_Principle;

/**
 * ClassName: Client_Eager_Singleton
 * Package: a1_DesignPattern.DesignPhilosophy.a4_InterfaceIsolation_Principle
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 20:49
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 黑马安全门
        HeiMaSafetyDoor heiMaSafetyDoor = new HeiMaSafetyDoor();
        System.out.println("黑马安全门");
        heiMaSafetyDoor.antiTheft();
        heiMaSafetyDoor.fireproof();
        heiMaSafetyDoor.waterproof();

        // 传智安全门
        ItcastSafetyDoor itcastSafetyDoor = new ItcastSafetyDoor();
        System.out.println("传智安全门");
        itcastSafetyDoor.antiTheft();
        itcastSafetyDoor.fireproof();

    }

}
