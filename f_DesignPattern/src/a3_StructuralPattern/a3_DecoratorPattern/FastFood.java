package a3_StructuralPattern.a3_DecoratorPattern;

/**
 * ClassName: FastFood
 * Package: a3_StructuralPattern.a3_DecoratorPattern
 * Description:快餐类，抽象构件角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 18:05
 * @Version: v1.0
 */
public abstract class FastFood {

    private float price;
    private String desc;

    public FastFood() {
    }
    public FastFood(float price, String desc) {
        this.price = price;
        this.desc = desc;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return price;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public abstract float cost(); //获取价格

}
