package a5_Common_Class;

import org.junit.Test;

import java.math.BigDecimal;

public class CC7 {

    //System系统类方法
    @Test
    public void test() {

        System.out.println(System.currentTimeMillis()); //时间戳
        //System.exit(0); //退出程序
        System.gc(); //垃圾回收
        System.out.println(System.getProperty("java.version")); //传入属性名（String型），获取对应系统属性说明
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.dir"));
			/*
				属性名				属性说明
				java.version		Java运行时的环境版本
				java.home			java安装目录
				os.name				操作系统名称
				os.version			操作系统版本
				user.name			用户账户名称
				user.home			用户主目录
				user.dir			用户当前目录
			 */

    }

    //Math数学类方法
    @Test
    public void test2() {

        double i1 = 55.45;
        System.out.println(Math.abs(i1)); //绝对值
        System.out.println(Math.acos(i1)); //三角函数arccos
        System.out.println(Math.sin(i1)); //三角函数sin
        System.out.println(Math.sqrt(i1)); //平方根
        System.out.println(Math.pow(i1, 3)); //i1的3次幂
        System.out.println(Math.log(i1)); //自然对数
        System.out.println(Math.exp(i1)); //e为底指数
        System.out.println(Math.max(i1, 12)); //最大值
        System.out.println(Math.min(i1, 7)); //最小值
        System.out.println(Math.random()); //[0,1)随机数
        System.out.println(Math.round(i1)); //四舍五入
        System.out.println(Math.toDegrees(i1)); //弧度->角度
        System.out.println(Math.toRadians(i1)); //角度->弧度

    }

    //BigInteger不可变任意精度整数 & BigDecimal数字精度非常高，对应浮点型（解决了一般浮点型计算不精确的问题）
    @Test
    public void test3() {

        //BigDecimal类方法
		/*
			加add
			减subtract
			乘multiply
			除divide
		 */
        BigDecimal bigDecimal = new BigDecimal("12.3333");
        BigDecimal bigDecimal2 = new BigDecimal("134.53432342342");
        System.out.println(bigDecimal.add(bigDecimal2));
        System.out.println(bigDecimal.subtract(bigDecimal2));
        System.out.println(bigDecimal.divide(bigDecimal2, 132, BigDecimal.ROUND_HALF_UP));
        //注意除法，除不尽会报错，需要指定保留位数（scale：132）和保留方式（BigDecimal.ROUND_HALF_UP为最后一位进位）

    }

}
