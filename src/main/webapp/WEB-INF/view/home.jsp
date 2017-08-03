<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/5/10
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${pname}管理系统-首页</title>
    </head>
    <body>
    <script>
        $(document).ready(function() {
            <%-- 登录成功,提示 --%>
            $(window).load(function() {
                setTimeout(function() {
                    $.gritter.add({
                        title: '欢迎您,'+'${sessionScope.login_user.name}',
                        text: '开启一天美好的生活吧!',
                        image: '${pageContext.request.contextPath}/resources/color-admin/img/user-12.jpg',
                        sticky: true,
                        time: '',
                        class_name: 'my-sticky-class'
                    });
                }, 1000);
            });
        });
    </script>
    </body>
</html>