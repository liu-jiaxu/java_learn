package a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.after;

/**
 * ClassName: Square
 * Package: a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.after
 * Description:正方形类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 11:38
 * @Version: v1.0
 */
public class Square implements Quadrilateral{

    private double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getLength() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }

}
