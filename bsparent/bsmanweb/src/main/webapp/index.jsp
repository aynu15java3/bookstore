<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书销售商城后台管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/dq.css" />
<script type="text/javascript" src="js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:70px;text-align:center">
		<h1>图书销售商城后台管理系统</h1>
    </div>   
    <div data-options="region:'south'" style="height:50px;text-align:center">
    	共享版本V1.0
    </div> 
    <div data-options="region:'west',title:'导航栏',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
			<li>
				<span>分类管理</span>
				<ul>
					<li data-options="attributes:{'url':'tag-add'}">新增分类</li>
					<li data-options="attributes:{'url':'tag-list'}">查询分类</li>
				</ul>
			</li>
			<li>
         		<span>图书管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'item-add'}">新增图书</li>
	         		<li data-options="attributes:{'url':'item-list'}">查询图书</li>
	         	</ul>
         	</li>
         	<li>
         		<span>前台管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'content'}">内容管理</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	欢迎来到图书商城后台
		    </div>
		</div>
    </div>
    
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>