<%--无权限提示页面--%>
<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8" contentType="text/html; UTF-8" isErrorPage="true" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>500页面</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/css/animate.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/css/style.min.css" rel="stylesheet" />
</head>
<body class="pace-top">
<div id="page-loader" class="fade in"><span class="spinner"></span></div>
<div id="page-container" class="fade">
    <div class="error">
        <div class="error-code m-b-10">500系统异常<i class="fa fa-warning"></i></div>
        <div class="error-content">
            <div class="error-message">
                    异常信息:${pageContext.exception}
            </div>
            <div class="error-message">
                异常堆栈:<br/>
                <c:forEach begin="0" end="12" var="trace" items="${pageContext.exception.cause.stackTrace}">
                    ${trace}<br/>
                </c:forEach>
            </div>
            <a href="/" class="btn btn-success">返回首页</a>
        </div>
    </div>
    <a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
</div>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/js/apps.min.js"></script>
<script>
    $(document).ready(function() {
        App.init();
    });
</script>
</body>
</html>
