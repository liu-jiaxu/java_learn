package com.example.ajax_and_json;

/**
 * ClassName: AJ1
 * Package: a6_AJAX
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/22 - 20:43
 * @Version: v1.0
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 一.AJAX
 *     1.概念：ASynchronous JavaScript And XML 异步的Java和XML
 *            AJAX不是新的编程语言，而是一种使用现有标准的新方法。
 *            AJAX最大的优点是在不重新加载整个页面的情况下，可以与服务器交换数据并更新部分网页内容。
 *            AJAX不需要任何浏览器插件，但需要用户允许JavaScript在浏览器上执行。
 *            XMLHttpRequest只是实现Ajax的一种方式。
 *         (1)异步和同步
 *             [1]同步：客户端一次请求服务器端时，服务器处理数据的同时客户端无法进行其它操作，必须等服务器端
 *                     返回响应才能继续工作
 *             [2]异步：客户端的请求不必等待服务器端的响应，客户端在等待响应时可以进行其它操作
 *     2.实现方式
 *         (1)原生js(了解即可)
 *         (2)JQuery
 *             [1]$.ajax()
 *                 $.ajax({键值对})；
 *             [2]$.get()
 *                 语法：$.get(url,[data],[callback],[type])
 *                 参数：
 *                     url：请求路径
 *                     data：请求参数
 *                     callback：回调函数
 *                     type：响应结果类型
 *             [3]$.post()
 */

@WebServlet("/aj1")
public class AJ1_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取请求参数
        String username = req.getParameter("username");
        // 2.打印参数
        System.out.println(username);
        //3.响应
        resp.getWriter().write("hello"+username);

    }


}
