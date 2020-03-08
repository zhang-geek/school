<%--
  Created by IntelliJ IDEA.
  User: cf
  Date: 2020/3/7
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        show();
    })

    function show() {
        var index = layer.load(1);
        $.post(
            "/users/list",
            $("#fm").serialize(),
            function (data) {
                layer.close(index);
                if (data.code != 200) {
                    layer.alert(data.msg);
                    return;
                }
                var html = "";
                for (var i = 0; i < data.data.list.length; i++) {
                    var user = data.data.list[i];
                    html += "<tr>";
                    html += "<td><input type='checkbox' name='id' value='"+user.id+"'></td>";
                    html += "<td>" + user.id +"</td>";
                    html += "<td>" + user.username +"</td>";
                    html += "<td>" + user.userPhone +"</td>";
                    html += "<td>" + user.userEmail +"</td>";
                    html += "<td>" + user.roleName +"</td>";
                    html += user.userStatus == 1 ? "<td>正常</td>" : "<td>未激活</td>";
                    html += "<td>" + user.createTime +"</td>";
                    html += user.lastLoginTime == null ? "<td>暂无</td>" : "<td>" + user.lastLoginTime +"</td>";
                    html += "</tr>";
                }
                $("#tbd").html(html);
                var pageHtml = "";
                var pageNo = parseInt($("#pageNo").val());
                pageHtml += "<input type='button' value='上一页' onclick='page("+data.data.totalNum+","+(pageNo - 1)+")'/>";
                pageHtml += "<input type='button' value='下一页' onclick='page("+data.data.totalNum+","+(pageNo + 1)+")'/>";
                $("#pageInfo").html(pageHtml);
            }
        );
    }

    function page(totalNum, pageNo) {
        if (pageNo < 1) {
            layer.msg("已是第一页");
            return;
        }
        if (pageNo > totalNum) {
            layer.msg("已是最后一页");
            return;
        }
        $("#pageNo").val(pageNo);
        show();
    }
    //查询
    function fuzzySearch() {
        $("#pageNo").val(1);
        show();
    }

    //修改
    function toUpdate() {
        var boxValue = $("input[name='id']:checked");
        if (boxValue.length < 1) {
            layer.msg('请选择一条信息', {icon: 6, time: 1000});
            return;
        }
        if (boxValue.length > 1) {
            layer.msg('只能选择一条信息', {icon: 6, time: 1000});
            return;
        }
        var array = boxValue.val().split(",");

        layer.open({
            type: 2,
            title: '修改页面',
            shadeClose: true,
            shade: 0.5,
            area: ['380px', '90%'],
            content: "/user/toUpdate?id=" + array[0]
        });

    }
</script>
<body>
<form id="fm">
    <input type="hidden" value="1" name="pageNo" id="pageNo">
        模糊查：<input type="text" name="username" placeholder="用户名，手机号，邮箱"><br>
        角&nbsp;&nbsp;&nbsp;&nbsp;色：
        <c:forEach var="r" items="${roleList}">
            <input type="radio" name="userRole" value="${r.id}">${r.roleName}
        </c:forEach><br>
        状&nbsp;&nbsp;&nbsp;&nbsp;态：
        <select name="userStatus" >
            <option value="-1">请选择</option>
            <option value="1">正常</option>
            <option value="0">未激活</option>
        </select><br>

        <input type="button" value="查询" onclick="fuzzySearch()">
    <input type="button" value="修改" onclick="toUpdate()">
</form>
<table border="1px" cellpadding="5" cellspacing="0">
    <tr>
        <th></th>
        <th>id</th>
        <th>用户名</th>
        <th>手机号</th>
        <th>邮箱</th>
        <th>级别</th>
        <th>状态</th>
        <th>注册时间</th>
        <th>最后登录时间</th>
    </tr>
    <tbody id="tbd"></tbody>
</table>
<div id="pageInfo"></div>
</body>
</html>
