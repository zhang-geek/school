<%--
  Created by IntelliJ IDEA.
  User: zhangzhikai
  Date: 2020/2/2
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/page/common/taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/static/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx}/static/zTree_v3/js/jquery.ztree.excheck.js"></script>
    <SCRIPT type="text/javascript">

        var setting = {
            check: {
                enable: true
            },
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
            $.post("${ctx}/resource/show",
                {"roleId":"${role.id}"},
                function (data){
                    $.fn.zTree.init($("#resourceTree"), setting, data.data);
                })
        }

        function relatedResource() {
            var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
            var nodes = treeObj.getCheckedNodes(true);
            var ids = new Array();
            for (var i = 0; i < nodes.length; i++) {
                ids.push(nodes[i].id);
            }
            var index = layer.load(1);
            $.post(
                "${ctx}/role/relatedResource",
                {roleId:"${role.id}", resourceIds:ids.join()},
                function (data) {
                    layer.msg(data.msg, {time:1000}, function () {
                        layer.close(index);
                        window.location.href = '${ctx}/role/toRelatedResource?id='+${role.id};
                    })
                }
            );
        }
    </SCRIPT>
</head>
<body>
<form id="fm">
    <input type="hidden" value="${role.id}" name="roleId">
    <input type="button" onclick="relatedResource()" value="保存">
    <div id="resourceTree" class="ztree"></div>
</form>
</body>
</html>
