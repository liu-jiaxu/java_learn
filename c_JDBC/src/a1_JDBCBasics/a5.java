package a1_JDBCBasics;

import org.junit.jupiter.api.Test;

import java.sql.*;

import java.util.Random;

/**
 * ClassName: a1_JDBCBasics.a5
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/5 - 10:58
 * @Version: v1.0
 */
public class a5 {

    // JDBC拓展

    // 获取随机字符串以生成随机姓名等信息
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(52);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 1.自增主键回显实现
     * java程序获取插入数据时自增主键的值
     * <p>
     * 使用方法：
     * 1.创建preparedStatement时，携带回数据库自增的主键Statement.RETURN_GENERATED_KEYS
     * 2.获取装主键值的结果集对象，一行一列获取对应的数据即可
     */
    @Test
    public void test1() throws ClassNotFoundException, SQLException {

        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1:3306/atguigu?user=root&password=zgh2960425");

        // 3.编写SQL语句
        String sql = "insert into t_user(account,password,nickname) values (?,?,?);";

        // 4.创建preparedStatement并传入SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // 携带回数据库自增的主键

        // 5.占位符赋值
        preparedStatement.setString(1, "lsy");
        preparedStatement.setString(2, "lsy123");
        preparedStatement.setString(3, "刘思瑶");

        // 6.发送SQL语句，并获取结果
        int i = preparedStatement.executeUpdate();

        // 7.结果集解析
        if (i > 0) {
            System.out.println("succeed!");

            // 问题：在insert插入数据之前若delete删除记录，则会出现主键id自增不连续的问题，jdbc无法解决！
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            // 检索由于执行此 Statement 对象而创建的任何自动生成的密钥。
            resultSet.next();
            // 游标下移
            System.out.println(resultSet.getInt(1));
            // 检索此 ResultSet 对象当前行中指定列的值

        } else {
            System.out.println("false!");
        }

        // 8.关闭连接
        preparedStatement.close();
        connection.close();

    }

    /**
     * 2.批量数据插入性能提升
     * 总结问题;
     * [1]url路径后加上rewriteBatchedStatements=true允许批量插入
     * [2]insert语句必须用values，后面不能加“;”
     * [3]不是单次执行每条插入语句，是批量添加addBatch()
     * [4]遍历添加完成后，统一批量执行executeBatch()
     */
    @Test
    public void test2() throws ClassNotFoundException, SQLException {

        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1:3306/atguigu?rewriteBatchedStatements=true", "root", "zgh2960425");
        // rewriteBatchedStatements=true：表示允许MySQL支持批量插入

        // 3.编写SQL语句
        String sql = "insert into t_user(account,password,nickname) values (?,?,?)";
        // 注：此处不要在SQL语句后加“;”，因为要插入多条记录，加入“;”后仅会插入一条记录就停止了！

        // 4.创建preparedStatement并传入SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 5.占位符赋值

        // 生成1000条随机数据并存储到数组中
        String[] str = new String[1000];
        for (int i = 0; i < 1000; i++) {
            str[i] = getRandomString(10);
        }

        long start = System.currentTimeMillis(); // 记录执行时间

        // 单次插入1000条数据
        /*for (int i = 0; i < 1000; i++) {

            preparedStatement.setObject(1,  str[i]);
            // getRandomString(length)：随机生成长度为10的字符串
            preparedStatement.setObject(2,   str[i] + "123");
            preparedStatement.setObject(3,  str[i]);

            preparedStatement.executeUpdate(); // 单次插入

        }*/

        // 批量插入1000条数据
        for (int i = 0; i < 1000; i++) {

            preparedStatement.setObject(1, str[i]);
            // getRandomString(length)：随机生成长度为10的字符串
            preparedStatement.setObject(2, str[i] + "123");
            preparedStatement.setObject(3, str[i]);

            preparedStatement.addBatch(); // 单次插入不执行，追加到values后面

        }

        // 6.发送SQL语句，并获取结果
        preparedStatement.executeBatch(); // 执行批量操作

        long end = System.currentTimeMillis();

        // 7.结果集解析
        System.out.println("time=" + (end - start) + "ms"); // 31ms，时间比单次插入快5-6倍！
        // 8.关闭连接
        preparedStatement.close();
        connection.close();

    }

}
