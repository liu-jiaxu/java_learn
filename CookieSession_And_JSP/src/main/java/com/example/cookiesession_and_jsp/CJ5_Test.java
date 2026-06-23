package com.example.cookiesession_and_jsp; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/12 - 9:38
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * 一.Session
 *     1.概念：
 *         服务器会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器对象中。HttpSession
 *     2.使用
 *         HttpSession对象
 *             (1)获取对象
 *                 HttpSession session = request.getSession();
 *             (2)使用对象
 *                 Object getAttribute(String name)
 *                 void setAttribute(String name, Object value)
 *                 void removeAttribute(String name)
 *     3.服务器如何确保一次会话范围内，多个获取的Session对象是同一个？
 *         原理：类似于cookie，服务器会话第一次创建Session时，会在内存中新建一个Session对象，并且赋值id=...
 *              这个id通过响应头set-cookie：JSESSIONID=...携带发送给浏览器。之后的请求中，浏览器的请求头
 *              的cookie：JSESSIONID=...会携带session的id信息，与服务端端的Session对象id匹配，从而达到
 *              一次会话使用同一个Session对象(示例：JSESSIONID=967FD32AC68DC95AEDB413DBAD9203FD)
 *         总结：Session的实现依赖于Cookie
 *     4.细节
 *         (1)默认情况下，客户端关闭后，会话结束，两次获取的session不是同一个
 *             若需要相同，则创建Cookie对象，键为JSESSIONID，setMaxAge设置cookie的过期时间
 *         (2)默认情况下，服务器关闭后，两次获取的session不是同一个
 *             若要保证相同：(系统自动完成)
 *                 session的钝化：在服务器正常关闭之前，将session对象系列化到硬盘上
 *                 session的活化：在服务器启动后，将session文件转化为内存中的session对象
 *         (3)session的失效时间
 *             [1]服务器关闭
 *             [2]session对象调用invalidate()
 *             [3]session默认失效时间：30min (web.xml中的session-config标签中设置)
 *     5.session特点
 *         (1)session用于存储一次会话的多次请求的数据，存储在服务器
 *         (2)session可以存储任意类型，任意大小的数据
 *     6.session与cookie的区别
 *         (1)session存储在服务器端，cookie存储在客户端
 *         (2)session存储无大小类型限制，cookie仅存储字符串4kb数据
 *         (3)session存储数据安全，cookie相对不安全
 */

@WebServlet("/cj5")
public class CJ5_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取session
        HttpSession session = request.getSession();
        // 获取session
        session.setAttribute("msg","hello session!");
        session.setAttribute("name","lisi");
        session.setAttribute("age",18);
        session.removeAttribute("age");
        // 获取数据
        Object msg = session.getAttribute("msg");
        Object name = session.getAttribute("name");
        Object age = session.getAttribute("age");
        System.out.println(name + " " + msg + " " + age);

        // 细节分析
        // 期望客户端关闭后，session也可以相同
        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(60*60); // 设置相同时间3600s
        response.addCookie(cookie);

    }

}
