package com.example.cookiesession_and_jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * ClassName: CJ1_Test
 * Package: com.example.cookiesession_and_jsp
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/9 - 9:09
 * @Version: v1.0
 */

/**
 * 一.会话技术
 *     1.会话：一次会话中包含多次请求和响应
 *         一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止
 *     2.功能：在一次会话的范围内的多次请求间，共享数据
 *     3.方式
 *         (1)客户端会话技术：Cookie(把数据存到客户端)
 *         (2)服务器端会话技术：Session(数据存到服务器端)
 *
 * 二.Cookie
 *     1.概念：
 *         客户端会话技术，把数据存到客户端
 *     2.使用
 *         (1)创建Cookie对象，绑定数据(客户端发送请求到服务器，服务器响应后发送数据到客户端，客户端绑定这些返回的数据)
 *             new Cookie(String name,String value);
 *         (2)发送Cookie对象(此后的会话中，客户端每次请求会一并发送之前服务器返回的所有数据)
 *             response.addCookie(Cookie cookie)
 *         (3)获取Cookie，拿到数据(服务器端拿到客户端请求数据)
 *             request.getCookies() 有多个请求，所以会返回数组
 *     3.原理
 *         (1)首先客户端发送请求到服务器端，服务端响应后发送cookie信息，其中的响应头附带信息set-cookie:name=value
 *         (2)客户端再次发送请求并附带服务器端返回的响应头，此时客户端请求头附带信息cookie:name=value，之后服务器另一个进程
 *            接收此请求头，获取cookie，从而获取cookie的信息
 *     4.cookie细节
 *         (1)一次可以发送多个cookie
 *         (2)cookie存活时间：
 *             [1]默认时，浏览器关闭后cookie数据被销毁
 *             [2]持久化存储：setMaxAge(int seconds)
 *                 seconds参数：{1}正数：将Cookie数据写入硬盘的文件中n秒，此时关闭浏览器仍可访问cookie，n秒过后自动删除该文件
 *                             {2}负数：默认值(默认-1)
 *                             {3}0：删除cookie信息
 *         (3)存储中文：(需要设置编码格式，否则控制台会出现中文乱码！)
 *             tomcat8之前不能直接存储中文
 *             tomcat8之后支持存储中文，但特殊符号不支持
 *             补：设置编码位置：(很重要！)
 *                 [1]File->Settings->File Encodings全改为utf-8
 *                 [2]Help->Edit Custom VM Options 在最后加上 -Dfile.encoding=UTF-8
 *                 [3]设置tomcat的虚拟机选项，加上 -Dfile.encoding=UTF-8
 *                 [4]在IDEA的安装目录的bin目录下找到idea64exe.vmoptions，打开后在末尾加上 -Dfile.encoding=UTF-8
 *         (4)cookie共享
 *             默认cookie不能共享。
 *             setPath(String path)；设置cookie的获取范围，默认情况下，设置当前的虚拟目录
 *             共享设置：setPath(/)；
 *             补：不同的tomcat共享
 *                 setDomain(String domain)；设置cookie的一级域名相同，多个服务器之间可共享
 *                 例：setDomain(".baidu.com")，则tieba.baidu.com和news.baidu.com在的cookie可以共享
 *     5.cookie属性描述
 *          属性	      描述
 *          name	  Cookie的名称，Cookie一旦创建，名称便不可更改
 *          value	  Cookie的值。如果值为Unicode字符，需要为字符编码。如果值为二进制数据，则需要使用BASE64编码
 *          maxAge	  Cookie失效的时间，单位秒。如果为正数，则该Cookie在maxAge秒之后失效。如果为负数，该Cookie为临时Cookie，关闭浏览器即失效，浏览器也不会以任何形式保存该Cookie。如果为0，表示删除该Cookie。默认为-1。
 *          secure	 该Cookie是否仅被使用安全协议传输。安全协议。安全协议有HTTPS，SSL等，在网络上传输数据之前先将数据加密。默认为false。
 *          path	  Cookie的使用路径。如果设置为“/sessionWeb/”，则只有contextPath为“/sessionWeb”的程序可以访问该Cookie。如果设置为“/”，则本域名下contextPath都可以访问该Cookie。注意最后一个字符必须为“/”。
 *          domain	 可以访问该Cookie的域名。如果设置为“.google.com”，则所有以“google.com”结尾的域名都可以访问该Cookie。注意第一个字符必须为“.”。
 *          comment	 该Cookie的用处说明，浏览器显示Cookie信息的时候显示该说明。
 *          version	 Cookie使用的版本号。0表示遵循Netscape的Cookie规范，1表示遵循W3C的RFC 2109规范
 *     6.cookie特点、作用
 *         特点：(1)cookie存储数据在客户端浏览器
 *              (2)浏览器对于单个cookie的大小有限制(4kb)以及对同一个域名下的总cookie数量也有限制(20个)
 *              (3)只能存储字符串数据
 *         作用：(1)cookie一般用于存储少量的不太敏感的数据
 *              (2)在浏览器不登录的情况下，完成服务器对客户端的身份识别
 */

// 为了方便，虚拟目录设置为空！
@WebServlet("/cj1")
public class CJ1_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        // 1.创建Cookie对象
        Cookie cookie = new Cookie("name","zhangsan");
        Cookie cookie2 = new Cookie("msg","hello");
        String msg = URLEncoder.encode("你好我的世界", "utf-8"); // 设置编码格式
        Cookie cookie3 = new Cookie("msg",msg);
            // host、path和name相同的cookie会被新的给替换掉，此处cookie3替换了cookie2
        // 补：设置cookie存活时间(s)
        cookie.setMaxAge(30);
        // 2.发送Cookie
        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        resp.addCookie(cookie3);
        // 3.获取Cookie
        // 见CJ_Test2

    }

}
