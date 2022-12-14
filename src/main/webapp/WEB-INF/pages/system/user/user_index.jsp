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
	    //????????????
		var userObject = new Object();
		var rowIndex = -1;//???????????????????????????
		var util_status = null;//????????????
		var user_sex = null;//????????????
		//???????????????
		$(document).ready(function(){
			initAuthorize();
			//????????????
			$("#delete-user").linkbutton("disable");
			$("#edit-user").linkbutton("disable");
			$("#get-user").linkbutton("disable");
			$("#reload-password").linkbutton("disable");
			$("#edit-user-status").linkbutton("disable");
			//???????????????
			loadSelectData("util_status",$("#userStatus"),"");
			//???????????????
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
		/*???????????????????????????*/
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
		/*???????????????????????????*/
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
		 /* ??????????????????????????? */
		function editStatus() {
			var message = null;
			$("#editUserId").val(userObject.userId);
			if (userObject.userStatus == 0) {
				$("#editUserStatus").val(1);
				message = "??????????????????????????????";
			} else if (userObject.userStatus == 1) {
				$("#editUserStatus").val(0);
				message = "??????????????????????????????";
			}
			$.messager.confirm("????????????", message, function (r) {
				if (r) {
					var param = getSystemParam();
					var url = "user/updateUser.json?param=" + param;
					//????????????
					$.post(url, $("#indexForm").serialize(), function (data) {
						if (data.result == 1) {
							if ($("#editUserStatus").val() == 0) {
								info("????????????", "???????????????");
							} else if ($("#editUserStatus").val() == 1) {
								info("????????????", "???????????????");
							}
							searchUser();
						} else {
							info("????????????", "???????????????????????????");
							return;
						}
					}, "json");
		
				}
			});
		}
				
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;">  
<div data-options="region:'north',title:'????????????????????????',iconCls:'icon-user-list'" border="false"  noheader="true" style="height:auto;padding:0px;">
	<!-- ????????????  -->
	<div class="form_div"  id="form_div" style="width:100%;height:auto;padding:0px; ">
       <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:10px;">
   				<label for="userId">????????????:</label>
				<input class="easyui-textbox trimBlank" type="text" style="width:70%;height:32px;padding:10px" name="userId" id="userId" value="" data-options="prompt:'?????????????????????',validType:'loginAccount[]'">
			</div>
			<div style="margin-top:10px;">
				<label for="userName">????????????:</label>
				<input class="easyui-textbox trimBlank"  type="text" style="width:70%;height:32px;padding:10px" name="userName" id="userName" value=""  data-options="prompt:'?????????????????????',validType:'CN_EN_NO[]'">
			</div>
   			<div style="margin-top:10px">
				<label for="userStatus">????????????:</label>
				<select id="userStatus" class="easyui-combobox"  name="userStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">?????????</option>  
				</select>  
			</div>
   			<div style="margin-top:10px">
				<label for="userSex">????????????:</label>
				<select id="userSex" class="easyui-combobox"  name="userSex" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">?????????</option>  
				</select>  
			</div>
			<div style="margin-top:10px;">
				<label for="userName">????????????:</label>
				<input class="easyui-datebox"  type="text" style="width:70%;height:32px;padding:10px" name="beginDate" id="beginDate" value=""  data-options="editable:false,prompt:'?????????????????????'">
			</div>
			<div style="margin-top:10px;">
				<label for="userName">????????????:</label>
				<input class="easyui-datebox"  type="text" style="width:70%;height:32px;padding:10px" name="endDate" id="endDate" value=""  data-options="editable:false,prompt:'?????????????????????'">
			</div>
	  </form>
    </div>
    <!-- ?????? -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-search'" id="search-user" onclick="searchUser()">????????????</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="display:none;" id="reload-password" onclick="reloadPassword()">????????????</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;"  id="edit-user-status" onclick="editStatus()">??????/??????</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-info'" style="display:none;"  id="get-user" onclick="getUser()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-add'" style="display:none;"  id="add-user" onclick="addUser()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;"  id="edit-user" onclick="editUser()">????????????</a> 
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-remove'" style="display:none;"  id="delete-user" onclick="deleteUser()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">???&nbsp;&nbsp;???</a>&nbsp;&nbsp;&nbsp;  
   	</div>
</div>  
<!-- ???????????? ?????? -->
<div data-options="region:'center',iconCls:'icon-user-list',title:'??????????????????????????????'"  style="width:100%;height:100%;padding:0px;margin:0px; ">
   <table id="tableData"></table>
<!-- ???????????? -->
<div id="win" class="easyui-window" title="Basic Window"  data-options="modal:true,closed:true,iconCls:'icon-user-list',minimizable:false,collapsible:false" style="width:70%;height:70%;padding:10px;overflow:hidden;">
	<iframe id="paramIframe" name="paramIframe" style="width:100%; height:100%;" frameborder="0" scrolling="no"  style="padding:0px;margin:0px;"></iframe>
</div>
<!-- ???????????? -->
<form id="indexForm" name="indexForm" action="" method="post" target="other" style="dispaly:none;padding:0px;margin:0px;">
	<input id="menuId" type="hidden" value="" name="menuURL" />
	<input id="editUserId" type="hidden" value="" name="userId" />
	<input id="editUserStatus" type="hidden" value="" name="userStatus" />
</form> 
</div> 
<script type="text/javascript">


/*???????????????????????????*/
function searchUser(){
	
	rowIndex = -1;
	//????????????
	$("#delete-user").linkbutton("disable");
	$("#edit-user").linkbutton("disable");
	$("#get-user").linkbutton("disable");
	$("#reload-password").linkbutton("disable");
	$("#edit-user-status").linkbutton("disable");
	var userId = $("#userId").val();//?????????
	var userName = $("#userName").val();//??????
	var userStatus = $("#userStatus").combobox("getValue");//??????
	var userSex = $("#userSex").combobox("getValue");
	var beginDate = $("#beginDate").datebox("getValue");
	var endDate = $("#endDate").datebox("getValue");
	var param = getSystemParam();
	$("#tableData").datagrid({   
	    url:"user/getUserListByPage.json?param="+param, 
	    method:"post",
	    striped:true,//????????????  
	    autoRowHeight:false,
	    singleSelect:true,
	    rownumbers:true,
	    fitColumns:true,
		fit:true,
	    columns:[[   
	        {field:"id",checkbox:true},
			{field:"userId",title:"?????????",align:"center",width:100},
			{field:"userName",title:"??????",align:"center",width:100},
			{field:"userSex",title:"??????",align:"center",width:100,formatter: function(value,row,index){
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
			{field:"userAge",title:"??????",align:"center",width:100},
			{field:"userPhone",title:"??????",align:"center",width:100},
			{field:"userSkin",title:"??????",align:"center",width:100},
			{field:"userStatus",title:"????????????",align:"center",width:100,formatter: function(value,row,index){
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
			{field:"userCreateTime",title:"????????????",align:"center",width:100},
			{field:"userCreateUserName",title:"?????????",align:"center",width:100}
	    ]],
	    //????????????
	    queryParams: {
	    	"userId":userId,"userName":userName,"userStatus":userStatus,"userSex":userSex,"beginDate":beginDate,"endDate":endDate
		},
		//????????????		    
	    pagination:true,
	    pagePosition:"bottom",
	    pageNumber:1,
	    pageList:[10,20,30,40],
	    onLoadError:function(none){
			//????????????
			$("#delete-user").linkbutton("disable");
			$("#edit-user").linkbutton("disable");
			$("#get-user").linkbutton("disable");
			$("#reload-password").linkbutton("disable");
			$("#edit-user-status").linkbutton("disable");
			userObject = null;
			error("????????????","???????????????????????????");
		},
		onLoadSuccess:function(data){
			if(data.total == 0){
				info("????????????","??????????????????????????????");
			}
		},//????????????
	    onSelect:function(rowIndex, rowData){
			rowIndex = rowIndex;
			userObject = rowData;
			//????????????
			$("#delete-user").linkbutton("enable");
			$("#edit-user").linkbutton("enable");
			$("#get-user").linkbutton("enable");
			$("#reload-password").linkbutton("enable");
			$("#edit-user-status").linkbutton("enable");
		},//????????????
		onDblClickRow:function(rowIndex, rowData){
			rowIndex = rowIndex;
			userObject = rowData;
			//????????????
			$("#delete-user").linkbutton("enable");
			$("#edit-user").linkbutton("enable");
			$("#get-user").linkbutton("enable");
			$("#reload-password").linkbutton("enable");
			$("#edit-user-status").linkbutton("enable");
			getUser();
		}
	}); 
}
/*??????????????????*/
function deleteUser(){
	$.messager.confirm("????????????","???????????????????????????????????????", function(r){
		if(r){
			var param = getSystemParam();
			var url = "user/deleteUser.json?param="+param;
			$.post(url, { "userId": userObject.userId },
					   function(data){
				   		if(data.result == 1){
				   			info("????????????","???????????????"+userObject.userName+"????????????");
				   			self.searchUser();
				   			//????????????
							$("#delete-user").linkbutton("disable");
							$("#edit-user").linkbutton("disable");
					   	}else{
					   		info("????????????","???????????????"+userObject.userName+"????????????");
						}
			   }, "json");
		}
	});
}
/*????????????*/
function reloadPassword(){
	$.messager.confirm("????????????","??????????????????????????????", function(r){
	      if(r){
			//????????????
			var param = getSystemParam();
			var url = "user/resetPassword.json?param="+param;
			$.post(url, {userId:userObject.userId},function(data){
			    if(data.result==1){
			    	info("????????????","?????????????????????");
					self.searchUser();
				}else{
					info("????????????","?????????????????????");
					return;
				}
		   }, "json");
	    }
	});
}

</script>
<script type="text/javascript">
	/*????????????????????????*/
	function addUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadAddUser.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function editUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#editUserId").val(userObject.userId);
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadEditUser.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function getUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#editUserId").val(userObject.userId);
       	indexForm.target= "paramIframe";
        indexForm.action = "user/getUser.html?param="+param;
   		indexForm.submit();
	}
	function exit(){
		$("#win").dialog("close");
	}
	/*?????????????????????*/
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