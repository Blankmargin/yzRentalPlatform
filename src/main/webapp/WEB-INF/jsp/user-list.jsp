<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="userList" title="用户列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:200">用户ID</th>
        <th data-options="field:'username',width:200">用户名</th>
        <th data-options="field:'password',width:200">密码</th>
        <th data-options="field:'phone',width:100">手机号</th>
        <th data-options="field:'email',width:100">邮箱</th>
        <th data-options="field:'createtime',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
        <th data-options="field:'updatetime',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
    </tr>
    </thead>
</table>
<div id="userEditWindow" class="easyui-window" title="编辑用户" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/user-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>
    function getSelectionsIds(){
        var userList = $("#userList");
        var sels = userList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
            $(".tree-title:contains('新增用户')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
            var ids = getSelectionsIds();
            if(ids.length == 0){
                $.messager.alert('提示','必须选择一个用户才能编辑!');
                return ;
            }
            if(ids.indexOf(',') > 0){
                $.messager.alert('提示','只能选择一个用户!');
                return ;
            }
            $("#userEditWindow").window({
                onLoad :function(){
                    //回显数据
                    var data = $("#userList").datagrid("getSelections")[0];
                    $("#userEditForm").form("load",data);
                }
            }).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
            var ids = getSelectionsIds();
            if(ids.length == 0){
                $.messager.alert('提示','未选中用户!');
                return ;
            }
            $.messager.confirm('确认','确定删除ID为 '+ids+' 的用户吗？',function(r){
                if (r){
                    var params = {"ids":ids};
                    $.post("/rest/user/delete",params, function(data){
                        if(data.status == 200){
                            $.messager.alert('提示','删除用户成功!',undefined,function(){
                                $("#userList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>