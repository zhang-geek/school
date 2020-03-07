<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>

</head>
<body>

<form id="fm">
    <input type="hidden" name="_method" value="POST"/>
    根据书名查询:
    <input type="text" name="bookName"/><br/>
    根据上架时间查询:
    <input type="text" name="startTime"/>~<input type="text" name="endTime"/><br/>
    <input type="button" value="搜索" onclick="show()"/><br/>
    <c:if test="${userRole.roleId == 1}">
        <input type='button' value='新增' onclick='add()'>
        <input type='button' value='修改' onclick='toUpdate()'>
        <input type='button' value='删除' onclick='del()'>
        <input type='button' value='上下架' onclick='updateStatus()'>
    </c:if>
    <c:if test="${userRole.roleId == 2}">
        <input type='button' value='借书' onclick='toBorrow()'>
    </c:if>
    <input type="hidden" value="0" name="isDel"><br/>
    <input type="hidden" value="${type}" name="type"><br/>
    <input type="hidden" value="1" name="nowPage" id="nowPage">
</form>
<table border="1px" cellpadding="10" cellspacing="0">
    <tr>
        <th></th>
        <th>书名</th>
        <th>作者</th>
        <th>类型</th>
        <th>状态</th>
        <th>库存</th>
        <th>上架时间</th>
    </tr>
    <tbody id="tbd"></tbody>
</table>
<div id="pageInfo"></div>

</body>
<script type="text/javascript">
    $(function () {
        show();
    })

    function show() {
        var index = layer.load(1, {shade: 0.5});
        $.post("/book/list",
            $("#fm").serialize(),
            function (data) {
                layer.close(index);
                if (data.code != 200) {
                    layer.alert(data.msg);
                    return;
                }
                var html = "";
                for (var i = 0; i < data.data.list.length; i++) {
                    var d = data.data.list[i];
                    html += "<tr>"
                    html += "<td><input type='checkbox' value=" + d.id + "," + d.status + " name='id' '></td>"
                    html += "<td>" + d.bookName + "</td>"
                    html += "<td>" + d.author + "</td>"
                    html += "<td>" + d.typeName + "</td>"
                    html += d.status == 0 ? "<td>上架</td>" : "<td>下架</td>"
                    html += "<td>" + d.count + "</td>"
                    html += "<td>" + d.shelfTime + "</td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
                var pageHtml = "";
                var nowPage = $("#nowPage").val();
                pageHtml += "<input type='button' value='上一页'  onclick='page(" + (parseInt(nowPage) - 1) + ")'/>"
                pageHtml += "<input type='button' value='下一页'  onclick='page(" + (parseInt(nowPage) + 1) + "," + data.data.totalPage + ")'/>"
                $("#pageInfo").html(pageHtml);

            });
    }

    function page(nowPage, totalPage) {
        if (nowPage < 1) {
            layer.msg("首页", {time: 1000});
            return;
        }
        if (nowPage > totalPage) {
            layer.msg("尾页", {time: 1000});
            return;
        }
        $("#nowPage").val(nowPage);
        show();
    }


    function add() {

        layer.open({
            type: 2,
            title: '新增页面',
            shadeClose: true,
            shade: 0.5,
            area: ['380px', '90%'],
            content: "/book/toAdd"
        });

    }

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
            content: "/book/toUpdate?id=" + array[0]
        });

    }

    function del() {
        var index1 = layer.load(1);

        var array = new Array();
        $("input[name = 'id']:checked").each(function(){
            array.push($(this).val().split(",")[0]);
        })

        if (array.length < 1) {
            layer.msg('请至少选择一条信息', {icon: 6,time:1000});
            layer.close(index1);
            return;
        }
        var str = array.join(",");

        layer.confirm('确定删除嘛', {icon: 3, title: '询问框'}, function (index) {
            //do something
            layer.close(index);
            var index1 = layer.load(1);
            $.post("/book",
                {_method: "DELETE", "ids": str, isDel: 1},
                function (data) {
                    layer.close(index1);
                    layer.msg(data.msg, {icon: 6, time: 1000});
                    if (data.code != "200") {
                        return;
                    }
                    window.location.href = "/book/toShow";
                });
        })
    }

    function updateStatus() {
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

        var status = "";
        if (array[1] == 0) {
            array[1] = 1;
            status = "下架";
        } else {
            array[1] = 0;
            status = "上架";
        }

        layer.confirm("确定要将图书"+status+"嘛", {icon: 3, title: "询问框"}, function (index) {
			layer.close(index);
			var index1 = layer.load(1);
			$.post("/book",
				{_method: "PUT", "id": array[0], status: array[1]},
				function (data) {
                    layer.close(index1);
                    layer.msg(data.msg, {icon: 6, time: 1000});
                    if (data.code != "200") {
                        return;
                    }
                    window.location.href = "/book/toShow";
                }
            )
    	})
    }

    function toBorrow() {
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
            title: '借书页面',
            shadeClose: true,
            shade: 0.5,
            area: ['380px', '90%'],
            content: "/book/toBorrow?id=" + array[0]
        });

    }



</script>
</html>