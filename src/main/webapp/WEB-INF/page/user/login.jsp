<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/1/30
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" rel="stylesheet" media="all" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/md5-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
</head>
<style type="text/css">
    .error{
        color: red;
    }
</style>
<script type="text/javascript">

    $(function(){
        if ('${msg}' != '') {
            layer.msg('${msg}');
        }
        $("#fm").validate({
            rules:{
                username:{
                    required:true
                },
                password:{
                    required:true,
                    rangelength:[0,5]
                }
            },
            messages:{
                username:{
                    required:"请输入姓名"
                },
                password:{
                    required:"请输入年龄",
                    rangelength:"长度限制在{0}到{1}之间"
                }
            }
        })
    })

    $.validator.setDefaults({
        submitHandler : function() {
            $("#password").val(md5($("#password").val()));
            var index = layer.load(1);
            $.get(
                "<%=request.getContextPath()%>/users/login",
                $("#fm").serialize(),
                function(data) {
                    layer.close(index);
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/index/toIndex";
                    });
                }
            );
        }
    });

    function toAdd() {
        window.location.href = "<%=request.getContextPath()%>/user/toAdd";
    }

    if (window.top.document.URL != document.URL) {
        window.top.location = document.URL;
    }

</script>
<body>
<form id="fm" class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="username" id="username" required lay-verify="required" placeholder="请输入姓名" autocomplete="on" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="password" name="password" id="password" required lay-verify="required" placeholder="请输入密码" autocomplete="on" class="layui-input">
        </div>
    </div>
    <tr>
        <td align="center">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="登☻录" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger"/>
            <input type="button" value="没有账号？点我注册" onclick="toAdd()" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger"/><p>
        </td>
    </tr>
    <a href="<%=request.getContextPath()%>/user/userUpdatePwd">忘记密码</a>
</form>
</body>
</html>
