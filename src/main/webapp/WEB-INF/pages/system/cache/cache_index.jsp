<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>" />
    <title>${WEBNAME}</title>
   	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/icon.css" />
   	<security:authentication property="principal" var="userSession" ></security:authentication>
   	<c:if test="${userSession.userSkin != ''}">
	    <link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/${userSession.userSkin}/easyui.css" />
	</c:if>
	<c:if test="${empty userSession || empty userSession.userSkin || userSession.userSkin == null}">
	    <link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/default/easyui.css" />
	</c:if>
	<link rel="stylesheet" type="text/css" href="./commons/css/index_select.css"/>
    <script type="text/javascript" src="./plugin/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
	<script type="text/javascript">
	    //页面对象
		var cacheObject = new Object();
		var rowIndex = -1;//标示当前的选择的行
		$(document).ready(function(){
			initAuthorize();
			//禁用按钮
			$("#delete-cache").linkbutton("disable");
			searchCache();
		});

		/*查询缓存信息的方法*/
		function searchCache(){
			rowIndex = -1;
			//禁用按钮
			$("#deleteCache").linkbutton("disable");
			var key = $("#key").val();//姓名
			var param = getSystemParam();
			$("#tableData").datagrid({   
			    url:"cache/getCacheList.json?param="+param, 
			    method:"post",
			    striped:true,//各行换色  
			    autoRowHeight:false,
			    singleSelect:true,
			    rownumbers:true,
			    fitColumns:true,
				fit:true,
			    columns:[[   
			        {field:"id",checkbox:true},
					{field:"value",title:"缓存名称",align:"center",width:100},
					{field:"size",title:"缓存值大小",align:"center",width:100}
			    ]],
			    //查询条件
			    queryParams: {
			    	"key":"*"
				},
			    onLoadError:function(none){
					//禁用按钮
					$("#delete-cache").linkbutton("disable");
					cacheObject = null;
					//error("缓存模块","查询缓存信息失败！");
				},
				onLoadSuccess:function(data){
					if(data.total == 0){
						info("缓存模块","当前没有查询到信息！");
					}
				},
			    onSelect:function(rowIndex, rowData){
					rowIndex = rowIndex;
					cacheObject = rowData;
					//禁用按钮
					$("#delete-cache").linkbutton("enable");
				},//双击事件
				onDblClickRow:function(rowIndex, rowData){
					rowIndex = rowIndex;
					cacheObject = rowData;
					//禁用按钮
					$("#delete-cache").linkbutton("enable");
				}
			}); 
		}
		/*删除缓存信息*/
		function deleteCache(){
			$.messager.confirm("缓存模块","您是否确认删除此条缓存信息吗？", function(r){
				if(r){
					var param = getSystemParam();
					var url = "cache/deleteCache.json?param="+param;
					$.post(url, { "key": cacheObject.value },
							   function(data){
						   		if(data.result == 1){
						   			info("缓存模块","删除缓存【"+cacheObject.value+"】成功！");
						   			self.searchCache();
						   			//禁用按钮
									$("#delete-cache").linkbutton("disable");
							   	}else{
							   		info("缓存模块","删除缓存【"+cacheObject.value+"】失败！");
								}
					   }, "json");
				}
			});
		}
		/*刷新所有缓存*/
		function reloadCache(){
			var param = getSystemParam();
			var url = "cache/reloadCache.json?param="+param;
			$.post(url, {},
					   function(data){
				   		if(data.result){
				   			info("缓存模块","刷新缓存成功！");
				   			self.searchCache();
				   			//禁用按钮
							$("#delete-cache").linkbutton("disable");
					   	}else{
					   		info("缓存模块","刷新缓存失败！");
						}
			   }, "json");
		}
		/*初始化按钮权限*/
		function initAuthorize(){
			$.ajax({
				   type: "POST",
				   url: "authorize/loadAuthorizeButton.json",
				   async: true,
				   data: {},
				   success: function(data){
					   if(null != data && "" != data && undefined != data){
						   var buttons = data.buttonList;
						   if(null != buttons && "" != buttons){
							   for(var i=0;i<buttons.length;i++){
								   $("#"+buttons[i].buttonCode).show();
							   }
						   }
					   }
				   }
				});
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;">  
<div data-options="region:'north',title:'系统缓存信息管理',iconCls:'icon-user-list'" border="false"  noheader="true" style="height:auto;padding:0px;">
	<!-- 查询条件 -->
	<div class="form_div" id="form_div" style="width:100%;height:auto;padding:0px; ">
       <!-- <form id="paramForm" method="post" name="paramForm" action="">  
			<div style="margin-top:10px;">
				<label for="key">缓存名称:</label>
				<input class="easyui-textbox trimBlank"  type="text" style="width:70%;height:32px;padding:10px" name="key" id="key" value=""  data-options="prompt:'请输入缓存名称'">
			</div>
	   </form> -->
    </div>
    <!-- 按钮 -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" id="search-cache" onclick="reloadCache()">刷新缓存</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-remove'" style="display:none;" id="delete-cache" onclick="deleteCache()">清除缓存</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;&nbsp;出</a>&nbsp;&nbsp;&nbsp;  
   	</div>
</div>  
<!-- 数据列表 展示 -->
<div data-options="region:'center',iconCls:'icon-user-list',title:'系统缓存信息展示列表'"  style="width:100%;height:100%;padding:0px;margin:0px; ">
   <table id="tableData"></table>
<!-- 数据提交 -->
<form id="indexForm" name="indexForm" action="" method="post" target="other" style="dispaly:none;padding:0px;margin:0px;">
	<input id="menuId" type="hidden" value="" name="menuURL" />
	<input id="editCacheId" type="hidden" value="" name="cacheId" />
</form> 
</div> 
</body>  
</html>