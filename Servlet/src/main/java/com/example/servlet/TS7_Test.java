package com.example.servlet;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: TS7_Test
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/5 - 19:48
 * @Version: v1.0
 */

// request其它功能：请求转发，共享数据，获取ServletContext
@WebServlet("/ts7")
public class TS7_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 4.获取ServletContext
            ServletContext servletContext = req.getServletContext();
            System.out.println(servletContext);
                // org.apache.catalina.core.ApplicationContextFacade@1ea71410

        // 3.共享数据
            // {1}存储数据：void setAttribute(String name, Object value)
                req.setAttribute("phone", "12345678911");
                req.setAttribute("msg", "123456789");
            // {2}通过键获取值：Object getAttribute(String name)
                // 见TS7_Test7
            // {3}通过键移除键值对：void removeAttribute(String name)
                req.removeAttribute("phone"); // 空值即为null

        // 2.请求转发：一种在服务器内部的资源跳转方式
            //{1}通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(String path)
            //{2}使用RequestDispatcher对象来进行转发：forward(ServletRequest request, ServletResponse response)
                req.getRequestDispatcher("/ts72").forward(req, resp);
                    // 浏览器执行此类后立即跳转执行/ts1对应url的类
                // req.getRequestDispatcher("http://jw.qut.edu.cn/").forward(req, resp);
                    // 访问本文件之外的网址会报错404
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req, resp);

    }
}
