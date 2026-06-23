package a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle;

/**
 * ClassName: KingstonMemory
 * Package: a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle
 * Description:具体内存条类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 20:11
 * @Version: v1.0
 */
public class KingstonMemory implements Memory {

    public void save() {
        System.out.println("使用金士顿作为内存条");
    }

}
