<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/1/31
  Time: 19:55
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
<style type="text/css">
    .error{
        color: red;
    }
</style>
<script type="text/javascript">

    $(function(){
        $("#fm").validate({
            rules:{
                roleName:{
                    rangelength:[0,5],
                    remote: {
                        type: 'POST',
                        url: "${ctx}/role/deDuplicate",
                        data:{
                            roleName:function() {
                                return $("#roleName").val();
                            },
                            id:function() {
                                return $("#id").val();
                            },
                            dataType:"json",
                            dataFilter:function(data,type){
                                return data;
                            }
                        }
                    }
                }
            },
            messages:{
                roleName:{
                    rangelength:"长度限制在{0}到{1}之间",
                    remote:"角色名已存在~"
                }
            }
        })
    })

    $.validator.setDefaults({
        submitHandler : function() {
            var index = layer.load(1);
            $.post(
                "${ctx}/role/update",
                $("#fm").serialize(),
                function(data) {
                    layer.close(index);
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function(){
                        parent.window.location.href = "${ctx}/role/toShow";
                    });
                }
            );
        }
    });

</script>
<body>
<form id="fm">
    <input type="hidden" name="id" value="${role.id}" id="id">
    角色名：<input type="text" name="roleName" id="roleName" value="${role.roleName}"><br>
    <input type="submit" value="编辑角色">
</form>
</body>
</html>
