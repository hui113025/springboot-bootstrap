<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@ page import="org.apache.commons.lang3.RandomUtils" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<%-- 浏览器兼容问题 --%>
<!DOCTYPE html>
<%--[if IE 8]> <html lang="en" class="ie8"> <![endif]--%>
<%--[if !IE]><%--%>
<html lang="en">
<%--<![endif]--%>
<head>
    <meta charset="utf-8" />
    <title>${pname}管理系统登录</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/css/style.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/css/style-responsive.min.css" rel="stylesheet" />
    <link rel="shortcut icon" href="/resources/app/img/favicon.ico" >
</head>
<body class="pace-top bg-white">
<input id="path" type="hidden" value="${pageContext.request.contextPath}" >
<%-- begin #page-loader --%>
<div id="page-loader" class="fade in"><span class="spinner"></span></div>
<%-- end #page-loader --%>

<%-- begin #page-container --%>
<div id="page-container" class="fade">
    <%-- begin login --%>
    <div class="login login-with-news-feed">
        <%-- begin news-feed --%>
        <div class="news-feed">
            <div class="news-image">
                <img src="${pageContext.request.contextPath}/resources/color-admin/img/login-bg/bg-<% out.print(RandomUtils.nextInt(1,8));%>.jpg" data-id="login-cover-image" alt="" />
            </div>
            <div class="news-caption">
                <h4 class="caption-title"><i class="fa fa-diamond text-success"></i>${pname}</h4>
                <p>
                    成就职业梦想!
                </p>
            </div>
        </div>
        <%-- end news-feed --%>
        <%-- begin right-content --%>
        <div class="right-content">
            <%-- begin login-header --%>
            <div class="login-header">
                <div class="brand">
                    <span class="logo"></span>${pname}
                    <small>让学习,触手可及</small>
                </div>
                <div class="icon">
                    <i class="fa fa-sign-in"></i>
                </div>
            </div>
            <%-- end login-header --%>
            <%-- begin login-content --%>
            <div class="login-content">
                <form id="loginform" action="${path}/auth/check" class="margin-bottom-0">
                    <div id="user_div" class="form-group m-b-15">
                        <input id="user_input" type="text" class="form-control input-lg" placeholder="用户名"  />
                    </div>
                    <div id="pwd_div" class="form-group m-b-15">
                        <input id="user_pwd" type="password" class="form-control input-lg" placeholder="密码" />
                    </div>
                    <div class="checkbox m-b-30">
                        <label>
                            <input id="remember" type="checkbox" /> 记住我(保持一周)
                        </label>
                    </div>
                    <p id="login_error_tip" style="font-size:15px;display:block;height:15px;;" class="text-danger">

                    </p>
                    <div class="login-buttons">
                        <button id="login_btn" type="button" class="btn btn-success btn-block bt n-lg">登录</button>
                    </div>
                    <div class="m-t-20 m-b-40 p-b-40">
                        <%-- 可以填写文字  --%>
                    </div>
                    <hr />
                    <p class="text-center text-inverse">
                        &copy;${pname}管理系统 V1.0
                    </p>
                </form>
            </div>
            <%-- end login-content --%>
        </div>
        <%-- end right-container --%>
    </div>
    <%-- end login --%>
</div>
<%-- end page container --%>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/jquery-cookie/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/js/apps.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/utils.js"></script>
<script>
    $(document).ready(function() {
        App.init();
    });

    var path;
    $(function () {
        path = $("#path").val();
        var userInput = $("#user_input");/*用户名*/
        var pwdInput = $("#user_pwd");//密码
        var rem = $("#remember");//记住我
        var loginErrTip = $("#login_error_tip");//登录错误提示
        var userDivError = $("#user_div");//用户名Div提示
        var pwdDivError = $("#pwd_div");//密码Div提示
        var login_btn = $("#login_btn");//登录btn
        userInput.focus();

        /*回车登录*/
        $("body").keydown(function (e) {
            if (e.keyCode == 13) {
                $("#login_btn").click();
            }
        })




        /*清空错误提示*/
        function clearErrorTip() {
            loginErrTip.html("");
            loginErrTip.hide();
            userDivError.removeClass("has-error");
            pwdDivError.removeClass("has-error");
        }


        /*登录验证非空*/
        function LoginisNotNull(username, pwd) {
            var flag = true;
            if ($.trim(username) == "") {
                loginErrTip.html("请填写用户名或密码!");
                loginErrTip.show();
                userDivError.addClass("has-error");
                flag = false;
            }
            if ($.trim(pwd) == "") {
                loginErrTip.html("请填写用户名或密码!");
                loginErrTip.show();
                pwdDivError.addClass("has-error");
                flag = false;
            }
            return flag;
        }


        $("#login_btn").bind("click",function(){
            var username = $.trim(userInput.val());
            var pwd = $.trim(pwdInput.val());
            var urls = $("#loginform").attr("action");
            var loginBtn = $(this);
            var isrem = $("#remember")[0].checked;
            var flag = true;
            clearErrorTip();
            if(LoginisNotNull(username,pwd)){
                if (username.indexOf('@') < 0) {
                    loginErrTip.html("请填写正确的邮箱账号!");
                    loginErrTip.show();
                    userDivError.addClass("has-error");
                    flag = false;
                }
                if(flag){
                    loginBtn.html("登录中,请稍后!");
                    data = {"email": username, "password":pwd,"isrem":isrem};
                    $.ajax({
                        url: urls,
                        type: "post",
                        dataType: "json",
                        cache: false,
                        async: false,
                        data : data,
                        success: function (datas) {
                            loginBtn.html("登录");
                            if (datas.success) {
                                window.location.href = path+"/"
                            } else{
                                loginErrTip.html(datas.message);
                                loginErrTip.show();
                            }
                        }
                    })
                }
            }
        })


    });

</script>
</body>
</html>
