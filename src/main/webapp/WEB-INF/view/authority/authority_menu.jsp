<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<title>${pname}管理系统-菜单管理</title>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/app/plugins/switch/css/bootstrap-switch.min.css" rel="stylesheet" />
    <style type="text/css">
      .labelWidth{
          width:16%;
      }
    </style>
</head>
<body>
         <div>
			<div class="row">
			    <div class="col-md-6" style="width:30%;">
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                               <%-- <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>--%>
                            </div>
                            <h4 class="panel-title">菜单管理</h4>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <ul id="jstree-menu" class="ztree" style="height:600px;overflow: auto">
                                </ul>
                            </div>
                        </div>
                    </div>
			    </div>
			    <div class="col-md-6" style="width:70%;">
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
               <%--                 <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>--%>
                            </div>
                            <h4 id="editPanleTitle" class="panel-title">菜单详情</h4>
                        </div>
                        <div class="panel-body">
                            <div id="menuBody" class="row" style="display: none;">
                                <form id="menuForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                                    <div class="form-group">
                                        <label  class="col-md-3 control-label labelWidth">菜单名称*</label>
                                        <div class="col-md-9">
                                            <input id="name" type="text"  class="form-control"/>
                                            <ul  class="parsley-errors-list filled">
                                                <li name="errorTip" class="parsley-required"></li>
                                            </ul>
                                        </div>

                                    </div>
                                    <div class="form-group" level="2">
                                        <label class="col-md-3 control-label labelWidth ">菜单权限码*</label>
                                        <div class="col-md-9">
                                            <input id="code" type="text" class="form-control"/>
                                            <ul  class="parsley-errors-list filled">
                                                <li name="errorTip" class="parsley-required"></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="form-group" level="2">
                                        <label class="col-md-3 control-label labelWidth">菜单URL*</label>
                                        <div class="col-md-9">
                                            <input id="url" type="text" class="form-control"/>
                                            <ul  class="parsley-errors-list filled">
                                                <li name="errorTip" class="parsley-required"></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="form-group" level="1">
                                        <label class="col-md-3 control-label labelWidth">菜单图标</label>
                                        <div class="col-md-9">
                                            <a id="icon" style="text-decoration:none;" data-toggle="modal" href="#icon-alert" class="fa fa-2x fa-adjust"></a>
                                        </div>
                                    </div>
                                    <shiro:hasPermission name="menu:operManage">
                                    <div class="form-group" level="2">
                                        <label class="col-md-3 control-label labelWidth">菜单内操作</label>
                                        <div id="operDiv" class="col-md-9">
                                            <button  class="btn btn-primary m-r-5 m-b-5" onclick="newDialog(this);" type="button" data-toggle="modal" href="#oper-alert">新增操作</button>
                                        </div>
                                    </div>
                                    </shiro:hasPermission>
                                    <div class="form-group" level="2">
                                        <label class="col-md-3 control-label labelWidth">菜单启用</label>
                                        <div class="col-md-9">
                                                <input id="menu_enable" type="checkbox"  data-on-text="启用" data-off-text="停用" data-on-color="primary"  data-off-color="danger"  checked />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label labelWidth">菜单描述</label>
                                        <div class="col-md-9">
                                            <textarea id="desc" class="form-control"  rows="5"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label labelWidth"></label>
                                        <div class="col-md-9">
                                           <shiro:hasPermission name="menu:update">
                                            <button id="menuSubmit" type="button" class="btn btn-success m-r-5 m-b-5">修改</button>
                                           </shiro:hasPermission>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
			    </div>
			</div>
		  </div>
        <%@include file="/WEB-INF/view/authority/menu_dialog.jsp" %>
        <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.core.js"></script>
        <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.exedit.js"></script>
        <script src="${pageContext.request.contextPath}/resources/app/plugins/ztree/js/jquery.ztree.excheck.js"></script>
        <script src="${pageContext.request.contextPath}/resources/app/plugins/switch/js/bootstrap-switch.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/app/js/menu.js"></script>
        <script src="${pageContext.request.contextPath}/resources/app/js/oper.js"></script>
	    <script>
	    	$(document).ready(function() {
              //是否有查询菜单的权限
              <shiro:hasPermission name="menu:find">
                handleMenuJstree();//初始化菜单
              </shiro:hasPermission>
		    });
		</script>
</body>
</html>
