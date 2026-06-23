package a2_CreatorPattern.a2_FactoryPattern.SimpleFactory_Pattern;

import java.util.Scanner;

/**
 * ClassName: Client
 * Package: a2_CreatorModel.a2_FactoryPattern.SimpleFactory_Pattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/12 - 11:00
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        System.out.println("请选择：latte / american");
        Scanner scanner = new Scanner(System.in);
        CoffeeStore store = new CoffeeStore();
        Coffee coffee = store.orderCoffee(scanner.next());
        System.out.println(coffee.getName());

    }

}
