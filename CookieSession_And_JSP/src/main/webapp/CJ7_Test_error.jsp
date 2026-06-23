<%--
  Created by IntelliJ IDEA.
  User: zgh29
  Date: 2023/3/13
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ include file="CJ7_Test_top.jsp" %>
    <%--导入对应文件--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--导入资源--%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        String msg = exception.getMessage();
        out.print(msg);
    %>
        <%--设置isErrorPage="true"后可以使用exception对象获取错误信息--%>

    <c:if test="!isErrorPage">
        <%--taglib导入jar包起名后，可以使用对应方法了--%>

</body>
</html>
