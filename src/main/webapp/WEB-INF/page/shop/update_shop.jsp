<%--
  Created by IntelliJ IDEA.
  User: dj
  Date: 2020/3/7
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
</head>
<body>
<form id="frm">
    <input type="hidden" name="id" value="${shop.id}"/>
    商品名称:<input type="text" name="shopName" value="${shop.shopName}" id="shopName"/><p/>
    商品价格:<input type="text" name="shopPrice" value="${shop.shopPrice}"/>
    商品类别:<select name="baseDataId">
                <option value="">--请选择--</option>
                <option value="2" <c:if test="${shop.baseDataId == 2}">selected</c:if>>速食</option>
                <option value="3" <c:if test="${shop.baseDataId == 3}">selected</c:if>>辣条</option>
                <option value="4" <c:if test="${shop.baseDataId == 4}">selected</c:if>>肉食</option>
                <option value="5" <c:if test="${shop.baseDataId == 5}">selected</c:if>>零食</option>
                <option value="6" <c:if test="${shop.baseDataId == 6}">selected</c:if>>碳酸饮料</option>
                <option value="7" <c:if test="${shop.baseDataId == 7}">selected</c:if>>果汁</option>
                <option value="8" <c:if test="${shop.baseDataId == 8}">selected</c:if>>雪糕</option>
                <option value="9" <c:if test="${shop.baseDataId == 9}">selected</c:if>>冰淇淋</option>
            </select><p/>
    <input type="hidden" name="_method" value="Post"/>
    <input type="submit" value="修改" class="layui-btn layui-btn-sm"/><p/>
</form>
</body>
<script type="text/javascript">
    $(function(){
        $("#frm").validate({
            rules:{
                shopName:{
                    required:true,
                    minlength:2,
                    remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                        type: 'POST',
                        url: "<%=request.getContextPath()%>/shop/findByShopName",
                        data:{
                            shopName:function() {
                                return $("#shopName").val();
                            },
                            dataType:"json",
                            dataFilter:function(data,type){
                                if (data == 'true'){
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                },
                shopPrice:{
                    required:true

                },
                baseDataId:{
                    required:true

                }
            },
            messages:{
                shopName:{
                    required:"请输入商品名称",
                    rangelength:"输入不符合长度"
                },
                shopPrice:{
                    required:"请输入价格"

                },
                baseDataId:{
                    required:"请选择类别"
                }

            }
        })
    })

    $.validator.setDefaults({
        submitHandler: function() {
            var index = layer.load();
            $.post(
                "/shop/updateShop",
                $("#frm").serialize(),
                function(resule){
                    layer.close(index)
                    if(resule.code == 200){
                        layer.msg(resule.msg, {
                            icon: 1,
                            time: 500
                        }, function() {
                            window.location.href="<%=request.getContextPath()%>/shop/toShowShop";
                        });

                    }
                })
        }
    });
</script>
<style>
    .error{
        color:red;
    }
</style>
</html>
