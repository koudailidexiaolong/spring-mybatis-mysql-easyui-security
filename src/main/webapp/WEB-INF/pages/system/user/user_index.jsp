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
	<link rel="stylesheet" type="text/css" href="./commons/css/index_select.css"/>
    <script type="text/javascript" src="./plugin/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.validate.js"></script>
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
	<script type="text/javascript">
	    //页面对象
		var userObject = new Object();
		var rowIndex = -1;//标示当前的选择的行
		var util_status = null;//用户状态
		var user_sex = null;//用户性别
		//初始化加载
		$(document).ready(function(){
			initAuthorize();
			//禁用按钮
			$("#delete-user").linkbutton("disable");
			$("#edit-user").linkbutton("disable");
			$("#get-user").linkbutton("disable");
			$("#reload-password").linkbutton("disable");
			$("#edit-user-status").linkbutton("disable");
			//加载下拉框
			loadSelectData("util_status",$("#userStatus"),"");
			//加载下拉框
			loadSelectData("user_sex",$("#userSex"),"");
			initUserStatus();
			initUserSex();
			searchUser();
		    $("#beginDate").datebox({
		    	onSelect: function(date){
		    		var beginDate = $("#beginDate").datebox("getValue");
		    		var endDate = $("#endDate").datebox("getValue");
		    		if(null != endDate && endDate != "" && endDate != undefined){
		    			if(beginDate > endDate){
		    				$("#endDate").datebox("setValue",beginDate);
		    			}
		    		}
		    	}
		    });
		    $("#endDate").datebox({
		    	onSelect: function(date){
		    		var beginDate = $("#beginDate").datebox("getValue");
		    		var endDate = $("#endDate").datebox("getValue");
		    		if(null != beginDate && beginDate != "" && beginDate != undefined){
		    			if(beginDate > endDate){
		    				$("#endDate").datebox("setValue",beginDate);
		    			}
		    		}
		    	}
		    });
			
		});
		/*初始化加载用户状态*/
		function initUserStatus(){
			$.ajax({
				   type: "POST",
				   url: "dictionary/getCode.json",
				   async: false,
				   data: { dictionaryType: "util_status" },
				   success: function(data){
					   util_status = data;
				   }
				});
		}
		/*初始化加载用户性别*/
		function initUserSex(){
			$.ajax({
				   type: "POST",
				   url: "dictionary/getCode.json",
				   async: false,
				   data: { dictionaryType: "user_sex" },
				   success: function(data){
					   user_sex = data;
				   }
				});
		}
		 /* 启用、禁用客服状态 */
		function editStatus() {
			var message = null;
			$("#editUserId").val(userObject.userId);
			if (userObject.userStatus == 0) {
				$("#editUserStatus").val(1);
				message = "您确定要禁用该用户？";
			} else if (userObject.userStatus == 1) {
				$("#editUserStatus").val(0);
				message = "您确定要启用该用户？";
			}
			$.messager.confirm("用户模块", message, function (r) {
				if (r) {
					var param = getSystemParam();
					var url = "user/updateUser.json?param=" + param;
					//提交表单
					$.post(url, $("#indexForm").serialize(), function (data) {
						if (data.result == 1) {
							if ($("#editUserStatus").val() == 0) {
								info("用户管理", "启用成功！");
							} else if ($("#editUserStatus").val() == 1) {
								info("用户管理", "禁用成功！");
							}
							searchUser();
						} else {
							info("用户管理", "修改用户信息失败！");
							return;
						}
					}, "json");
		
				}
			});
		}
				
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;">  
<div data-options="region:'north',title:'系统用户信息管理',iconCls:'icon-user-list'" border="false"  noheader="true" style="height:auto;padding:0px;">
	<!-- 查询条件  -->
	<div class="form_div"  id="form_div" style="width:100%;height:auto;padding:0px; ">
       <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:10px;">
   				<label for="userId">登录账号:</label>
				<input class="easyui-textbox trimBlank" type="text" style="width:70%;height:32px;padding:10px" name="userId" id="userId" value="" data-options="prompt:'请输入登录账号',validType:'loginAccount[]'">
			</div>
			<div style="margin-top:10px;">
				<label for="userName">用户姓名:</label>
				<input class="easyui-textbox trimBlank"  type="text" style="width:70%;height:32px;padding:10px" name="userName" id="userName" value=""  data-options="prompt:'请输入用户姓名',validType:'CN_EN_NO[]'">
			</div>
   			<div style="margin-top:10px">
				<label for="userStatus">用户状态:</label>
				<select id="userStatus" class="easyui-combobox"  name="userStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">请选择</option>  
				</select>  
			</div>
   			<div style="margin-top:10px">
				<label for="userSex">用户性别:</label>
				<select id="userSex" class="easyui-combobox"  name="userSex" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">请选择</option>  
				</select>  
			</div>
			<div style="margin-top:10px;">
				<label for="userName">开始日期:</label>
				<input class="easyui-datebox"  type="text" style="width:70%;height:32px;padding:10px" name="beginDate" id="beginDate" value=""  data-options="editable:false,prompt:'请输入开始日期'">
			</div>
			<div style="margin-top:10px;">
				<label for="userName">结束日期:</label>
				<input class="easyui-datebox"  type="text" style="width:70%;height:32px;padding:10px" name="endDate" id="endDate" value=""  data-options="editable:false,prompt:'请输入结束日期'">
			</div>
	  </form>
    </div>
    <!-- 按钮 -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-search'" id="search-user" onclick="searchUser()">查询用户</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="display:none;" id="reload-password" onclick="reloadPassword()">重置密码</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;"  id="edit-user-status" onclick="editStatus()">启用/禁用</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-info'" style="display:none;"  id="get-user" onclick="getUser()">查看详情</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-add'" style="display:none;"  id="add-user" onclick="addUser()">添加用户</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;"  id="edit-user" onclick="editUser()">修改用户</a> 
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-remove'" style="display:none;"  id="delete-user" onclick="deleteUser()">删除用户</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;&nbsp;出</a>&nbsp;&nbsp;&nbsp;  
   	</div>
</div>  
<!-- 数据列表 展示 -->
<div data-options="region:'center',iconCls:'icon-user-list',title:'系统用户信息展示列表'"  style="width:100%;height:100%;padding:0px;margin:0px; ">
   <table id="tableData"></table>
<!-- 模态界面 -->
<div id="win" class="easyui-window" title="Basic Window"  data-options="modal:true,closed:true,iconCls:'icon-user-list',minimizable:false,collapsible:false" style="width:70%;height:70%;padding:10px;overflow:hidden;">
	<iframe id="paramIframe" name="paramIframe" style="width:100%; height:100%;" frameborder="0" scrolling="no"  style="padding:0px;margin:0px;"></iframe>
</div>
<!-- 数据提交 -->
<form id="indexForm" name="indexForm" action="" method="post" target="other" style="dispaly:none;padding:0px;margin:0px;">
	<input id="menuId" type="hidden" value="" name="menuURL" />
	<input id="editUserId" type="hidden" value="" name="userId" />
	<input id="editUserStatus" type="hidden" value="" name="userStatus" />
</form> 
</div> 
<script type="text/javascript">


/*查询用户信息的方法*/
function searchUser(){
	
	rowIndex = -1;
	//禁用按钮
	$("#delete-user").linkbutton("disable");
	$("#edit-user").linkbutton("disable");
	$("#get-user").linkbutton("disable");
	$("#reload-password").linkbutton("disable");
	$("#edit-user-status").linkbutton("disable");
	var userId = $("#userId").val();//登录名
	var userName = $("#userName").val();//姓名
	var userStatus = $("#userStatus").combobox("getValue");//电话
	var userSex = $("#userSex").combobox("getValue");
	var beginDate = $("#beginDate").datebox("getValue");
	var endDate = $("#endDate").datebox("getValue");
	var param = getSystemParam();
	$("#tableData").datagrid({   
	    url:"user/getUserListByPage.json?param="+param, 
	    method:"post",
	    striped:true,//各行换色  
	    autoRowHeight:false,
	    singleSelect:true,
	    rownumbers:true,
	    fitColumns:true,
		fit:true,
	    columns:[[   
	        {field:"id",checkbox:true},
			{field:"userId",title:"登录名",align:"center",width:100},
			{field:"userName",title:"姓名",align:"center",width:100},
			{field:"userSex",title:"性别",align:"center",width:100,formatter: function(value,row,index){
				if(null != user_sex){
					for(var i=0;i<user_sex.length;i++){
						if( value == user_sex[i].dictionaryCode){
							return user_sex[i].dictionaryName;
						}
					}
				}else{
					return value;
				}
			}},
			{field:"userAge",title:"年龄",align:"center",width:100},
			{field:"userPhone",title:"电话",align:"center",width:100},
			{field:"userSkin",title:"主题",align:"center",width:100},
			{field:"userStatus",title:"用户状态",align:"center",width:100,formatter: function(value,row,index){
				if(null != util_status){
					for(var i=0;i<util_status.length;i++){
						if(value == util_status[i].dictionaryCode){
							return util_status[i].dictionaryName;
						}
					}
				}else{
					return value;
				}
			}},
			{field:"userCreateTime",title:"创建时间",align:"center",width:100},
			{field:"userCreateUserName",title:"创建人",align:"center",width:100}
	    ]],
	    //查询条件
	    queryParams: {
	    	"userId":userId,"userName":userName,"userStatus":userStatus,"userSex":userSex,"beginDate":beginDate,"endDate":endDate
		},
		//分页参数		    
	    pagination:true,
	    pagePosition:"bottom",
	    pageNumber:1,
	    pageList:[10,20,30,40],
	    onLoadError:function(none){
			//禁用按钮
			$("#delete-user").linkbutton("disable");
			$("#edit-user").linkbutton("disable");
			$("#get-user").linkbutton("disable");
			$("#reload-password").linkbutton("disable");
			$("#edit-user-status").linkbutton("disable");
			userObject = null;
			error("用户模块","查询用户信息失败！");
		},
		onLoadSuccess:function(data){
			if(data.total == 0){
				info("用户模块","当前没有查询到信息！");
			}
		},//单击事件
	    onSelect:function(rowIndex, rowData){
			rowIndex = rowIndex;
			userObject = rowData;
			//禁用按钮
			$("#delete-user").linkbutton("enable");
			$("#edit-user").linkbutton("enable");
			$("#get-user").linkbutton("enable");
			$("#reload-password").linkbutton("enable");
			$("#edit-user-status").linkbutton("enable");
		},//双击事件
		onDblClickRow:function(rowIndex, rowData){
			rowIndex = rowIndex;
			userObject = rowData;
			//禁用按钮
			$("#delete-user").linkbutton("enable");
			$("#edit-user").linkbutton("enable");
			$("#get-user").linkbutton("enable");
			$("#reload-password").linkbutton("enable");
			$("#edit-user-status").linkbutton("enable");
			getUser();
		}
	}); 
}
/*删除用户信息*/
function deleteUser(){
	$.messager.confirm("用户模块","您是否确认删除此条数据吗？", function(r){
		if(r){
			var param = getSystemParam();
			var url = "user/deleteUser.json?param="+param;
			$.post(url, { "userId": userObject.userId },
					   function(data){
				   		if(data.result == 1){
				   			info("用户模块","删除用户【"+userObject.userName+"】成功！");
				   			self.searchUser();
				   			//禁用按钮
							$("#delete-user").linkbutton("disable");
							$("#edit-user").linkbutton("disable");
					   	}else{
					   		info("用户模块","删除用户【"+userObject.userName+"】失败！");
						}
			   }, "json");
		}
	});
}
/*重置密码*/
function reloadPassword(){
	$.messager.confirm("用户模块","您确定要重置密码吗？", function(r){
	      if(r){
			//提交表单
			var param = getSystemParam();
			var url = "user/resetPassword.json?param="+param;
			$.post(url, {userId:userObject.userId},function(data){
			    if(data.result==1){
			    	info("用户管理","重置密码成功！");
					self.searchUser();
				}else{
					info("用户管理","重置密码失败！");
					return;
				}
		   }, "json");
	    }
	});
}

</script>
<script type="text/javascript">
	/*打开添加用户页面*/
	function addUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"用户管理"});
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadAddUser.html?param="+param;
   		indexForm.submit();
	}
	/*打开修改用户页面*/
	function editUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"用户管理"});
	   	var param = getSystemParam();
	   	$("#editUserId").val(userObject.userId);
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadEditUser.html?param="+param;
   		indexForm.submit();
	}
	/*打开用户详情页面*/
	function getUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"用户管理"});
	   	var param = getSystemParam();
	   	$("#editUserId").val(userObject.userId);
       	indexForm.target= "paramIframe";
        indexForm.action = "user/getUser.html?param="+param;
   		indexForm.submit();
	}
	function exit(){
		$("#win").dialog("close");
	}
	/*初始化按钮权限*/
	function initAuthorize(){
		$.ajax({
			   type: "POST",
			   url: "authorize/loadAuthorizeButton.json",
			   async: true,
			   data: {},
			   success: function(data){
				   if(null != data && "" != data && undefined != data){
					   var buttons = data.buttonList;
					   if(null != buttons && "" != buttons){
						   for(var i=0;i<buttons.length;i++){
							   $("#"+buttons[i].buttonCode).show();
						   }
					   }
				   }
			   }
			});
	}
</script>
</body>  
</html>