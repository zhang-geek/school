<%--
  Created by IntelliJ IDEA.
  User: cf
  Date: 2020/3/6
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery.validate.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
</head>
<style type="text/css">
    .error{
        color: #FF0000;
    }
</style>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler:function () {
            var index = layer.load(0,{shade:0.5});
            $.post("/book/borrow",
                $("#fm").serialize(),
                function (data) {
                    layer.close(index);
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/book/toShow";
                    });
                }
            )
        }
    })
    $(function () {
        $("#fm").validate({
            rules:{
                count:{
                    required:true,
                    digits:true,
                    min:1
                }
            },
            messages:{
                count:{
                    required:"您要借的本数",
                    digits:"必须是整数哦",
                    min:"您不借书了吗？"
                }
            }
        })
    })
</script>
<body>
    <form id="fm">
        <input type="hidden" name="_method" value="POST"/>
        <input type="hidden" name="id" value="${book.id}"/>
        借&nbsp;&nbsp;书&nbsp;人：${USER.username}<br />
        书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${book.bookName}<br />
        类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:
        <c:forEach items="${resourseList}" var="type">
            <c:if test="${book.type == type.id}">
                ${type.resourceName}
            </c:if>
        </c:forEach><br/>
        库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存：${book.count}<br />
        <p>借书数量：<input type="text" name="count" id="count" /></p>
        <font style="color: #FF0000" size="4">还书时间为借书后的15天内！！！</font><br />
        <font style="color: #FF0000" size="4">逾期后需缴费0.8￥/天</font><br />
        <input type="submit" value="借书" />
    </form>
</body>
</html>
