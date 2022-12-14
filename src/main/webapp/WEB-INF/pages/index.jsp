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
	a:link {color: #000000}     /* ?????????????????? */
	a:visited {color: #000000}  /* ?????????????????? */
	a:hover {color: #FF00FF}    /* ?????????????????????????????? */
	a:active {color: #0000FF}   /* ?????????????????? */
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
	    font: 16px/24px "????????????";
	    color: #363636;
	    text-align: center;
	}
	.con_time {
	    text-align: center;
	    font: 12px/20px "????????????";
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
	//?????????
	$(document).ready(function(){
		  $("a[name=menu]").click(function(){
			  //??????????????????
			  $("div[name=systemMenu]").hide();
			  var id = $(this).attr("id");
		 	  $("#menu_"+id).show();
		 	  $("#menu").panel({title:$(this).attr("title")});
		  });
		  //????????????
		  var length = $("a[name=menu]").length;
		  $($("a[name=menu]")[length-1]).click();
		  $(window).resize(function(){
		      var height = document.documentElement.clientHeight-164;
		      $(".tabHeightAuto iframe").attr("style","padding:0px;margin:0px;width:100%;height:"+height+"px;");
		  });
		  
		  
		
	});
	//??????????????????
	self.setInterval("timer();",1000);
	//?????????????????????
	function loginOut(){
		var param = getSystemParam();
		indexForm.target="other";
		indexForm.action = "j_spring_security_logout?param="+param;
		indexForm.submit();
	}
	//????????????
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
				    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true">?????????:<var><c:if test="${not empty userSession }">${userSession.username }</c:if></var></a>
				    <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#userCenter',plain:true,iconCls:'icon-user-info'" >????????????</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-factory'" target="_self" onclick="loginOut();">????????????</a>&nbsp;
					    <div id="userCenter" style="width:80px;">
					        <div  data-options="iconCls:'icon-user-set'" onclick="editPassword()">????????????</div>
					        <div  data-options="iconCls:'icon-user-set'" onclick="editUser()">??????????????????</div>
					        <div  data-options="iconCls:'icon-user-set'" onclick="about()">????????????</div>
					    </div>
				</div>
				<%-- <div id="head_head_right_foot" style="height:35%;width:100%;">
					<div style="width:10%;">&nbsp;&nbsp;</div>
					<span id="" style="font-size:14px;font-weight:bold;">
					&nbsp;&nbsp;?????????:<var><c:if test="${not empty userSession}">${userSession.systemUser.userName }</c:if></var>
					<!-- ????????????:<code id="show_time" style="font-size:14px;">2015???8???30???  12???12???12???</code> -->
					</span>
				</div> --%>
			</div>
		</div>
		<!-- ?????????????????????????????????????????????????????????????????? -->
 		<div id="head_foot" class="head_foot" style="height:30%;width:100%;">
			<div id="head_head_right_top" style="height:65%;width:100%;text-align: right;">
			 	<c:set var="isNull" value="${empty menuList}"></c:set>
			    <c:if test="${not isNull}">
		         <!-- ???????????????????????? -->
				 <c:forEach var="menu" items="${menuList }" varStatus="i">
				 	 <!-- ????????????????????? -->
				 	 <c:if test="${menu.menuFatherId == '0' and menu.menuChecked == 'true'}">
					 	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'${menu.menuIco }'" name="menu" id="${menu.menuId }" title="${menu.menuName }" >${menu.menuName }</a>&nbsp;
				 	 </c:if>
				 </c:forEach>
				 </c:if>
				<!-- <a href="javascript:void();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-large-clipart'" name="menu" title="model" >????????????</a>&nbsp;	 -->
			</div>
		</div>
 	</div>  
    <div id="menu" data-options="region:'west',title:'?????????',split:true,iconCls:'icon-archive'" style="width:250px;">
    	<!-- ????????????????????? ?????????????????? ??????????????????????????? -->
    	<!-- ???????????????????????? -->
	    <c:forEach var="root" items="${menuList }" varStatus="i">
		    <c:if test="${root.menuFatherId == '0'}">
		    	<div id="menu_${root.menuId }" name="systemMenu" class="easyui-accordion"  fit="true" >
					  	<!-- ?????????????????? -->
		   			<c:forEach var="second" items="${menuList }" varStatus="k">
		   				<c:if test="${second.menuFatherId == root.menuId and  second.menuChecked == 'true'}">
				    	  <div title="${second.menuName }"  class="${second.menuId }" data-options="iconCls:'icon-model'" style="overflow:auto;padding:10px;">  
					   		<ul id="menu_ul">
						   		 <c:forEach var="model" items="${menuList}" varStatus="j">
					   			 	<c:if test="${model.menuFatherId == second.menuId and model.menuChecked == 'true'}">
						   			 	<!-- ?????????????????????????????????????????????????????????????????? -->
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
    <div id="tabCenter" data-options="region:'center',title:'?????????',iconCls:'icon-util-system'" border="false" noheader="true" style="padding:0px;background:#eee;">
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
    	${WEBFOOT}<code id="show_time" style="font-size:12px;  float:right;">2015???8???30???  12???12???12???</code>&nbsp;&nbsp;&nbsp;&nbsp;
	   	 <!-- ???????????? -->
		<div id="win" class="easyui-window" title="Basic Window"  data-options="modal:true,closed:true,iconCls:'icon-user-list',minimizable:false" style="width:70%;height:70%;padding:10px;overflow:hidden;">
			<iframe id="paramIframe" name="paramIframe" style="width:100%; height:100%;" frameborder="0" scrolling="no"  style="padding:0px;margin:0px;"></iframe>
		</div>
		<!-- ???????????? -->
		<form id="indexForm" name="indexForm" action="" method="post" target="other" style="dispaly:none;padding:0px;margin:0px;">
			<input id="menuId" type="hidden" value="" name="menuURL" />
			<input id="userId" type="hidden" value="${userSession.userId }" name="userId" />
		</form> 
    </div> 
    <script type="text/javascript">
    /*????????????????????????*/
	function editUser(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????"});
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadCenterEditUser.html?param="+param;
   		indexForm.submit();
	}
    /*????????????????????????*/
	function editPassword(){
		$("#win").dialog("open");
		$("#win").panel({title:"????????????",width:"40%",height:"50%"});
		$("#win").window("center");
	   	var param = getSystemParam();
       	indexForm.target= "paramIframe";
        indexForm.action = "user/loadEditPassword.html?param="+param;
   		indexForm.submit();
	}
	//????????????
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
     		//?????????????????????
     		var indexs = obj.id.charAt(0);
   			var param = getSystemParam();
            indexForm.target="tab"+obj.id;
            document.getElementById("menuId").value = obj.name;
            indexForm.action = "menu/sendMenu.html?param="+param;
         	indexForm.submit();
     	}else{
     		//??????????????????
     		$("#tabs").tabs("select",obj.title);
     	}
     }
 	//????????????
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
