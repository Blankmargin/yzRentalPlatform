<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="userAddForm" class="itemForm" method="post">
        <table cellpadding="5">
            <tr>
                <td>用户名:</td>
                <td><input class="easyui-textbox" type="text" name="username" data-options="required:true" style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input class="easyui-textbox" type="text" name="password" data-options="required:true" style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>手机号:</td>
                <td><input class="easyui-textbox" type="text" name="phone" data-options="required:true" style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input class="easyui-textbox" type="text" name="email" data-options="required:true" style="width: 280px;"></input></td>
            </tr>
        </table>
        <input type="hidden" name="itemParams"/>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">
    var itemAddEditor ;
    
    //提交表单
    function submitForm(){
        //有效性验证
        if(!$('#userAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        //ajax的post方式提交表单
        alert($("#userAddForm").serialize());
        $.post("/user/save",$("#userAddForm").serialize(), function(data){
            if(data.status == 200){
                $.messager.alert('提示','新增用户成功!');
                $(".tree-title:contains('查询用户')").parent().click();
                $('#tabs').tabs('close','新增用户');
            }
        });
    }
    function clearForm(){
        $('#userAddForm').form('reset');
        itemAddEditor.html('');
    }
</script>