package a1_DesignPattern.DesignPhilosophy.a6_SyntheticReuse_Principle;

/**
 * ClassName: PetrolCar
 * Package: a1_DesignPattern.DesignPhilosophy.a6_SyntheticReuse_Principle
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 21:31
 * @Version: v1.0
 */
public class PetrolCar extends Car{

    Color color;

    @Override
    public void move(){
        System.out.println("PetrolCar");
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void get_Color() {
        color.get_Color();
    }
}
