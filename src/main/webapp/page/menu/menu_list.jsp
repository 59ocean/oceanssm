<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../BaseContext.jsp"%>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>menu</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${baseUrl}/css/font.css">
    <link rel="stylesheet" href="${baseUrl}/css/xadmin.css">
    <script src="${baseUrl}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${baseUrl}/js/xadmin.js"></script>
    <style>
        .layui-table-body1{
            height: auto;
        }
    </style>
</head>
<body>


<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">系统管理</a>
            <a href="">菜单管理</a>
            <a>
              <cite>列表</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">

                <script type="text/html" id="toolbarDemo">
                    <div class = "layui-btn-container" >
                        <button class="layui-btn" onclick="xadmin.open('菜单用户','${baseUrl}/menu/toAdd',500,400)"><i class="layui-icon"></i>添加</button>
                    </div>
                </script>
                <div class="layui-card-body " id="treeTable" lay-filter="test">

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="toobar">
 <%--   <a title="查看"  onclick="xadmin.open('查看','${baseUrl}/menu/view?id={{d.id}}',600,400)" href="javascript:;">
        <i class="layui-icon">&#xe642;</i>
    </a>--%>
    <a title="编辑"  onclick="xadmin.open('编辑','${baseUrl}/menu/toEdit?id={{d.id}}',600,400)" href="javascript:;">
        <i class="layui-icon">&#xe642;</i>
    </a>
    <a title="删除" onclick="del(this,'{{d.id}}')" href="javascript:;">
        <i class="layui-icon">&#xe640;</i>
    </a>
</script>
<script>
    var editObj=null,ptable=null,treeGrid=null,tableId='treeTable',layer=null;
    layui.config({
        base: '${baseUrl}/lib/layui/extend/'
    }).extend({
        treeGrid:'treeGrid'
    }).use(['laydate', 'laypage', 'layer', 'table','treeGrid', 'carousel', 'upload', 'element'], function(){
        var layer = layui.layer; //弹层
        var laydate = layui.laydate; //日期
        var laypage = layui.laypage; //分页
        var table = layui.table; //表格
        var carousel = layui.carousel; //轮播
        var upload = layui.upload; //上传
        var element = layui.element; //元素操作
        var form = layui.form;
        //var index = layer.load(1);//开启进度条
        //绑定table
        var $=layui.jquery;
        treeGrid = layui.treeGrid;//很重要
        layer=layui.layer;
        ptable=treeGrid.render({
            id:tableId
            ,elem: '#'+tableId
            ,idField:'id'
            ,url:'${baseUrl}/menu/list'
            ,cellMinWidth: 100
            ,toolbar: '#toolbarDemo'
            ,height:800
            ,treeId:'id'//树形id字段名称
            ,treeUpId:'parentId'//树形父id字段名称
            ,treeShowName:'name'//以树形式显示的字段
            ,cols: [[

                {field:'name',width:'20%',align : 'center', title: '菜单名称'}
                ,{field:'menuSort',width:'10%',align : 'center', title: '排序'},
                {field:'icon',width:'10%',align : 'center', title: '图标'},
                {field:'type',width:'10%', align : 'center',title: '菜单类型',templet:function (d) {
                      var html='';
                      var text = '';
                      if(d.type==1){
                          text = '菜单'
                      }else{
                          text = '功能'
                      }
                      return text;
                    }}
                ,{field:'menuUrl',width:'20%', title: '菜单url'},
                {width:'20%',title: '操作', align:'center'/*toolbar: '#barDemo'*/
                    ,toolbar:'#toobar'
                }
            ]]
            ,page:false
        });

        treeGrid.on('tool('+tableId+')',function (obj) {
            if(obj.event === 'del'){//删除行
                console.log(obj)
                del(obj);
            }else if(obj.event==="edit"){//添加行
                add(obj.data);
            }
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

        function batchAll() {
            var checkStatus = table.checkStatus('searchID');
            var data = checkStatus.data;
            console.log(data)
            var ids = data.map(function(value){
                return value.id
            })
            console.log(ids)
            $.ajax({
                url:"${baseUrl}/menu/batchDelete",
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
        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '' //指定元素
        });
    });

    /*menu删除*/
    function del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                url:"${baseUrl}/menu/delete",
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