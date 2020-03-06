<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/2/1
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
</head>
<script type="text/javascript">
    function auth() {
        var index = layer.load(1);
        $.post(
            "${ctx}/user/auth",
            $("#fm").serialize(),
            function (data) {
                if (data.code != 200) {
                    layer.msg(data.msg);
                    layer.close(index);
                    return;
                }
                layer.msg(data.msg,{time:500},function () {
                    parent.window.location.href = "${ctx}/user/toShow";
                })
            }
        );
    }
</script>
<body>
<form id="fm">
    <input type="hidden" value="${userRole.userId}" name="userId">
    <input type="button" value="确认" onclick="auth()">
    <table>
        <tr>
            <th></th>
            <th>编号</th>
            <th>角色名</th>
        </tr>
        <c:forEach items="${roleList}" var="role">
            <tr>
                <td><input type="radio" name="roleId" value="${role.id}" <c:if test="${userRole.roleId == role.id}">checked</c:if>></td>
                <td>${role.id}</td>
                <td>${role.roleName}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
