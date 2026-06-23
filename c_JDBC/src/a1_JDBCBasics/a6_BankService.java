package a1_JDBCBasics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class a6_BankService {

    /*
      总结：
          1.一个事务必须保证用同一个连接
          2.利用try代码块开启事务
          3.在service中调用dao方法
     */
    /**
     * 转账业务方法
     * @param addAccount  加钱账号
     * @param subAccount  减钱账号
     * @param money  金额
     */
    public void transfer(String addAccount,String subAccount, int money) throws ClassNotFoundException, SQLException {

        System.out.println("addAccount = " + addAccount + ", subAccount = " + subAccount + ", money = " + money);

        // 一个事务必须保证是同一个连接对象connection
        // 此转账事务包含加钱减钱两个方法，即一个事务包含两个SQL语句，必须要用同一连接

        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql:///atguigu", "root", "zgh2960425");

        // flag参数判断转账成功与否
        int flag = 0;

        //利用try代码块,调用dao
        try {

            //开启事务(关闭事务自动提交)
            connection.setAutoCommit(false);

            a6_BankDao bankDao = new a6_BankDao();
            //调用加钱和减钱
            bankDao.addMoney(addAccount,money,connection);
            System.out.println("--------------");
            bankDao.subMoney(subAccount,money,connection);
            flag = 1;
            //不报错,提交事务
            connection.commit();

        } catch (Exception e) {

            //报错回滚事务
            connection.rollback();
            System.out.println("余额不足！");
            // throw e;

        } finally {

            connection.close();

        }

        if (flag == 1) {
            System.out.println("转账成功!");
        } else {
            System.out.println("转账失败!");
        }

    }

}
