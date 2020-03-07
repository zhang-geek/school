<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/1/30
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/static/zTree_v3/js/jquery.ztree.core.js"></script>
    <SCRIPT type="text/javascript">

        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: "parentId"
                },
                key: {
                    name: "resourceName"
                }
            }
        };

        $(function(){
            show();
        });

        function show(){
            $.post("${ctx}/resource/showMenu",
                {},
                function (data){
                    $.fn.zTree.init($("#resourceTree"), setting, data.data);
                })
        }

    </SCRIPT>
</head>
<body>
<div id="resourceTree" class="ztree"></div>
<a href="<%=request.getContextPath()%>/book/toShow" target="right">图书展示</a>
</body>
</html>
