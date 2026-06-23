package a1_Basics;

import java.util.Scanner;
import java.util.Random;

public class B4 {

    public static void main(String[] args) {
	
		/*
		输入
			1.导入包
				import java.util.Scanner;
			2.定义对象并调用方法
				Scanner in=new Scanner(System.in);
				int a=in.nextInt();
				double b=nextDouble();
				String c=in.next();
				......
			3.注
				输入与定义类型不匹配会报错（自动类型转换除外）
		*/
		
		
		
		/*
		随机数获取方法
			1.Math.random() --> double型 [0.0,1.0]							
			2.import java.util.Random;
			  Random random = new Random();
         	  int number = random.nextInt(END - START +  1) + START;
		*/
        //例：获取一个10-99范围的随机整数
        int i1 = (int) (Math.random() * 90 + 10); //注意数据类型转换
        System.out.println("i1:" + i1);
        //例：获取一个[START,END]范围的随机整数
        int START = 33, END = 100;
        Random random = new Random();
        int number = random.nextInt(END - START + 1) + START;
        System.out.println("number:" + number);

        //补充：一维数组获取10个一位不同随机数
        int[] arr1 = new int[10];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random() * 10);
            for (int j = 0; j < i; j++) {
                if (arr1[i] == arr1[j]) {
                    i--;
                    break;
                }
            }
        }
	  		/*
	  			例：a[0]=3,a[1]=3,此时出现重复项，i=1，j=0，执行i--后内部for循环立即终止，之后外层for循环
	  		结束一次循环后又执行了i++，相当于i值不变，重新对a[i]=a[1]赋值
	  		 */
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
	  	
	  	
	  	
		/*
		流程控制
			顺序结构
			分支结构if-else switch-case
			循环结构while do-while for foreach
		 */

        //分支结构
        //if-else三种
        int age;
        Scanner in = new Scanner(System.in);
        System.out.print("请输入年龄：");
        age = in.nextInt();
        if (age < 0) {
            System.out.println("Error!");
        } else if (age > 18) {
            System.out.println("legal");
        } else {
            System.out.println("illegal");
        }
			/*
			注意区别：
				if-else:从上往下运行且最多只满足其中一个，之后退出判断
				多个if:从上往下运行但可以同时满足多个if
			*/

        //switch-case
        //JDK7.0之后可以接收byte、short、char、int、String、枚举六种类型
        //default位置随意，总是最后执行
        System.out.print("输入符号：");
        String size = in.next();
        switch (size) {
            case "+":
                System.out.println("5" + size + "3" + "=" + (5 + 3));
                break;
            case "-":
                System.out.println("5" + size + "3" + "=" + (5 - 3));
                break;
            case "*":
                System.out.println("5" + size + "3" + "=" + (5 * 3));
                break;
            case "/":
                System.out.println("5" + size + "3" + "=" + (5 / 3.0));
                break;
            default:
                System.out.println("Error!");
        }
        //例：输入年、月、日，得到这是一年中的第几天
        //注意解题技巧!
        System.out.print("输入年、月、日：");
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int sumday = 0;
        if (year > 0 && year < 100000 && month > 0 && month < 13 && day > 0 && day < 32) {
            switch (month) {
                case 12:
                    sumday += 30;
                case 11:
                    sumday += 31;
                case 10:
                    sumday += 30;
                case 9:
                    sumday += 31;
                case 8:
                    sumday += 31;
                case 7:
                    sumday += 30;
                case 6:
                    sumday += 31;
                case 5:
                    sumday += 30;
                case 4:
                    sumday += 31;
                case 3:
                    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) { //闰年判断
                        sumday += 29;
                    } else {
                        sumday += 28;
                    }
                case 2:
                    sumday += 31;
                case 1:
                    sumday += day;
                    System.out.println("一共有" + sumday + "天");
                    break;
                default:
                    System.out.println("Error!");
            }
        } else {
            System.out.println("Error!");
        }
			
			/*
			总结
				1.switch-case --> if-else恒成立，反之不成立（switch接收数据类型少）
				2.优先使用switch-case，执行效率高
			 */

    }

}
