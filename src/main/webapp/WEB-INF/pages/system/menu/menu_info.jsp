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
  		var index = "${systemMenuDTO.menuStatus }";
		var menuLevel = "${systemMenuDTO.menuLevel }";
		var menuFatherId = "${systemMenuDTO.menuFatherId }";
  		$(document).ready(function(){
  			//???????????????
	  		loadSelectData("util_status",$("#menuStatus"),index);
	  		loadSelectData("menu_level",$("#menuLevel"),menuLevel);
	  		
	  		//???????????????
	  		//??????????????????
        	if(menuFatherId == "0"){ //??????
        		 var data = new Array();
        		 var obj = new Object();
        		 obj.menuName = "ROOT";
        		 obj.menuId = "0";
			     data.unshift(obj);
        		$("#menuFatherId").combobox({
					data:data,
			        valueField:"menuId",
			        textField:"menuName"
			 	});
        		 //????????????
			    $("#menuFatherId").combobox("select",menuFatherId);
        	}else{
        		loadParentMenu(menuLevel);
        	}
		});
  		
  		
  		/*??????????????????*/
		function loadParentMenu(menuLevel){
			if(menuLevel == "1"){
				menuLevel = "0";
			}else if(menuLevel == "2"){
				menuLevel = "1";
			}else if (undefined == menuLevel || "" == menuLevel){
				return;
			}
			//????????????
			var param = getSystemParam();
			$.post("menu/selectMenuParentList.json?param="+param, {"menuLevel":menuLevel},function(data){
				 var obj = new Object();
				 obj.menuName = "?????????";
				 obj.menuId = "";
			     data.unshift(obj);
				$("#menuFatherId").combobox({
					data:data,
			        valueField:"menuId",
			        textField:"menuName"
			 	});
				//????????????
			   $("#menuFatherId").combobox("select",menuFatherId);
			}, "json");
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'??????????????????',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">???&nbsp;???</a>&nbsp;&nbsp;  
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'????????????????????????'" border="true"  noheader="true"   fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		  <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				 <label for="menuId">????????????:</label>
				<input class="easyui-textbox" id="menuId" type="text" style="width:70%;height:32px;padding:10px" name="menuId"  value="${systemMenuDTO.menuId }"  data-options="disabled:true,required:true">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="menuName">????????????:</label>
				<input class="easyui-textbox" id="menuName" type="text" style="width:70%;height:32px;padding:10px" name="menuName"  value="${systemMenuDTO.menuName }"  data-options="disabled:true,required:true">
			</div>
   			<div style="margin-top:15px">
				<label for="menuLevel">????????????:</label>
				<select id="menuLevel" class="easyui-combobox"  name="menuLevel"  style="width:70%;padding:10px;height:32px" data-options="disabled:true,required:true"  >  
				    <option value="">?????????</option>  
				</select>
			</div>
   			<div style="margin-top:15px">
				<label for="menuFatherId">???????????????:</label>
				<select id="menuFatherId" class="easyui-combobox"  name="menuFatherId"  style="width:70%;padding:10px;height:32px" data-options="disabled:true,required:true"  >  
				    <option value="">?????????</option>
				</select>  
			</div>
   			<div style="margin-top:15px">
				<label for="menuStatus">????????????:</label>
				<select id="menuStatus" class="easyui-combobox"  name="menuStatus"  style="width:70%;padding:10px;height:32px" data-options="disabled:true,required:true"  >  
				    <option value="">?????????</option>  
				</select>  
			</div>
   			<div style="margin-top:15px">
				<label for="menuUrl">??????URL:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="menuUrl"  value="${systemMenuDTO.menuUrl }"  data-options="disabled:true,required:true">
			</div>
   			<div style="margin-top:15px">
				<label for="menuIco">????????????:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="menuIco"  value="${systemMenuDTO.menuIco }"  data-options="disabled:true,required:true">
			</div>
   			
   			<div style="margin-top:15px">
				<label for="menuOrder">????????????:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="menuOrder"  value="${systemMenuDTO.menuOrder }"  data-options="disabled:true,required:true">
			</div>
			<div style="margin-top:15px">
				<label for="menuCreateUserId">?????????:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="menuCreateUserId" value="${systemMenuDTO.menuCreateUserName }" data-options="disabled:true,required:true">
			</div>
			<div style="margin-top:15px">
				<label for="menuCreateTime">????????????:</label>
				<input class="easyui-datetimebox" type="text" style="width:70%;height:32px;padding:10px" name="roleCreateTime" value="${systemMenuDTO.menuCreateTime }" data-options="disabled:true,required:true">
			</div>
			<div style="margin-top:15px">
				<label for="menuUpdateTime">????????????:</label>
				<input class="easyui-datetimebox" type="text" style="width:70%;height:32px;padding:10px" name="roleUpdateTime" value="${systemMenuDTO.menuUpdateTime }" data-options="disabled:true,required:true">
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