package a2_CreatorPattern.a4_BuilderPattern;

/**
 * ClassName: Director
 * Package: a2_CreatorPattern.a4_BuilderPattern
 * Description:指挥者类
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 10:40
 * @Version: v1.0
 */
public class Director {

    private Bulider mBulider;

    public Director(Bulider bulider) {
        mBulider = bulider;
    }

    public Bike construct() {
        mBulider.buildFrame();
        mBulider.buildSeat();
        return mBulider.createBike();
    }

    /*
        指挥者类Director在建造者模式中具有很重要的作用，它用于指导具体构建者如何构建产品，控制调用先后次序，
    并向调用者返回完整的产品类。
     */

}
