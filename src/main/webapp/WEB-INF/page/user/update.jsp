<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery.validate.js"></script>
<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/md5-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>


	<style>
	.error{
		color:red;
	}
</style>
</head>

<body>
	<form id="fm">
		<input type="hidden" id="salt" name="salt">
				<input type="hidden" name="_method" value="PUT"/>
				<input type="hidden" name="id" value="${user.id}"/>
		姓名: <input type="text" name="username" id="username" value="${user.username}"><br/>
		密码：<input type="text" name="password" id="password" value="${user.password}"><br/>
		密码：<input type="text" name="password2" value="${user.password}"><br/>
		手机号: <input type="text" name="userPhone" id="userPhone" value="${user.userPhone}"><br/>
		邮箱: <input type="text" name="userEmail" id="userEmail" value="${user.userEmail}"><br/>
		<input type="submit" value="修改"><br/>
	</form>
</body>
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
				},
				userEmail:{
					required:true,
					email:true,
				}

			},
			messages:{
				username:{
					required:"请输入姓名",
					rangelength:"长度限制在{0}到{1}之间",
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
				},
				userEmail:{
					required:"请输入有效的邮箱地址",
					email:"邮箱格式错误",
				}
			}
		})
	})


	$.validator.setDefaults({
	    submitHandler: function() {
			var salt = md5(new Date().valueOf() + Math.floor(Math.random()*100000));
			$("#password").val(md5(md5($("#password").val()) + salt));
			$("#salt").val(salt);
	    	var index = layer.load(1);
			$.post("/users",
					$("#fm").serialize(),
					function(data){
						layer.close(index);
						layer.msg(data.msg, {
							  icon: 1,
							  time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							if (data.code != "200") {
								return;
							}
							parent.window.location.href="/user/toShow";
						});
			});
	    }
	});
</script>
</html>