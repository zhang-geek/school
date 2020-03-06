<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/1/29
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<frameset rows="30%, *">
<frame src="<%=request.getContextPath()%>/index/toTop" name="top">
    <frameset cols="25%, *">
        <frame src="<%=request.getContextPath()%>/index/toLeft" name="left">
        <frame src="<%=request.getContextPath()%>/index/toRight" name="right">
    </frameset>
</frameset>
</html>
