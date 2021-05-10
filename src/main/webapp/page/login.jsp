<%--
  Created by IntelliJ IDEA.
  User: ocean
  Date: 2019/7/5
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@include file="BaseContext.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="${baseUrl}/css/font.css"/>
    <link rel="stylesheet" href="${baseUrl}/css/login.css"/>
    <link rel="stylesheet" href="${baseUrl}/css/xadmin.css"/>
    <script src="${baseUrl}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${baseUrl}/js/jquery.min.js"></script>

    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">登录 </div>
    <div id="darkbannerwrap"></div>

    <form method="post"class="layui-form" >
        <input name="account" placeholder="用户名"  type="text" value="admin" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码" value="888888"  type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>

<script>

    $(function  () {
        layui.use(['layer','form'], function(){
            var layer = layui.layer
            var form = layui.form;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function(data){
                // alert(888)
                console.log(data.field)
                $.ajax({
                    type:"POST",
                    url:"${baseUrl}/user/login",
                    data:data.field,
                    dataType:"json",
                    success:function(json){
                        console.log(json)
                        if(json.code == '0'){
                            layer.msg(JSON.stringify(json.msg),function(){
                                location.href='${baseUrl}/user/home'
                            });
                        }else {
                            layer.msg(JSON.stringify(json.msg));
                        }

                    }
                })
                return false;
            });
        });
    })
</script>
<!-- 底部结束 -->
<script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
<script type="text/javascript" src="${baseUrl}/js/jquery.min.js"></script>
</html>
