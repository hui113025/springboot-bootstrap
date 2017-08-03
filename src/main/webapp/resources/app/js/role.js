var zTree;//菜单树对象
var userDiv = $("#userDiv");//用户DIV
var userBody = $("#userBody");
var defaultId = "-1";//默认选中的菜单名称
var roleIds = [101,104,105];
var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false
    },
    async:{
        enable:true,
        type:"post",
        url:"/role/list",
        dataFilter: RoleFilter
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        showRemoveBtn: false,
        showRenameBtn:showRenameBtn,
        renameTitle:"修改角色名称",
        drag:false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick:nodeOnClick,
        onAsyncError:function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown){
            console.log("ztree 异常码:",textStatus);
            console.log("ztree 异常信息:",errorThrown);
        },
        onAsyncSuccess:function(e,treeId,treeNode,msg){
            var node = zTree.getNodeByParam("id",defaultId, null);
            zTree.selectNode(node);
            nodeOnClick(null,node.id,node);
        },
        beforeRename:beforeRename,
        onRename:onRename,
        beforeEditName:function zTreeBeforeEditName(treeId, treeNode) {
            //编辑框打开之前隐藏启动、停用按钮
            $('#' + treeNode.tId).children('a').children('.close_switch').hide();
            return true;
        }
    }
};

//角色节点点击事件
function nodeOnClick(e,treeId,treeNode){
    if(treeNode.parent){
        //父菜单展开子节点
        zTree.expandNode(treeNode, true, false, true);
    }
    clearData();
    if(treeNode.id == -1 || treeNode.getParentNode().id == -1||treeNode.create){
        $("#userBody").hide();
        return false;
    }else{
        var url = "/role/list/user"
        $.ajax({
            url:url,
            type:"post",
            dataType:"html",
            cache: false,
            data : {"roleId":treeNode.id},
            success: function (datas){
                userBody.html(datas);
                userBody.show();
            }
        })
    }
}

function clearData(){
    userDiv.find("ul").find("li").remove();
    $("#userEmail").val("");
    $("#userEmail").parent().removeClass("has-error has-feedback");
}

var newCount = 1000000;
//鼠标悬浮时显示自定义标签
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if ((treeNode.id==-1||treeNode.getParentNode().id == -1) && $("#addBtn_"+treeNode.tId).length<=0&&!treeNode.create){
        //添加角色按钮
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='添加角色' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
            zTree.addNodes(treeNode,{id:newCount, pId:treeNode.id,name:"",open:true,"create":true});
            var newNodes = zTree.getNodeByParam("id",newCount,treeNode);
            zTree.selectNode(newNodes);
            $("#userBody").hide();
            newCount++;
            return false;
        });
    }else if(treeNode.id!=-1&&treeNode.getParentNode().id != -1&&$("#openBtn_"+treeNode.tId).length<=0&&$("#closeBtn_"+treeNode.tId).length<=0&&!treeNode.create){
        if(roleIds.indexOf(treeNode.id) != -1){
        }else{
            if(treeNode.enable==1){
                //显示停用
                var closeStr = "<span class='button close_switch' id='closeBtn_" + treeNode.tId
                    + "' title='停用角色' onfocus='this.blur();' roleId = '"+treeNode.id+"' name='"+treeNode.name+"' onclick='roleSwitch(this,0)'></span>";
                sObj.after(closeStr);
            }else{
                //显示启用
                var openStr = "<span class='button open_switch' id='openBtn_" + treeNode.tId
                    + "' title='启用角色' onfocus='this.blur();' roleId = '"+treeNode.id+"' name='"+treeNode.name+"' onclick='roleSwitch(this,1)'></span>";
                sObj.after(openStr);
            }
        }
    }

};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};

//显示修改名称按钮
function showRenameBtn(treeId, treeNode) {
    var isShow = true;
    if(treeNode.id == -1){//只有根节点不显示
        isShow = false;
    }else{
        if(roleIds.indexOf(treeNode.id) != -1){//只有指点角色不显示
            isShow = false;
        }
    }
    return isShow;
}


function checkRoleAttr(data){
    var url = "/role/check/role"
    var flag = false;
    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        cache: false,
        async: false,
        data : data ,
        success: function (d){
            if(d.code==HttpUtil.success_code){
                flag = true;
            }else if(d.code == HttpUtil.error_code){
                $().toastmessage('showErrorToast', d.msg);
            }
        }
    })
    return flag;
}


function beforeRename(treeId,treeNode,newName,isCancel){
    if(!isCancel){
        if(newName==null||$.trim(newName)==""){
            return false;
        }
        var data =  {"name":newName,"id":treeNode.id};
        var flag = checkRoleAttr(data);
        return flag;
    }
}


//在修改名字后,添加角色
function onRename(event,treeId,treeNode,isCancel){
    if(!isCancel){
        var name = treeNode.name;//角色姓名
        var parentId = treeNode.getParentNode().id == -1?0: treeNode.getParentNode().id;//上级ID
        var url = "/role/saveOrUpdate";
        var roleData = {
            "name":name,
            "parentId":parentId
        }
        if(treeNode.create){
            //添加
            roleData['id'] = -1;
        }else{
            //修改
            roleData['id'] = treeNode.id;
        }
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            cache: false,
            data : roleData,
            success: function (datas){
                if(datas.code==HttpUtil.success_code){
                    defaultId =datas.result;
                    zTree.reAsyncChildNodes(null,"refresh");//异步刷新树节点
                }
            }
        })
    }
}
// (启用/停用)角色
function roleSwitch(obj,flag){
    var name = $(obj).attr("name");
    $.ajax({
        url:"/role/saveOrUpdate",
        type:"post",
        dataType:"json",
        cache: false,
        data : {"id":$(obj).attr("roleId"),"enable":flag},
        success: function (datas){
            if(datas.code==HttpUtil.success_code){
                defaultId =$(obj).attr("roleId");
                $(obj).remove();
                zTree.reAsyncChildNodes(null,"refresh");//异步刷新树节点
            }
        }
    })
}

function RoleFilter(treeId, parentNode, responseData) {
    responseData.icon= '/resources/app/plugins/ztree/css/zTreeStyle/img/diy/1_open.png';
    var oneChildren = responseData.children;
    for (var i = 0; i< oneChildren.length; i++) {
        if (oneChildren[i].parent) {
            oneChildren[i].icon =  '/resources/app/plugins/ztree/css/zTreeStyle/img/diy/1_close.png';
            var twoChildren  = oneChildren[i].children;
            for(var j = 0; j< twoChildren.length; j++){
                if(!twoChildren[j].parent){
                    twoChildren[j].icon =  '/resources/app/plugins/ztree/css/zTreeStyle/img/diy/3.png';
                }
            }
        }
    }
    return responseData;
}

var showResult = function(obj){
    $("#userEmail").val($(obj).attr("title"));
    $(obj).remove();
}

//用户授权
function saveAuthUser(obj){
    if($("#userEmail").val()!=null&&$("#userEmail").val()!=""){
        var node =  zTree.getSelectedNodes()[0];
        $.ajax({
            url:"/role/user/auth",
            type:"post",
            dataType:"json",
            cache: false,
            data : {"roleId":node.id,"email":$("#userEmail").val()},
            success: function (datas){
                if(datas.code==HttpUtil.success_code){
                    defaultId =node.id;
                    zTree.reAsyncChildNodes(null,"refresh");//异步刷新树节点
                }else{
                    $("#userEmail").parent().addClass("has-error has-feedback");
                    $("#userEmail").parent().find("li").html("请输入正确的邮箱!");
                }
            }
        })
    }else{
        $("#userEmail").parent().addClass("has-error has-feedback");
        $("#userEmail").parent().find("li").html("请输入正确的邮箱!");
    }
}

//删除角色下用户
function delRoleUser(rid,uid){
    var node =  zTree.getSelectedNodes()[0];
    BootstrapDialog.confirm({
        title: '删除角色用户',
        message: '确定要删除当前角色下的用户吗?',
        type: BootstrapDialog.TYPE_WARNING,
        closable: true,
        btnCancelLabel: '取消',
        btnOKLabel: '确认',
        btnOKClass: 'btn-warning',
        callback: function (result) {
            if (result) {
                $.ajax({
                    url:  "/role/del/user",
                    type: "post",
                    dataType: "json",
                    data: {"userId":uid,"roleId":rid},
                    success: function (d) {
                        if (ExceptionDialog(d)) {
                            defaultId =node.id;
                            zTree.reAsyncChildNodes(null,"refresh");//异步刷新树节点
                        }
                    }
                })
            }
        }
    })
}

var handleRoleJstree = function(){
    zTree = $.fn.zTree.init($("#jstree-role"), setting);
}

