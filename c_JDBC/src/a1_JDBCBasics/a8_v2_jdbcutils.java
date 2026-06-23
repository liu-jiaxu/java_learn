package a1_JDBCBasics;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * ClassName: a1_JDBCBasics.a8_v2_jdbcutils
 * Package: PACKAGE_NAME
 * Description:注意这个好用！以后的连接调用即可！
 *
 * @Author: zgh296
 * @Create: 2023/2/6 - 9:29
 * @Version: v1.0
 */
public class a8_v2_jdbcutils {

    /**
     * v2.0版本工具类
     *     1.JDK1.2提供java.lang.ThreadLocal，用于在多线程中共享数据库连接
     *     2.ThreadLocal用于保存某个线程共享变量，线程之间操作不会相互影响
     *         包含get、set、remove方法
     *     3.优势
     *         [1]利用本地变量存储连接信息，确保一个线程的多个方法可以获取同一个connection
     *         [2]事务操作时多个方法属于同一线程，不用再传递connection参数了，调用v2中的getConnection方法获取的是同一连接！
     */
    private static DataSource dataSource = null ; //连接池对象

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>() ; //线程本地变量

    static {

        // 初始化连接池对象
        Properties properties = new Properties();
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // 对外提供连接的方法
    public static Connection getConnection() throws Exception {

        // 先查看线程本地变量是否存在
        Connection connection = threadLocal.get();
        if (connection == null) { // 若没有本地变量
            connection = dataSource.getConnection(); // 创建一个本地变量
            threadLocal.set(connection); // 之后通过set方法设置本地变量
        }
        return connection; // 返回此变量
    }

    // 回收外部连接的方法
    public static void releaseConnection() throws Exception {

        Connection connection = threadLocal.get(); // 此时get方法获取的是ThreadLocal中的同一连接
        if (connection != null) {
            threadLocal.remove(); // 清空本地变量数据
            connection.setAutoCommit(true); // 回归事务默认状态
            connection.close(); // 回收连接
        }

    }

}
