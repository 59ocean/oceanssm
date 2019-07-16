<%--
Created by IntelliJ IDEA.
User: hori
Date: 2019/7/8
Time: 16:28
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../BaseContext.jsp"%>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${baseUrl}/css/font.css">
    <link rel="stylesheet" href="${baseUrl}/css/xadmin.css">
    <script type="text/javascript" src="${baseUrl}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${baseUrl}/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <input type="hidden" name="parentId" id="parentId">
            <div class="layui-form-item">
            <label for="L_name" class="layui-form-label">
                <span class="x-red">*</span>菜单名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_name" name="name" required="" autocomplete="off" class="layui-input">
            </div>
        </div>
                                <div class="layui-form-item">
            <label for="L_parentId" class="layui-form-label">
                <span class="x-red">*</span>上级菜单
            </label>
            <div class="layui-input-inline">
                <input type="text" id="tree" id="L_parentId" lay-filter="tree"  required="" autocomplete="off" class="layui-input">
            </div>
        </div>

            <%--<div class="layui-form-item">
            <label for="L_icon" class="layui-form-label">
                <span class="x-red">*</span>图标
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_icon" name="icon" required="" autocomplete="off" class="layui-input">
            </div>
        </div>--%>
                                <div class="layui-form-item">
            <label for="L_menuSort" class="layui-form-label">
                <span class="x-red">*</span>菜单排序
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_menuSort" name="menuSort" required="" autocomplete="off" class="layui-input">
            </div>
        </div>
                                <div class="layui-form-item">
            <label for="L_menuUrl" class="layui-form-label">
                <span class="x-red">*</span>菜单url
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_menuUrl" name="menuUrl" required="" autocomplete="off" class="layui-input">
            </div>
        </div>
                


            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-filter="add" lay-submit="">增加</button>
                </div>
            </div>

        </form>
    </div>
</div>
<script>
    layui.config({
        base: "${baseUrl}/lib/layui/module/"
    }).extend({
        treeSelect: "treeSelect/treeSelect"
    });

    layui.use(['form','treeSelect', 'layer','jquery'],
        function() {
            $ = layui.jquery;
            var form = layui.form,
                    layer = layui.layer;

            //自定义验证规则 对应着lay-verify的
            form.verify({
                nikename: function(value) {
                    if (value.length < 5) {
                        return '昵称至少得5个字符啊';
                    }
                },
                pass: [/(.+){6,12}$/, '密码必须6到12位'],
                repass: function(value) {
                    if ($('#L_pass').val() != $('#L_repass').val()) {
                        return '两次密码不一致';
                    }
                }
            });
            var treeSelect= layui.treeSelect;

            treeSelect.render({
                // 选择器
                elem: '#tree',
                // 数据
                data: '${baseUrl}/menu/getTreeMenu',
                // 异步加载方式：get/post，默认get
                type: 'get',
                // 占位符
                placeholder: '请选择上级菜单',
                // 是否开启搜索功能：true/false，默认false
                search: true,
                // 点击回调
                click: function(d){
                    console.log(d.current.id);
                    $('#parentId').val(d.current.id)
                },
                // 加载完成后的回调函数
                success: function (d) {
                    console.log(d);
//                选中节点，根据id筛选
//                treeSelect.checkNode('tree', 3);

//                获取zTree对象，可以调用zTree方法
//                var treeObj = treeSelect.zTree('tree');
//                console.log(treeObj);

//                刷新树结构
//                treeSelect.refresh();
                }
            });
            //监听提交
            form.on('submit(add)',
                    function(data) {
                        console.log(data);
                        //发异步，把数据提交给php
                        $.ajax({
                            url:"${baseUrl}/menu/save",
                            type:"POST",
                            data:data.field,
                            dataType:"json",
                            success:function(result){
                                if(result.code == '0'){
                                    layer.alert("增加成功", {
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

                        return false;
                    });

        });</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
