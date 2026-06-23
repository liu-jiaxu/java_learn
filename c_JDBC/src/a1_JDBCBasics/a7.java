package a1_JDBCBasics;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: a1_JDBCBasics.a7
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/2/5 - 21:16
 * @Version: v1.0
 */
public class a7 {

    // Druid连接池技术使用

    /*
      数据库连接池作用
          1.不使用连接池时
              每次都要通过DriverManager获取新连接，用完后直接关闭，利用率低
              数据库服务器与java程序连接数无法控制，数据库服务器易崩溃
          2.使用连接池
              建立连接池，池中可容纳一定数量连接对象，一开始创建一些连接对象放入连接池，使用时直接从池中获取，不用新建，节省时间，
          用完之后再放回连接池，其它用户可以继续使用。若连接池连接使用完，则连接池可以向服务器申请创建新连接，直至达到最大连接数，
          此时用户只能等待其它用户使用完连接放回连接池。
     */

    /**
     * JDBC的数据库连接池使用
     *     javax.sql.DataSource接口进行规范，所有的第三方连接池都实现此接口，自行添加具体实现！
     * 也就是说，所有连接池获取连接和回收连接方法都一样，不同的只有性能和扩展功能！
     *
     *     DBCP    是Apache提供的数据库连接池，速度相对c3p0较快，但自身存在BUG
     *     C3PD    是一个开源组织提供的一个数据库连接池,速度相对较慢,稳定性还可以
     *     Proxool 是sourceforge下的一个开源项目数据库连接池,有监控连被池状态的功能.稳定性较c3p0目差一点
     *     Druid   是阿里提供的数据库连接池,是集DBCP 、C3P0、ProxooL优点于一身的数据库连接池(牛啊牛啊)
     */

    // 硬编码：直接使用代码设置连接池连接参数方式(编写后无法外部修改，不建议使用)
    @Test
    public void test1() throws SQLException {

        // 1.创建一个druid连接池对象
        DruidDataSource druidDataSource = new DruidDataSource();

        // 2.设置连接池参数[ 必须 | 非必须 ]
        /*
          设置参数
              必须：连接数据库的全限定符
                  druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/atguigu");
                  druidDataSource.setUsername("root");
                  druidDataSource.setPassword("zgh2960425");
                  druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
              非必须：初始化连接数量、最大连接数量...
                  druidDataSource.setInitialSize(5); 初始化连接数量
                  druidDataSource.setMaxActive(10); 最大连接数量
         */
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/atguigu");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("zgh2960425");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // 3.获取连接 [ 通用方法，所有连接池一样 ]
        Connection connection = druidDataSource.getConnection();

        // 4.回收连接 [ 通用方法，所有连接池一样 ]
        connection.close(); // 连接池提供的连接会优化，从关闭连接变为回收连接

    }

    // 软编码：通过读取外部配置文件的方法，实例化druid连接池对象
    @Test
    public void test2() throws Exception {

        // 1.读取外部配置文件properties
        Properties properties = new Properties();

        // src下的文件，使用类加载器提供的方法
        InputStream inputStream = DruidDataSource.class.getClassLoader().getResourceAsStream("druid.properties");

        properties.load(inputStream);

        // 2.使用连接池的工具类的工程模式，创建连接池(记住就行吧...)
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();

        connection.close();
        if (inputStream != null) {
            inputStream.close();
        }

    }

    // 软编码示例
    @Test
    public void test3() throws Exception {

        // 1.读取外部配置文件properties
        Properties properties = new Properties();

        // src下的文件，使用类加载器提供的方法
        InputStream inputStream = DruidDataSource.class.getClassLoader().getResourceAsStream("druid.properties");

        properties.load(inputStream);

        // 2.使用连接池的工具类的工程模式，创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();

        String sql = "select * from t_bank;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String money = resultSet.getString("money");
            System.out.println(id+"--"+account+"--"+money);
        }

        connection.close();
        if (inputStream != null) {
            inputStream.close();
        }

    }

}
