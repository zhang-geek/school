<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: zhangzhikai--%>
  <%--Date: 2020/1/30--%>
  <%--Time: 20:30--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/md5-min.js"></script>--%>
    <%--<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>--%>
<%--</head>--%>
<%--<style type="text/css">--%>
    <%--.error{--%>
        <%--color: red;--%>
    <%--}--%>
<%--</style>--%>
<%--<script type="text/javascript">--%>

    <%--$(function(){--%>
        <%--if ('${msg}' != '') {--%>
            <%--layer.msg('${msg}');--%>
        <%--}--%>
        <%--$("#fm").validate({--%>
            <%--rules:{--%>
                <%--username:{--%>
                    <%--required:true--%>
                <%--},--%>
                <%--password:{--%>
                    <%--required:true,--%>
                    <%--rangelength:[0,5]--%>
                <%--}--%>
            <%--},--%>
            <%--messages:{--%>
                <%--username:{--%>
                    <%--required:"请输入姓名"--%>
                <%--},--%>
                <%--password:{--%>
                    <%--required:"请输入年龄",--%>
                    <%--rangelength:"长度限制在{0}到{1}之间"--%>
                <%--}--%>
            <%--}--%>
        <%--})--%>
    <%--})--%>

    <%--$.validator.setDefaults({--%>
        <%--submitHandler : function() {--%>
            <%--$("#password").val(md5($("#password").val()));--%>
            <%--var index = layer.load(1);--%>
            <%--$.get(--%>
                <%--"<%=request.getContextPath()%>/users/login",--%>
                <%--$("#fm").serialize(),--%>
                <%--function(data) {--%>
                    <%--layer.close(index);--%>
                    <%--if (data.code != 200){--%>
                        <%--layer.msg(data.msg);--%>
                        <%--return;--%>
                    <%--}--%>
                    <%--layer.msg(data.msg,{time:1000},function(){--%>
                        <%--parent.window.location.href = "<%=request.getContextPath()%>/index/toIndex";--%>
                    <%--});--%>
                <%--}--%>
            <%--);--%>
        <%--}--%>
    <%--});--%>

    <%--if (window.top.document.URL != document.URL) {--%>
        <%--window.top.location = document.URL;--%>
    <%--}--%>

<%--</script>--%>
<%--<body>--%>
<%--<form id="fm">--%>
    <%--用户名：<input type="text" name="username" id="username"><br>--%>
    <%--密码：<input type="password" name="password" id="password"><br>--%>
    <%--<a href="<%=request.getContextPath()%>/user/toAdd">还没有账号？点我去注册</a>--%>
    <%--<br>--%>
    <%--<input type="submit" value="登录">--%>
    <%--<a href="<%=request.getContextPath()%>/user/userUpdatePwd">忘记密码</a>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
