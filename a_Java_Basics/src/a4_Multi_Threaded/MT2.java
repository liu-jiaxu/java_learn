package a4_Multi_Threaded;

/*
Thread中常用方法：
	1.start：启动当前线程，调用当前线程的run方法
	2.run：重写原始run方法，将要执行的多线程操作写入run方法中
	3.currentThread：静态方法，返回当前代码的线程
	4.getName：获取当前线程的名字（不设置时默认从Thread-0开始）
	5.setName：设置当前线程的名字（在start执行前设置，可以通过调用该方法或父类构造器两种方法使用）
	6.yield：释放当前CPU的执行权，暂时中止此时的线程，但如果下一次该线程竞争到CPU的执行权，还是可以执行此线程剩余的部分
	7.join：a线程在其他线程start方法之前调用join方法时，会先将主线程（main）及其它正在执行的线程存储到子线程a的缓存池中
			（此时线程a仍在运行），直到线程a完全结束时，再执行主线程（main）及其它线程，一个线程的join方法不会影响已经
			开始执行的其他线程的进程。在run中调用join方法，其余线程结束后无法再执行此线程剩余进程。（要使用try-catch）
	8.stop：强制结束当前线程
	9.sleep(long millitime)：让当前线程睡眠指定时间（毫秒），此间该线程一直处于阻塞状态
	10.isAlive：判断当前线程是否存活（boolean型）
 */

class MyThread1 extends Thread {

    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(this.getName() + " " + i);
            if (i > 20) {
                Thread.yield();
            }
            if (i > 40) {
                try {
                    join(); //在run中调用join方法，其余线程结束后无法再执行此线程剩余进程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class MyThread2 extends Thread {

    //构造器方式使用setName
    public MyThread2(String string) {
        super(string);
    }

    public void run() {
        for (int i = 50; i < 100; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }

}

class MyThread3 extends Thread {

    public void run() {
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 150; i < 200; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }

}

class MyThread4 extends Thread {

    public void run() {
        for (int i = 200; i < 250; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }

}


public class MT2 {

    public static void main(String[] args) {

        MyThread3 myThread3 = new MyThread3();
        myThread3.setName("线程3");
        myThread3.start();

        MyThread4 myThread4 = new MyThread4();
        myThread4.setName("线程4");
        myThread4.start();
        try {
            myThread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyThread1 myThread1 = new MyThread1();
        myThread1.setName("线程1");
        myThread1.start();

        MyThread2 myThread2 = new MyThread2("线程2");
        myThread2.start();

        System.out.println(myThread1.isAlive());
		/*
		执行顺序：
			线程3先调用start执行，但设置了sleep睡眠1ms，所以线程4先执行，在线程4执行后调用了join方法，因此其它正在执行的线程
	    进入线程4的缓存池，直到线程4执行完成后再执行其它已经开始执行的线程。此时线程1、2、3均开始执行，CPU根据运行给三个线程分
		配内存，当线程1运行到满足join时，线程1进入缓存池，仅执行其余线程，直到结束，CPU不会再给没有执行完的线程1分配内存了，
		程序一直挂起但不结束。
		 */
    }

}
