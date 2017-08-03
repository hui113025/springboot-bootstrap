(function(window){


    var DataTable;
    var setSwitch =  $("#sex").bootstrapSwitch();
    var updateSetSwitch =  $("#update_sex").bootstrapSwitch();
    var statusSwitch = $("#status").bootstrapSwitch();
    var addForm = $('#userForm');
    var updateForm = $('#updateUserForm');

    //时间选择器
    var createDate =  $("#createDate").daterangepicker({
        format:'YYYY-MM-DD',//格式化日期
        showDropdowns:true,//显示年与月的选择框
        minDate:"2010-01-01",//最小日期
        maxDate:"2020-12-31",//最大日期
        applyClass: 'btn-success',//应用按钮class
        cancelClass: 'btn-warning',//取消按钮class
        separator:"/",
        ranges: {
            '今天': [moment(), moment()],
            '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            '一周内': [moment().subtract(7, 'days'), moment()],
            '30天内': [moment().subtract(30, 'days'), moment()],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        locale: {
            applyLabel: '保存',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '选择日期',
            daysOfWeek: ['周日','周一', '周二', '周三', '周四', '周五', '周六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        },
    });

    function initBindEvents(){

        DataTable = initDataTables('user-table');

        //保存用户
        $("#create-user-btn").click(function(){
            //清除
            formInit();

            //表单验证是否通过
            if(!addForm.data('bootstrapValidator').validate().isValid()) return;

            var parm =  {
                "email": $("#email").val(),
                "num": $("#num").val()
            };

            if(checkEmail(parm)){
                var sex = setSwitch.bootstrapSwitch("state") ?
                    setSwitch.bootstrapSwitch("onText") :
                    setSwitch.bootstrapSwitch("offText");

                var datas = {
                    "name": $("#name").val(),
                    "flowerName": $("#flowerName").val(),
                    "mobile": $("#mobile").val(),
                    "email": $("#email").val(),
                    "sex": sex,
                    "job": $("#job").val(),
                    "num": $("#num").val()
                };

                $.ajax({
                    url:"/user/save",
                    type:"post",
                    dataType:"json",
                    cache: false,
                    data :datas,
                    success: function (datas){
                        if(datas.code == HttpUtil.success_code){
                            addForm.data('bootstrapValidator').resetForm();
                            addForm[0].reset();
                            $("#close").click();
                            DataTable.ajax.reload();//刷新
                        }
                    }
                });

            }else{
                addForm.find("#email").parent().parent().addClass("has-error has-feedback");
                addForm.find("#email").parent().find("li").html("邮箱已经存在!");
            }
        })

        //修改用户
        $("#update-user-btn").click(function(){
            formInit();
            var parm =  {
                "email": $("#update_email").val(),
                "num": $("#update_num").val(),
                "id": $("#userId").val()
            };

            if(checkEmail(parm)){
                //表单验证是否通过
                if(!updateForm.data('bootstrapValidator').validate().isValid()) return;

                var sex = updateSetSwitch.bootstrapSwitch("state") ?
                    updateSetSwitch.bootstrapSwitch("onText") :
                    updateSetSwitch.bootstrapSwitch("offText");

                var status = statusSwitch.bootstrapSwitch("state") ? 1 : 0;//默认在职

                var datas = {
                    "id": $("#userId").val(),
                    "name": $("#update_name").val(),
                    "flowerName": $("#update_flowerName").val(),
                    "mobile": $("#update_mobile").val(),
                    "email": $("#update_email").val(),
                    "sex": sex,
                    "job": $("#update_job").val(),
                    "num": $("#update_num").val(),
                    "userStatus": status,
                    "password": $("#password").val()
                };

                $.ajax({
                    url:"/user/update",
                    type:"post",
                    dataType:"json",
                    cache: false,
                    data :datas,
                    success: function (datas){
                        if(datas.code == HttpUtil.success_code){
                            $("#update_close").click();
                            DataTable.ajax.reload();//刷新
                        }
                    }
                })
            }else{
                updateUserForm.find("#update_email").parent().parent().addClass("has-error has-feedback");
                updateUserForm.find("#update_email").parent().find("li").html("邮箱已经存在");
            }
        })

        //选择取消按钮时
        $("#createDate").on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });

        $("#find").click(function(){
            DataTable.ajax.reload();
        });

        //绑定表单验证器
        addForm.bootstrapValidator({
            message: 'This value is not valid',
            excluded: [':disabled'],//不加它重新打开模态框时提示不会清除
            fields: {
                email: {
                    validators : {
                        notEmpty: {
                            message: '不能为空'
                        },
                        callback: {
                            message: '请填写对啊网员工邮箱',
                            callback: function (value, validator) {
                                return value ? IsDuiaEmail(value) : true;
                            }
                        }
                    }
                },
                mobile : {
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        },
                        regexp: {
                            regexp: /^1[3,4,5,7,8]\d{9}$/,
                            message: '请填写正确的手机号'
                        }
                    }
                },
                name : {
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        },
                        stringLength: {
                            max: 10,
                            message: '不能超过10个字'
                        }
                    }
                },
                flowerName : {
                    validators: {
                        notEmpty: {
                            message: '花名不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '不能超过30个字'
                        }
                    }
                },
                num : {
                    validators: {
                        notEmpty: {
                            message: '工号不能为空'
                        },
                        stringLength: {
                            max: 4,
                            message: '不能超过4个字'
                        },
                        callback: {
                            message: '工号已经存在',
                            callback: function (value, validator) {
                                var data =  {
                                    "num": value
                                };
                                return value ? checkNum(data) : true;
                            }
                        }
                    }
                },
                job : {
                    validators: {
                        notEmpty: {
                            message: '职位不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '职位输入过长，最多为30字符'
                        }
                    }
                }
            }
        });
        updateForm.bootstrapValidator({
            message: 'This value is not valid',
            excluded: [':disabled'],//不加它重新打开模态框时提示不会清除
            fields: {
                mobile : {
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        },
                        regexp: {
                            regexp: /^1[3,4,5,7,8]\d{9}$/,
                            message: '请填写正确的手机号'
                        }
                    }
                },
                name : {
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        },
                        stringLength: {
                            max: 10,
                            message: '不能超过10个字'
                        }
                    }
                },
                flowerName : {
                    validators: {
                        notEmpty: {
                            message: '花名不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '不能超过30个字'
                        }
                    }
                },
                num : {
                    validators: {
                        notEmpty: {
                            message: '工号不能为空'
                        },
                        stringLength: {
                            max: 4,
                            message: '不能超过4个字'
                        }
                    }
                },
                job : {
                    validators: {
                        notEmpty: {
                            message: '职位不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '职位输入过长，最多为30字符'
                        }
                    }
                },
                password : {
                    validators: {
                        regexp: {
                            regexp: /^.{6,18}$/,
                            message: '密码长度为6~18位'
                        },
                    }
                },
                rpassword : {
                    validators: {
                        regexp: {
                            regexp: /^.{6,18}$/,
                            message: '密码长度为6~18位'
                        },
                        identical: {
                            field: 'password',
                            message: '两次输入密码不一致'
                        }
                    }
                }
            }
        });
    }

    var columns = [
        { "data": null},
        { "data": "id","visible":false},//visible 隐藏或显示
        { "data": "name"},
        { "data": "mobile"},
        { "data": "num" },
        { "data": "job" },
        { "data": "sex" },
        {"data":"flowerName"},
        { "data": "userStatus", "render":function(data,type,row,meta){
            return data == 1?"启用":"停用";
        }
        },
        { "data": "lastModifyUserEmail"},
        { "data": "lastModifyDatetime"},
        { "data": "lastLoginDatetime"},
        { "data": null,"render":function(data,type,row,meta){
            if(null!=data){
                var buttons = '';
                buttons += $('#button-1').html();
                if(data.userLock == 1){
                    buttons += $('#button-2').html();
                }else{
                    buttons += $('#button-3').html();
                }
                return buttons.replace(/#id/g, row.id).replace(/#lock/g, row.userLock);
            }
        }},
    ];
    function initDataTables(tableId) {
        return $('#' + tableId + '').DataTable({
            bDestroy: true,
            searching: false,
            processing: true,
            serverSide: true,
            ordering: false, //排序
            ajax:{
                "url":"/user/serach",
                "type": "POST",
                "dataType":"json",
                "data":function(d){
                    d.mobile=$("#userMobile").val();
                    d.email=$("#userEmail").val();
                    d.num=$("#userNum").val();
                    d.dateString=$("#createDate").val();
                    d.userStatus=$("#userStatus").val();
                }
            },
            columns: columns,
            drawCallback: function(settings){//设置第一列为自增列
                this.api().column(0).nodes().each(function(cell,i){
                    cell.innerHTML =  i + 1;
                })
            },
            columnDefs:[
                {className: "dt-body-center", "targets": "_all"},
                {
                    "targets": [11,12],
                    "render":function(data,type,row,meta){
                        if(data == null){
                            return "无登录";
                        }
                        return TimeObjectUtil.formatterDateTime(new Date(data))
                    }
                }
            ],
            language: {
                lengthMenu: "每页 _MENU_ 条记录",//下拉框文字
                info: "第 _PAGE_ 页 ( 总共 _PAGES_ 页 ,共 _TOTAL_ 项)",//左下角显示文字
                infoEmpty: "",//当查询没有数据时左下角显示文字
                sEmptyTable: "没有数据..",//当查询没有数据时表格中间显示文字
                paginate: {                          //分页
                    first: "首页",
                    last: "尾页",
                    next: "下一页",
                    previous: "上一页"
                },
                decimal:","
            }
        });

    }

    //验证Email是否唯一
    function checkEmail(data){
        var flag = false;
        $.ajax({
            url:"/user/check/userEmail",
            type:"post",
            dataType:"json",
            cache: false,
            async: false,
            data :data,
            success: function (datas){
                if(datas.code == HttpUtil.success_code){
                    flag =  true;
                }
            }
        })
        return flag;
    }

    //验证工号是否唯一
    function checkNum(data){
        var flag = false;
        $.ajax({
            url:"/user/check/userNum",
            type:"post",
            dataType:"json",
            cache: false,
            async: false,
            data :data,
            success: function (datas){
                if(datas.code == HttpUtil.success_code){
                    flag =  true;
                }
            }
        })
        return flag;
    }

    function formInit(){
        $(".has-error").each(function(i,d){
            $(d).removeClass("has-error has-feedback");
        })
        $("li[name=ErrorTip]").each(function(i,d){
            $(d).html("");
        })
    }

    //锁定用户
    function lockUser(id,lock){
        //status当前状态,1正常,0锁定
        var message = lock == 1?"是否要锁定用户?":"是否要解锁用户?";
        var data = {};
        BootstrapDialog.confirm({
            title: '用户锁定',
            message: message,
            type: BootstrapDialog.TYPE_WARNING,
            closable: true,
            btnCancelLabel: '取消',
            btnOKLabel: '确认',
            btnOKClass: 'btn-warning',
            callback: function (result) {
                if (result) {
                    if (lock == 1) {
                        //锁定用户
                        data = {"id": id, "userLock": 0}
                    } else {
                        //解锁用户
                        data = {"id": id, "userLock": 1}
                    }
                    $.ajax({
                        url:"/user/lock",
                        type: "post",
                        dataType: "json",
                        cache: false,
                        async: false,
                        data: data,
                        success: function (d) {
                            if (d.code == HttpUtil.success_code) {
                                //重新加载下用户表格
                                DataTable.ajax.reload(null, false);
                            }
                            if (d.code == HttpUtil.error_code) {

                            }
                        }
                    })
                }
            }
        })
    }

    //修改用户弹出框
    function updateUserDialog(obj){
        updateForm.data('bootstrapValidator').resetForm();
        updateForm[0].reset();
        formInit();
        $.ajax({
            url:"/user/entity",
            type:"post",
            dataType:"json",
            cache: false,
            async: false,
            data :{"id":obj},
            success: function (datas){
                if(datas.code == HttpUtil.success_code){
                    var result = datas.result;
                    updateForm.find("#userId").val(result.id);
                    updateForm.find("#update_email").val(result.email);
                    updateForm.find("#update_mobile").val(result.mobile);
                    updateForm.find("#update_name").val(result.name);
                    updateForm.find("#update_flowerName").val(result.flowerName);
                    updateForm.find("#update_num").val(result.num);
                    updateForm.find("#update_job").val(result.job);
                    if(result.sex == "女"){
                        updateForm.find("#update_sex").prop("checked",false);
                        updateSetSwitch.bootstrapSwitch("state",false);
                    }else{
                        updateForm.find("#update_sex").prop("checked",true);
                        updateSetSwitch.bootstrapSwitch("state",true);
                    }

                    if(result.userStatus == 1){
                        //在职
                        updateForm.find("#status").prop("checked",true);
                        statusSwitch.bootstrapSwitch("state",true);
                    }else{
                        //离职
                        updateForm.find("#status").prop("checked",false);
                        statusSwitch.bootstrapSwitch("state",false);
                    }

                    updateForm.find("#update_setState").text(result.sex);
                    updateForm.find("#update_email").val(result.email);

                }
            }
        })
    }

//需要暴露到global域的API
    window.page ={
        init:function () {

            //初始化各种事件绑定
            initBindEvents();

        }, updateUserDialog:updateUserDialog,lockUser:lockUser
    }


})(window);



