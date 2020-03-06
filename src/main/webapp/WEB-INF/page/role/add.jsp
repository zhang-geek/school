<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/1/31
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
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
                    required:true,
                    rangelength:[0,5],
                    remote: {
                        type: 'POST',
                        url: "${ctx}/role/deDuplicate",
                        data:{
                            roleName:function() {
                                return $("#roleName").val();
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
                    required:"请输入角色名",
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
                "<%=request.getContextPath()%>/role/add",
                $("#fm").serialize(),
                function(data) {
                    layer.close(index);
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/role/toShow";
                    });
                }
            );
        }
    });

</script>
<body>
<form id="fm">
    角色名：<input type="text" name="roleName" id="roleName"><br>
    <input type="submit" value="新增角色">
</form>
</body>
</html>
