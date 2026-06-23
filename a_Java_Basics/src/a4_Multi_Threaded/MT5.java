package a4_Multi_Threaded;

import java.util.concurrent.locks.ReentrantLock;

/*
线程的生命周期
	1.新建：Thread及子类new一个对象时
	2.就绪：调用start()后，但还没有分配内存资源（或调用yield之后CPU重新分配内存）
	3.运行：进入运行状态，CPU分配空间，执行对应的run()方法
	4.阻塞：CPU临时阻止自己运行（sleep、join、wait、suspend）
	5.死亡：线程全部结束或被强制退出
 */

/*
死锁问题
	1.理解：不同线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了死锁
	2.说明：出现死锁后，程序不会异常不会报错不会停止，只是所有线程处于阻塞状态无法继续
 */

/*
Java中解决：同步机制解决安全问题
	方式3：Lock锁（注意import导入）
	使用顺序：lock>同步代码块>同步方法
 */
//例：售票
class Window_Alter3 implements Runnable {

    private int ticket = 50;
    private ReentrantLock lock = new ReentrantLock(true); // 实例化lock
    // 参数true表示当一个线程结束后，下一次不会再执行它了，而是执行其余线程，保证公平性

    public void run() {
        while (true) {
            try {
                lock.lock(); // 调用lock锁定
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "票号：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock(); // 解锁
            }
        }
    }

}

public class MT5 {

    public static void main(String[] args) {

        //lock方法演示
        Window_Alter3 window_Alter3 = new Window_Alter3();
        Thread thread1 = new Thread(window_Alter3);
        Thread thread2 = new Thread(window_Alter3);
        Thread thread3 = new Thread(window_Alter3);
        thread1.setName("窗口一");
        thread2.setName("窗口二");
        thread3.setName("窗口三");
        //加锁后，
        thread1.start();
        thread2.start();
        thread3.start();

        //死锁问题演示
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();

			/*第一个匿名类执行时，synchronized先获取stringBuffer1对象，执行期间遇到sleep进入阻塞，此时第一个匿名类
		处于等待状态，等待之后的synchronized (stringBuffer2)获取stringBuffer1对象，而在等待期间CPU极有可能为下一
		个匿名类分配内存执行，若执行了下一个匿名类，则下一个匿名类synchronized (stringBuffer2)先获取stringBuffer2
		对象，之后sleep阻塞，等待获取stringBuffer1对象。此时的状态是第一个匿名类在用stringBuffer1对象，要获取
		stringBuffer2对象，第二个匿名类在用stringBuffer2对象，要获取stringBuffer1对象，两者互不相让形成死锁。*/

        new Thread() {
            public void run() {
                synchronized (stringBuffer1) {
                    stringBuffer1.append("a");
                    stringBuffer2.append("1");
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    synchronized (stringBuffer2) {
                        stringBuffer1.append("b");
                        stringBuffer2.append("2");
                        System.out.println(stringBuffer1);
                        System.out.println(stringBuffer2);
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                synchronized (stringBuffer2) {
                    stringBuffer1.append("c");
                    stringBuffer2.append("3");
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    synchronized (stringBuffer1) {
                        stringBuffer1.append("d");
                        stringBuffer2.append("4");
                        System.out.println(stringBuffer1);
                        System.out.println(stringBuffer2);
                    }
                }
            }
        }.start();

    }

}
