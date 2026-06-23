package a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.after;

/**
 * ClassName: Test
 * Package: a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.after
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 11:40
 * @Version: v1.0
 */
public class Test {

    public static void main(String[] args) {

        // 创建长方形对象
        Rectangle rectangle = new Rectangle();
        // 设置长宽
        rectangle.setLength(20);
        rectangle.setWidth(50);
        // 增长
        resize(rectangle);
        // 打印
        printlw(rectangle);

        // 创建正方形对象
        Square square = new Square();
        // 设置长宽
        square.setSide(20);
        // 增长
        // resize(square); // 此时仅允许传入长方形类参数
        // 打印
        printlw(square);

    }

    //  增长方法
    public static void resize(Rectangle rectangle){
        while(rectangle.getWidth() >= rectangle.getLength()) {
            rectangle.setLength(rectangle.getLength()+1);
        }
    }

    // 打印长宽方法
    public static void printlw(Quadrilateral quadrilateral){
        System.out.println("长："+quadrilateral.getLength()+" 宽："+quadrilateral.getWidth());
    }

}
