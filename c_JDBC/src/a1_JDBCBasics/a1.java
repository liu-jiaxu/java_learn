package a1_JDBCBasics;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * ClassName: a1_JDBCBasics.a1
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/1/30 - 10:58
 * @Version: v1.0
 */
public class a1 {

    /*
        一.JDBC
            1.Java Database Connectivity，Java连接数据库技术
                在Java中使用JDBC提供的方法，可以发送字符串类型的SQL语句到数据库管理软件，获取语句执行结果，
            进而实现数据库数据的增删改查(CURD)操作。
            2.jdbc由java语言规范(接口)和各个数据库实现的驱动(jar)组成
                java仅提供接口，规定数据库操作方法。标准的类库存在于java.sql，javax.sql包下
                数据库厂商根据java的jdbc接口实现具体的驱动代码，实现方法都相同
            3.jar包
                java程序打开成的一种压缩包形式，将jar导入项目后即可使用

        二.JDBC具体核心类及接口
            1.DriverManager
                将第三方数据库厂商的实现驱动jar注册到程序中
                可以根据数据库连接信息获取connection
            2.Connection
                和数据库建立的连接，在连接对象上，可以多次执行数据库crud操作
                可以获取Statement | PreparedStatement | CallableStatement对象
            3.Statement | PreparedStatement | CallableStatement
                Statement：用于静态SQL语句
                PreparedStatement：用于动态SQL语句，SQL语句中某个值不确定，需要传入数据
                CallableStatement：执行标准化存储过程
                具体发送SQL语句到数据库管理软件的对象
                不同对象有所不同，大多使用PreparedStatement
            4.ResultSet
                存储DQL查询数据库结果的对象，仅有查询才有返回结果，用ResultSet存储
                需要进行解析，获取具体的数据库数据

        三.JDBC核心API
            1.jar工程导入依赖
                [1]项目创建lib文件夹
                [2]导入驱动依赖jar包到文件夹
                [3]jar包右键添加为库(项目依赖，Add as Library)
            2.java与数据库交互过程
                [1]注册驱动依赖的jar包，进行安装
                [2]建立连接connection(相当于java与数据库的桥梁)
                [3]创建发送SQL语句的对象statement等(相当于数据的运输工具)
                [4]用statement等对象发送SQL语句从数据库获取返回结果，结果存储在result对象中(相当于用运输工具得到了数据)
                [5]解析结果集(相当于把得到的数据从包装中拆解出来获取)
                [6]释放资源，先释放connection，之后是statement等对象，最后是resultSet
    */


    // 例1：完整jdbc使用步骤示例
    // API：DriverManager  Connection  Statement | PreparedStatement | CallableStatement  ResultSet
    public static void main(String[] args) throws SQLException {

        // 1.注册驱动
            // 依赖：驱动版本8+ com.mysql.cj.jdbc.Driver
            //      驱动版本5+ com.mysql.jdbc.Driver
            DriverManager.registerDriver(new Driver());

        // 2.获取连接
            // java程序连接数据库，调用某个方法并填入数据库的基本信息
            //     数据库ip地址：127.0.0.1
            //     数据库端口号：3306
            //     账号&密码：root zgh2960425
            //     连接数据库的名称：atguigu

            // 参数设置
            // url："jdbc:数据库厂商名://ip地址:port/数据库名"
            // username：账号
            // password：密码
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1:3306/atguigu","root","zgh2960425");
                        // java.sql 接口 = 实现类
           /* Connection connection2 = DriverManager.getConnection
                    ("jdbc:mysql://192.168.119.130:3306/atguigu","root","zgh2960425");*/
                        // 可以连接虚拟机的数据库...

        // 3.创建statement对象
            Statement statement = connection.createStatement();

        // 4.发送SQL语句，并获取返回结果
            String sql = "select * from t_user";
            ResultSet resultSet = statement.executeQuery(sql);

        // 5.进行结果集解析
            while (resultSet.next()) {
                // next()方法返回值:true如果新的当前行有效; false如果没有更多行
                //     将光标从其当前位置向前移动一行。 ResultSet游标最初位于第一行之前;对该方法next的第一次调用使第一行成为当前行;
                // 第二次调用使第二行成为当前行，依此类推。当对方法的next调用返回false时，光标位于最后一行之后。对需要当前行的方法的任
                // 何ResultSet调用都将导致SQLException抛出。
                int id = resultSet.getInt("id");
                String account = resultSet.getString("account");
                String password = resultSet.getString("password");
                String nickname = resultSet.getString("nickname");
                System.out.println(id+"--"+account+"--"+password+"--"+nickname);
            }

        // 6.关闭资源
            resultSet.close();
            statement.close();
            connection.close(); // 从内往外关闭

        }

}
