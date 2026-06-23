package a4_Multi_Threaded;

/*
线程优先级设定
	1.参数（越大优先级越高）
		MAX_PRIORITY:10
		MIN_PRIORITY:1
		NORM_PRIORITY:5
	2.获取和设置优先级
		getPriority()：获取线程优先级（默认为5，大于0小于11）
		setPriority()：设置线程优先级
	3.注：
	    （1）线程的优先级并不是先全部执行完再执行其他线程的关系，而是优先级高的线程有更高概率提前被CPU执行！
	    （2）java线程默认优先级为5，当设置父类优先级后，其子类的优先级也会从默认的5变为和父类相同！
 */

class MyThread5 extends Thread {

    //构造器方式使用setName，为当前线程命名
    public MyThread5(String string) {
        super(string);
    }

    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }

}

class MyThread6 extends Thread {

    //构造器方式使用setName
    public MyThread6(String string) {
        super(string);
    }

    public void run() {
        for (int i = 50; i < 100; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }

}

public class MT3 extends Thread {

    public static void main(String[] args) {

        MyThread5 myThread5 = new MyThread5("线程5");
        myThread5.setPriority(Thread.MIN_PRIORITY); //可以直接调用参数设置

        MyThread6 myThread6 = new MyThread6("线程6");
        myThread6.setPriority(10); //可以直接设置参数

        Thread.currentThread().setName("主线程");

        myThread5.start(); //1
        myThread6.start(); //10
        for (int i = 100; i < 150; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }//此处为主线程，不设置优先级默认为5

        //获取优先级参数
        System.out.println(myThread5.getPriority());
        System.out.println(new MT3().getPriority());

    }

}
