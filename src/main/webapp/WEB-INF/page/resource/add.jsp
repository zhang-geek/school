<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/2/2
  Time: 20:32
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
                resourceName:{
                    required:true,
                    rangelength:[0,10],
                    remote: {
                        type: 'POST',
                        url: "${ctx}/resource/deDuplicate",
                        data:{
                            dataType:"json"
                        }
                    }
                },
                url:{
                    required:true,
                    remote: {
                        type: 'POST',
                        url: "${ctx}/resource/deDuplicate",
                        data:{
                            dataType:"json"
                        }
                    }
                }
            },
            messages:{
                resourceName:{
                    required:"请输入角色名",
                    rangelength:"长度限制在{0}到{1}之间",
                    remote:"资源已存在~"
                },
                url:{
                    required:"请输入url~",
                    remote:"url已存在~"
                }
            }
        })
    })

    $.validator.setDefaults({
        submitHandler : function() {
            var index = layer.load(1);
            $.post(
                "<%=request.getContextPath()%>/resource/add",
                $("#fm").serialize(),
                function(data) {
                    layer.close(index);
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/resource/toShow";
                    });
                }
            );
        }
    });

</script>
<body>
<form id="fm">
    <input type="hidden" name="parentId" value="${parentId}">
    上级：${resource.resourceName}<c:if test="${resource.resourceName == null}">暂无</c:if><br>
    资源名称：<input type="text" name="resourceName" ><br>
    url：<input type="text" name="url" ><br>
    类型：<select name="resourceType">
        <option value="1">url</option>
        <option value="2">按钮</option>
    </select><br>
    <input type="submit" value="新增">
</form>
</body>
</html>
