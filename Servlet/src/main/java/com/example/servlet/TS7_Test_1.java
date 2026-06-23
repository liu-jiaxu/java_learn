package com.example.servlet;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * ClassName: TS7_Test_1
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/5 - 20:25
 * @Version: v1.0
 */

@WebServlet("/ts72")
public class TS7_Test_1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 3.共享数据
        // {1}存储数据：void setAttribute(String name, Object value)
            // 见TS7_Test7
        // {2}通过键获取值：Object getAttribute(String name)
            Object msg = req.getAttribute("msg");
            System.out.println(msg);
        // {3}通过键移除键值对：void removeAttribute(String name)
            Object phone = req.getAttribute("phone");
            System.out.println(phone);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req, resp);

    }

}
