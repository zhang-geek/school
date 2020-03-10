<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宿舍展示</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<body>

<form id="fm">
    <input type="hidden" value="1" name="nowPage" id="nowPage">
</form>
<input type="button" value="添加宿舍" onclick="addDorm()" />&nbsp;
<table border="1px" cellpadding="10" cellspacing="0">
    <tr>
        <th></th>
        <th>宿舍号</th>
        <th>宿舍状态</th>
    </tr>
    <tbody id="tbd"></tbody>
</table>
</body>
<script type="text/javascript">
    $(function () {
        show();
    })

    function show() {
        var index = layer.load(1, {shade: 0.5});
        $.post("/dorm/list",
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
                    html += "<td><input type='checkbox' value=" + d.id+" name='id' '></td>"
                    html += "<td>" + d.dormNumber + "</td>"
                    html += "<td>" + d.dormStatusShow + "</td>"
                    html +="<td><input type='button' value='删除' onclick='addDorm("+d.id+")'/>";
                    html +="<td><input type='button' value='修改宿舍信息' onclick='updateDorm("+d.id+")'/>";
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

    function addDorm() {
        layer.open({
            type: 2,
            title: '新增',
            shadeClose: true,
            shade: 0.3,
            area: ['30%', '80%'],
            content: "<%=request.getContextPath()%>/dorm/toAddDorm"
        });
    }
    function updateDorm() {
        window.location.href="<%=request.getContextPath()%>/dorm/toUpdateById"
    }

</script>
</html>
