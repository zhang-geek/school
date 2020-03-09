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
		手机号: <input type="text" name="userPhone" id="userPhone"><br/>
		验证码: <input type="text" name="code" id="code">  <input type="button" value="点击发送验证码"><br/>
		<input type="submit" value="修改"><br/>
	</form>
</body>
<script type="text/javascript">

	jQuery.validator.addMethod("phone", function(value, element) {
		return /^1[3456789][0-9]{9}$/.test(value);
	}, "手机号码格式错误!");

	$(function(){
		$("#fm").validate({
			rules:{
				userPhone:{
					required:true,
					phone:true,
				},
				code:{
					required:true,
					code:true,
				}

			},
			messages:{
				userPhone:{
					required:"请输入有效的手机号",
				}
			}
		})
	})



</script>
</html>