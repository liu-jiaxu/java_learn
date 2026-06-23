package a6_EnumClass_Annotation;

/*
枚举类
	1.枚举类的对象只有有限个，确定的（构造器私有）
	2.需要定义一组常量时，一般使用枚举类
	3.枚举中仅有一个对象，可以作为单例模式实现方式
 */

//enum关键字定义枚举类
/*
	1.必须先创建对象，注意创建格式，多个对象直接用“,“隔开；
	2.声明私有属性及构造器
	3.编写其他方法
	4.说明：枚举类父类为Enum
	5.枚举类只可实现接口，不能继承，接口方法可以在枚举类中重写，也可以在对象中重写
 */
enum Season1 implements info {

    //提供枚举类的多个对象，多个对象直接用“,“隔开
    SPRING("春", "1122") {
        @Override
        public void show() {
            // TODO Auto-generated method stub
            System.out.println(getSeasonName());
        } //在对象中重写接口的方法，可以实现不同对象对应不同的功能，会覆盖枚举类中的重写方法
    }, //相当于public static final Season SPRING = new Season("春", "1122");
    SUMMER("夏", "3344"),
    AUTUMN("秋", "5566"),
    WINTER("冬", "7788");

    //属性用private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //私有化构造器，给属性赋值
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //获取对象属性，只有get方法
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //toString
    @Override
    public String toString() {
        return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
    }

    //实现接口后必须重写其方法
    @Override
    public void show() {
        // TODO Auto-generated method stub
        System.out.println("This season is " + getSeasonName());
    }

}

interface info {

    void show();

}

//自定义枚举类
class Season {

    //提供枚举类的多个对象
    public static final Season SPRING = new Season("春", "1122");
    public static final Season SUMMER = new Season("夏", "3344");
    public static final Season AUTUMN = new Season("秋", "5566");
    public static final Season WINTER = new Season("冬", "7788");
    //属性用private final修饰
    private final String seasonName;
    private final String seasonDesc;
    //私有化构造器，给属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //获取对象属性，只有get方法
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //重写toString方法
    @Override
    public String toString() {
        return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
    }

}

public class EA1 {

    public static void main(String[] args) {

        Season spring = Season.SPRING;
        System.out.println(spring); //toString重写方法输出
		
		/*
		枚举类常用方法：
			1.values:获取枚举类的对象数组，用于遍历所有枚举值
			2.valueOf:传递一个枚举类对象，判断其是否在枚举类中
			2.toString:用于重写使结果更易读
			3.getDeclaringClass:得到枚举常量的枚举类
			4.compareTo:比较两个枚举常量的大小
		 */

        Season1 summer = Season1.SUMMER; //enum枚举类对象
        //values
        Season1[] arr = Season1.values();
        for (Season1 i : arr) { //foreach输出更为便捷
            System.out.println(i);
        }
        //valueOf
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter); //若枚举类中有对象WINTER则返回toString，否则抛异常
        //toString
        System.out.print(summer); //重写toString方法
        System.out.println(summer.getSeasonDesc());
        //getDeclaringClass
        System.out.println(summer.getDeclaringClass());
        //compareTo
        System.out.println(summer.compareTo(winter));

        //枚举类对象调用接口方法
        Season1 spring1 = Season1.SPRING;
        spring1.show();
        summer.show();

    }

}
