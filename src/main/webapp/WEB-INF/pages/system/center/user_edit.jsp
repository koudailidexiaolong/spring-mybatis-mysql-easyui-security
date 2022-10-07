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
	    //用户性别
		var index = "${systemUserDTO.userSex}";
		//用户状态
		var status = "${systemUserDTO.userStatus}";
	 	var userSkin = "${systemUserDTO.userSkin}";
  		$(document).ready(function(){
  			// 在从远程服务器加载数据之前改变请求参数
			$("#themesSelect").combobox({
				onSelect: function(param){
					$("#themes").css("background-color",param.dictionaryCode);
				}
			});
  			
			//加载下拉框
			loadSelectData("user_sex",$("#userSex"),index);
			//加载下拉框
			loadSelectData("user_status",$("#userStatus"),status);
			
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
		/*保存用户信息的方法*/
		function update(){
			var param = getSystemParam();
			var url = "user/updateUser.json?param="+param;
			$("#userSkin").val($("#themesSelect").combobox("getText"));
			if($("#paramForm").form("validate") ==false){
				return;
			}
			//提交表单
			$.post(url, $("#paramForm").serialize(),function(data){
			    if(data.result==1){
			    	parent.info("个人中心","修改个人信息成功！");
					parent.exitWin();
				}else{
					info("个人中心","修改个人信息失败！");
					return;
				}
			}, "json");
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'修改用户',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="update()">保&nbsp;存</a>
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exitWin();">退&nbsp;出</a>&nbsp;&nbsp;  
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统用户信息管理'" border="true"  noheader="true"   fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		 <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
	   				<label for="userId">登录账号:</label>
					<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" value="${systemUserDTO.userId}" data-options="editable:false,disabled:true,prompt:'请输入登录账号'">
					<input type="hidden" id="userId" name="userId" value="${systemUserDTO.userId}" />
				</div>
				<div style="margin-top:15px;">
					<label for="userName">用户姓名:</label>
					<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="userName" value="${systemUserDTO.userName}"  data-options="prompt:'请输入用户姓名',required:true,validType: ['CN_EN_NO[]','length[2,10]']">
				</div>
	   			<div style="margin-top:15px;">
	   				 <label for="userIdentity">身份证号:</label>
					<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userIdentity" value="${systemUserDTO.userIdentity}" data-options="prompt:'请输入身份证号',required:true,validType:'IDCARD[]'">
				</div>
				<div style="margin-top:15px">
					<label for="userPhone">手机号码:</label>
					<input class="easyui-numberbox" type="text" style="width:70%;height:32px;padding:10px" name="userPhone" value="${systemUserDTO.userPhone}" data-options="prompt:'请输入手机号码',validType:'phone[]'">
				</div>
	   			<div style="margin-top:15px">
	   				<label for="userSex">性别:</label>
					<select id="userSex" class="easyui-combobox"  name="userSex" style="width:70%;padding:10px;height:32px"  data-options="panelHeight:'auto',editable:false,validType:'OPTION[]'">  
					    <option value="">请选择</option> 
					</select>  
				</div>
				<div style="margin-top:15px">
					<label for="userAge">年龄:</label>
					<input class="easyui-numberbox" type="text" style="width:70%;height:32px;padding:10px" name="userAge" value="${systemUserDTO.userAge}" data-options="precision:0,validType:'length[1,3]',prompt:'请输入年龄'">
				</div>
	   			<div style="margin-top:15px">
					<label for="userStatus">用户状态:</label>
					<select id="userStatus" class="easyui-combobox"  name="userStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]'">  
					    <option value="">请选择</option> 
					</select>  
				</div>
	   			<div style="margin-top:15px">
					<label for="userMail">邮箱地址:</label>
					<input class="easyui-textbox trimBlank" type="text" style="width:70%;height:32px;padding:10px" name="userMail" value="${systemUserDTO.userMail}" data-options="prompt:'请输入邮箱地址',validType:'email[]'">
				</div>
	   			<div style="margin-top:15px">
					<label for="userSkin">
						<span id="themes" style="border:1px solid white; width:32px; heiht:32px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					皮肤:</label>
					<select id="themesSelect" class="easyui-combobox"   data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]'"  name="themes" style="width:70%;padding:10px;height:32px" >  
					</select>  
					<input type="hidden" value="default" name="userSkin" value="${systemUserDTO.userSkin}" id="userSkin" />
				</div>
				<div style="margin-top:15px;">
					<label for="userAddress">家庭住址:</label>
					<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" id="userAddress" name="userAddress" value="${systemUserDTO.userAddress}" data-options="prompt:'请输入家庭住址',validType: ['maxLength[500]','CN_EN_NO[]']">
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
