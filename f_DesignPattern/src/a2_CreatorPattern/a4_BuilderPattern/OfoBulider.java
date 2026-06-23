package a2_CreatorPattern.a4_BuilderPattern;

/**
 * ClassName: OfoBulider
 * Package: a2_CreatorPattern.a4_BuilderPattern
 * Description:ofo单车类
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 10:39
 * @Version: v1.0
 */
public class OfoBulider extends Bulider {

    @Override
    public void buildFrame() {
        mBike.setFrame("碳纤维车架");
    }
    @Override
    public void buildSeat() {
        mBike.setSeat("橡胶车座");
    }
    @Override
    public Bike createBike() {
        return mBike;
    }

}
