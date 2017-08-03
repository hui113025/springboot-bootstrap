<%--无权限提示页面--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>总异常页面</title>
</head>
<body>
<div>
    <div class="row">
        <div class="col-md-10" style="width:100%;">
            <h3>异常:${e}  <button id="btn" class="btn btn-primary m-r-5"  >详情</button></h3>

        </div>
        <div id="exDiv" class="col-md-10" style="width:100%;display:none;">
            <h3>
                <c:forEach items="${stack}" var="s">
                    <p>${s}</p>
                </c:forEach>
            </h3>
        </div>
    </div>
</div>
<script>
    $(function(){
        $("#btn").click(function(){
            $("#exDiv").toggle();
        })
    })
</script>
</body>
</html>