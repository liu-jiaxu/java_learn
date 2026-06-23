package a1_JDBCBasics;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: a1_JDBCBasics.a9
 * Package: PACKAGE_NAME
 * Description:这个类也是直接继承调用...
 *
 * @Author: zgh296
 * @Create: 2023/2/6 - 10:04
 * @Version: v1.0
 */
public class a9 {

    /*
      普通jdbc连接步骤
          1.注册驱动
          2.获取连接
          3.编写SQL语句
          4.创建Statement并传入SQL语句
          5.占位符赋值
          6.发送SQL语句，并获取结果
          7.结果集解析
          8.关闭连接
      封装类简写步骤1,2,8
      高级应用层封装BaseDao：封装剩余步骤
           封装数据库重复代码：一个查询方法(DQL)
                            一个增删改方法(非DQL)
     */

    /**
     * 封装简化非DQL语句
     * @param sql：带占位符的SQL语句
     * @param params：占位符的值。注：传入的占位符顺序必须与SQL语句?位置一致！
     * @return int型执行影响的行数
     */
    public int executeUpdate(String sql, Object... params) throws Exception {

        // 获取连接
        Connection connection = a8_v2_jdbcutils.getConnection(); // 注：修改类名后记得改这里！

        // 创建Statement并传入SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 占位符赋值
        // 可变参数当数组使用
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        // 发送SQL语句
        int row = preparedStatement.executeUpdate();

        // 回收连接
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            a8_v2_jdbcutils.releaseConnection();
        }

        return row;
    }

    /**
     * 封装简化DQL语句
     * @param sql：带占位符的SQL语句
     * @param params：占位符的值。注：传入的占位符顺序必须与SQL语句?位置一致！
     * @param clazz：要接值的实体类集合的模板对象。声明一个泛型，不确定类型
     *                 1.确定泛型User.class T = a1_JDBCBasics.User
     *                 2.使用反射技术属性赋值
     * @param <T>：声明的结果的泛型
     * @return list：集合存放数据
     */
    public <T> List<T> executeSelect(Class<T> clazz, String sql, Object... params) throws Exception {

        // 获取连接
        Connection connection = a8_v2_jdbcutils.getConnection();

        // 创建preparedStatement，并传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 占位符赋值
        if (params != null && params.length != 0) {
            for(int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }

        // 发送SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 结果集解析
        List<T> list = new ArrayList<>(); // 创建集合

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 检索此ResultSet对象的列的数量、类型和属性。
            // resultSetMetaData装的是当前结果集列的信息对象(通过下角标获取列的名称和列的数量)

        int columnCount = resultSetMetaData.getColumnCount();
            // 返回此ResultSet对象中的列数

        // 二维数组遍历，先行后列。
        // 先取第一行，然后遍历每一列，取出每一列的值并按顺序用数字给每列命名，
        while (resultSet.next()) { // next()移动光标

            T t = clazz.newInstance(); // 调用类的无参构造函数实例化对象

            // 从1开始遍历，注：小于等于总列数
            for (int i = 1; i <= columnCount; i++) {

                // 对象的属性值
                Object value = resultSet.getObject(i);

                // 获取指定列的下角标的列的名称
                String columnName = resultSetMetaData.getColumnLabel(i);

                // 反射，给对象的属性值赋值
                Field field = clazz.getDeclaredField(columnName); // 获取类中名为columnName的属性
                field.setAccessible(true); // 属性可以设置，打破private修饰限制

                /*
                  参数1：要赋值的对象 若属性为静态，第一个参数可以为null！
                  参数2：具体的属性值
                 */
                field.set(t, value); // 指定当前列对象t的columnName属性值为value
                    // 注意：此处的set方法不接受数据库和java数据类型不同，若两者存在属性的数据类型不同(或数据类型转换不一致等)，
                // 则set方法会跳过不同数据类型属性的设置，从而引起所设置的参数为null！
                    // 例：MySQL的money类型为非负int型，但java没有对应的类型可以表示非负，则set()方法会跳过money属性值的设置，
                // 从而使java中的money参数为null，导致程序报错java.lang.IllegalArgumentException

            }

            list.add(t);

        }

        // 回收连接
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()) { // 若没有事务
            a8_v2_jdbcutils.releaseConnection();
        }

        return list;

    }

}
