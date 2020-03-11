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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
    <link href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" rel="stylesheet" media="all" />

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
                    html += "<shiro:hasPermission name='role:related_resource'><a href='javascript:toRelatedResource("+role.id+")' class=\"layui-btn layui-btn-radius  layui-btn-sm\">关联资源</a></shiro:hasPermission>";
                    html += "<shiro:hasPermission name='role:update'> | <a href='javascript:toUpdate("+role.id+")' class=\"layui-btn layui-btn-radius  layui-btn-sm\">编辑</a></shiro:hasPermission>";
                    html += "<shiro:hasPermission name='role:delete'> | <a href='javascript:del("+role.id+")' class=\"layui-btn layui-btn-radius  layui-btn-sm\">删除</a></shiro:hasPermission>";
                    html += "</td>";
                    html += "</tr>";
                }
                $("#tbd").html(html);
                var pageHtml = "";
                var pageNo = parseInt($("#pageNo").val());
                pageHtml += "<input type='button' value='上一页' onclick='page("+data.data.totalNum+","+(pageNo - 1)+")' class=\"layui-btn layui-btn-radius  layui-btn-sm\"/>";
                pageHtml += "<input type='button' value='下一页' onclick='page("+data.data.totalNum+","+(pageNo + 1)+")' class=\"layui-btn layui-btn-radius  layui-btn-sm\"/>";
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
                {"id":id, "isDel":1},
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
    <input type="button" value="新增" onclick="toAdd()" class="layui-btn layui-btn-radius  layui-btn-sm">
</shiro:hasPermission>
<table border="0px" class="layui-table" lay-skin="nob">
    <colgroup>
        <col width="100">
        <col width="100">
        <col width="500">
        <col>
    </colgroup>
    <thead>
        <tr>
            <th>编号</th>
            <th>角色名</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody id="tbd"></tbody>
</table>
<div id="pageInfo"></div>
</body>
</html>
