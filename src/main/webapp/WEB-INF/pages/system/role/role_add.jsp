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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	    var isFind = false;//??????????????????
		$(document).ready(function(){
  			//???????????????
			loadSelectData("util_status",$("#roleStatus"),"0");
				//????????????
		 	$("#roleName").textbox("textbox").bind("blur", function(){
				//????????????
				var param = getSystemParam();
				var url = "role/validateRole.json?param="+param;
				/*???????????????*/
				$.post(url,{roleName:$("#roleName").val()},function(data){
				    if(data.result == 1){
				    	info("????????????","??????????????????");
				    	$("#roleName").textbox("setValue","");
				    	isFind = true;
					}else if(data.result == -1){
				    	info("????????????","???????????????????????????");
				    	isFind = true;
					}else{
					    isFind = false;
					}
				   }, "json");
		 	});
		});
		/*???????????????????????????*/
		function save(){
		    if(isFind){
				info("????????????","??????????????????");
				return;
		    }
			var param = getSystemParam();
			var url = "role/saveRole.json?param="+param;
			if($("#paramForm").form("validate") == false){
				return;
			}
			//????????????
			$.post(url, $("#paramForm").serialize(),function(data){
			    if(data.result==1){
					parent.info("????????????","???????????????????????????");
					parent.exit();
					parent.searchRole();
				}else{
					info("????????????","???????????????????????????");
					return;
				}
			   }, "json");
		}
		
		function clearForm(){
			$("#paramForm").form("clear");
		}
		
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'????????????',iconCls:'icon-user-add'" >
   		 <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="clearForm()">???&nbsp;???</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">???&nbsp;???</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">???&nbsp;???</a>&nbsp;&nbsp;  
   		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'????????????????????????'" border="true"  noheader="true"   fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		   <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				 <label for="roleName">????????????:</label>
				<input class="easyui-textbox" id="roleName" type="text" style="width:70%;height:32px;padding:10px" name="roleName" value="" data-options="prompt:'?????????????????????',required:true,validType: ['CN_EN_NO[]','maxLenght[50]']">
			</div>
   			<div style="margin-top:15px">
				<label for="roleStatus">????????????:</label>
				<select id="roleStatus" class="easyui-combobox"  name="roleStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]'"  >  
				    <option value="">?????????</option>  
				</select>  
			</div>
   			<div style="margin-top:15px">
				<label for="roleDesc">????????????:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="roleDesc" value="" data-options="prompt:'?????????????????????',validType: ['CN_EN_NO[]','maxLenght[500]']">
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
