package a1_JDBCBasics;

import java.sql.Connection;

/**
 * ClassName: a1_JDBCBasics.a8_BankService
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/6 - 9:53
 * @Version: v1.0
 */
public class a8_BankService {

    public void transfer(String addAccount,String subAccount, int money) throws Exception {

        System.out.println("addAccount = " + addAccount + ", subAccount = " + subAccount + ", money = " + money);

        // 一个事务必须保证是同一个连接对象connection
        // 此转账事务包含加钱减钱两个方法，即一个事务包含两个SQL语句，必须要用同一连接

        // 直接使用封装类创建连接即可
        /*//注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql:///atguigu", "root", "zgh2960425");*/
        Connection connection = a8_v2_jdbcutils.getConnection();

        // flag参数判断转账成功与否
        int flag = 0;

        //利用try代码块,调用dao
        try {

            //开启事务(关闭事务自动提交)
            connection.setAutoCommit(false);

            a6_BankDao bankDao = new a6_BankDao();
            //调用加钱和减钱
            bankDao.addMoney(addAccount, money, connection);
            System.out.println("--------------");
            bankDao.subMoney(subAccount, money, connection);
            flag = 1;
            //不报错,提交事务
            connection.commit();

        } catch (Exception e) {

            //报错回滚事务
            connection.rollback();
            System.out.println("余额不足！");
            // throw e;

        } finally {

            // 利用封装类方法回收连接
            a8_v2_jdbcutils.releaseConnection();

        }

        if (flag == 1) {
            System.out.println("转账成功!");
        } else {
            System.out.println("转账失败!");
        }

    }

}
