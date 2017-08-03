var zTree;//菜单树对象
var defaultId = "-1";//默认选中的菜单名称
var path = $("#path").val();
var menuForm = $("#menuForm");
var operDiv = $("#operDiv");
var setting = {
    view: {
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false
    },
    async:{
      enable:true,
      type:"post",
      url:path+"/menu/list",
      dataFilter: RoleFilter
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        showRemoveBtn: false,
        showRenameBtn:showRenameBtn,
        renameTitle:"修改菜单名称",
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
        onRename:onRename
    }
};

var  menuEnableSwitch =  $("#menu_enable").bootstrapSwitch();//菜单启用停用状态

$("#saveIcon").click(function(){
   var radioIco = $("#icon-alert").find("input[type=radio]:checked").val();
   $("#icon").removeClass().addClass(radioIco);
})

$("#menuSubmit").click(function(){
    var nodes = zTree.getSelectedNodes();
    var menuLvl = nodes[0].getParentNode().id == -1?true:false;//是否是一级菜单还是二级菜单,true一级菜单,false二级菜单
    if(formValidate(menuLvl)){
       updateMenu(nodes[0],menuLvl);
    }
})

//修改菜单
function updateMenu(treeNode,menuLvl){
    var menuData = {
        id:treeNode.id,
        menuName:$("#name").val(),
        menuDesc:$("#desc").val()
    }
    if(menuLvl){
        menuData['menuIcon'] = $("#icon").attr("class");
    }else{
        menuData['menuUrl'] = $("#url").val();
        menuData['menuCode'] = $("#code").val();
        var enable =1;//默认启用
        if(!menuEnableSwitch.bootstrapSwitch("state")){
            enable = 0;//禁用
        }
        menuData['menuEnable'] = enable;
    }
    var flag = checkMenuAttr(menuData);
    if(flag){
        $.ajax({
            url:path+"/menu/update",
            type:"post",
            dataType:"json",
            cache: false,
            data : menuData,
            success: function (datas){
                if(datas.code=="200"){
                    $().toastmessage('showSuccessToast',"修改成功!");
                    defaultId =treeNode.id;
                    zTree.reAsyncChildNodes(null,"refresh");//异步刷新树节点
                }else if(datas.code=="500"){
                    $().toastmessage('showErrorToast',"修改失败!");
                }
            }
        })
    }
}



//菜单树单击事件,进入编辑页面
function nodeOnClick(e,treeId,treeNode){
    if(treeNode.parent){
        //父菜单展开子节点
        zTree.expandNode(treeNode, true, false, true);
    }
    forminit();
     if(treeNode.id == -1 || treeNode.create){
         $("#menuBody").hide();
         return false;
     }else{
         $("#menuBody").show();
         $("#editPanleTitle").html(treeNode.create?"新建菜单":"修改"+treeNode.name+"菜单");
         formHiddlen(treeNode);
         //如果不是新建的
         var url = path+"/menu/entity"
         $.ajax({
                 url:url,
                 type:"post",
                 dataType:"json",
                 cache: false,
                 data : {"id":treeNode.id},
                 success: function (datas){
                     menuForm.find("#name").val(datas.menuName);
                     menuForm.find("#code").val(datas.menuCode);
                     menuForm.find("#url").val(datas.menuUrl);
                     if(datas.menuIcon != null && datas.menuIcon!=""){
                         menuForm.find("#icon").removeClass().addClass(datas.menuIcon);
                     }
                     $("#icon-alert").find("input[value='"+datas.menuIcon+"']").attr("checked","checked");
                     if(datas.operationList!=null){
                         for(var i = 0 ;i<datas.operationList.length;i++){
                             var oper = datas.operationList[i];
                             if($.trim(oper.code) != "view"){
                                 var button = "<button id='"+oper.id+"' code='"+oper.code+"' desc='"+oper.description+"' name='oper'" +
                                     "class='btn btn-info m-r-5 m-b-5' data-toggle='modal' href='#oper-alert' onclick='updateDialog(this);' type='button'>"+ oper.name+"</button>";
                                 operDiv.append(button);
                             }
                         }
                     }
                     if(datas.menuEnable==1){
                         menuEnableSwitch.bootstrapSwitch("state",true);
                     }else{
                         menuEnableSwitch.bootstrapSwitch("state",false);
                     }
                     menuForm.find("#desc").val(datas.menuDesc);
                 }
         })
     }
}

//根据一级菜单和二级菜单来隐藏或显示
function formHiddlen(treeNode){
   if(treeNode.getParentNode().id == -1){
     //一级菜单
       menuForm.find("div[level=2]").hide();
   }else{
     //二级菜单
       menuForm.find("div[level=1]").hide();
   }
}

//表单初始化
function forminit(){
    menuForm.find(".form-group").each(function(k,v){
        $(v).show();
    });
    menuForm.find("#name").val("");
    menuForm.find("#code").val("");
    menuForm.find("#url").val("");
    menuForm.find("#icon").addClass("fa fa-2x fa-adjust");//默认的菜单图标
    $("button[name=oper]").remove();
    menuEnableSwitch.bootstrapSwitch("state",true);
    menuForm.find("#desc").val("");
      $(".has-error").each(function(i,d){
        $(d).removeClass("has-error has-feedback");
    })
    $("li[name=errorTip]").each(function(i,d){
        $(d).html("");
    })
}

//表单验证
function formValidate(menuLvl){
    $(".has-error").each(function(i,d){
        $(d).removeClass("has-error has-feedback");
    })
    $("li[name=errorTip]").each(function(i,d){
        $(d).html("");
    })
    if($.trim(menuForm.find("#name").val())==""){
        menuForm.find("#name").parent().parent().addClass("has-error has-feedback");
        menuForm.find("#name").parent().find("li").html("菜单名称不能为空!");
        return false;
    }

    if(!menuLvl){
        if($.trim(menuForm.find("#code").val())==""){
            menuForm.find("#code").parent().parent().addClass("has-error has-feedback");
            menuForm.find("#code").parent().find("li").html("菜单代码不能为空!");
            return false;
        }

        if($.trim(menuForm.find("#url").val())==""){
            menuForm.find("#url").parent().parent().addClass("has-error has-feedback");
            menuForm.find("#url").parent().find("li").html("菜单URL不能为空!");
            return false;
        }
    }
    return true
}

function showRenameBtn(treeId, treeNode) {
    return treeNode.create;
}

var newCount = 1000000;
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if ((treeNode.getParentNode()&&treeNode.getParentNode().id != -1) || $("#addBtn_"+treeNode.tId).length>0 || treeNode.create) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='添加菜单' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        zTree.addNodes(treeNode,{id:newCount, pId:treeNode.id,name:"",open:true,"create":true});
        var newNodes = zTree.getNodeByParam("id",newCount,treeNode);
        zTree.selectNode(newNodes);
        newCount++;
        $("#menuBody").hide();
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};


function checkMenuAttr(data){
    var url = path+"/menu/check/menu"
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
                $().toastmessage('showErrorToast', "菜单名称,URL,权限码不能重复!");
            }
        }
    })
    return flag;
}

function beforeRename(treeId,treeNode,newName,isCancel){
    if(!isCancel){
        if(newName==null||newName==""){
            return false
        }
        var data =  {"menuName":newName};
        var flag = checkMenuAttr(data);
        return flag;
    }
}




function onRename(event,treeId,treeNode,isCancel){
   if(!isCancel){
       var datas = {
           "menuName":treeNode.name,
           "menuParentId":treeNode.getParentNode().id == -1?0:treeNode.getParentNode().id
       }
       $.ajax({
           url:path+"/menu/save",
           type:"post",
           dataType:"json",
           cache: false,
           data : datas,
           success: function (d){
               if(d.code==HttpUtil.success_code){
                   defaultId = d.result;
                   zTree.reAsyncChildNodes(null,"refresh");//异步刷新树节点
               }
           }
       })
   }
}

function RoleFilter(treeId, parentNode, responseData) {
    responseData.icon= path + '/resources/app/plugins/ztree/css/zTreeStyle/img/diy/1_open.png';
    var oneChildren = responseData.children;
    for (var i = 0; i< oneChildren.length; i++) {
        if (oneChildren[i].parent) {
            oneChildren[i].icon = path + '/resources/app/plugins/ztree/css/zTreeStyle/img/diy/1_close.png';
            var twoChildren  = oneChildren[i].children;
            for(var j = 0; j< twoChildren.length; j++){
                if(!twoChildren[j].parent){
                    twoChildren[j].icon = path + '/resources/app/plugins/ztree/css/zTreeStyle/img/diy/3.png';
                }
            }
        }
    }
    return responseData;
}

var handleMenuJstree = function(){
    zTree = $.fn.zTree.init($("#jstree-menu"), setting);
}

