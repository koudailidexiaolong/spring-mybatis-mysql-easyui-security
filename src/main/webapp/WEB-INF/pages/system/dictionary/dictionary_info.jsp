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
  		var index = "${systemDictionaryDTO.dictionaryStatus }";
  		$(document).ready(function(){
  			//加载下拉框
			loadSelectData("util_status",$("#dictionaryStatus"),index);
		});
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'数据字典信息展示',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>&nbsp;&nbsp;  
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统数据字典信息管理'" border="true"  noheader="true"  fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		  <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				 <label for="dictionaryName">数据字典名称:</label>
   				 <input type="hidden"   id="dictionaryId" name="dictionaryId" value="${systemDictionaryDTO.dictionaryId }"/>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="dictionaryName" value="${systemDictionaryDTO.dictionaryName }" data-options="disabled:true">
			</div>
		   <div style="margin-top:15px;">
   				 <label for="dictionaryCode">数据字典编码:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="dictionaryCode" value="${systemDictionaryDTO.dictionaryCode }" data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryType">数据字典类型:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryType" value="${systemDictionaryDTO.dictionaryType }"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryOrder">数据字典顺序:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryOrder" value="${systemDictionaryDTO.dictionaryOrder }"  data-options="disabled:true">
			</div>
   			<div style="margin-top:15px">
				<label for="dictionaryStatus">数据字典状态:</label>
				<select id="dictionaryStatus" class="easyui-combobox"  name="dictionaryStatus" style="width:70%;padding:10px;height:32px" data-options="disabled:true">  
				    <option value="">请选择</option>  
				</select>  
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryCreateUserId">创建人:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" id="dictionaryCreateUserId" name="dictionaryCreateUserId" value="${systemDictionaryDTO.dictionaryCreateUserName }"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryCreateTime">创建时间:</label>
				<input class="easyui-datetimebox"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryCreateTime" value="${systemDictionaryDTO.dictionaryCreateTime }"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryUpdateUserId">修改人:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px"  id="dictionaryUpdateUserId" name="dictionaryUpdateUserId" value="${systemDictionaryDTO.dictionaryUpdateUserName }"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryUpdateTime">修改时间:</label>
				<input class="easyui-datetimebox"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryUpdateTime" value="${systemDictionaryDTO.dictionaryUpdateTime }"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryOrder">描述:</label>
				<input class="easyui-textbox trimBlank"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryDesc" value="${systemDictionaryDTO.dictionaryDesc }"  data-options="disabled:true">
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