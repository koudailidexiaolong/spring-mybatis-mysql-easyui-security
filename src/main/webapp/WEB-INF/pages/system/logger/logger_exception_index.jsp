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
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
	<script type="text/javascript">
	    	//页面对象
		var userObject = new Object();
		var rowIndex = -1;//标示当前的选择的行
		var method_type = null;
		var module_type = null;
		//初始化加载
		$(document).ready(function(){
			 //加载下拉框
			 loadSelectData("module_type",$("#loggerExceptionModule"),"");
			//加载下拉框
			 loadSelectData("method_type",$("#loggerExceptionType"),"");
			 initMethod();
			 initModule();
			//禁用按钮
			searchLogger();
		});

		/*初始化加载方法类型*/
		function initMethod(){
			$.ajax({
				   type: "POST",
				   url: "dictionary/getCode.json",
				   async: false,
				   data: { dictionaryType: "method_type" },
				   success: function(data){
					   method_type = data;
				   }
				});
		}
		
		/*初始化加载模块类型*/
		function initModule(){
			$.ajax({
				   type: "POST",
				   url: "dictionary/getCode.json",
				   async: false,
				   data: { dictionaryType: "module_type" },
				   success: function(data){
					   module_type = data;
				   }
				});
		}
		
		/*查询日志信息的方法*/
		function searchLogger(){
			rowIndex = -1;
			var loggerExceptionType = $("#loggerExceptionType").combobox("getValue");//登录名
			var loggerExceptionModule = $("#loggerExceptionModule").combobox("getValue");//登录名
			var param = getSystemParam();
			$("#tableData").datagrid({   
			    url:"logger/exception/selectLoggerListByPage.json?param="+param, 
			    method:"post",
			    striped:true,//各行换色  
			    autoRowHeight:false,
			    singleSelect:true,
			    rownumbers:true,
			    fitColumns:true,
				fit:true,
			    columns:[[   
			        {field:"id",checkbox:true},
					{field:"loggerExceptionId",title:"日志编号",align:"center",width:100},
					{field:"userId",title:"用户编号",align:"center",width:100},
					{field:"userName",title:"用户姓名",align:"center",width:100},
					{field:"loggerExceptionModule",title:"业务模块",align:"center",width:100,formatter: function(value,row,index){
						if(null != module_type){
							for(var i=0;i<module_type.length;i++){
								if( value == module_type[i].dictionaryCode){
									return module_type[i].dictionaryName;
								}
							}
						}else{
							return value;
						}
					}},
					{field:"loggerExceptionType",title:"操作类型",align:"center",width:100,formatter: function(value,row,index){
						if(null != method_type){
							for(var i=0;i<method_type.length;i++){
								if( value == method_type[i].dictionaryCode){
									return method_type[i].dictionaryName;
								}
							}
						}else{
							return value;
						}
					}},
					{field:"loggerResponseTime",title:"响应毫秒",align:"center",width:100},
					{field:"loggerExceptionIp",title:"访问IP",align:"center",width:100},
					{field:"loggerOperatingSystem",title:"操作系统",align:"center",width:100},
					{field:"loggerBrowserType",title:"浏览器类型",align:"center",width:100},
					{field:"loggerBrowserVersion",title:"浏览器版本",align:"center",width:100},
					{field:"loggerExceptionDescription",title:"操作描述",align:"center",width:100},
					{field:"loggerExceptionContext",title:"错误信息描述",align:"center",width:100},
					{field:"loggerExceptionCreateTime",title:"创建时间",align:"center",width:100}
			    ]],
			    //查询条件
			    queryParams: {
			    	"loggerExceptionType":loggerExceptionType,"loggerExceptionModule":loggerExceptionModule
				},
				//分页参数		    
			    pagination:true,
			    pagePosition:"bottom",
			    pageNumber:1,
			    pageList:[10,20,30,40],
			    onLoadError:function(XMLHttpRequest){
		    		//禁用按钮
					userObject = null;
					//error("日志模块","查询日志信息失败！");
				},
				onLoadSuccess:function(data){
					if(data.total == 0){
						info("日志模块","当前没有查询到信息！");
					}
				},
			    onSelect:function(rowIndex, rowData){
					rowIndex = rowIndex;
					userObject = rowData;
					//禁用按钮
				},//双击事件
				onDblClickRow:function(rowIndex, rowData){
					rowIndex = rowIndex;
					userObject = rowData;
				}
			}); 
		}
		/*删除日志信息*/
		function deleteLogger(){
			$.messager.confirm("日志模块","您是否确认删除此条数据吗？", function(r){
				if(r){
					var param = getSystemParam();
					var url = "user/deleteLogger.json?param="+param;
					$.post(url, { "userId": userObject.userId },
							   function(data){
						   		if(data.result == 1){
						   			info("日志模块","删除日志【"+userObject.userName+"】成功！");
						   			self.searchLogger();
							   	}else{
							   		info("日志模块","删除日志【"+userObject.userName+"】失败！");
								}
					   }, "json");
				}
			});
		}
	</script>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;">  
<div data-options="region:'north',title:'系统日志信息管理',iconCls:'icon-user-list'" border="false"  noheader="true" style="height:auto;padding:0px;">
	<!-- 查询条件  -->
	<div class="form_div"  id="form_div" style="width:100%;height:auto;padding:0px; ">
       <form id="paramForm" method="post" name="paramForm" action="">  
   			<div style="margin-top:10px;">
   				<label for="loggerExceptionModule">操作模块:</label>
   				<select  class="easyui-combobox" name="loggerExceptionModule" id="loggerExceptionModule"  style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">请选择</option>  
				</select>  
			</div>
   			<div style="margin-top:10px;">
   				<label for="loggerExceptionType">日志类型:</label>
   				<select  class="easyui-combobox" name="loggerExceptionType" id="loggerExceptionType"  style="width:70%;padding:10px;height:32px" data-options="panelHeight:'auto',editable:false">  
				    <option value="">请选择</option>  
				</select>  
			</div>
		  </form>
    </div>
    <!-- 按钮 -->
	<div class="form_div"  style="width:100%;height:auto;padding:5px 0px 3px 0px;;text-align:right;">
   		<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="search-logger" onclick="searchLogger()">查询日志</a>
    	<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;&nbsp;出</a>&nbsp;&nbsp;&nbsp;  
   	</div>
</div>  
<!-- 数据列表 展示 -->
<div data-options="region:'center',iconCls:'icon-user-list',title:'日志信息展示列表'"  style="width:100%;height:100%;padding:0px;margin:0px; ">
   <table id="tableData"></table>
<!-- 模态界面 -->
<div id="win" class="easyui-window" title="Basic Window"  data-options="modal:true,closed:true,iconCls:'icon-user-list',minimizable:false,collapsible:true" style="width:70%;height:70%;padding:10px;overflow:hidden;">
	<iframe id="paramIframe" name="paramIframe" style="width:100%; height:100%;" frameborder="0" scrolling="no"  style="padding:0px;margin:0px;"></iframe>
</div>
<!-- 数据提交 -->
<form id="indexForm" name="indexForm" action="" method="post" target="other" style="dispaly:none;padding:0px;margin:0px;">
	<input id="menuId" type="hidden" value="" name="menuURL" />
	<input id="logger_Id" type="hidden" value="" name="loggerId" />
</form> 
</div> 
<script type="text/javascript">
	/*打开日志详情页面*/
	function getLogger(){
		$("#win").dialog("open");
		$("#win").panel({title:"日志管理"});
	   	var param = getSystemParam();
	   	$("#logger_Id").val(userObject.loggerId);
       	indexForm.target= "paramIframe";
        indexForm.action = "logger/getLogger.html?param="+param;
   		indexForm.submit();
	}
	function exit(){
		$("#win").dialog("close");
	}
</script>
</body>  
</html>