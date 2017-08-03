/*   
Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.5
Version: 1.9.0
Author: Sean Ngu
Website: http://www.seantheme.com/color-admin-v1.9/admin/
*/

var handleDataTableSelect = function() {
	"use strict";
    if ($('#data-table').length !== 0) {
      var empData =  $('#data-table').DataTable({
            select: true, //是否选中
            responsive: true,
            searching:false, //是否开启搜索
            pagingType:"full_numbers",//分页类型 full_numbers显示所有分页信息
            processing:"true", //是否显示处理状态
            serverSide:"true", //是否开启服务器模式
            aLengthMenu:[10,20,50,100], //下拉框每页显示数量
            ajax:{
              "url":"/emp/serach",
              "type": "POST",
              "dataType":"json",
              "data":{
                  "id":1,
                  "name":"haha"
              }
            },
            columns: [
                { "data": "first_name" },
                { "data": "last_name" },
                { "data": "position" },
                { "data": "office" },
                { "data": "start_date" },
                { "data": "salary" }
            ],
            language:{
                "lengthMenu": "每页 _MENU_ 条记录",//下拉框文字
                "info":"第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",//左下角显示文字
                paginate:{                          //分页
                    first:"首页",
                    last:"尾页",
                    next:"下一页",
                    previous:"上一页"
                }
            }
        });
    }
};


var TableManageTableSelect = function () {
	"use strict";
    return {
        //main function
        init: function () {
            handleDataTableSelect();
        }
    };
}();