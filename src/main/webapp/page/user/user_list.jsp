<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../BaseContext.jsp"%>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${baseUrl}/css/font.css">
    <link rel="stylesheet" href="${baseUrl}/css/xadmin.css">
    <script src="${baseUrl}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${baseUrl}/js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">系统管理</a>
            <a href="">用户管理</a>
            <a>
              <cite>用户列表</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit lay-filter="search"><i class="layui-icon">&#xe615;</i></button>

                        </div>
                    </form>
                </div>
                <script type="text/html" id="toolbarDemo">
                    <div class = "layui-btn-container" >
                    <button class="layui-btn layui-btn-danger" lay-event="batchDel"><i class="layui-icon"></i>批量删除</button>
                    <button class="layui-btn" onclick="xadmin.open('添加用户','${baseUrl}/user/toAdd',500,400)"><i class="layui-icon"></i>添加</button>
                    </div>
                </script>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form" id="dataTable" lay-filter="test">

                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="toobar">
    {{#  if(d.status == 1){ }}
    <a onclick="updateStatus(this,'禁用','{{d.id}}',0)" href="javascript:;"  title="禁用">
        <i class="layui-icon">&#xe601;</i>
    </a>
    {{#  } else { }}
    <a onclick="updateStatus(this,'启用','{{d.id}}',1)" href="javascript:;"  title="启用">
        <i class="layui-icon">&#xe62f;</i>
    </a>
    {{#  } }}
    <a title="编辑"  onclick="xadmin.open('编辑','${baseUrl}/user/toEdit?id={{d.id}}',600,400)" href="javascript:;">
        <i class="layui-icon">&#xe642;</i>
    </a>
    <%--<a onclick="xadmin.open('修改密码','member-password.html',600,400)" title="修改密码" href="javascript:;">
        <i class="layui-icon">&#xe631;</i>
    </a>--%>
    <a title="删除" onclick="member_del(this,'{{d.id}}')" href="javascript:;">
        <i class="layui-icon">&#xe640;</i>
    </a>

</script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var layer = layui.layer; //弹层
        var laydate = layui.laydate; //日期
        var laypage = layui.laypage; //分页
        var table = layui.table; //表格
        var carousel = layui.carousel; //轮播
        var upload = layui.upload; //上传
        var element = layui.element; //元素操作
        var form = layui.form;
        var index = layer.load(1);//开启进度条
        //绑定table
         var tableIn = table.render({
            elem: '#dataTable' ,//table id
            url: '${baseUrl}/user/list',
            method : 'GET', //方式
            page : true,//是否开启分页
            limits : [ 5, 10, 20, 30, 50, 100 ],
            limit : 5, //默认采用20
            cellMinWidth: 120,
            toolbar: '#toolbarDemo',
            even : true, //开启隔行背景
            id : 'searchID',
            done: function(res, curr, count){
                console.log("res",res);
                console.log("curr",curr);
                console.log("count",count)
                //加载后回调
                layer.close(index);//关闭
               // noAuthTip(res);//无权限提示
            },
            cols : [ [ //标题栏
                {checkbox: true},
                {field : 'account',title : '账号',align : 'center',sort : true,width : '15%'},
                {field : 'username',title : '用户名称',align : 'center',sort : true,width : '15%'},
                {field : 'phone',title : '手机号码',align : 'center',sort : true,width : '15%'},
                {field : 'email',title : '邮箱',align : 'center',sort : true,width : '12%'},
                {field : 'remark',title : '备注',align : 'center',sort : true,width : '12%'},
                {fixed : 'right',title : '操作',align : 'center',toolbar : '#toobar',width : '20%'}
            ] ]
        });
        form.on('submit(search)', function(data){
            console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            tableIn.reload({
                where: data.field
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
        function batchAll() {
            var checkStatus = table.checkStatus('searchID');
            var data = checkStatus.data;
            console.log(data)
            var ids = data.map(function(value){
                return value.id
            })
            console.log(ids)
            $.ajax({
                url:"${baseUrl}/user/batchDelete",
                type:"POST",
                data:{item:JSON.stringify(ids)},
                dataType:"json",
                success:function(result){
                    if(result.code == '0'){
                        layer.alert("刪除成功！", {
                                icon: 6
                            },
                            function() {
                                //关闭当前frame
                                xadmin.close();

                                // 可以对父窗口进行刷新
                                xadmin.father_reload();
                            });
                    }else{
                        layer.alert(result.msg, {
                            icon: 2
                        });
                    }
                }
            })
        }
        //头工具栏事件
        table.on('toolbar(test)',
            function(obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {
                    case 'batchDel':
                        batchAll();
                        break;

                };
            });
    });

    /*用户-停用*/
    function updateStatus(obj,statusText,id,status){
        layer.confirm('确认要'+statusText+'吗？',function(index){
            $.ajax({
                url:"${baseUrl}/user/update",
                type:"POST",
                data:{id:id,status:status},
                dataType:"json",
                success:function(result){
                    if(result.code == '0'){
                        layer.alert(statusText+"成功", {
                                icon: 6
                            },
                            function() {
                                //关闭当前frame
                                xadmin.close();

                                // 可以对父窗口进行刷新
                                xadmin.father_reload();
                            });
                    }else{
                        layer.alert(result.msg, {
                            icon: 2
                        });
                    }
                }
            })



        });
    }

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                url:"${baseUrl}/user/delete",
                type:"POST",
                data:{id:id},
                dataType:"json",
                success:function(result){
                    if(result.code == '0'){
                        layer.alert("删除成功！", {
                                icon: 6
                            },
                            function() {
                                //关闭当前frame
                                xadmin.close();

                                // 可以对父窗口进行刷新
                                xadmin.father_reload();
                            });
                    }else{
                        layer.alert(result.msg, {
                            icon: 2
                        });
                    }
                }
            })
        });
    }



    function delAll (argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</html>