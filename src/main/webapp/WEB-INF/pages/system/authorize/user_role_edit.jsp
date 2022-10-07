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
	<style type="text/css">
		/*表单自适应样式
		48% 代表只显示 两行
		350px 是以这个为定长
		*/
		#paramForm div{
			text-align:right;
			width:48%;
			float: left;
		}
		.model-div{
    		width:200px;
    		margin:10px;
    		/* float:left; */
    		text-align: left;
    	}
    	ul,li{
    	 list-style: none;
    	}
	</style>
    <script type="text/javascript" src="./plugin/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			loadRoleUser();
		});
  		/*加载角色菜单关系*/
  		function loadRoleUser(){
  			var param = getSystemParam();
  			$.post("authorize/getRoleUser.json?param="+param, $("#paramForm").serialize(),
 				   function(data){
	  					var mappedList = data.mappedList;
	  					for(var i = 0;i< mappedList.length;i++){
	  					    $("#"+mappedList[i].roleId).checkbox({checked: true});
	  					}
 				   });
  		}
  		/*保存角色对应菜单关系*/
  		function saveRoleUser(){
  			//一级菜单
  			var firstArray = "";
  			var firstMenuArray = $("input[name=role]");
  			for(i=0;i<firstMenuArray.length;i++){
  				if(firstMenuArray[i].checked){
  					firstArray = firstArray +firstMenuArray[i].value+"-";
  				}
  			}
		   $("#roleArray").val(firstArray);
		   var param = getSystemParam();
		   $.post("authorize/editRoleUser.json?param="+param, $("#paramForm").serialize(),
				   function(data){
			   		if(data.result>=0){
			   			parent.info("角色菜单配置","配置角色菜单权限成功！");
			   		}else{
			   			parent.info("角色菜单配置","配置角色菜单权限失败！");
			   		}
				   });
		   
  		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'角色信息展示',iconCls:'icon-user-add'" >
	 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveRoleUser();">保&nbsp;存</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>&nbsp;&nbsp;  
	  	  </div>
   		  <div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:''" border="true" noheader="true" fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
	   	     <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
		   	     <ul>
		   	     	<c:forEach items="${roleList }" var="role" varStatus="i">
		   	     		<li class="model-div">
							<input class="model-check easyui-checkbox" type="checkbox" name="role" id="${role.roleId }" value="${role.roleId }" /> 
							<label for="role">${role.roleName }</label>  
						</li>
		   	     	</c:forEach>
	             </ul>
		   		 <form id="paramForm" method="post" name="paramForm" action="">  
		   		 	<input id="userId" name="userId" value="${userId }" type="hidden" />
		   		 	<input id="roleArray" name="roleArray" value="" type="hidden" />
				 </form>
			 </div>
    	 </div>
    </div>
    <iframe id="other" name="other" src=""  style="padding:0px;margin:0px;display:none;"></iframe>  
</body>  
</html>