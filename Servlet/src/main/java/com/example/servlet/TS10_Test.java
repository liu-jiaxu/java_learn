package com.example.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: TS10_Test
 * Package: com.example.servlet
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/8 - 8:55
 * @Version: v1.0
 */

/**
 * 一.ServletContent对象
 *     1.概念：代表整个web应用，可以和程序的容器(服务器)来通信
 *     2.获取
 *         (1)通过request对象获取：request.getSetServletContent();
 *         (2)通过HttpServlet获取：this.getServletContent();
 *     3.功能
 *         (1)获取MIME类型
 *             MIME类型：在互联网通信过程中定义的一种文件数据类型
 *                 格式：大类型/小类型  text/html  image/jpg
 *         (2)域对象：共享数据
 *             servletContext对象范围：所有用户所有请求的数据
 *             setAttribute(String name,Object value)
 *             getAttribute(String name)
 *             removeAttribute(String name)
 *         (3)获取文件真实路径(服务器路径)
 *             方法：String getRealPath(String path)
 *             注：以后规定所有getRealPath获取的文件都直接放到webapp文件下，这样getRealPath方法参数直接写文件名即可！
 */

@WebServlet("/ts10")
public class TS10_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取ServletContent对象
        // 1.通过request对象获取：request.getSetServletContent();
        ServletContext servletContext1 = req.getServletContext();
        // 2.通过HttpServlet获取：this.getServletContent();
        ServletContext servletContext2 = this.getServletContext();
        System.out.println(servletContext1);
        System.out.println(servletContext2);

        // 获取MIME类型
        // 1.通过HttpServlet获取：this.getServletContent();
        ServletContext servletContext3 = this.getServletContext();
        // 2.定义文件名称
        String filename = "a.jpg";
        // 3.获取MIME类型
        String mineType = servletContext3.getMimeType(filename);
        System.out.println(mineType);

        // 域对象
        ServletContext servletContext4 = this.getServletContext();
        servletContext4.setAttribute("name", "zgh296");
        servletContext4.setAttribute("age", 18);
        servletContext4.removeAttribute("age");

        // 获取文件真实路径(服务器路径)
        // 分别获取文件a，b，c的真实路径(a在src下，b在webapp下，c在WEB-INF下)
        ServletContext servletContext5 = this.getServletContext();
        String a = servletContext5.getRealPath("/WEB-INF/classes/a.jpg");
        String b = servletContext5.getRealPath("/b.jpg");
        String c = servletContext5.getRealPath("/WEB-INF/c.jpg");
            // 注意不同路径下文件的真实路径获取！
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }

}
