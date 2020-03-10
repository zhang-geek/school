<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/3/9
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    请输入新密码：<input type="text" name="password">
    请再次输入新密码：<input type="text" name="newPwd">
    请输入你收到的验证码：<input type="text" name="hairPhoneCode">
    <input type="button" value="输入完成" onclick="toUpdatePwd()">
</body>
<script type="text/javascript">
    function toUpdatePwd() {
        var index = layer.load(1);
        $.post(
            "${ctx}/user/updateUserPwd",
            $("#fm").serialize(),
            function (data) {
                if (data.code != 200) {
                    layer.msg(data.msg);
                    layer.close(index);
                    return;
                }
                layer.msg(data.msg,{time:500},function () {
                    parent.window.location.href = "${ctx}/user/toShow";
                })
            }
        );
    }
</script>
</html>
