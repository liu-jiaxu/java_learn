package a4_Multi_Threaded;

//线程的通信
/*
sleep与wait的异同：
	1.相同：
		都可以使当前线程阻塞
	2.不同：
		（1）声明位置不同，Thread中声明的sleep，Object中声明的wait
		（2）调用范围不同，sleep随意，wait要在同步代码块（synchronized()）中使用
		（3）释放同步锁：sleep 不会释放锁，但是会让出CPU执行权，wait释放锁，同时让出CPU执行权
 */

//例：两个线程分别交替输出100个数
class Number implements Runnable {

    private int number = 1;

    /*
        当线程1调用run方法时，synchronized锁住该线程，必须执行完CPU才分配其余内存，此时notifyAll()无用，
    执行到wait时，该线程被阻塞，同时解开synchronized同步锁，运行CPU为另外的线程分配内存执行，此时线程2
    开始执行，先被synchronized锁住，再执行notifyAll()，随机激活一个被wait()阻塞的所有线程（此处仅有
    线程1，因此线程1又被激活），之后执行到wait()时线程2又被阻塞，CPU开始执行线程1，依次重复
     */
    public void run() {
        while (true) {
            synchronized (this) {
                notifyAll(); //激活所有被wait()阻塞的所有线程，notify()激活一个阻塞线程
                if (number <= 100) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                    try {
                        wait(); //使当前线程处于阻塞状态，同时解开synchronized锁
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

public class MT7 {

    public static void main(String[] args) {

        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);
        thread1.setName("one");
        thread2.setName("two");
        thread1.start();
        thread2.start();

    }

}
