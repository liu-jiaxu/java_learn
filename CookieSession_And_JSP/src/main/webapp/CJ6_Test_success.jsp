<%--
  Created by IntelliJ IDEA.
  User: zgh29
  Date: 2023/3/12
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>登陆成功，欢迎<%=request.getSession().getAttribute("user") %>！</h1>
</body>
</html>
