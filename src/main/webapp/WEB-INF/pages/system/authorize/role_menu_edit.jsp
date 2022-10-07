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
    <script type="text/javascript" src="./plugin/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./plugin/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="./plugin/md5/jquery.md5.js"></script>
    <script type="text/javascript" src="./commons/js/formatterUtil.js"></script>
	<script type="text/javascript" src="./commons/js/message.js"></script>
	<style type="text/css">
		
	</style>
  </head>
<body class="easyui-layout" style="padding:0px;margin:0px;" >  
    <div data-options="region:'center',title:'角色菜单配置',iconCls:'icon-user-add'" >
 	  <div style="width:100%;height:auto;padding:3px 0px 3px 0px;;text-align:right;">
 	  	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveRoleMenu()">保&nbsp;存</a>
    	<a id="" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-factory'" onclick="parent.exit();">退&nbsp;出</a>&nbsp;&nbsp;  
  	  </div>
   	  <div id="form_div" class="easyui-panel" data-options="iconCls:'icon-user-list',title:'角色菜单配置'" border="false"  noheader="true"  fit="true" style="width:100%;height:100%;padding:0px 0px 32px 0px;text-align:center;">
   		   <!-- begin easyui-tabs  -->
   		     <div class="easyui-tabs" fit="true" >
		        	<!-- 循环root -->
		        	 <!-- 加载主菜单栏信息 -->
					 <c:forEach var="root" items="${menuList }" varStatus="i">
					 	 <!-- 判断是否为一级菜单 -->
					 	 <c:if test="${root.menuFatherId == '0' }">
					 	    <!-- begin tab -->
		       				<div title="${root.menuName }"  data-options="iconCls:'icon-util-info'">
							     <div class="easyui-accordion" id="accordion-${root.menuId }"  fit=true  data-options="multiple:true"   style="padding:0 20px 0 20px;overflow-y:auto;background:#F5F5F5;">
						          	  <div title="菜单" data-options="iconCls:'icon-search',collapsed:false,collapsible:false" style="padding:10px;">
											<div style=" width:100%;" >
							                	<input class="checkbox-root easyui-checkbox" type="checkbox" name="firstMenu" id="${root.menuId }" value="${root.menuId }" /> 
												<label for="firstMenu" >${root.menuName }<strong style="color:red"></strong></label> 
										    </div>
									  </div>
						          		<!-- 菜单 -->
						          		<c:forEach var="second" items="${menuList }" varStatus="k">
							   				<c:if test="${second.menuFatherId == root.menuId}">
												<div title="${second.menuName }" data-options="iconCls:'icon-ok'" fit=true  id="div-menu-${second.menuId }" >
													<div style="padding:10px; width:100%;" >
									                	<input class="checkbox-second  easyui-checkbox" type="checkbox"  model-id="${root.menuId }" name="secondMenu" id="${second.menuId }" value="${second.menuId }"  /> 
													    <label for="secondMenu" >${second.menuName }</label> 
												    </div>
												    <!-- 菜单 -->
												    <c:forEach var="model" items="${menuList}" varStatus="j">
									   			 	<c:if test="${model.menuFatherId == second.menuId }">
									   			 	<div style="padding:5px 0 5px 40px;width:100%;">
									   			 		<div >
						 									<input class="checkbox-model easyui-checkbox" type="checkbox"  name="thirdMenu" model-id="${second.menuId }" id="${model.menuId }" value="${model.menuId }" /> 
															<label for="thirdMenu">${model.menuName }</label>  
														</div>
														<!-- 按钮 -->
														<div class="div-button" style="padding:5px 0 5px 40px;" id="div-button-${model.menuId }" title="${model.menuId }" >
															<c:forEach var="button" items="${buttonList}" varStatus="s">
																<c:if test="${button.menuId == model.menuId }">
																	<div class="model-button" style="width:100%;padding:5px 0 5px 0;">
									 									<input class="checkbox-button easyui-checkbox" type="checkbox" name="thirdButton"  model-id="${model.menuId }" id="${button.buttonId }" value="${button.buttonId }" /> 
																		<label for="thirdButton">${button.buttonName }</label> 
																	</div>
																</c:if>
															</c:forEach>
														</div>
									   			 	</div>
									   			 	</c:if>
									   			  </c:forEach>
												</div>
											</c:if>
										</c:forEach>
								</div>
					        </div>
					        <!-- end tab -->
					        
					 	 </c:if>
					 </c:forEach>
		    </div>
		    <!-- begin easyui-tabs  -->
		    <form id="paramForm" method="post" name="paramForm" action="" style="padding:0px;margin:0px;">  
		    	<input type="hidden" name="menuArray" value="" id="menuArray" />
		    	<input type="hidden" name="mappedMenuArray" value="" id="mappedMenuArray" />
		    	<input type="hidden" name="roleId" value="${roleId }" id="roleId" />
		    	<input type="hidden" name="orgId" value="${orgId }" id="orgId" />
		    </form>
      </div>
    </div>
    	<script type="text/javascript">
  		$(document).ready(function(){
  			loadRoleMenu();
  			  
  			//判断 顶级菜单是否选中
  		    $('.checkbox-root').checkbox({onChange:function(checked){
  		  		var rootId = $(this).attr("id");
  		  		if(checked){//选中
	  		  		$("#accordion-"+rootId).find(".checkbox-second").each(function(index,obj){
			  			$(this).checkbox({checked: true});
	  		  		});
	  		  		$("#accordion-"+rootId).find(".checkbox-model").each(function(index,obj){
			  			$(this).checkbox({checked: true});
	  		  		});
	  		  		$("#accordion-"+rootId).find(".checkbox-button").each(function(index,obj){
			  			$(this).checkbox({checked: true});
	  		  		});
  		  		}else{//未选中
	  		  		$("#accordion-"+rootId).find(".checkbox-second").each(function(index,obj){
		  				$(this).checkbox({checked: false});
			  		});
	  		  		$("#accordion-"+rootId).find(".checkbox-model").each(function(index,obj){
		  				$(this).checkbox({checked: false});
			  		});
	  		  		$("#accordion-"+rootId).find(".checkbox-button").each(function(index,obj){
		  				$(this).checkbox({checked: false});
			  		});
  		  		}
  		    }});
  			
  			//判断二级菜单
  		    $('.checkbox-second').checkbox({onChange:function(checked){
  		    	//console.log("二级菜单："+checked);
  		    	var modelId = $(this).attr("id");
  		    	//获取父节点编号
  		  		var parentId = $(this).attr("model-id");
  		  		//console.log(parentId);
  		  		if(checked){
  		  			$("#div-menu-"+modelId).find(".checkbox-model").each(function(index,obj){
  		  				$(this).checkbox({checked: true});
	  		  		});
		  			$("#div-menu-"+modelId).find(".checkbox-button").each(function(index,obj){
		  				$(this).checkbox({checked: true});
			  		});
  		  			//选中它的父级 
  		  			$("#"+parentId).checkbox({checked: true});
  		  			//获取祖父级
  			  		var rootId = $("#"+parentId).attr("model-id");
  		  			//选中祖父级
  		  			$("#"+rootId).checkbox({checked: true});
  		  		}else{//未选中
	  		  		$("#div-menu-"+modelId).find(".checkbox-model").each(function(index,obj){
			  			$(this).checkbox({checked: false});
	  		  		});
		  			$("#div-menu-"+modelId).find(".checkbox-button").each(function(index,obj){
		  				$(this).checkbox({checked: false});
			  		});
		  			var isFind = false;
		  			//console.log("父菜单："+parentId);
		  			// 判断是否存在选中的二级菜单 如果存在则顶级菜单选中
		  			var arrays = $("#accordion-"+parentId).find(".checkbox-second");
		  			//console.log("子菜单数量："+arrays.length);
		  			for(i = 0; i< arrays.length;i++){
		  				//console.log("编号："+arrays[i].id+"选中状态："+ $(arrays[i]).checkbox("options").checked);
		  				var check = $(arrays[i]).checkbox("options").checked;
		  				if(check){
		  					isFind = true;
		  					break;
		  				}
		  			}
		  			if(isFind){
		  				//选中祖父级
	  		  			$("#"+parentId).checkbox({checked: true});
		  			}else{
		  				//选中祖父级
	  		  			$("#"+parentId).checkbox({checked: false});
		  			}
		  			
  		  		}
  		  		
  		    }});
  			
  			//判断三级菜单
  		    $('.checkbox-model').checkbox({onChange:function(checked){
  		    	//console.log("三级菜单："+checked);
  		  		var modelId = $(this).attr("id");
  		  		//获取父节点编号
  		  		var parentId = $(this).attr("model-id");
  		  		//获取祖父级
			  	var rootId = $("#"+parentId).attr("model-id");
  		  		if(checked){
  		  			$("#div-button-"+modelId).find(".checkbox-button").each(function(index,obj){
  		  				$(this).checkbox({checked: true});
	  		  		});
  		  			//选中它的父级 
  		  			$("#"+parentId).checkbox({checked: true});
  		  			//选中它的祖父级
  		  			var rootId = $("#"+parentId).attr("model-id");
  		  			$("#"+rootId).checkbox({checked: true});
  		  		}else{//取消选中
	  		  		$("#div-button-"+modelId).find(".checkbox-button").each(function(index,obj){
			  			$(this).checkbox({checked: false});
	  		  		});
	  		  		var isFind = false;
	  		  		//console.log("父菜单："+parentId);
  		  			//判断同级菜单是否有选中 如果有选中 二级菜单默认不重置
		  		  	var arrays = $("#div-menu-"+parentId).find(".checkbox-model");
		  		  	for(i = 0; i< arrays.length;i++){
		  				console.log("编号："+arrays[i].id+"选中状态："+ $(arrays[i]).checkbox("options").checked);
		  				var check = $(arrays[i]).checkbox("options").checked;
		  				if(check){
		  					isFind = true;
		  					break;
		  				}
		  			}
		  			if(isFind){
		  				//选中父级
	  		  			$("#"+parentId).checkbox({checked: true});
	  		  			//选中祖父级
	  		  			$("#"+rootId).checkbox({checked: true});
		  			}else{
		  				//选中父级
	  		  			$("#"+parentId).checkbox({checked: false});
	  		  			var isSecondFind = false;
		  				//判断其他父菜单同级是否有选中
		  				var secondArrays = $("#accordion-"+rootId).find(".checkbox-second");
		  				for(i = 0; i< secondArrays.length;i++){
			  				//console.log("编号："+secondArrays[i].id+"选中状态："+ $(secondArrays[i]).checkbox("options").checked);
			  				var check = $(secondArrays[i]).checkbox("options").checked;
			  				if(check){
			  					isSecondFind = true;
			  					break;
			  				}
		  				}
		  				if(isSecondFind){
		  		  			//选中祖父级
		  		  			$("#"+rootId).checkbox({checked: true});
		  				}else{
		  					$("#"+rootId).checkbox({checked: false});
		  				}
		  			}
  		  			
  		  		}
  		    }});
  			
  			//判断按钮
  		    $('.checkbox-button').checkbox({onChange:function(checked){
  		  		//console.log("按钮："+checked);
  		  		//获取父节点编号
  		  		var parentId = $(this).attr("model-id")
  		  		//选中它的祖父级
  		  		var secondId = $("#"+parentId).attr("model-id");
  		  		//选中它的祖祖父级
		  		var rootId = $("#"+secondId).attr("model-id");
  		  		if(checked){
  		  			//选中按钮则选中它的父级
  		  			$("#"+parentId).checkbox({checked: true});
  		  			
  		  			$("#"+secondId).checkbox({checked: true});
  		  			
  		  			$("#"+rootId).checkbox({checked: true});
  		  		}else{
  		  			var isFind = false;
  		  			//此处需要判断 当按钮取消选中时 是否同级还有选中的按钮
  		  			var buttonArray = $("#div-button-"+parentId).find(".checkbox-button");
  		  			for(i=0;i<buttonArray.length;i++){
  		  				console.log("编号："+buttonArray[i].id+"选中状态："+ $(buttonArray[i]).checkbox("options").checked);
  		  				isFind = $(buttonArray[i]).checkbox("options").checked;
		  				if(isFind){
		  					isFind = true;
		  					break;
		  				}
  		  			}
  		  			if(isFind == false){
  		  				//父级取消选中
  		  				$("#"+parentId).checkbox({checked: false});
  		  				var isSecondFind = false;
  		  				//当前按钮同级的父级的同级是否有选中
	  		  			var secondArray = $("#div-menu-"+secondId).find(".checkbox-model");
		  		  		for(i=0;i<secondArray.length;i++){
	  		  				//console.log("编号："+secondArray[i].id+"选中状态："+ $(secondArray[i]).checkbox("options").checked);
	  		  				isSecondFind = $(secondArray[i]).checkbox("options").checked;
			  				if(isSecondFind){
			  					isSecondFind = true;
			  					break;
			  				}
	  		  			}
		  		  		if(isSecondFind == false){
		  		  			$("#"+secondId).checkbox({checked: false});
		  		  			//判断同级是否选中
			  		  		var isNodeFind = false;
			  				//判断其他父菜单同级是否有选中
			  				var secondArrays = $("#accordion-"+rootId).find(".checkbox-second");
			  				for(i = 0; i< secondArrays.length;i++){
				  				//console.log("编号："+secondArrays[i].id+"选中状态："+ $(secondArrays[i]).checkbox("options").checked);
				  				var check = $(secondArrays[i]).checkbox("options").checked;
				  				if(check){
				  					isNodeFind = true;
				  					break;
				  				}
			  				}
			  				if(isNodeFind == false){
			  		  			//选中祖父级
			  		  			$("#"+rootId).checkbox({checked: false});
			  				}
		  		  		}
  		  			}
  		  		}
  		    }});
  			  
		});
  		
  		
  		/*加载角色菜单关系*/
  		function loadRoleMenu(){
  			var param = getSystemParam();
  			$.post("authorize/getRoleMenu.json?param="+param, $("#paramForm").serialize(),
 				   function(data){
	  					var menuMappedList = data.menuMappedList;
	  					var buttonMappedList = data.buttonMappedList;
	  					//菜单
	  					for(var i = 0;i< menuMappedList.length;i++){
	  						//选中菜单
	  					    $("#"+menuMappedList[i].menuId).checkbox({checked: true});
	  					}
	  					//菜单按钮
	  					for(var i = 0;i< buttonMappedList.length;i++){
	  						//选中菜单
	  					    $("#"+buttonMappedList[i].buttonId).checkbox({checked: true});
	  					}
	  					
 				   });
  		}
  		
  		/*保存角色对应菜单关系*/
  		function saveRoleMenu(){
  			//一级菜单
  			var firstArray = "";
  			var buttonList = "";
  			//一级菜单
  			$("input[name=firstMenu]").each(function(index,object){
  				//console.log("一级菜单："+object.value);
  				if(object.checked){
  					firstArray = firstArray +object.value + "-";
  				}
  			});
  			//二级菜单
  			$("input[name=secondMenu]").each(function(index,object){
  				//console.log("二级菜单："+object.value);
  				if(object.checked){
  					firstArray = firstArray +object.value + "-";
  				}
  			});
  		  
  		   //三级菜单
  		   $("input[name=thirdMenu]").each(function(index,object){
  				//console.log("二级菜单："+object.value);
  				if(object.checked){
  					firstArray = firstArray +object.value + "-";
  				}
  			});
  		   console.log("菜单数组："+firstArray);
  		   //三级菜单的按钮
  		   
  		   $(".div-button").each(function(index,object){
  				var parent = object.title;
  				//console.log("三级菜单按钮父节点："+ parent);
  				//判断当前父节点是否选中
  				var check = $("#"+parent).checkbox("options").checked;
  				if(check){
  					var buttonArray = "";
  	 			    //console.log("三级菜单按钮父节点："+ parent);
  	  				$(object).find("input[name=thirdButton]").each(function(index,button){
  	  					//console.log("三级菜单按钮对象："+button.value+"==checked:"+button.checked);
  	  					if(button.checked){
  	  						buttonArray = buttonArray + button.value + ",";
  	  	  				}
  	  					//console.log("三级菜单按钮："+ buttonArray);
  	  				});
  	  				//console.log("三级菜单按钮："+ buttonArray);
  	  				if(buttonArray != null && buttonArray != ""){
  	  					buttonList = buttonList + (parent + "-" + buttonArray) +"@";
  	 			    }
  				}
  			   
  		   });
  		   //console.log("buttonList："+ buttonList);
		   $("#mappedMenuArray").val(buttonList);
		   $("#menuArray").val(firstArray);
		   var param = getSystemParam();
		   $.post("authorize/editRoleMenu.json?param="+param, $("#paramForm").serialize(),
				  function(data){
			   		if(data.result > 0){
			   			info("角色菜单配置","配置角色菜单权限成功！");
			   		}else{
			   			info("角色菜单配置","配置角色菜单权限失败！");
			   		}
		   });
		   
  		}
	</script>
    <iframe id="other" name="other" src=""  style="padding:0px;margin:0px;display:none;"></iframe>  
</body>  
</html>