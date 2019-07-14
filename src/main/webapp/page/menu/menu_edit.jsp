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
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <input name="id" type="hidden" value="${menu.id}">
                        
                                                <div class="layui-form-item">
                <label for="L_name" class="layui-form-label">
                    <span class="x-red">*</span>菜单名</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_name" value="${menu.name" name="name" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                                <div class="layui-form-item">
                <label for="L_parentId" class="layui-form-label">
                    <span class="x-red">*</span>父菜单id</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_parentId" value="${menu.parentId" name="parentId" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                                <div class="layui-form-item">
                <label for="L_type" class="layui-form-label">
                    <span class="x-red">*</span>类型：1、菜单 2、功能</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_type" value="${menu.type" name="type" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                                <div class="layui-form-item">
                <label for="L_icon" class="layui-form-label">
                    <span class="x-red">*</span>图标</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_icon" value="${menu.icon" name="icon" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                                <div class="layui-form-item">
                <label for="L_menuSort" class="layui-form-label">
                    <span class="x-red">*</span>菜单排序</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_menuSort" value="${menu.menuSort" name="menuSort" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                                <div class="layui-form-item">
                <label for="L_menuUrl" class="layui-form-label">
                    <span class="x-red">*</span>菜单url</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_menuUrl" value="${menu.menuUrl" name="menuUrl" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                        
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-inline">
                    <button class="layui-btn" lay-filter="add" lay-submit="">确定</button>
                </div>
            </div>

        </form>
    </div>
</div>
<script>layui.use(['form', 'layer','jquery'],
        function() {
            $ = layui.jquery;
            var form = layui.form,
                    layer = layui.layer;

            //自定义验证规则
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

            //监听提交
            form.on('submit(add)',
                    function(data) {
                        console.log(data);
                        //发异步，把数据提交给后台
                        $.ajax({
                            url:"${baseUrl}/menu/update",
                            type:"POST",
                            data:data.field,
                            dataType:"json",
                            success:function(result){
                                if(result.code == '0'){
                                    layer.alert("修改成功", {
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
