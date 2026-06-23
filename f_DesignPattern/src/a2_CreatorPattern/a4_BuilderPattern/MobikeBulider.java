package a2_CreatorPattern.a4_BuilderPattern;

/**
 * ClassName: MobikeBulider
 * Package: a2_CreatorPattern.a4_BuilderPattern
 * Description:摩拜单车类
 *
 * @Author: zgh296
 * @Create: 2023/4/18 - 10:37
 * @Version: v1.0
 */
public class MobikeBulider extends Bulider {

    @Override
    public void buildFrame() {
        mBike.setFrame("铝合金车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("真皮车座");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }

}
