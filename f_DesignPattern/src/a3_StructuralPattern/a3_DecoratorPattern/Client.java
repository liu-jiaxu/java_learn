package a3_StructuralPattern.a3_DecoratorPattern;

/**
 * ClassName: Client
 * Package: a3_StructuralPattern.a3_DecoratorPattern
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 18:36
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 点炒饭
        FastFood friedRice = new FriedRice();
        System.out.println(friedRice.getDesc()+" "+friedRice.cost());

        // 加俩鸡蛋和一个培根
        friedRice = new Egg(friedRice);
        System.out.println(friedRice.getDesc()+" "+friedRice.cost());
        friedRice = new Egg(friedRice);
        System.out.println(friedRice.getDesc()+" "+friedRice.cost());
        friedRice = new Bacon(friedRice);
        System.out.println(friedRice.getDesc()+" "+friedRice.cost());

        // 之后还需火腿肠，则直接定义火腿肠类Ham继承Garnish类即可

    }

}
