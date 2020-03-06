<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/1/30
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>top</title>
</head>
<body>
欢迎${USER.username}登录<br>
<a href="<%=request.getContextPath()%>/user/logout">退出登录</a>
</body>
</html>
