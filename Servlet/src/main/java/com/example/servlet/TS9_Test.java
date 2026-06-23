package com.example.servlet;

/**
 * ClassName: TS9_Test
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/7 - 10:34
 * @Version: v1.0
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 一.HTTP协议：
 *     1.请求消息：客户端发送给服务器端的数据(见TS4_Test/4.HTTP请求消息数据格式)
 *         数据格式
 *             请求行
 *             请求头
 *             请求空行
 *             请求体
 *     2.响应消息：服务器端发送给客户端的数据
 *         (1)数据格式
 *             [1]响应行
 *                 {1}组成：
 *                      协议/版本 响应状态码 状态码描述
 *                      HITP/2.0    200      ОК
 *                 {2}响应状态码：服务器告诉客户端浏览器本次请求和响应的一个状态
 *                     {{1}}状态码都是3位数字
 *                     {{2}}分类：
 *                         1xx(表示100-199)：服务器仅接受客户端消息，但没有接受完成，等待一段时间后，发送1xx状态码
 *                         2xx：成功。状态码代表：200
 *                         3xx：重定向。状态码代表：304。当前服务器无法完成任务，需要给客户端发送可以完成任务的网页地址并跳转。
 *                              或者当前访问的资源已经被服务器缓存，则直接跳转到缓存即可
 *                         4xx：客户端错误。状态码代表：404(请求路径没有对应的访问资源)
 *                                                  405(请求方式没有对应的doGet方法)
 *                         5xx：服务器端错误、状态码代表：500(服务器内部出现异常)
 *             [2]响应头
 *                 {1}组成：
 *                     头名称:值
 *                     Content-Type: text/html;charset=UTF-8
 *                     Content-Length: 101
 *                     Date: Wed, 06 Jun 2018 07:08:42 GMT
 *                 {2}常见响应头：
 *                     {{1}}Content-Type：服务器告知客户端本次响应体数据格式以及编码格式
 *                     {{2}}Content-Length：字符长度
 *                     {{3}}Date：日期
 *                     {{4}}Content-disposition：服务器告知客户端以什么格式打开响应体数据
 *                                              in-line:默认值，在当前页面打开
 *                                              attachment:以附件形式打开响应体，用于文件下载
 *             [3]响应空行
 *             [4]响应体：真实传输的数据
 *          (2)响应字符串格式
 *              HTTP/2.0 200 ОК
 *              Content-Type: text/html;charset=UTF-8
 *              Content-Length: 101
 *              Date: Wed, 06 Jun 2018 07:08:42 GMT
 *
 *              <html>
 *                  <head>
 *                      <title>$Title$</title>
 *                  </head>
 *                  <body>
 *                      hello , response
 *                  </body>
 *              </html>
 *
 * 二.Response对象
 *     1.功能：设置响应消息
 *         (1)设置响应行
 *             设置状态码：setStatus(int sc)
 *         (2)设置响应头
 *             setHeader(String name,String value)
 *         (3)设置响应体
 *             [1]获取输出流
 *                 字符输出流：PrintWriter getWriter()
 *                 字节输出流：ServletOutputStream getOutputStream()
 *             [2]使用输出流，将数据输出到客户端浏览器
 *     2.案例(见TS9_Test相关类)
 *         (1)完成重定向(见TS9_Test_1)
 *             [1]重定向redirect特点
 *                 {1}地址栏发生变化
 *                 {2}重定向可以访问其它站点(服务器)的资源(可以访问文件目录外的网址)
 *                 {3}重定向是两次请求，不能通过request域共享数据(setAttributes和getAttribute无法使用)
 *             [2]转发forward特点
 *                 {1}地址栏不发生变化
 *                 {2}转发不可以访问其它站点(服务器)的资源
 *                 {3}转发是一次请求，可以通过request域共享数据
 *         (2)服务器输出字符数据到浏览器(见TS9_Test_2)
 *             步骤
 *                 获取字符输出流
 *                 输出数据
 *         (3)服务器输出字节数据到浏览器(见TS9_Test_3)
 *             步骤
 *                 获取字节输出流
 *                 输出数据
 *         (4)验证码(见TS9_Test_4)
 *             功能：鉴别机器人和人操作页面，防止恶意表单注册(机器人程序恶意循环注册表单，致使数据库存储大量表单数据崩溃)
 *             本质：一张图片
 *     3.路径(以后学的jsp一般用绝对路径)
 *         (1)绝对路径：通过绝对路径可以确定唯一资源
 *             [1]规则：判断谁用定义的路径，判断请求将来从何处发出
 *                 给客户端浏览器使用：需要加虚拟目录(项目的访问目录)。如<a>，<from>，重定向
 *                 给服务器使用：不需要加虚拟路径。如转发
 *             [2]获取动态虚拟目录：String contextPath = request.getContextPath();
 *                 使用虚拟目录+路径：response.sendRedirect(contextPath+"TS9_Test_1_welcome.html");
 *                     补：在html中用jsp获取虚拟目录
 *             [3]例：http://localhost:8080/ts91
 *         (2)相对路径：通过相对路径不可以确定唯一资源，首先要确定当前资源与目标资源的相对位置关系
 *             [1]规则：
 *                 ./  当前目录
 *                 ../ 后退一级目录
 *             [2]例：./ts91
 */

@WebServlet("/ts9")
public class TS9_Test extends HttpServlet {

    // 此时会报错500，原因在浏览器显示
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i = 3/0;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
