<%--
  Created by IntelliJ IDEA.
  User: cf
  Date: 2020/3/8
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>

</head>
<script type="text/javascript">
    $(function () {
        show();
    })

    function show() {
        var index = layer.load(1, {shade: 0.5});
        $.post("/borrow/list",
            $("#fm").serialize(),
            function (data) {
                layer.close(index);
                if (data.code != 200) {
                    layer.alert(data.msg);
                    return;
                }
                var html = "";
                for (var i = 0; i < data.data.list.length; i++) {
                    var borrow = data.data.list[i];
                    html += "<tr>";
                    html += "<td><input type='checkbox' value=" + borrow.id + " name='id' '></td>"
                    html += "<td>" + borrow.userName + "</td>";
                    html += "<td>" + borrow.bookName + "</td>";
                    html += "<td>" + borrow.author + "</td>";
                    html += "<td>" + borrow.number + "</td>";
                    html += "<td>" + borrow.statusShow + "</td>";
                    html += "<td>" + borrow.payShow + "</td>";
                    html += "<td>" + borrow.typeName + "</td>";
                    html += "<td>" + borrow.borrowTime + "</td>";
                    html += "<td>" + borrow.repayTime + "</td>";
                    html += "</tr>";
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
    /**
     * 去还书 chengf
     */
    function toRepay() {
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

        layer.confirm("确定要将图书还回去嘛", {icon: 3, title: "询问框"}, function (index) {
            layer.close(index);
            var index1 = layer.load(1);
            $.post("/borrow/repay",
                {_method: "PUT", "id": array[0]},
                function (data) {
                    layer.close(index1);
                    layer.msg(data.msg, {icon: 6, time: 1000});
                    if (data.code != "200") {
                        return;
                    }
                    window.location.href = "/borrow/toShow";
                }
            )
        });
    }
    /**
     * 去缴费 chengf
     */
    function toPay() {
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
            title: '缴费页面',
            shadeClose: true,
            shade: 0.5,
            area: ['380px', '90%'],
            content: "/borrow/toPay?id=" + array[0]
        });

    }
</script>
<body>
    <form id="fm">
        <input type="hidden" name="_method" value="POST"/>
        <input type="hidden" value="0" name="isDel"><br/>
        <input type="hidden" value="1" name="nowPage" id="nowPage">
        根据书名查询:
        <input type="text" name="bookName"/>
        <input type="button" value="查询" onclick="show()"/><br/>
        <input type='button' value='还书' onclick='toRepay()'>
        <input type='button' value='缴费' onclick='toPay()'>
    </form>
    <table border="1px" cellpadding="10" cellspacing="0">
        <tr>
            <th></th>
            <th>借书人</th>
            <th>书名</th>
            <th>作者</th>
            <th>借书数量</th>
            <th>逾期状态</th>
            <th>缴费状态</th>
            <th>图书类型</th>
            <th>借书时间</th>
            <th>到期时间</th>
        </tr>
        <tbody id="tbd"></tbody>
    </table>
    <div id="pageInfo"></div>
</body>
</html>
