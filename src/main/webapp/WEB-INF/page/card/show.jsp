<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<!DOCTYPE  html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<script type="text/javascript" src = "<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
 <link href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" rel="stylesheet"  media="all" />

</head>
<script type="text/javascript">

    var totalNum = 0;

	$(function () {
		show();
	})

	
	function show() {
		var index = layer.load({
			  shade: [0.1,'#fff']
		});
		$.post("${ctx}/card/show",
				$("#fm").serialize(),
				function (data) {
					layer.close(index); 
					if(data.code != "200"){
						layer.alert(data.msg, {
							icon: 5,
							shadeClose: true
						});
						return;
					}
					var html = "";
					for (var i = 0; i < data.data.cardList.length; i++) {
						var card = data.data.cardList[i];
						totalNum = data.data.totalNum;
						html += "<tr>";
						html += "<td><input type = 'radio' name = 'id' value = '"+card.id+","+card.cardStatus+"'></td>";
						html += "<td>"+card.cardNumber+"</td>";
						html += "<td>"+card.userName+"</td>";
						html += "<td>"+card.cardMoney+"</td>";
						html += card.cardStatus == 0 ? "<td>正在使用</td>" : "<td>已挂失</td>";
						html += "<td>"+card.createTime+"</td>";
						html += "</tr>";
					}
					$("#tb").html(html);
					var pageNo = $("#pageNo").val();
					var pageInf = "<input type = 'button' value = '上一页' class='layui-btn layui-btn-normal layui-btn-radius'  onclick = 'page("+(parseInt(pageNo) - 1)+")'/ >";
					pageInf += "<input type = 'button' value = '下一页' class='layui-btn layui-btn-normal layui-btn-radius' onclick = 'page("+(parseInt(pageNo) + 1)+")' />";
					$("#pageInfo").html(pageInf);

				})
	}

	function page(page) {
		if(page < 1) {
			layer.alert("已在当前首页", {
				icon: 3,
				shadeClose: true
			});
			return;
		}
		if(totalNum < page) {
			layer.alert("已到最后一页", {
				icon: 3,
				shadeClose: true
			});
			return;
		}
		$("#pageNo").val(page);
		show();
		return;
	}

	function addMoney() {
		var val = $('input:radio:checked').val();
		var ids = val.split(",");
		if(ids[1] == 1){
			layer.msg('此卡已挂失', {icon: 2, time: 1000});
			return;
		}
		layer.open({
			type: 2,
			title: '充值',
			shadeClose: true,
			maxmin: true, //开启最大化最小化按钮
			shade: 0.8,
			area: ['380px', '90%'],
			content: '<%=request.getContextPath()%>/card/toAddMoney/'+ids[0]
		});
}

	function updateStatus() {
        var val = $('input:radio:checked').val();
        var ids = val.split(",");
		if(ids[1] == 1){
			layer.msg('此卡已挂失', {icon: 2, time: 1000});
			return;
		}
		layer.confirm('确定挂失嘛', {icon: 3, title: '询问框'}, function (index) {
			//do something
			layer.close(index);
			var index1 = layer.load(1);
			$.post("/card/updateStatusOrIsDel",
					{_method: "put",
						"id": ids[0],
						"cardStatus": 1},
					function (data) {
						layer.close(index1);
						if(data.code != "200"){
							layer.alert(data.msg, {icon: 2, shadeClose: true});
							return;
						}
						layer.msg(data.msg, {icon: 6,
							shadeClose: true,
							time: 1000
						}, function(){
							window.location.href = "${ctx}/card/toShow";
						});
					});
		})
	}

	function replaceCard() {
        var val = $('input:radio:checked').val();
        var ids = val.split(",");
		if(ids[1] == 0){
			layer.msg('您的这张卡并未挂失', {icon: 2, time: 1000});
			return;
		}
		layer.confirm('确定补办嘛', {icon: 3, title: '询问框'}, function (index) {
			//do something
			layer.close(index);
			var index1 = layer.load(1);
			$.post("/card/replaceCard",
					{_method: "put",
						"id": ids[0]},
					function (data) {
						layer.close(index1);
						if(data.code != "200"){
							layer.alert(data.msg, {icon: 2, shadeClose: true});
							return;
						}
						layer.msg(data.msg, {
							icon: 6,
							shadeClose: true,
							time: 1000
						}, function(){
							window.location.href = "${ctx}/card/toShow";
						});
					});
		})
	}

	function del() {
		var val = $('input:radio:checked').val();
		var ids = val.split(",");
		if(ids[1] == 0){
			layer.msg('这张卡卡正在使用！不能注销！', {icon: 2, time: 1000});
			return;
		}
		layer.confirm('确定注销嘛', {icon: 3, title: '询问框'}, function (index) {
			//do something
			layer.close(index);
			var index1 = layer.load(1);
			$.post("/card/updateStatusOrIsDel",
					{_method: "put",
						"id": ids[0],
						"isDel": 1},
					function (data) {
						layer.close(index1);
						if(data.code != "200"){
							layer.alert(data.msg, {icon: 2, shadeClose: true});
							return;
						}
						layer.msg(data.msg, {icon: 6,
							shadeClose: true,
							time: 1000
						}, function(){
							window.location.href = "${ctx}/card/toShow";
						});
					});
		})
	}
	function findByWhere() {
		$("#pageNo").val(1);
		show();
	}
	
</script>
<body>
	<form id = "fm">
		<input type = 'hidden' value = '1' name = "pageNo" id = 'pageNo'/>
		<shrio:hasPermission name="card:addMoney">
			<input type="button" value="充值" onclick="addMoney()" class='layui-btn layui-btn-normal layui-btn-radius'>
		</shrio:hasPermission>
		<shrio:hasPermission name="card:update">
			<input type="button" value="挂失" onclick="updateStatus()" class='layui-btn layui-btn-normal layui-btn-radius'>
		</shrio:hasPermission>
		<shrio:hasPermission name="card:replaceCard">
			<input type="button" value="补办" onclick="replaceCard()" class='layui-btn layui-btn-normal layui-btn-radius'>
		</shrio:hasPermission>
		<shrio:hasPermission name="card:updateIsDel">
			<input type="button" value="注销" onclick="del()" class='layui-btn layui-btn-normal layui-btn-radius'>
		</shrio:hasPermission>
	</form>

	<table class="layui-table">
		<tr>
			<th></th>
			<th>卡号</th>
			<th>用户名</th>
			<th>余额</th>
			<th>校园卡状态</th>
			<th>办理时间</th>
		</tr>
		<tbody id = "tb">
		</tbody>
	</table>
	<div id = "pageInfo" align="center">
	</div>
</body>
</html>