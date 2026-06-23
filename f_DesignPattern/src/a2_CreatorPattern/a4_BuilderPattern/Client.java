package a2_CreatorPattern.a4_BuilderPattern;

/**
 * ClassName: Client
 * Package: a2_CreatorPattern.a4_BuilderPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 10:41
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建指挥者对象
        Director director = new Director(new MobikeBulider());
        // 指挥者组装自行车
        Bike bike = director.construct();

        System.out.println(bike.getFrame());
        System.out.println(bike.getSeat());

    }

}
