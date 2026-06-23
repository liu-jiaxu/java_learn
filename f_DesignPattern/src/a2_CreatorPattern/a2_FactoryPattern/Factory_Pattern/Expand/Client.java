package a2_CreatorPattern.a2_FactoryPattern.Factory_Pattern.Expand;

/**
 * ClassName: Client
 * Package: a2_CreatorModel.a2_FactoryPattern.Factory_Pattern.Expand
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 10:21
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        Coffee coffee = CoffeeFactory.createCoffee("american");
        System.out.println(coffee.getName());

    }

}
