package com.example.servlet;

// 妈的，这包名真特么烦啊...
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: TS4_Test
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/4 - 16:13
 * @Version: v1.0
 */

/**
 * 一.Servlet体系结构
 *     Servlet接口
 *          |(子类)
 *          |
 *         GenericServlet抽象类
 *              |(子类)
 *              |
 *             HttpServlet抽象类
 *     1.GenericServlet抽象类将Servlet接口中除service方法均做了空实现，仅对service()方法进行抽象
 *         之后仅定义Servlet类时，可以继承GenericServlet抽象类，仅实现service()方法即可
 *     2.实际工作用HttpServlet抽象类。HttpServlet是对http协议的一种封装，简化操作
 *         步骤：(1)定义类继承HttpServlet
 *              (2)复写doGet/doPost方法
 *
 * 二.HTTP
 *     1.概念：Hyper Text Transfer Protocol 超文本传输协议
 *         传输协议：定义了客户端与服务器端通信时，发送数据的格式
 *             客户端 -> 请求消息 -> 服务器端
 *             服务器端 -> 响应消息 -> 客户端
 *     2.HTTP特点：
 *         (1)基于TCP/IP的高级协议
 *         (2)默认端口号80
 *         (3)基于请求响应模型：一次请求对应一次响应
 *             补：访问一个网页时，会有多个请求和响应，一个jpg、png、mp4、css等文件都会进行一次请求响应
 *         (4)无状态的：每次请求之间相互独立，之间不能交互
 *     3.HTTP历史版本1
 *         (1)1.0：每次请求和响应都会建立一次连接
 *         (2)1.1：首次建立连接后会复用连接直至所有请求响应结束
 *     4.HTTP请求消息数据格式
 *         (1)请求行
 *             请求方式              请求URL                 请求协议/版本
 *             GET/POST   对应url(例：TS4_Test_login.html)    HTTP/1.1
 *             [1]请求方式：http有7种请求方式，常用get和post
 *                 {1}GET：
 *                     请求参数在请求行中，在url后
 *                     请求的url长度有限制
 *                     不安全
 *                 {2}POST：
 *                     请求参数在请求体中
 *                     请求的url长度无限制
 *                     相对安全
 *         (2)请求头
 *             请求头名称:请求头值
 *             [1]常见请求头
 *                 {1}User-Agent：浏览器告知服务器，访问时使用的浏览器版本信息，可以在服务器端获取该头信息，解决浏览器兼容问题
 *                 {2}Accept：可以接收的文件格式
 *                 {3}Accept-Language/Accept-Encoding：可以接收的语言和压缩格式
 *                 {4}Referer：告知服务器当前请求从何处来。
 *                     作用：防止盗链(盗取超链接：别的盗版网站盗取正版超链接，为了防止盗链出现了referer，每次打开连接都会进行
 *                                 referer匹配判断，只有匹配才可以打开超链接)
 *                          统计工作：统计各个网站访问超链接的次数
 *                 {5}Connection：1.1版本为keep-alive存活，表示连接复用
 *                 {6}Ugprade：升级信息
 *         (3)请求空行
 *             空行，用于分隔请求头和请求体
 *         (4)请求体(请求正文)
 *             例：username=111
 *             作用：封装POST请求消息的请求参数
 */

// 测试HttpServlet抽象类
@WebServlet("/ts4")
public class TS4_Test extends HttpServlet {

        // HttpServlet类共有七种请求方式，最常用的是doGet和doPost
        // 根据实际需求重写doGet和doPost方法
        // 从浏览器打开时为get方法，表单提交可以测试post方法(见TS4_Test_login.html)
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws jakarta.servlet.ServletException, IOException {
            System.out.println("doget");
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws jakarta.servlet.ServletException, IOException {
            System.out.println("dopost");
        }

}
