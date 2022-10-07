/*格式化代码的js文件*/
/*格式化日期*/
function formatterDate(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
/*加密算法*/
function getSystemParam(){
	var date = new Date();
	//引用位运算
	var iUnsigned64 = -64 >>> 0;
	//然后，用 Number 类型的 toString() 获取它的真正的位表示，采用的基为 2：
	var param = $.md5(iUnsigned64.toString(2)+date);
	return param;
}
//拦截 ajax请求响应后是否session过期和异常错误 设置全局 AJAX 默认选项。
$.ajaxSetup({
  complete:function (XMLHttpRequest, textStatus) {
	   // 调用本次AJAX请求时传递的options参数
	   //console.log("complete-textStatus:"+textStatus);
	   if(XMLHttpRequest.getResponseHeader("Response-session") == -1){
    		$.messager.confirm("登录提示","登录失效，请重新登录",function(result){
    		    if (result){
    		    	window.top.location.reload();
    		    }
    		});
    		
      }else if(XMLHttpRequest.getResponseHeader("Response-error") == -1){
    		error("系统提示","异步请求数据失败, 请联系管理员！");
      }
  },
  error:function (XMLHttpRequest, textStatus, errorThrown) {
	    // 通常 textStatus 和 errorThrown 之中
	    // 只有一个会包含信息
	    // 调用本次AJAX请求时传递的options参数
	  console.log("error-textStatus:"+textStatus);
	  
  }
});

/*
 * type:查找的参数
 * select:seelct框的对象
 * index：默认选中参数 ""代表选中第一个请选择 
 * */
function loadSelectData(type,select,index){
	$.post("dictionary/getCode.json", { dictionaryType: type },
			   function(data){
				 var obj = new Object();
				 obj.dictionaryName = "请选择";
				 obj.dictionaryCode = "";
			     data.unshift(obj);
			     $(select).combobox({
			        data:data,
			        valueField:"dictionaryCode",
			        textField:"dictionaryName",
			        panelHeight:200,
			        onLoadSuccess:function(){
			        	//默认选中
					    $(this).combobox("select",index);
			        }
			     });
		 });
}


/*重新弹出一个页面的方法*/
function openPageLocationURL(action,target){
   //var h = window.screen.height-window.screen.availHeight;
    //设置窗体初始的大小
   var windowWidth = window.screen.availWidth; //document.documentElement.scrollWidth-5;
   var windowHeight = window.screen.availHeight-30;//document.documentElement.scrollHeight;
   var iLeft = (window.screen.width - windowWidth)/2; //获得窗口的水平位置; 
   //判断屏幕的分辨率
  // if(window.screen.height <= 768 || window.screen.width <= 1366){
    	windowWidth = windowWidth -9; 
   //}else{
   	//	windowWidth  = windowWidth - 200;
   //		iLeft = (window.screen.width - windowWidth)/2;
  // }
   var myWindow = window.open(action,target,"toolbar=no, location=no, top=0, left="+iLeft+" directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width="+windowWidth+", height="+windowHeight+"");
  return myWindow;
}
/*模态弹出页面
    <div id="model-window" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
		<iframe name="windowIframe" id="windowIframe" frameborder="0" height="100%" width="100%" src="" style="overflow: hidden;"></iframe>
	</div>
*/
function openModelWindow(url){
  	var windowWidth = document.documentElement.scrollWidth;
  	var windowHeight = document.documentElement.scrollHeight;
  	$("#model-window").window({
		 width:windowWidth,
		 height:windowHeight,
		 top:0,
		 left:0,
		 modal:true
	});
  	$("#model-window").window("open");
  	$("#windowIframe").attr("src",url);
}