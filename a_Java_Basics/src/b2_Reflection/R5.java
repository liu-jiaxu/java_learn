package b2_Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理

interface Human {

    String getBelief();

    void eat(String food);

}

//被代理类
class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("I like eat" + food);
    }

}

//代理类
class ProxyFactory {

    public static Object getProxyInstance(Object obj) {
        MyInvocationHander myInvocationHander = new MyInvocationHander();
        myInvocationHander.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myInvocationHander);
			/*
			public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)throws IllegalArgumentException
			newProxyInstance方法有三个参数：
				loader: 用哪个类加载器去加载代理对象
				interfaces:动态代理类需要实现的接口
				h:动态代理方法在执行时，会调用h里面的invoke方法去执行
			 */
    }

}

//InvocationHandler接口
class MyInvocationHander implements InvocationHandler {

    private Object object; //需要使用被代理类赋值

    public void bind(Object object) {
        this.object = object;
    }

    //当通过代理类对象调用方法getBelief()、eat()时，就会自动调用如下方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue = method.invoke(object, args); //被代理类对象要调用的方法
        return returnValue;
    }
	/*
	invoke(Object proxy, Method method, Object[] args)
		proxy：代理类对象 superMan对象
		method：代理类对象调用方法 eat方法
		args：传入method方法的参数 fish参数
	 */

}

public class R5 {

    public static void main(String[] args) {

        SuperMan superMan = new SuperMan();
        Human proxyInstanceHuman = (Human) ProxyFactory.getProxyInstance(superMan);
        System.out.println(proxyInstanceHuman.getBelief());
        proxyInstanceHuman.eat("fish");

    }

}
