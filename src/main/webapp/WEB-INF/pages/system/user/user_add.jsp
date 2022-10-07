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
		var isFind = false;//判断是否存在
		$(document).ready(function(){
		 	// 在从远程服务器加载数据之前改变请求参数
			
			//加载下拉框
			loadSelectData("util_status",$("#userStatus"),"0");
			loadSelectData("user_sex",$("#userSex"),"");
			//主题
			loadSelectData("user_themes",$("#themesSelect"),"");
			
			$("#themesSelect").combobox({
				onSelect: function(param){
					$("#themes").css("background-color",param.dictionaryCode);
				}
			});
			
		 	//绑定事件
		 	$("#userId").textbox("textbox").bind("blur", function(){
				//提交表单
				var param = getSystemParam();
				var url = "user/validateUser.json?param="+param;
				/*校验用户名*/
				$.post(url,{userId:$("#userId").val()},function(data){
					    if(data.result == 1){
					    	info("用户管理","登录账号已存在！");
					    	$("#userId").textbox("setValue","");
					    	isFind = true;
						}else if(data.result == -1){
							info("用户管理","校验用户信息发生异常！");
					    	isFind = true;
						}else{
							isFind = false;
						}
				   }, "json");
		 	});
		});
		/*保存用户信息的方法*/
		function save(){
			if(isFind){
				info("用户管理","登录账号已存在！");
				return;
			}
			var param = getSystemParam();
			var url = "user/saveUser.json?param="+param;
			$("#userSkin").val($("#themesSelect").combobox("getText"));
			if($("#paramForm").form("validate") ==false){
				return;
			}
			//提交表单
			$.post(url, $("#paramForm").serialize(),function(data){
			    if(data.result==1){
			    	parent.info("用户管理","新增用户信息成功！");
					parent.exit();
					parent.searchUser();
				}else{
					info("用户管理","新增用户信息失败！");
					return;
				}
			   }, "json");
		}
		
		function clearForm(){
			$("#paramForm").form("reset");
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'添加用户',iconCls:'icon-user-add'" >
   		 <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="clearForm()">重&nbsp;置</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保&nbsp;存</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>&nbsp;&nbsp;  
   		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统用户信息管理'" border="true"  noheader="true" fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		 <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				<label for="userId">登录账号:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" id="userId" name="userId" value=""  data-options="prompt:'请输入登录账号',required:true,validType:'loginAccount[]'">
			</div>
			<div style="margin-top:15px;">
				<label for="userName">用户姓名:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="userName" value=""  data-options="prompt:'请输入用户姓名',required:true,validType:['CN_EN_NO[]','length[2,10]']">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="userIdentity">身份证号:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userIdentity" value="" data-options="prompt:'请输入身份证号',required:true,validType:'IDCARD[]'">
			</div>
			<div style="margin-top:15px">
				<label for="userPhone">手机号码:</label>
				<input class="easyui-textbox validatebox" type="text" style="width:70%;height:32px;padding:10px" name="userPhone" value="" data-options="prompt:'请输入用户手机号码',required:true,validType:'phone[]'">
			</div>
   			<div style="margin-top:15px">
   				<label for="userSex">性别:</label>
				<select id="userSex" class="easyui-combobox"  name="userSex" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,validType:'OPTION[]'" >  
				    <option value="">请选择</option>
				</select>  
			</div>
			<div style="margin-top:15px">
				<label for="userAge">年龄:</label>
				<input class="easyui-numberbox" type="text" style="width:70%;height:32px;padding:10px" name="userAge" value="" data-options="precision:0,validType:'age[]',prompt:'请输入年龄'">
			</div>
   			<div style="margin-top:15px">
				<label for="userStatus">用户状态:</label>
				<select id="userStatus" class="easyui-combobox"  name="userStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]',">  
				    <option value="">请选择</option> 
				</select>
			</div>
   			<div style="margin-top:15px">
				<label for="userMail">邮箱地址:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userMail" value="" data-options="prompt:'请输入邮箱地址',validType:'email[]'">
			</div>
   			<div style="margin-top:15px">
				<label for="userSkin">
					<span id="themes" style="border:1px solid white; width:32px; heiht:32px;background-color: #E0ECFF;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				皮肤:</label>
				<select id="themesSelect" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,editable:false,validType:'OPTION[]'" name="themes" style="width:70%;padding:10px;height:32px">  
				    <option value="">请选择</option>  
				</select>  
				<input type="hidden" value="default" name="userSkin" id="userSkin" />
			</div>
			<div style="margin-top:15px;">
				<label for="userAddress">家庭住址:</label>
				<input class="easyui-textbox" type="text" id="userAddress" name="userAddress" style="width:70%;height:32px;padding:10px" data-options="prompt:'请输入家庭住址',validType: ['maxLength[500]','CN_EN_NO[]']">
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
