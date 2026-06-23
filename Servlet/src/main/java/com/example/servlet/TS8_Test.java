package com.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import org.junit.Test;

import jakarta.servlet.http.HttpServlet;

/**
 * ClassName: TS8_Test
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/5 - 20:50
 * @Version: v1.0
 */

/**
 * 案例：用户登录
 *     1.编写TS8_Test_login.html登录页面
 *         username & password两个输入框，登录一个提交按钮
 *     2.使用Druid数据库连接池技术,操作mysql, TS8_Test_msg数据库中user表
 *     3.使用JdbcTemplate技术封装JDBC
 *     4,登录成功跳转到SuccessServlet展示:登录成功!用户名,欢迎您
 *     5·登录失败跳转到FailServlet展示:登录失败,用户名或密码错误
 */

/**
 * 过程
 *     1.LoginServlet登录类
 *         (1)设置编码
 *         (2)获取username和password
 *         (3)将username和password封装为一个User对象
 *         (4)调用UserDao的login方法查询，获取返回值User对象
 *         (5)判断User是否为null
 *             是：登录失败 -> FailServlet
 *             否：登录成功 -> 将用户信息存放在request域，转发SuccessServlet
 *     2.UserDao类
 *         public User login(User user){
 *             select * from user where username =? and password =?
 *         }
 */

/**
 * 步骤：
 *     1.创建项目，创建html页面1，配置文件，导入jar包
 *     2.创建数据库环境
 *     3.创建具体类：用户实体类TS8_Test_User
 *                 登录类TS8_Test_UserDao，login方法
 *                 连接数据库类TS8_Test_JDBCUtils
 *                 Servlet类TS8_Test_LoginServlet
 *                 成功失败类
 *     4.TS8_Test_login.html中from表单的action路径写法
 *         虚拟目录+Servlet的资源路径
 *     5.BeanUtils工具类，简化数据封装
 *         JavaBean：标准的Java类，用于封装数据
 *             (1)类必须被public修饰
 *             (2)必须提供空参的构造器
 *             (3)成员变量必须使用private修饰
 *             (4)提供公共setter和getter方法
 *         概念
 *             (1)成员变量：类中定义的变量
 *             (2)属性：setter和getter方法截取后的产物
 *                 当get、set方法与成员变量不同名时两者不同，其余时候两者无差别
 *         方法
 *             (1)setProperty()
 *             (2)getProperty()
 *             (3)populate()
 */

/**
 * 总结注意事项；
 *     1.注意连接语句，要随着jar包版本的不同而变换
 *         驱动版本8+ com.mysql.cj.jdbc.Driver
 *         驱动版本5+ com.mysql.jdbc.Driver
 *     2.注意properties文件中的数据库名称要正确，否则一直循环报错未知数据库
 *     3.注意maven项目的properties文件要放在resources文件目录下，与常规模块路径不一致！
 *     4.注意类之间的调用和引用名字不要写错
 *     5.在TS8_Test_UserDao.java中调用queryForObject方法可能返回空值出现异常，需要用try-catch处理
 */

@WebServlet("/ts8")
public class TS8_Test extends HttpServlet {

    // 其它见相关TS8类

    // 测试类
    @Test
    public void test() {

        TS8_Test_User loginUser = new TS8_Test_User();
        loginUser.setUsername("superbaby");
        loginUser.setPassword("123");

        TS8_Test_UserDao dao = new TS8_Test_UserDao();
        TS8_Test_User user = dao.login(loginUser);

        System.out.println(user);

    }

}
