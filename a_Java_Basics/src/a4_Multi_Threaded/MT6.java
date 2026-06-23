package a4_Multi_Threaded;

import java.util.concurrent.locks.ReentrantLock;

//例：四个账户向同一个账户存款5000元，每次存1000元，存5次。每次存完打印账户余额。
class Account {

    private double balance;
    private ReentrantLock lock = new ReentrantLock(true); //注意锁的位置！不是锁Customer

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amt) {
        try {
            lock.lock();
            if (amt > 0) {
                balance += amt;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "存钱成功！余额为：" + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + "存钱失败！");
            }
        } finally {
            lock.unlock();
        }
    }

}

class Customer implements Runnable {

    private Account account;

    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 5; i++) {
            account.deposit(1000);
        }
    }

}

public class MT6 {

    public static void main(String[] args) {

        Account account = new Account(0);
        Customer customer = new Customer(account);
        Thread thread1 = new Thread(customer);
        Thread thread2 = new Thread(customer);
        Thread thread3 = new Thread(customer);
        Thread thread4 = new Thread(customer);
        thread1.setName("甲");
        thread2.setName("乙");
        thread3.setName("丙");
        thread4.setName("丁");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

}
