<%--
  Created by IntelliJ IDEA.
  User: cf
  Date: 2020/3/6
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
</head>
<script type="text/javascript">
    function borrow() {
        var index = layer.load(0,{shade:0.5});
        $.post("/book",
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
</script>
<body>
    <form id="fm">
        借&nbsp;&nbsp;书&nbsp;&nbsp;人：${user.userName}
        书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${book.bookName}
        库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存：${book.count}
        借书数量：<input type="text" name="count" /><br />
        <input type="button" value="借书" onclick="borrow()" /><br />
    </form>
</body>
</html>
