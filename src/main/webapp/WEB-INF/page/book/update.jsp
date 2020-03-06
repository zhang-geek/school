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
				<input type="hidden" name="_method" value="PUT"/>
				<input type="hidden" name="id" value="${book.id}"/>
		书名: <input type="text" name="bookName" id="bookName" value="${book.bookName}"><br/>
		类型:
		<c:forEach items="${resourseList}" var="type">
			<input type="radio" name="type" value="${type.id}" id="type" <c:if test="${book.type == type.id}">checked</c:if> >${type.resourceName}
		</c:forEach><br/>
		作者: <input type="text" name="author" id="author" value="${book.author}"><br/>
		库存: <input type="number" name="count" id="count" value="${book.count}"><br/>
		上线时间: <input type="text" name="shelfTime" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'})" id="shelfTime" value="${book.shelfTimeShow}"><br/>
		<input type="submit" value="修改"><br/>
	</form>
</body>
<script type="text/javascript">


	$(function (){
		$("#fm").validate({
			errorPlacement: function (error, element) {
				if (element.is("[name='type']") || element.is("[name='type']")){ //如果需要验证的元素名为userHobby
					 error.appendTo($("#parent"));   //错误增加在id为'checkbox-lang'中
				} else {
					error.insertAfter(element);//其他的就显示在添加框后
				}

			},
			//校验规则
			rules:{
				//1:name:名称:"校验规则"
				//2:name:名字:{
					//校验规则:"值"
					//校验规则:"值"
				//}
				/* userName:"required"  */
				bookName:{
					required:true,
					rangelength:[2,6],
					remote:{
						type:"post",
						url:"/book/distinct",
						data:{
							bookName:function() {
								return $("#bookName").val();
							},
							id:${book.id},
							dataType:"json",
							function (data){
								return data;
							}
						}
					}
				},
				type:{
					required:true
				},
				author:{
					required:true
				},
				count:{
					required:true
				},
				shelfTime:{
					required:true
				}
			},
			
			//显示消息:
			messages:{
				bookName:{
					required:"不能为空",
					rangelength:"长度必须在{0}和{1}之间",
					remote:"该图书已存在"
				},

				type:{
					required:"不能为空"
				},
				author:{
					required:"不能为空"
				},
				count:{
					required:"不能为空"
				},
				shelfTime:{
					required:"不能为空"
				}
			}
		});
	})


	$.validator.setDefaults({
	    submitHandler: function() {
	    	var index = layer.load(1);
			$.post("/book",
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
							parent.window.location.href="/book/toShow";
						});
			});
	    }
	});
</script>
</html>