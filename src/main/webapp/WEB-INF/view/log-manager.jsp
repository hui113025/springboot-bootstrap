<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${pname}管理系统-操作日志</title>
    <link href="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/media/css/dataTables.bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
</head>
<body>
<div>
    <div class="row">
        <div class="col-md-10" style="width:100%;">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default"
                           data-click="panel-expand"><i class="fa fa-expand"></i></a>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning"
                           data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                    </div>
                    <h4 class="panel-title">操作日志列表</h4>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group m-r-10">
                            <input type="text" class="form-control"  id="userName"
                                   placeholder="操作人员姓名"/>
                        </div>
                        <div class="form-group m-r-10">
                            <select id="operType" class="form-control" data-map=${operTypeMapString}>

                                <option value="">操作类型</option>
                                <c:forEach items="${operTypelList}" var="t">
                                    <option name="operType" value="${t.value}">${t.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group m-r-10">
                            <select id="operLevel" class="form-control">
                                <option value="">操作级别</option>
                                <c:forEach items="${operLevelList}" var="l">
                                    <option name="operLeave" value="${l.value}">${l.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group  m-r-10">
                            <div class="input-group">
                                <input readonly="readonly" class="form-control"  id="operTime"
                                       placeholder="操作时间">
									<%--<span class="input-group-btn">
									   <button id="showCreateBtn" class="btn btn-default" type="button"><i
                                               class="fa fa-calendar"></i></button>
									</span>--%>
                            </div>
                        </div>
                        <shiro:hasPermission name="adminLog:find">
                            <button type="button" class="btn btn-sm btn-info m-r-5" id="find">查询</button>
                        </shiro:hasPermission>
                    </form>
                </div>
                <div class="panel-body">
                    <table id="adminLog-table" class="table table-striped table-bordered " width="100%">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>员工编号</th>
                            <th>姓名</th>
                            <th>调用方法</th>
                            <th>操作类型</th>
                            <th>操作描述</th>
                            <th>传递参数</th>
                            <th>操作等级</th>
                            <th>操作时间</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/media/js/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/media/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrap-daterangepicker/moment.js"></script>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/tool.date.js"></script>
<script>
    $(document).ready(function () {
        var adminLogTable;
        var tempObj = "";//存上次展开的对象
        var tempIndex = 0;//存上次展开的行号
        var count = true;
        var operTypeMap;

        var findObj = $("#find");

        //时间选择器
        var operTime =  $("#operTime").daterangepicker({
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
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            }
        });
        //选择取消按钮时
        $("#operTime").on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });


        findObj.click(function () {
            findObj.attr('disabled',true);
            adminLogTable.ajax.reload(null,false);
        });


        //监听所有折叠标志 对整个table监听
        $('#adminLog-table').on('click', 'span.glyphicon', function (e) {
            //获取行对象
            var tr = $(this).closest('tr');

            var index = tr.find("td").eq(0).html(); //行号

            //具体点击对象
            var obj = $(e.target).parent();
            //row对象
            var row = adminLogTable.row(tr);

            //第一次初始化赋值
            if(count){
                tempIndex = index;
                tempObj = row.child;
                count = false;
            }

            //如果本次展开行和原来的对象不一样把原来的收起来
            if (index != tempIndex) {
                tempObj.hide();
            }

            //把所有的先收起 ,避免一行多个个收起按钮
            $('.glyphicon').removeClass().addClass("glyphicon glyphicon-plus").html("展开");

            //如果是展开状态
            if (row.child.isShown()) {

                // This row is already open - close it
                //改变标志提示内容，收起对象
                obj.html("<span class='glyphicon glyphicon-plus'>展开</span>"+obj.data("cont"));
                row.child.hide();
            }
            else {
                //展开时把当前行付给tempIndex
                tempIndex = index;
                tempObj = row.child;
                // Open this row
                //改变标志提示内容，展开对象
                obj.html("<span class='glyphicon glyphicon-minus'>收起</span>"+obj.data("cont"));
                row.child(format(row.data(), obj.data("name"))).show();
            }

        });

        //传入行内数据，和要显示的详细内容
        function format(d,name) {
            var temp = "";
            if(name=="operName"){
                temp+= '<td width="6%">调用方法:</td><td style="word-wrap:break-word;word-break:break-all;" width="94%"> ' + d.operName + '</td>' ;
            }
            if(name=="operParam"){
                temp+= '<td width="6%">传递参数:</td><td style="word-wrap:break-word;word-break:break-all;" width="94%">' + d.operParam + '</td>';
            }
            //展开的表格数据处理
            return '<table cellpadding="5" cellspacing="0" border="0" width="100%" style="table-layout: fixed"><tbody>' +
                '<tr>' +
                temp
                + '</tr>' +
                '</tbody></table>';
        }

        operTypeMap = ($("#operType").data("map"));
        <shiro:hasPermission name="adminLog:find">
        adminLogTable = $('#adminLog-table').DataTable({
            select: true, //是否选中
            autoWidth: true,
            deferRender: true,
            responsive: true,
            searching: false, //是否开启搜索
            pagingType: "full_numbers",//分页类型 full_numbers显示所有分页信息
            processing: false, //是否显示处理状态
            serverSide: true, //是否开启服务器模式
            ordering: false, //排序
            aLengthMenu: [10, 20, 50, 100], //下拉框每页显示数量
            ajax: {
                "url": "/adminLog/search",
                "type": "POST",
                "dataType": "json",
                "data": function (d) {
                    d.userName = $("#userName").val();
                    d.operType = $("#operType").val();
                    d.operLevel = $("#operLevel").val();
                    d.dateString = $("#operTime").val();
                }
            },
            columns: [
                {"data": "id"},
                {"data": "userId", "visible": false},//visible 隐藏或显示
                {"data": "userName"},
                {
                    "data": "operName", "render": function (data, type, row, meta) {
                //替换显示内容
                    var cont = data.substring(0, 30)+"...";
                    return "<span data-name='operName' data-cont='"+cont+"'><span class='glyphicon glyphicon-plus'>展开</span>"+cont + "<span>";
                }
                },
                {"data": "operTypeDto"},
                {"data": "operDescribe"},
                {
                    "data": "operParam", "render": function (data, type, row, meta) {
                    //替换显示内容
                    var cont = data.substring(0, 30)+"...";
                    return "<span data-name='operParam' data-cont='"+cont+"'><span class='glyphicon glyphicon-plus'>展开</span>"+cont + "<span>";
                }
                },
                {"data": "operLevelDto"},
                {"data": "operTime"},
            ],
            columnDefs: [
                {className: "dt-body-center", "targets": "_all"},
                {
                    "targets": [8],
                    "render": function (data, type, row, meta) {
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
                }
            }
        });
        </shiro:hasPermission>
    });
</script>
</body>
</html>