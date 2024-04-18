<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>设备申报单</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/plugins/jqgrid/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="/css/modules/vue/vue-datepicker-local.css">
    <script src="/libs/jquery.min.js"></script>
    <script src="/plugins/layer/layer.js"></script>
    <script src="/libs/bootstrap.min.js"></script>
    <script src="/libs/vue.min.js"></script>
    <script src="/libs/vue-resource.min.js"></script>
    <script src="/libs/vue-validator.min.js"></script>
    <script src="/libs/vue-datepicker-local.js"></script>
    <script src="/plugins/jqgrid/grid.locale-cn.js"></script>
    <script src="/plugins/jqgrid/jquery.jqGrid.min.js"></script>
    <script src="/plugins/ztree/jquery.ztree.all.min.js"></script>
    <script src="/plugins/ztree/jquery.ztree.all.min.js"></script>
    <script src="/plugins/select2/js/select2.js"></script>
    <script src="/plugins/layui/layui.all.js"></script>
    <script src="/libs/moment.js"></script>
    <script src="/js/common.js"></script>
</head>
<body>
<div id="_list">
    <div class="container-fluid" style="margin-bottom: 20px;">
        <div class="col-md-12 col-lg-12 col-sm-12 row">
            <div class="col-md-3 col-lg-3 col-sm-3 form-inline">
                <label class="control-label">设备</label>
                <input v-model="deviceCode" type="text" placeholder="请输入设备编号/名称" class="form-control">
            </div>
            <div class="col-md-3 col-lg-3 col-sm-3 form-inline">
                <label class="control-label">申报部门</label>
                <select v-model="department" class="form-control" placeholder="请选择申报部门">
                    <option value="">请选择申请部门</option>
                    <template v-if="departmentOptions && departmentOptions.length > 0" v-for="department in departmentOptions">
                        <option :value="department.id" v-text="department.text"></option>
                    </template>
                </select>
            </div>
            <div class="col-md-3 col-lg-3 col-sm-3 form-inline">
                <label class="control-label" style="float: left; margin-top: 7px">申报时间</label>
                <vue-datepicker-local v-model="startTime" placeholder="开始时间" style="width: 120px; float:left; margin-left: 5px"></vue-datepicker-local>
                <vue-datepicker-local v-model="endTime" placeholder="结束时间" style="width: 120px; float:left;"></vue-datepicker-local>
            </div>
            <div class="col-md-3 col-lg-3 col-sm-3 form-inline">
                <a class="btn btn-primary" @click="query" style="margin-left: 50px"><i class="fa fa-search"></i>&nbsp;查询</a>
                <a class="btn btn-primary" @click="exportExcel"><i class="fa"></i>导出Excel</a>
            </div>
        </div>
        <div class="col-md-12 col-lg-12 col-sm-12 row" style="margin-top:20px;">
            <div class="col-md-5 col-lg-5 col-sm-5 form-inline">
                <label class="control-label" style="float:left; margin-top: 7px">申报单状态</label>
                <select v-model="applyStatus" class="form-control" style="width: 230px; float:left; margin-left: 5px">
                    <option value="">请选择申报单状态</option>
                    <option value="0">待处理</option>
                    <option value="1">转交</option>
                    <option value="2">已提交</option>
                    <option value="3">已终止</option>
                </select>
            </div>
        </div>
    </div>
    <hr/>
    <a class="btn btn-primary" @click="add" style="float:left; margin-left: 20px; margin-top: -10px"><i class="fa"></i>新增申报单</a>
    <div style="height: 600px">
        <table class="layui-table" style="float: left; margin-top: 10px;">
            <thead>
            <tr>
                <th>序号</th>
            <#list meta as m>
                <th>${m.comment}</th>
            </#list>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <template v-if="rows && rows.length > 0" v-for="row, index in rows">
                <tr>
                    <td v-text="index + 1"></td>
                <#list meta as m>
                    <td v-text="row.${m.fieldName}"></td>
                </#list>
                    <td>
                        <input @click="edit(row)" type="button" class="layui-btn layui-btn-primary layui-btn-xs" value="编辑"/>
                        <input @click="remove(row)" type="button" class="layui-btn layui-btn-primary layui-btn-xs" value="删除"/>
                        <input @click="stop(row)" type="button" class="layui-btn layui-btn-primary layui-btn-xs" value="终止"/>
                        <input @click="reason(row)" type="button" class="layui-btn layui-btn-primary layui-btn-xs" value="原因"/>
                    </td>
                </tr>
            </template>
            </tbody>
        </table>
    </div>

    <div id="layPager" style="float:left; margin-left: 400px"></div>
</div>
<script src="${jsPath}/${variableName}/list.js"></script>
</body>
</html>