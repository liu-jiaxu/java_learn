package a2_CreatorPattern.a4_BuilderPattern;

/**
 * ClassName: Bulider
 * Package: a2_CreatorPattern.a4_BuilderPattern
 * Description:抽象builder类
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 10:34
 * @Version: v1.0
 */
public abstract class Bulider {

    protected Bike mBike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    public abstract Bike createBike();

}
