package com.example.cookiesession_and_jsp; /**
 * ClassName: ${NAME}
 * Package: ${PACKAGE_NAME}
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/9 - 19:34
 * @Version: v1.0
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 案例：记录上一次访问时间
 *     需求：1.访问一个Servlet，如果是第一次访问，则提示：您好，欢迎首次访问！
 *          2.若不为第一次访问，则提示：欢迎回来，上次访问时间为：时间字符串
 *     分析：1.采用cookie完成
 *          2.在服务器中的servlet判断是否有一个名为lastTime的cookie
 *              (1)有：
 *                  [1]响应数据：欢迎回来，上次访问时间为：2000-12-12 12:12:12
 *                  [2]写回cookie：listTime=2000-12-12 12:12:12
 *              (2)无：
 *                  [1]响应数据：您好，欢迎首次访问！
 *                  [2]写回cookie：listTime=2000-12-12 12:12:12
 */

@WebServlet("/cj3")
public class CJ3_Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean flag = false;

        // 1.设置响应消息体的数据格式及编码(对于中文)
        response.setContentType("text/html;charset=utf-8");
        // 2.获取所有cookie
        Cookie[] cookies = request.getCookies();
        // 3.遍历cookie数组
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie:cookies) {
                // 3.获取cookie名称
                String name = cookie.getName();
                // 4.判断cookie名称是否为lastTime
                if ("lastTime".equals(name)) {
                    // 有该cookie，不是第一次访问
                    flag = true;
                    // 设置cookie的value
                    // 获取当前时间的字符串并重新设置cookie的值，重新发送cookie
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(date);
                    // URL编码
                    str = URLEncoder.encode(str, "UTF-8");
                    cookie.setValue(str);
                    // 设置cookie的存活时间(1day)
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                    // 响应数据
                    // 获取cookie的value时间
                    String value = cookie.getValue();
                    // URL解码
                    value = URLDecoder.decode(value, "UTF-8");
                    response.getWriter().write("<h1>欢迎回来，上次访问时间为:" + value + "</h1>");
                    // 找到value值直接停止循环
                    break;
                }
            }
        }
        if(cookies == null || cookies.length == 0 || flag == false){
            // 没有value=lastTime，第一次访问
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(date);
            format = URLEncoder.encode(format,"utf-8");
            Cookie cookie = new Cookie("lastTime",format);
            // 设置cookie的存活时间(1day)
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
            // 响应数据
            // 获取cookie的value时间
            String value = cookie.getValue();
            value = URLDecoder.decode(value, "UTF-8");
            response.getWriter().write("<h1>您好，欢迎首次访问！</h1>");
        }

    }

}
