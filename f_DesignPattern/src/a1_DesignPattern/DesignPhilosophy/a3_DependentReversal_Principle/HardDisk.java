package a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle;

/**
 * ClassName: HardDisk
 * Package: a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle
 * Description:硬盘接口
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 20:24
 * @Version: v1.0
 */
public interface HardDisk {

    public void save (String data);

    public String get();

}
