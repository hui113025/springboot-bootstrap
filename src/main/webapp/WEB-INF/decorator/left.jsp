<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<%-- 侧边栏 --%>
<%-- begin sidebar scrollbar --%>
<div data-scrollbar="true" data-height="100%">
    <%-- begin sidebar user --%>
    <ul class="nav">
        <li class="nav-profile">
            <div class="image">
                <a href="javascript:;"><img src="${pageContext.request.contextPath}/resources/color-admin/img/user-12.jpg" alt="" /></a>
            </div>
            <div class="info">
                ${sessionScope.login_user.name}
                <small>${sessionScope.login_user.email}</small>
            </div>
        </li>
    </ul>
    <%-- end sidebar user --%>
    <%-- begin sidebar nav --%>
    <ul class="nav">
        <li class="nav-header">菜单列表</li>
        <c:forEach items="${requestScope.user_menu}" var="menu">
            <c:if test="${menu.subIsExist}">
              <li class="has-sub">
                 <a href="javascript:;">
                         <i class="${menu.menuIcon}"></i>
                         <b class="caret pull-right"></b>
                         <span>${menu.menuName}</span>
                  </a>
                  <ul class="sub-menu">
                      <c:forEach items="${menu.subMenu}" var="smenu" varStatus="item">
                          <li name="sub" <c:if test="${code==smenu.menuCode}">class="active"</c:if>><a href="${pageContext.request.contextPath}${smenu.menuUrl}">${smenu.menuName}</a></li>
                      </c:forEach>
                  </ul>
              </li>
            </c:if>
        </c:forEach>
        <%-- begin sidebar minify button --%>
        <li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
        <%-- end sidebar minify button --%>
    </ul>
    <%-- end sidebar nav --%>
</div>
<%-- end sidebar scrollbar --%>
<script>
    $("li[name=sub][class=active]").parent().parent().addClass("active");
</script>

