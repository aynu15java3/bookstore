<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">

			<tr>
				<td>父类:</td>
				<td>
					<input id="tagtree" name="pid"   data-options="required:true" style="width: 280px;">
				</td>
			</tr>
	        <tr>
	            <td>名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>

			<tr>
				<td>图标:</td>
				<td><input class="easyui-textbox" type="text" name="icon" data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>别名:</td>
				<td><input class="easyui-textbox" type="text" name="alias" data-options="required:true" style="width: 280px;"></input></td>
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
            url:'/tag/tagtree?tagId=1',//第一次请求
            onBeforeExpand:function (node) {//在节点展开前执行  参数：当前节点
                var id = node.tagId;
                //得到树形结构
                var $current = $("#tagtree").combotree("tree");
                //得到属性对象
                var option = $current.tree("options");
                //替换url属性
                option.url = "/tag/tagtree?tagId="+id;
            }
        });

    });


	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#itemAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//同步文本框中的商品描述
		/*itemAddEditor.sync();*/
		//ajax的post方式提交表单
		$.post("/tag/tagsave",$("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增标签成功!');
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		/*itemAddEditor.html('');*/
	}
</script>
