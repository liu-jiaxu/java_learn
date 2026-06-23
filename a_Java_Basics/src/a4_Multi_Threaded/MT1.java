package a4_Multi_Threaded;

/*
程序
	用某种语言编写的静态代码的集合
进程
	程序的一次执行过程或是正在运行的一个程序。每个进程对应不同内存区域
线程
	同一进程同一时间执行多个线程，则为支持多线程
	每个线程有独立的运行栈和程序计数器，多个线程共用一个堆
	
主线程：main方法执行的线程
子线程：包含在 Thread thread = new Thread( );里面均视为子线程。

并行：每个CPU（核）执行自己的事，多个CPU同时执行多个任务
并发：一个CPU同时执行多个任务
 */

/*
多线程的创建
	方法一：继承Thread类
		1.创建一个继承Thread类的子类
		2.重写Thread的run方法,将此线程执行的操作声明在run方法中
		3.创建Thread子类的对象
		4.通过子类对象调用start方法
			start方法：（1）启动当前线程；
			          （2）调用当前线程的run()；
			          （3）一个对象的start方法只能执行一次，想要多次执行需要创建多个对象；
					  （4）直接调用run方法不会启动线程，必须用start方法
		补充：可以直接通过匿名类的方法重写run()然后调用start()！
	方法二：实现Runnable接口
		1.创建一个实现RUN内部类接口的类
		2.实现类去实现Runnable中的抽象方法run()
		3.创建实现类对象
		4.将此对象作为参数传递给Thread构造器中，创建Thread类对象
		5.通过Thread类的对象调用start()
	方法三四：见程序MT9
		
	比较：优先选择方法二实现接口
		1.实现没有单继承局限性
		2.更适合处理多线程数据共享
		
 */

//方法一：继承Thread类
class MyThread extends Thread {

    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
    }

}

//方法二：实现Runnable接口
class MyThreadOne implements Runnable {

    public void run() {
        for (int i = 100; i < 200; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
    }

}

public class MT1 {

    public static void main(String[] args) {

        //方法一
        MyThread myThread = new MyThread();
        myThread.start(); //start开启线程

        //主线程
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.print(i + "a ");
            }
        }
		/*
			线程情况：
			    1.主线程：main函数内的线程
			    2.方法一start开启的线程
			    3、4.方法一使用匿名内部类开启的线程
			    5.方法二开启的线程
		此时相当于主线程中有两个线程同时执行，为多线程，线程执行顺序的先后取决于CPU的运行情况，每次执行的结果可能不同！
		 */

        //例：利用匿名类分别创建两个分线程
        new Thread() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        }.start();
        new Thread() {
            public void run() {
                for (int i = 50; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        }.start();

        //方法二
        MyThreadOne myThreadOne = new MyThreadOne();
        Thread thread = new Thread(myThreadOne);
        try {
            //线程开启前先睡眠1ms
            thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();

    }

}
