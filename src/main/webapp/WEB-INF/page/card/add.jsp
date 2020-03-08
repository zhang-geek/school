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
	<script type="text/javascript">

		jQuery.validator.addMethod("minNumber",function(value, element){
			var returnVal = true;
			inputZ=value;
			var ArrMen= inputZ.split(".");    //截取字符串
			if(ArrMen.length==2){
				if(ArrMen[1].length>2){    //判断小数点后面的字符串长度
					returnVal = false;
					return false;
				}
			}
			return returnVal;
		},"小数点后最多为两位");

		$(function (){
			var index = layer.load(1);
			$.post("<%=request.getContextPath()%>/card/toFindCard",
					{"_method" : "post"},
					function (data) {
						layer.close(index);
						if (data.code == 200) {
							return;
						}
						layer.msg(data.msg,{time:500},function () {
							window.location.href = "<%=request.getContextPath()%>/card/toShow";
						});
			});

			$("#fm").validate({
				//校验规则
				rules:{
					cardMoney:{
						required: true,    //要求输入不能为空
						number: true,     //输入必须是数字
						min: 20.00,          //输入的数字最小值为0.01，不能为0或者负数
						minNumber: $("#cardMoney").val()    //调用自定义验证
					}
				},

				//显示消息:
				messages: {
					cardMoney: {
						required: "请填写充值金额",
						number: "请正确输入金额",
						min: "输入最小金额为20.00",
						length: "输入数字最多小数点后两位"
					}
				}
			});
		})


		$.validator.setDefaults({
			submitHandler: function() {
				var index = layer.load(1);
				$.post("/card/addCard",
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
								layer.close(index);
								window.location.href="/card/toShow";
							});
						});
			}
		});
	</script>
</head>

<body>
	<form id="fm">
				<input type="hidden" name="_method" value="post"/>
				<input type="hidden" name="isDel" value="0"/>
				<input type="hidden" name="cardStatus" value="0"/>
		首次充值: <input type="text" name="cardMoney" id="cardMoney"><span style="color: red">首次充值会收取您两元卡费哦！</span><br/>
		<input type="submit" value="办理"><br/>
	</form>
</body>
</html>