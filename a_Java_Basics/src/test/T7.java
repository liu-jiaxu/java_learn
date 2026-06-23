package test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: T7
 * Package: test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/15 - 10:30
 * @Version: v1.0
 */

public class T7 {

    static class A extends Thread {

        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }

        }

    }

    static class B implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }

        }

    }

    static class C implements Callable<String> {

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            return "C";
        }
    }

    public static void main(String[] args) {

        A a = new A();
        B bT = new B();
        Thread b = new Thread(bT);
        C cT = new C();
        a.setName("A");
        b.setName("B");
        FutureTask<String> futureTask = new FutureTask<String>(cT);
        Thread c = new Thread(futureTask);
        c.setName("C");
        a.start();
        b.start();
        c.start();
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }

    }

}

/*
共1000张电影票，在两个窗口领取，假设每次领取时间为3000毫秒，
    要求：使用多线程模拟卖票过程并打印剩余电影票的数量
        100份礼品，两人同时发送，当剩下的礼品小10份时，则不再送出
    利用多线程模块该过程并将线程的名字和礼物的剩余数量打印出来
 */
class Ticket {

    private static int tickets = 1000;

    private final ReentrantLock lock = new ReentrantLock(true);

    public Ticket() {
    }

    public Ticket(int tickets) {
        this.tickets = tickets;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "tickets=" + tickets +
                '}';
    }

    public void sellTicket() {

        try {
            lock.lock();
            Thread.sleep(3);
            System.out.println(Thread.currentThread().getName() + "：当前售出的票为第" + tickets + "张");
            tickets--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }


    }

}

class Sell implements Runnable {

    Ticket ticket = new Ticket(1000);

    public Sell() {
    }

    public Sell(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "ticket=" + ticket +
                '}';
    }

    @Override
    public void run() {
        while (ticket.getTickets() > 10) {
            ticket.sellTicket();
        }
    }

}

class Test {

    public static void main(String[] args) {

        Ticket ticket = new Ticket(1000);
        Sell sell = new Sell(ticket);
        Thread t1 = new Thread(sell);
        Thread t2 = new Thread(sell);
        t1.start();
        t2.start();

    }

}

/*
同时开启两个线程，共同获取1-100之间的所有数字
要求：输出所有的奇数
 */
class Test2 implements Runnable {

    int number = 1;

    ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                //notify();
                if (number > 100) {
                    break;
                } else {
                    //if (number % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + number);
                    //}
                    number ++;
                    /*try {
                        wait(); //使当前线程处于阻塞状态，同时解开synchronized锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }
    }

    public static void main(String[] args) {

        Test2 t = new Test2();
        Thread a1 = new Thread(t);
        Thread a2 = new Thread(t);
        a1.start();
        a2.start();

    }

}