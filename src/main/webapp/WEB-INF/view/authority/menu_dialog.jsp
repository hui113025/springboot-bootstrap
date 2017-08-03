<%@ page pageEncoding="utf-8"  language="java" %>
<%-- 菜单图标弹框 --%>
<div class="modal fade" id="icon-alert">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title">请选择菜单图标</h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="alert m-b-0">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-bordered m-b-0">
                                <tbody>
                                <tr>
                                    <td class="text-center"><div><i class="fa fa-2x fa-adjust"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-adjust" checked="checked"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-male"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-male"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-archive"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-archive"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-arrows"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-arrows"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-asterisk"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-asterisk"></div></td>
                                </tr>
                                <tr>
                                    <td class="text-center"><div><i class="fa fa-2x fa-ban"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-ban"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-bank"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-bank"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-bar-chart-o"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-bar-chart-o"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-briefcase"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-briefcase"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-bars"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-bars"></div></td>
                                </tr>
                                <tr>
                                    <td class="text-center"><div><i class="fa fa-2x fa-wrench"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-wrench"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-video-camera"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-video-camera"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-book"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-book"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-bookmark"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-bookmark"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-cubes"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-cubes"></div></td>
                                </tr>
                                <tr>
                                    <td class="text-center"><div><i class="fa fa-2x fa-building-o"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-building-o"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-bullhorn"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-bullhorn"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-bullseye"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-bullseye"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-child"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-child"></div></td>
                                    <td class="text-center"><div><i class="fa fa-2x fa-calendar"></i></div><div class="hidden-xs"><input type="radio" name="icon" value="fa fa-2x fa-calendar"></div></td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="javascript:;" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
                <a id="saveIcon" href="javascript:;" class="btn btn-sm btn-danger" data-dismiss="modal">保存</a>
            </div>
        </div>
    </div>
</div>


<%-- 操作弹框 --%>
<div class="modal fade" id="oper-alert">
    <div class="modal-dialog">
        <div class="modal-content">
           <%-- <div class="panel panel-inverse">
                <div class="panel-heading">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 id="operTitle" class="panel-title"></h4>
                </div>
            </div>--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >修改用户</h4>
            </div>
            <div id="operBody" class="panel-body">
                <form id="operForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                    <input id="operId" type="hidden" >
                    <div class="form-group">
                        <label class="col-md-3 control-label">操作名称*</label>
                        <div class="col-md-9">
                            <input id="name" type="text"  class="form-control"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="operErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">操作权限码*</label>
                        <div class="col-md-9">
                            <input id="code" type="text" class="form-control" placeholder="操作码不允许填写view"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="operErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">操作描述</label>
                        <div class="col-md-9">
                            <textarea id="desc" class="form-control"  rows="5"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="closeOper" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="saveOper" href="javascript:;" class="btn btn-sm btn-success">保存</a>
            </div>
        </div>
    </div>
</div>

