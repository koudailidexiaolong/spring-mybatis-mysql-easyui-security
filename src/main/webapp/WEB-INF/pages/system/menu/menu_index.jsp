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
		var menuObject = new Object();
		var rowIndex = -1;//???????????????????????????
		var util_status = null;
		$(document).ready(function(){
			initAuthorize();
			//????????????
			$("#delete-menu").linkbutton("disable");
			$("#edit-menu").linkbutton("disable");
			$("#get-menu").linkbutton("disable");
			//???????????????
			loadSelectData("util_status",$("#menuStatus"),"");
			loadSelectData("menu_level",$("#menuLevel"),"");
			initUtilStatus();
			searchMenu();
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
		function searchMenu(){
			rowIndex = -1;
			//????????????
			$("#delete-menu").linkbutton("disable");
			$("#edit-menu").linkbutton("disable");
			$("#get-menu").linkbutton("disable");
			var menuStatus = $("#menuStatus").combobox("getValue");//????????????
			var menuLevel = $("#menuLevel").combobox("getValue");//????????????
			var menuName = $("#menuName").val();//??????
			var param = getSystemParam();
			$("#tableData").datagrid({   
			    url:"menu/selectMenuListByPage.json?param="+param, 
			    method:"post",
			    striped:true,//????????????  
			    autoRowHeight:false,
			    singleSelect:true,
			    rownumbers:true,
			    fitColumns:true,
				fit:true,
			    columns:[[   
			        {field:"id",checkbox:true},
			        {field:"menuId",title:"????????????",align:"center",width:100},
					{field:"menuName",title:"????????????",align:"center",width:100},
					{field:"menuCode",title:"????????????",align:"center",width:100},
					{field:"menuFatherId",title:"???????????????",align:"center",width:100},
					{field:"menuStatus",title:"????????????",align:"center",width:100,formatter: function(value,row,index){
						if(util_status != null){
							for(var i=0;i<util_status.length;i++){
								if(value == util_status[i].dictionaryCode){
									return util_status[i].dictionaryName;
								}
							}
						}else{
							return value;
						}
					}},
					{field:"menuUrl",title:"??????URL",align:"center",width:100},
					{field:"menuIco",title:"????????????",align:"center",width:100},
					{field:"menuLevel",title:"????????????",align:"center",width:100},
					{field:"menuOrder",title:"????????????",align:"center",width:100},
					{field:"menuCreateUserName",title:"?????????",align:"center",width:100},
					{field:"menuCreateTime",title:"????????????",align:"center",width:100}
			    ]],
			    //????????????
			    queryParams: {
			    	"menuStatus":menuStatus,"menuName":menuName,"menuLevel":menuLevel
				},
				//????????????		    
			    pagination:true,
			    pagePosition:"bottom",
			    pageNumber:1,
			    pageList:[10,20,30,40],
			    onLoadError:function(none){
					//????????????
					$("#delete-menu").linkbutton("disable");
					$("#edit-menu").linkbutton("disable");
					$("#get-menu").linkbutton("disable");
					menuObject = null;
					//error("????????????","???????????????????????????");
				},
				onLoadSuccess:function(data){
					if(data.total == 0){
						info("????????????","??????????????????????????????");
					}
				},
			    onSelect:function(rowIndex, rowData){
					rowIndex = rowIndex;
					menuObject = rowData;
					//????????????
					$("#delete-menu").linkbutton("enable");
					$("#edit-menu").linkbutton("enable");
					$("#get-menu").linkbutton("enable");
				},//????????????
				onDblClickRow:function(rowIndex, rowData){
					rowIndex = rowIndex;
					menuObject = rowData;
					//????????????
					$("#delete-menu").linkbutton("enable");
					$("#edit-menu").linkbutton("enable");
					$("#get-menu").linkbutton("enable");
					getMenu();
				}
			}); 
		}
		/*??????????????????*/
		function deleteMenu(){
			$.messager.confirm("????????????","???????????????????????????????????????", function(r){
				if(r){
					var param = getSystemParam();
					var url = "menu/deleteMenu.json?param="+param;
					$.post(url, { "menuId": menuObject.menuId },
							   function(data){
						   		if(data.result >= 0){
						   			info("????????????","???????????????"+menuObject.menuName+"????????????");
						   			self.searchMenu();
						   			//????????????
									$("#delete-menu").linkbutton("disable");
									$("#edit-menu").linkbutton("disable");
							   	}else{
							   		info("????????????","???????????????"+menuObject.menuName+"????????????");
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
				<label for="menuName">????????????:</label>
				<input class="easyui-textbox trimBlank"  type="text" style="width:70%;height:32px;padding:10px" name="menuName" id="menuName" value=""  data-options="validType:'CN_EN_NO[]',prompt:'?????????????????????'">
			</div>
   			<div style="margin-top:10px">
				<label for="menuStatus">????????????:</label>
				<select class="easyui-combobox"  id="menuStatus"  name="menuStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">?????????</option>  
				</select>  
			</div>
   			<div style="margin-top:10px">
				<label for="menuLevel">????????????:</label>
				<select class="easyui-combobox"  id="menuLevel"  name="menuLevel" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">?????????</option>  
				</select>  
			</div>
		  </form>
    </div>
    <!-- ?????? -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-search'" id="search-menu" onclick="searchMenu()">????????????</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-info'" style="display:none;" id="get-menu" onclick="getMenu()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-add'" style="display:none;" id="add-menu" onclick="addMenu()">????????????</a>  
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;" id="edit-menu" onclick="editMenu()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-remove'" style="display:none;" id="delete-menu" onclick="deleteMenu()">????????????</a>
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
	<input id="editMenuId" type="hidden" value="" name="menuId" />
</form> 
</div> 
<script type="text/javascript">
	/*????????????????????????*/
	function addMenu(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "menu/loadAddMenu.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function editMenu(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#editMenuId").val(menuObject.menuId);
       	indexForm.target= "paramIframe";
        indexForm.action = "menu/loadEditMenu.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function getMenu(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#editMenuId").val(menuObject.menuId);
       	indexForm.target= "paramIframe";
        indexForm.action = "menu/getMenu.html?param="+param;
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