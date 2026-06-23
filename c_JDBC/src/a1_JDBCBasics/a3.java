package a1_JDBCBasics;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName: a1_JDBCBasics.a3
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/1/30 - 22:04
 * @Version: v1.0
 */
public class a3 {

    // 例3：使用PreparedStatement模拟登录

    /**
     * 使用preparedStatement步骤
     * 1.注册驱动
     * 2.获取连接
     * 3.编写SQL语句
     * 4.创建prepareStatement并传入SQL语句
     * 5.占位符赋值
     * 6.发送SQL语句，并获取结果
     * 7.结果集解析
     * 8.关闭连接
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1.键盘输入事件，收集账号与密码信息
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入账号：");
        String account = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        // 2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 3.获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/atguigu?user=root&password=zgh2960425");

        // 4.编写SQL语句结果
        String sql = "select * from t_user where account = ? and password = ?;";

        // 5.创建preparedStatement并且设置sql语句
        /*
          statement
              1.创建statement
              2.拼接sql语句
              3.发送SQL语句，并且返回结果
          preparedStatement
              1.编写sql语句结果，动态值部分的语句使用占位符 ? 替代
              2.创建preparedStatement，并传入动态值
              3.动态值占位符?单独赋值
         */
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 6.单独的占位符赋值
        /*
          占位符参数使用
              1.index：占位符的位置从左往右数(从1开始)
              2.object：占位符的值，可设置任何类型的数据
         */
        preparedStatement.setObject(1, account);
        // 表示给第一个占位符赋值参数account
        preparedStatement.setObject(2, password);

        // 7.发送SQL语句，并获取返回结果
        ResultSet resultSet = preparedStatement.executeQuery();

        // 8.结果集解析
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        // 9.关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

}
