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
	<link rel="stylesheet" type="text/css" href="./commons/css/form.css" />
    <script type="text/javascript" src="./plugin/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.validate.js"></script>
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
  	<script type="text/javascript">
  	    var index = "${systemButtonDTO.buttonStatus}";
  	    var menuId = "${systemButtonDTO.menuId}";
  		$(document).ready(function(){
  			//加载下拉框
			loadSelectData("util_status",$("#buttonStatus"),index);
			initMenu();
		});
  		
  		/*初始化菜单选择框*/
		function initMenu(){
			$.ajax({
				   type: "POST",
				   url: "menu/selectMenuParentList.json",
				   async: false,
				   data: {},
				   success: function(data){
					   var obj = new Object();
					   obj.menuName = "请选择";
					   obj.menuId = "";
					   data.unshift(obj);
					   menu_data = data;
					   $("#menuId").combobox({
							data:data,
					        valueField:"menuId",
					        textField:"menuName",
					        panelHeight:200
					 	});
					   //默认选中
					    $("#menuId").combobox("select",menuId);
				   }
				});
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'按钮信息展示',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>&nbsp;&nbsp;  
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统按钮信息管理'" border="true"  noheader="true"   fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		  <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				 <label for="buttonId">按钮编号:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="buttonId" value="${systemButtonDTO.buttonId }" data-options="disabled:true,editable:false,required:true">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="buttonName">按钮名称:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="buttonName" value="${systemButtonDTO.buttonName }" data-options="disabled:true,editable:false,required:true">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="buttonUrl">按钮url:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="buttonUrl" value="${systemButtonDTO.buttonUrl }" data-options="disabled:true,editable:false,required:true">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="buttonCode">按钮代码:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="buttonCode" value="${systemButtonDTO.buttonCode }" data-options="disabled:true,editable:false,required:true">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="buttonIco">按钮图标:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="buttonIco" value="${systemButtonDTO.buttonIco }" data-options="disabled:true,editable:false,required:true">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="menuId">菜单名称:</label>
				<select id="menuId" class="easyui-combobox"  name="menuId" style="width:70%;padding:10px;height:32px" data-options="disabled:true,editable:false,required:true">  
				    <option value="">请选择</option> 
				</select>  
			</div>
   			<div style="margin-top:15px">
				<label for="buttonStatus">按钮状态:</label>
				<select id="buttonStatus" class="easyui-combobox"  name="buttonStatus" style="width:70%;padding:10px;height:32px" data-options="disabled:true,editable:false,required:true">  
				    <option value="">请选择</option> 
				</select>  
			</div>
			<div style="margin-top:15px">
				<label for="buttonCreateUserId">创建人:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px"  value="${systemButtonDTO.buttonCreateUserName }" data-options="disabled:true,required:true">
			</div>
			<div style="margin-top:15px">
				<label for="buttonCreateTime">创建时间:</label>
				<input class="easyui-datetimebox" type="text" style="width:70%;height:32px;padding:10px" name="buttonCreateTime" value="${systemButtonDTO.buttonCreateTime }" data-options="disabled:true,required:true">
			</div>
			<div style="margin-top:15px">
				<label for="buttonUpdateTime">修改时间:</label>
				<input class="easyui-datetimebox" type="text" style="width:70%;height:32px;padding:10px" name="buttonUpdateTime" value="${systemButtonDTO.buttonUpdateTime }" data-options="disabled:true,required:true">
			</div>
			<div style="margin-top:15px;">&nbsp;</div>
			<div style="margin-top:15px;">&nbsp;</div>
		   </form>
		  </div>
    </div>
    </div>
    <iframe id="other" name="other" src=""  style="padding:0px;margin:0px;display:none;"></iframe>  
</body>  
</html>