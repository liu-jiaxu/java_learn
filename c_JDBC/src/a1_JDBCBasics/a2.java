package a1_JDBCBasics;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName: a1_JDBCBasics.a2
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/1/30 - 11:01
 * @Version: v1.0
 */
public class a2 {

    // 例2：模拟登录，该数据库中有root用户(密码123456)和admin用户(密码666666)，控制台输入账号和密码，判断是否登录成功
            // [1]明确jdbc使用流程和内部设计api方法
            // [2]具体使用preparedStatement
    public static void main(String[] args)  throws SQLException, ClassNotFoundException {

            // 1.键盘输入事件，收集账号与密码信息
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入账号：");
            String account = scanner.nextLine();
            System.out.print("请输入密码：");
            String password = scanner.nextLine();

            // 2.注册驱动
            /*
              方案1：
                  DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver())
                  注意：驱动版本8+ com.mysql.cj.jdbc.Driver
                       驱动版本5+ com.mysql.jdbc.Driver
                  问题：注册两次驱动
                      1.DriverManager.registerDriver() 方法本身注册一次
                      2.Driver.static{ DriverManager.registerDriver() } 静态代码块注册一次
                  解决：只注册一次代码
                      只触发静态代码块Driver
                  触发静态代码块：
                      类加载机制：类加载的时刻，会触发静态代码块
                               加载 [ class文件 -> jvm虚拟机的class对象 ]
                               连接 [ 验证(检查文件类型) -> 准备(静态变量默认值) -> 解析(触发静态代码块) ]
                               初始化(静态属性赋真实值)
                  触发类加载：
                      1.new关键字
                      2.调用静态方法
                      3.调用静态属性
                      4.接口1.8default默认实现
                      5.反射
                      6.子类触发父类
                      7.程序的入口main
             */
            // DriverManager.registerDriver(new Driver());

            // 方案2：固定写法，直接使用对应版本驱动，但更换数据库时还要查找更改对应代码
            // new Driver();

            // 方案3：反射。字符串的Driver权限字符，可以引导外部配置文件xxx.properties，更改数据库后只需修改外部文件即可
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 3.获取连接
            /*
              getConnection(1,2,3)方法，是一个重载方法
              允许开发者用不同形式传入数据库连接的核心参数，最终结果相同
              核心属性：
                  1.数据库软件所在主机的ip地址：localhost | 127.0.0.1(不是主机地址则另外改！)
                  2.数据库软件所在主机的端口号：3306(可能自己改变)
                  3.连接的具体数据库：atguigu
                  4.本次连接的账号和密码：root  zgh2960425
                  5.可选的信息
              三个参数：
                  String url       数据库软件所在的信息，连接的数据库，以及其它可选信息
                                   语法：jdbc:数据库管理软件名称[mysql|oracle|...]://ip地址(或主机名):端口号/数据库名?key = value
                                   具体示例：jdbc:mysql://127.0.0.1:3306/atguigu
                                           jdbc:mysql://localhost:3306/atguigu
                                   补充：若数据库软件安装在本机，则可以省略ip地址和端口号3306(端口号只有是3306才可以省略！)
                  String user      数据库的账号
                  String password  数据库的密码
              二个参数：
                  String url       与三个参数的url相同
                  Properties info  存储账号和密码
                                   Properties相当于Map的key-value结构，只不过都是字符串形式的
                                   要使用必须传入参数info，分别put方法设置user和password的值
              一个参数：
                  String url  数据库ip，端口号，具体数据库，可选信息(账号密码...)
                              语法：jdbc:数据库软件名://ip:port/数据库?key=value&key=value&key=value...
                              具体示例：jdbc:mysql://localhost:3306/atguigu?user=root&password=zgh2960425
              url的可选信息：
                  serverTimezone=Asia/Shanghai  8.0.25之前要添加，之后版本会自动识别时区不用添加了
                  useUnicode=true&characterEncoding=utf8&useSSL=true  8版本之后编码格式默认为utf8不用设置
             */

            // 三个参数：
            /*Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1:3306/atguigu","root","zgh2960425");*/
            // 二个参数：
            /*Properties info = new Properties();
            info.put("user","root");
            info.put("password","zgh2960425");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1:3306/atguigu",info);*/
            // 一个参数：
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/atguigu?user=root&password=zgh2960425");

            // 4.创建statement
            Statement statement = connection.createStatement();

            // 5.发送查询SQL语句，并获取返回结果
            String sql = "select * from t_user where account = '" + account + "' and password = '" + password + "';";
                // 注：此处存在注入攻击，若输入参数为true时，则where语句永久为真，此时则视为登录成功！

            /*
              SQL分类：DDL(容器创建，修改，删除)，DML(增删改)，DQL(查询)，DCL(权限控制)，TCL(事务控制)
              1.statement.executeUpdate()
                  参数：sql，非DML
                  返回：int
                      情况1：DML。返回影响的行数(例：删除3条数据->return 3;修改1条记录->return 1;)
                      情况2：非DML。return 0
              2.statement.executeQuery()
                  参数：sql，DQL
                  返回：resultSet结果封装对象
                  ResultSet resultSet = statement.executeQuery(sql)
             */
            // int i = statement.executeUpdate(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            // 6.结束判断，显示登录成功还是失败
            /*
              Java是一种面向对象的思维，将查询结果封装成了resultSet对象，其内部一定也是有行和列的，和SQLyog数据一样
              resultSet -> 逐行读取数据， 行 -> 行的列的数据
              数据解析：
                  1.移动游标指定获取数据行
                      游标移动问题：
                              resultSet内部包含一个游标，默认指向第一行之前，调用next()方法后游标向后移动一行，
                          当有很多行数据时，使用while(ResultSet.next())获取每一行数据，next()返回true表示下
                          方还有数据，要向下移动光标；返回false表示下面没有数据，不移动了
                              移动光标的方法有很多，但只需要记住next()方法就行了，要获取对应数据，只需要修改SQL
                          语句while条件即可！
                  2.获取指定数据行的列的数据即可
                      获取列数据问题：
                          resultSet.get类型(String columnLable | int columnIndex);
                              columnLable：列名，若有别名写别名
                              columnIndex：列的下角标，从左往右，从1开始
             */

           // 7.结果集解析
           /* while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String account2 = resultSet.getString("account");
                String password2 = resultSet.getString("password");
                String nickname = resultSet.getString("nickname");
                System.out.println(id+"--"+account2+"--"+password2+"--"+nickname);
            }*/
            // 对于要求，仅需要判断光标是否移动就可以判断是否登录成功
            if (resultSet.next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }

            // 8.关闭资源
            resultSet.close();
            statement.close();
            connection.close();

    }

    /*statement问题：
        1.SQL语句需要字符串拼接，比较麻烦
        2.只能拼接字符串类型，其他的数据库类型无法处理
        3.可能发生注入攻击(sql参数的动态值影响查询结果)*/

}
