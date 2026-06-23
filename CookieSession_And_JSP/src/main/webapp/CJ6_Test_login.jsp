<%--
  Created by IntelliJ IDEA.
  User: zgh29
  Date: 2023/3/12
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>

<%--存在问题：刷新验证码时会连同页面一起刷新，导致输入的姓名密码数据也刷新为空，已解决，不知道原理！--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>

    <script>

        function refreshCode(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");
            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/cj6vc?time="+new Date().getTime();
        }

    </script>
        <%--实现验证码点击切换，利用时间戳实现不同路径--%>

    <style>
        div{
            color: red;
        }
    </style>

</head>
<body>

    <form action="${pageContext.request.contextPath}/cj6ls" method="post">
        <%--${pageContext.request.contextPath}：之后学的获取虚拟目录方法--%>
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"></td>
            </tr>
            <tr>
                <td>
                    <a href="#" onclick="refreshCode();return false;">
                        <img src="${pageContext.request.contextPath}/cj6vc" title="看不清点击刷新" id="vcode"/>
                    </a>
                </td>
                <td><button id="btn" onclick="refreshCode();return false;">看不清，换一张</button></td>
                    <%--点击图片和按钮均可切换--%>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
        <%--注意表单form不要写成from，否则提交按钮失效！--%>

    <%--<div><%=request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error")%></div>--%>
    <div><%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_error")%></div>
        <%--三元表达式消除页面null值显示--%>
    <div>${requestScope.cc_error}</div>
        <%--el表达式替换--%>

</body>
</html>
