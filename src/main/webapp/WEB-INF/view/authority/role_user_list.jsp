<%@ page pageEncoding="utf-8" language="java" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="input-group">
    <input id="userEmail" type="text" placeholder="用户授权"  class="form-control" data-provide="typeahead"/>
    <div class="input-group-btn">
        <shiro:hasPermission name="role:userAuth">
            <button id="userAuth" onclick="page.saveAuthUser(this);" type="button" class="btn btn-success" style="width:126px;">授权</button>
        </shiro:hasPermission>
    </div>
    <div class="col-md-4">
        <ul  class="parsley-errors-list filled">
            <li name="ErrorTip" class="parsley-required"></li>
        </ul>
    </div>
</div>
<div id="userDiv" class="row" style="margin-top:10px;max-height:500px;overflow-y:scroll;">
    <%-- 角色下的用户集合 --%>
    <c:forEach var="u" items="${us}">
        <div class="col-md-3 col-sm-6">
            <div class="widget widget-stats bg-green">
                <div class="stats-icon"><i class="fa fa-users"></i></div>
                <div class="stats-info">
                    <h4>${u.email}</h4>
                    <p>${u.name}</p>
                </div>
                <div class="stats-link">
                    <shiro:hasPermission name="role:delRoleUser">
                     <a href="javascript:;" onclick="page.delRoleUser(${rid},${u.id});">删除用户</a>
                    </shiro:hasPermission>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<script>
    $("#userEmail").autocomplete("/role/user/email", {
        dataType: "json",
        max: 12,  //列表里的条目数
        minChars: 0,    //自动完成激活之前填入的最小字符,0时双击时触发查询
        width:990,
        scrollHeight: 300,//提示的高度，溢出显示滚动条
        matchSubset: false,
        mustMatch:true,
        matchContains: true, //包含匹配，就是data参数里的数据,是否只要包含文本框里的数据就显示
        autoFill: false,    //自动填充
        cacheLength:1, //缓存长度1为不缓存
        extraParams : {
            "roleId": function (){
                return zTree.getSelectedNodes()[0].id;
            },
            "email": function (){
                return   $("#userEmail").val();
            }
        },
        parse: function (d) {
            var a = [];
            if(d){
                $.each(d, function (i, j) {
                    a.push({id: j.id, result: j.email});
                });
            }
            return a;
        },
        formatItem: function (row, i, max) {
            var userEmail = $("#userEmail").val();
            if(userEmail){
                return '<li onclick="showResult(this);" id="' + row.id + '" title="' + row.result + '"><strong>' + row.result + '</strong></li>';
            }
        }
    });
</script>

