<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%--
  Created by IntelliJ IDEA.
  User: zgh29
  Date: 2023/3/11
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>helloitcast</title>
</head>
<body>

    <%

        boolean flag = false;

        // 1.设置响应消息体的数据格式及编码(对于中文)
        // response.setContentType("text/html;charset=utf-8");
        // jsp中会设置编码格式
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

    %>

    <h1>欢迎回来，上次访问时间为<%= value %></h1>
        <%--jsp可以同时写java和web--%>

    <%
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

    %>

    <h1>您好，欢迎首次访问！</h1>


    <%

        }

    %>

</body>
</html>
