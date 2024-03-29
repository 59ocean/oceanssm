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
            <input name="id" type="hidden" value="${u.id}">
            <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">
                    <span class="x-red">*</span>邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_email" name="email" readonly value="${u.email}" required="" lay-verify="email" autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">
                    <span class="x-red">*</span>昵称</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_username" name="username" readonly value="${u.username}" required="" lay-verify="nikename" autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_account" class="layui-form-label">
                    <span class="x-red">*</span>账号</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_account" name="account" readonly value="${u.account}" required="" lay-verify="nikename" autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_account" class="layui-form-label">
                    手机号码</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_phone" name="phone" readonly value="${u.phone}" required="" lay-verify="phone" autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_roleID" class="layui-form-label">
                    所属角色</label>
                <div class="layui-input-inline">
                    <select id="L_roleID" name="roleId">
                        <option value="">请选择</option>
                        <option value="">北京</option>
                        <option value="">商户</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="L_account" class="layui-form-label"></label>
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
                //发异步，把数据提交给php
                $.ajax({
                    url:"${baseUrl}/user/update",
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
