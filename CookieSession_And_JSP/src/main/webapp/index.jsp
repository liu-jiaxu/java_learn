<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<% System.out.println("helloworld!"); %>
    <%--java代码写在jsp中--%>
<%
    String contextPath = request.getContextPath();
    out.print(contextPath+"111");
%>
    <%--内置对象out，service方法中有声明，可以直接使用--%>
</body>
</html>