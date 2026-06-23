package a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.before;

/**
 * ClassName: RectangleDemo
 * Package: a1_DesignPattern.DesignPhilosophy.a2_RichterSubstitution_Principle.before
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 11:22
 * @Version: v1.0
 */
public class RectangleDemo {

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
        square.setLength(20);
        // 增长
        resize(square); // 会报错，因为长宽相同会一直增加
        // 打印
        printlw(square);

        /*
            我们运行一下这段代码就会发现，假如我们把一个普通长方形作为参数传入resize方法，就会看到长方形宽度逐渐增长的效果，当宽度
        大于长度,代码就会停止，这种行为的结果符合我们的预期；假如我们再把一个正方形作为参数传入resize方法后，就会看到正方形的宽度和
        长度都在不断增长，代码会一直运行下去，直至系统产生溢出错误。所以，普通的长方形是适合这段代码的，正方形不适合。 我们得出结论：
        在resize方法中，Rectangle类型的参数是不能被Square类型的参数所代替，如果进行了替换就得不到预期结果。因此，Square类和
        Rectangle类之间的继承关系违反了里氏代换原则，它们之间的继承关系不成立，正方形不是长方形。
         */

    }

    //  增长方法
    public static void resize(Rectangle rectangle){
        while(rectangle.getWidth() >= rectangle.getLength()) {
            rectangle.setLength(rectangle.getLength()+1);
        }
    }

    // 打印长宽方法
    public static void printlw(Rectangle rectangle){
        System.out.println("长："+rectangle.getLength()+" 宽："+rectangle.getWidth());
    }

}
