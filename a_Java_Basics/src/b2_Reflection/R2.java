package b2_Reflection;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class R2 {

	/*
	1.类的加载过程:
		程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。接着我们使用java.exe命令对某个
		字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。此过程就称为类的加载。加载到内存中的类，
		我们就称为运行时类，此运行时类，就作为Class的一个实例。
	2.换句话说， Class的实例就对应着一个运行时类。
	3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类。
	 */

    //获取Class的实例的方式
    @Test
    public void test() throws ClassNotFoundException {

        //方式1：调用运行时属性
        Class<PersonA> class2 = PersonA.class;
        System.out.println(class2);

        //方式2：通过运行时类的对象
        PersonA personA = new PersonA("a", 10);
        Class<PersonA> class3 = (Class<PersonA>) personA.getClass();
        System.out.println(class3);

        //方式3：调用Class的静态方法forName(String classPath)（常用）
        Class class4 = Class.forName("b2_Reflection.PersonA");
        System.out.println(class4);

    }

    //Class实例可以是哪些结构的说明:
    @Test
    public void test2() {

        Class<Object> c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> c10 = a.getClass();
        Class<? extends int[]> c11 = b.getClass();
        //只要元素类型与维度（例如一维！=二维）一样,就是同一个Class
        System.out.println(c10 == c11);

    }

    //通过反射创建对应运行时类的对象
    @Test
    public void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

        Class<PersonA> class5 = PersonA.class;
        PersonA personA = class5.newInstance(); //newInstance调用对应类的空参构造器创建对象(权限满足，必须有空参构造器)
        System.out.println(personA);

        //有参构造器必须先创建constructor对象
        Class<PersonA> class1 = PersonA.class;
        Constructor<PersonA> constructor = class1.getConstructor(String.class, int.class); //getConstructor()返回指定参数类型public的构造器。
        PersonA p = (PersonA) constructor.newInstance("Tom", 21);  //创建一个PersonA类的对象p
        System.out.println(p.toString());

    }

    //反射动态性（框架中写好通用结构，通过反射获取对象）
    @Test
    public void test4() throws Exception {

        for (int i = 0; i < 10; i++) {
            int num = new Random().nextInt(3); //0,1,2
            String classPath = "";
            switch (num) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "b2_Reflection.PersonA";
                    break;
            }
            System.out.println(getInstance(classPath));
        }

    }

    public Object getInstance(String classPath) throws Exception {
        Class<?> clazz = Class.forName(classPath);
        return clazz.newInstance();
    }

}
