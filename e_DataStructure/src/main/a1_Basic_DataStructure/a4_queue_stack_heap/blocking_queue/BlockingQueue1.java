package main.a1_Basic_DataStructure.a4_queue_stack_heap.blocking_queue;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: BlockingQueue1
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.blocking_queue
 * Description:单锁阻塞队列
 *
 * @Author: zgh296
 * @Create: 2023/5/22 - 20:15
 * @Version: v1.0
 */
public class BlockingQueue1<E> implements BlockingQueue<E>, Iterable<E>{

    // 1.synchronized关键字，功能少
    // 2.ReentrantLock 可重入锁，功能多

    private final E[] array;
    private int head = 0;
    private int tail = 0;
    private int size = 0; // 元素个数

    @SuppressWarnings("all")
    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    // 锁对象
    ReentrantLock lock = new ReentrantLock();
    // 条件变量对象，是一个集合，，可以存储多个阻塞线程
    Condition tailWaits = lock.newCondition();
    Condition headWaits = lock.newCondition();

    @Override
    public void offer(E e) throws InterruptedException {
        // lock.lock()：所有线程每次只能加一个锁，一个线程加锁后其余线程再加锁时，会处于阻塞状态，直至之前线程解锁
        // lockInterruptibly()：该锁可以在阻塞状态被其它线程随时打断
        lock.lockInterruptibly();
        // 加锁解锁之间会进行锁对象保护
        // 由于加锁后出现异常时程序会自动结束，因此有异常时不会执行解锁，此时需要try-finally代码块
        try {
            while (isFull()) { // 虚假唤醒，要用while重复判断多个线程(若用if只判断一次)
                // 数组满了之后，使该线程阻塞，等到该数组删除元素空缺位置时，重新唤醒该线程
                tailWaits.await(); // 将当前线程加入到tailWaits，并且让此线程阻塞
            }
            // 数组不满时
            array[tail] = e;
            // 当从指针处开始添加元素至数组最大索引时，更新指针变为0，继续使用数组
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal(); // size++后，数组不为空，将当前poll方法因数组为空时阻塞的线程唤醒
        } finally {
            lock.unlock(); // 解锁
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            // 给定时间结束后，自动唤醒
            long t = TimeUnit.MILLISECONDS.toNanos(timeout); // 将传入的毫秒变为纳秒
            while (isFull()) {
                if (t <= 0) {
                    return false;
                }
                t = tailWaits.awaitNanos(t);
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            E e = array[head];
            array[head] = null; // help GC
            if (++head == array.length) {
                head = 0;
            }
            size--;
            tailWaits.signal(); // size--后，当前数组不满，将当前offer方法因数组为满时阻塞的线程唤醒
            return e;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E peek() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            return array[head];
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int p = 0;
            @Override
            public boolean hasNext() {
                return p < size;
            }

            @Override
            public E next() {
                return array[p++];
            }
        };
    }

}
