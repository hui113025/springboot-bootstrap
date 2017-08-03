<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${pname}网管理系统-员工授权</title>
        <link href="${pageContext.request.contextPath}/resources/app/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/resources/app/plugins/autocomplete/css/jquery.autocomplete.css" rel="stylesheet" />
    </head>
    <body>
    <div>
        <div class="row">
            <div class="col-md-6" style="width:30%;">
                <div class="panel panel-inverse">
                    <div class="panel-heading">
                        <div class="panel-heading-btn">
                       <%--     <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>--%>
                        </div>
                        <h4 class="panel-title">角色列表</h4>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <ul id="jstree-role" class="ztree" style="height:600px;overflow: auto">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6" style="width:70%;">
                <div class="panel panel-inverse">
                    <div class="panel-heading">
                        <div class="panel-heading-btn">
                        </div>
                        <h4 id="editPanleTitle" class="panel-title">授权员工</h4>
                    </div>
                    <div class="panel-body" >
                        <div class="row" id="userBody" style="display: none;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/app/plugins/autocomplete/js/jquery.autocomplete.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.exedit.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.excheck.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/js/role.js"></script>
    <script>
        $(document).ready(function() {
            <shiro:hasPermission name="role:findRole">
                handleRoleJstree();//初始化菜单
            </shiro:hasPermission>
        });
    </script>
    </body>
</html>