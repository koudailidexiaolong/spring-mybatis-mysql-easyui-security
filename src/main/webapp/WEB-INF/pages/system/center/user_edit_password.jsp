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
	<script type="text/javascript" src="./plugin/jsencrypt/jsencrypt.min.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
	<style type="text/css">
		/*表单自适应样式
		48% 代表只显示 两行
		350px 是以这个为定长
		*/
		#paramForm div{
			text-align:right;
			width:79%;
			float: left;
		}
	</style>
	<script type="text/javascript">
		var encrypt = new JSEncrypt();
		var keys = "";
		$(document).ready(function() {
			$("#password").textbox("setValue","");
			loadKey();
		});
		function save(){
			validateOldPassword();
		}
		/*校验用户的原始密码*/
		function validateOldPassword(){
			if($("#password").val() == "" ){
				info("输入提示","请输入用户密码！");
				return;
			}
			if($("#userPassword").val() == "" ){
				info("输入提示","请输入新用户密码！");
				return;
			}
			if($("#rePassword").val() == "" ){
				info("输入提示","请再次输入新密码！");
				return;
			}
			if($("#userPassword").val() != $("#rePassword").val()){
				info("修改密码","当前两次输入的新密码不一致！");
				clearForm();
				return;
			}
			if ($("#userPassword").val()==$("#password").val()) {
				info("修改密码","修改的密码和原密码不能一样！");
				clearForm();
				return;
			}
			encrypt.setPublicKey(keys);
			var param = getSystemParam();
			$.post("user/validatePassword.action?param="+param,
					{"userPassword": encrypt.encrypt($("#password").val())},function(data){
						if(null != data.result){
							if(data.result == "-1"){
								error("错误","当前输入的历史密码有误！");
							    clearForm();
								return ;
							}else if(data.result == "1"){
								$("#userPassword").textbox("setValue",encrypt.encrypt($("#userPassword").val()))
								$.post("user/updatePassword.json?param="+param, $("#paramForm").serialize(),
								   function(data){
									if(data.result=="1"){
										parent.info("用户管理","修改密码成功！");
										parent.exitWin();
									}else{
										info("用户管理","修改用户密码失败！");
										clearForm();
									
									}
								   }, "json");
							}
						}
				   }, "json");
		}
		function clearForm(){
			$("#paramForm").form("clear");
		}
		function loadKey(){
			$.ajax({
				  type: "POST",
				  url: "login/getKey.json",
				  cache: false,
				  dataType: "json",
				  success: function(data){
				     keys = data.result;
				  }
		   });
		}
	</script>
  </head>
<body class="easyui-layout"  >  
    <div data-options="region:'center',title:'修改密码',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保&nbsp;存</a>
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exitWin();">退&nbsp;出</a>&nbsp;&nbsp;  
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'修改个人密码'" border="true"  noheader="true" fit="true" style="width:100%;height:90%;padding:0px;text-align:center;">
   		 	<form id="paramForm" method="post" name="paramForm" action="">  
	   			<div style="margin-top:15px;">
	   				<label for="password">原始密码:</label>
					<input class="easyui-textbox" type="password" style="width:70%;height:32px;padding:10px" id="password" name="password" value=""  data-options="prompt:'',required:true,validType:'password[]'">
				</div>
	   			<div style="margin-top:15px;">
	   				<label for="userPassword">新密码:</label>
					<input class="easyui-textbox" type="password" style="width:70%;height:32px;padding:10px" id="userPassword" name="userPassword" value=""  data-options="prompt:'',required:true,validType:'password[]'">
				</div>
	   			<div style="margin-top:15px;">
	   				<label for="rePassword">确认密码:</label>
					<input class="easyui-textbox" type="password" style="width:70%;height:32px;padding:10px"  id="rePassword" name="rePassword" value=""  data-options="prompt:'',required:true,validType:'password[]'">
				</div>
			</form>
    	</div>
    </div>
    <iframe id="other" name="other" src=""  style="padding:0px;margin:0px;display:none;"></iframe>  
</body>  
</html>
