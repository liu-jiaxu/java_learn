package a1_DesignPattern.DesignPhilosophy.a1_OpenClose_Principle;

/**
 * ClassName: Client_Eager_Singleton
 * Package: a1_DesignPattern.DesignPhilosophy.a1_OpenClose_Principle
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 10:32
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 1.创建搜狗输入法对象
        SougouInput sougouInput = new SougouInput();
        // 2.创建皮肤对象
        DefaultSkin defaultSkin = new DefaultSkin();
            // HeimaSkin heimaSkin = new HeimaSkin();
                // 需要切换不同的皮肤时，仅需新建一个类，继承抽象类display方法并重写，并在此创建对象并调用即可，无需修改源代码了！
        // 3.将皮肤设置到输入法中
        sougouInput.setSkin(defaultSkin);
            // sougouInput.setSkin(heimaSkin);
        // 4.显示皮肤
        sougouInput.display();

    }

}
