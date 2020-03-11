<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/3/8
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
<head>
    <title>班级管理</title>
    <link href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" rel="stylesheet" media="all" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        show();
    })

    function show() {
        var index = layer.load(1);
        $.post(
            "/cla/list",
            $("#fm").serialize(),
            function (data) {
                layer.close(index);
                if (data.code != 200) {
                    layer.alert(data.msg);
                    return;
                }
                var html = "";
                for (var i = 0; i < data.data.list.length; i++) {
                    var cla = data.data.list[i];
                    html += "<tr>";
                    html += "<td><input type='checkbox' name='ids' value='"+cla.id+"'></td>"
                    html += "<td>" + cla.id +"</td>";
                    html += "<td>" + cla.classNum +"</td>";
                    html += "<td>" + cla.classStatusShow +"</td>";
                    html += "<td><input type='button' value='查看班内学生' onclick='toShowStudent("+cla.classNum+")' class='layui-btn'></td>";
                    html += "</tr>";
                }
                $("#tbd").html(html);
                var pageHtml = "";
                var pageNo = parseInt($("#pageNo").val());
                pageHtml += "<input type='button' value='上一页' onclick='page("+data.data.totalNum+","+(pageNo - 1)+")' class='layui-btn' align='center'/>";
                pageHtml += "<input type='button' value='下一页' onclick='page("+data.data.totalNum+","+(pageNo + 1)+")' class='layui-btn' align='center'/>";
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

    //模糊查询
    function fuzzySearch() {
        $("#pageNo").val(1);
        show();
    }

    function getIds() {
        var ids=new Array();
        $(":checkbox[name='ids']:checked").each(function () {
            ids.push($(this).val());
        })
        return ids;
    }

    function toShowStudent(classNum) {
        window.location.href = "${ctx}/user/toShow?classNum=" + classNum;
    }

    function toUpdate() {
        var ids = getIds();
        if (ids.length < 1){
            layer.msg("请选择一条数据");
            return;
        }
        if (ids.length > 1) {
            layer.msg("只能选择一条数据！");
            return;
        }
        layer.open({
            type: 2,
            title: '修改',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['380px', '90%'],
            content: '<%=request.getContextPath()%>/cla/toUpdate?id='+ids[0]
        });
    }

    function toAdd() {
        layer.open({
            type: 2,
            title: '修改',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['380px', '90%'],
            content: '<%=request.getContextPath()%>/cla/toAdd'
        });
    }

    function del() {
        var ids = getIds();
        if (ids.length < 1){
            layer.msg("请选择一条数据");
            return;
        }
        layer.confirm('确认删除？', {icon: 3, title:'提示'}, function(index){
            $.post(
                "${ctx}/cla",
                {"ids":ids, "_method":"DELETE"},
                function (data) {
                    layer.msg(data.msg,{time:500},function () {
                        window.location.href = "<%=request.getContextPath()%>/cla/toShow";
                    });
                }
            );
            layer.close(index);
        });
    }
</script>
<body>
<form id="fm">
    <input type="hidden" value="1" name="pageNo" id="pageNo">
        班&nbsp;&nbsp;级：<input type="text" name="classNum" placeholder="班级"><br>
        状&nbsp;&nbsp;态：
        <select name="classStatus" >
            <option value="-1">请选择</option>
            <option value="0">未开班</option>
            <option value="1">已开班</option>
            <option value="2">已结课</option>
        </select><br>
        <input type="button" value="查询" onclick="fuzzySearch()">
</form>
<shiro:hasPermission name="cla:add">
    <input type="button" value="新增" onclick="toAdd()" class='layui-btn'>
</shiro:hasPermission>
<shiro:hasPermission name="cla:update">
    <input type="button" value="修改" onclick="toUpdate()" class='layui-btn'>
</shiro:hasPermission>
<shiro:hasPermission name="cla:delete">
    <input type="button" value="删除" onclick="del()" class='layui-btn'>
</shiro:hasPermission>
<table border="1px" cellpadding="5" cellspacing="0" class="layui-table">
    <tr>
        <th></th>
        <th>id</th>
        <th>班级</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <tbody id="tbd"></tbody>
</table>
<div id="pageInfo"></div>
</body>
</html>
