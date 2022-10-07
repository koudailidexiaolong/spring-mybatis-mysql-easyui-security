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
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			var index = "${systemOrgDTO.orgStatus }";
  			var orgLevel = "${systemOrgDTO.orgLevel }";
  			var orgType = "${systemOrgDTO.orgType }";
  			//加载下拉框
			loadSelectData("util_status",$("#orgStatus"),index);
			loadSelectData("org_level",$("#orgLevel"),orgLevel);
			loadSelectData("org_type",$("#orgType"),orgType);
		});
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'机构信息展示',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>&nbsp;&nbsp;  
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统机构信息管理'" border="true"  noheader="true"  fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		  <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				 <label for="orgId">机构编码:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="orgId" value="${systemOrgDTO.orgId }" data-options="disabled:true,">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="orgName">机构简称:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="orgName" value="${systemOrgDTO.orgName }" data-options="disabled:true,">
			</div>
			<div style="margin-top:15px;">
				<label for="orgFullName">机构全称:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="orgFullName" value="${systemOrgDTO.orgFullName }"  data-options="disabled:true,">
			</div>
			<div style="margin-top:15px">
				<label for="orgLevel">机构等级:</label>
				<select id="orgLevel" class="easyui-combobox"  name="orgLevel" style="width:70%;padding:10px;height:32px" data-options="disabled:true">  
				    <option value="">请选择</option>  
				</select>  
			</div>
			<div style="margin-top:15px">
				<label for="orgType">机构类型:</label>
				<select id="orgType" class="easyui-combobox"  name="orgType" style="width:70%;padding:10px;height:32px" data-options="disabled:true">  
				    <option value="">请选择</option>  
				</select>  
			</div>
   			<div style="margin-top:15px">
				<label for="orgStatus">机构状态:</label>
				<select id="orgStatus" class="easyui-combobox"  name="orgStatus" style="width:70%;padding:10px;height:32px" data-options="disabled:true">  
				    <option value="">请选择</option>  
				</select>  
			</div>
   			<div style="margin-top:15px">
				<label for="orgParentId">父级机构编码:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="orgParentId" value="${systemOrgDTO.orgParentId }"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px">
				<label for="orgName">父级机构名称:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="orgName" value="${systemOrgDTO.orgName }"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="orgPhone">组织机构电话:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:62px;padding:10px" name="orgPhone" value="${systemOrgDTO.orgPhone }"  data-options="multiline:true,disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="orgAddress">网点地址:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:62px;padding:10px" name="orgAddress" value="${systemOrgDTO.orgAddress }"  data-options="multiline:true,disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="orgDesc">机构备注:</label>
				<input class="easyui-textbox trimBlank"  type="text" style="width:70%;height:62px;padding:10px" name="orgDesc" value="${systemOrgDTO.orgDesc }"  data-options="multiline:true,validType: 'lengthText[4000]',disabled:true">
			</div>
			<div style="margin-top:15px;">&nbsp;</div>
			<div style="margin-top:15px;">&nbsp;</div>
			<div style="margin-top:15px;">&nbsp;</div>
		   </form>
		   </div>
    </div>
    </div>
    <iframe id="other" name="other" src=""  style="padding:0px;margin:0px;display:none;"></iframe>  
</body>  
</html>