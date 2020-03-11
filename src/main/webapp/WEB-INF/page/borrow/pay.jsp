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
    function updatePay() {
        var index = layer.load(0,{shade:0.5});
        $.post("/borrow/updatePay",
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
        <input type="hidden" name="id" value="${borrowList.id}"/>
        借&nbsp;&nbsp;书&nbsp;人：${borrowList.userName}<br />
        书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${borrowList.bookName}<br />
        类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:${borrowList.typeName}<br />
        <c:if test="${borrowList.days > 0 && borrowList.pay == null}">
            卡上余额：<font style="color: #00B83F">${borrowList.cardMoney}元</font><br />
            逾期天数：<font style="color: #FF0000">${borrowList.days}天</font><br />
            应缴费用：<font style="color: #FF0000">${borrowList.penalty}元</font><br />
        </c:if>
        <c:if test="${borrowList.days <= 0}">
            <h4 style="color: #FF0000">你还未逾期，无需缴费！！！</h4>
        </c:if>
        <c:if test="${borrowList.pay != null}">
            <h4 style="color: #FF0000">您已缴过费，无需二次缴费！！！</h4>
        </c:if>
        <c:if test="${borrowList.pay == null && borrowList.days > 0}">
            <input type="button" value="缴费" onclick="updatePay()" />
        </c:if>
    </form>
</body>
</html>
