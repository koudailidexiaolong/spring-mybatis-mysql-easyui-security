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
			$("#delete-role-user").linkbutton("disable");
			$("#edit-role-user").linkbutton("disable");
			//加载下拉框
			loadSelectData("util_status",$("#userStatus"),"");
			initUserStatus();
			initUserSex();
			searchUser();
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
		/*查询用户信息的方法*/
		function searchUser(){
			rowIndex = -1;
			//禁用按钮
			$("#delete-role-user").linkbutton("disable");
			$("#edit-role-user").linkbutton("disable");
			var userId = $("#userId").val();//登录名
			var userName = $("#userName").val();//姓名
			var userPhone = $("#userPhone").val();//电话
			var userStatus=$("#userStatus").combobox("getValue");
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
					{field:"userCreateTime",title:"创建时间",align:"center",width:100},
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
					}}
			    ]],
			    //查询条件
			    queryParams: {
			    	"userId":userId,"userName":userName,"userPhone":userPhone,"userStatus":userStatus
				},
				//分页参数		    
			    pagination:true,
			    pagePosition:"bottom",
			    pageNumber:1,
			    pageList:[10,20,30,40],
			    onLoadError:function(none){
					//禁用按钮
					$("#delete-role-user").linkbutton("disable");
					$("#edit-role-user").linkbutton("disable");
					userObject = null;
					//error("用户模块","查询用户信息失败！");
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
					$("#delete-role-user").linkbutton("enable");
					$("#edit-role-user").linkbutton("enable");
				},//双击事件
				onDblClickRow:function(rowIndex, rowData){
					rowIndex = rowIndex;
					userObject = rowData;
					//禁用按钮
					$("#delete-role-user").linkbutton("enable");
					$("#edit-role-user").linkbutton("enable");
					editRoleUser();
				}
			}); 
		}
		/*删除用户角色关系映射信息*/
		function deleteRoleUser(){
			$.messager.confirm("用户授权模块","您是否确认清除此用户的权限吗？", function(r){
				if(r){
					var param = getSystemParam();
					var url = "authorize/deleteRoleUser.json?param="+param;
					$.post(url, { "userId": userObject.userId },
						   function(data){
						   		if(data.result > -1){
						   			info("用户授权模块","清除用户【"+userObject.userName+"】权限成功！");
							   	}else{
							   		info("用户授权模块","清除用户【"+userObject.userName+"】权限失败！");
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
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="userId" id="userId" value="" data-options="prompt:'请输入登录账号',validType:'loginAccount[]'">
			</div>
			<div style="margin-top:10px;">
				<label for="userName">用户姓名:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="userName" id="userName" value=""  data-options="prompt:'请输入用户姓名',validType:'CN_EN_NO[]'">
			</div>
   			<div style="margin-top:10px">
				<label for="userStatus">用户状态:</label>
				<select id="userStatus" class="easyui-combobox"  name="userStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">请选择</option>  
				</select>  
			</div>
		  </form> 
    </div>
    <!-- 按钮 -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-search'" id="search-user" onclick="searchUser()">查询用户</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-remove'" style="display:none;" id="delete-role-user" onclick="deleteRoleUser()">清除授权</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;" id="edit-role-user" onclick="editRoleUser()">用户授权</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;&nbsp;出</a>&nbsp;&nbsp;&nbsp;  
   	</div>
	
</div>  
<!-- 数据列表 展示 -->
<div data-options="region:'center',iconCls:'icon-user-list',title:'系统用户信息展示列表'"  style="width:100%;height:100%;padding:0px;margin:0px; ">
   <table id="tableData"></table>
<!-- 模态界面 -->
<div id="win" class="easyui-window" title="Basic Window"  data-options="modal:true,closed:true,iconCls:'icon-user-list',minimizable:false,collapsible:false" style="width:50%;height:70%;padding:10px;overflow:hidden;">
	<iframe id="paramIframe" name="paramIframe" style="width:100%; height:100%;" frameborder="0" scrolling="no"  style="padding:0px;margin:0px;"></iframe>
</div>
<!-- 数据提交 -->
<form id="indexForm" name="indexForm" action="" method="post" target="other" style="dispaly:none;padding:0px;margin:0px;">
	<input id="menuId" type="hidden" value="" name="menuURL" />
	<input id="editUserId" type="hidden" value="" name="userId" />
</form> 
</div> 
<script type="text/javascript">
	/*打开添加用户页面*/
	function editRoleUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"用户授权管理"});
	   	var param = getSystemParam();
	   	$("#editUserId").val(userObject.userId);
       	indexForm.target= "paramIframe";
        indexForm.action = "authorize/loadAddRole.html?param="+param;
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