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
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">
    function insertPay() {
        var index = layer.load(0,{shade:0.5});
        $.post("/borrow/insertPay",
                $("#fm").serialize(),
                function (data) {
                    layer.close(index);
                    if (data.code != 200){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function(){
                        parent.window.location.href = "<%=request.getContextPath()%>/borrow/toShow";
                    });
                 }
        )
    }
</script>
<body>
    <form id="fm">
        <input type="hidden" name="_method" value="POST"/>
        <input type="hidden" name="id" value="${borrow.id}"/>
        借&nbsp;&nbsp;书&nbsp;人：${user.username}<br />
        书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${book.bookName}<br />
        类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:
        <c:forEach items="${typeList}" var="type">
            <c:if test="${borrow.type == type.id}">
                ${type.resourceName}
            </c:if>
        </c:forEach><br/>
        缴费方式：
        <select name="pay">
            <c:forEach items="${payList}" var="p">
                <option value="${p.id}">${p.name}</option>
            </c:forEach>
        </select><br/>
        <input type="button" value="缴费" onclick="insertPay()" />
    </form>
</body>
</html>
