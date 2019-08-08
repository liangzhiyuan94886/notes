<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>QuickNotes</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="/static/css/navigation.css">
    <script src="/static/echarts/echarts.min.js"></script>
    <script src="/static/layui/jquery-1.9.1.min.js" charset="utf-8"></script>
    <script src="/static/echarts/echarts.min.js"></script>
    <script src="${request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-container">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
        <legend>产品类型</legend>
    </fieldset>
    <button id="addData" class="layui-btn" style="float: right">添加</button>
    <div class="layui-row layui-col-space1">
        <div class="layui-col-md12">
            <table class="layui-hide" id="table1" lay-filter="table1"></table>
        </div>
    </div>
</div>

<div id="form1" style="display: none;width: 90%;margin-top: 30px;text-align: center">
    <form class="layui-form" action="/addNote">
        <div class="layui-inline">
            <label class="layui-form-label">所属产品</label>
            <div class="layui-input-inline">
                <select name="type" lay-search="" >
                    <option value="">直接选择或搜索选择</option>
                    <option value="1">java</option>
                    <option value="2">linux</option>
                </select>
            </div>
        </div>
        </br></br>
        <div class="layui-inline">
            <label class="layui-form-label">自定义类型</label>
            <div class="layui-input-block">
                <input type="text" lay-verify="title" autocomplete="off" class="layui-input">
            </div>
        </div>
        </br></br>
        <div class="layui-inline">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <input type="text" name="describe" lay-verify="title" autocomplete="off" class="layui-input">
            </div>
        </div>
        </br></br>
        <button type="submit" class="layui-btn" lay-submit="">保存</button>
    </form>
</div>
</body>
<script type="text/javascript" id="detail">
    <i class="layui-icon layui-icon-share" style="font-size: 30px; color: #009688;"></i>
</script>
<script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate','table'], function() {
        var form = layui.form,
            layer = layui.layer,
            laydate = layui.laydate;

        var table = layui.table;
        //初始化项目分类
        table.render({
            elem: '#table1',
            url: '/getALLNotes',
            // toolbar: true,
            title: '用户数据表',
            totalRow: true,
            cols: [[
                {field: 'id', title: '序号', width: 100, fixed: 'left', unresize: true, sort: true},
                {field: 'type', title: '类型' ,width: 100},
                { field: 'describe', title: '描述' ,width: 834, edit: 'text'},
                {field: '', title: '详情', width: 100, templet: '#detail'}
            ]],
            page: true,
            limit: 10,
            response: {
                statusCode: 200
            },
            parseData: function (res) {
                return {
                    "code": 200, //解析接口状态
                    "msg": '', //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.data.list //解析数据列表
                };
            },
        });

        //监听单元格编辑
        table.on('edit(table1)', function(obj){
            var describe = obj.value, //得到修改后的值
                // data = obj.data, //得到所在行所有键值
                id = obj.data.id
                // field = obj.field; //得到字段
            // layer.msg(value);
            $.ajax({
                type : "post",
                dataType : "json",
                data : {
                    describe : describe,
                    id : id
                },
                url : "/updateDescribe",
                success : function (data) {
                    layer.msg("修改成功！");
                }
            });
        });

        $('#addData').on('click', function(){
            var index = layer.open({
                title: "新增一个XXX",
                type: 1,
                content: $('#form1'),
                area: ['700px', '400px']
            });
        });

    });
</script>
</html>