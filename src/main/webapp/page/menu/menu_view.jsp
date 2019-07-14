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
                    <input type="text" id="L_name" readonly value="${menu.name}" name="name" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                    <div class="layui-form-item">
                <label for="L_parentId" class="layui-form-label">
                    <span class="x-red">*</span>父菜单id</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_parentId" readonly value="${menu.parentId}" name="parentId" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                    <div class="layui-form-item">
                <label for="L_type" class="layui-form-label">
                    <span class="x-red">*</span>类型：1、菜单 2、功能</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_type" readonly value="${menu.type}" name="type" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                    <div class="layui-form-item">
                <label for="L_icon" class="layui-form-label">
                    <span class="x-red">*</span>图标</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_icon" readonly value="${menu.icon}" name="icon" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                    <div class="layui-form-item">
                <label for="L_menuSort" class="layui-form-label">
                    <span class="x-red">*</span>菜单排序</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_menuSort" readonly value="${menu.menuSort}" name="menuSort" required="" autocomplete="off" class="layui-input">
                </div>
            </div>
                                    <div class="layui-form-item">
                <label for="L_menuUrl" class="layui-form-label">
                    <span class="x-red">*</span>菜单url</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_menuUrl" readonly value="${menu.menuUrl}" name="menuUrl" required="" autocomplete="off" class="layui-input">
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





        });</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
