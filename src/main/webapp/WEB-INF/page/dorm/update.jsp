<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/3/9
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改宿舍情况</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<body>
    宿舍号:<input type="text" name="dormNumber" value="${dorm.dormNumber}"/>
    宿舍状态:<input type="text" name="dormStatus" value="${dorm.dormStatus}"/>
    <input type="button" onclick="updateDorm(${dorm.id})"/>
</body>
<script type="text/javascript">
    function updateDorm() {
        var index = layer.load(1, {shade: 0.5});
        $.post("/dorm/updateDormSchool",
            $("#fm").serialize(),
            function (data) {
                if (data.code == 200) {
                    layer.msg("修改成功");
                    window.location.href = "<%=request.getContextPath()%>/dorm/toShowDorm";
                }
                layer.msg("修改失败请重新尝试");
                window.location.href = "<%=request.getContextPath()%>/dorm/toAddDorm";
    });
    }
</script>
</html>
