<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>upload</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css"  media="all">
    <script src="/static/layui/jquery-1.9.1.min.js" charset="utf-8"></script>
    <script src="${request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layui-row" style="text-align: center;margin-top: 10px">
    <div class="layui-col-xs6">
        <div class="grid-demo grid-demo-bg1">
            <textarea class="scroll" id="text" placeholder="在这粘贴图片..." style="width: 300px;height: 180px;"></textarea>
        </div>
    </div>
    <div class="layui-col-xs6">
        <div class="grid-demo">
            <div id="text2" style="border: #5FB878 1px dashed;height: 180px;width: 325px"></div>
        </div>
    </div>
</div>

<div style="text-align: center">
    <#list pic as p>
        <img src="/static/images/upload/${p.picName}" style="height: 200px;width: 325px">
    </#list>
</div>
</body>
<script type="text/javascript">
    layui.use(['form', 'layedit', 'laydate','table'], function() {
        var layer = layui.layer;
        document.querySelector("#text").addEventListener("paste", function(e){
            //添加监听paste事件
            var cbd = e.clipboardData;
            var ua = window.navigator.userAgent;
            if (!(e.clipboardData && e.clipboardData.items)) {
                return;
            }
            if (cbd.items && cbd.items.length === 2 && cbd.items[0].kind === "string" && cbd.items[1].kind === "file" && cbd.types && cbd.types.length === 2 && cbd.types[0] === "text/plain" && cbd.types[1] === "Files" && ua.match(/Macintosh/i) && Number(ua.match(/Chrome\/(\d{2})/i)[1]) < 49) {
                return;
            }
            for (var i = 0; i < cbd.items.length; i++) {
                var item = cbd.items[i];
                if (item.kind == "file") {
                    var blob = item.getAsFile();
                    if (blob.size === 0) {
                        return;
                    }
                    var data = new FormData();
                    data.append("blob", blob);
                    $.ajax({
                        url: "/uploads",
                        type: 'POST',
                        cache: false,
                        data: data,
                        processData: false,
                        contentType: false,
                        success: function (result) {
                            if (result.state == "1") {
                                layer.tips(result.msg, '#text2');
                                var html = "<img src='" + result.fileAddress + "' width='325' height='180'>";
                                $("#text2").html(html);
                                $("#submit").trigger("click"); //模拟点击按钮，粘贴之后直接发送
                            } else if (result.state == "2") {
                                layer.msg(result.msg)
                            } else if (result.state == "3") {
                                layer.msg(result.msg)
                            }
                        }
                    });
                }
            }
        }, false);
    });
</script>
</html>