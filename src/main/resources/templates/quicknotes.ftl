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
    <div>
        <button id="addTag" class="layui-btn" style="float: right">新增标签</button>
        <button id="addData" class="layui-btn" style="float: right;margin-right: 20px">新增</button>
    </div>
    <div class="layui-row layui-col-space1">
        <div class="layui-col-md12">
            <table class="layui-hide" id="table1" lay-filter="table1"></table>
        </div>
    </div>
</div>
<#--————————————————以下隐藏弹框————————————————-->
<div id="form1" style="display: none;margin-top: 20px;">
    <form class="layui-form" >
        <div class="layui-inline">
            <label class="layui-form-label">标签</label>
            <div class="layui-input-inline"  id="selectId">
                <#--<select name="tid" lay-search="">-->
                    <#--<#list tag as t>-->
                        <#--<option value= ${t.tid} > ${t.tagName} </option>-->
                    <#--</#list>-->
                <#--</select>-->
            </div>
        </div>
        </br></br>
        <div class="layui-inline">
            <label class="layui-form-label">关键词</label>
            <div class="layui-input-block">
                <input id="keyword" type="text" autocomplete="off" class="layui-input" lay-verify="required" style="padding-right: 30px;">
            </div>
        </div>
        </br></br>
        <div class="layui-inline">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea id="text" placeholder="请输入内容" class="layui-textarea" lay-verify="required" style="width: 450px;"></textarea>
            </div>
        </div>
        </br></br>
        <div class="layui-inline">
            <label class="layui-form-label"></label>
            <button lay-filter="btn1" class="layui-btn" lay-submit="">保存</button>
        </div>
    </form>
</div>

<div id="form2" style="display: none;margin-top: 20px;">
    <form class="layui-form" >
        <div class="layui-inline">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input id="tag" type="text" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
        </br></br>
        <div class="layui-inline">
            <label class="layui-form-label"></label>
            <button lay-filter="btn2" class="layui-btn" lay-submit="">保存</button>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" id="detail">
    <i class="layui-icon layui-icon-share" style="font-size: 30px; color: #009688"></i>
</script>
<script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate','table'], function() {
        var form = layui.form,
            layer = layui.layer,
            laydate = layui.laydate;

        var table = layui.table;
        //初始化表格
        getAllNotes();
        function getAllNotes() {
            table.render({
                elem: '#table1',
                url: '/getALLNotes',
                // toolbar: true,
                title: '用户数据表',
                totalRow: true,
                cols: [[
                    {field: 'id', title: '序号', width: 100, fixed: 'left', unresize: true, sort: true},
                    {field: 'tagName', title: '标签' ,width: 100},
                    {field: 'keyword', title: '关键字' ,width: 100},
                    { field: 'content', title: '描述' ,width: 734, edit: 'text'},
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
        }
        getAllTags();
        function getAllTags() {
            $.ajax({
                type : "post",
                dataType : "json",
                url : "/getAllTags",
                success : function(data) {
                    var listHtml = '';
                    for (var i = 0;i<data.length;i++) {
                        listHtml += "<option value= '"+data[i].tid+"' >"+data[i].tagName+"</option>"
                        var selectHtml = '<select name="tid" lay-search="">' + listHtml + '</select>'
                            }
                    $("#selectId").html(selectHtml);
                    form.render();//重新渲染
                }
            });
        }

        //监听单元格编辑
        table.on('edit(table1)', function(obj){
            var content = obj.value, //得到修改后的值
                // data = obj.data, //得到所在行所有键值
                id = obj.data.id
                // field = obj.field; //得到字段
            // layer.msg(value);
            $.ajax({
                type : "post",
                dataType : "json",
                data : {
                    content : content,
                    id : id
                },
                url : "/updateContent",
                success : function (data) {
                    layer.msg("修改成功！");
                }
            });
        });

        $('#addData').on('click', function(){
            var index = layer.open({
                title: "新增一个信息",
                type: 1,
                content: $('#form1'),
                area: ['700px', '400px']
            });
        });

        //监听提交
        form.on('submit(btn1)', function(data){
            var keyword = $("#keyword").val(),
                text = $("#text").val();
            for(key in data.field) {
                if (key.indexOf("tid") > -1) {
                    var tid = data.field[key];
                }
            }
            $(".layui-layer-close1").trigger('click');
            $.ajax({
                type : "post",
                dataType : "json",
                data : {
                    content : text,
                    nid : tid,
                    keyword : keyword
                },
                url : "/addNote",
                success : function (data) {
                    layer.msg("新增成功！");
                }
            });
            getAllNotes();
            return false;
        });
        // 新增标签
        $('#addTag').on('click', function(){
            var index = layer.open({
                title: "新增标签",
                type: 1,
                content: $('#form2'),
                area: ['700px', '400px']
            });
        });

        form.on('submit(btn2)', function(data){
            var tag = $("#tag").val();
            $.ajax({
                type : "post",
                dataType : "json",
                data : {
                    tagName : tag
                },
                url : "/addTag",
                success : function (data) {
                    layer.msg("新增成功！");
                }
            });
            $(".layui-layer-close1").trigger('click');
            getAllTags();
            return false;
        });


    });

</script>
</html>