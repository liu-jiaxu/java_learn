package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: TS9_Test_2
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/8 - 8:55
 * @Version: v1.0
 */

// 服务器输出字符数据到浏览器
@WebServlet("/ts92")
public class TS9_Test_2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 输出中文前要设置编码
        resp.setCharacterEncoding("utf-8"); // 注：设置服务器端编码方式，服务器默认编码为ISO-8859-1
        resp.setContentType("text/html;charset=utf-8"); // 注：设置浏览器编码规则，浏览器默认gbk
            // 可以仅设置resp.setContentType("text/html;charset=utf-8");

        // 1.获取字符流
        PrintWriter printWriter = resp.getWriter(); // resp在一次响应过后会自动销毁，不用关闭调用的流
        // 2.输出数据
        printWriter.write("你好！");

    }

}
