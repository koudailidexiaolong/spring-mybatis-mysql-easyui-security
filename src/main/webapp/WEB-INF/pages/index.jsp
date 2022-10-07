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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${WEBNAME}</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/icon.css" />
<security:authentication property="principal" var="userSession" ></security:authentication>
<c:if test="${userSession.userSkin != ''}">
<link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/${userSession.userSkin }/easyui.css" />
</c:if>
<c:if test="${empty userSession || empty userSession.userSkin || userSession.userSkin == null}">
    <link rel="stylesheet" type="text/css" href="./plugin/easyui/themes/default/easyui.css" />
</c:if>
<script type="text/javascript" src="./plugin/easyui/jquery.min.js"></script>
<script type="text/javascript" src="./plugin/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
<script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
<script type="text/javascript" src="./commons/js/message.js"></script>
<script type="text/javascript" src="./commons/js/timer.js"></script>
<style type="text/css">
	#index_header div,ul,li,a{
		padding:0px;
		margin:0px;
	}
	#index_header ul li{
		list-style: none;
		width:150px;
		height: 25px;
		float:right;
		text-align:center;
		/*padding-top:2px;*/
	}
	#head_head div{
		padding:0px;
		margin:0px;
		float:left;
	}
	#head_head_right_foot span,var{
		padding:0px;
		margin:0px;
	}
	#menu_ul,li,a,h1{
		padding:0px;
		margin:0px;
		text-decoration: none;
		list-style: none;
	}
	#menu_ul li{
		padding-top:3px;
		height: 25px;
		/*border-bottom-color: black;
		border-bottom-style: solid;
		border-bottom-width: 1px;*/
	}
	a:link {color: #000000}     /* 未访问的链接 */
	a:visited {color: #000000}  /* 已访问的链接 */
	a:hover {color: #FF00FF}    /* 当有鼠标悬停在链接上 */
	a:active {color: #0000FF}   /* 被选择的链接 */
	.head_foot{
		font-weight: bold;
	}
	.title_menu_ul li{
		padding:0px;
		margin:0px;
		width:100px;
	}
	.system{
		display: none;
	}
	#tabCenter{
	    overflow:hidden!important;
	}
	.con_tit {
	    padding: 20px 40px 10px 40px;
	    height: auto;
	    font: 16px/24px "微软雅黑";
	    color: #363636;
	    text-align: center;
	}
	.con_time {
	    text-align: center;
	    font: 12px/20px "微软雅黑";
	    color: #555555;
	    padding: 10px 0;
	}
	#con_con {
	    min-height: 300px;
	    padding: 20px 0;
	    border-top: 1px solid #ff8e1a;
	}
	div#winconennt {
	    padding-left: 20px;
	    padding-right: 20px;
	}
	
</style>
<script type="text/javascript">
	var userId = "${userSession.userId}";
	//初始化
	$(document).ready(function(){
		  $("a[name=menu]").click(function(){
			  //设置隐藏全部
			  $("div[name=systemMenu]").hide();
			  var id = $(this).attr("id");
		 	  $("#menu_"+id).show();
		 	  $("#menu").panel({title:$(this).attr("title")});
		  });
		  //显示菜单
		  var length = $("a[name=menu]").length;
		  $($("a[name=menu]")[length-1]).click();
		  $(window).resize(function(){
		      var height = document.documentElement.clientHeight-164;
		      $(".tabHeightAuto iframe").attr("style","padding:0px;margin:0px;width:100%;height:"+height+"px;");
		  });
		  
		  
		
	});
	//启动定时任务
	self.setInterval("timer();",1000);
	//退出系统的方法
	function loginOut(){
		var param = getSystemParam();
		indexForm.target="other";
		indexForm.action = "j_spring_security_logout?param="+param;
		indexForm.submit();
	}
	//关于系统
	function about(){
		var url= "<%=basePath%>page/about/about.html";
		var window = openPageLocationURL(url,"other");
	}
</script>
  </head>
<body class="easyui-layout"  >  
<!-- background-image: url('./images/banner.jpg'); background-repeat:no-repeat;background-color:#65cea7; background-position:center;background-size: cover; f8f2ec-->
 	<div id="index_header" class="index_header" data-options="region:'north'" style="height:100px;width:100%;overflow:hidden;background-color:#E0FFFF;">
 		<div id="head_head" class="head_head" style="height:69%;width:100%;">
			<div id="head_head_left" class="head_head_left" style="height:100%;width:60%;text-align:center;">
				<!-- <img src="commons/images/logo.png" alt="" style="float: left;z-index:100;padding-top:20px;padding-left:20px;" /> -->
				<h1 style="padding-top:20px;font-size:28;font-weight:bold;">${WEBNAME}</h1>
			</div>
			<div id="head_head_right" class="head_head_right" style="height:100%;width:39%;">
				<div id="head_head_right_top" style="height:65%;width:100%;text-align: right;">
				    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true">欢迎您:<var><c:if test="${not empty userSession }">${userSession.username }</c:if></var></a>
				    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#userCenter',plain:true,iconCls:'icon-user-info'" >个人中心</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-factory'" target="_self" onclick="loginOut();">退出系统</a>&nbsp;
					    <div id="userCenter" style="width:80px;">
					        <div  data-options="iconCls:'icon-user-set'" onclick="editPassword()">修改密码</div>
					        <div  data-options="iconCls:'icon-user-set'" onclick="editUser()">修改个人信息</div>
					        <div  data-options="iconCls:'icon-user-set'" onclick="about()">关于系统</div>
					    </div>
				</div>
				<%-- <div id="head_head_right_foot" style="height:35%;width:100%;">
					<div style="width:10%;">&nbsp;&nbsp;</div>
					<span id="" style="font-size:14px;font-weight:bold;">
					&nbsp;&nbsp;欢迎您:<var><c:if test="${not empty userSession}">${userSession.systemUser.userName }</c:if></var>
					<!-- 当前时间:<code id="show_time" style="font-size:14px;">2015年8月30日  12时12分12秒</code> -->
					</span>
				</div> --%>
			</div>
		</div>
		<!-- 如果以后有三级菜单的时候放开这个作为一级菜单 -->
 		<div id="head_foot" class="head_foot" style="height:30%;width:100%;">
			<div id="head_head_right_top" style="height:65%;width:100%;text-align: right;">
			 	<c:set var="isNull" value="${empty menuList}"></c:set>
			    <c:if test="${not isNull}">
		         <!-- 加载主菜单栏信息 -->
				 <c:forEach var="menu" items="${menuList }" varStatus="i">
				 	 <!-- 为一级菜单时用 -->
				 	 <c:if test="${menu.menuFatherId == '0' and menu.menuChecked == 'true'}">
					 	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'${menu.menuIco }'" name="menu" id="${menu.menuId }" title="${menu.menuName }" >${menu.menuName }</a>&nbsp;
				 	 </c:if>
				 </c:forEach>
				 </c:if>
				<!-- <a href="javascript:void();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-large-clipart'" name="menu" title="model" >业务管理</a>&nbsp;	 -->
			</div>
		</div>
 	</div>  
    <div id="menu" data-options="region:'west',title:'菜单栏',split:true,iconCls:'icon-archive'" style="width:250px;">
    	<!-- 首先循环最外层 获取最外层的 信息来标示循环几次 -->
    	<!-- 加载主菜单栏信息 -->
	    <c:forEach var="root" items="${menuList }" varStatus="i">
		    <c:if test="${root.menuFatherId == '0'}">
		    	<div id="menu_${root.menuId }" name="systemMenu" class="easyui-accordion"  fit="true" >
					  	<!-- 加载二级菜单 -->
		   			<c:forEach var="second" items="${menuList }" varStatus="k">
		   				<c:if test="${second.menuFatherId == root.menuId and  second.menuChecked == 'true'}">
				    	  <div title="${second.menuName }"  class="${second.menuId }" data-options="iconCls:'icon-model'" style="overflow:auto;padding:10px;">  
					   		<ul id="menu_ul">
						   		 <c:forEach var="model" items="${menuList}" varStatus="j">
					   			 	<c:if test="${model.menuFatherId == second.menuId and model.menuChecked == 'true'}">
						   			 	<!-- 判断菜单是否为启用状态，如果为启用状态则显示 -->
						   				<li>
						   					<a href="javascript:void(0)" class="easyui-linkbutton" name="${model.menuUrl}" id="${model.menuId}" title="${model.menuName }" data-options="plain:true,iconCls:'icon-util-info'" onClick="openMenu(this);">${model.menuName }</a>
						   				</li>
					   			 	</c:if>
					   			 </c:forEach>
					   		</ul>
					      </div>  
					   </c:if>
		   			</c:forEach>
				</div>
		    </c:if>
    	  </c:forEach>
    </div>  
    <div id="tabCenter" data-options="region:'center',title:'信息栏',iconCls:'icon-util-system'" border="false" noheader="true" style="padding:0px;background:#eee;">
        <div id="tabs" class="easyui-tabs easyui-resizable" data-options="tools:'#tab-tools'" style="padding:0px;margin:0px;width:100%;height:auto">
        	<%-- <security:authentication property="principal"></security:authentication>
        	<security:authentication property="principal.userId"></security:authentication>
        	<security:authentication property="principal.username"></security:authentication>
        	<security:authentication property="principal.userSkin"></security:authentication>
        	<security:authentication property="principal.orgId"></security:authentication> --%>
    	</div>
    </div>
    <div id="tab-tools">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="exit()"></a>
    </div>
    <div data-options="region:'south'" border="false" noheader="false" style="height:30px;text-align: center;padding-top:5px;background:#E0FFFF">
    	${WEBFOOT}<code id="show_time" style="font-size:12px;  float:right;">2015年8月30日  12时12分12秒</code>&nbsp;&nbsp;&nbsp;&nbsp;
	   	 <!-- 模态界面 -->
		<div id="win" class="easyui-window" title="Basic Window"  data-options="modal:true,closed:true,iconCls:'icon-user-list',minimizable:false" style="width:70%;height:70%;padding:10px;overflow:hidden;">
			<iframe id="paramIframe" name="paramIframe" style="width:100%; height:100%;" frameborder="0" scrolling="no"  style="padding:0px;margin:0px;"></iframe>
		</div>
		<!-- 数据提交 -->
		<form id="indexForm" name="indexForm" action="" method="post" target="other" style="dispaly:none;padding:0px;margin:0px;">
			<input id="menuId" type="hidden" value="" name="menuURL" />
			<input id="userId" type="hidden" value="${userSession.userId }" name="userId" />
		</form> 
    </div> 
    <script type="text/javascript">
    /*打开添加用户页面*/
	function editUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"个人中心"});
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadCenterEditUser.html?param="+param;
   		indexForm.submit();
	}
    /*打开添加用户页面*/
	function editPassword(){
		$("#win").dialog("open");
		$("#win").panel({title:"个人中心",width:"40%",height:"50%"});
		$("#win").window("center");
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadEditPassword.html?param="+param;
   		indexForm.submit();
	}
	//打开菜单
    function openMenu(obj){
     	var exists = $("#add"+obj.id);
     	if(exists.html() == undefined){
     		 var height = $("#tabCenter").height()-35;
     		  $("#tabs").tabs("add",{
               	   id:"add"+obj.id,
                   title: obj.title,
                   iconCls:"icon-archive",
                   content: "<div style='padding:0px;margin:0px;'class='tabHeightAuto'><iframe name='tab"+obj.id+"' id='tab"+obj.id+"' src ='' style='padding:0px;margin:0px;width:100%;height:"+height+"px;' scrolling='no' frameborder='0'></iframe></div>",
                   closable: true,
               });
     		//获取菜单的编号
     		var indexs = obj.id.charAt(0);
   			var param = getSystemParam();
            indexForm.target="tab"+obj.id;
            document.getElementById("menuId").value = obj.name;
            indexForm.action = "menu/sendMenu.html?param="+param;
         	indexForm.submit();
     	}else{
     		//选中当前面板
     		$("#tabs").tabs("select",obj.title);
     	}
     }
 	//关闭菜单
	function exit(){
           var tab = $("#tabs").tabs("getSelected");
           if (tab){
               var index = $("#tabs").tabs("getTabIndex", tab);
               $("#tabs").tabs("close", index);
           }
	}
	function exitWin(){
		$("#win").dialog("close");
	}
    </script>
 <iframe id="other" name="other" src=""  style="padding:0px;margin:0px;display:none;"></iframe>  
</body>  
</html>
