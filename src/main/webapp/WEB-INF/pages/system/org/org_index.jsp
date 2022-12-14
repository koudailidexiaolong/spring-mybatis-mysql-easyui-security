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
		var orgObject = new Object();
		var rowIndex = -1;//???????????????????????????
		var util_status = null;
		var org_type = null;
		var org_level = null;
		$(document).ready(function(){
			initAuthorize();
			//????????????
			$("#delete-org").linkbutton("disable");
			$("#edit-org").linkbutton("disable");
			$("#get-org").linkbutton("disable");
			//???????????????
 			loadSelectData("util_status",$("#orgStatus"),"");
			//???????????????
			loadSelectData("org_level",$("#orgLevel"),"");
			initUtilStatus();
			initOrgType();
			initOrgLevel();
			searchOrg();
		});
		/*???????????????????????????*/
		function initUtilStatus(){
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
		function initOrgType(){
			$.ajax({
				   type: "POST",
				   url: "dictionary/getCode.json",
				   async: false,
				   data: { dictionaryType: "org_type" },
				   success: function(data){
					   org_type = data;
				   }
				});
		}
		/*???????????????????????????*/
		function initOrgLevel(){
			$.ajax({
				   type: "POST",
				   url: "dictionary/getCode.json",
				   async: false,
				   data: { dictionaryType: "org_level" },
				   success: function(data){
					   org_level = data;
				   }
				});
		}
		/*???????????????????????????*/
		function searchOrg(){
			rowIndex = -1;
			//????????????
			$("#delete-org").linkbutton("disable");
			$("#edit-org").linkbutton("disable");
			$("#get-org").linkbutton("disable");
			var orgName = $("#orgName").val();//????????????
			var orgLevel = $("#orgLevel").combobox("getValue");//??????
			var orgStatus = $("#orgStatus").combobox("getValue");//??????
			var param = getSystemParam();
			$("#tableData").treegrid({   
			    url:"org/getOrgList.json?param="+param, 
			    method:"post",
			    striped:true,//????????????  
			    autoRowHeight:false,
			    singleSelect:true,
			    rownumbers:true,
			    fitColumns:true,
				fit:true,
				idField:"orgId",
			    treeField:"orgName",
			    animate:true,//????????????
			    columns:[[   
			        {field:"id",checkbox:true},
					{field:"orgId",title:"????????????",align:"center",width:50},
					{field:"orgName",title:"????????????",align:"left",width:180},
					{field:"orgLevel",title:"????????????",align:"center",width:70,formatter: function(value,row,index){
						if(org_level != null){
							for(var i=0;i<org_level.length;i++){
								if(value == org_level[i].dictionaryCode){
									return org_level[i].dictionaryName;
								}
							}
						}else{
							return value;
						}
					}},
					{field:"orgType",title:"????????????",align:"center",width:100,formatter: function(value,row,index){
						if(org_type != null){
							for(var i=0;i<org_type.length;i++){
								if(value == org_type[i].dictionaryCode){
									return org_type[i].dictionaryName;
								}
							}
						}else{
							return value;
						}
					}},
					{field:"orgParentId",title:"???????????????",align:"center",width:50},
					{field:"orgStatus",title:"????????????",align:"center",width:70,formatter: function(value,row,index){
						if(util_status != null){
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
			    //????????????
			    queryParams: {
			    	"orgLevel":orgLevel,"orgName":orgName,"orgStatus":orgStatus
				},
			    onLoadError:function(data){
					//????????????
					$("#delete-org").linkbutton("disable");
					$("#edit-org").linkbutton("disable");
					$("#get-org").linkbutton("disable");
					orgObject = null;
					info("????????????","??????????????????????????????");
				},
				onBeforeExpand:function(row){    //???????????????????????????+"&orgLevel="+data.orgLevel
                   if(row != null){
                	   $(this).treegrid("options").url = "org/getOrgList.json?orgParentId="+row.orgId;  
                   }
	            },
			    onSelect:function(record){
					orgObject = record;
					if(orgObject.orgParentId == "0"){
						$("#delete-org").linkbutton("disable");
						$("#edit-org").linkbutton("disable");
					}else{
						$("#delete-org").linkbutton("enable");
						$("#edit-org").linkbutton("enable");
						$("#get-org").linkbutton("enable");
					}
					
				},//????????????
				onDblClickRow:function(row){
					orgObject = row;
					if(orgObject.orgParentId == "0"){
						$("#delete-org").linkbutton("disable");
						$("#edit-org").linkbutton("disable");
					}else{
						$("#delete-org").linkbutton("enable");
						$("#edit-org").linkbutton("enable");
						$("#get-org").linkbutton("enable");
					}
					getOrg();
				}
			}); 
		}
		/*??????????????????*/
		function deleteOrg(){
			$.messager.confirm("????????????","???????????????????????????????????????", function(r){
				if(r){
					var param = getSystemParam();
					var url = "org/deleteOrg.json?param="+param;
					$.post(url, {"orgId":orgObject.orgId},
							   function(data){
						   		if(data.result == 1){
						   			info("????????????","???????????????"+orgObject.orgName+"????????????");
						   			self.searchOrg();
						   			//????????????
									$("#delete-org").linkbutton("disable");
									$("#edit-org").linkbutton("disable");
							   	}else if(data.result == 2){
							   		info("????????????","????????????????????????????????????????????????????????????????????????");
							   		return ;
								}else {
							   		info("????????????","???????????????"+orgObject.orgName+"????????????");
							   		return ;
								}
					 }, "json");
				}
			});
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;">  
<div data-options="region:'north',title:'????????????????????????',iconCls:'icon-user-list'" border="false"  noheader="true" style="height:auto;padding:0px;">
	<!-- ???????????? -->
	<div class="form_div" id="form_div" style="width:100%;height:auto;padding:0px; ">
       <form id="paramForm" method="post" name="paramForm" action="">  
			<div style="margin-top:10px;">
				<label for="orgName">????????????:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="orgName" id="orgName" value=""  data-options="prompt:'?????????????????????',validType:'CN_EN_NO[]'">
			</div>
   			<div style="margin-top:10px">
				<label for="orgStatus">????????????:</label> 
 				<select id="orgStatus" class="easyui-combobox"  name="orgStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
 				    <option value="">?????????</option> 
 				</select>  
 			</div> 
			<div style="margin-top:10px">
				<label for="orgLevel">????????????:</label>
				<select class="easyui-combobox"  name="orgLevel" id="orgLevel" data-options="panelHeight:'auto',editable:false"  style="width:70%;padding:10px;height:32px" >  
				</select>  
			</div>
		  </form>
    </div>
    <!-- ?????? -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-search'" id="search-org" onclick="searchOrg()">????????????</a>
<!--    		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" id="searchOrg" onclick="javascript:alert('????????????');">????????????</a>&nbsp; -->
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-info'" style="display:none;" id="get-org" onclick="getOrg()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-add'" style="display:none;" id="add-org" onclick="addOrg()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;" id="edit-org" onclick="editOrg()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-remove'" style="display:none;" id="delete-org" onclick="deleteOrg()">????????????</a>
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
	<input id="orgId" type="hidden" value="" name="orgId" />
</form> 
</div> 
<script type="text/javascript">
	/*????????????????????????*/
	function addOrg(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "org/loadAddOrg.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function editOrg(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#orgId").val(orgObject.orgId);
       	indexForm.target= "paramIframe";
        indexForm.action = "org/loadEditOrg.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function getOrg(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#orgId").val(orgObject.orgId);
       	indexForm.target= "paramIframe";
        indexForm.action = "org/getOrg.html?param="+param;
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