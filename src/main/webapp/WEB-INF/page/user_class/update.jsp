<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/3/9
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
<head>
    <title>新增</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
</head>
<body>
<form id="fm">
    <input type="hidden" value="${cla.id}" name="id">
    <input type="hidden" value="PUT" name="_method">
    班级名：<input type="number" name="classNum" value="${cla.classNum}">
    状态：
    <select name="classStatus" >
        <option value="0" <c:if test="${cla.classStatus == 0}">selected</c:if>>未开班</option>
        <option value="1" <c:if test="${cla.classStatus == 1}">selected</c:if>>已开班</option>
        <option value="2" <c:if test="${cla.classStatus == 2}">selected</c:if>>已结课</option>
    </select><br>
    <input type="submit" value="提交">
</form>
</body>
<script type="text/javascript">

    $(function(){
        $("#fm").validate({
            rules:{
                classNum:{
                    required:true,
                    rangelength:[1,6],
                },

            },
            messages:{
                classNum:{
                    required:"请输入班级名称",
                    rangelength:"长度限制在{0}到{1}之间"
                }
            }
        })
    })

    $.validator.setDefaults({
        submitHandler: function () {
            var index = layer.load(1);
            $.post(
                "<%=request.getContextPath()%>/cla",
                $("#fm").serialize(),
                function (data) {
                    layer.close(index);
                    if (data.code != 200) {
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg, {time: 1000}, function () {
                        parent.window.location.href = "<%=request.getContextPath()%>/cla/toShow";
                    });
                }
            );
        }
    });

</script>
</html>
