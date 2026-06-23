package a1_JDBCBasics;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.JDBCType;
import java.util.Properties;

/**
 * ClassName: a1_JDBCBasics.a8_v1_jdbcutils
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/6 - 8:46
 * @Version: v1.0
 */
public class a8_v1_jdbcutils {

    /**
     * v1.0版本工具类
     *     内部包含一个连接池对象，并且对外提供获取连接和回收连接的方法
     * 建议
     *     工具类的方法写出静态，外部调用更方便
     * 实现
     *     属性 连接池对象 [实例化一次]
     * 方法
     *     对外提供连接的方法
     *     回收外部传入连接方法
     */
    private static DataSource dataSource = null ; //连接池对象

    static {

        // 初始化连接池对象
        Properties properties = new Properties();
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
        } catch ( IOException e) {
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

        return dataSource.getConnection();

    }

    // 回收外部连接的方法
    public static void releaseConnection(Connection connection) throws Exception {

        connection.close();

    }

    // 缺点：不能保证一个线程中所有的方法都使用同一个连接，必须指定传入一个参数保证是同一连接

}
