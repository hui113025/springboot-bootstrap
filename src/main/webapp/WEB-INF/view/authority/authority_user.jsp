<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${pname}管理系统-用户管理</title>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/switch/css/bootstrap-switch.min.css" rel="stylesheet" />
</head>
<body>
<div>
    <div class="row">
        <div class="col-md-10" style="width:100%;">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                    </div>
                    <h4 class="panel-title">用户列表</h4>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group m-r-10">
                            <input type="text" class="form-control"  style="width:220px;" id="userEmail" placeholder="用户邮箱" />
                        </div>
                        <div class="form-group m-r-10">
                            <input type="text" class="form-control" style="width:220px;" id="userMobile" placeholder="用户手机" />
                        </div>
                        <div class="form-group m-r-10">
                            <input type="text" class="form-control"  style="width:220px;"  id="userNum" placeholder="用户工号" />
                        </div>
                        <div class="form-group  m-r-10" >
                            <div class="input-group">
                                <input  readonly="readonly" class="form-control" style="width:220px;" id="createDate" placeholder="创建日期">
                            </div>
                        </div>
                        <div class="form-group m-r-10">
                            <select id="userStatus" class="form-control">
                                <option value="-1">--用户状态--</option>
                                <option value="1">启用</option>
                                <option value="0">停用</option>
                            </select>
                        </div>
                        <shiro:hasPermission name="user:find">
                            <button type="button" class="btn btn-sm btn-info m-r-5" id="find">查询</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:save">
                            <button type="button" data-toggle="modal" href="#create-user-modal"  class="btn btn-sm btn-primary m-r-5">新增</button>
                        </shiro:hasPermission>
                    </form>
                    <table id="user-table" class="table table-striped table-bordered" width="100%">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>编号</th>
                            <th>姓名</th>
                            <th>手机</th>
                            <th>工号</th>
                            <th>职位</th>
                            <th>性别</th>
                            <th>花名</th>
                            <th>启用状态</th>
                            <th>修改人</th>
                            <th>修改时间</th>
                            <th>最后登录时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/view/authority/user_dialog.jsp" %>
<div class="row">
    <!-- 按钮-->
    <div id="button-1" style="display: none">
        <shiro:hasPermission name="user:update">
            <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" href="#update-user-modal" onclick="page.updateUserDialog('#id')">修改</button>
        </shiro:hasPermission>
    </div>
    <div id="button-2" style="display: none">
        <shiro:hasPermission name="user:lock">
            <button type="button" class="btn btn-sm btn-danger" onclick="page.lockUser('#id','#lock')">锁定</button>
        </shiro:hasPermission>
    </div>
    <div id="button-3" style="display: none">
        <shiro:hasPermission name="user:lock">
            <button type="button" class="btn btn-sm btn-danger" onclick="page.lockUser('#id','#lock')">解锁</button>
        </shiro:hasPermission>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/switch/js/bootstrap-switch.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/tool.date.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/user.js"></script>
<script>
    page.init();
</script>
</body>
</html>