package a2_OOP;

//成员内部类
class Creature {

    String name = "abc";

    //静态内部类
    static class Dog {

        public static String name = "Smitch";

        public void eat() {
            System.out.println("meat");
        }

        //静态类只能获取本类中的属性，对于非static类的属性无法获取！
        public void display() {
            System.out.println(name);
        }

    }

    //非静态内部类
    class Bird {
        String name = "abr12345";

        public void sing() {
            System.out.println("lalala");
        }

        //注意不同变量的获取
        public void display() {
            System.out.println(name);
            System.out.println(Creature.this.name);
            System.out.println(Dog.name);
        }

    }

}

public class OOP13 {

    public static void main(String[] args) {

        //内部类实例化
        //静态内部类,直接创建内部类对象即可
        Creature.Dog dog = new Creature.Dog();
        System.out.println(dog.name);

        //非静态内部类，要用外部类的对象实例化
        Creature creature = new Creature();
        Creature.Bird bird = creature.new Bird();
        bird.sing();
        bird.display();

    }

}
