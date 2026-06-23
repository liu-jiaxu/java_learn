package a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle;

/**
 * ClassName: Computer
 * Package: a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle
 * Description:电脑类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 20:12
 * @Version: v1.0
 */
public class Computer {

    // 声明接口对象
    private HardDisk hardDisk;
    private Cpu cpu;
    private Memory memory;

    public Cpu getCpu() {
        return cpu;
    }
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }
    public Memory getMemory() {
        return memory;
    }
    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }
    public void setHardDisk(HardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }
    public void run() {
        System.out.println("计算机工作");
        cpu.run();
        memory.save();
        String data = hardDisk.get();
        System.out.println("从硬盘中获取的数据为：" + data);
    }

}
