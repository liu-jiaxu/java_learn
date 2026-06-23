package main.a1_Basic_DataStructure.a4_queue_stack_heap.blocking_queue;

import org.junit.jupiter.api.Test;

/**
 * ClassName: BlockingQueue_Test
 * Package: main.a1_Basic_DataStructure.a4_queue_stack_pile.blocking_queue
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/5/22 - 17:00
 * @Version: v1.0
 */
public class BlockingQueue_Test {

    @Test
    public void test() throws InterruptedException {

        main.a1_Basic_DataStructure.a4_queue_stack_heap.blocking_queue.BlockingQueue1<String> queue = new main.a1_Basic_DataStructure.a4_queue_stack_heap.blocking_queue.BlockingQueue1<String>(10);
        for (int i = 0; i < 10; i++) {
            queue.offer("a" + i);
        }

        // 下面两个线程针对BlockingQueue1的offer方法判满为if(不为while)，且没有headWaits.signal()唤醒线程时
        //     当第一个线程执行到queue.offer("a10")时判满语句执行，该暂时线程阻塞，第二个线程开始执行，
        // queue.tailWaits.signal()方法会唤醒tailWaits.await()阻塞线程并释放锁，线程二结束时，线程一被唤醒，
        // 添加元素a10，执行遍历操作，由于此时唤醒的线程又执行了一次size++，因此此时size == 11，循环会报错：数组
        // 索引越界异常
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "添加元素之前");
                queue.offer("a10");
                System.out.println(Thread.currentThread().getName() + "添加元素成功");
                for (Object o : queue) {
                    System.out.print(o+ " ");
                }
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1").start();

        new Thread(() -> {
            System.out.println("开始唤醒");
            try {
                queue.lock.lockInterruptibly();
                queue.tailWaits.signal();
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                queue.lock.unlock();
            }
        },"t2").start();

    }

    @Test
    public void test2() {

        main.a1_Basic_DataStructure.a4_queue_stack_heap.blocking_queue.BlockingQueue1<String> queue = new main.a1_Basic_DataStructure.a4_queue_stack_heap.blocking_queue.BlockingQueue1<String>(3);
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(System.currentTimeMillis() + "begin");
                queue.offer("b1");
                queue.offer("b1");
                queue.offer("b1");
                queue.offer("b4",3000);
                System.out.println(System.currentTimeMillis() + "end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "生产者");
        t1.start();

    }

}
