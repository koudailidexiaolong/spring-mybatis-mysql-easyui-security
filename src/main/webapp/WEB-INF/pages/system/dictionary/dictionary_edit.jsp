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
	  	 var dictionaryCode = "${systemDictionaryDTO.dictionaryCode }";
	  	 var dictionaryType = "${systemDictionaryDTO.dictionaryType }";
  		 var index = "${systemDictionaryDTO.dictionaryStatus }";
  		$(document).ready(function(){
  			// 在从远程服务器加载数据之前改变请求参数
  			//加载下拉框
			loadSelectData("util_status",$("#dictionaryStatus"),index);
		});
	
		/*保存数据字典信息的方法*/
		function update(){
			var param = getSystemParam();
			if($("#paramForm").form("validate") == false){
				return;
			}
			//如果两者相等 则不进行校验
			if(dictionaryCode == $("#dictionaryCode").val() && dictionaryType == $("#dictionaryType").val()){
				//提交表单
				$.post("dictionary/updateDictionary.json?param="+param, $("#paramForm").serialize(),function(data){
				    if(data.result == 1){
				    	parent.info("数据字典管理","修改数据字典信息成功！");
						parent.exit();
						parent.searchDictionary();
					}else{
						info("数据字典管理","修改数据字典信息失败！");
						return;
					}
				}, "json");
			}else{
				//提交表单
				$.post("dictionary/validateDictionary.json?param="+param, 
				{"dictionaryCode":$("#dictionaryCode").val(),"dictionaryType":$("#dictionaryType").val()},
				function(data){
				    if(data.result <= 0){
				    	  //提交表单
						  $.post("dictionary/updateDictionary.json?param="+param, $("#paramForm").serialize(),function(data){
							    if(data.result==1){
							    	parent.info("数据字典管理","修改数据字典信息成功！");
									parent.exit();
									parent.searchDictionary();
								}else{
									info("数据字典管理","修改数据字典信息失败！");
									return;
								}
						   }, "json");
					}else{
						info("数据字典管理","修改数据字典信息失败！");
						return;
					}
			   }, "json");
			}
		}

	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'修改数据字典',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="update()">保&nbsp;存</a>
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>
  		 </div>
   		<div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'系统数据字典信息管理'" border="true"  noheader="true" fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		 <div  class="easyui-panel" noborder="true" border="false" style="width:100%;height:100%;padding:0px;margin:0px;">
   		 <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:15px;">
   				 <label for="dictionaryName">数据字典名称:</label>
   				 <input type="hidden"   id="dictionaryId" name="dictionaryId" value="${systemDictionaryDTO.dictionaryId }"/>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="dictionaryName" value="${systemDictionaryDTO.dictionaryName }" data-options="prompt:'请输入数据字典名称',required:true,validType:['CN_EN_NO[]','maxLength[50]']">
			</div>
		   <div style="margin-top:15px;">
   				 <label for="dictionaryCode">数据字典编码:</label>
				<input class="easyui-textbox" type="text" style="width:70%;height:32px;padding:10px" name="dictionaryCode" value="${systemDictionaryDTO.dictionaryCode }" data-options="prompt:'请输入数据字典编码',required:true,validType:['CN_EN_NO[]','maxLength[50]']">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryType">数据字典类型:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryType" value="${systemDictionaryDTO.dictionaryType }"  data-options="prompt:'请输入数据字典类型',required:true,validType:['CN_EN_NO[]','maxLength[50]']">
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryOrder">数据字典顺序:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryOrder" value="${systemDictionaryDTO.dictionaryOrder }"  data-options="prompt:'请输入数据字典顺序',validType:'int_double[]'">
			</div>
   			<div style="margin-top:15px">
				<label for="dictionaryStatus">数据字典状态:</label>
				<select id="dictionaryStatus" class="easyui-combobox"  name="dictionaryStatus" style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false,required:true,validType:'OPTION[]'">  
				    <option value="">请选择</option>  
				</select>  
			</div>
			<div style="margin-top:15px;">
				<label for="dictionaryOrder">描述:</label>
				<input class="easyui-textbox"  type="text" style="width:70%;height:32px;padding:10px" name="dictionaryDesc" value="${systemDictionaryDTO.dictionaryDesc }"  data-options="prompt:'请输入数据字典描述'">
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