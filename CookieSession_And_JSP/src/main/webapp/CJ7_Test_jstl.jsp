<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oracle.wls.shaded.org.apache.regexp.REUtil" %>
<%@ page import="com.example.cookiesession_and_jsp.CJ7_Test_User" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: zgh29
  Date: 2023/3/14
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--导入包，一般起名为c--%>
    <%--注：tomcat10版本对应的jar包和8版本的不一样，是如下两个jar包：
        jakarta.servlet.jsp.jstl-2.0.0.jar
        jakarta.servlet.jsp.jstl-api-2.0.0.jar--%>
<html>
<head>
    <title>jstl</title>

    <style>
        table {
            text-align:center;
        }
    </style>

</head>
<body>

    <%--
        c:if标签
            1.属性test
                必须属性,接收boolean表达式，若为true，则执行c:if标签显示内容，否则不显示标签体内容。
                一般情况下，test属性会结合el表达式一起使用
            2.注：c:if标签没有else，只能重新定义其他c:if标签
        --%>
    <c:if test="true">
        <h1>lalala</h1>
    </c:if>

    <%--判断request域中的一个list集合是否为空，不为空则遍历集合--%>
    <%
        List list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("this is " + i + " ");
        }
        request.setAttribute("list", list);
    %>
    <c:if test="${not empty list}">
        <h3>遍历集合</h3>
        <%
            for(Object i : list){
            out.println(i);
            }
        %>
    </c:if>

    <%--判断奇偶--%>
    <%
        request.setAttribute("msg", 3);
    %>
    <c:if test="${msg % 2 != 0}">
        <h1>${msg}为奇数</h1>
    </c:if>
    <c:if test="${msg % 2 == 0}">
        <h1>${msg}为偶数</h1>
    </c:if>



    <%--
        c:choose标签
            案例：完成数字编号对应星期案例
                1.域中存储数字
                2.使用choose标签取出数字，相当于switch
                3.使用when标签判断，相当于case
                4.otherwise标签做其它情况的声明，相当于default
        --%>
    <% request.setAttribute("number",3); %>

    <h1>choose标签</h1>
    <c:choose>
        <c:when test="${number == 1}">星期一</c:when>
        <c:when test="${number == 2}">星期二</c:when>
        <c:when test="${number == 3}">星期三</c:when>
        <c:when test="${number == 4}">星期四</c:when>
        <c:when test="${number == 5}">星期五</c:when>
        <c:when test="${number == 6}">星期六</c:when>
        <c:when test="${number == 7}">星期日</c:when>
        <c:otherwise>error!</c:otherwise>
    </c:choose> <br>



    <%--
        c:forEach标签
            1.循环操作
            属性
                begin：开始值
                end：结束值
                var：临时变量1
                step：步长
                varStatus：循环状态变量
                    index
                    count
            2.遍历容器
                List list = new ArrayList();
                for (var: items){...}
                    items：容器对象
                    var：容器中元素的临时变量
        --%>

    <c:forEach begin="1" end="6" var="i" step="2" varStatus="s">
        ${i} ${s.index} ${s.count} <br>
            <%--s.index：容器中元素的索引，从0开始，普通遍历和变量值相同，
                s.count：循环次数，从1开始--%>
    </c:forEach>

    <%
        List list2 = new ArrayList();
        list2.add(111);
        list2.add(222);
        list2.add("lalala");
        request.setAttribute("list2", list2);
    %>
    <c:forEach items="${list2}" var="str" varStatus="s">
        ${s.index} ${s.count} ${str} <br>
    </c:forEach>



    <%--案例：使用jstl将list集合数据展示到jsp页面的表格table中--%>
    <%
        List alist = new ArrayList();
        alist.add(new CJ7_Test_User(101, "lisi",new Date()));
        alist.add(new CJ7_Test_User(102, "wangwu",new Date()));
        alist.add(new CJ7_Test_User(104, "qianqi",new Date()));
        request.setAttribute("alist", alist);
    %>

    <table border="1" width="500" align="center" bgcolor="#ffe4c4">
        <tr>
            <th>i</th>
            <th>id</th>
            <th>name</th>
            <th>birthday</th>
        </tr>
        <c:forEach items="${alist}" var="user" varStatus="s">
                <c:if test="${s.count % 2 == 0}">
                    <tr bgcolor="aqua">
                        <td>${s.count}</td>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.bir}</td>
                    </tr>
                </c:if>
                <c:if test="${s.count % 2 != 0}">
                    <tr bgcolor="#7fffd4">
                        <td>${s.count}</td>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.bir}</td>
                    </tr>
                </c:if>
        </c:forEach>
    </table>

</body>
</html>
