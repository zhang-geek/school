<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/1/30
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
</head>
<script type="text/javascript">

    $(function () {
        search();
    })

    function search() {
        var index = layer.load(1);
        $.post(
            "<%=request.getContextPath()%>/role/show",
            $("#fm").serialize(),
            function (data) {
                layer.close(index);
                if (data.code != 200) {
                    layer.alert(data.msg);
                    return;
                }
                var html = "";
                for (var i = 0; i < data.data.list.length; i++) {
                    var role = data.data.list[i];
                    html += "<tr>";
                    html += "<td>" + role.id +"</td>";
                    html += "<td>" + role.roleName +"</td>";
                    html += "<td>";
                    html += "<shiro:hasPermission name='role:related_resource'><a href='javascript:toRelatedResource("+role.id+")'>关联资源</a></shiro:hasPermission>";
                    html += "<shiro:hasPermission name='role:update'> | <a href='javascript:toUpdate("+role.id+")'>编辑</a></shiro:hasPermission>";
                    html += "<shiro:hasPermission name='role:delete'> | <a href='javascript:del("+role.id+")'>删除</a></shiro:hasPermission>";
                    html += "</td>";
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
        search();
    }

    function toUpdate(id) {
        //iframe层
        layer.open({
            type: 2,
            title: '修改',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['380px', '90%'],
            content: '<%=request.getContextPath()%>/role/toUpdate?id='+id
        });
    }

    function toRelatedResource(id) {
        //iframe层
        layer.open({
            type: 2,
            title: '修改',
            shadeClose: true,
            maxmin: true, //开启最大化最小化按钮
            shade: 0.8,
            area: ['380px', '90%'],
            content: '<%=request.getContextPath()%>/role/toRelatedResource?id='+id
        });
    }
    
    function del(id) {
        layer.confirm('确认删除？', {icon: 3, title:'提示'}, function(index){
            $.post(
                "${ctx}/role/updateIsDel",
                {"id":id, "isDel":0},
                function (data) {
                    layer.msg(data.msg,{time:500},function () {
                        window.location.href = "<%=request.getContextPath()%>/role/toShow";
                    });
                }
            );
            layer.close(index);
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
            content: '<%=request.getContextPath()%>/role/toAdd'
        });
    }
</script>
<body>
<form id="fm">
    <input type="hidden" value="1" name="pageNo" id="pageNo">
</form>
<shiro:hasPermission name='role:add'>
    <input type="button" value="新增" onclick="toAdd()">
</shiro:hasPermission>
<table border="1px" cellpadding="5" cellspacing="0">
    <tr>
        <th>编号</th>
        <th>角色名</th>
        <th>操作</th>
    </tr>
    <tbody id="tbd"></tbody>
</table>
<div id="pageInfo"></div>
</body>
</html>
