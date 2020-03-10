<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/3/9
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<body>
    宿舍编号：<input type="text" name="dormNumber">
    <input type="hidden" name="isDel" value="0"/>
    <input type="hidden" name="dormStatus" value="0"/>
    <input type="button" value="注册" onclick="addDorm()"/>
</body>
<script type="text/javascript">
    function addDorm() {
        var index = layer.load(1, {shade: 0.5});
        $.post("/dorm/addDorm",
            $("#fm").serialize(),
            function (data) {
                if (data.code == 200) {
                    layer.msg("新增成功");
                    window.location.href = "<%=request.getContextPath()%>/dorm/toShowDorm";
                }
                layer("新增失败请重新尝试");
                window.location.href = "<%=request.getContextPath()%>/dorm/toAddDorm";
            });
    }
</script>
</html>
