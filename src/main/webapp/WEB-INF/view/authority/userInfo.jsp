<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<head>
    <title>对啊网管理系统-个人</title>
    <link href="${path}/resources/color-admin/plugins/bootstrapValidator/css/bootstrapValidator.css" rel="stylesheet">
</head>
<body>

<div>
    <div class="row">
        <%--密码修改--%>
        <div class="col-md-6">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default"
                           data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning"
                           data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">修改密码</h4>
                </div>
                <div class="panel-body">
                    <form id="passwordForm" class="form-horizontal form-bordered" >
                        <div class="form-group">
                            <label class="col-md-3 control-label">原密码*</label>

                            <div class="col-md-9">
                                <input id="oldPassWord" name="oldPassWord" type="password" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">新密码*</label>

                            <div class="col-md-9">
                                <input id="newPassWord" name="newPassWord" type="password" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">确认密码*</label>

                            <div class="col-md-9">
                                <input id="againPassWord" name="againPassWord" type="password" class="form-control"/>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button id="clearBtn" type="button" class="btn btn-sm btn-info">重置</button>
                    <button id="savePassWord" type="button" class="btn btn-sm btn-success">保存</button>
                </div>
            </div>
        </div>


        <div class="col-md-6">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default"
                           data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning"
                           data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">个人信息</h4>
                </div>
                <div class="panel-body">
                    <form id="userInfoForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                        <div class="col-xs-2 col-sm-2 col-md-2">
                            <div class="form-group">
                                <img src="${pageContext.request.contextPath}/resources/color-admin/img/user-12.jpg"/>
                            </div>

                        </div>
                        <div class="col-xs-10 col-sm-10 col-md-10">
                            <div class="form-group">
                                <label class="col-md-3 control-label">姓名</label>

                                <div class="col-md-9">
                                    <input type="text" class="form-control" readonly="readonly"
                                           value="${user.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">工号</label>

                                <div class="col-md-9">
                                    <input type="text" class="form-control" readonly="readonly" value="${user.num}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">职位</label>

                                <div class="col-md-9">
                                    <input type="text" class="form-control" readonly="readonly" value="${user.job}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">手机号</label>

                                <div class="col-md-9">
                                    <input type="text" class="form-control" readonly="readonly" value="${user.mobile}"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">电子邮箱</label>

                                <div class="col-md-9">
                                    <input type="text" class="form-control" readonly="readonly" value="${user.email}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">性别</label>

                                <div class="col-md-9">
                                    <input type="text" class="form-control" readonly="readonly" value="${user.sex}"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrapValidator/js/bootstrapValidator.js"></script>
<script>
    //dom对象外提 定义一次减少重复查询
    var form = $("#passwordForm");
    var opw = form.find("#oldPassWord");
    var npw = form.find("#newPassWord");
    var apw = form.find("#againPassWord");

    //值置空
    $(document).ready(function () {
        initBindEvents();
    });
    //初始化各种事件绑定
    function initBindEvents(){

        //重置数据
        $("#clearBtn").unbind().bind("click", function () {restFormDatas();});

        //修改保存
        $("#savePassWord").unbind().bind("click", function () {
            var pass = form.data('bootstrapValidator').validate().isValid();//表单验证是否通过
            if (pass){

                var data = {oldPassWord: opw.val(),newPassWord: npw.val()}

                $.post('/user/updatePassWord',data,function (datas) {
                    if (datas.code == HttpUtil.success_code){
                        BootstrapDialog.success(datas.msg);
                        restFormDatas();
                    } else{
                        BootstrapDialog.alert(datas.msg);
                    }
                });
            }
        });

        //绑定表单验证器
        form.bootstrapValidator({
            message: 'This value is not valid',
            excluded: [':disabled'],//不加它重新打开模态框时提示不会清除
            fields: {
                oldPassWord: {
                    validators: {
                        notEmpty: {
                            message: '原密码不能为空!'
                        }
                    }
                },
                newPassWord : {
                    validators: {
                        notEmpty: {
                            message: '请输入新密码!'
                        },
                        regexp: {
                            regexp:/^.{6,18}$/,
                            message: '密码长度为6~18位'
                        }
                    }
                },
                againPassWord : {
                    validators: {
                        notEmpty: {
                            message: '请再次输入新密码'
                        },
                        identical: {
                            field: 'newPassWord',
                            message: '两次密码不同请重新输入'
                        }
                    }
                }
            }
        });
    }

    //重置表单数据
    function restFormDatas(){
        form.bootstrapValidator('resetForm', true);
        form[0].reset();
    }

</script>
</body>

</html>
