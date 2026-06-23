package a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle;

/**
 * ClassName: TestComputer
 * Package: a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 20:13
 * @Version: v1.0
 */
public class TestComputer {

    public static void main(String[] args) {

        // 计算机对象
        Computer computer = new Computer();
        // 计算机组装
        computer.setHardDisk(new XiJieHardDisk());
        computer.setCpu(new IntelCpu());
        computer.setMemory(new KingstonMemory());
        // 计算机运行
        computer.run();

        /*
            上面代码可以看到已经组装了一台电脑，但是似乎组装的电脑的cpu只能是Intel的，内存条只能是金士顿的，硬盘只能是希捷的，
        这对用户肯定是不友好的，用户有了机箱肯定是想按照自己的喜好，选择自己喜欢的配件。
            修改：为各个硬件类创建接口，各个具体类实现接口，而computer类则创建接口对象即可。
         */

        // 修改后
        // 创建所选的计算机组件对象
        HardDisk hardDisk = new XiJieHardDisk();
        Cpu cpu = new IntelCpu();
        Memory memory = new KingstonMemory();
        // 创建计算机对象
        Computer computer2 = new Computer();
        // 组装计算机
        computer2.setCpu(cpu);
        computer2.setHardDisk(hardDisk);
        computer2.setMemory(memory);
        // 运行计算机
        computer2.run();

        // 此时若电脑需要更换硬件，只需创建具体类实现对应硬件接口，然后在此创建对象设置即可，无需修改computer类了

    }

}
