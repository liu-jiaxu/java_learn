package a1_DesignPattern.DesignPhilosophy.a1_OpenClose_Principle;

/**
 * ClassName: SougouInput
 * Package: a1_DesignPattern.DesignPhilosophy.a1_OpenClose_Principle
 * Description:传入皮肤类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 10:30
 * @Version: v1.0
 */
public class SougouInput {

    private AbstractSkin skin;

    // 接收一个抽象类对象，将skin赋予具体对象的值
    public void setSkin(AbstractSkin skin){
        this.skin = skin;
    }

    // 使具体对象skin调用对应的display方法
    public void display(){
        skin.display();
    }

}
