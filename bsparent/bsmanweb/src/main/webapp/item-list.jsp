<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="图书列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/booklist',method:'get',pageSize:10,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">图书ID</th>
            <th data-options="field:'tagId',width:60">分类</th>
            <th data-options="field:'name',width:60">书名</th>
            <th data-options="field:'author',width:60">作者</th>
            <th data-options="field:'publishing',width:60">出版社</th>
            <th data-options="field:'oPrice',width:60">原价</th>
            <th data-options="field:'dPrice',width:60">现价</th>
            <th data-options="field:'amount',width:60">库存</th>
            <th data-options="field:'description',width:60">简介</th>
            <th data-options="field:'createdAt',width:130,align:'center',formatter:DONGQU.formatDateTime">创建日期</th>
            <th data-options="field:'updatedAt',width:130,align:'center',formatter:DONGQU.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑图书" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
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
        	$(".tree-title:contains('新增图书')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个图书才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个图书!');
        		return ;
        	}
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        		    //回显数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			$("#itemeEditForm").form("load",data);
        			DONGQU.init({
        				"pics" : data.image,
        				"tagId" : data.tagId,
//
        			});
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中图书!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的图书吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/bookdelete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除图书成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }
//    ,'-',{
//        text:'下架',
//        iconCls:'icon-remove',
//        handler:function(){
//        	var ids = getSelectionsIds();
//        	if(ids.length == 0){
//        		$.messager.alert('提示','未选中商品!');
//        		return ;
//        	}
//        	$.messager.confirm('确认','确定下架ID为 '+ids+' 的商品吗？',function(r){
//        	    if (r){
//        	    	var params = {"ids":ids};
//                	$.post("/rest/item/instock",params, function(data){
//            			if(data.status == 200){
//            				$.messager.alert('提示','下架商品成功!',undefined,function(){
//            					$("#itemList").datagrid("reload");
//            				});
//            			}
//            		});
//        	    }
//        	});
//        }
//    },{
//        text:'上架',
//        iconCls:'icon-remove',
//        handler:function(){
//        	var ids = getSelectionsIds();
//        	if(ids.length == 0){
//        		$.messager.alert('提示','未选中商品!');
//        		return ;
//        	}
//        	$.messager.confirm('确认','确定上架ID为 '+ids+' 的商品吗？',function(r){
//        	    if (r){
//        	    	var params = {"ids":ids};
//                	$.post("/rest/item/reshelf",params, function(data){
//            			if(data.status == 200){
//            				$.messager.alert('提示','上架商品成功!',undefined,function(){
//            					$("#itemList").datagrid("reload");
//            				});
//            			}
//            		});
//        	    }
//        	});
//        }
//    }
    ];
</script>