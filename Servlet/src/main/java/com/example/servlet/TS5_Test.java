package com.example.servlet;

/**
 * ClassName: TS5_Test
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/4 - 19:38
 * @Version: v1.0
 */

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 一.浏览器与服务器交互
 *     1.tomcat服务器会根据请求url中的资源路径，创建对应的Servlet_Test类的对象
 *     2.tomcat服务器会创建request和response两个对象，request对象中封装请求消息数据
 *     3.tomcat将request和response对象传递给service方法，并调用service方法
 *     4.程序员通过request对象获取请求消息数据，通过response对象设置响应消息数据
 *     5.服务器在浏览器做出响应之前，会从response对象中拿出程序员设置的响应消息数据
 *
 * 二.Request
 *     1.request和response对象的原理
 *         (1)request和response对象是由服务器tomcat创建的
 *         (2)request对象用于获取请求信息，response对象设置响应消息
 *     2.request对象继承体系结构
 *         ServletRequest -> 接口
 *         HttpServletRequest -> 接口，继承ServletRequest
 *         org.apache.catalina.connector.RequestFacade@1169dd4b -> RequestFacade类，tomcat创建，实现HttpServletRequest
 *     3.request功能
 *         (1)获取请求消息数据
 *             [1]获取请求行数据doGet()
 *                 请求行示例：GET /Servlet_Test_war/ts5?name=111 HTTP/1.1
 *                 获取方法：(重点方法2、5)
 *                     {1}获取请求方式GET方法：String getMethod()
 *                     {2}获取虚拟目录/Servlet_Test_war方法：String getContextPath()
 *                     {3}获取Servlet路径/ts5方法：String getServletPath()
 *                     {4}获取get方法请求参数name=111方法：String getQueryString()
 *                     {5}获取请求url(/Servlet_Test_war/ts5)方法：String getRequestURI() | String getRequestURL()
 *                         URL：统一资源定位符
 *                         URI：统一资源标识符
 *                     {6}获取协议及版本HTTP/1.1方法：String getProtocol()
 *                     {7}获取客户机IP地址方法：String getRemoteAddr()
 *             [2]获取请求头数据doGet()
 *                 获取方法：(重点1)
 *                     {1}通过请求头的名称获取请求头的值：String getHeader(String name)
 *                     {2}获取所有请求头名称：Enumeration<String> getHeaderNames()
 *             [3]获取请求体数据doPost()
 *                 步骤
 *                     {1}获取流对象
 *                         获取字符输入流，只能操作字符数据：BufferedReader getReader()
 *                         获取字节输入流，可以操作所以类型数据：ServletInputStream getInputStream()
 *                     {2}再从流对象中拿数据
 *         (2)其它功能
 *             [1]获取请求参数通用方式(见TS_Test6)
 *                 {1}根据参数名获取参数值：String getParameter(String name)
 *                 {2}根据参数名称获取参数值的数组：String[] getParameterValues(String name)
 *                 {3}获取所有请求参数名称Enumeration<String> getParameterNames()
 *                 {4}获取所有参数的map集合：Map<String, String[]> getParameterMap()
 *             [2]请求转发：一种在服务器内部的资源跳转方式(见TS_Test7)
 *                 步骤
 *                     {1}通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(String path)
 *                     {2}使用RequestDispatcher对象来进行转发：forward(ServletRequest request, ServletResponse response)
 *                 特点
 *                     {1}转发后浏览器路径不会发生变化
 *                     {2}只能转发到当前服务器内部资源中(转发到当前模块中没有的文件会无法访问报错404)
 *                     {3}转发是一次请求
 *             [3]共享数据
 *                 域对象：一个有作用范围的对象，可以在范围内共享数据
 *                 request域；代表一次请求的范围，一般用于请求转发的多个资源中共享数据
 *                 方法
 *                     {1}存储数据：void setAttribute(String name, Object value)
 *                     {2}通过键获取值：Object getAttribute(String name)
 *                     {3}通过键移除键值对：void removeAttribute(String name)
 *             [4]获取ServletContext
 *                 ServletContext getServletContext()
 */

@WebServlet("*.ts5") //*表示非0任意长度的字符串
public class TS5_Test extends HttpServlet {

    // 访问网址示例：http://localhost:8080/test.ts5?name=zhangsan&age=20
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求行数据
        //{1}获取请求方式GET方法：String getMethod()
            String method = req.getMethod();
            System.out.println(method);
        //{2}获取虚拟目录(此处虚拟目录未设置，为空)方法：String getContextPath()
            String method2 = req.getContextPath();
            System.out.println(method2);
        //{3}获取Servlet路径/test.ts5方法：String getServletPath()
            String method3 = req.getServletPath();
            System.out.println(method3);
        //{4}获取get方法请求参数name=zhangsan&age=20方法：String getQueryString()
            String method4 = req.getQueryString();
            System.out.println(method4);
        //{5}获取请求url(url+/test.ts5)方法：String getRequestURI() | String getRequestURL()
            String method5 = req.getRequestURI();
            StringBuffer method52 = req.getRequestURL();
            System.out.println(method5); // /test.ts5
            System.out.println(method52); // http://localhost:8080/test.ts5
        //{6}获取协议及版本HTTP/1.1方法：String getProtocol()
            String method6 = req.getProtocol();
            System.out.println(method6);
        //{7}获取客户机IP地址0:0:0:0:0:0:0:1方法：String getRemoteAddr()
            String method7 = req.getRemoteAddr();
            System.out.println(method7);

        System.out.println();

        // 获取请求头数据
        //{1}通过请求头的名称获取请求头的值：String getHeader(String name)
            // 例1：请求头User-Agent
            String agent = req.getHeader("User-Agent");
            if(agent.contains("Chrome")){
                System.out.println("Chrome");
            } else if(agent.contains("Edge")) {
                System.out.println("Edge");
            } else {
                System.out.println("Firefox");
            }
            // 例2：请求头Referer
            String referer = req.getHeader("Referer"); // 对应html文件
            System.out.println(referer); // http://localhost:8080/TS5_Test_login.html
            // 防盗链，看referer是否匹配
            if(referer != null) { // 注意先判断非空！
                if (referer.contains("http://localhost:8080/TS5_Test_")) {
                    resp.setContentType("text/html;charset=utf-8"); // 支持浏览器输出中文
                    resp.getWriter().write("yes，正常"); // 在浏览器上输出信息
                        // resp为doGet方法传入的参数
                } else {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().write("no，不正常");
                }
            }
        //{2}获取所有请求头名称：Enumeration<String> getHeaderNames()
            Enumeration<String> headerNames = req.getHeaderNames();
            while(headerNames.hasMoreElements()){
                // 获取请求头名称
                String name = headerNames.nextElement();
                // 根据名称获取请求头的值
                String value = req.getHeader(name);
                System.out.println(name+"="+value);
            }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求消息体--请求参数BufferedReader getReader()
            // (1)获取字符流
            BufferedReader br = req.getReader();
            // (2)读取数据
            String line = null;
            while( (line = br.readLine()) != null){
                System.out.println(line);
            }

    }

}
