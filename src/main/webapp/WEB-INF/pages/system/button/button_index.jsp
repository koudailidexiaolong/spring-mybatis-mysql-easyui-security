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
		var buttonObject = new Object();
		var rowIndex = -1;//???????????????????????????
		var util_status = null;
		var menu_data = null;
		$(document).ready(function(){
			initAuthorize();
			//????????????
			$("#delete-button").linkbutton("disable");
			$("#edit-button").linkbutton("disable");
			$("#get-button").linkbutton("disable");
			//???????????????
			loadSelectData("util_status",$("#buttonStatus"),"");
			initUtilStatus();
			initMenu();
			searchButton();
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
		/*????????????????????????*/
		function initMenu(){
			$.ajax({
				   type: "POST",
				   url: "menu/selectMenuParentList.json",
				   async: false,
				   data: {},
				   success: function(data){
					   var obj = new Object();
					   obj.menuName = "?????????";
					   obj.menuId = "";
					   data.unshift(obj);
					   menu_data = data;
					   $("#menuId").combobox({
							data:data,
					        valueField:"menuId",
					        textField:"menuName",
					        panelHeight:200
					 	});
				   }
				});
		}
		
		/*???????????????????????????*/
		function searchButton(){
			rowIndex = -1;
			//????????????
			$("#delete-button").linkbutton("disable");
			$("#edit-button").linkbutton("disable");
			$("#get-button").linkbutton("disable");
			var buttonStatus = $("#buttonStatus").combobox("getValue");//????????????
			var buttonName = $("#buttonName").val();//??????
			var param = getSystemParam();
			$("#tableData").datagrid({   
			    url:"button/selectButtonListByPage.json?param="+param, 
			    method:"post",
			    striped:true,//????????????  
			    autoRowHeight:false,
			    singleSelect:true,
			    rownumbers:true,
			    fitColumns:true,
				fit:true,
			    columns:[[   
			        {field:"id",checkbox:true},
					{field:"buttonId",title:"????????????",align:"center",width:100},
					{field:"buttonName",title:"????????????",align:"center",width:100},
					{field:"buttonUrl",title:"??????url",align:"center",width:100},
					{field:"buttonCode",title:"????????????",align:"center",width:100},
					{field:"buttonIco",title:"????????????",align:"center",width:100},
					{field:"menuId",title:"????????????",align:"center",width:100,formatter: function(value,row,index){
						if(util_status != null){
							for(var i=0;i<menu_data.length;i++){
								if(value == menu_data[i].menuId){
									return menu_data[i].menuName;
								}
							}
						}else{
							return value;
						}
					}},
					{field:"buttonStatus",title:"????????????",align:"center",width:100,formatter: function(value,row,index){
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
					{field:"buttonCreateUserName",title:"?????????",align:"center",width:100},
					{field:"buttonCreateTime",title:"????????????",align:"center",width:100}
			    ]],
			    //????????????
			    queryParams: {
			    	"buttonStatus":buttonStatus,"buttonName":buttonName
				},
				//????????????		    
			    pagination:true,
			    pagePosition:"bottom",
			    pageNumber:1,
			    pageList:[10,20,30,40],
			    onLoadError:function(none){
					//????????????
					$("#delete-button").linkbutton("disable");
					$("#edit-button").linkbutton("disable");
					$("#get-button").linkbutton("disable");
					buttonObject = null;
					//error("????????????","???????????????????????????");
				},
				onLoadSuccess:function(data){
					if(data.total == 0){
						info("????????????","??????????????????????????????");
					}
				},
			    onSelect:function(rowIndex, rowData){
					rowIndex = rowIndex;
					buttonObject = rowData;
					//????????????
					$("#delete-button").linkbutton("enable");
					$("#edit-button").linkbutton("enable");
					$("#get-button").linkbutton("enable");
				},//????????????
				onDblClickRow:function(rowIndex, rowData){
					rowIndex = rowIndex;
					buttonObject = rowData;
					//????????????
					$("#delete-button").linkbutton("enable");
					$("#edit-button").linkbutton("enable");
					$("#get-button").linkbutton("enable");
					getButton();
				}
			}); 
		}
		/*??????????????????*/
		function deleteButton(){
			$.messager.confirm("????????????","???????????????????????????????????????", function(r){
				if(r){
					var param = getSystemParam();
					var url = "button/deleteButton.json?param="+param;
					$.post(url, { "buttonId": buttonObject.buttonId },
							   function(data){
						   		if(data.result >= 0){
						   			info("????????????","???????????????"+buttonObject.buttonName+"????????????");
						   			self.searchButton();
						   			//????????????
									$("#delete-button").linkbutton("disable");
									$("#edit-button").linkbutton("disable");
							   	}else{
							   		info("????????????","???????????????"+buttonObject.buttonName+"????????????");
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
				<label for="buttonName">????????????:</label>
				<input class="easyui-textbox trimBlank"  type="text" style="width:70%;height:32px;padding:10px" name="buttonName" id="buttonName" value=""  data-options="validType:'CN_EN_NO[]',prompt:'?????????????????????'">
			</div>
   			<div style="margin-top:10px">
				<label for="buttonStatus">????????????:</label>
				<select class="easyui-combobox"  id="buttonStatus"  name="buttonStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">?????????</option>  
				</select>  
			</div>
   			<div style="margin-top:10px">
				<label for="menuId">????????????:</label>
				<select class="easyui-combobox"  id="menuId"  name="menuId" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">?????????</option>  
				</select>  
			</div>
		  </form>
    </div>
    <!-- ?????? -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-search'" id="search-button" onclick="searchButton()">????????????</a>
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-info'" style="display:none;" id="get-button" onclick="getButton()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-add'" style="display:none;" id="add-button" onclick="addButton()">????????????</a>  
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit'" style="display:none;" id="edit-button" onclick="editButton()">????????????</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-remove'" style="display:none;" id="delete-button" onclick="deleteButton()">????????????</a>
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
	<input id="editButtonId" type="hidden" value="" name="buttonId" />
</form> 
</div> 
<script type="text/javascript">
	/*????????????????????????*/
	function addButton(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "button/loadAddButton.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function editButton(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#editButtonId").val(buttonObject.buttonId);
       	indexForm.target= "paramIframe";
        indexForm.action = "button/loadEditButton.html?param="+param;
   		indexForm.submit();
	}
	/*????????????????????????*/
	function getButton(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
	   	$("#editButtonId").val(buttonObject.buttonId);
       	indexForm.target= "paramIframe";
        indexForm.action = "button/getButton.html?param="+param;
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