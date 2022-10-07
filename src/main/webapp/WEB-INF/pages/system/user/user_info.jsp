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
		var index = "${systemUserDTO.userStatus}";
		var userSex = "${systemUserDTO.userSex}";
		var userSkin = "${systemUserDTO.userSkin}";
  		$(document).ready(function(){
  			// 在从远程服务器加载数据之前改变请求参数
			$("#themesSelect").combobox({
				onSelect: function(param){
					$("#themes").css("background-color",param.value);
				}
			});
			//加载下拉框
			loadSelectData("util_status",$("#userStatus"),index);
			//加载下拉框
			loadSelectData("user_sex",$("#userSex"),userSex);
			loadSelectDataTemp("user_themes",$("#themesSelect"),userSkin);
		});
  		/*加载选中主题*/
  		function loadSelectDataTemp(type,select,index){
  			$.post("dictionary/getCode.json", { dictionaryType: type },
  					   function(data){
  						 var obj = new Object();
  						 obj.dictionaryName = "请选择";
  						 obj.dictionaryCode = "";
  					     data.unshift(obj);
  					     $(select).combobox({
  						        data:data,
  						        valueField:"dictionaryCode",
  						        textField:"dictionaryName",
  						        panelHeight:200,
  						        onLoadSuccess:function(){
  						        	for(var i = 0;i<data.length;i++ ){
  						        		if(data[i].dictionaryName == index){
  						        			//默认选中
  		  								    $(this).combobox("select",data[i].dictionaryCode);
  		  									$("#themes").css("background-color",data[i].dictionaryCode);
  						        			break;
  						        		}
  						        	}
  						        	
  						        	
  						        }
  						    });
  				 });
  		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'用户信息展示',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>&nbsp;&nbsp;  
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统用户信息管理'" border="true"  noheader="true" fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		 <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				<label for="userId">登录账号:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userId" value="${systemUserDTO.userId}" data-options="disabled:true">
			</div>
			<div style="margin-top:15px;">
				<label for="userName">用户姓名:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="userName" value="${systemUserDTO.userName}" data-options="disabled:true">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="userIdentity">身份证号:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userIdentity" value="${systemUserDTO.userIdentity}"  data-options="disabled:true">
			</div>
			<div style="margin-top:15px">
				<label for="userPhone">手机号码:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userPhone" value="${systemUserDTO.userPhone}" data-options="disabled:true">
			</div>
   			<div style="margin-top:15px">
   				<label for="userSex">性别:</label>
				<select id="userSex" class="easyui-combobox"  name="userSex" style="width:70%;padding:10px;height:32px"   data-options="disabled:true">  
				    <option value="">请选择</option> 
				</select>  
			</div>
			<div style="margin-top:15px">
				<label for="userAge">年龄:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userAge" value="${systemUserDTO.userAge}" data-options="disabled:true">
			</div>
   			<div style="margin-top:15px">
				<label for="userStatus">用户状态:</label>
				<select id="userStatus" class="easyui-combobox"  name="userStatus" style="width:70%;padding:10px;height:32px"  data-options="disabled:true">  
				    <option value="">请选择</option> 
				</select>  
			</div>
   			<div style="margin-top:15px">
				<label for="userMail">邮箱地址:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userMail" value="${systemUserDTO.userMail}"  data-options="disabled:true">
			</div>
   			<div style="margin-top:15px">
				<label for="userSkin">
					<span id="themes" style="border:1px solid white; width:32px; heiht:32px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				皮肤:</label>
				<select id="themesSelect" class="easyui-combobox"  name="themesSelect" style="width:70%;padding:10px;height:32px" data-options="disabled:true">  
					
				</select>  
				<input type="hidden" value="default" name="userSkin" value="${systemUserDTO.userSkin}" id="userSkin" />
			</div>
			<div style="margin-top:15px;">
				<label for="userAddress">家庭住址:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" id="userAddress" name="userAddress" value="${systemUserDTO.userAddress}"  data-options="disabled:true,editable:false">
			</div>
			<div style="margin-top:15px;">
				<label for="userCreateTime">创建时间:</label>
				<input class="easyui-datetimebox" type="text" style="width:70%;height:32px;padding:10px" id="userCreateTime" name="userCreateTime" value="${systemUserDTO.userCreateTime}"  data-options="disabled:true,editable:false">
			</div>
			<div style="margin-top:15px;">
				<label for="userCreateUserId">创建人:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" value="${systemUserDTO.userCreateUserName}"  data-options="disabled:true,editable:false">
			</div>
			<div style="margin-top:15px;">
				<label for="userUpdateTime">修改时间:</label>
				<input class="easyui-datetimebox" type="text" style="width:70%;height:32px;padding:10px" id="userUpdateTime" name="userUpdateTime" value="${systemUserDTO.userUpdateTime}"  data-options="disabled:true,editable:false">
			</div>
			<div style="margin-top:15px;">
				<label for="userUpdateUserId">修改人:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" value="${systemUserDTO.userUpdateUserName}"  data-options="disabled:true,editable:false">
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