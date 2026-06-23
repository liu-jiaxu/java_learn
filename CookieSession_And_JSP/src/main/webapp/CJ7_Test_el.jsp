<%@ page import="com.example.cookiesession_and_jsp.CJ7_Test_User" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: zgh29
  Date: 2023/3/13
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el</title>
</head>
<body>

    <h1>1.el示例</h1>
    ${3>4}
    \${3>4}

    <h1>2.运算符使用</h1>>
    ${3+1} <br>
    ${3/4} <br>
    ${3%4} <br>
    ${3 mod 4} <br>

    ${3 <= 4} <br>

    ${3>4 && 3<4} <br>
    ${1==1 || 1==0} <br>

    \${empty arr} <br>
        <%--判断数组arr是否为null长度是否为0--%>
        <%--见7.empty--%>

    <h1>3.获取域中的数据</h1>>
    <%
        // 在域中存储数据
        request.setAttribute("name", "zhangsan");
        session.setAttribute("name", "lisi");
        pageContext.setAttribute("name", "wangwu");
        application.setAttribute("age", "23");
    %>
    <%--获取数据--%>
    ${requestScope.name} <br>
    ${sessionScope.name} <br>
    ${pageScope.name} <br>
    ${applicationScope.age} <br>
    ${requestScope.qaq} <br> <%--获取不到值显示空字符串--%>
    ${name} <br>

    <h1>4.获取对象值</h1>
    <%
        CJ7_Test_User user = new CJ7_Test_User();
        user.setId(11);
        user.setUsername("zhangsan");
        user.setBirthday(new Date());
        request.setAttribute("user", user);
    %>
    ${requestScope.user} <br> <%--获取对象toString重写后的返回值--%>
    ${requestScope.user.id} <br> <%--若类中有get方法，则可以使用成员变量名获取单个变量--%>
    ${requestScope.user.birthday} <br>
    ${requestScope.user.birthday.month} <br> <%--日期中有额外的获取年月日等方法--%>
    ${requestScope.user.bir} <br> <%--根据编写的成员变量获取中文日期--%>
    \${requestScope.user.ider} <br> <%--成员变量名写错会报错500并显示原因！--%>

    <h1>5.获取list集合</h1>
    <%
        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add(user);
        request.setAttribute("list", list);
    %>
    ${requestScope.list} <br> <%--获取集合list--%>
    ${requestScope.list[0]} <br> <%--获取集合第一个元素--%>
    ${requestScope.list[10]} <br> <%--索引越界不会报错，显示空字符串--%>
    ${requestScope.list[2].username} <br> <%--若集合存储对象，则先通过索引获取对象，再通过成员变量获取对应变量值--%>

    <h1>6.获取map集合</h1>
    <%
        Map map = new HashMap();
        map.put("mName", "zhangsan");
        map.put("mAge", "23");
        map.put("user", user);
        request.setAttribute("map", map);
    %>
    ${requestScope.map} <br> <%--获取map集合--%>
    ${requestScope.map.mAge} <br> <%--根据key获取map集合中的值value--%>
    ${requestScope.map.user.username} <br> <%--若map集合存储对象，则先通过key获取对象，再通过成员变量获取对应变量值--%>

    <h1>7.empty</h1>
    <%
        String str = "abc";
        String str2 = null;
        List list1 = new ArrayList();
        list1.add(str);
        request.setAttribute("str", str);
        request.setAttribute("str2", str2);
        request.setAttribute("list1", list1);
    %>
    ${empty str} <br> <%--判断字符串str是否为空且长度是否为0--%>
    ${empty str2} <br> <%--判断字符串str是否为空且长度是否为0--%>
    ${not empty str} <br> <%--判断字符串str是否不为空且长度是否大于0，与empty相反！--%>
    ${not empty list1} <br> <%--集合有元素str，不为空，返回true--%>

    <h1>8.pageContext</h1>
    ${pageContext.request} <br> <%--通过pageContext获取其它内置对象--%>
    ${pageContext.request.contextPath} <br> <%--在jsp通过pageContext动态获取虚拟目录--%>

</body>
</html>
