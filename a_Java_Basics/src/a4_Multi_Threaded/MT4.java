package a4_Multi_Threaded;

//例：创建三个窗口卖票300张
//方法一：继承Thread类
class Window extends Thread {

    private static int ticket = 300;

    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "票号：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }

}

//方法二：实现Runnable接口
class Window1 implements Runnable {

    private int ticket = 300;

    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "票号：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }

}

/*
线程安全问题
	1.问题：出现重票、错票
	2.原因：（1）重票：一个线程执行时，获取了票号60，而由于某些原因（手动的sleep、join等方法阻塞或系统问题自动阻塞）产生阻塞，
					  此时该线程暂停执行，并且还没有减掉线程获取的票数，而CPU给其他线程分配内存，使另一个线程开始执行，另一个
					  线程获取当前的票数，最终两个线程获取了同样的票号，出现了重票
			（2）错票：只剩最后一张票时，一个线程获取了这张票，但立即被阻塞，而另外两个线程同样地获取了这张票，之后也被阻塞，
					  最后三个线程依次恢复执行，最后恢复执行的线程的票数变成了-1，出现错票
	3.解决：让一个线程操作时其他线程不能操作，直到一个线程执行完本次操作其余线程再执行
	4.Java中解决：同步机制解决安全问题
		方式1：同步代码块
			synchronized(同步监视器){
				需要同步的代码（操作共享数据的代码）
			}
			同步监视器：锁。任何一个对象都可作为锁。
			理解：程序运行时会将synchronized包含的代码锁住，只有在包含的代码执行完成、处于wait状态、强制停止时CPU才会为其余线程分配内存
			要求：多个线程必须用同一把锁，注意synchronized不能包括多了代码	
		方式2：同步方法
		方式3：Lock锁（见程序MT5）
			
	5.同步后：解决安全问题，但执行效率低
 */

//加入同步监视器后的售票
//方式1：同步代码块继承Thread类改写
class Window_Alter extends Thread {

    static final Object object = new Object();
    private static int ticket = 300;
		/*
			因为有三个Window_Alter类对象，每个对象都会调用一次run方法，因此每个对象会执行不同的synchronized，此时synchronized
		失效，因此要改为静态的对象传递给synchronized方法，让每个对象都调用相同的synchronized，保证多个线程必须用同一把锁
		 */

    public void run() {
        while (true) {
            synchronized (object) { //补充：synchronized (Window_Alter.class)也可以，类也是对象，只会加载一次
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "票号：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }

}

//方式1：同步代码块实现Runnable接口改写
class Window_Alter1 implements Runnable {

    private int ticket = 300;

    public void run() {
        while (true) {
            synchronized (this) { //synchronized (this)就可以了
                //每个线程执行到synchronized包括的代码块时，都会暂时锁定，直到本次线程执行完成后才解锁，期间其他线程无法被CPU执行
                if (ticket > 0) {
                    try {
                        Thread.sleep(1); //这里的sleep其实没有用，因为被synchronized包裹“失效了”
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "票号：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }

}

//方式2：同步方法实现Runnable接口改写
class Window_Alter2 implements Runnable {

    private int ticket = 300;

    public synchronized void show() { //直接在方法中写synchronized，相当于synchronized (this)
        if (ticket > 0) {
            /*//此处sleep模拟门票办理等待时间
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            System.out.println(Thread.currentThread().getName() + "票号：" + ticket);
            ticket--;
        }
    }

    //重写run方法，循环调用show
    public void run() {
        while (ticket > 0) {
            show();
        }
    }

}

public class MT4 {

    public static void main(String[] args) {
		
		/*//无同步售票
		Window1 window1=new Window1();
		Thread thread1=new Thread(window1);
		Thread thread2=new Thread(window1);
		Thread thread3=new Thread(window1);
		thread1.setName("窗口一");
		thread2.setName("窗口二");
		thread3.setName("窗口三");
		thread1.start();
		thread2.start();
		thread3.start();*/
		
		/*//方式1：同步代码块继承Thread类改写
		Window_Alter window_Alter=new Window_Alter();
		Thread thread1=new Thread(window_Alter);
		Thread thread2=new Thread(window_Alter);
		Thread thread3=new Thread(window_Alter);
		thread1.setName("窗口一");
		thread2.setName("窗口二");
		thread3.setName("窗口三");
		thread1.start();
		thread2.start();
		thread3.start();*/
		
		//方式1：同步代码块实现Runnable接口改写
		Window_Alter1 window_Alter1=new Window_Alter1();
		Thread thread1=new Thread(window_Alter1);
		Thread thread2=new Thread(window_Alter1);
		Thread thread3=new Thread(window_Alter1);
		thread1.setName("窗口一");
		thread2.setName("窗口二");
		thread3.setName("窗口三");
		thread1.start();
		thread2.start();
		thread3.start();

        //方式2：同步方法实现Runnable接口改写
        /*Window_Alter2 window_Alter2 = new Window_Alter2();
        Thread thread1 = new Thread(window_Alter2);
        Thread thread2 = new Thread(window_Alter2);
        Thread thread3 = new Thread(window_Alter2);
        thread1.setName("窗口一");
        thread2.setName("窗口二");
        thread3.setName("窗口三");
        thread1.start();
        thread2.start();
        thread3.start();*/

    }

}
