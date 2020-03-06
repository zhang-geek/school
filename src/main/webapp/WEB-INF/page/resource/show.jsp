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
    <script type="text/javascript" src="${ctx}/static/zTree_v3/js/jquery.ztree.exedit.js"></script>
    <SCRIPT type="text/javascript">

        var setting = {
            view: {
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true,
                    pIdKey: "parentId"
                },
                key: {
                    name: "resourceName",
                    url: "xUrl"
                }
            }
        };


        $(function(){
            show();
            // $("#add").bind("click", add);
            // $("#edit").bind("click", edit);
            // $("#remove").bind("click", remove);
        });
        function show(){
            $.post("${ctx}/resource/show",
                {},
                function (data){
                    $.fn.zTree.init($("#resourceTree"), setting, data.data);
                })
        }

        function add() {
            var zTree = $.fn.zTree.getZTreeObj("resourceTree"),
                nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0],
                parentId = treeNode == null ? 0: treeNode.id;
            layer.open({
                type: 2,
                title: '修改',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath()%>/resource/toAdd?parentId='+parentId
            });

        };
        function edit() {
            var zTree = $.fn.zTree.getZTreeObj("resourceTree"),
                nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0];
            if (nodes.length == 0) {
                alert("请先选择一个节点");
                return;
            }
            layer.open({
                type: 2,
                title: '修改',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath()%>/resource/toUpdate?id='+treeNode.id
            });
        };
        function remove() {
            var zTree = $.fn.zTree.getZTreeObj("resourceTree"),
                nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0];
            if (nodes.length == 0) {
                layer.alert("请先选择一个节点");
                return;
            }
            $.post("<%=request.getContextPath()%>/resource/del",
                {"id":treeNode.id},
                function (data){
                    if (data.code != "200") {
                        return;
                    }
                    layer.msg(data.msg,{time:500},function () {
                        show();
                    })

                })
        };

    </SCRIPT>
</head>
<body>
<form id="fm">
    <shiro:hasPermission name="resource:add">
        <input type="button" value="新增" onclick="add()">
    </shiro:hasPermission>
    <shiro:hasPermission name="resource:update">
        <input type="button" value="修改" onclick="edit()">
    </shiro:hasPermission>
    <shiro:hasPermission name="resource:delete">
        <input type="button" value="删除" onclick="remove()">
    </shiro:hasPermission>
    <div id="resourceTree" class="ztree"></div>
</form>
</body>
</html>
