package a2_OOP;

import java.util.Scanner;

//例：求图形周长面积
class Shape {

    private double radius = 0;
    private final double PI = Math.PI;
    private double length = 0;
    private double width = 0;
    private double side = 0;
    private int side_num = 0;

    //构造方法及重载
    public Shape() {
    }

    public Shape(double radius) {
        this.radius = radius;
    }

    public Shape(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public Shape(double side, int side_num) {
        this.side = side;
        this.side_num = side_num;
    }

    //周长
    public double perimeter() {
        double p = 0;
        if (radius != 0) {
            p = 2 * PI * radius;
        } else if (length != 0 && width != 0) {
            p = 2 * (length + width);
        } else if (side != 0 && side_num != 0) {
            p = side * side_num;
        } else {
            System.out.println("输入有误，自动结束程序!");
            System.exit(0);
        }
        return p;

    }

    //面积
    public double area() {
        double a = 0;
        if (radius != 0) {
            a = PI * radius * radius;
        } else if (length != 0 && width != 0) {
            a = length * width;
        } else if (side != 0 && side_num != 0) {
            if (side_num == 3) {
                a = Math.sqrt(3) * side * side / 4;
            } else if (side_num == 4) {
                a = side * side;
            } else {
                System.out.println("输入有误，自动结束程序!");
                System.exit(0);
            }
        } else {
            System.out.println("输入有误，自动结束程序!");
            System.exit(0);
        }
        return a;

    }

}

public class OOP5 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double radius;
        double length;
        double width;
        double side;
        int side_num;

        //label标签真的香啊...
        //String.format("%.2f", r1)保留两位小数，注意该方法会返回String型的结果！
        label: while (true) {
            System.out.print("请输入求解图形（1.圆 2.矩形 3.正三、四边形 4.退出程序）：");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    System.out.print("请输入圆的半径（radius>0）:");
                    radius = scanner.nextDouble();
                    Shape shape = new Shape(radius);
                    double r = shape.perimeter();
                    double r1 = shape.area();
                    System.out.print("该圆周长为：" + String.format("%.2f", r) + "\n该圆面积为：" + String.format("%.2f", r1) + "\n");
                    continue;
                case 2:
                    System.out.print("请输入矩形的长和宽（length>0,width>0）:");
                    length = scanner.nextDouble();
                    width = scanner.nextDouble();
                    Shape shape1 = new Shape(length, width);
                    double l = shape1.perimeter();
                    double l1 = shape1.area();
                    System.out.print("该矩形周长为：" + String.format("%.2f", l) + "\n该矩形面积为：" + String.format("%.2f", l1) + "\n");
                    continue;
                case 3:
                    System.out.print("请输入正三、四边形的边长及边数（side>0,side num>0）:");
                    side = scanner.nextDouble();
                    side_num = scanner.nextInt();
                    Shape shape2 = new Shape(side, side_num);
                    double s = shape2.perimeter();
                    double s1 = shape2.area();
                    System.out.print("该图形周长为：" + String.format("%.2f", s) + "\n该图形面积为：" + String.format("%.2f", s1) + "\n");
                    continue;
                case 4:
                    System.out.print("The system automatically exits!");
                    break label;
                default:
                    System.out.print("输入错误！\n请选择（1.重新输入 2.退出）:");
                    int chooseSecond = scanner.nextInt();
                    if (chooseSecond != 1) {
                        throw new IllegalArgumentException("The system automatically exits!");
                    }
            }
        }

    }

}
