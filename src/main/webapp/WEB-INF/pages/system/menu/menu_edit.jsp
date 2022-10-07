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
  	    var isFind = false;//判断是否存在
  		var index = "${systemMenuDTO.menuStatus }";
		var menuLevel = "${systemMenuDTO.menuLevel }";
		var menuFatherId = "${systemMenuDTO.menuFatherId }";
  		$(document).ready(function(){
  			//加载下拉框
			loadSelectData("util_status",$("#menuStatus"),index);
			loadSelectData("menu_level",$("#menuLevel"),menuLevel);
  			
			//绑定事件
		 	$("#menuId").textbox("textbox").bind("blur", function(){
				//提交表单
				var param = getSystemParam();
				var url = "menu/validateMenu.json?param="+param;
				/*校验关键词*/
				$.post(url,{menuId:$("#menuId").val()},function(data){
				    if(data.result == 1){
				    	info("菜单管理","菜单编号已存在！");
				    	$("#menuId").textbox("setValue","");
				    	isFind = true;
					}else if(data.result == -1){
				    	info("菜单管理","校验菜单编号发生异常！");
				    	isFind = true;
					}else{
					    isFind = false;
					}
				   }, "json");
		 	});
			
		 	
		 	//菜单等级
		 	$("#menuLevel").combobox({
				onSelect:function(record){
		        	//判断选择的值
		        	if(record.dictionaryCode == "0"){ //顶级
		        		 var data = new Array();
		        		 var obj = new Object();
						 obj.menuName = "请选择";
						 obj.menuId = "";
					     data.unshift(obj);
					     var obj1 = new Object();
						 obj1.menuName = "ROOT";
						 obj1.menuId = "0";
					     data.push(obj1);
		        		$("#menuFatherId").combobox({
							data:data,
					        valueField:"menuId",
					        textField:"menuName"	       
					 	});
		        		if(menuFatherId == "0"){
		        			//默认选中
						    $("#menuFatherId").combobox("select",menuFatherId);
		        		}
		        	}else{
		        		loadParentMenu(record.dictionaryCode)
		        	}
		        }
		    });
			
		
		});
	
  		
  		/*查询上级菜单*/
		function loadParentMenu(menuLevel){
			if(menuLevel == "1"){
				menuLevel = "0";
			}else if(menuLevel == "2"){
				menuLevel = "1";
			}else if (undefined == menuLevel || "" == menuLevel){
				return;
			}
			//提交表单
			var param = getSystemParam();
			$.post("menu/selectMenuParentList.json?param="+param, {"menuLevel":menuLevel},function(data){
				 var obj = new Object();
				 obj.menuName = "请选择";
				 obj.menuId = "";
			     data.unshift(obj);
				$("#menuFatherId").combobox({
					data:data,
			        valueField:"menuId",
			        textField:"menuName",
			        onLoadSuccess:function(){
					    for(var i=0;i<data.length;i++){
			        		if(data[i].menuId == menuFatherId){
			        			//默认选中
							    $("#menuFatherId").combobox("select",menuFatherId);
			        			break;
			        		}
			        	}
			        }
			 	});
			}, "json");
		}
  		
		/*保存菜单信息的方法*/
		function update(){
		    if(isFind){
				info("菜单管理","菜单已存在！");
				return;
			}
			var param = getSystemParam();
			var url = "menu/updateMenu.json?param="+param;
			if($("#paramForm").form("validate") ==false){
				return;
			}
			//提交表单
			$.post(url, $("#paramForm").serialize(),function(data){
			    if(data.result==1){
			    	parent.info("菜单管理","修改菜单信息成功！");
					parent.exit();
					parent.searchMenu();
				}else{
					info("菜单管理","修改菜单信息失败！");
					return;
				}
			   }, "json");
		}

	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'修改菜单',iconCls:'icon-user-add'" >
 	  	<div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="update()">保&nbsp;存</a>
	    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统菜单信息管理'" border="true"  noheader="true"   fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
			 <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
		   		 <form id="paramForm" method="post" name="paramForm" action="">  
		   			<div style="margin-top:15px;">
		   				 <label for="menuId">菜单编号:</label>
						<input class="easyui-textbox" id="menuId" type="text" style="width:70%;height:32px;padding:10px" name="menuId" value="${systemMenuDTO.menuId }" data-options="readonly:true,prompt:'请输入菜单编号']">
					</div>
		   			<div style="margin-top:15px;">
		   				<label for="menuName">菜单名称:</label>
						<input class="easyui-textbox trimBlank" type="text" style="width:70%;height:32px;padding:10px" name="menuName" id="menuName" value="${systemMenuDTO.menuName }" data-options="prompt:'请输入菜单名称',required:true,validType: ['CN_EN_NO[]','maxLenght[50]']">
					</div>
					<div style="margin-top:15px">
						<label for="menuLevel">菜单等级:</label>
						<select id="menuLevel" class="easyui-combobox"  name="menuLevel" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]'"  >  
						    <option value="">请选择</option>  
						</select>
					</div>
		   			<div style="margin-top:15px">
						<label for="menuFatherId">菜单父节点:</label>
						<select id="menuFatherId" class="easyui-combobox"  name="menuFatherId" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]'"  >  
						    <option value="">请选择</option>  
						</select>  
					</div>
		   			<div style="margin-top:15px">
						<label for="menuStatus">菜单状态:</label>
						<select  class="easyui-combobox" id="menuStatus" name="menuStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]'">  
						     <option value="">请选择</option> 
						</select>  
					</div>
					<div style="margin-top:15px">
						<label for="menuUrl">菜单URL:</label>
						<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="menuUrl" value="${systemMenuDTO.menuUrl }" data-options="prompt:'请输入菜单url',validType: ['maxLenght[500]']">
					</div>
		   			<div style="margin-top:15px">
						<label for="menuIco">菜单图标:</label>
						<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="menuIco" value="${systemMenuDTO.menuIco }" data-options="prompt:'请输入菜单图标',validType: ['maxLenght[500]']">
					</div>
		   			<div style="margin-top:15px">
						<label for="menuOrder">菜单顺序:</label>
						<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="menuOrder" value="${systemMenuDTO.menuOrder }" data-options="prompt:'请输入菜单顺序',validType: ['maxLenght[500]']">
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
