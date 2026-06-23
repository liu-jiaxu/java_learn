package a3_Exception_Handling;

//异常方法重写子类重写的方法抛出的异常类型不大于父类的异常类型

/*
try-catch/throws的选取
	1.父类无throw时子类无法用throws，只能用try-catch
	2.多个方法有先后顺序时，用throws，在执行总调用时加一个总的try-catch
 */

//手动抛出异常
class Student {

    private int id = 0;

    public Student(int id) {
        this.id = id;
    }

    public void printid() {
        if (id >= 0) {
            System.out.println(id);
        } else {
            //throw new RuntimeException("非法id"); //手动抛出异常
            throw new MyException("自定义抛出异常"); //自定义异常
        }

    }
}

/*
自定义异常
	1.继承现有异常类RuntimeException、Exception
	2.提供全局异常serialVersionUID
	3.提供重载构造器
 */
class MyException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L; //序列号标识此异常

    public MyException() {
    }

    public MyException(String string) {
        super(string);
    }

}

public class EH3 {

    public static void main(String[] args) {

        Student student = new Student(-20);
        student.printid();

    }
}
