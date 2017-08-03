<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>对啊网管理系统-角色授权</title>
        <link href="${pageContext.request.contextPath}/resources/app/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
    </head>
    <body>
    <div>
        <div class="row">
            <div class="col-md-6" style="width:25%;">
                <div class="panel panel-inverse">
                    <div class="panel-heading">
                        <div class="panel-heading-btn">
                        <%--    <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>--%>
                        </div>
                        <h4 class="panel-title">角色列表</h4>
                    </div>
                    <div class="panel-body" style="height:600px;overflow: auto">
                        <div class="row">
                            <ul id="jstree-role" class="ztree">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6" style="width:25%;">
                <div class="panel panel-inverse">
                    <div class="panel-heading">
                        <div class="panel-heading-btn">
                           <%-- <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>--%>
                        </div>
                        <h4 class="panel-title">菜单列表</h4>
                    </div>
                    <div class="panel-body" style="height:600px;overflow: auto">
                        <div class="row">
                            <ul id="jstree-menu" class="ztree">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6" style="width:25%;">
                <div class="panel panel-inverse">
                    <div class="panel-heading" >
                        <div class="panel-heading-btn">
               <%--             <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>--%>
                        </div>
                        <h4 class="panel-title">操作列表</h4>
                    </div>
                    <div class="panel-body" style="height:600px;overflow: auto">
                        <div class="row">
                            <ul id="jstree-oper" class="ztree">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <shiro:hasPermission name="per:roleAuth">
        <a id="saveBtn" class="btn btn-success btn-block" href="javascript:;" style="display:none;">保存</a>
        </shiro:hasPermission>
    </div>
    <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.exedit.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.excheck.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/js/permission.js"></script>
    <script>
        $(document).ready(function() {
            <shiro:hasPermission name="per:find">
            handleJstree();//初始化菜单
            </shiro:hasPermission>
        });
    </script>
    </body>
</html>