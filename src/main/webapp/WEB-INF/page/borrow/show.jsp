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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
    <link href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" rel="stylesheet" media="all" />
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
                    html += "<td>" + borrow.typeName + "</td>";
                    html += "<td>" + borrow.number + "本</td>";
                    if (borrow.surplus > 0) {
                        html += "<td>剩余" + borrow.surplus + "天</td>";
                    } else if (borrow.surplus = 0) {
                        html += "<td>最后一天</td>";
                    } else {
                        html += "<td>逾期" + borrow.days + "天</td>";
                    }
                    html += "<td>" + borrow.statusShow + "</td>";
                    html += "<td>" + borrow.payShow + "</td>";
                    html += borrow.pay == null ? "<td></td>" : "<td>" + borrow.pay + "元</td>";
                    html += "<td>" + borrow.borrowTime + "</td>";
                    html += "<td>" + borrow.repayTime + "</td>";
                    html += "</tr>";
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
        <input type="button" value="查询" onclick="show()" class="layui-btn layui-btn-radius  layui-btn-sm"/><br/>
        <c:if test="${userRole.roleId != 1 && userRole.roleId != 6}">
            <input type='button' value='还书' onclick='toRepay()' class="layui-btn layui-btn-radius  layui-btn-sm">
            <input type='button' value='缴费' onclick='toPay()' class="layui-btn layui-btn-radius  layui-btn-sm">
        </c:if>
    </form>
    <table border="0px" class="layui-table" lay-skin="nob" >
        <thead>
            <tr>
                <th></th>
                <th>借书人</th>
                <th>书名</th>
                <th>作者</th>
                <th>图书类型</th>
                <th>借书数量</th>
                <th>剩余天数</th>
                <th>逾期状态</th>
                <th>缴费状态</th>
                <th>缴费金额</th>
                <th>借书时间</th>
                <th>到期时间</th>
            </tr>
        </thead>
        <tbody id="tbd"></tbody>
    </table>
    <div id="pageInfo"></div>
</body>
</html>
