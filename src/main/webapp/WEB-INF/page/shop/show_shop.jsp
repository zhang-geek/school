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
    商品名称:<input type="text" name="shopName" placeholder="商品名称"/>
    商品类别:
    <select id="name" name="baseDataId">
        <option value="">---请选择商品类别---</option>
        <c:forEach var="b" items="${baseDataList}">
            <option value="${b.id}">${b.name}</option>
        </c:forEach>
    </select><p>
    <input type="hidden" name="pageNo" id="pageNo" value="1">
    <input type="button" value="查询" onclick="show()" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger"/>&nbsp;
    <input type="button" value="添加" onclick="addShop()" class="layui-btn layui-btn-sm"/>&nbsp;
    <input type="button" value="修改" onclick="updateShop()" class="layui-btn layui-btn-sm"/>&nbsp;
    <input type="button" value="删除" onclick="delShop()" class="layui-btn layui-btn-sm"/>&nbsp;
    <input type="button" value="上架/下架" onclick="updateStatus()" class="layui-btn layui-btn-sm"/>&nbsp;

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
                    html+="<td><input type='checkbox' value='"+shop.id+"' name='shopId'/></td>"
                    html+="<input type='hidden' value='"+shop.shopStatus+"' id='"+shop.id+"'/>"
                    html+="<td>"+shop.id+"</td>"
                    html+="<td>"+shop.shopName+"</td>"
                    html+="<td>"+shop.baseName+"</td>"
                    html+="<td>"+shop.shopPrice+"</td>"
                    html+= shop.shopStatus == 0 ? "<td>上架</td>" : "<td>下架</td>"
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
            content: "<%=request.getContextPath()%>/shop/toAddShop"
        });
    }

    function updateShop(){
        var upShop = $('input[name="sid"]:checked');
        var ids = $('input[name="sid"]:checked').val();
        if (upShop.length <= 0) {
            layer.alert("至少选择一项");
            return false;
        }
        if (upShop.length > 1) {
            layer.alert("只能选择一个哦");
            return false;
        }
        window.location.href="<%=request.getContextPath()%>/shop/toUpdateShop?id="+ids;
    }


    function delShop() {
        var array = new Array();
        $(":input[name = 'shopId']:checked").each(function(){
            array.push($(this).val());
        });
        if(array.length <1){
            layer.alert("请选择", {
                icon: 3,
                shadeClose: true
            });
            return;
        }
        layer.confirm("您确认删除吗？",
            {icon:3,titel:"确认框"},
            function(index){
            var idArr = new Array();
            for (var i = 0; i < array.length; i++) {
                var arr = array[i].split(",");
                idArr.push(arr[0]);
            }
            var ids = idArr.join(',');
            $.post(
                "/shop/delShop",
                {"shopId" : ids, "_method" : "DELETE"},
                function (data) {
                    layer.close(index);
                    if(data.code != "200"){
                        layer.alert(data.msg, {
                            icon: 5,
                            shadeClose: true
                        });
                        return;
                    }
                    layer.msg(data.msg, {
                        icon: 6,
                        shadeClose: true,
                        time: 1000
                    }, function(){
                        //alert(data.msg);
                        window.location.href = "<%=request.getContextPath()%>/shop/toShowShop";
                    });
                })
        })
    }

    function updateStatus() {
        var sta = "";
        $('input[name="shopId"]:checked').each(function() {
            sta += $(this).val();
        });
        if (sta.length < 1) {
            layer.alert("至少选择一个", {
                icon: 3,
                shadeClose: true
            });
            return;
        } else if (sta.length > 1) {
            layer.alert("只能选择一个", {
                icon: 3,
                shadeClose: true
            });
            return;
        }
        debugger
        var shopStatus = $("#"+sta).val();
        var show = "您确认将该商品设置为";
        show += shopStatus == 1 ? "上架吗" : "下架吗";
        var Sta = shopStatus == 0 ? 1 : 0;
        layer.confirm(show, {icon:3,titel:"确认框"}, function(index){
            $.post(
                "/shop/updateStatus",
                {"id" : sta , "shopStatus" : Sta , "_method" : "PUT"},
                function(data) {
                    if (data.code != 200) {
                        layer.alert(data.msg, {
                            icon: 5,
                            shadeClose: true
                        });
                        return;
                    }
                    layer.msg(data.msg, {
                        icon: 6,
                        shadeClose: true,
                        time: 1000
                    }, function(){
                        //alert(data.msg);
                        window.location.href = "<%=request.getContextPath()%>/shop/toShowShop";
                    });
                });

        })

    }
</script>
</html>
