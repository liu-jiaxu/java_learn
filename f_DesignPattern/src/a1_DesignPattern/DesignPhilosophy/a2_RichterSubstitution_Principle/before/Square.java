package a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.before;

/**
 * ClassName: Square
 * Package: a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.after
 * Description:正方形类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 11:19
 * @Version: v1.0
 */
public class Square extends Rectangle{

    @Override
    public void setLength(double length) {
        super.setLength(length);
        super.setWidth(length);
    }

    @Override
    public void setWidth(double width) {
        super.setLength(width);
        super.setWidth(width);
    }

}
