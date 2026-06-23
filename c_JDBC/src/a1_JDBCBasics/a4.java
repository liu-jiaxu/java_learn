package a1_JDBCBasics;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: a1_JDBCBasics.a4
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/5 - 9:32
 * @Version: v1.0
 */
public class a4 {

    // preparedStatement的curd

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {

        /*
          t_user插入一条数据
              account  lsy
              password  lsy123
              nickname  刘思瑶
         */

        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1:3306/atguigu","root","zgh2960425");

        // 3.编写SQL语句结果，动态值的部分使用?代替
        String sql = "insert into t_user(account,password,nickname) values (?,?,?);";

        // 4.创建preparedStatement，并传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 5.占位符赋值
        preparedStatement.setObject(1, "lsy");
        preparedStatement.setObject(2, "lsy123");
        preparedStatement.setObject(3, "刘思瑶");

        // 6.发送SQL语句
        int row = preparedStatement.executeUpdate();

        // 7.输出结果
        if (row > 0) {
            System.out.println("数据插入成功！");
        } else {
            System.out.println("数据插入失败！");
        }

        // 8.关闭资源
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {

        /*
          t_user更新一条数据
              修改password = lsy123456
         */

        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1:3306/atguigu","root","zgh2960425");

        // 3.编写SQL语句结果，动态值的部分使用?代替
        String sql = "update t_user set password = ? where nickname = ? ;";

        // 4.创建preparedStatement，并传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 5.占位符赋值
        preparedStatement.setObject(1, "lsy123456");
        preparedStatement.setObject(2, "刘思瑶");

        // 6.发送SQL语句
        int row = preparedStatement.executeUpdate();

        // 7.输出结果
        if (row > 0) {
            System.out.println("数据更新成功！");
        } else {
            System.out.println("数据更新失败！");
        }

        // 8.关闭资源
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException  {

        /*
          删除nickname = 刘思瑶的一条数据
         */

        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1:3306/atguigu","root","zgh2960425");

        // 3.编写SQL语句结果，动态值的部分使用?代替
        String sql = "delete from t_user where nickname = ?;";

        // 4.创建preparedStatement，并传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 5.占位符赋值
        preparedStatement.setObject(1,"刘思瑶");

        // 6.发送SQL语句
        int row = preparedStatement.executeUpdate();

        // 7.输出结果
        if (row > 0) {
            System.out.println("数据删除成功！");
        } else {
            System.out.println("数据删除失败！");
        }

        // 8.关闭资源
        preparedStatement.close();
        connection.close();

    }

    /**
     * 目标：查询所有用户数据，并且封装到一个List<Map> list中
     *     数据库 -> resultSet -> java -> 一行 map(key=列名,value=列信息) -> List<Map> list
     *     实现思路：
     *         1.遍历行数据，一行对应一个Map，获取一行的列名和对应的列的属性，装配即可！
     *         2.将map装到一个集合就可以了
     */
    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {

        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取连接
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://127.0.0.1:3306/atguigu?user=root&password=zgh2960425");

        // 3.编写SQL语句结果，动态值的部分使用?代替
        String sql = "select * from t_user;";

        // 4.创建preparedStatement，并传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 5.占位符赋值
        // 省略

        // 6.发送SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 7.结果集解析
        List<Map<String,Object>> list = new ArrayList<>(); // 创建集合

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 检索此ResultSet对象的列的数量、类型和属性。
            // resultSetMetaData装的是当前结果集列的信息对象(通过下角标获取列的名称和列的数量)

        int columnCount = resultSetMetaData.getColumnCount();
            // 返回此ResultSet对象中的列数

        // 二维数组遍历
        while (resultSet.next()) { // next()移动光标
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {

                Object value = resultSet.getObject(i);
                    // 获取此 ResultSet 对象的当前行中指定列的值，作为 Java 编程语言中的值 Object 。

                String columnLabel = resultSetMetaData.getColumnLabel(i);
                    // 获取指定列的建议标题，以便在打印输出和显示中使用。建议的标题通常由 SQL AS 子句指定。如果未指定 SQL AS ，
                    // 则返回的值 getColumnLabel 将与该方法返回 getColumnName 的值相同。
                    //形参: column – 第一列是 1，第二列是 2，...
                    //返回值:建议的列标题

                map.put(columnLabel, value); // 将列和列值添加到map集合中

            }

            list.add(map);

        }

        System.out.println("list = " + list);

        // 8.关闭资源
        preparedStatement.close();
        connection.close();

    }

    /*
      使用动态API总结：
          1.注册驱动
          Class.forName("com.mysql.cj.jdbc.Driver");

          2.获取连接
          Connection connection = DriverManager.getConnection
              ("jdbc:mysql://127.0.0.1:3306/atguigu?user=root&password=zgh2960425");

          3.编写SQL语句
          String sql = "...";

          4.创建preparedStatement并传入SQL语句
          PreparedStatement preparedStatement = connection.prepareStatement(sql);

          5.占位符赋值
          preparedStatement.setObject(1,...);
          preparedStatement.setObject(2,...);

          6.发送SQL语句，并获取结果
          ResultSet resultSet = preparedStatement.executeQuery(); 或 int row = preparedStatement.executeUpdate();

          7.结果集解析
              移动光标指向行数据：if(next()) while(next())
              获取列的数据：int id = resultSet.getInt("id");
                          String account = resultSet.getString("account");
              获取列的信息：
                  ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                      // 检索此ResultSet对象的列的数量、类型和属性。
                  int columnCount = resultSetMetaData.getColumnCount();
                      // 返回此ResultSet对象中的列数
                  Object value = resultSet.getObject(i);
                     // 获取此 ResultSet 对象的当前行中指定列的值，作为 Java 编程语言中的值 Object 。
                  String columnLabel = resultSetMetaData.getColumnLabel(i);
                     // 获取指定列的建议标题

          8.关闭连接
          preparedStatement.close();
          connection.close();

     */

}
