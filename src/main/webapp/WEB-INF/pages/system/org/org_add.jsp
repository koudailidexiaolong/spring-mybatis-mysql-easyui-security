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
		$(document).ready(function(){
			//???????????????
			loadSelectData("util_status",$("#orgStatus"),"0");//??????
			loadSelectData("util_leaf",$("#orgLeaf"),"");//???????????????
			//loadSelectData("org_type",$("#orgType"),"");//????????????
			initOrgLevel();
			$("#orgLevel").combobox({
				onSelect:function(record){
		        	loadParentOrg(record.dictionaryCode);
		        }
		    });
			$("#orgParentId").combobox({
				onSelect:function(record){
		        	$("#orgParentName").val(record.orgName);
		        }
		    });
		});
		/*?????????????????????*/
		function initOrgLevel(){
			var param = getSystemParam();
			$.ajax({
				   type: "POST",
				   url: "dictionary/getCode.json?param="+param,
				   async: false,
				   data: { dictionaryType: "org_level" },
				   success: function(data){
					   if(null != data && "" != data){
						   data[0].dictionaryName = "?????????";
						   data[0].dictionaryCode = "";
					   }
					   $("#orgLevel").combobox({
					        data:data,
					        valueField:"dictionaryCode",
					        textField:"dictionaryName",
					        panelHeight:200
					    });
				   }
			});
		}
		/*??????????????????*/
		function loadParentOrg(dictionaryCode){
			if(dictionaryCode == "1"){
				dictionaryCode = "0";
			}else if(dictionaryCode == "2"){
				dictionaryCode = "1";
			}else if (undefined == dictionaryCode || "" == dictionaryCode){
				return;
			}
			//????????????
			var param = getSystemParam();
			$.post("org/getSystemOrgParentList.json?param="+param, {"orgLevel":dictionaryCode},function(data){
				 var obj = new Object();
				 obj.orgName = "?????????";
				 obj.orgId = "";
			     data.unshift(obj);
				$("#orgParentId").combobox({
					data:data,
			        valueField:"orgId",
			        textField:"orgName",
			        panelHeight:200	       
			 	});
			}, "json");
		}
		
	 	/*???????????????????????????*/
	 	function validateOrg(){
	 		var param = getSystemParam();
	 		$.post("org/validateOrg.json?param="+param, { orgId: $("#orgId").val() },function(data){
	 			alert(data.result);
	 			if(data.result == 0){
	 				save();
	 			}else{
	 				info("????????????","????????????????????????????????????");
	 				return;
	 			}
			}, "json");
	 	}
	 	
		/*???????????????????????????*/
		function save(){
			var param = getSystemParam();
			var url = "org/saveOrg.json?param="+param;
			//????????????????????????
			$("#orgParentName").val($("#orgParentId").combobox("getText"));
			if($("#paramForm").form("validate") ==false){
				return;
			}
			//????????????
			$.post(url, $("#paramForm").serialize(),function(data){
			    if(data.result == 1){
					parent.info("????????????","???????????????????????????");
					parent.exit();
					parent.searchOrg();
				}else{
					info("????????????","???????????????????????????");
					return;
				}
			   }, "json");
		}
		
		function clearForm(){
			$("#paramForm").form("clear");
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'????????????',iconCls:'icon-user-add'" >
   		 <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="clearForm()">???&nbsp;???</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="validateOrg()">???&nbsp;???</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">???&nbsp;???</a>&nbsp;&nbsp;  
   		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'????????????????????????'" border="true"  noheader="true"  fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		  <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   		 	<div style="margin-top:15px;">
   				 <label for="orgId">????????????:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="orgId" id="orgId" value="" data-options="prompt:'?????????????????????',required:true,validType: 'CN_EN_NO[]'">
			</div>
   			<div style="margin-top:15px;">
   				 <label for="orgName">????????????:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="orgName" value="" data-options="prompt:'?????????????????????',required:true,validType: 'CN_EN_NO[]'">
			</div>
			<div style="margin-top:15px;">
				<label for="orgFullName">????????????:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="orgFullName" value=""  data-options="prompt:'?????????????????????',required:true,validType: 'CN_EN_NO[]'">
			</div>
   			<div style="margin-top:15px">
				<label for="orgStatus">????????????:</label>
				<select id="orgStatus" class="easyui-combobox"  name="orgStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION'">  
				    <option value="">?????????</option>  
				</select>  
			</div>
			<div style="margin-top:15px">
				<label for="orgLevel">????????????:</label>
				<select id="orgLevel" class="easyui-combobox"  name="orgLevel" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION'">
				 	<option value="">?????????</option>  
				</select>  
			</div>
			<div style="margin-top:15px">
				<label for="orgParentId">??????????????????:</label>
				<select id="orgParentId" class="easyui-combobox"  name="orgParentId" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'200',required:true,editable:false,validType:'OPTION'">  
					 <option value="">?????????</option>  
				</select>
				<input type="hidden" id="orgParentName" name="orgParentName" value=""/>
			</div> 
			<!-- 
			<div style="margin-top:15px">
				<label for="orgType">????????????:</label>
				<select id="orgType" class="easyui-combobox"  name="orgType" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION'">  
				    <option value="">?????????</option>  
				</select>  
			</div>
			 -->
			<div style="margin-top:15px">
				<label for="orgLeaf">???????????????:</label>
				<select id="orgLeaf" class="easyui-combobox"  name="orgLeaf" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',required:true,editable:false,validType:'OPTION'">  
				 <option value="">?????????</option>  
				</select>
			</div>
			<div style="margin-top:15px;">
				<label for="orgPhone">????????????:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:62px;padding:10px" name="orgPhone" value=""  data-options="prompt:'?????????????????????',multiline:true,validType: 'maxLength[200]'">
			</div>
			<div style="margin-top:15px;">
				<label for="orgAddress">????????????:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:62px;padding:10px" name="orgAddress" value=""  data-options="prompt:'?????????????????????',multiline:true,validType: 'maxLength[500]'">
			</div>
			<div style="margin-top:15px;">
				<label for="orgDesc">????????????:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:62px;padding:10px" name="orgDesc" value=""  data-options="prompt:'???????????????????????????',multiline:true,validType: 'maxLength[500]'">
			</div>
			<div style="margin-top:15px;">&nbsp;</div>
			<div style="margin-top:15px;">&nbsp;</div>
			<div style="margin-top:15px;">&nbsp;</div>
		   </form>
		   </div>
    </div>
    </div>
    <iframe id="other" name="other" src=""  style="padding:0px;margin:0px;display:none;"></iframe>  
</body>  
</html>
