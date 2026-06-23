package a1_DesignPattern.DesignPhilosophy.a4_InterfaceIsolation_Principle;

/**
 * ClassName: HeiMaSafetyDoor
 * Package: a1_DesignPattern.DesignPhilosophy.a4_InterfaceIsolation_Principle
 * Description:黑马安全门
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 20:46
 * @Version: v1.0
 */

// 改进：使用接口隔离原则，这样可以避免被迫实现其不使用的方法
public class HeiMaSafetyDoor implements AntiTheft,Fireproof,Waterproof {

    public void antiTheft() {
        System.out.println("防盗");
    }
    public void fireproof() {
        System.out.println("防火");
    }
    public void waterproof() {
        System.out.println("防水");
    }

}
