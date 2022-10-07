<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
    <title>${WEBNAME}</title>
   	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<!-- easyui #FF8E1A-->
	<link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/default/easyui.css" />
    <script type="text/javascript"  src="./plugin/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
  </head>
  <body class="easyui-layout" style="text-align: center;" onkeydown="onkeydowns(event)" >  
    <div data-options="region:'center'" style="padding-top:13%;background-image: url('./commons/images/login-bg.jpg'); background-repeat:no-repeat;background-color:#f8f2ec; background-position:center;width: 100%;height: 100%;background-size: cover;" align="center">
    	<div id="cc" class="easyui-layout"  style="width:450px;height:290px;background-color: #fff;position: relative;">  
    			<div data-options="region:'center',title:'登录',iconCls:'icon-user-list'" noheader="true" border="false" style="padding:0px;text-align: center;background-image: url('./commons/images/login-bg.jpg');">
    				<c:url value="j_spring_security_check" var="loginUrl"/>
    				<form id="loginForm" name="loginForm" action="${loginUrl }" method="post" >
    					  <div style="background-image: url('./commons/images/logo.png') !important;background-repeat: no-repeat;background-position: center;">  
						       <label style="font-size:32px;">巨龙科技管理系统</label>
						  </div>
						  <div style="height:20px;"></div>
    					  <div  style="margin-bottom:20px">  
						        <label for="userId" ><span style="width:70px;">登录名:</span></label>  
						        <input class="easyui-textbox" type="text" name="userId" id="userId"  value="" data-options="iconCls:'icon-man',iconWidth:38,iconAlign:'left',prompt:'请输入登录账号'"  style="width:70%;height:32px"/>  
						  </div>
						  <div style="margin-bottom:20px">  
						        <label for="userPassword" ><span style="width:70px;">密&nbsp;&nbsp;&nbsp;码:</span></label>  
						        <input class="easyui-textbox" type="password" id="userPassword" name="userPassword" value="" data-options="iconCls:'icon-lock',iconWidth:38,iconAlign:'left',prompt:'请输入密码'"  style="width:70%;height:32px"/>  
						  </div>
						  <div style="margin-bottom:20px;">  
						        <label for="captcha" ><span style="width:70px;">验证码:</span></label>  
						        <input class="easyui-textbox" type="text" id="captcha" name="captcha" value="" data-options="iconCls:'icon-lock',iconWidth:38,iconAlign:'left',prompt:'请输入验证码'"  style="width:46%;height:32px"/>  
							    <div class="textbox easyui-fluid" style="padding:0px;margin:0px;width:100px;height:32px;"><img src="login/captcha.html" id="captchaImg" onclick="code()" style="width:100%;height:31px;padding:0px;margin:0px;"/></div>  
						  </div>
						  <div> 
						  		<label for="">&nbsp;&nbsp;&nbsp;</label>    
						        <a id="btn" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width: 80px;height:32px;" onclick="$('#loginForm').form('clear')">重&nbsp;置</a>&nbsp;&nbsp;  
								<a id="btn" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-login'" onclick="login()" style="width: 80px;height:32px;">登&nbsp;录</a>  
						  </div>
						  <div>&nbsp;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						  	<c:if test="${not empty error}">
								<div class="error">${error}</div>
							</c:if>
						  </div>
    				</form>
    			</div>  
			</div> 
    	</div>
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
    <script type="text/javascript" src="./plugin/base64/base64.js"></script>
    <script type="text/javascript" src="./plugin/jsencrypt/jsencrypt.min.js"></script>
	<script type="text/javascript">
		var encrypt = new JSEncrypt();
		var keys = "";
		//初始化
		$(document).ready(function(){
			 if (window != top) {  
			        top.location.href = location.href;  
			    }  
			$("#loginForm").form("clear");
			loadKey();
		});
		//登录
		function login(){
			var param = getSystemParam();
			if("" == $("#userId").val() || null == $("#userId").val() ){
				alert("请输入登录账号！");
				return;
			}
			if("" == $("#userPassword").val() || null == $("#userPassword").val() ){
				alert("请输入登录密码！");
				return;
			}
			if("" == $("#captcha").val() || null == $("#captcha").val() ){
				alert("请输入验证码！");
				return;
			}
			encrypt.setPublicKey(keys);
			$("#userPassword").textbox("setValue",encrypt.encrypt($("#userPassword").val()));
			//$("#loginForm").submit();
			//$.post("login/login.json?param="+param, $("#loginForm").serialize(),function(data){
			
			$.post("<c:url value='j_spring_security_check' />?param="+param, $("#loginForm").serialize(),function(data){
				console.log(data);
				if(data.result == true){
					loginForm.action="login/index.html?param="+param;
					loginForm.submit();
				}else if(data == undefined || data == null){
					alert("登录系统发生异常！");
					$("#loginForm").form("reset");
					code();
					return;
				}else{
					if(data.message == undefined || data.message == null){
						alert("登录系统发生异常！");
						$("#loginForm").form("reset");
					}else{
						alert(data.message);
						$("#loginForm").form("reset");
					}
					code();
					return;
				}
			});
			
		}
		//键盘按下
		function onkeydowns(e){
			var keynum = "";
			if(window.event){ // IE
			  keynum = e.keyCode;
			}else if(e.which){// Netscape/Firefox/Opera
			  keynum = e.which;
			}
			if(13 == keynum){
				login();
			}
		}
		/*图片校验码刷新*/
		function code(){
			var param = getSystemParam();
			document.getElementById("captchaImg").src="login/captcha.html?param="+param;
		}
		/*获取key*/
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
</body>  
</html>
