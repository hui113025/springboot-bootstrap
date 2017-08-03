<%@ page pageEncoding="utf-8"  language="java" %>
<%-- 新增员工 --%>
<div class="modal fade bs-example-modal-lg" id="create-user-modal">
    <div class="modal-dialog">
        <div class="modal-content">
           <%-- <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></a>
                    </div>
                    <h4 class="panel-title">新增用户</h4>
                </div>
            </div>--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >新增用户</h4>
            </div>
            <div class="panel-body">
                <form id="userForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户263邮箱*</label>
                        <div class="col-md-7">
                            <input id="email" name="email" type="text"  class="form-control"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户手机*</label>
                        <div class="col-md-7">
                            <input id="mobile" name="mobile" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户姓名*</label>
                        <div class="col-md-7">
                            <input id="name" name="name" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户花名*</label>
                        <div class="col-md-7">
                            <input id="flowerName" name="flowerName" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">员工工号*</label>
                        <div class="col-md-7">
                            <input  id="num" name="num" onkeyup="this.value=this.value.replace(/\D/g,'')"   class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户职位*</label>
                        <div class="col-md-7">
                            <input  id="job" name="job" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户性别*</label>
                        <div class="col-md-7">
                            <div class="switch">
                                <input id="sex" name="sex" type="checkbox"  data-on-text="男" data-off-text="女" data-on-color="primary"  data-off-color="info"  checked />
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="close" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="create-user-btn"  href="javascript:;" class="btn btn-sm btn-success">保存</a>
            </div>
        </div>
    </div>
</div>

<%-- 修改员工 --%>
<div class="modal fade" id="update-user-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <%--<div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></a>
                    </div>
                    <h4 class="panel-title">修改用户</h4>
                </div>
            </div>--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >修改用户</h4>
            </div>
            <div class="panel-body">
                <form id="updateUserForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                    <input type="hidden" id="userId">
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户263邮箱*</label>
                        <div class="col-md-7">
                            <input id="update_email" name="email" type="text" readonly  class="form-control"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户手机*</label>
                        <div class="col-md-7">
                            <input id="update_mobile" name="mobile" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户姓名*</label>
                        <div class="col-md-7">
                            <input id="update_name" name="name" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户花名*</label>
                        <div class="col-md-7">
                            <input id="update_flowerName"  name="flowerName" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">员工工号*</label>
                        <div class="col-md-7">
                            <input  id="update_num" name="num" onkeyup="this.value=this.value.replace(/\D/g,'')"   class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户职位*</label>
                        <div class="col-md-7">
                            <input  id="update_job" name="job" class="form-control" maxlength="30"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户性别*</label>
                        <div class="col-md-7">
                                <input id="update_sex" name="sex" type="checkbox"  data-on-text="男" data-off-text="女" data-on-color="primary"  data-off-color="info"  checked />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">启用状态*</label>
                        <div class="col-md-7">
                            <div class="switch">
                                <input id="status" name="status" type="checkbox"  data-on-text="启用" data-off-text="停用" data-on-color="primary"  data-off-color="danger"  checked />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">修改密码</label>
                        <div class="col-md-7">
                            <input type="password" id="password" name="password" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">确认密码</label>
                        <div class="col-md-7">
                            <input type="password" id="rpassword" name="rpassword"  class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="update_close" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="update-user-btn"  href="javascript:;" class="btn btn-sm btn-success">保存</a>
            </div>
        </div>
    </div>
</div>