<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/3/7
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" rel="stylesheet" media="all" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
</head>
<body>
<form id="frm">
    <input type="hidden" name="_method" value="POST"/>
    商品名称:<input type="text" name="shopName" placeholder="店名"/>
    商品类别:
    <select id="name" name="name">
        <option value="">---请选择报销人---</option>
        <c:forEach var="b" items="${baseDataList}">
            <option value="${b.id}">${b.name}</option>
        </c:forEach>
    </select><p>


    <input type="hidden" name="pageNo" id="pageNo" value="1">
    <input type="button" value="查询" onclick="show()" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger"/>&nbsp;
    <input type="button" value="添加" onclick="addShop()" class="layui-btn layui-btn-sm"/>&nbsp;
    <input type="button" value="修改" onclick="updateShop()" class="layui-btn layui-btn-sm"/>&nbsp;
</form>
<form id="fm">
    <table align="center" border="1px solid blcak" cellspacing="0" cellpadding="10" class="layui-table" lay-skin="line">
        <tr bgcolor="#CDCDCD">
            <th>0</th>
            <th>id</th>
            <th>商品名称</th>
            <th>商品类别</th>
            <th>商品价格</th>
            <th>商品(上架/下架)</th>
            <th>操作</th>
        </tr>
        <tbody id="tbs"></tbody>
    </table>
</form>
<div id="pageInfo" align="center">

</div>
</body>
<script type="text/javascript">
    $(function() {
        show();
    });

    function show() {
        $.post(
            "/shop",
            $("#frm").serialize(),
            function(result) {
                var data = result.data.shopList;
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    var shop = data[i];
                    html+="<tr>"
                    html+="<td><input type='checkbox' value='"+shop.id+"' name='sid'/></td>"
                    html+="<td>"+shop.id+"</td>"
                    html+="<td>"+shop.shopName+"</td>"
                    html+="<td>"+shop.baseName+"</td>"
                    html+="<td>"+shop.shopPrice+"</td>"
                    html+= shop.shopStatus == 1 ? "<td>上架</td>" : "<td>下架</td>"
                    html+="</tr>"
                }
                $("#tbs").html(html);
                var pageNo = $("#pageNo").val();
                var pageHtml = "<input type='button' value='上一页' onclick='page("+(parseInt(pageNo) - 1)+","+result.data.totalNum+")' class='layui-btn layui-btn-sm'>";
                pageHtml += "<input type='button' value='下一页' onclick='page("+(parseInt(pageNo) + 1)+","+result.data.totalNum+")' class='layui-btn layui-btn-sm'>";
                $("#pageInfo").html(pageHtml);
            });
    }

    function page(page, totalNum) {
        if (page < 1) {
            layer.msg('已经到首页啦!', {icon:0});
            return;
        }
        if (page > totalNum) {
            layer.msg('已经到尾页啦!!', {icon:0});
            return;
        }
        $("#pageNo").val(page);
        show();

    }



    function addShop() {
        layer.open({
            type: 2,
            title: '新增',
            shadeClose: true,
            shade: 0.3,
            area: ['30%', '80%'],
            content: "<%=request.getContextPath()%>/shp/toAddShop"
        });
    }

</script>
</html>
