package a4_Multi_Threaded;

import java.util.concurrent.*;

/*
多线程的创建
	方法三：实现Callable接口（注意import导入）
		1.创建一个实现Callable的实现类
		2.实现call方法，将线程要执行的操作写入call中
		3.创建Callable接口实现类的对象
		4.将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask类的对象
		5.将FutureTask对象作为参数传递给Thread类中，创建Thread类对象，并调用start方法
		6.用FutureTask对象调用get方法获取Callable中call方法的返回值
		补充：Callable接口优于Runnable接口：
			1.call方法可以有返回值
			2.call方法可以抛出异常
			3.Callable支持泛型
	方法四：使用线程池（注意import导入）
		1.提供指定线程数量的线程池
		2.执行指定的线程操作
		3.关闭连接池
		补充：优点：
			1.提高响应速度（减少创建新线程的总时间）
			2.降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
			3.便于线程管理
				corePoolSize：核心池大小
				maximumPoolSize：最大线程数
				keepAliveTime：线程没有任务时最多保持多长时间后终止
 */

//方法三：实现Callable接口
class NumberThread implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 101; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        System.out.println("end!");
        return sum;
    }

}

//方法四：使用线程池
class NumberThread1 implements Runnable {

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < 101; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
            if (i == 100) {
                System.out.println(Thread.currentThread().getName() + ":" + sum);
            }
        }
    }

}

public class MT9 {

    public static void main(String[] args) {

        //方法三：实现Callable接口
        NumberThread numThread = new NumberThread(); //创建Callable接口实现类的对象
        FutureTask futureTask = new FutureTask(numThread);
        //将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask类的对象
        new Thread(futureTask).start();
        //将FutureTask对象作为参数传递给Thread类中，创建Thread类对象，并调用start方法
        try {
            Object sum = futureTask.get(); //get方法获取Callable中call方法的返回值
            System.out.println(Thread.currentThread().getName() + ":" + sum);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //方法四：使用线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10); //提供指定线程数量的线程池，此处提供了10个

        //设置线程池属性（见印象笔记）
        //ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor) executorService;
        //threadPoolExecutor.setCorePoolSize(15);
        //threadPoolExecutor.setKeepAliveTime();

        //执行指定的线程操作
        executorService.execute(new NumberThread1()); //适用于Runnable
        executorService.submit(new NumberThread()); //适用于Callable

        executorService.shutdown(); //关闭连接池

    }

}
