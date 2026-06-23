package a3_Exception_Handling;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EH2 {

    public static void main(String[] args) {

        new EH2().test1();

        try {
            new EH2().test2();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //异常处理方式
	/*
	1.try-catch-finally
		try{
			可能出现错误的代码
		}
		catch(异常处理1 变量1){
			处理异常的方式1
		}
		catch(异常处理2 变量2){
			处理异常的方式2
		}
		...
		finally{
			一定执行的代码
		}

		说明：
			1.finally非必须，finally先于return执行
			2.一个catch对应一个异常对象，当try中出现异常时，会自动生成一个异常对象并终止try异常后面的代码，
		该异常对象按顺序和下面的catch匹配，若异常类型匹配则进入catch（仅会按顺序匹配一个catch，执行完对应
		catch后直接退出try-catch而不再判断其他catch），最后执行finally
			3.catch要求异常子类对象的顺序要在父类之上，否则报错
			4.常用异常处理方式：
				（1）system.out.println(e.getMessage()); 显示error
				（2）e.printStackTrace(); 显示错误及类型（常用）
			5.try中声明的变量属于局部变量，在try外不能使用
			6.try-catch可嵌套
	2.throws+异常类型
		throws会在异常时生成异常对象并抛出，终止其后的代码。
	3.对比
		try—catch：解决了异常问题
		throws：只是将异常抛出给方法的调用者，没有实际解决
	 */
    @Test
    public void test() {

        try {
            String string = "qwert";
            int i = Integer.parseInt(string);
            //只要出现异常就不会执行下面的语句，自动终止跳入catch
            System.out.println("123");
            int i1 = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("整数除零异常");
        } catch (NumberFormatException e) {
            System.out.println("数字格式化异常");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) { //总异常
            System.out.println("异常");
        } finally {
            System.out.println("end");
        }

    }

    //注意finally会在所有的return执行之前执行
    public int test1() {

        try {
            int[] arr = new int[9];
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界异常");
            return 1;
        } catch (Exception e) {
            System.out.println("异常");
            return 2;
        } finally {
            System.out.println("end");
        }
        return 0;

    }

    public void test2() throws FileNotFoundException, IOException {

        File file = new File("123.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        int data = fileInputStream.read();
        while (data != -1) {
            System.out.println((char) data);
            data = fileInputStream.read();
        }

        fileInputStream.close();

    }

}
