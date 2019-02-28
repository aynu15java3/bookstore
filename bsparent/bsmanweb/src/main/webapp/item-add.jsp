<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
				<tr>
					<td>图书类型:</td>
					<td>
						<input id="tagtree" name="tagId"  value=" " data-options="required:true" style="width: 280px;">
					</td>
				</tr>
	        <tr>
	            <td>图书名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
			<tr>
				<td>图书作者:</td>
				<td><input class="easyui-textbox" type="text" name="author" data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>出版社:</td>
				<td><input class="easyui-textbox" type="text" name="publishing" data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>原价:</td>
				<td><input class="easyui-numberbox" type="text" name="oPrice" data-options="required:true,min:0,precision:2" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>现价:</td>
				<td><input class="easyui-numberbox" type="text" name="dPrice" data-options="required:true,min:0,precision:2" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>库存:</td>
				<td><input type="text" class="easyui-numberbox" value="999"name="amount" data-options="min:0,precision:0" style="width: 280px;"></input></td>
			</tr>

	        <tr>
	            <td>图书图片:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr>
	        <tr>
	            <td>图书描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="description"></textarea>
	            </td>
	        </tr>

	    </table>

	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
    $(function () {
        $("#tagtree").combotree({
            url:'tag/tagList?tagId=1',//第一次请求
            onBeforeExpand:function (node) {//在节点展开前执行  参数：当前节点
                var id = node.tagId;
                //得到树形结构
                var $current = $("#tagtree").combotree("tree");
                //得到属性对象
                var option = $current.tree("options");
                //替换url属性
                option.url = "tag/tagList?tagId="+id;
            },
            onClick:function (node) {
                var isLeaf =  $("#tagtree").tree("isLeaf",node.target);
                if (!isLeaf){
                    $.messager.alert("警告","请选择具体的标签");
                    return false;
                }
                return true;

            }
        });

    });





	var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//创建富文本编辑器
		itemAddEditor = DONGQU.createEditor("#itemAddForm [name=description]");
		//初始化类目选择和图片上传器
		DONGQU.init({fun:function(node){
		}});
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#itemAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//同步文本框中的商品描述
		itemAddEditor.sync();
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
//		alert($("#itemAddForm").serialize());
		$.post("/item/booksave",$("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增图书成功!');
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
