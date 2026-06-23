package a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.after;

/**
 * ClassName: Rectangle
 * Package: a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.after
 * Description:长方形类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 11:39
 * @Version: v1.0
 */
public class Rectangle implements Quadrilateral{

    private double length;
    private double width;

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public double getWidth() {
        return width;
    }

}
