package a1_DesignPattern.DesignPhilosophy.a6_SyntheticReuse_Principle;

/**
 * ClassName: Client_Eager_Singleton
 * Package: a1_DesignPattern.DesignPhilosophy.a6_SyntheticReuse_Principle
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 21:35
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        ElectricCar electricCar = new ElectricCar();
        Color color = new Red();
        electricCar.setColor(color);
        System.out.print("颜色：");
        electricCar.get_Color();
        System.out.print("能源：");
        electricCar.move();

    }

}
