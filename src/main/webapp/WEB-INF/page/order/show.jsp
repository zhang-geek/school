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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
    <link href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" rel="stylesheet" media="all" />

</head>
<body>

<form id="fm">
    <input type="hidden" value="1" name="nowPage" id="nowPage">
</form>
<table border="0px" cellpadding="10" cellspacing="0" class="layui-table" lay-skin="nob">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
        <tr>
            <th></th>
            <th>商品名</th>
            <th>购买人</th>
            <th>商品价格</th>
            <th>商品编号</th>
        </tr>
    </thead>
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
        $.post("/order/list",
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
                    html += "<td><input type='checkbox' value=" + d.id+" name='id' '></td>"
                    html += "<td>" + d.shopName + "</td>"
                    html += "<td>" + d.userName + "</td>"
                    html += "<td>" + d.shopPrice + "</td>"
                    html += "<td>" + d.orderNum + "</td>"
                    html += "</tr>"
                }
                $("#tbd").html(html);
                var pageHtml = "";
                var nowPage = $("#nowPage").val();
                pageHtml += "<input type='button' value='上一页'  onclick='page(" + (parseInt(nowPage) - 1) + ")' class=\"layui-btn layui-btn-radius  layui-btn-sm\"/>"
                pageHtml += "<input type='button' value='下一页'  onclick='page(" + (parseInt(nowPage) + 1) + "," + data.data.totalPage + ")' class=\"layui-btn layui-btn-radius  layui-btn-sm\"/>"
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


</script>
</html>