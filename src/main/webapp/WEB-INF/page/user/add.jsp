
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/md5-min.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
</head>
<style type="text/css">
    .error{
        color: red;
    }
</style>
<script type="text/javascript">

    jQuery.validator.addMethod("phone", function(value, element) {
        return /^1[3456789][0-9]{9}$/.test(value);
    }, "手机号码格式错误!");

    jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
        return value != $(param).val();
    }, $.validator.format("两次输入不能相同!"));

    $(function(){
        $("#fm").validate({
            rules:{
                username:{
                    required:true,
                    rangelength:[0,8],
                    remote: {
                        type: 'POST',
                        url: "/users/deDuplicate",
                        data:{
                            username:function() {
                                return $("#username").val();
                            },
                            dataType:"json"
                        }
                    }
                },
                password:{
                    required:true,
                    rangelength:[0,6]
                },
                password2:{
                    required:true,
                    rangelength:[0,6],
                    equalTo: "#password"
                },
                userPhone:{
                    required:true,
                    phone:true,
                    remote: {
                        type: 'POST',
                        url: "/users/deDuplicate",
                        data:{
                            username:function() {
                                return $("#userPhone").val();
                            },
                            dataType:"json",
                            dataFilter:function(data,type){
                                return data;
                            }
                        }
                    }
                },
                userEmail:{
                    required:true,
                    email:true,
                    remote: {
                        type: 'POST',
                        url: "/users/deDuplicate",
                        data:{
                            username:function() {
                                return $("#userEmail").val();
                            },
                            dataType:"json",
                            dataFilter:function(data,type){
                                return data;
                            }
                        }
                    }
                }

            },
            messages:{
                username:{
                    required:"请输入姓名",
                    rangelength:"长度限制在{0}到{1}之间",
                    remote:"用户名已存在~"
                },
                password:{
                    required:"请输入密码",
                    rangelength:"长度限制在{0}到{1}之间"
                },
                password2:{
                    required:"请再次输入密码~",
                    rangelength:"长度限制在{0}到{1}之间",
                    equalTo: "与上次输入不一致~"
                },
                userPhone:{
                    required:"请输入有效的手机号",
                    remote: "该手机号已被使用~"
                },
                userEmail:{
                    required:"请输入有效的邮箱地址",
                    email:"邮箱格式错误",
                    remote:"该邮箱已被使用~"
                }
            }
        })
    })

    $.validator.setDefaults({
        submitHandler : function() {
            var salt = md5(new Date().valueOf() + Math.floor(Math.random()*100000));
            $("#password").val(md5(md5($("#password").val()) + salt));
            $("#salt").val(salt);
            var index = layer.load(1);
            $.post(
                "/users/add",
                $("#fm").serialize(),
                function(data) {
                    layer.close(index);
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/user/toLogin";
                    });
                }
            );
        }
    });

</script>
<body>
<form id="fm">
    <input type="hidden" name="_method" value="POST"/>
    <input type="hidden" id="salt" name="salt">
    用户名：<input type="text" name="username" id="username"><br>
    密码：<input type="password" name="password" id="password"><br>
    确认密码：<input type="password" name="password2"><br>
    手机：<input type="text" name="userPhone" id="userPhone"><br>
    邮箱：<input type="text" name="userEmail" id="userEmail"><br>
    职位：<input type="radio" name="userRole" value="3" checked>学生
        <input type="radio" name="userRole" value="5">老师<br>
    班级：
        <select name="userClass">
            <c:forEach items="${classList}" var="c">
                <option value="${c.classNum}">${c.classNum}</option>
            </c:forEach>
        </select><br>
    <a href="<%=request.getContextPath()%>/user/toLogin">已有账号？去登陆</a><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
